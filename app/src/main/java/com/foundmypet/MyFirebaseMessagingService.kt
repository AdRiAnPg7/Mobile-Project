package com.foundmypet

import android.app.Notification
import android.app.NotificationManager
import android.content.Context
import android.media.RingtoneManager
import android.util.Log
import androidx.core.app.NotificationCompat
import com.google.firebase.messaging.FirebaseMessaging
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class MyFirebaseMessagingService : FirebaseMessagingService() {
    override fun onNewToken(token: String) {
        super.onNewToken(token)
        Log.d("TOKEN", token)
    }

    override fun onMessageReceived(msg: RemoteMessage) {
        super.onMessageReceived(msg)
        if(msg.notification!=null){
            showNotification(msg.notification!!.title,
            msg.notification!!.body)
        }
    }
    fun showNotification(title:String?,description:String?){
        val sound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
        val notification = NotificationCompat.Builder(this)
            .setSmallIcon(R.drawable.huella)
            .setContentTitle(title)
            .setContentText(description)
            .setSound(sound)
            .setAutoCancel(true)

        val notifyManager = getSystemService(Context.NOTIFICATION_SERVICE)
        as NotificationManager

        notifyManager.notify(0,notification.build())
    }
}