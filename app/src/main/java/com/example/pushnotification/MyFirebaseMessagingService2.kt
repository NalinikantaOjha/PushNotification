package com.example.pushnotification

import android.app.*
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.app.RemoteInput
import com.example.pushnotification.local.NotificationData
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import org.json.JSONArray


class MyFirebaseMessagingService2 :FirebaseMessagingService() {

    val CHANNEL_NAME = "GFG ContentWriting"
    val CHANNEL_DESCRIPTION = "GFG NOTIFICATION"
    val context=this


    override fun onMessageReceived(message: RemoteMessage) {
        super.onMessageReceived(message)
        val nBuilder = NotificationCompat.Builder(this,"Firebase")
        val jsonArray=JSONArray(message.data.get("message"))
        val cancelIntent = Intent(this, NotificationReceiver::class.java)
        cancelIntent.putExtra("ID", message.data.get("id").toString().toInt())
        val cancelPendingIntent = PendingIntent.getBroadcast(this, 100, cancelIntent, PendingIntent.FLAG_UPDATE_CURRENT)
        val feedbackIntent = Intent(this, NotificationReceiver::class.java)
        feedbackIntent.putExtra("D",message.data.get("id").toString().toInt())
        val feedbackPendingIntent = PendingIntent.getBroadcast(this, 100, feedbackIntent, PendingIntent.FLAG_UPDATE_CURRENT)
        val remoteInput = RemoteInput.Builder("DirectReplyNotification").build()
        val cancelaction=NotificationCompat.Action.Builder(android.R.drawable.ic_menu_camera,"cancel",cancelPendingIntent).build()
        val action = NotificationCompat.Action.Builder(android.R.drawable.ic_delete, "Write here...", feedbackPendingIntent).addRemoteInput(remoteInput).build()
        val activityManager = getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager


       if ( isAppInForeground(activityManager)){
           val intent =Intent("com.nalini.custom")
       val notificationData=NotificationData(message.data.get("title").toString(),message.data.get("desc").toString(),jsonArray[0].toString(),message.data.get("id").toString().toInt(),message.data.get("image").toString())
           intent.putExtra("notificationData",notificationData)
           sendBroadcast(intent)
       }else{
            getFirebaseBuildNotification(nBuilder,
                message.data.get("title").toString(),
                message.data.get("desc").toString(),
                context,
                message.senderId.toString().toLong(),jsonArray[0].toString(),message.data.get("image").toString(),cancelaction,action,message.data.get("id").toString().toInt()
            )
        }






    }
    fun getFirebaseBuildNotification(nBuilder:NotificationCompat.Builder,title:String?, desc:String?, context: Context, id:Long,message:String,image:String,actionCancel:NotificationCompat.Action,action:NotificationCompat.Action,id2:Int){

        val bundle=Bundle()
        val intent =Intent(applicationContext,MainActivity::class.java)
        val notificationData=NotificationData(title.toString(),desc.toString(),message.toString(),id.toInt(),image.toString())
        intent.putExtra("notificationData",notificationData)
        val pi = PendingIntent.getActivity(this, 0, intent, 0);
            notificationChannel()
            nBuilder.setContentTitle(title)
            .setContentText(desc)
            .setSmallIcon(R.drawable.ic_baseline_notifications_active_24)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setAutoCancel(true)
            .setStyle(NotificationCompat.InboxStyle().addLine(title.toString()).addLine(message.toString()))
                .setContentIntent(pi)
                .addAction(actionCancel)
                .addAction(action)
                .setColor(Color.BLACK)
                .addExtras(bundle)
                .setGroup(id.toString())


            //.setLargeIcon((Glide.with(context).asBitmap().load(image).submit().get())).build()

       //  finally notifying the notification
        val nManager = NotificationManagerCompat.from(context)
       nManager.notify(id2.toInt(), nBuilder.build())
    }


    private fun notificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel("Firebase", CHANNEL_NAME, NotificationManager.IMPORTANCE_DEFAULT).apply {
                description = CHANNEL_DESCRIPTION
            }
            val notificationManager: NotificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }
    fun isAppInForeground(activityManager:ActivityManager): Boolean {
        val appProcesses = activityManager.runningAppProcesses ?: return false
        for (appProcess in appProcesses) {
            if (appProcess.importance == ActivityManager.RunningAppProcessInfo.IMPORTANCE_FOREGROUND && appProcess.processName == packageName) {
                return true
            }
        }
        return false
    }
}