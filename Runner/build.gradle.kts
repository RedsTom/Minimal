plugins {
    kotlin("jvm")
    id("application")

    id("com.github.johnrengelman.shadow") version "8.1.1"
}

group = "me.redstom"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation("org.jetbrains.kotlin:kotlin-test")

    implementation(project(":Compiler"))
    implementation(project(":Interpreter"))

    implementation("org.jetbrains.kotlinx:kotlinx-cli-jvm:0.3.5")

    implementation("com.fasterxml.jackson.dataformat:jackson-dataformat-xml:2.16.1")
    implementation("com.fasterxml.jackson.datatype:jackson-datatype-jdk8:2.16.1")
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(21)
}

application {
    mainClass.set("me.redstom.minimal.runner.MainKt")
}

tasks.withType<Jar> {
    manifest {
        attributes("Main-Class" to "me.redstom.minimal.runner.MainKt")
    }
}
