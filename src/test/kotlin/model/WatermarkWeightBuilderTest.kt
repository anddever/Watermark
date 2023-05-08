package model

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class WatermarkWeightBuilderTest {

    @Test
    fun weight20test() {
        val expected = 20
        val actual = WatermarkWeight.Builder("20").build()
        assertEquals(expected, actual)
    }
}