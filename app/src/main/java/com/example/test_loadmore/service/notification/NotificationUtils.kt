//package com.example.test_loadmore.service.notification
//
//import android.app.NotificationManager
//import android.app.PendingIntent
//import android.content.Context
//import android.content.Intent
//import android.os.Build
//import android.util.Log
//import android.widget.Toast
//import androidx.annotation.RequiresApi
//import androidx.core.app.NotificationCompat
//import com.example.test_loadmore.R
//
//
//fun createNotification(context: Context){
////    val emptyIntent = Intent()
////    var pendingIntent =
////        PendingIntent.getActivity(context, emptyIntent, PendingIntent.FLAG_UPDATE_CURRENT)
////
////    var mBuilder = NotificationCompat.Builder(this)
////        .setSmallIcon(R.drawable.notification_icon)
////        .setContentTitle("My notification")
////        .setContentText("Hello World!")
////        .setContentIntent(pendingIntent)
//}
//
//@RequiresApi(Build.VERSION_CODES.LOLLIPOP)
//fun showNotification() {
//    var mediaSessionCompat = createMediaSession()
//    notificationBuilder = NotificationCompat.Builder(this, "CHANNEL_ID")
//    Toast.makeText(this, "showNotification", Toast.LENGTH_SHORT).show()
//    val contentPendingIntent = PendingIntent.getActivity(
//        this,
//        0, Intent(this, UIMain::class.java), 0
//    )
//
//    //Intent for Previous
//    //Intent for Previous
//    val previousIntent = Intent(this, MyService::class.java)
//    previousIntent.action = PREVIOUS_ACTION
//    val previous_Intent = PendingIntent.getService(this, 0, previousIntent, 0)
//
//    //Intent for Next
//    //Intent for Previous
//    val nextIntent = Intent(this, MyService::class.java)
//    nextIntent.action = NEXT_ACTION
//    val next_Intent = PendingIntent.getService(this, 0, nextIntent, 0)
//
//    //Intent for Pause
//    //Intent for Pause
//    val play_pauseIntent = Intent(this, MyService::class.java)
//    play_pauseIntent.action = PAUSE_ACTION
//    val Play_PauseIntent = PendingIntent.getService(this, 0, play_pauseIntent, 0)
//
//    //Intent for Close
//    //Intent for Close
//    var stopIntent = Intent(this, MyService::class.java)
//    stopIntent.setAction(CLOSE_ACTION)
//    val closeIntent = PendingIntent.getService(this, 0, stopIntent, 0)
//
//    var mediaStyle = androidx.media.app.NotificationCompat.MediaStyle()
//        .setMediaSession(mediaSessionCompat.sessionToken)
//        .setShowActionsInCompactView(0)
//    notificationBuilder.setContentTitle("Song Title")
//        .setSmallIcon(R.mipmap.ic_launcher_round)
//        .setContentIntent(contentPendingIntent)
//        .setVisibility(NotificationCompat.VISIBILITY_PUBLIC)
//        .setOngoing(true)
//        .addAction(android.R.drawable.ic_media_previous, "PREVIOUS", previous_Intent)
//        .addAction(android.R.drawable.ic_media_play, "PLAY_PAUSE", Play_PauseIntent)
//        .addAction(android.R.drawable.ic_media_next, "NEXT", next_Intent)
//        .addAction(android.R.drawable.ic_menu_close_clear_cancel, "CLOSE", closeIntent)
//        .setPriority(NotificationCompat.PRIORITY_MAX)
//        .setStyle(mediaStyle)
//        .setVisibility(NotificationCompat.VISIBILITY_PUBLIC);
//    notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
//    notificationManager!!.notify(101, notificationBuilder.build())
//    startForeground(101, notificationBuilder.build());
//}
//
//lateinit var notificationManager: NotificationManager
//fun Service.updateNotifi(album: Album, play: Boolean) {
//    if(play){
//        notificationBuilder.setContentTitle(album.title)
//        notificationManager!!.notify(101, notificationBuilder.build())
//    }
//    else{
//
//    }
//
//}
//
//fun Notification(album: Album ,x: Int, restart_app: Boolean){
//    var mediaSessionCompat = createMediaSession()
//    notificationBuilder = NotificationCompat.Builder(this, "CHANNEL_ID")
//    Toast.makeText(this, "showNotification", Toast.LENGTH_SHORT).show()
//    val previousIntent = Intent(this, MyService::class.java)
//    previousIntent.action = PREVIOUS_ACTION
//    val previous_Intent = PendingIntent.getService(this, 0, previousIntent, 0)
//
//    //Intent for Next
//    //Intent for Previous
//    val nextIntent = Intent(this, MyService::class.java)
//    nextIntent.action = NEXT_ACTION
//    val next_Intent = PendingIntent.getService(this, 0, nextIntent, 0)
//
//    //Intent for Pause
//    //Intent for Pause
//    val play_pauseIntent = Intent(this, MyService::class.java)
//    play_pauseIntent.action = PLAY_PAUSE_ACTION
//    val Play_PauseIntent = PendingIntent.getService(this, 0, play_pauseIntent, 0)
//
//    //Intent for Close
//    //Intent for Close
//    var stopIntent = Intent(this, MyService::class.java)
//    stopIntent.setAction(CLOSE_ACTION)
//    val closeIntent = PendingIntent.getService(this, 0, stopIntent, 0)
//
//    var mediaStyle = androidx.media.app.NotificationCompat.MediaStyle()
//        .setMediaSession(mediaSessionCompat.sessionToken)
//        .setShowActionsInCompactView(0)
//    val contentPendingIntent: PendingIntent
//    if(restart_app){
//        contentPendingIntent = PendingIntent.getActivity(
//            this,
//            0, Intent(this, UIMain::class.java), 0
//        )
//        Log.e("notificationBuilder","contentPendingIntent")
//        notificationBuilder.setContentTitle(album.title)
//            .setSmallIcon(R.mipmap.ic_launcher_round)
//            .setContentIntent(contentPendingIntent)
//            .setPriority(Notification.PRIORITY_HIGH)
//            .setVisibility(NotificationCompat.VISIBILITY_PUBLIC)
//            .setOngoing(true)
//            .addAction(android.R.drawable.ic_media_previous, "PREVIOUS", previous_Intent)
//            .addAction(x, "PLAY_PAUSE", Play_PauseIntent)
//            .addAction(android.R.drawable.ic_media_next, "NEXT", next_Intent)
//            .addAction(android.R.drawable.ic_menu_close_clear_cancel, "CLOSE", closeIntent)
//            .setPriority(NotificationCompat.PRIORITY_MAX)
//            .setStyle(mediaStyle)
//            .setVisibility(NotificationCompat.VISIBILITY_PUBLIC);
//    }
//    else{
//        notificationBuilder.setContentTitle(album.title)
//            .setSmallIcon(R.mipmap.ic_launcher_round)
//            .setVisibility(NotificationCompat.VISIBILITY_PUBLIC)
//            .setOngoing(true)
//            .addAction(android.R.drawable.ic_media_previous, "PREVIOUS", previous_Intent)
//            .addAction(x, "PLAY_PAUSE", Play_PauseIntent)
//            .addAction(android.R.drawable.ic_media_next, "NEXT", next_Intent)
//            .setPriority(NotificationCompat.PRIORITY_MAX)
//            .setStyle(mediaStyle)
//            .setVisibility(NotificationCompat.VISIBILITY_PUBLIC);
//    }
//
//    //Intent for Previous
//    //Intent for Previous
//
//
//    notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
//    notificationManager!!.notify(101, notificationBuilder.build())
//    startForeground(101, notificationBuilder.build());
//}
//
//fun removeNotification() {
//    stopForeground(true)
//    notificationManager.cancel(101)
//}