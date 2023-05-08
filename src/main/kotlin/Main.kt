import domain.image.ImageData
import java.io.File
import kotlin.system.exitProcess

fun main() {
    println("Input the image filename:")
    val filePath = readln()
    val file = File(filePath)
    if (file.exists()) {
        val imageData = ImageData.Base(filePath, file)
        imageData.printImageInfo()
    } else {
        print("The file $filePath doesn't exist.")
        exitProcess(1)
    }
}