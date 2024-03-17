package ui

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import ui.config.PlaywrightExtension
import ui.data.Product
import ui.pages.cart.CartPage
import ui.pages.inventory.InventoryPage
import ui.pages.login.LoginPage
import ui.pages.login.User

@ExtendWith(PlaywrightExtension::class)
class CartTests {

    @BeforeEach
    fun beforeEach() {
        val loginPage = LoginPage(PlaywrightExtension.getPage(), PlaywrightExtension.getBrowserContext())

        loginPage.loginAs(User.STANDARD_USER)
    }

    @Test
    fun `products can be added to cart and removed in the inventory page`(inventoryPage: InventoryPage) {
        val products = listOf(Product.BACKPACK, Product.FLEECE_JACKET)

        inventoryPage.addToCart(products)
        inventoryPage.validateRemoveButtonsAreVisible(products)

        inventoryPage.removeFromCart(products)
        inventoryPage.validateAddToCartButtonsAreVisible(products)
    }

    @Test
    fun `products can be added in the inventory page and removed from the cart page`(
        inventoryPage: InventoryPage,
        cartPage: CartPage
    ) {
        val products = listOf(Product.BACKPACK, Product.FLEECE_JACKET)

        inventoryPage.addToCart(products)
        inventoryPage.goToCart()

        cartPage.validateItemsAreInCart(products)
        cartPage.removeItems(products)

        cartPage.validateCartIsEmpty()
    }
}
