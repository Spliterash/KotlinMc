package ru.spliterash.kotlinmc

import kotlinx.coroutines.*
import kotlinx.coroutines.future.future
import org.bukkit.plugin.java.JavaPlugin
import java.util.concurrent.CompletableFuture
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext

private val JavaPlugin.storage: CoroutinePlugin
    get() = CoroutinePluginStorage.find(this)

private val JavaPlugin.scope: CoroutineScope
    get() = storage.scope

suspend fun <T> JavaPlugin.withSync(block: suspend CoroutineScope.() -> T) =
    withContext(storage.minecraftDispatcher, block)

suspend fun <T> JavaPlugin.withIO(block: suspend CoroutineScope.() -> T): T {
    return if (isEnabled)
        withContext(Dispatchers.IO, block)
    else
        runBlocking {
            block()
        }
}

fun JavaPlugin.launch(
    context: CoroutineContext = EmptyCoroutineContext,
    start: CoroutineStart = CoroutineStart.DEFAULT,
    block: suspend CoroutineScope.() -> Unit
): Job = scope.launch(context, start, block)

fun JavaPlugin.launchIO(
    start: CoroutineStart = CoroutineStart.DEFAULT,
    block: suspend CoroutineScope.() -> Unit
): Job = scope.launch(Dispatchers.IO, start, block)

fun <T> JavaPlugin.async(
    context: CoroutineContext = EmptyCoroutineContext,
    start: CoroutineStart = CoroutineStart.DEFAULT,
    block: suspend CoroutineScope.() -> T
): Deferred<T> = scope.async(context, start, block)

fun <T> JavaPlugin.asyncIO(
    start: CoroutineStart = CoroutineStart.DEFAULT,
    block: suspend CoroutineScope.() -> T
): Deferred<T> = scope.async(Dispatchers.IO, start, block)

fun <T> JavaPlugin.future(
    context: CoroutineContext = EmptyCoroutineContext,
    start: CoroutineStart = CoroutineStart.DEFAULT,
    block: suspend CoroutineScope.() -> T
): CompletableFuture<T> = scope.future(context, start, block)

fun <T> JavaPlugin.futureIO(
    start: CoroutineStart = CoroutineStart.DEFAULT,
    block: suspend CoroutineScope.() -> T
): CompletableFuture<T> = scope.future(Dispatchers.IO, start, block)