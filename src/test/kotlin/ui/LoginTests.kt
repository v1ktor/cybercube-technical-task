package ui

import com.microsoft.playwright.Page
import com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource
import ui.config.PlaywrightExtension
import ui.pages.InventoryPage
import ui.pages.LoginPage

@ExtendWith(PlaywrightExtension::class)
class LoginTests {
    @ParameterizedTest
    @ValueSource(strings = ["standard_user", "problem_user", "performance_glitch_user", "error_user", "visual_user"])
    fun `user can login with valid credentials`(
        user: String,
        page: Page,
        loginPage: LoginPage,
        inventoryPage: InventoryPage
    ) {
        page.navigate("/")

        loginPage.fillLoginForm(user, "secret_sauce")
        loginPage.clickLoginButton()

        assertThat(inventoryPage.headerSecondary).hasText("Products")
    }

    @Test
    fun `user will receive an error if he is locked out`(page: Page, loginPage: LoginPage) {
        page.navigate("/")

        loginPage.fillLoginForm("locked_out_user", "secret_sauce")
        loginPage.clickLoginButton()

        assertThat(loginPage.messageError).hasText("Epic sadface: Sorry, this user has been locked out.")
    }

    @Test
    fun `user will receive an error if he enters invalid credentials`(page: Page, loginPage: LoginPage) {
        page.navigate("/")

        loginPage.fillLoginForm("invalid_user", "invalid_password")
        loginPage.clickLoginButton()

        assertThat(loginPage.messageError).hasText("Epic sadface: Username and password do not match any user in this service")
    }
}
