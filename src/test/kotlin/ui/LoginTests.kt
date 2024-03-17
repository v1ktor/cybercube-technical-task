package ui

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource
import ui.config.PlaywrightExtension
import ui.pages.inventory.InventoryPage
import ui.pages.login.LoginPage

@ExtendWith(PlaywrightExtension::class)
class LoginTests {

    @BeforeEach
    fun beforeEach() {
        val loginPage = LoginPage(PlaywrightExtension.getPage(), PlaywrightExtension.getBrowserContext())

        loginPage.navigateTo()
    }

    @ParameterizedTest
    @ValueSource(strings = ["standard_user", "problem_user", "performance_glitch_user", "error_user", "visual_user"])
    fun `user can login with valid credentials`(
        user: String,
        loginPage: LoginPage,
        inventoryPage: InventoryPage
    ) {
        loginPage.fillLoginForm(user, "secret_sauce")
        loginPage.clickLoginButton()

        inventoryPage.validateTitle()
    }

    @Test
    fun `user will receive an error if he is locked out`(loginPage: LoginPage) {
        loginPage.fillLoginForm("locked_out_user", "secret_sauce")
        loginPage.clickLoginButton()

        loginPage.validateErrorMessage("Epic sadface: Sorry, this user has been locked out.")
    }

    @Test
    fun `user will receive an error if he enters invalid credentials`(loginPage: LoginPage) {
        loginPage.fillLoginForm("invalid_user", "invalid_password")
        loginPage.clickLoginButton()

        loginPage.validateErrorMessage("Epic sadface: Username and password do not match any user in this service")
    }
}
