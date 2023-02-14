plugins {
    kotlin("jvm") version "1.8.0"
    `maven-publish`
}

group = "com.github.curur.customconfig"
version = "0.0.1"

java {
    toolchain.languageVersion.set(JavaLanguageVersion.of(17))
}

repositories {
    maven("https://repo.papermc.io/repository/maven-public/")
}

dependencies {
    compileOnly("io.papermc.paper:paper-api:1.19.3-R0.1-SNAPSHOT")
}

tasks {
    jar {

        from(sourceSets["main"].allSource.filter {it.name != "Main.kt"} )

    }

    create<Jar>("plugin") {

        from(sourceSets["main"].output)

    }

}