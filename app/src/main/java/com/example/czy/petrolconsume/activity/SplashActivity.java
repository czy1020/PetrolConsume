package com.example.czy.petrolconsume.activity;

import android.content.Intent;

import com.example.czy.petrolconsume.R;
import com.example.czy.petrolconsume.base.BaseActivity;

public class SplashActivity extends BaseActivity {


    @Override
    protected int bindLayout() {
        return R.layout.activity_splash;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

        //启动页
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(intent);

            }
        }).start();

    }
}
