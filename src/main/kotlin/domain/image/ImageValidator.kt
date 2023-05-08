package domain.image

import model.FileType
import kotlin.system.exitProcess

interface ImageValidator {

    fun validate()

    fun compareDimensions(otherImageData: ImageData.Base)

    class Base(private val imageData: ImageData.Base, private val imageType: FileType) : ImageValidator {

        override fun validate() {
            if (imageData.numOfColorComponents != 3) {
                println("The number of ${imageType.name.lowercase()} color components isn't 3.")
                exitProcess(2)
            }
            if (imageData.bitsPerPixel != 24 && imageData.bitsPerPixel != 32) {
                println("The ${imageType.name.lowercase()} isn't 24 or 32-bit.")
                exitProcess(3)
            }
        }

        override fun compareDimensions(otherImageData: ImageData.Base) {
            if (otherImageData.width != imageData.width || otherImageData.height != imageData.height) {
                println("The image and watermark dimensions are different.")
                exitProcess(7)
            }
        }
    }
}