import domain.ImageData
import java.io.File

fun main() {
    println("Input the image filename:")
    val filePath = readln()
    val file = File(filePath)
    if (file.exists()) {
        val imageData = ImageData.Base(filePath, file)
        imageData.printImageInfo()
    } else print("The file $filePath doesn't exist.")
}