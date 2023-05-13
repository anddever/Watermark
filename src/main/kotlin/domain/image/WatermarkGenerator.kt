package domain.image

import model.FileName
import model.TypeOfBlending
import java.awt.Color
import java.awt.image.BufferedImage
import java.io.File
import javax.imageio.ImageIO

interface WatermarkGenerator {

    fun blendImages(): BufferedImage

    fun saveWatermarkedImage()

    class Base(
        private val originalImage: ImageData.Base,
        private val watermarkImage: ImageData.Base,
        private val watermarkWeight: Int,
        private val outputFileName: FileName,
        private val typeOfBlending: TypeOfBlending
    ) : WatermarkGenerator {

        override fun blendImages(): BufferedImage {
            val useAlpha = typeOfBlending == TypeOfBlending.WITH_ALPHA_CHANNEL
            val outputImage = BufferedImage(originalImage.width, originalImage.height, BufferedImage.TYPE_INT_RGB)
            for (x in 0 until originalImage.width) {
                for (y in 0 until originalImage.height) {
                    val inputPixel = Color(originalImage.image.getRGB(x, y))
                    val watermarkPixel = Color(watermarkImage.image.getRGB(x, y), useAlpha)
                    val color = if (useAlpha && watermarkPixel.alpha == 0) {
                        inputPixel
                    } else {
                        Color(
                            (watermarkWeight * watermarkPixel.red + (100 - watermarkWeight) * inputPixel.red) / 100,
                            (watermarkWeight * watermarkPixel.green + (100 - watermarkWeight) * inputPixel.green) / 100,
                            (watermarkWeight * watermarkPixel.blue + (100 - watermarkWeight) * inputPixel.blue) / 100
                        )
                    }
                    outputImage.setRGB(x, y, color.rgb)
                }
            }
            return outputImage
        }

        override fun saveWatermarkedImage() {
            val watermarked = blendImages()
            val filePath = "${outputFileName.name}.${outputFileName.extension.name.lowercase()}"
            val outputFile = File(filePath)
            ImageIO.write(watermarked, outputFileName.extension.name.lowercase(), outputFile)
        }
    }
}