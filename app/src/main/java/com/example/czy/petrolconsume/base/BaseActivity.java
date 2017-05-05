package com.example.czy.petrolconsume.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * Created by CZY on 2017/3/25.
 */

public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(bindLayout());

        initView();

        initData();

    }

    /**
     * 绑定布局
     *
     * @return
     */
    protected abstract int bindLayout();

    /**
     * 初始化组件
     */
    protected abstract void initView();

    /**
     * 初始化数据
     */
    protected abstract void initData();

    /**
     * 绑定组件
     *
     * @param id  组件id
     * @param <T> 强制转换的类型
     * @return
     */
    protected <T extends View> T bindView(int id) {
        return (T) findViewById(id);
    }

}
