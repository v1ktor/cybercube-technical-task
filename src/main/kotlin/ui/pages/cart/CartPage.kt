package ui.pages.cart

import com.microsoft.playwright.Locator
import com.microsoft.playwright.Locator.FilterOptions
import com.microsoft.playwright.Page
import com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat
import ui.data.Product
import ui.pages.inventory.InventoryPage

class CartPage(page: Page) {
    private val buttonCheckout: Locator = page.locator("button[data-test='checkout']")

    private val itemInTheCart: Locator = page.locator("div[class='cart_item']")
    private val itemTitle: Locator = page.locator("div[class='cart_item_label'] a")
    private val itemDescription: Locator = page.locator("div[class='cart_item_label'] div[class='inventory_item_desc']")

    private val inventoryPage = InventoryPage(page)

    fun clickCheckout() {
        buttonCheckout.click()
    }

    fun validateItemsAreInCart(products: List<Product>) {
        assertThat(itemInTheCart).hasCount(products.size)

        products.forEach {
            assertThat(itemTitle.filter(FilterOptions().setHasText(it.title))).isVisible()
            assertThat(itemDescription.filter(FilterOptions().setHasText(it.description))).isVisible()
        }
    }

    fun removeItems(products: List<Product>) {
        inventoryPage.removeFromCart(products)
    }

    fun validateCartIsEmpty() {
        assertThat(itemInTheCart).isHidden()
    }
}
