plugins {
    id("java")
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")

    testImplementation("org.testcontainers:testcontainers:1.18.3")

    testImplementation("org.testcontainers:postgresql:1.18.3")
    testImplementation("org.postgresql:postgresql:42.2.1")

    testImplementation("org.jooq:jooq:3.17.7")
    testImplementation("org.jooq:jooq-meta:3.17.7")
    testImplementation("org.jooq:jooq-codegen:3.17.7")

    testImplementation("org.apache.logging.log4j:log4j-api:2.19.0")
    testImplementation("org.apache.logging.log4j:log4j-core:2.19.0")
}

tasks.test {
    useJUnitPlatform()
}