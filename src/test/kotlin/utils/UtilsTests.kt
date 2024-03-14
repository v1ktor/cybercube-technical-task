package utils

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import ui.utils.Utils
import java.math.BigDecimal

class UtilsTests {

    @Test
    fun `should calculate tax`() {
        val expectedTax = BigDecimal("0.80")
        val itemPrice = BigDecimal("9.99")

        val actualTax = Utils.calculateTax(itemPrice)

        assertEquals(expectedTax, actualTax)
    }
}
