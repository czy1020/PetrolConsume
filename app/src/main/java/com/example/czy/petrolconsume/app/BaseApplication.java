package com.example.czy.petrolconsume.app;

import android.app.Application;
import android.content.Context;

import cn.sharesdk.framework.ShareSDK;

/**
 * Created by CZY on 2017/4/13.
 */

public class BaseApplication extends Application {

    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
        ShareSDK.initSDK(this);
    }

    public static Context getContext() {
        return context;
    }
}
