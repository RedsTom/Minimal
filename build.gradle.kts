plugins {
    id("java")
}

group = "me.redstom"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

tasks.test {
    useJUnitPlatform()
}

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(21)
    }
}

allprojects {
    tasks.withType<JavaCompile>().configureEach {
        options.compilerArgs.add("--enable-preview")
        options.encoding = "UTF-8"
    }

    tasks.withType<Test>().configureEach {
        jvmArgs("--enable-preview")
    }
}
