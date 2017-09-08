package com.weex.sample.extend.module;

import android.widget.Toast;

import com.taobao.weex.annotation.JSMethod;
import com.taobao.weex.bridge.JSCallback;
import com.taobao.weex.common.WXModule;

import java.util.HashMap;
import java.util.Map;

public class MyModule extends WXModule {
    public MyModule() {
    }

    @JSMethod(uiThread = false)
    public void printLog(String msg,JSCallback callback) {

//        mWXSDKInstance.getContext().startActivity(new Intent(mWXSDKInstance.getContext(), WXFragmentActivity.class));
        Toast.makeText(mWXSDKInstance.getContext(), msg, Toast.LENGTH_SHORT).show();
        Map<String,String> data=new HashMap<>();
        data.put("x","x");
        data.put("y","y");
        //通知一次
        callback.invoke(data);
    }
}