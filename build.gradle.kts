import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    `java-library`
    kotlin("jvm") version "1.7.21"
    `maven-publish`
    id("com.github.johnrengelman.shadow") version "7.1.2"
    id("net.minecrell.plugin-yml.bukkit") version "0.5.2"

}

group = "ru.spliterash"
version = "1.0.0"

repositories {
    mavenCentral()
    maven("https://hub.spigotmc.org/nexus/content/repositories/snapshots")
}

dependencies {
    api("org.jetbrains.kotlinx:kotlinx-coroutines-core-jvm:1.6.4")
    api("org.jetbrains.kotlinx:kotlinx-coroutines-jdk8:1.6.4")
    api("org.jetbrains.kotlin:kotlin-reflect:1.7.21")

    compileOnly("org.spigotmc:spigot-api:1.19.2-R0.1-SNAPSHOT")
}
tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}

tasks {
    java {
        withSourcesJar()
    }
    assemble { dependsOn(shadowJar) }
}

bukkit {
    name = "KotlinMc"
    main = "ru.spliterash.kotlinmc.KotlinMCPlugin"
    apiVersion = "1.13"
}

publishing {
    publications {
        create<MavenPublication>("maven") {
            groupId = "ru.spliterash"
            artifactId = "kotlin-mc"

            from(components["java"])
        }
    }

    repositories {
        maven {
            name = "nexus"
            url = uri("https://nexus.spliterash.ru/repository/kotlin-mc")
            credentials {
                username = findProperty("SPLITERASH_NEXUS_USR")?.toString()
                password = findProperty("SPLITERASH_NEXUS_PSW")?.toString()
            }
        }
    }
}