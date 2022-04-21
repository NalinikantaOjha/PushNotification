package com.example.pushnotification.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.pushnotification.local.dao.NotificationDao
import com.example.pushnotification.local.entity.Message
import com.example.pushnotification.local.entity.User
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.json.JSONArray
import org.json.JSONObject
import java.io.OutputStreamWriter
import java.net.HttpURLConnection
import java.net.URL
import javax.inject.Inject

class FRepository @Inject constructor() {
//    fun getMessage(id:Int):LiveData<List<Message>>{
//       return dao.getMessage(id)
//    }
//    suspend    fun addUser(user: User){
//        dao.addUser(user)
//    }





    fun postData() {
        val data = JSONObject()
        val params = JSONObject()
       // data.put("image",image)
        data.put("title", "you have new message")
        data.put("desc", "This message is from full creative")
        val a= JSONArray()
        a.put("nalini kanta ojha")
        a.put("works in any where")
        a.put("feeling good")
        data.put("message",a)
        params.put(
            "to",
            "ewVjH0a8S4a1sodo-vrrq5:APA91bG7gEjFhieVMquln21Ai8Czqh0M_j6RU3sEh2F4nZaPsVpnGWha_V1qaSqsvpV9AoURx4m7fiRBjMKN8SWaGk5yBsiHYv89gZWRNIolOMJlej3FNZYDm72ndAkC2V8oAn_dNwxH"
        )
        params.put("data", data)
        val paramsString = params.toString()
        CoroutineScope(Dispatchers.IO).launch {
            var url: URL? = null
            try {
                url = URL("https://fcm.googleapis.com/fcm/send")
                //Open the connection and connect to server
                val urlConnection = url.openConnection() as HttpURLConnection
                urlConnection.requestMethod = "POST"
                urlConnection.setRequestProperty("Content-Type", "application/json")
                // The format of the content we're sending to the server
                urlConnection.setRequestProperty(
                    "Authorization",
                    "key=AAAAsnMqDDg:APA91bEceJjVJ3frfsfUQOFusIMko806PKDlTvyuyEQpuwnfBGYwyFKQ9W89ezk-7j8TYY9hm1x7nQncLiGBRTqzUxAHnnZQsWJtwnnhLfPpTCLG2kAuFgAac_pLv9Iz95OJyG99mrQH"
                )
                // The format of response we want to get from the server
                urlConnection.doInput = true
                urlConnection.doOutput = true
                // Send the JSON we created
                val outputStreamWriter = OutputStreamWriter(urlConnection.outputStream)
                outputStreamWriter.write(paramsString)
                outputStreamWriter.flush()
                Log.d("nalinierror", urlConnection.responseCode.toString())

            } catch (e: Exception) {
                e.printStackTrace()
            }
        }


    }
    fun createNewContact(firstName: String, lastName: String, email: String, arrayList: ArrayList<String>) {
        val params = JSONObject()
        params.put("firstName", firstName)
        params.put("lastName", lastName)
        params.put("email", email)
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
                Log.d("nalinierror", urlConnection.responseCode.toString())

            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}