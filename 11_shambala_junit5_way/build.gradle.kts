plugins {
    `java-test-fixtures`
    kotlin("jvm")
}

tasks.test {
    useJUnitPlatform()
}

dependencies {
    testFixturesApi(project(":10_shambala"))
    testFixturesApi(kotlin("stdlib-jdk8"))

    val junitBomVersion = "5.5.2"
    testFixturesApi(enforcedPlatform("org.junit:junit-bom:$junitBomVersion")) {
        because("enforce matching Platform, Jupiter, and Vintage versions")
    }
    testFixturesApi("org.junit.jupiter:junit-jupiter-api")

    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine")
}
