package io.github.domgew.kedis.samples

import io.github.domgew.kedis.KedisClient
import io.github.domgew.kedis.commands.KedisListCommands
import io.github.domgew.kedis.commands.KedisValueCommands
import io.github.domgew.kedis.commands.list.LPushCommand
import io.github.domgew.kedis.results.value.SetResult
import kotlin.test.assertEquals

object ListSamples {

    suspend fun simple(
        client: KedisClient,
    ) {
        val key = "list"
        val value = "Test Value"

        client.execute(KedisValueCommands.del(key))
        assertEquals(1L, client.execute(KedisListCommands.lpush(key, "Hello")))
        assertEquals(2L, client.execute(KedisListCommands.lpush(key, "World")))
        assertEquals(2L, client.execute(KedisListCommands.len(key)))
        assertEquals(0L, client.execute(KedisListCommands.len("nonexistantkey")))


        assertEquals("Hello", client.execute(KedisListCommands.rpop(key)))
        assertEquals("World", client.execute(KedisListCommands.rpop(key)))
//         assertEquals(null, client.execute(KedisListCommands.range(key)))
//         assertEquals(listOf("World", "Hello"), client.execute(KedisListCommands.lpop(key, 2)))
//         assertEquals(listOf("Hello", "World"), client.execute(KedisListCommands.rpop(key, 3)))


    }
}
