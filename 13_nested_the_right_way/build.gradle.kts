plugins {
    kotlin("jvm")
}

tasks.test {
    useJUnitPlatform()
}

dependencies {
    implementation(project(":10_shambala"))
    implementation(project(":12_shambala_vs_neverland"))
    implementation(kotlin("stdlib-jdk8"))

    val junitBomVersion = "5.4.2"
    implementation(enforcedPlatform("org.junit:junit-bom:$junitBomVersion")) {
        because("enforce matching Platform, Jupiter, and Vintage versions")
    }
    testImplementation("org.junit.jupiter:junit-jupiter-api")

    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine")
}
