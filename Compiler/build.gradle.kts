plugins {
    id("java")
}

group = "me.redstom"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")

    implementation("org.springframework:spring-context:6.1.3")
    annotationProcessor("org.springframework:spring-context:6.1.3")
}

tasks.test {
    useJUnitPlatform()
}
