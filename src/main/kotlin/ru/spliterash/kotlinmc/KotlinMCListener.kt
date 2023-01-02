package ru.spliterash.kotlinmc

import com.destroystokyo.paper.event.server.ServerTickStartEvent
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.server.PluginDisableEvent

open class KotlinMCListener : Listener {
    @EventHandler
    open fun onPluginDisable(e: PluginDisableEvent) {
        KotlinMCHandler.onPluginDisable(e.plugin)
    }

    @EventHandler
    open fun onTickStart(e: ServerTickStartEvent) {
        KotlinMCHandler.onTickStart(e.tickNumber)
    }
}