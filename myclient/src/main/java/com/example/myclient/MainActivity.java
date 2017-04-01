package com.example.myclient;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.ecarx.aidldemo.IMyAidlInterface;
import com.example.ecarx.aidldemo.IMyPlayerCallback;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btn_look_all_sings;
    private Button btn_play;
    private Button btn_pause;
    private Button btn_play_next;
    private Button btn_play_pre;
    private Button btn_switch_albums;
    private Button btn_switch_play_mode;
    private IMyAidlInterface iMyAidlInterface;

    private ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder service) {
            iMyAidlInterface = IMyAidlInterface.Stub.asInterface(service);
            Log.d("onServiceConnected", "onServiceConnected: "+"成功");
            try {
                //在服务连接成功时候，调用！！！没有这句话回调函数不会执行啊 55555555555！
                iMyAidlInterface.playerPlayCallback(iMyPlayerCallback);
            } catch (RemoteException e) {
                e.printStackTrace();
            }

        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            iMyAidlInterface = null;
            Log.d("onServiceConnected", "onServiceConnected: "+"失败");

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bindMyService();
        initView();
        initListener();
    }

    private void initListener() {
        btn_look_all_sings.setOnClickListener(this);
        btn_play.setOnClickListener(this);
        btn_pause.setOnClickListener(this);
        btn_play_next.setOnClickListener(this);
        btn_play_pre.setOnClickListener(this);
        btn_switch_albums.setOnClickListener(this);
        btn_switch_play_mode.setOnClickListener(this);
    }

    private void initView() {
        btn_look_all_sings = (Button) findViewById(R.id.btn_look_all_sings);
        btn_play = (Button) findViewById(R.id.btn_play);
        btn_pause = (Button) findViewById(R.id.btn_pause);
        btn_play_next = (Button) findViewById(R.id.btn_play_next);
        btn_play_pre = (Button) findViewById(R.id.btn_play_pre);
        btn_switch_albums = (Button) findViewById(R.id.btn_switch_albums);
        btn_switch_play_mode = (Button) findViewById(R.id.btn_switch_play_mode);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_play:
                try {
                    iMyAidlInterface.play();
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
                break;
            case R.id.btn_pause:
                try {
                    iMyAidlInterface.pause();
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
                break;
            case R.id.btn_play_next:

                break;
            case R.id.btn_play_pre:

                break;
            case R.id.btn_look_all_sings:

                break;
            case R.id.btn_switch_albums:

                break;
            case R.id.btn_switch_play_mode:
                try {
                    int result = iMyAidlInterface.cal(1,2);
                    Log.d("calResult", "onClick: "+result);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
                break;
        }
    }

    private void bindMyService() {
        Intent intent = new Intent();
        intent.setAction("com.example.ecarx.aidldemo.MyTestService");
        intent.setComponent(new ComponentName("com.example.ecarx.aidldemo","com.example.ecarx.aidldemo.service.MyTestService"));
        bindService(intent,serviceConnection,BIND_AUTO_CREATE);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbindService(serviceConnection);
    }
    IMyPlayerCallback iMyPlayerCallback = new IMyPlayerCallback.Stub() {

        @Override
        public void playListener() throws RemoteException {
            Log.d("CallbackPlay", "播放的回调执行了");

        }

        @Override
        public void pauseListener() throws RemoteException {
            Log.d("CallbackPause", "暂停的回调执行了");

        }

        @Override
        public void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat, double aDouble, String aString) throws RemoteException {

        }
    };
}
