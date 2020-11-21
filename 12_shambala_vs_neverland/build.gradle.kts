plugins {
    `java-test-fixtures`
    kotlin("jvm")
}

tasks.test {
    useJUnitPlatform()
}

dependencies {
    implementation(project(":10_shambala"))

    testImplementation(testFixtures(project(":11_shambala_junit5_way")))
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine")
}
