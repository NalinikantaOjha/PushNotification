package com.example.pushnotification.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Message(
    @PrimaryKey val messageId: Int,
    val message: String,
    val userId: Int,
)