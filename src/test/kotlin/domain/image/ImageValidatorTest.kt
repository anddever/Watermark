package domain.image

import model.FileType
import org.junit.jupiter.api.Test
import java.io.File

class ImageValidatorTest {

    @Test
    fun color3componentsTest() {
        val filePath = "src/test/testAssets/10151rgb.png"
        val file = File(filePath)
        val imageData = ImageData.Base(filePath, file)
        ImageValidator.Base(imageData, FileType.IMAGE).validate()
    }

    @Test
    fun image24or32bitTest() {
        val filePath = "src/test/testAssets/10151rgb.png"
        val file = File(filePath)
        val imageData = ImageData.Base(filePath, file)
        ImageValidator.Base(imageData, FileType.WATERMARK).validate()
    }

    @Test
    fun compareDimensionsTest() {
        val filePath1 = "src/test/testAssets/10151rgb.png"
        val file1 = File(filePath1)
        val imageData1 = ImageData.Base(filePath1, file1)

        val filePath2 = "src/test/testAssets/10151rgba.png"
        val file2 = File(filePath2)
        val imageData2 = ImageData.Base(filePath2, file2)
        ImageValidator.Base(imageData1, FileType.IMAGE).compareDimensions(imageData2)
    }
}