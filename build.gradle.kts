plugins {
    kotlin("jvm") version "1.4.10" apply false
}

subprojects {

    apply(plugin = "java-library")

    repositories {
        jcenter()
    }

    val junitVersion = "5.7.0"
    dependencies {
        "testImplementation"(platform("org.junit:junit-bom:$junitVersion"))
    }
}
