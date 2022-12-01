package ru.spliterash.kotlinmc

import org.bukkit.plugin.java.JavaPlugin
class KotlinMCPlugin : JavaPlugin() {
    override fun onEnable() {
        server.pluginManager.registerEvents(KotlinMCListener(), this)
    }
}