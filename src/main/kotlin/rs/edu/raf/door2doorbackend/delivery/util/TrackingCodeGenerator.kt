package rs.edu.raf.door2doorbackend.delivery.util

object TrackingCodeGenerator {
    fun generateTrackingCode(): String {
        val upperLetters = ('A'..'Z').toList()
        val numbers = ('0'..'9').toList()

        val startLetters = (1..2).map { upperLetters.random() }.joinToString("")
        val middleNumbers = (1..9).map { numbers.random() }.joinToString("")
        val endLetters = (1..2).map { upperLetters.random() }.joinToString("")

        // Spajamo sve delove u jedan string
        return startLetters + middleNumbers + endLetters
    }
}