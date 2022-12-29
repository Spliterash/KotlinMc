package ru.spliterash.kotlinmc

import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import org.bukkit.plugin.java.JavaPlugin

class CoroutinePlugin(
    plugin: JavaPlugin
) {
    private val minecraftDispatcher = MinecraftDispatcher(plugin)
    val scope: CoroutineScope = CoroutineScope(
        SupervisorJob() +
                minecraftDispatcher +
                CoroutineExceptionHandler { _, ex -> ex.printStackTrace() }
    )
}