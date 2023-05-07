package domain

import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import java.io.File
import kotlin.test.assertEquals

internal class ImageDataTest {

    @Test
    fun testImageDataOpaque() {
        val filePath = "src/test/testAssets/10151rgb.png"
        val file = File(filePath)
        val imageData = ImageData.Base(filePath, file)
        val expected = """Image file: src/test/testAssets/10151rgb.png
            |Width: 101
            |Height: 51
            |Number of components: 3
            |Number of color components: 3
            |Bits per pixel: 24
            |Transparency: OPAQUE""".trimMargin()
        val actual = imageData.toString()
        assertTrue(file.exists())
        assertEquals(expected, actual)
    }

    @Test
    fun testImageDataAlpha() {
        val filePath = "src/test/testAssets/10151rgba.png"
        val file = File(filePath)
        val imageData = ImageData.Base(filePath, file)
        val expected = """Image file: src/test/testAssets/10151rgba.png
            |Width: 101
            |Height: 51
            |Number of components: 4
            |Number of color components: 3
            |Bits per pixel: 32
            |Transparency: TRANSLUCENT""".trimMargin()
        val actual = imageData.toString()
        assertTrue(file.exists())
        assertEquals(expected, actual)
    }
}