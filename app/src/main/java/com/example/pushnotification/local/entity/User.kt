package com.example.pushnotification.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User(
    @PrimaryKey val userId: Int,
    val name: String,
    val title: String
)