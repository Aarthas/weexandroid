package com.weex.sample.extend.module;

import android.util.Log;

import com.taobao.weex.annotation.JSMethod;
import com.taobao.weex.common.WXModule;

public class LogModule extends WXModule {
    public LogModule() {
    }

    @JSMethod(uiThread = false)
    public void log(String element) {
        Log.d("aa",element);
    }
}