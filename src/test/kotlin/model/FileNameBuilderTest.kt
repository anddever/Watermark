package model

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

internal class FileNameBuilderTest {

    @Test
    fun jpgFileTest() {
        val expectedName = "result"
        val expectedExtension = FileExtension.JPG
        val actual = FileName.Builder("result.jpg").build()
        assertEquals(expectedName, actual.name)
        assertEquals(expectedExtension, actual.extension)
    }

    @Test
    fun pngFileTest() {
        val expectedName = "result"
        val expectedExtension = FileExtension.PNG
        val actual = FileName.Builder("result.png").build()
        assertEquals(expectedName, actual.name)
        assertEquals(expectedExtension, actual.extension)
    }
}