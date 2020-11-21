plugins {
    kotlin("jvm") version "1.3.61" apply false
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
