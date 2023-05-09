package domain.image

import model.FileName
import model.FileType
import model.WatermarkWeight
import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import java.io.File
import javax.imageio.ImageIO
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class WatermarkGeneratorTest {

    companion object {
        lateinit var generator: WatermarkGenerator.Base
        var width: Int = 0
        var height: Int = 0
        private const val resultFilePath = "src/test/testAssets/out.png"
        val resultFile = File(resultFilePath)

        @JvmStatic
        @BeforeAll
        fun setup() {
            val originalFilePath = "src/test/testAssets/image.png"
            val originalImage = ImageDataBuilder.Base(originalFilePath, FileType.IMAGE).build()
            val watermarkFilePath = "src/test/testAssets/watermark.png"
            val watermarkImage = ImageDataBuilder.Base(watermarkFilePath, FileType.WATERMARK).build()
            width = originalImage.width
            height = originalImage.height
            val weight = WatermarkWeight.Builder("20").build()
            val outputFileName = FileName.Builder(resultFilePath).build()
            generator = WatermarkGenerator.Base(originalImage, watermarkImage, weight, outputFileName)
        }

        @JvmStatic
        @AfterAll
        fun teardown() {
            if (resultFile.exists()) resultFile.delete()
        }
    }

    @Test
    fun blendImagesTest() {
        val actual = generator.blendImages()
        val expected = ImageIO.read(File("src/test/testAssets/expected.png"))
        for (x in 0 until width) {
            for (y in 0 until height) {
                assertEquals(expected.getRGB(x, y), actual.getRGB(x, y))
            }
        }
    }

    @Test
    fun saveWatermarkedImageTest() {
        assertTrue(!resultFile.exists())
        generator.saveWatermarkedImage()
        assertTrue(resultFile.exists())
    }
}