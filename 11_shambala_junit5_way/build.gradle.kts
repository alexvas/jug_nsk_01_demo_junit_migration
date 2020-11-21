plugins {
    `java-test-fixtures`
    kotlin("jvm")
}

tasks.test {
    useJUnitPlatform()
}

dependencies {
    testFixturesApi(project(":10_shambala"))

    val junitBomVersion = "5.7.0"
    testFixturesApi(platform("org.junit:junit-bom:$junitBomVersion")) {
        because("enforce matching Platform, Jupiter, and Vintage versions")
    }
    testFixturesApi("org.junit.jupiter:junit-jupiter-api")

    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine")
}
