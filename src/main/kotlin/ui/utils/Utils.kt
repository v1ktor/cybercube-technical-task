package ui.utils

import java.math.BigDecimal
import java.math.RoundingMode

object Utils {
    private val TAX_PERCENTAGE = BigDecimal("8.01")

    fun calculateTax(itemPrice: BigDecimal): BigDecimal {
        return itemPrice.times(TAX_PERCENTAGE).div(BigDecimal("100")).setScale(2, RoundingMode.DOWN)
    }
}
