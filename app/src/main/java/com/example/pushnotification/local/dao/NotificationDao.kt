package com.example.pushnotification.local.dao

import androidx.room.*
import com.example.pushnotification.local.entity.Message
import com.example.pushnotification.local.entity.User
import com.example.pushnotification.local.entity.UserWithMessage

@Dao
interface NotificationDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend    fun addUser(user: User)
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend    fun addMessage(playlist: Message)
    @Transaction
    @Query("SELECT * FROM User")
    fun getUsersWithMessage(): List<UserWithMessage>
    @Query("SELECT * FROM Message where userId = :id")
    fun getMessage(id:Int):List<Message>
    @Query("delete from message")
    fun deleteAll()

    @Query("delete from message where userId=:id")
    fun deleteAllS(id:Int)
}