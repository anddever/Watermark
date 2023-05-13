package domain.image

import model.FileType
import org.junit.jupiter.api.Test
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class AlphaCheckerTest {

    @Test
    fun imageWithAlphaTest() {
        val imageWithAlphaPath = "src/test/testAssets/watermarkWithAlpha.png"
        val imageWithAlphaData = ImageDataBuilder.Base(imageWithAlphaPath, FileType.WATERMARK).build()
        val actual = AlphaChecker.Base(imageWithAlphaData).isAlphaChannel()
        assertTrue(actual, "The image doesn't have an alpha channel")
    }

    @Test
    fun imageNoAlphaTest() {
        val imageNoAlphaPath = "src/test/testAssets/watermarkNoAlpha.png"
        val imageNoAlphaData = ImageDataBuilder.Base(imageNoAlphaPath, FileType.WATERMARK).build()
        val actual = AlphaChecker.Base(imageNoAlphaData).isAlphaChannel()
        assertFalse(actual, "The image has an alpha channel")
    }
}