package ru.spliterash.kotlinmc

import org.bukkit.plugin.java.JavaPlugin

internal object CoroutinePluginStorage {
    private val map = hashMapOf<JavaPlugin, CoroutinePlugin>()

    fun find(plugin: JavaPlugin): CoroutinePlugin {
        if (!plugin.isEnabled) throw RuntimeException("${plugin.name} trying get coroutine plugin when disabled")
        return map.computeIfAbsent(plugin, CoroutinePluginStorage::createCoroutinePlugin)
    }

    fun shutdown(plugin: JavaPlugin) {
        map.remove(plugin)
    }

    private fun createCoroutinePlugin(plugin: JavaPlugin): CoroutinePlugin {
        return CoroutinePlugin(plugin)
    }
}