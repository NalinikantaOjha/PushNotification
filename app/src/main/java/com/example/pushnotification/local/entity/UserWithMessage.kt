package com.example.pushnotification.local.entity

import androidx.room.Embedded
import androidx.room.Relation

data class UserWithMessage(
    @Embedded val user: User,
    @Relation(
        parentColumn = "userId",
        entityColumn = "userId"
    )
    val playlists: List<Message>
)