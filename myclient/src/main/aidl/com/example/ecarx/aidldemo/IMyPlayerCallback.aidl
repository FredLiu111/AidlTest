// IMyPlayerCallback.aidl
package com.example.ecarx.aidldemo;

// Declare any non-default types here with import statements

interface IMyPlayerCallback {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
    void playListener();
    void pauseListener();

    void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat,
            double aDouble, String aString);
}
