package domain.image

import model.FileType
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class ImageDataBuilderTest {

    @Test
    fun buildImageDataTest() {
        val filePath = "src/test/testAssets/10151rgb.png"
        val imageData = ImageDataBuilder.Base(filePath, FileType.IMAGE).build()
        val expectedWidth = 101
        val expectedHeight = 51
        val expectedBitsPerPixel = 24
        val expectedNumOfColorComponents = 3
        assertEquals(expectedWidth, imageData.width)
        assertEquals(expectedHeight, imageData.height)
        assertEquals(expectedBitsPerPixel, imageData.bitsPerPixel)
        assertEquals(expectedNumOfColorComponents, imageData.numOfColorComponents)
    }
}