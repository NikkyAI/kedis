package io.github.domgew.kedis.commands.list

import io.github.domgew.kedis.KedisException
import io.github.domgew.kedis.arguments.list.InsertPivotDirection
import io.github.domgew.kedis.commands.KedisFullCommand
import io.github.domgew.kedis.impl.RedisMessage

// // see https://redis.io/commands/linsert/
internal class LInsertCommand(
    val key: String,
    val pivotDirection: InsertPivotDirection,
    val index: Long,
    val element: String,
) : KedisFullCommand<Long?> {
    override fun fromRedisResponse(response: RedisMessage): Long? =
        when (response) {
            is RedisMessage.IntegerMessage ->
                response.value

            is RedisMessage.NullMessage ->
                null

            is RedisMessage.ErrorMessage ->
                handleRedisErrorResponse(
                    response = response,
                )

            else ->
                throw KedisException.WrongResponseException(
                    message = "Expected long or null response, was ${response::class.simpleName}",
                )
        }

    override fun toRedisRequest(): RedisMessage =
        RedisMessage.ArrayMessage(
            value = listOf(
                RedisMessage.BulkStringMessage(OPERATION_NAME),
                RedisMessage.BulkStringMessage(key),
                pivotDirection.redisMessage,
                RedisMessage.IntegerMessage(index),
                RedisMessage.BulkStringMessage(element)
            ),
        )

    companion object {
        private const val OPERATION_NAME = "LINSERT"
    }
}
