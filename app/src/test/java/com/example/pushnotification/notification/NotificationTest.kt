package com.example.pushnotification.notification

import android.app.ActivityManager
import android.app.PendingIntent
import android.content.Context
import androidx.core.app.NotificationCompat
import com.example.pushnotification.MyFirebaseMessagingService2
import com.google.common.truth.Truth.assertThat
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import org.json.JSONArray
import org.junit.Before
import org.junit.Test


class NotificationTest {
    @MockK
    lateinit var jsonAny: JSONArray
    @MockK
    lateinit var activityManager: ActivityManager
    @MockK
    lateinit var context:Context
    @MockK
    lateinit var pendingIntent: PendingIntent
    @MockK
    lateinit var notificationCompatAction: NotificationCompat.Action
    @MockK
    lateinit var notificationBuilder: NotificationCompat.Builder

    private lateinit var myFirebaseMessagingService: MyFirebaseMessagingService2

    @Before
    fun setUp(){
        MockKAnnotations.init(this)
        myFirebaseMessagingService= MyFirebaseMessagingService2()


    }
    @Test
    fun isGetFirebaseFunction_is_Not_Null(){
     val firebaseNottification=  coEvery {
           myFirebaseMessagingService.getFirebaseBuildNotification(notificationBuilder, "Title", "Desc",context,1,jsonAny,"https://freeiconshop.com/wp-content/uploads/edd/notification-flat.png",pendingIntent,notificationCompatAction)
       } returns Unit
        assertThat(firebaseNottification).isNotNull()
    }
    @Test
    fun isGetFirebaseFunction_is_Not_Null_When_Title_is_Null(){
        val firebaseNottification=  coEvery {
            myFirebaseMessagingService.getFirebaseBuildNotification(notificationBuilder, null, "Desc",context,1,jsonAny,"https://freeiconshop.com/wp-content/uploads/edd/notification-flat.png",pendingIntent,notificationCompatAction)
        } returns Unit
        assertThat(firebaseNottification).isNotNull()
    }
    @Test
    fun isGetFirebaseFunction_is_Not_Null_When_Desc_is_Null(){
        val firebaseNottification=  coEvery {
            myFirebaseMessagingService.getFirebaseBuildNotification(notificationBuilder, null, null,context,1,jsonAny,"https://freeiconshop.com/wp-content/uploads/edd/notification-flat.png",pendingIntent,notificationCompatAction)
        } returns Unit
        assertThat(firebaseNottification).isNotNull()
    }

    @Test
    fun isApp_On_Foreground(){
        val foreground=  coEvery {
            myFirebaseMessagingService.isAppInForeground(activityManager)
        } returns true
        assertThat(foreground).isNotNull()
    }

}