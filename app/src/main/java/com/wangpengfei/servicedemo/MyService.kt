package com.wangpengfei.servicedemo

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder
import android.util.Log

/**
 * Created by WPF on 2019/11/2 14:56
 * 邮箱：527821836@qq.com
 * decs：
 *
 *
 */
class MyService : Service() {

    private var TAG:String = "MyService"
    private var playBinder = MyPlayBinder()

    override fun onBind(intent: Intent?): IBinder? {
        Log.d(TAG,"-----service onBind")
        return playBinder
    }

    override fun onCreate() {
        super.onCreate()
        Log.d(TAG,"-----service onCreate")
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.d(TAG,"-----service onStartCommand")
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onUnbind(intent: Intent?): Boolean {
        Log.d(TAG,"-----service onUnbind")
        return super.onUnbind(intent)
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG,"-----service onDestroy")
    }


    inner class MyPlayBinder : Binder(){

        fun play(){
            Log.d(TAG,"-----MyPlayBinder playing")
        }

        fun stop(){
            Log.d(TAG,"-----MyPlayBinder stoped")
        }

    }



}