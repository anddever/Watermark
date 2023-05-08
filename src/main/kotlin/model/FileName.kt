package model

import kotlin.system.exitProcess

class FileName private constructor(
    val name: String,
    val extension: FileExtension
) {

    data class Builder(
        val fileName: String
    ) {
        fun build(): FileName {
            val name = fileName.substringBeforeLast('.')
            val extension = fileName.substringAfterLast('.')
            if (FileExtension.values().map { it.name.lowercase() }.contains(extension)) {
                return FileName(name, FileExtension.valueOf(extension.uppercase()))
            } else {
                println("The output file extension isn't \"${FileExtension.JPG.name.lowercase()}\" or \"${FileExtension.PNG.name.lowercase()}\".")
                exitProcess(6)
            }
        }
    }
}