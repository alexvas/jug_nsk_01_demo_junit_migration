plugins {
    kotlin("jvm")
}

tasks.test {
    useJUnitPlatform()
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.3.2")

    val junitBomVersion = "5.5.2"
    implementation(enforcedPlatform("org.junit:junit-bom:$junitBomVersion")) {
        because("enforce matching Platform, Jupiter, and Vintage versions")
    }
    testImplementation("org.junit.jupiter:junit-jupiter-api")

    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine")
}
