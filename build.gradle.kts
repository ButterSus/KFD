plugins {
    kotlin("jvm") version "1.8.20"
    id("com.gradleup.shadow") version "8.3.0"
    id("java")
}

group = "com.buttersus"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

tasks.shadowJar {
    from("LICENSE")
    manifest {
        attributes("Main-Class" to "MainKt")
    }
}

kotlin {
    jvmToolchain(17)
}
