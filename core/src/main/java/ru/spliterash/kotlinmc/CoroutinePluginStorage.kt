package ru.spliterash.kotlinmc

import org.bukkit.plugin.java.JavaPlugin

internal object CoroutinePluginStorage {
    private val map = hashMapOf<JavaPlugin, CoroutinePlugin>()

    fun find(plugin: JavaPlugin): CoroutinePlugin {
        return map.computeIfAbsent(plugin, CoroutinePluginStorage::createCoroutinePlugin)
    }

    fun shutdown(plugin: JavaPlugin) {
        map.remove(plugin)?.shutdown()
    }

    private fun createCoroutinePlugin(plugin: JavaPlugin): CoroutinePlugin {
        return CoroutinePlugin(plugin)
    }
}