package io.corbs.redis

import org.springframework.data.annotation.Id
import org.springframework.data.redis.core.RedisHash
import org.springframework.data.redis.core.index.Indexed

enum class Gender {
    FEMALE, MALE, NA
}

@RedisHash("persons")
data class Person(
    @Indexed val firstName: String,
    @Indexed val lastName: String,
    val gender: Gender) {

    @Id val id: String? = null
    constructor() : this("","", Gender.NA)
}
