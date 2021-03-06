package com.weex.sample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.taobao.weex.IWXRenderListener;
import com.taobao.weex.WXSDKEngine;
import com.taobao.weex.WXSDKInstance;
import com.taobao.weex.common.Constants;
import com.taobao.weex.common.WXException;
import com.taobao.weex.common.WXRenderStrategy;
import com.weex.sample.extend.module.LogModule;
import com.weex.sample.extend.module.MyModule;
import com.weex.sample.extend.module.PhoneInfoModule;

import java.util.HashMap;
import java.util.Map;

public class NetworkActivity extends AppCompatActivity implements IWXRenderListener {

    private static String TEST_URL = "http://193.0.1.157:8080/dist/weex/views/404/app.js";
    private WXSDKInstance mWXSDKInstance;
    private FrameLayout mContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_network);

        try {
            WXSDKEngine.registerModule("poneInfo", PhoneInfoModule.class);
            WXSDKEngine.registerModule("myModule", MyModule.class);
            WXSDKEngine.registerModule("LogModule", LogModule.class);
        }catch (WXException e)
        {

        }

        findViewById(R.id.aaaa).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("aaaa");
//                WXBridgeManager.getInstance().fireEvent();
                mWXSDKInstance.fireEvent( mWXSDKInstance.getRootComponent().getRef(), Constants.Event.APPEAR,new HashMap<String, Object>());
                Map<String,Object> params=new HashMap<>();
                params.put("key","value");
                mWXSDKInstance.fireGlobalEventCallback("geolocation",params);
//                WXBridgeManager.getInstance().fireEvent(this.mInstanceId, comp.getRef(), Constants.Event.VIEWAPPEAR,null, null);
            }
        });
        mContainer = (FrameLayout) findViewById(R.id.container);

        mWXSDKInstance = new WXSDKInstance(this);
        mWXSDKInstance.registerRenderListener(this);
        /**
         * pageName:自定义，一个标示符号。
         * url:远程bundle JS的下载地址
         * options:初始化时传入WEEX的参数，比如 bundle JS地址
         * flag:渲染策略。WXRenderStrategy.APPEND_ASYNC:异步策略先返回外层View，其他View渲染完成后调用onRenderSuccess。WXRenderStrategy.APPEND_ONCE 所有控件渲染完后后一次性返回。
         */
//    Map<String, Object> options = new HashMap<>();
//    options.put(WXSDKInstance.BUNDLE_URL, TEST_URL);
        mWXSDKInstance.renderByUrl("WXSample", TEST_URL, null, null, WXRenderStrategy.APPEND_ONCE);


    }




    @Override
    protected void onStart() {
        super.onStart();
        if (mWXSDKInstance != null) {
            mWXSDKInstance.onActivityStart();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mWXSDKInstance != null) {
            mWXSDKInstance.onActivityResume();

        }

    }

    @Override
    protected void onPause() {
        super.onPause();
        if (mWXSDKInstance != null) {
            mWXSDKInstance.onActivityPause();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mWXSDKInstance != null) {
            mWXSDKInstance.onActivityStop();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mWXSDKInstance != null) {
            mWXSDKInstance.onActivityDestroy();
        }
    }

    @Override
    public void onViewCreated(WXSDKInstance instance, View view) {
        if (view.getParent() != null) {
            ((ViewGroup) view.getParent()).removeView(view);
        }
        mContainer.addView(view);
    }

    @Override
    public void onRenderSuccess(WXSDKInstance instance, int width, int height) {

    }

    @Override
    public void onRefreshSuccess(WXSDKInstance instance, int width, int height) {

    }

    @Override
    public void onException(WXSDKInstance instance, String errCode, String msg) {

    }
}
