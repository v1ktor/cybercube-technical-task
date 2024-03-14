package ui.pages.checkout

import com.microsoft.playwright.Locator
import com.microsoft.playwright.Page
import com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat
import ui.data.Product
import ui.utils.Utils
import java.math.RoundingMode

class CheckoutPage(private val page: Page) {
    private val selectors = CheckoutPageSelectors(page)

    fun fillCheckoutForm(firstName: String, lastName: String, zipCode: String) {
        selectors.fieldFirstName.fill(firstName)
        selectors.fieldLastName.fill(lastName)
        selectors.fieldZipCode.fill(zipCode)
    }

    fun clickContinue() {
        selectors.buttonContinue.click()
    }

    fun clickFinish() {
        selectors.buttonFinish.click()
    }

    fun validateItemsInCheckout(products: List<Product>) {
        assertThat(selectors.itemInTheCart).hasCount(products.size)

        products.forEach {
            assertThat(selectors.itemTitle.filter(Locator.FilterOptions().setHasText(it.title))).isVisible()
            assertThat(selectors.itemDescription.filter(Locator.FilterOptions().setHasText(it.description))).isVisible()
        }
    }

    fun validatePaymentAndShippingInfo(products: List<Product>) {
        val totalPrice = products.sumOf { it.price }.setScale(2, RoundingMode.DOWN)
        val totalTax = products.sumOf { Utils.calculateTax(it.price) }.setScale(2, RoundingMode.DOWN)
        val total = totalPrice + totalTax

        assertThat(selectors.labelPaymentInformationValue).hasText("SauceCard #31337")
        assertThat(selectors.labelShippingInformationValue).hasText("Free Pony Express Delivery!")
        assertThat(selectors.labelPriceTotalValue).hasText("Item total: $$totalPrice")
        assertThat(selectors.labelPriceTotalTaxValue).hasText("Tax: $$totalTax")
        assertThat(selectors.labelTotal).hasText("Total: $$total")
    }

    fun validateCheckoutIsCompleted() {
        assertThat(selectors.textTitle).hasText("Checkout: Complete!")
        assertThat(selectors.textThankYou).hasText("Thank you for your order!")
        assertThat(selectors.textComplete).hasText("Your order has been dispatched, and will arrive just as fast as the pony can get there!")
        assertThat(selectors.buttonBackHome).isVisible()
    }

    fun navigateToCheckoutOverview() {
        page.navigate("/checkout-step-one.html")
        fillCheckoutForm("John", "Doe", "12345")
        clickContinue()
    }
}
