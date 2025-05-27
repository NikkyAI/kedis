package io.github.domgew.kedis.commands

import io.github.domgew.kedis.arguments.list.InsertPivotDirection
import io.github.domgew.kedis.arguments.list.ListDirection
import io.github.domgew.kedis.commands.list.LIndexCommand
import io.github.domgew.kedis.commands.list.LInsertCommand
import io.github.domgew.kedis.commands.list.LLenCommand
import io.github.domgew.kedis.commands.list.LMoveCommand
import io.github.domgew.kedis.commands.list.LPopCommand
import io.github.domgew.kedis.commands.list.LPopListCommand
import io.github.domgew.kedis.commands.list.LPushCommand
import io.github.domgew.kedis.commands.list.LRangeCommand
import io.github.domgew.kedis.commands.list.RPopCommand
import io.github.domgew.kedis.commands.list.RPopListCommand

public object KedisListCommands {
    public fun len(
        key: String
    ): KedisCommand<Long> = LLenCommand(
        key = key,
    )

    public fun index(
        key: String,
        index: Long,
    ): KedisCommand<String?> = LIndexCommand(
        key = key,
        index = index,
    )

    public fun insert(
        key: String,
        pivotDirection: InsertPivotDirection,
        index: Long,
        element: String
    ): KedisCommand<Long?> = LInsertCommand(
        key = key,
        pivotDirection = pivotDirection,
        index = index,
        element = element,
    )

    public fun move(
        source: String,
        destination: String,
        sourceDirection: ListDirection,
        destinationDirection: ListDirection,
    ): KedisCommand<String?> = LMoveCommand(
        source = source,
        destination = destination,
        sourceDirection = sourceDirection,
        destinationDirection = destinationDirection,
    )


    public fun lpush(
        key: String,
        element: String,
        additionalElements: List<String> = emptyList()
    ): KedisCommand<Long?> = LPushCommand(
        key = key,
        element = element,
        additionalElements = additionalElements
    )

    public fun rpush(
        key: String,
        element: String,
        additionalElements: List<String> = emptyList()
    ): KedisCommand<Long?> = LPushCommand(
        key = key,
        element = element,
        additionalElements = additionalElements
    )
    public fun lpop(
        key: String,
    ): KedisCommand<String?> = LPopCommand(
        key = key,
    )
    public fun lpop(
        key: String,
        count: Long,
    ): KedisCommand<List<String>?> = LPopListCommand(
        key = key,
        count = count,
    )

    public fun rpop(
        key: String,
        count: Long,
    ): KedisCommand<List<String>?> = RPopListCommand(
        key = key,
        count = count,
    )
    public fun rpop(
        key: String,
    ): KedisCommand<String?> = RPopCommand(
        key = key,
    )
    public fun range(
        key: String,
        start: Long = -1,
        end: Long = -1
    ): KedisCommand<List<String>?> = LRangeCommand(
        key = key,
        start = start,
        stop = end
    )
}
