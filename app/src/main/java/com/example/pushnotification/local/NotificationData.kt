package com.example.pushnotification.local

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class NotificationData(val title:String,val desc:String,val message:String,val id:Int,val image:String):Parcelable {
}