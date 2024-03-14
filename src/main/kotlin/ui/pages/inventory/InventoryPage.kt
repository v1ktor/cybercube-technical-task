package ui.pages.inventory

import com.microsoft.playwright.Locator
import com.microsoft.playwright.Page
import com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat
import ui.data.Product

class InventoryPage(private val page: Page) {
    private val buttonAddToCartBackpack: Locator = page.locator("button[data-test='add-to-cart-sauce-labs-backpack']")
    private val buttonAddToCartBikeLight: Locator =
        page.locator("button[data-test='add-to-cart-sauce-labs-bike-light']")
    private val buttonAddToCartBoltTShirt: Locator =
        page.locator("button[data-test='add-to-cart-sauce-labs-bolt-t-shirt']")
    private val buttonAddToCartFleeceJacket: Locator =
        page.locator("button[data-test='add-to-cart-sauce-labs-fleece-jacket']")
    private val buttonAddToCartOnesie: Locator = page.locator("button[data-test='add-to-cart-sauce-labs-onesie']")
    private val buttonAddToCartTShirtRed: Locator =
        page.locator("button[data-test='add-to-cart-test.allthethings()-t-shirt-(red)']")

    private val shoppingCartBadge: Locator = page.locator("span[class='shopping_cart_badge']")
    private val shoppingCartLink: Locator = page.locator("a[class='shopping_cart_link']")

    private val textTitle: Locator = page.locator(".header_secondary_container > .title")

    fun navigateTo() {
        page.navigate("/inventory.html")
    }

    fun addToCart(products: List<Product>) {
        products.forEach {
            when (it) {
                Product.FLEECE_JACKET -> buttonAddToCartFleeceJacket.click()
                Product.BACKPACK -> buttonAddToCartBackpack.click()
                Product.BIKE_LIGHT -> buttonAddToCartBikeLight.click()
                Product.BOLT_T_SHIRT -> buttonAddToCartBoltTShirt.click()
                Product.ONESIE -> buttonAddToCartOnesie.click()
                Product.T_SHIRT_RED -> buttonAddToCartTShirtRed.click()
            }
        }

        assertThat(shoppingCartBadge).hasText("${products.size}")
    }

    fun goToCart() {
        shoppingCartLink.click()
    }

    fun validateTitle() {
        assertThat(textTitle).hasText("Products")
    }
}
