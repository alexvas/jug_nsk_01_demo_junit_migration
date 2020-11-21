tasks.test {
    useJUnitPlatform()
}

dependencies {
    implementation(project(":01_library_under_test"))

    testImplementation("junit:junit:4.12")
    testImplementation("org.junit.jupiter:junit-jupiter-api")

    testRuntimeOnly("org.junit.vintage:junit-vintage-engine")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine")
}
