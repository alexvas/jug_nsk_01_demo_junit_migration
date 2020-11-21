plugins {
    kotlin("jvm")
}

tasks.test {
    useJUnitPlatform()
}

dependencies {
    implementation(project(":01_library_under_test"))

    implementation(kotlin("stdlib-jdk8"))

    testImplementation("junit:junit:4.12")

    testRuntimeOnly("org.junit.vintage:junit-vintage-engine")
}
