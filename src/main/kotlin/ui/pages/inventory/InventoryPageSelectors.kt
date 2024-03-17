package ui.pages.inventory

import com.microsoft.playwright.Locator
import com.microsoft.playwright.Page

class InventoryPageSelectors(page: Page) {
    val buttonAddToCartBackpack: Locator = page.locator("button[data-test='add-to-cart-sauce-labs-backpack']")
    val buttonAddToCartBikeLight: Locator =
        page.locator("button[data-test='add-to-cart-sauce-labs-bike-light']")
    val buttonAddToCartBoltTShirt: Locator =
        page.locator("button[data-test='add-to-cart-sauce-labs-bolt-t-shirt']")
    val buttonAddToCartFleeceJacket: Locator =
        page.locator("button[data-test='add-to-cart-sauce-labs-fleece-jacket']")
    val buttonAddToCartOnesie: Locator = page.locator("button[data-test='add-to-cart-sauce-labs-onesie']")
    val buttonAddToCartTShirtRed: Locator =
        page.locator("button[data-test='add-to-cart-test.allthethings()-t-shirt-(red)']")
    val buttonRemoveFromCartBackpack: Locator = page.locator("button[data-test='remove-sauce-labs-backpack']")
    val buttonRemoveFromCartBikeLight: Locator =
        page.locator("button[data-test='remove-sauce-labs-bike-light']")
    val buttonRemoveFromCartBoltTShirt: Locator =
        page.locator("button[data-test='remove-sauce-labs-bolt-t-shirt']")
    val buttonRemoveFromCartFleeceJacket: Locator =
        page.locator("button[data-test='remove-sauce-labs-fleece-jacket']")
    val buttonRemoveFromCartOnesie: Locator = page.locator("button[data-test='remove-sauce-labs-onesie']")
    val buttonRemoveFromCartTShirtRed: Locator =
        page.locator("button[data-test='remove-test.allthethings()-t-shirt-(red)']")

    val shoppingCartBadge: Locator = page.locator("span[class='shopping_cart_badge']")
    val shoppingCartLink: Locator = page.locator("a[class='shopping_cart_link']")

    val textTitle: Locator = page.locator(".header_secondary_container > .title")
}
