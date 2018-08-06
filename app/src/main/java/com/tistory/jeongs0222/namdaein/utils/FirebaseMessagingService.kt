package com.tistory.jeongs0222.namdaein.utils

import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.media.RingtoneManager
import android.support.v4.app.NotificationCompat
import android.util.Log
import com.google.firebase.messaging.RemoteMessage
import com.tistory.jeongs0222.namdaein.R
import com.tistory.jeongs0222.namdaein.model.DBHelper
import com.tistory.jeongs0222.namdaein.ui.activity.main.MainActivity


class FirebaseMessagingService : com.google.firebase.messaging.FirebaseMessagingService() {

    private val TAG = "남대인"

    private lateinit var push: String

    private val dbHelper = DBHelper(this, "USERINFO.db", null, 1)


    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        super.onMessageReceived(remoteMessage)

        push = dbHelper.getPush()!!

        if (push == "on") {
            Log.e("messageReceive","messageReceive")
            sendNotification(remoteMessage.notification!!.body!!)
        }
    }

    private fun sendNotification(messageBody: String) {
        val intent = Intent(this, MainActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)

        val pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_ONE_SHOT)

        val defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)

        val notificationBuilder = NotificationCompat.Builder(this)
                .apply {
                    setSmallIcon(R.mipmap.ic_launcher)
                    setContentTitle(TAG)
                    setContentText(messageBody)
                    setAutoCancel(true)
                    setSound(defaultSoundUri)
                    setContentIntent(pendingIntent)
                }

        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        notificationManager.notify(0, notificationBuilder.build())
    }
}
