package com.example.pushnotification

import android.app.NotificationManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.Toast
import androidx.core.app.RemoteInput
import com.example.pushnotification.local.database.NotificationDatabase

class NotificationReceiver :BroadcastReceiver(){
    var notificationManager: NotificationManager? = null
    override fun onReceive(p0: Context, p1: Intent) {
        val dao = NotificationDatabase.getNotificationDatabase(p0).getNotificationDao()

       // Log.d("remoteinput",p1.getIntExtra("ID",0).toString())
        notificationManager = p0.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager?
        if (p1.hasExtra("D")) {
            val remoteInput = RemoteInput.getResultsFromIntent(p1)
            if (remoteInput != null) {
                Log.d("remoteinput",remoteInput.toString())
                val feedback = remoteInput.getCharSequence("DirectReplyNotification")
                Toast.makeText(p0,feedback.toString(),Toast.LENGTH_LONG).show()

                val noteId: Int = p1.getIntExtra("D", 0)
                Log.d("remoteinput",noteId.toString())


                notificationManager!!.cancel(noteId)





        } else {
                val noteId: Int = p1.getIntExtra("ID", 0)
                Log.d("remoteinput",noteId.toString())
                notificationManager!!.cancel(noteId)
            }

        }

    }
}
