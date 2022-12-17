package com.shanu.stockapp.networking;

import android.content.Context;

import com.chaquo.python.Python;
import com.chaquo.python.android.AndroidPlatform;

public class PythonClient {
    public static void initPython(Context context) {
        if (! Python.isStarted()) {
            Python.start(new AndroidPlatform(context));
        }
    }
}
