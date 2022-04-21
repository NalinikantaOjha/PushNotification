package com.example.pushnotification.local.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.pushnotification.local.dao.NotificationDao
import com.example.pushnotification.local.entity.User
import com.example.pushnotification.local.entity.Message

@Database(entities = [User::class,Message::class],version = 1)
abstract class NotificationDatabase : RoomDatabase() {
    abstract fun getNotificationDao(): NotificationDao
    companion object{
        private var instance:NotificationDatabase?=null
        fun getNotificationDatabase(context: Context):NotificationDatabase{
            if (instance!=null){
                return instance!!
            }else{
                val builder= Room.databaseBuilder(
                    context.applicationContext,
                    NotificationDatabase::class.java,
                    "notificationDb"
                )
                builder.fallbackToDestructiveMigration()
                instance=builder.build()
            }
            return instance!!
        }
    }
}