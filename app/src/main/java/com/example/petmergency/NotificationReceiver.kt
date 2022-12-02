package com.example.petmergency

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent

class NotificationReceiver: BroadcastReceiver() {

    override fun onReceive(p0: Context, p1: Intent?) {
        TODO("Not yet implemented")
        val service = NotificationService(p0)
        service.delete()
    }
}