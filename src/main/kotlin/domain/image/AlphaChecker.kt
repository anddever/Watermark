package domain.image

interface AlphaChecker {

    fun isAlphaChannel(): Boolean

    class Base(private val image: ImageData.Base): AlphaChecker {

        override fun isAlphaChannel() = image.transparency == ImageData.TransparencyValues.TRANSLUCENT
    }
}