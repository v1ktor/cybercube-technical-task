package ui.config

import com.microsoft.playwright.Browser
import com.microsoft.playwright.BrowserType
import com.microsoft.playwright.Playwright

object BrowserConfig {
    private const val CLI_BROWSER_TYPE_KEY = "browser"
    private val DEFAULT_BROWSER = Browsers.CHROMIUM.engine

    const val DEFAULT_URL = "https://www.saucedemo.com/"

    private fun init(): String {
        val browserType = System.getProperty(CLI_BROWSER_TYPE_KEY)
        if (!browserType.isNullOrEmpty()) {
            return browserType
        }

        return DEFAULT_BROWSER
    }

    fun launchBrowser(playwright: Playwright): Browser {
        val browserType = init()

        val launchOptions = BrowserType.LaunchOptions()
            .setHeadless(false)

        val browser = when (browserType.lowercase()) {
            Browsers.WEBKIT.engine -> playwright.webkit().launch(launchOptions)
            Browsers.FIREFOX.engine -> playwright.firefox().launch(launchOptions)
            Browsers.CHROMIUM.engine -> playwright.chromium().launch(launchOptions)
            else -> playwright.chromium().launch(launchOptions)
        }

        return browser
    }
}
