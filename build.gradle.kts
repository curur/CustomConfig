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

    build {
        dependsOn(named("source"))
    }

    create<Jar>("plugin") {
        archiveClassifier.set("plugin")
        from(sourceSets["main"].output)
    }

    create<Jar>("source") {
        archiveClassifier.set("source")
        from(sourceSets["main"].allSource.filter { it.name != "Main.kt" } )
        exclude("plugin.yml")
        exclude("Main.kt")
    }

}

publishing {
    publications {
        create<MavenPublication>("jitpack") {

            groupId = "com.github.curur.customconfig"
            artifactId = "customconfig"
            version = "0.0.1"

        }
    }
}