plugins {
    kotlin("jvm") version "1.3.30" apply false
}

subprojects {

    apply(plugin = "java-library")

    repositories {
        jcenter()
    }

}
