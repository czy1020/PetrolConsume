package com.example.czy.petrolconsume.activity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.icu.util.Calendar;
import android.view.View;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TimePicker;

import com.example.czy.petrolconsume.R;
import com.example.czy.petrolconsume.base.BaseActivity;

/**
 * Created by CZY on 2017/4/20.
 */

public class AddOilRecordActivity extends BaseActivity {

    private LinearLayout mBackLl;
    private TextView mDateTv, mTimeTv;

    @Override
    protected int bindLayout() {
        return R.layout.activity_add_record;
    }

    @Override
    protected void initView() {
        mBackLl = (LinearLayout) findViewById(R.id.back_ll);
        mDateTv = (TextView) findViewById(R.id.date_tv);
        mTimeTv = (TextView) findViewById(R.id.time_tv);
    }

    @Override
    protected void initData() {
        mBackLl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        mDateTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                    Calendar calendar = Calendar.getInstance();

                    new DatePickerDialog(AddOilRecordActivity.this, new DatePickerDialog.OnDateSetListener() {
                        @Override
                        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                            mDateTv.setText(year + "年" + month + "月" + dayOfMonth + "日");
                        }
                    }, calendar.get(Calendar.YEAR)
                            , calendar.get(Calendar.MONTH)
                            , calendar.get(Calendar.DAY_OF_MONTH)).show();
                }
            }
        });

        mTimeTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                    Calendar calendar = Calendar.getInstance();

                    new TimePickerDialog(AddOilRecordActivity.this, new TimePickerDialog.OnTimeSetListener() {
                        @Override
                        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                            mTimeTv.setText(hourOfDay + "时" + minute + "分");
                        }
                    }, calendar.get(Calendar.HOUR_OF_DAY)
                            , calendar.get(Calendar.MINUTE)
                            , true).show();
                }
            }
        });

    }
}