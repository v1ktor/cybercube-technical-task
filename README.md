# Technical Stack

- [Kotlin](https://kotlinlang.org/)
- [JUnit 5](https://junit.org/junit5/)
- [Ktor HTTP client](https://ktor.io/docs/getting-started-ktor-client.html) for API tests
- [Playwright](https://playwright.dev/java/docs/intro) for UI tests

# How to Run

- `./gradlew clean test` - runs all tests
- `./gradlew clean testApi` - runs only API tests
- `./gradlew clean testUi` - runs only UI tests
- `./gradlew clean testUnit` - runs only unit tests

By default `chromium` browser is used for UI tests. This can be changed either by passing `-Pbrowser=` (
e.g. `./gradlew clean test -Pbrowser=firefox`) variable or by setting `DEFAULT_BROWSER`
in `src/main/kotlin/ui/config/BrowserConfig.kt`.

# Test Results

Test results will be available in the build directory of the project: `%rootDir%/build/reports/tests/test/index.html`.

For failed UI tests, the trace will be generated and saved in `%rootDir%/build/traces/` folder. The trace can be viewed
on https://trace.playwright.dev/ by uploading .zip file.
