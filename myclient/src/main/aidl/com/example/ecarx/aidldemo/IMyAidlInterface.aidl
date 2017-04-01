// IMyAidlInterface.aidl
package com.example.ecarx.aidldemo;
import com.example.ecarx.aidldemo.IMyPlayerCallback;
// Declare any non-default types here with import statements
interface IMyAidlInterface {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
    void play();
    void pause();
    void playPre();
    void playNext();
    void switchAlbums();
    void lookAllSings();
    void switchPlayMode();
    int cal(int num1,int num2);

    void playerPlayCallback(IMyPlayerCallback playCallback);
    void playerPauseCallback(IMyPlayerCallback pauseCallback);

    void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat,
            double aDouble, String aString);
}
