package com.wangpengfei.servicedemo

import android.content.ComponentName
import android.content.Intent
import android.content.ServiceConnection
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.IBinder
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    private var tv_start:TextView?=null
    private var tv_stop:TextView?=null

    private var tv_bind_service:TextView?=null
    private var tv_play:TextView?=null
    private var tv_unbind_service:TextView?=null

    private var myplayBinder:MyService.MyPlayBinder?=null

    private var connection = object:ServiceConnection{

        override fun onServiceDisconnected(name: ComponentName?) {

        }

        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            myplayBinder = service as MyService.MyPlayBinder
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        tv_stop = findViewById(R.id.tv_stop)
        tv_start = findViewById(R.id.tv_start)

        tv_bind_service = findViewById(R.id.tv_bind_service)
        tv_play = findViewById(R.id.tv_play)
        tv_unbind_service = findViewById(R.id.tv_unbind_service)

        tv_start?.setOnClickListener {
            //开启服务
            startMyService()
        }

        tv_stop?.setOnClickListener {
            //停止服务
            stopMyService()
        }

        tv_bind_service?.setOnClickListener {
            //绑定服务
            bindMyService()
        }
        tv_play?.setOnClickListener {
            //来首歌
            play()
        }
        tv_unbind_service?.setOnClickListener {
            //解绑服务
            unbindMyService()
        }
    }

    /***
     * 开启服务
     */
    private fun startMyService(){
        var intent = Intent(this,MyService::class.java)
        startService(intent)

    }

    /***
     * 关闭服务
     */
    private fun stopMyService(){
        var intent = Intent(this,MyService::class.java)
        stopService(intent)
    }

    /***
     * 绑定服务
     */
    private fun bindMyService(){
        var intent  = Intent(this,MyService::class.java)
        bindService(intent,connection,BIND_AUTO_CREATE)
    }

    /**
     * 播放音乐
     */
    private fun play(){
        if(myplayBinder!=null)
        {
            if(tv_play?.text.toString() == "来首歌"){
                myplayBinder?.play()
                tv_play?.text = "播放中"
            }else{
                myplayBinder?.stop()
                tv_play?.text = "来首歌"
            }

        }
    }

    /***
     * 解绑服务
     */
    private fun unbindMyService(){
        unbindService(connection)
    }

}
