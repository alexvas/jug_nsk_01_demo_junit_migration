plugins {
    `java-test-fixtures`
    kotlin("jvm")
}

tasks.test {
    useJUnitPlatform()
}

dependencies {
    testFixturesApi(project(":10_shambala"))

    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine")
}
