package ui.pages

import com.microsoft.playwright.Locator
import com.microsoft.playwright.Page

class InventoryPage(page: Page) {
    val headerSecondary: Locator = page.locator(".header_secondary_container > .title")
}
