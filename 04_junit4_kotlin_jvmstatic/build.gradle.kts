plugins {
    kotlin("jvm")
}

dependencies {
    implementation(project(":01_library_under_test"))

    testImplementation("junit:junit:4.12")
}
