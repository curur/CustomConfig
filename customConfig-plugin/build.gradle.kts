dependencies {
    implementation(project(":customConfig-api"))
}

tasks.jar {

    include("plugin.yml")

}