package ui.pages.inventory

import com.microsoft.playwright.Page
import com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat
import ui.data.Product

class InventoryPage(page: Page) {
    private val selectors = InventoryPageSelectors(page)

    fun addToCart(products: List<Product>) {
        products.forEach {
            when (it) {
                Product.FLEECE_JACKET -> selectors.buttonAddToCartFleeceJacket.click()
                Product.BACKPACK -> selectors.buttonAddToCartBackpack.click()
                Product.BIKE_LIGHT -> selectors.buttonAddToCartBikeLight.click()
                Product.BOLT_T_SHIRT -> selectors.buttonAddToCartBoltTShirt.click()
                Product.ONESIE -> selectors.buttonAddToCartOnesie.click()
                Product.T_SHIRT_RED -> selectors.buttonAddToCartTShirtRed.click()
            }
        }

        assertThat(selectors.shoppingCartBadge).hasText("${products.size}")
    }

    fun removeFromCart(products: List<Product>) {
        products.forEach {
            when (it) {
                Product.FLEECE_JACKET -> selectors.buttonRemoveFromCartFleeceJacket.click()
                Product.BACKPACK -> selectors.buttonRemoveFromCartBackpack.click()
                Product.BIKE_LIGHT -> selectors.buttonRemoveFromCartBikeLight.click()
                Product.BOLT_T_SHIRT -> selectors.buttonRemoveFromCartBoltTShirt.click()
                Product.ONESIE -> selectors.buttonRemoveFromCartOnesie.click()
                Product.T_SHIRT_RED -> selectors.buttonRemoveFromCartTShirtRed.click()
            }
        }
    }

    fun goToCart() {
        selectors.shoppingCartLink.click()
    }

    fun validateTitle() {
        assertThat(selectors.textTitle).hasText("Products")
    }

    fun validateRemoveButtonsAreVisible(products: List<Product>) {
        products.forEach {
            when (it) {
                Product.FLEECE_JACKET -> assertThat(selectors.buttonRemoveFromCartFleeceJacket).isVisible()
                Product.BACKPACK -> assertThat(selectors.buttonRemoveFromCartBackpack).isVisible()
                Product.BIKE_LIGHT -> assertThat(selectors.buttonRemoveFromCartBikeLight).isVisible()
                Product.BOLT_T_SHIRT -> assertThat(selectors.buttonRemoveFromCartBoltTShirt).isVisible()
                Product.ONESIE -> assertThat(selectors.buttonRemoveFromCartOnesie).isVisible()
                Product.T_SHIRT_RED -> assertThat(selectors.buttonRemoveFromCartTShirtRed).isVisible()
            }
        }
    }

    fun validateAddToCartButtonsAreVisible(products: List<Product>) {
        products.forEach {
            when (it) {
                Product.FLEECE_JACKET -> assertThat(selectors.buttonAddToCartFleeceJacket).isVisible()
                Product.BACKPACK -> assertThat(selectors.buttonAddToCartBackpack).isVisible()
                Product.BIKE_LIGHT -> assertThat(selectors.buttonAddToCartBikeLight).isVisible()
                Product.BOLT_T_SHIRT -> assertThat(selectors.buttonAddToCartBoltTShirt).isVisible()
                Product.ONESIE -> assertThat(selectors.buttonAddToCartOnesie).isVisible()
                Product.T_SHIRT_RED -> assertThat(selectors.buttonAddToCartTShirtRed).isVisible()
            }
        }
    }
}
