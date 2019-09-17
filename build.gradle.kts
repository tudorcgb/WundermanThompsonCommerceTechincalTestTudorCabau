

plugins {
    val kotlinVersion = "1.3.21"
    kotlin("jvm") version kotlinVersion
    java
    id("org.springframework.boot") version "2.1.2.RELEASE"
    id("org.jetbrains.kotlin.plugin.spring") version kotlinVersion
    id("org.jetbrains.kotlin.plugin.jpa") version kotlinVersion
    id("io.spring.dependency-management") version "1.0.6.RELEASE"
}

repositories {
    jcenter()
}

dependencies {
    compile( kotlin("stdlib") )
    compile("com.google.guava:guava:18.0")
    compile( "org.apache.commons:commons-lang3:3.3.2")
    testCompile("junit:junit:4.+")

    compile ("com.fasterxml.jackson.dataformat:jackson-dataformat-csv:2.9.6")
    compile ("org.jgrapht:jgrapht-core:1.3.1")

    //compile group: 'com.fasterxml.jackson.dataformat', name: 'jackson-dataformat-csv', version: '2.9.6'
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")

}

tasks.test {
    testLogging {
        events("PASSED", "FAILED", "SKIPPED", "STANDARD_ERROR", "STANDARD_OUT")
    }
}