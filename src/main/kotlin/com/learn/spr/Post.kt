package com.learn.spr

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id
import java.time.LocalDateTime

@Entity
data class Post(
    var content: String,
    @Id @GeneratedValue
    var id: Int? = null,
    var createdAt: LocalDateTime? = LocalDateTime.now(),
)