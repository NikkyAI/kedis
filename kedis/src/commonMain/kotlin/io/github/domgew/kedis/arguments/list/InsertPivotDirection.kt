package io.github.domgew.kedis.arguments.list

import io.github.domgew.kedis.impl.RedisMessage

public enum class InsertPivotDirection {
    BEFORE,
    AFTER,
    ;
    internal val redisMessage get() = RedisMessage.SimpleStringMessage(name)
}
