val ktor_version: String by project
val kotlinx_version: String by project
val logback_version: String by project
val junit_version: String by project
val faker_version: String by project
val assertj_version: String by project
val playwright_version: String by project

plugins {
    kotlin("jvm") version "1.9.22"
    kotlin("plugin.serialization") version "1.9.23"
    id("com.adarshr.test-logger") version "4.0.0"
}

group = "com.cybercube"
version = "1.0-SNAPSHOT"


repositories {
    mavenCentral()
}

dependencies {
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:$kotlinx_version")
    implementation("io.ktor:ktor-client-core:$ktor_version")
    implementation("io.ktor:ktor-client-cio:$ktor_version")
    implementation("io.ktor:ktor-client-logging:$ktor_version")
    implementation("io.ktor:ktor-client-content-negotiation:$ktor_version")
    implementation("io.ktor:ktor-serialization-kotlinx-json:$ktor_version")
    implementation("ch.qos.logback:logback-classic:$logback_version")
    implementation("io.github.serpro69:kotlin-faker:$faker_version")
    implementation("com.microsoft.playwright:playwright:$playwright_version")

    implementation(platform("org.junit:junit-bom:$junit_version"))
    implementation("org.junit.jupiter:junit-jupiter")

    testImplementation("org.assertj:assertj-core:$assertj_version")

    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

tasks.test {
    useJUnitPlatform()
}

tasks.register<Test>("testApi") {
    useJUnitPlatform()
    include("**/api/**")
    outputs.upToDateWhen { false }
}

tasks.register<Test>("testUi") {
    useJUnitPlatform()
    include("**/ui/**")
    outputs.upToDateWhen { false }
}

tasks.register<Test>("testUnit") {
    useJUnitPlatform()
    include("**/utils/**")
    outputs.upToDateWhen { false }
}

kotlin {
    jvmToolchain(21)
}
