package dev.discordkt.api

import java.io.OutputStream

/**
 *
 */
class Logger(val outputStreams: MutableMap<OutputStream, Level>) {

    /**
     * Logs message to all {@link OutputStream} with a higher or the same logging level
     *
     * @param message the message to be logged
     * @param level the threshold level for OutputStreams
     */
    fun log(message: String, level: Level) = outputStreams
        .filter { it.value >= level }
        .forEach { it.key.write(message.toByteArray()) }

    /**
     * Logs a message to all {@link OutputSteam} with a higher or the debug logging level
     *
     * @param message the message to be logged
     * @see log
     */
    fun debug(message: String) = log(message, Level.DEBUG)

    /**
     * Logs a message to all {@link OutputSteam} with a higher or the information logging level
     *
     * @param message the message to be logged
     * @see log
     */
    fun info(message: String) = log(message, Level.INFORMATION)

    /**
     * Logs a message to all {@link OutputSteam} with a higher or the warning logging level
     *
     * @param message the message to be logged
     * @see log
     */
    fun warn(message: String) = log(message, Level.WARNING)

    /**
     * Logs a message to all {@link OutputSteam} with a higher or the error logging level
     *
     * @param message the message to be logged
     * @see log
     */
    fun error(message: String) = log(message, Level.ERROR)

    /**
     *
     */
    enum class Level {
        DEBUG,
        INFORMATION,
        WARNING,
        ERROR
    }
}
