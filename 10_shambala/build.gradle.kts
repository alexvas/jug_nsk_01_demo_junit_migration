tasks.test {
    useJUnitPlatform()
}

dependencies {
    testImplementation("junit:junit:4.12")
    testRuntimeOnly("org.junit.vintage:junit-vintage-engine")
}
