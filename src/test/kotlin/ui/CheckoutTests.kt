package ui

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import ui.config.PlaywrightExtension
import ui.data.Product
import ui.pages.cart.CartPage
import ui.pages.checkout.CheckoutPage
import ui.pages.inventory.InventoryPage
import ui.pages.login.LoginPage
import ui.pages.login.User

@ExtendWith(PlaywrightExtension::class)
class CheckoutTests {

    @BeforeEach
    fun beforeEach() {
        val loginPage = LoginPage(PlaywrightExtension.getPage(), PlaywrightExtension.getBrowserContext())

        loginPage.loginAs(User.STANDARD_USER)
    }

    @Test
    fun `user can checkout items`(inventoryPage: InventoryPage, cartPage: CartPage, checkoutPage: CheckoutPage) {
        val products = listOf(Product.BACKPACK, Product.FLEECE_JACKET)

        inventoryPage.addToCart(products)
        inventoryPage.goToCart()

        cartPage.validateItemsAreInCart(products)
        cartPage.clickCheckout()

        checkoutPage.fillCheckoutForm("John", "Doe", "12345")
        checkoutPage.clickContinue()

        checkoutPage.validateItemsInCheckout(products)
        checkoutPage.validatePaymentAndShippingInfo(products)
        checkoutPage.clickFinish()

        checkoutPage.validateCheckoutIsCompleted()
    }

    @Test
    fun `prices are correctly calculated`(inventoryPage: InventoryPage, checkoutPage: CheckoutPage) {
        val products = listOf(Product.BIKE_LIGHT, Product.FLEECE_JACKET)

        inventoryPage.addToCart(products)
        inventoryPage.goToCart()

        checkoutPage.navigateToCheckoutOverview()

        checkoutPage.validateItemsInCheckout(products)
        checkoutPage.validatePaymentAndShippingInfo(products)
    }
}
