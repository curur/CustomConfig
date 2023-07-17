plugins {
    kotlin("jvm") version "1.8.0"
    `maven-publish`
}

group = "com.github.curur.customconfig"
version = "0.0.3"

var something: Any? = null

java {
    toolchain.languageVersion.set(JavaLanguageVersion.of(17))
}

allprojects {
    apply(plugin = "org.jetbrains.kotlin.jvm")

    repositories {
        maven("https://repo.papermc.io/repository/maven-public/")
    }

    dependencies {
        compileOnly("io.papermc.paper:paper-api:1.19.3-R0.1-SNAPSHOT")
    }
}

tasks {
    jar {
        from(project(":customConfig-api").sourceSets["main"].output)
        from(project(":customConfig-plugin").sourceSets["main"].output)
        include("plugin.yml")

//        from(sourceSets["main"].output.filter { !(listOf("Main.kt", "plugin.yml").contains(it.name)) })

    }

    create<Jar>("sources") {
        from(project(":customConfig-api").sourceSets["main"].output)
        destinationDirectory.set(file(".jitpack"))
        archiveBaseName.set("jitpack")
        archiveVersion.set("")
    }

}

publishing {
    publications {
        create<MavenPublication>("customconfig") {

            groupId = "com.github.curur.customconfig"
            artifactId = "customconfig"
            version = "0.0.1"
            artifact(".jitpack\\jitpack.jar")

        }
    }
}