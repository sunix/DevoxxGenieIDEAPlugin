import java.util.*

plugins {
    java
    id("org.jetbrains.intellij") version "1.17.2"
    id("com.github.johnrengelman.shadow") version "8.1.1"
}

group = "com.devoxx.genie"
version = "0.2.11"

repositories {
    mavenCentral()
}

tasks.register("updateProperties") {
    doLast {
        val projectVersion = version
        val propertiesFile = file("src/main/resources/application.properties")

        if (propertiesFile.exists()) {
            val properties = Properties().apply {
                load(propertiesFile.inputStream())
            }

            properties.setProperty("version", projectVersion.toString())

            properties.store(propertiesFile.outputStream(), null)
        } else {
            println("application.properties file not found!")
        }
    }
}

tasks.named("buildPlugin") {
    dependsOn("updateProperties")
}

dependencies {
    // Add the dependencies for the core module
    implementation(project(":core"))

    compileOnly("org.projectlombok:lombok:1.18.34")
    annotationProcessor("org.projectlombok:lombok:1.18.34")

    testImplementation("org.junit.jupiter:junit-jupiter-api:5.11.0-M2")
    testImplementation("org.mockito:mockito-core:5.11.0")
    testImplementation("org.mockito:mockito-inline:5.2.0")
    testImplementation("org.assertj:assertj-core:3.26.0")
    testImplementation("io.github.cdimascio:dotenv-java:3.0.0")
    testImplementation("org.junit.jupiter:junit-jupiter-params:5.10.3")

    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.11.0-M2")
}

// Configure Gradle IntelliJ Plugin
// Read more: https://plugins.jetbrains.com/docs/intellij/tools-gradle-intellij-plugin.html
intellij {
    version.set("2023.3.4")
    type.set("IC")
    plugins.set(listOf("java", "markdown"))
}

tasks {
    // Set the JVM compatibility versions
    withType<JavaCompile> {
    }

    patchPluginXml {
        sinceBuild.set("233")
        untilBuild.set("242.*")
    }

    shadowJar {
        mergeServiceFiles()
        manifest {
            attributes(
                "Implementation-Title" to "DevoxxGenie",
                "Implementation-Version" to version,
            )
        }
    }

    test {
        useJUnitPlatform()
    }

    signPlugin {
        certificateChain.set(System.getenv("CERTIFICATE_CHAIN"))
        privateKey.set(System.getenv("PRIVATE_KEY"))
        password.set(System.getenv("PRIVATE_KEY_PASSWORD"))
    }

    publishPlugin {
        token.set(System.getenv("PUBLISH_TOKEN"))
    }
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}
