package com.example.czy.petrolconsume.fragment;

import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.czy.petrolconsume.R;
import com.example.czy.petrolconsume.base.BaseFragment;
import com.example.czy.petrolconsume.bean.RecordBean;
import com.example.czy.petrolconsume.database.RxSQLite;
import com.example.czy.petrolconsume.customview.LinearChartView;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by CZY on 2017/4/18.
 */

public class OilConsumeFragment extends BaseFragment {

    private LinearLayout linearLayout;
    private int count = 0;
    private TextView textTop, oilFirstTv;
    private ImageView pointIv, pointIv1, pointIv2, pointIv3, pointIv4, leftIv, rightIv;
    private LinearChartView customView;
    private float[] consume;
    private int[] odometer;
    private float[] consumeA;
    private float[] price;
    private long[] dates;
    private float[] oilConsumption = new float[]{2, 10, 17, 11, 5, 8, 15, 20, 14, 8, 19, 4, 9, 12, 8, 5, 18, 8, 12, 5, 10, 6, 4, 15, 8, 9, 27, 6, 22, 15, 4, 14, 28, 5, 7};
    private float[] yearConsumption = new float[]{5, 11, 3, 9, 12, 25, 7, 17, 22, 8, 13, 27};
    private float[] halfConsumption = new float[]{6, 11, 25, 9, 5, 13};
    private float[] threeConsumption = new float[]{4, 28, 19};

    public static String getYearToString(long time) {
        Date d = new Date(time);
        SimpleDateFormat sf = new SimpleDateFormat("yyyy");
        return sf.format(d);
    }

    public static String getMonthToString(long time) {
        Date d = new Date(time);
        SimpleDateFormat sf = new SimpleDateFormat("MM");
        return sf.format(d);
    }

    @Override
    protected int bindLayout() {
        return R.layout.fragment_petrol_consume;
    }

    @Override
    protected void initView() {
        linearLayout = bindView(R.id.point_linear);
        textTop = bindView(R.id.text_top);
        leftIv = bindView(R.id.left_oil_first);
        rightIv = bindView(R.id.right_oil_first);
        oilFirstTv = bindView(R.id.oil_first_tv);
        customView = bindView(R.id.custom_view);
    }

    @Override
    protected void initData() {
        selectedRecords();
        customView.setCOUNT_X_MARK(35);
        customView.setConsume(oilConsumption);

        for (int i = 0; i < 5; i++) {
            pointIv = new ImageView(getContext());
            pointIv.setPadding(0, 0, 0, 0);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(80, 80);
            pointIv.setLayoutParams(params);
            if (i == 0) {
                pointIv.setImageResource(R.mipmap.point_white);
            } else {
                pointIv.setImageResource(R.mipmap.point_grey);
            }
            linearLayout.addView(pointIv);
        }
        rightIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (count) {
                    case 0:
                        one();
                        break;
                    case 1:
                        two();
                        break;
                    case 2:
                        three();
                        break;
                    case 3:
                        four();
                        break;
                    case 4:
                        zero();
                        break;
                }
            }
        });
        leftIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (count) {
                    case 0:
                        four();
                        break;
                    case 4:
                        three();
                        break;
                    case 3:
                        two();
                        break;
                    case 2:
                        one();
                        break;
                    case 1:
                        zero();
                        break;
                }
            }
        });
    }

    public void selectedRecords() {
        RxSQLite.querySelectedRecord()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<RecordBean>>() {
                    @Override
                    public void accept(@NonNull List<RecordBean> recordsEntities) throws Exception {
                        dates = new long[recordsEntities.size()];
                        for (int i = 0; i < recordsEntities.size(); i++) {
                            dates[i] = recordsEntities.get(i).getDate();
                            getYearToString(dates[i]);
                            Log.d("111", getMonthToString(dates[i]));
                        }
                        consume = new float[recordsEntities.size()];
                        odometer = new int[recordsEntities.size()];
                        consumeA = new float[recordsEntities.size()];
                        price = new float[recordsEntities.size()];
                        for (int i = 0; i < recordsEntities.size(); i++) {
                            price[i] = (float) recordsEntities.get(i).getPrice();
                            consume[i] = (float) recordsEntities.get(i).getYuan();
                            odometer[i] = recordsEntities.get(i).getOdometer();
                        }
                    }
                });
    }

    public void one() {
        customView.setCOUNT_X_MARK(12);
        customView.setConsume(yearConsumption);
        count = 1;
        textTop.setText("最近一年油耗统计曲线");
        linearLayout.removeAllViewsInLayout();
        for (int i = 0; i < 5; i++) {
            pointIv1 = new ImageView(getContext());
            pointIv1.setPadding(0, 0, 0, 0);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(80, 80);
            pointIv1.setLayoutParams(params);
            if (i == 1) {
                pointIv1.setImageResource(R.mipmap.point_white);
            } else {
                pointIv1.setImageResource(R.mipmap.point_grey);
            }
            linearLayout.addView(pointIv1);
        }
    }

    public void two() {
        customView.setCOUNT_X_MARK(6);
        customView.setConsume(halfConsumption);
        count = 2;
        textTop.setText("最近半年油耗统计曲线");
        linearLayout.removeAllViewsInLayout();
        for (int i = 0; i < 5; i++) {
            pointIv2 = new ImageView(getContext());
            pointIv2.setPadding(0, 0, 0, 0);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(80, 80);
            pointIv2.setLayoutParams(params);
            if (i == 2) {
                pointIv2.setImageResource(R.mipmap.point_white);
            } else {
                pointIv2.setImageResource(R.mipmap.point_grey);
            }
            linearLayout.addView(pointIv2);
        }
    }

    private void three() {
        customView.setCOUNT_X_MARK(3);
        customView.setConsume(threeConsumption);
        count = 3;
        textTop.setText("最近三个月油耗统计曲线");
        linearLayout.removeAllViewsInLayout();
        for (int i = 0; i < 5; i++) {
            pointIv3 = new ImageView(getContext());
            pointIv3.setPadding(0, 0, 0, 0);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(80, 80);
            pointIv3.setLayoutParams(params);
            if (i == 3) {
                pointIv3.setImageResource(R.mipmap.point_white);
            } else {
                pointIv3.setImageResource(R.mipmap.point_grey);
            }
            linearLayout.addView(pointIv3);
        }
    }

    private void four() {
        customView.setCOUNT_X_MARK(12);
        customView.setConsume(yearConsumption);
        count = 4;
        textTop.setText("同城车型油耗基准线");
        linearLayout.removeAllViewsInLayout();
        for (int i = 0; i < 5; i++) {
            pointIv4 = new ImageView(getContext());
            pointIv4.setPadding(0, 0, 0, 0);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(80, 80);
            pointIv4.setLayoutParams(params);
            if (i == 4) {
                pointIv4.setImageResource(R.mipmap.point_white);
            } else {
                pointIv4.setImageResource(R.mipmap.point_grey);
            }
            linearLayout.addView(pointIv4);
        }
    }

    private void zero() {
        customView.setCOUNT_X_MARK(35);
        customView.setConsume(oilConsumption);
        count = 0;
        textTop.setText("     油耗统计曲线");
        linearLayout.removeAllViewsInLayout();
        for (int i = 0; i < 5; i++) {
            pointIv = new ImageView(getContext());
            pointIv.setPadding(0, 0, 0, 0);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(80, 80);
            pointIv.setLayoutParams(params);
            if (i == 0) {
                pointIv.setImageResource(R.mipmap.point_white);
            } else {
                pointIv.setImageResource(R.mipmap.point_grey);
            }
            linearLayout.addView(pointIv);
        }
    }
}
