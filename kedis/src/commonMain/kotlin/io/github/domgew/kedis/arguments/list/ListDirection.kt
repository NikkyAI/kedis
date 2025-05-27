package io.github.domgew.kedis.arguments.list

import io.github.domgew.kedis.impl.RedisMessage

public enum class ListDirection {
    LEFT,
    RIGHT,
    ;
    internal val redisMessage get() = RedisMessage.SimpleStringMessage(name)
}
