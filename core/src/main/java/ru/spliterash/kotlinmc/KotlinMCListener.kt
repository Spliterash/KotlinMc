package ru.spliterash.kotlinmc

import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.server.PluginDisableEvent
import org.bukkit.plugin.java.JavaPlugin

open class KotlinMCListener : Listener {
    @EventHandler
    open fun onPluginDisable(e: PluginDisableEvent) {
        val plugin = e.plugin
        if (plugin is JavaPlugin)
            CoroutinePluginStorage.shutdown(plugin)
    }
}