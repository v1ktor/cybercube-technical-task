package ui.pages.checkout

import com.microsoft.playwright.Locator
import com.microsoft.playwright.Page

class CheckoutPageSelectors(page: Page) {
    val fieldFirstName: Locator = page.locator("input[data-test='firstName']")
    val fieldLastName: Locator = page.locator("input[data-test='lastName']")
    val fieldZipCode: Locator = page.locator("input[data-test='postalCode']")

    val buttonContinue: Locator = page.locator("input[data-test='continue']")
    val buttonFinish: Locator = page.locator("button[data-test='finish']")
    val buttonBackHome: Locator = page.locator("button[data-test='back-to-products']")

    val itemInTheCart: Locator = page.locator("div[class='cart_item']")
    val itemTitle: Locator = page.locator("div[class='cart_item_label'] a")
    val itemDescription: Locator = page.locator("div[class='cart_item_label'] div[class='inventory_item_desc']")

    val labelPaymentInformationValue: Locator = page.locator("div.summary_value_label:nth-child(2)")
    val labelShippingInformationValue: Locator = page.locator("div.summary_value_label:nth-child(4)")
    val labelPriceTotalValue: Locator = page.locator("div[class='summary_subtotal_label']")
    val labelPriceTotalTaxValue: Locator = page.locator("div[class='summary_tax_label']")
    val labelTotal: Locator = page.locator("div[class='summary_info_label summary_total_label']")

    val textThankYou: Locator = page.locator("h2[class='complete-header']")
    val textComplete: Locator = page.locator("div[class='complete-text']")
    val textTitle: Locator = page.locator("span[class='title']")
}
