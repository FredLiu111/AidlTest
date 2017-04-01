package com.example.ecarx.aidldemo.service;

import android.app.Service;
import android.content.Intent;
import android.nfc.Tag;
import android.os.IBinder;
import android.os.RemoteCallbackList;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.util.Log;

import com.example.ecarx.aidldemo.IMyAidlInterface;
import com.example.ecarx.aidldemo.IMyPlayerCallback;

import java.lang.reflect.Method;


/**
 * Created by liupeng on 2017/3/28.
 * 用来监听客户端的连接请求
 */

public class MyTestService extends Service {

    public MyTestService() {
    }

    private RemoteCallbackList<IMyPlayerCallback> mCallbacks = new RemoteCallbackList<>();

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return iBinder;
    }

    private IBinder iBinder = new IMyAidlInterface.Stub() {
        @Override
        public void play() throws RemoteException {
            Log.d("MyService", "正在播放");
            notifyClient("play");

        }

        @Override
        public void pause() throws RemoteException {
            Log.d("MyService", "暂停");
            notifyClient("pause");
        }

        @Override
        public void playPre() throws RemoteException {
            Log.d("MyService", "playPre");

        }

        @Override
        public void playNext() throws RemoteException {
            Log.d("MyService", "playNext");

        }

        @Override
        public void switchAlbums() throws RemoteException {
            Log.d("MyService", "switchAlbums");

        }

        @Override
        public void lookAllSings() throws RemoteException {
            Log.d("MyService", "lookAllSings");

        }

        @Override
        public void switchPlayMode() throws RemoteException {
            Log.d("MyService", "switchPlayMode");

        }


        @Override
        public int cal(int num1, int num2) throws RemoteException {
            Log.d("MyService", "cal函数执行了");
            return num1 + num2;

        }

        @Override
        public void registerPlayCallback(IMyPlayerCallback playCallback) throws RemoteException {
            if (playCallback != null) {
                mCallbacks.register(playCallback);
            }
        }

        @Override
        public void registerPauseCallback(IMyPlayerCallback pauseCallback) throws RemoteException {
            if (pauseCallback != null) {
                mCallbacks.register(pauseCallback);
            }
        }


        @Override
        public void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat, double aDouble, String aString) throws RemoteException {
            Log.d("MyService", "basicTypes");

        }

    };

    public void notifyClient(String tag) {
        final int len = mCallbacks.beginBroadcast();
        for (int i = 0; i < len; i++) {
            try {
                if (tag.equals("play")){
                    // 通知回调函数，这里只通知了播放的回调函数 playListener
                    mCallbacks.getBroadcastItem(i).playListener();
                    Log.d("mCallbacks","通知回调函数");
                }
                if (tag.equals("pause")){
                    // 通知回调函数，这里只通知了播放的回调函数 playListener
                    mCallbacks.getBroadcastItem(i).pauseListener();
                    Log.d("mCallbacks","通知回调函数");
                }
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
        mCallbacks.finishBroadcast();
    }

}
