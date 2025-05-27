package io.github.domgew.kedis.commands.list

import io.github.domgew.kedis.KedisException
import io.github.domgew.kedis.commands.KedisFullCommand
import io.github.domgew.kedis.impl.RedisMessage

// // see https://redis.io/commands/lpop/
internal class LPopListCommand(
    val key: String,
    val count: Long,
) : KedisFullCommand<List<String>?> {

    override fun fromRedisResponse(response: RedisMessage): List<String>? =
        when (response) {
            is RedisMessage.ArrayMessage ->
                response.value
                    .takeIf { it.isNotEmpty() }
                    ?.map {
                        if (it is RedisMessage.StringMessage) {
                            it.value
                        } else {
                            throw KedisException.WrongResponseException(
                                message = "Expected item key to be of type string," +
                                    " was ${it::class.simpleName}",
                            )
                        }
                    }

            is RedisMessage.NullMessage ->
                null

            is RedisMessage.ErrorMessage ->
                handleRedisErrorResponse(
                    response = response,
                )

            else ->
                throw KedisException.WrongResponseException(
                    message = "Expected array or null response, was ${response::class.simpleName}",
                )
        }

    override fun toRedisRequest(): RedisMessage =
        RedisMessage.ArrayMessage(
            value = listOfNotNull(
                RedisMessage.BulkStringMessage(OPERATION_NAME),
                RedisMessage.BulkStringMessage(key),
                RedisMessage.IntegerMessage(count),
            ),
        )

    companion object {
        private const val OPERATION_NAME = "LPOP"
    }
}
