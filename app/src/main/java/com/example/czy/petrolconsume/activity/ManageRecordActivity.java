package com.example.czy.petrolconsume.activity;

import android.view.View;
import android.widget.ImageView;

import com.example.czy.petrolconsume.R;
import com.example.czy.petrolconsume.base.BaseActivity;

/**
 * Created by CZY on 2017/4/28.
 */

public class ManageRecordActivity extends BaseActivity {

    private ImageView backIv;

    @Override
    protected int bindLayout() {
        return R.layout.activity_manage_record;
    }

    @Override
    protected void initView() {
        backIv = bindView(R.id.back_iv);
    }

    @Override
    protected void initData() {

        backIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
}
