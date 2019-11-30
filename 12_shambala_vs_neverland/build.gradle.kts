plugins {
    `java-test-fixtures`
    kotlin("jvm")
}

tasks.test {
    useJUnitPlatform()
}

dependencies {
    implementation(project(":10_shambala"))
    implementation(kotlin("stdlib-jdk8"))

    testImplementation(testFixtures(project(":11_shambala_junit5_way")))
}
