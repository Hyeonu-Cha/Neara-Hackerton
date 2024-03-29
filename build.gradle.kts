import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar
import java.net.URI

plugins {
    kotlin("jvm") version "1.8.0"
    id("com.github.johnrengelman.shadow") version "7.0.0"
    application
}

group = "org.example"
version = "1.0-SNAPSHOT"

application {
    mainClass.set("org.example.CrazyKt")
}

repositories {
    mavenCentral()
    maven {url = URI("https://jitpack.io") }
}

dependencies {
    // https://mvnrepository.com/artifact/dev.robocode.tankroyale/robocode-tankroyale-bot-api
    implementation("dev.robocode.tankroyale:robocode-tankroyale-bot-api:0.19.2")
    //implementation("com.github.Typiqally:kt-behaviour-tree:1.0.2")
    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<ShadowJar> {
    archiveFileName.set("Crazy.jar")

    doLast {
        copy {
            from("src/main/kotlin/org.example/Crazy.json")
            from("$buildDir/libs/Crazy.jar")
            into("Crazy")
        }
    }

}

tasks.clean {
    delete ("Crazy/Crazy.json")
    delete ("Crazy/Crazy.jar")
}

kotlin {
    jvmToolchain(11)
}
