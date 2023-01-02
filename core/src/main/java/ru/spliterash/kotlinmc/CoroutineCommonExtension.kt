package ru.spliterash.kotlinmc

import org.bukkit.Bukkit

suspend fun delayTick(ticks: Int) {
    if (ticks <= 0)
        return

    val currentTick = Bukkit.getCurrentTick()
    val resumeTick = ticks + currentTick

    KotlinMCTickDelayStorage.awakeOnTick(resumeTick)
}