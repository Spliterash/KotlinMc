package ru.spliterash.kotlinmc

import org.bukkit.plugin.Plugin
import org.bukkit.plugin.java.JavaPlugin

object KotlinMCHandler {
    fun onPluginDisable(plugin: Plugin) {
        if (plugin is JavaPlugin)
            CoroutinePluginStorage.shutdown(plugin)
    }
}