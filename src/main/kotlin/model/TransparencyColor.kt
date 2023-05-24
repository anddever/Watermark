package model

import java.awt.Color
import kotlin.system.exitProcess

class TransparencyColor private constructor() {

    data class Builder(private val input: String) {

        fun build(): Color {
            try {
                val (red, green, blue) = input.split(" ").map(String::toInt)
                if (red !in 0..255 && green !in 0..255 && blue !in 0..255) {
                    throw IllegalArgumentException()
                }
                return Color(red, green, blue)
            } catch (e: Exception) {
                println("The transparency color input is invalid.")
                exitProcess(8)
            }
        }
    }
}