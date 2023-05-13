package domain.image

import java.awt.image.BufferedImage
import java.io.File
import javax.imageio.ImageIO

interface ImageData {

    fun printImageInfo()

    enum class TransparencyValues {
        OPAQUE, BITMASK, TRANSLUCENT
    }

    data class Base(
        val imagePath: String,
        val imageFile: File,
    ) : ImageData {

        val image: BufferedImage = ImageIO.read(imageFile)
        val width: Int get() = image.width
        val height: Int get() = image.height
        private val numOfComponents: Int get() = image.colorModel.numComponents
        val numOfColorComponents: Int get() = image.colorModel.numColorComponents
        val bitsPerPixel: Int get() = image.colorModel.pixelSize
        val transparency: TransparencyValues
            get() = when (image.transparency) {
                1 -> TransparencyValues.OPAQUE
                2 -> TransparencyValues.BITMASK
                3 -> TransparencyValues.TRANSLUCENT
                else -> throw IllegalArgumentException("Invalid value of transparency")
            }

        override fun toString(): String {
            return """Image file: $imagePath
            |Width: $width
            |Height: $height
            |Number of components: $numOfComponents
            |Number of color components: $numOfColorComponents
            |Bits per pixel: $bitsPerPixel
            |Transparency: ${transparency.name}
        """.trimMargin()
        }

        override fun printImageInfo() = println(this)
    }
}