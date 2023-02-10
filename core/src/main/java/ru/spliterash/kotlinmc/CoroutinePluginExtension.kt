package ru.spliterash.kotlinmc

import kotlinx.coroutines.*
import kotlinx.coroutines.future.future
import org.bukkit.plugin.java.JavaPlugin
import java.util.concurrent.CompletableFuture
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext

private val JavaPlugin.storage: CoroutinePlugin
    get() = CoroutinePluginStorage.find(this)

/**
 * Не юзать без крайней необходимости
 *
 * Лучше юзайте методы
 */
val JavaPlugin.scope: CoroutineScope
    get() = storage.scope

suspend fun <T> JavaPlugin.withSync(block: suspend CoroutineScope.() -> T): T {
    return if (isEnabled)
        withContext(storage.scope.coroutineContext, block)
    else
        runBlocking {
            block()
        }
}

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
): Job {
    return if (isEnabled)
        scope.launch(context, start, block)
    else {
        runBlocking { block() }
        Job().apply { complete() }
    }
}

fun JavaPlugin.launchIO(
    start: CoroutineStart = CoroutineStart.DEFAULT,
    block: suspend CoroutineScope.() -> Unit
): Job {
    return if (isEnabled)
        scope.launch(Dispatchers.IO, start, block)
    else {
        runBlocking { block() }
        Job().apply { complete() }
    }
}

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


