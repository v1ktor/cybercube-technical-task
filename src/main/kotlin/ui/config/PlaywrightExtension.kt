package ui.config

import com.microsoft.playwright.*
import com.microsoft.playwright.Tracing.StopOptions
import org.junit.jupiter.api.extension.*
import java.nio.file.Files
import java.nio.file.Paths


class PlaywrightExtension : BeforeAllCallback, AfterAllCallback, BeforeEachCallback, TestWatcher, ParameterResolver {
    companion object {
        private lateinit var playwright: Playwright
        private lateinit var browser: Browser

        private val browserContextThreadLocal = ThreadLocal<BrowserContext>()

        fun getPage(): Page = browserContextThreadLocal.get().newPage()
        fun getContext(): BrowserContext = browserContextThreadLocal.get()
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
        browserContextThreadLocal.get().close()
        browserContextThreadLocal.remove()
    }

    override fun supportsParameter(parameterContext: ParameterContext, extensionContext: ExtensionContext): Boolean {
        val type = parameterContext.parameter.type
        return type == Page::class.java || type == BrowserContext::class.java
    }

    override fun resolveParameter(parameterContext: ParameterContext, extensionContext: ExtensionContext): Any {
        return when (val type = parameterContext.parameter.type) {
            Page::class.java -> getPage()
            BrowserContext::class.java -> getContext()
            else -> throw ParameterResolutionException("Can't resolve parameter of type $type")
        }
    }
}
