package domain.image

import model.FileType
import java.io.File
import kotlin.system.exitProcess

interface ImageDataBuilder {

    fun build(): ImageData.Base

    class Base(private val filePath: String, private val imageType: FileType) : ImageDataBuilder {

        override fun build(): ImageData.Base {
            val imageFile = File(filePath)
            if (!imageFile.exists()) {
                println("The file $filePath doesn't exist.")
                exitProcess(1)
            }
            val imageData = ImageData.Base(filePath, imageFile)
            ImageValidator.Base(imageData, imageType).validate()
            return imageData
        }
    }
}