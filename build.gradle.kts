import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.7.21"
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
    implementation("com.github.shynixn.mccoroutine:mccoroutine-bukkit-api:2.6.0")
    implementation("com.github.shynixn.mccoroutine:mccoroutine-bukkit-core:2.6.0")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core-jvm:1.6.4")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-jdk8:1.6.4")
    implementation("org.jetbrains.kotlin:kotlin-reflect:1.7.21")


    compileOnly("org.spigotmc:spigot-api:1.19.2-R0.1-SNAPSHOT")
}
tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}

tasks {
    assemble { dependsOn(shadowJar) }
    jar { enabled = false }
}

bukkit {
    name = "KotlinMc"
    main = "ru.spliterash.kotlinmc.KotlinMCPlugin"
    apiVersion = "1.13"
}