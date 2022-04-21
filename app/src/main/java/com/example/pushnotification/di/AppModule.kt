package com.example.pushnotification.di

import android.app.Application
import android.content.Context
import com.example.pushnotification.local.dao.NotificationDao
import com.example.pushnotification.local.database.NotificationDatabase


import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule  {


    @Provides
    @Singleton
    fun providesUserDatabase( context: Application): NotificationDatabase {
        return NotificationDatabase.getNotificationDatabase(context)
    }
    @Provides
    @Singleton
    fun providesUserContext( context: Application): Context {
        return context
    }
    @Provides
    @Singleton
    fun providesDao(notificationDatabase: NotificationDatabase): NotificationDao {
        return notificationDatabase.getNotificationDao()
    }

}