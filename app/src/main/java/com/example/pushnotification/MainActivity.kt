package com.example.pushnotification

import android.app.*
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.app.RemoteInput
import com.bumptech.glide.Glide
import com.example.pushnotification.local.NotificationData
import com.example.pushnotification.local.database.NotificationDatabase
import com.example.pushnotification.local.entity.Message
import com.example.pushnotification.local.entity.User
import com.example.pushnotification.viewmodel.FViewModel
import com.google.firebase.messaging.FirebaseMessaging
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    val CHANNEL_NAME = "GFG ContentWriting"
    val CHANNEL_DESCRIPTION = "GFG NOTIFICATION"
    val context=this
    val viewModel by viewModels<FViewModel>()



    //
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val intent=getIntent()
     //   if (intent!=null){
            val notificationData=intent.getParcelableExtra<NotificationData>("notificationData")
            tvTitle.text=notificationData?.title
            tvDesc.text=notificationData?.desc
            tvMessage2.setText(notificationData?.message)
            Glide.with(ivImageView).load(notificationData?.image).into(ivImageView)
       // }

        class Receiver():BroadcastReceiver(){
            override fun onReceive(p0: Context?, p1: Intent?) {
                val notificationData=p1?.getParcelableExtra<NotificationData>("notificationData")
                val id=notificationData?.id
                val title=notificationData?.title
                val desc=notificationData?.desc
                val message1=notificationData?.message
                val image=notificationData?.image

                //jsonArray.add(0,message1.toString())

                   val a=p1?.getStringArrayExtra("jsonarray")
                   tvTitle.text=title
                   tvDesc.text=desc
                tvMessage2.setText(message1)
                   Toast.makeText(p0, title,Toast.LENGTH_LONG).show()
                    var mes:String?=null

                   Glide.with(ivImageView).load(image.toString()).into(ivImageView)

            }
        }
        val receiver=Receiver()
        val intentFilter=IntentFilter("com.nalini.custom")
        registerReceiver(receiver,intentFilter)


   }



}




















































//                FirebaseMessaging.getInstance().subscribeToTopic("weather")
//            .addOnCompleteListener { task ->
//                var msg = "Done"
//                Log.d("nalinidoneorfailed", msg)
//                if (!task.isSuccessful) {
//                    msg = "Failed"
//                    Log.d("nalinidoneorfailed", msg)
//
//                }
//            }


//        FirebaseMessaging.getInstance().token.addOnSuccessListener { result->
//              if (result!=null){
//                  Log.d("Token",result.toString())
//              }
//
//          }
//

/*
 fun createNewContact() {
        val params = JSONObject()
        params.put("firstName", "firstName")
        params.put("lastName", "lastName")
        params.put("email", "email")
        val paramString = params.toString()
        CoroutineScope(Dispatchers.IO).launch {
            var url: URL? = null

            try {
                url = URL("https://dummyapi.io/data/v1/user/create")
                val urlConnection = url.openConnection() as HttpURLConnection
                urlConnection.doInput = true
                urlConnection.doOutput = true
                // Send the JSON we created
                val outputStreamWriter = OutputStreamWriter(urlConnection.outputStream)
                outputStreamWriter.write(paramString)
                outputStreamWriter.flush()
                Log.d("nalinierror1", urlConnection.responseCode.toString())

            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

 */