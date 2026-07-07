plugins {
    id("java")
}
repositories {
    mavenCentral()
}
dependencies {
    // PostgreSQL JDBC driver - lets Java talk to PostgreSQL
    implementation("org.postgresql:postgresql:42.7.3")
    // JUnit for testing
    testImplementation("org.junit.jupiter:junit-jupiter:5.10.0")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}
java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(21)
    }
}
tasks.named<Test>("test") {
    useJUnitPlatform()
}