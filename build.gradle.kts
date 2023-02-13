import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    `java-library`
    kotlin("jvm") version "1.8.20-Beta"
    `maven-publish`
    id("com.github.johnrengelman.shadow") version "7.1.2"
    id("net.minecrell.plugin-yml.bukkit") version "0.5.2"

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
dependencies {
    api(project(":core"))
}
allprojects {
    apply(plugin = "java-library")
    apply(plugin = "kotlin")
    apply(plugin = "maven-publish")

    group = "ru.spliterash"
    version = "1.0.3"

    repositories {
        mavenCentral()
        maven { url = uri("https://repo.papermc.io/repository/maven-public/") }
    }
    java {
        toolchain.languageVersion.set(JavaLanguageVersion.of(17))
        withSourcesJar()

    }
    dependencies {
        api("org.jetbrains.kotlinx:kotlinx-coroutines-core-jvm:1.6.4")
        api("org.jetbrains.kotlinx:kotlinx-coroutines-jdk8:1.6.4")
        api("org.jetbrains.kotlin:kotlin-reflect:1.7.21")

        compileOnly("io.papermc.paper:paper-api:1.19.3-R0.1-SNAPSHOT")
    }
    tasks.withType<KotlinCompile> {
        kotlinOptions.jvmTarget = "1.8"
    }
    val appendName = if (project == rootProject)
        ""
    else
        "-${project.name}"

    publishing {
        publications {
            create<MavenPublication>("maven") {
                groupId = "ru.spliterash"
                artifactId = "kotlin-mc$appendName"

                from(components["java"])
            }
        }

        repositories {
            maven {
                name = "nexus"
                url = uri("https://repo.spliterash.ru/kotlin-mc")
                credentials {
                    username = findProperty("SPLITERASH_NEXUS_USR")?.toString()
                    password = findProperty("SPLITERASH_NEXUS_PSW")?.toString()
                }
            }
        }
    }
}