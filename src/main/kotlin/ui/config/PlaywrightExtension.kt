package ui.config

import com.microsoft.playwright.*
import com.microsoft.playwright.Tracing.StopOptions
import org.junit.jupiter.api.extension.*
import ui.pages.cart.CartPage
import ui.pages.checkout.CheckoutPage
import ui.pages.inventory.InventoryPage
import ui.pages.login.LoginPage
import java.nio.file.Files
import java.nio.file.Paths


class PlaywrightExtension : BeforeAllCallback, AfterAllCallback, BeforeEachCallback, TestWatcher, ParameterResolver {
    companion object {
        private lateinit var playwright: Playwright
        private lateinit var browser: Browser

        private val browserContextThreadLocal = ThreadLocal<BrowserContext>()
        private val pageThreadLocal = ThreadLocal<Page>()

        fun getPage(): Page = pageThreadLocal.get()
        fun getBrowserContext(): BrowserContext = browserContextThreadLocal.get()
    }

    override fun beforeAll(context: ExtensionContext?) {
        playwright = Playwright.create()
        browser = BrowserConfig.launchBrowser(playwright)
    }

    override fun beforeEach(context: ExtensionContext?) {
        val browserContextOptions = Browser.NewContextOptions().setBaseURL(BrowserConfig.DEFAULT_URL)

        val browserContext = browser.newContext(browserContextOptions)
        browserContextThreadLocal.set(browserContext)

        browserContext.tracing().start(
            Tracing.StartOptions()
                .setScreenshots(true)
                .setSnapshots(true)
                .setSources(true)
        )

        val page = browserContext.newPage()
        pageThreadLocal.set(page)
    }

    override fun afterAll(context: ExtensionContext?) {
        playwright.close()
    }

    override fun testFailed(context: ExtensionContext, cause: Throwable?) {
        val browserContext: BrowserContext = browserContextThreadLocal.get()

        val tracePath = Paths.get("build/traces/${context.requiredTestMethod.name}-${System.currentTimeMillis()}.zip")
        Files.createDirectories(tracePath.parent)

        browserContext.tracing().stop(StopOptions().setPath(tracePath))

        cleanUp()
    }

    override fun testSuccessful(context: ExtensionContext?) {
        val browserContext: BrowserContext = browserContextThreadLocal.get()
        browserContext.tracing().stop()

        cleanUp()
    }

    private fun cleanUp() {
        pageThreadLocal.get().close()
        pageThreadLocal.remove()

        browserContextThreadLocal.get().close()
        browserContextThreadLocal.remove()
    }

    override fun supportsParameter(parameterContext: ParameterContext, extensionContext: ExtensionContext): Boolean {
        val type = parameterContext.parameter.type
        return type == Page::class.java
                || type == BrowserContext::class.java
                || type == LoginPage::class.java
                || type == InventoryPage::class.java
                || type == CartPage::class.java
                || type == CheckoutPage::class.java
    }

    override fun resolveParameter(parameterContext: ParameterContext, extensionContext: ExtensionContext): Any {
        return when (val type = parameterContext.parameter.type) {
            Page::class.java -> getPage()
            BrowserContext::class.java -> getBrowserContext()
            LoginPage::class.java -> LoginPage(getPage(), getBrowserContext())
            InventoryPage::class.java -> InventoryPage(getPage())
            CartPage::class.java -> CartPage(getPage())
            CheckoutPage::class.java -> CheckoutPage(getPage())
            else -> throw ParameterResolutionException("Can't resolve parameter of type $type")
        }
    }
}
