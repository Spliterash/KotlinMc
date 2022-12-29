package ru.spliterash.kotlinmc

import org.bukkit.Bukkit
import org.bukkit.plugin.java.JavaPlugin

class KotlinMCPlugin : JavaPlugin() {
    override fun onEnable() {
        Bukkit.getPluginManager().registerEvents(KotlinMCListener(), this)
    }
}