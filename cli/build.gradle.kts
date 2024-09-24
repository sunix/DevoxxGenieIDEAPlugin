

plugins {
    id("application")
}

application {
    mainClass = "com.devoxx.genie.Hello"
}

dependencies {
    implementation(project(":core"))
}

repositories {
    mavenCentral()
}
