package com.example.czy.petrolconsume.activity;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.czy.petrolconsume.adapter.AddCarSpinnerAdapter;
import com.example.czy.petrolconsume.R;
import com.example.czy.petrolconsume.base.BaseActivity;
import com.example.czy.petrolconsume.bean.BrandBean;
import com.example.czy.petrolconsume.bean.CarBean;
import com.example.czy.petrolconsume.bean.CarDetailBean;
import com.example.czy.petrolconsume.database.DBToolSingleton;
import com.example.czy.petrolconsume.net.HttpManager;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by CZY on 17/4/22.
 */

public class AddCarActivity extends BaseActivity {

    private Spinner brandsSpinner, seriesSpinner, typeSpinner;
    private int index, type, series;
    private TextView leftTv, rightTv;
    private RelativeLayout relativeLayout;
    private EditText editText;
    private ImageView saveIv;
    private CarBean carBean;
    private List<CarBean> carBeanList;
    private String name1;
    private String name2;
    private String name3;
    private AddCarSpinnerAdapter addCarSpinnerAdapter;

    @Override
    protected int bindLayout() {
        return R.layout.activity_add_car;
    }

    @Override
    protected void initView() {
        brandsSpinner = bindView(R.id.brand_spinner);
        seriesSpinner = bindView(R.id.series_spinner);
        typeSpinner = bindView(R.id.type_spinner);
        leftTv = bindView(R.id.text_add_left);
        rightTv = bindView(R.id.text_add_right);
        relativeLayout = bindView(R.id.view_add);
        editText = bindView(R.id.name_et);
        saveIv = bindView(R.id.save_iv);
    }

    @Override
    protected void initData() {
        addCarSpinnerAdapter = new AddCarSpinnerAdapter(AddCarActivity.this);
        brandsSpinner.setAdapter(addCarSpinnerAdapter);

        relativeLayout.setVisibility(View.GONE);

        carBean = new CarBean();
        carBeanList = new ArrayList<>();

        saveIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddCarActivity.this, MainActivity.class);
                intent.putExtra("name", editText.getText().toString());
                intent.putExtra("brand", name1);
                intent.putExtra("series", name2);
                intent.putExtra("type", name3);
                startActivity(intent);
                Log.d("111", "editText:" + editText.getText().toString());
                Log.d("111", name1 + name2 + name3);
                carBeanList = new ArrayList<>();
                carBean.setName(editText.getText().toString());
                carBeanList.add(carBean);
                DBToolSingleton.getInstance().addCar(carBean);
                Toast.makeText(AddCarActivity.this, "已保存" + editText.getText().toString(), Toast.LENGTH_SHORT).show();
            }
        });

        brands();
        brandsSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, final int position, final long id) {
                index = (int) id;
                series();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        seriesSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, final int position, long id) {
//                HttpManager.getInstance().getBearApi()
//                        .getCarSeries(index)
//                        .subscribeOn(Schedulers.io())
//                        .observeOn(AndroidSchedulers.mainThread())
//                        .subscribe(new Consumer<BrandBean>() {
//                            @Override
//                            public void accept(@NonNull BrandBean brandBean) throws Exception {
//                                series = brandBean.getIdxes().get(position);
//                                name2 = brandBean.getNames().get(position);
//                                Log.d("111", name2);
//                                type();
//                            }
//                        });
                series = (int) id;
                type();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        typeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, final int position, long id) {
//                HttpManager.getInstance().getBearApi()
//                        .getCarType(index, series)
//                        .subscribeOn(Schedulers.io())
//                        .observeOn(AndroidSchedulers.mainThread())
//                        .subscribe(new Consumer<BrandBean>() {
//                            @Override
//                            public void accept(@NonNull BrandBean brandBean) throws Exception {
//                                type = brandBean.getIdxes().get(position);
//                                name3 = brandBean.getNames().get(position);
//                                Log.d("111", name3);
//                                detail();
//                            }
//                        });
                type = (int) id;
                detail();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }


    public void brands() {
        HttpManager.getInstance().getBearApi()
                .getBrands()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<BrandBean>() {
                    @Override
                    public void accept(@NonNull BrandBean brandBean) throws Exception {
                        addCarSpinnerAdapter.setData(brandBean);
                    }
                });
    }

    public void series() {
        HttpManager.getInstance().getBearApi()
                .getCarSeries(index)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<BrandBean>() {
                    @Override
                    public void accept(@NonNull BrandBean brandBean) throws Exception {
                        AddCarSpinnerAdapter addCarSpinnerAdapter = new AddCarSpinnerAdapter(AddCarActivity.this);
                        addCarSpinnerAdapter.setData(brandBean);
                        seriesSpinner.setAdapter(addCarSpinnerAdapter);
                    }
                });
    }

    public void type() {
        HttpManager.getInstance().getBearApi()
                .getCarType(index, series)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<BrandBean>() {
                    @Override
                    public void accept(@NonNull BrandBean brandBean) throws Exception {
                        AddCarSpinnerAdapter addCarSpinnerAdapter = new AddCarSpinnerAdapter(AddCarActivity.this);
                        addCarSpinnerAdapter.setData(brandBean);
                        typeSpinner.setAdapter(addCarSpinnerAdapter);
                    }
                });
    }

    public void detail() {
        HttpManager.getInstance().getBearApi()
                .getCarDetail(type)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<CarDetailBean>() {
                    @Override
                    public void accept(@NonNull CarDetailBean carDetailBean) throws Exception {
                        if (carDetailBean != null) {
                            relativeLayout.setVisibility(View.VISIBLE);
                            leftTv.setText(carDetailBean.getEngine());
                            rightTv.setText(carDetailBean.getGearbox());
                        }
                    }
                });
    }
}
