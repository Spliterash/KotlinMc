package ru.spliterash.kotlinmc

import org.bukkit.Bukkit
import org.bukkit.plugin.Plugin

object KotlinMCInitializer {
    fun init(plugin: Plugin) {
        Bukkit.getPluginManager().registerEvents(KotlinMCListener(), plugin)
    }
}