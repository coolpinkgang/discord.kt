package io.github.romangraef.discordkt.event

import kotlin.reflect.KClass


interface Event

interface EventHandler<T : Event> {
    suspend fun invoke(event: T)

    companion object {
        fun <T : Event> from(handler: suspend (T) -> Unit) = object : EventHandler<T> {
            override suspend fun invoke(event: T) {
                handler(event)
            }
        }
    }
}

class ExceptionInHandlerEvent(val throwable: Throwable, val handler: EventHandler<Event>) : Event

/**
 * Event loop for coroutines.
 */
interface AbstractEventLoop {
    /**
     * Distribute an Event to all listeners that listen to that event, or to a subclass of
     *
     * @param event The event to be distributed
     */
    suspend fun emit(event: Event)

    /**
     * Register a handler for an Event and all subclasses
     *
     * @param kClass the kotlin class of the upper bound of the events to handle
     * @param handler the handler to invoke for each event
     */
    fun <T : Event> on(kClass: KClass<out T>, handler: EventHandler<T>)

    /**
     * Register a handler for an event with an inline function
     *
     * @see on(KClass, EventHandler)
     */
    fun <T : Event> on(kClass: KClass<out T>, handler: suspend (T) -> Unit) {
        on(kClass, EventHandler.from(handler))
    }

    /**
     * Register a handler for exceptions thrown in handlers
     * @see on(KClass, EventHandler)
     * @see ExceptionInHandlerEvent
     */
    fun onException(handler: EventHandler<ExceptionInHandlerEvent>) {
        on(ExceptionInHandlerEvent::class, handler)
    }

    /**
     * Register a handler for exceptions thrown in handlers with an inline function
     *
     * @see onException(EventHandler)
     */
    fun onException(handler: suspend (ExceptionInHandlerEvent) -> Unit) {
        onException(EventHandler.from(handler))
    }
}

/**
 * @see AbstractEventLoop.on
 */
inline fun <reified T : Event> AbstractEventLoop.on(handler: EventHandler<T>) {
    on(T::class, handler)
}

/**
 * @see AbstractEventLoop.on
 */
inline fun <reified T : Event> AbstractEventLoop.on(noinline handler: suspend (T) -> Unit) {
    on(EventHandler.from(handler))
}

class LateProxyEventLoop : AbstractEventLoop {
    private var proxy: AbstractEventLoop? = null
    private val cachedHandlers =
        mutableMapOf<KClass<out Event>, MutableList<EventHandler<Event>>>().withDefault { mutableListOf() }

    fun initProxy(loop: AbstractEventLoop) {
        cachedHandlers.forEach { (k, hs) ->
            hs.forEach { h ->
                loop.on(k, h)
            }
        }
        proxy = loop
    }

    override fun <T : Event> on(kClass: KClass<out T>, handler: EventHandler<T>) {
        val p = proxy
        if (p != null) {
            p.on(kClass, handler)
        } else {
            cachedHandlers[kClass]!!.add(handler as EventHandler<Event>)
        }
    }

    override suspend fun emit(event: Event) {
        proxy?.emit(event) ?: throw RuntimeException("Proxy of LateProxyEventLoop has not been initialized")
    }

}

class EventLoop : AbstractEventLoop {

    // Real type Map<KClass<T>, List<Handler<T>>
    private val handlers =
        mutableMapOf<KClass<out Event>, MutableList<EventHandler<Event>>>().withDefault { mutableListOf() }

    override fun <T : Event> on(kClass: KClass<out T>, handler: EventHandler<T>) {
        handlers[kClass]!!.add(handler as EventHandler<Event>)
    }

    override suspend fun emit(event: Event) {
        val exceptionsThrown = mutableListOf<ExceptionInHandlerEvent>()
        for ((kClass, handlers) in handlers) {
            if (kClass.isInstance(event))
                for (handler in handlers) {
                    try {
                        handler.invoke(event)
                    } catch (e: Throwable) {
                        exceptionsThrown += ExceptionInHandlerEvent(e, handler)
                    }
                }
        }
        for (exc in exceptionsThrown)
            if (event !is ExceptionInHandlerEvent) {
                emit(exc)
            } else {
                System.err.println("Exception while handling other exception in event loop; Original exception: ")
                event.throwable.printStackTrace()
                System.err.println("Handling this exception caused another exception: ")
                exc.throwable.printStackTrace()
            }
    }
}
