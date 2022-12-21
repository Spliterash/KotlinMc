package ru.spliterash.kotlinmc

import org.bukkit.plugin.java.JavaPlugin
import ru.spliterash.kotlinmc.KotlinMCInitializer

class KotlinMCPlugin : JavaPlugin() {
    override fun onEnable() {
        KotlinMCInitializer.init(this)
    }
}