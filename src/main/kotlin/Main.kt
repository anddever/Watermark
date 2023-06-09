import domain.image.AlphaChecker
import domain.image.ImageDataBuilder
import domain.image.ImageValidator
import domain.image.WatermarkGenerator
import model.*
import java.awt.Color

fun main() {
    var typeOfBlending = TypeOfBlending.NO_ALPHA_CHANNEL
    var transparencyColor: Color? = null

    println("Input the image filename:")
    val originalFilePath = readln()
    val originalImage = ImageDataBuilder.Base(originalFilePath, FileType.IMAGE).build()

    println("Input the watermark image filename:")
    val watermarkFilePath = readln()
    val watermarkImage = ImageDataBuilder.Base(watermarkFilePath, FileType.WATERMARK).build()

    ImageValidator.Base(originalImage, FileType.IMAGE).compareDimensions(watermarkImage)

    if (AlphaChecker.Base(watermarkImage).isAlphaChannel()) {
        println("Do you want to use the watermark's Alpha channel?")
        val isUseAlphaChannel = readln().lowercase()
        if (isUseAlphaChannel == "yes") {
            typeOfBlending = TypeOfBlending.WITH_ALPHA_CHANNEL
        }
    } else {
        println("Do you want to set a transparency color?")
        val isSetTransparencyColor = readln().lowercase()
        if (isSetTransparencyColor == "yes") {
            println("Input a transparency color ([Red] [Green] [Blue]):")
            transparencyColor = TransparencyColor.Builder(readln()).build()
        }
    }

    println("Input the watermark transparency percentage (Integer 0-100):")
    val watermarkWeight = WatermarkWeight.Builder(readln()).build()

    println("Input the output image filename (jpg or png extension):")
    val outputFileName: FileName = FileName.Builder(readln()).build()

    WatermarkGenerator.Base(originalImage, watermarkImage, watermarkWeight, outputFileName, typeOfBlending, transparencyColor)
        .saveWatermarkedImage()
    println("The watermarked image ${outputFileName.name}.${outputFileName.extension.name.lowercase()} has been created.")
}