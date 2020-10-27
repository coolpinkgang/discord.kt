package dev.discordkt.models.serial

import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import java.io.File
import java.util.*

@Serializable(with = ImageData.Serializer::class)
class ImageData private constructor(val format: Format, val content: ByteArray) {
    fun toURIEncoded() = "data:${format.htmlType};base64,${Base64.getEncoder().encodeToString(content)}"
    class Serializer : KSerializer<ImageData> {
        override val descriptor: SerialDescriptor = PrimitiveSerialDescriptor("ImageData", PrimitiveKind.STRING)
        override fun deserialize(decoder: Decoder): ImageData = of(decoder.decodeString())
        override fun serialize(encoder: Encoder, value: ImageData) = encoder.encodeString(value.toURIEncoded())
    }
    enum class Format(val htmlType: String, vararg val extensions: String) {
        JPG("image/jpeg", "jpg", "jpeg"),
        GIF("image/gif", "gif"),
        PNG("image/png", "png");
        companion object {
            fun of(fileExtension: String) = values().find { it.extensions.contains(fileExtension) } ?:
                throw UnsupportedFormatException(fileExtension)
        }
        class UnsupportedFormatException(extension: String) :
            Exception("The extension '.$extension' does not correspond with any of the supported image formats")
    }
    companion object {
        fun of(format: Format, content: ByteArray) = ImageData(format, content)
        fun of(file: File) = of(Format.of(file.extension), file.readBytes())
        fun of(string: String) = of(
            Format.of(string.substring(string.indexOf(':') + 1, string.indexOf(';') - 1)),
            Base64.getDecoder().decode(string.substring(string.indexOf(',') + 1))
        )
    }
}
