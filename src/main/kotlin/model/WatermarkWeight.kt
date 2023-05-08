package model

import kotlin.system.exitProcess

class WatermarkWeight private constructor() {

    data class Builder(
        private val weightStr: String
    ) {

        fun build(): Int {
            try {
                val weight = weightStr.toInt()
                if (weight !in 0..100) {
                    println("The transparency percentage is out of range.")
                    exitProcess(5)
                }
                return weight
            } catch (e: NumberFormatException) {
                println("The transparency percentage isn't an integer number.")
                exitProcess(4)
            }
        }
    }
}