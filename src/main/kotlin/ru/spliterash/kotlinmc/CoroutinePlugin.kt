package ru.spliterash.kotlinmc

import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import org.bukkit.plugin.java.JavaPlugin

class CoroutinePlugin(
    plugin: JavaPlugin
) {
    val minecraftDispatcher = MinecraftDispatcher(plugin)
    val scope: CoroutineScope = CoroutineScope(
        SupervisorJob() +
                minecraftDispatcher +
                CoroutineExceptionHandler { _, ex -> ex.printStackTrace() }
    )

    fun shutdown() {
        scope.cancel()
    }
}