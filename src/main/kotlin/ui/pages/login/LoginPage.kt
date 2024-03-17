package ui.pages.login

import com.microsoft.playwright.BrowserContext
import com.microsoft.playwright.Locator
import com.microsoft.playwright.Page
import com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat
import com.microsoft.playwright.options.Cookie

class LoginPage(private val page: Page, private val browserContext: BrowserContext) {
    private val fieldUsername: Locator = page.locator("#user-name")
    private val fieldPassword: Locator = page.locator("#password")

    private val buttonLogin: Locator = page.locator("#login-button")

    private val messageError: Locator = page.locator("[data-test=error]")

    fun navigateTo() {
        page.navigate("/")
    }

    fun fillLoginForm(userName: String, password: String) {
        fieldUsername.fill(userName)
        fieldPassword.fill(password)
    }

    fun clickLoginButton() {
        buttonLogin.click()
    }

    fun loginAs(user: User) {
        val userName = when (user) {
            User.STANDARD_USER -> User.STANDARD_USER.userName
            User.PROBLEM_USER -> User.PROBLEM_USER.userName
            User.PERFORMANCE_GLITCH_USER -> User.PERFORMANCE_GLITCH_USER.userName
            User.ERROR_USER -> User.ERROR_USER.userName
            User.VISUAL_USER -> User.VISUAL_USER.userName
        }

        browserContext.addCookies(
            listOf(
                Cookie("session-username", userName)
                    .setDomain("www.saucedemo.com")
                    .setPath("/"),
            )
        )

        page.navigate("/inventory.html")
    }

    fun validateErrorMessage(expectedMessage: String) {
        assertThat(messageError).hasText(expectedMessage)
    }
}

enum class User(val userName: String) {
    STANDARD_USER("standard_user"),
    PROBLEM_USER("problem_user"),
    PERFORMANCE_GLITCH_USER("performance_glitch_user"),
    ERROR_USER("error_user"),
    VISUAL_USER("visual_user")
}
