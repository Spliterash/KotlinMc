package ru.spliterash.kotlinmc

import kotlinx.coroutines.suspendCancellableCoroutine
import java.util.*
import kotlin.coroutines.Continuation
import kotlin.coroutines.resume

object KotlinMCTickDelayStorage {

    private val tickMap: TreeMap<Int, MutableList<Continuation<Unit>>> = TreeMap()
    suspend fun awakeOnTick(awakingTick: Int) {
        suspendCancellableCoroutine { cont ->
            val list = tickMap.computeIfAbsent(awakingTick) { ArrayList() }
            list.add(cont)

            cont.invokeOnCancellation {
                list.remove(cont)
            }
        }
    }

    fun onTickStart(tick: Int) {
        val map = tickMap.headMap(tick, true)
        for ((_, cont) in map) {
            for (continuation in cont) {
                continuation.resume(Unit)
            }
        }
        map.clear()
    }
}