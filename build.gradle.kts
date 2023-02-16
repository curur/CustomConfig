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

    create<Jar>("sources") {
        archiveClassifier.set("source")
        from(sourceSets["main"].allSource.filter { it.name != "Main.kt" } )
        exclude("plugin.yml")
    }

}

publishing {
    publications {
        create<MavenPublication>("customConfig") {

            groupId = "com.github.curur.customconfig"
            artifactId = "customconfig"
            version = "0.0.1"
            artifact(tasks["sources"])

        }
    }
}