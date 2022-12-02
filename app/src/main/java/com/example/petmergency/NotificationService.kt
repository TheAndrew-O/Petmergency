package com.example.petmergency

import android.app.*
import android.app.Notification
import android.content.Context
import android.content.Intent
import android.os.Build
import android.widget.Toast
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat.getSystemService

class NotificationService(private val context: Context): Application() {

    private var notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
    fun showNotification(medication: String){
        val activityIntent = Intent(context, com.example.petmergency.medication::class.java)
        val pendingIntent = PendingIntent.getActivity(context, 1, activityIntent,
                                                    if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) PendingIntent.FLAG_IMMUTABLE else 0)
        val dismiss = PendingIntent.getBroadcast(context, 2, Intent(context, NotificationReceiver::class.java),  if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) PendingIntent.FLAG_IMMUTABLE else 0)
        val notif = NotificationCompat.Builder(context, CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_baseline_medical_services_24)
                .setContentTitle("Time to administer medication")
                .setContentText("Medication: $medication")
                .setContentIntent(pendingIntent)
                .addAction(R.drawable.ic_baseline_medical_services_24, "Ok",dismiss)
                .build()
        notificationManager.notify(1, notif)
    }
    companion object{
        const val CHANNEL_ID = "channel_id"
    }
    public fun createNotificationChannel(){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            val channel = NotificationChannel(CHANNEL_ID, "Medication", NotificationManager.IMPORTANCE_DEFAULT)
            channel.description = "Provide medication"

            notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }

    public fun delete(){
        var toast = Toast.makeText(this, "DASDA", Toast.LENGTH_SHORT)
        toast.show()
    }

}