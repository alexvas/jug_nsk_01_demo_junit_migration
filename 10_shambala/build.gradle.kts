tasks.test {
    useJUnitPlatform()
}

dependencies {
    testImplementation("junit:junit:4.12")

    val junitBomVersion = "5.5.2"
    implementation(enforcedPlatform("org.junit:junit-bom:$junitBomVersion")) {
        because("enforce matching Platform, Jupiter, and Vintage versions")
    }

    testRuntimeOnly("org.junit.vintage:junit-vintage-engine")
}
