plugins {
    `java-library`
    id("maven-publish")
}

repositories {
    mavenCentral()
}

dependencies {
    api("dev.langchain4j:langchain4j:0.32.0")
    api("dev.langchain4j:langchain4j-ollama:0.32.0")
    api("dev.langchain4j:langchain4j-local-ai:0.32.0")
    api("dev.langchain4j:langchain4j-open-ai:0.32.0")
    api("dev.langchain4j:langchain4j-anthropic:0.32.0")
    api("dev.langchain4j:langchain4j-mistral-ai:0.32.0")
    api("dev.langchain4j:langchain4j-web-search-engine-google-custom:0.32.0")
    api("dev.langchain4j:langchain4j-web-search-engine-tavily:0.32.0")

    implementation("com.knuddels:jtokkit:1.0.0")
    api("org.commonmark:commonmark:0.22.0")

    compileOnly("org.projectlombok:lombok:1.18.34")
    annotationProcessor("org.projectlombok:lombok:1.18.34")

    testImplementation("org.junit.jupiter:junit-jupiter-api:5.11.0-M2")
    testImplementation("org.mockito:mockito-core:5.11.0")
    testImplementation("org.mockito:mockito-inline:5.2.0")
    testImplementation("org.assertj:assertj-core:3.26.0")

    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.11.0-M2")
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

publishing {
    publications {
        create<MavenPublication>("mavenJava") {
            from(components["java"])
            setGroupId("com.devoxx.genie")
            setArtifactId("devoxx-genie-core")
            setVersion("0.0.0-SNAPSHOT")
        }
    }
}
