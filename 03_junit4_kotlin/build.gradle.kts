plugins {
    kotlin("jvm")
}

dependencies {
    implementation(project(":01_library_under_test"))

    // Use JUnit test framework
    testImplementation("junit:junit:4.12")
    implementation(kotlin("stdlib-jdk8"))
}
