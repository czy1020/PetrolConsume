package com.example.czy.petrolconsume.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.czy.petrolconsume.fragment.RankFragment;
import com.example.czy.petrolconsume.tool.OnViewClickListener;
import com.example.czy.petrolconsume.R;
import com.example.czy.petrolconsume.adapter.ListAdapter;
import com.example.czy.petrolconsume.adapter.MainViewPagerAdapter;
import com.example.czy.petrolconsume.adapter.SpinnerAdapter;
import com.example.czy.petrolconsume.bean.CarBean;
import com.example.czy.petrolconsume.database.DBToolSingleton;
import com.example.czy.petrolconsume.database.RxSQLite;
import com.example.czy.petrolconsume.fragment.OilConsumeFragment;
import com.example.czy.petrolconsume.tool.SlidingMenu;

import java.util.ArrayList;
import java.util.List;

import cn.sharesdk.onekeyshare.OnekeyShare;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TabLayout mainTl;
    private ViewPager mainVp;
    private MainViewPagerAdapter adapter;
    private ImageView addRecordIv, lookRecordIv, openMallIv, shareIv;
    private Toolbar mainTb;
    private Spinner spinner;
    private CarBean myCar;
    private List<CarBean> cars;
    private int newPosition;
    private ListAdapter listAdapter;
    private ListView listView;
    private SpinnerAdapter spinnerAdapter;
    private SlidingMenu slidingMenu;
    private TextView toolbarTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainTb = (Toolbar) findViewById(R.id.main_tb);
        setSupportActionBar(mainTb);

        spinner = (Spinner) findViewById(R.id.spinner);

        mainTl = (TabLayout) findViewById(R.id.main_tl);
        mainVp = (ViewPager) findViewById(R.id.main_vp);

        slidingMenu = (SlidingMenu) findViewById(R.id.slidingMenu);

        addRecordIv = (ImageView) findViewById(R.id.add_record_iv);
        lookRecordIv = (ImageView) findViewById(R.id.manage_record_iv);
        openMallIv = (ImageView) findViewById(R.id.open_mall_iv);
        shareIv = (ImageView) findViewById(R.id.share_record_iv);
        toolbarTv = (TextView) findViewById(R.id.toolbar_tv);


        addRecordIv.setOnClickListener(this);
        lookRecordIv.setOnClickListener(this);
        openMallIv.setOnClickListener(this);
        shareIv.setOnClickListener(this);
        toolbarTv.setOnClickListener(this);

        myCar = new CarBean();
        cars = new ArrayList<>();

        queryCar();

        listAdapter = new ListAdapter(MainActivity.this);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if (position == spinner.getCount() - 1) {

                    final AlertDialog dialog = new AlertDialog.Builder(MainActivity.this).create();
                    view = LayoutInflater.from(MainActivity.this).inflate(R.layout.dialog_view, null);
                    dialog.setView(view);

                    listView = (ListView) view.findViewById(R.id.list_item_dialog);
                    RxSQLite.queryCars().map(new Function<List<CarBean>, List<CarBean>>() {
                        @Override
                        public List<CarBean> apply(@NonNull List<CarBean> carBeen) throws Exception {
                            return DBToolSingleton.getInstance().queryCars();
                        }
                    }).subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(new Consumer<List<CarBean>>() {
                                @Override
                                public void accept(@NonNull List<CarBean> carBeen) throws Exception {
                                    listAdapter.setCars(carBeen);
                                    listView.setAdapter(listAdapter);
                                    listAdapter.setOnViewClickListener(new OnViewClickListener() {
                                        @Override
                                        public void click(View view, int position) {
                                            newPosition = position;
                                            customDialog();
                                        }
                                    });
                                }
                            });


                    //添加按钮
                    ImageView addIv = (ImageView) view.findViewById(R.id.add_iv);
                    addIv.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
//                            myCar.setName("独轮车");
//                            cars = new ArrayList<>();
//                            cars.add(myCar);
//                            DBToolSingleton.getInstance().addCar(myCar);
//                            Toast.makeText(MainActivity.this, "成功", Toast.LENGTH_SHORT).show();
                            query();
                            Intent intent = new Intent(MainActivity.this, AddCarActivity.class);
                            startActivity(intent);
                        }
                    });
                    TextView backTv = (TextView) view.findViewById(R.id.back_tv);
                    backTv.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            dialog.dismiss();
                        }
                    });
                    dialog.show();
                    spinner.setSelection(spinnerAdapter.getCurrentPos());
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        adapter = new MainViewPagerAdapter(getSupportFragmentManager(), this);

        List<Fragment> fragments = new ArrayList<>();
        fragments.add(new OilConsumeFragment());
        fragments.add(new RankFragment());
        fragments.add(new OilConsumeFragment());
        fragments.add(new OilConsumeFragment());

        adapter.setFragments(fragments);
        String[] titles = {"油耗", "排名", "油费", "费用"};
        adapter.setTitles(titles);
        int[] imgs = {R.mipmap.chart_line, R.mipmap.chart_crown, R.mipmap.chart_bar, R.mipmap.chart_coin};
        adapter.setImgs(imgs);
        mainVp.setAdapter(adapter);
        mainTl.setSelectedTabIndicatorColor(ContextCompat.getColor(this, R.color.colorTabLine));
        mainTl.setSelectedTabIndicatorHeight(20);
        mainTl.setupWithViewPager(mainVp);

        for (int i = 0; i < adapter.getCount(); i++) {
            View view = adapter.getTabView(i);
            TabLayout.Tab tab = mainTl.getTabAt(i);
            tab.setCustomView(view);
        }
    }

    public void toggleMenu(View view){
        slidingMenu.toggle();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.add_record_iv:
                Intent intent = new Intent(MainActivity.this, AddOilRecordActivity.class);
                startActivity(intent);
                break;
            case R.id.manage_record_iv:
                Intent intent1 = new Intent(MainActivity.this, ManageRecordActivity.class);
                startActivity(intent1);
                break;
            case R.id.open_mall_iv:

                break;
            case R.id.share_record_iv:
                showShare();
                break;
            case R.id.toolbar_tv:
                toggleMenu(v);
                break;
        }
    }

    /**
     * Spinner上显示的数据
     */
    private void queryCar() {
        RxSQLite.queryCars().map(new Function<List<CarBean>, List<CarBean>>() {
            @Override
            public List<CarBean> apply(@NonNull List<CarBean> carBeen) throws Exception {
                CarBean carBean = new CarBean();
                carBean.setId(-1);
                carBean.setName("车辆管理");
                carBeen.add(carBean);
                if (carBeen.size() == 1) {
                    myCar.setName("我的小车");
                    myCar.setSelected(1);
                    carBeen.add(0, myCar);
                    DBToolSingleton.getInstance().addCar(myCar);
                }
                cars = carBeen;
                return carBeen;
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<CarBean>>() {
                    @Override
                    public void accept(@NonNull List<CarBean> carBeen) throws Exception {
                        spinnerAdapter = new SpinnerAdapter(MainActivity.this);
                        spinnerAdapter.setData(carBeen);
                        spinner.setAdapter(spinnerAdapter);
                    }
                });
    }

    /**
     * 添加数据时的查询操作
     */
    private void query() {

        RxSQLite.queryCars().map(new Function<List<CarBean>, List<CarBean>>() {
            @Override
            public List<CarBean> apply(@NonNull List<CarBean> carBeen) throws Exception {
                return DBToolSingleton.getInstance().queryCars();
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<CarBean>>() {
                    @Override
                    public void accept(@NonNull List<CarBean> carBeen) throws Exception {
                        listAdapter.setCars(carBeen);
                        listView.setAdapter(listAdapter);
                    }
                });

    }

    /**
     * 自定义对话框
     */
    private void customDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        //点击事件
        DialogInterface.OnClickListener onClickListener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                switch (which) {
                    case DialogInterface.BUTTON_NEGATIVE:
                        int newId = cars.get(newPosition).getId();
                        DBToolSingleton.getInstance().removeCar(newId);
                        Toast.makeText(MainActivity.this, "删除:" + String.valueOf(newPosition), Toast.LENGTH_SHORT).show();
                        break;

                    case DialogInterface.BUTTON_NEUTRAL:
                        Toast.makeText(MainActivity.this, "取消", Toast.LENGTH_SHORT).show();
                        break;
                }

            }
        };

        builder.setIcon(R.mipmap.notice_board_warn)
                .setTitle("删除车辆")
                .setMessage("删除将不可恢复。\n您确定删除" + myCar.getName() + "，以及它的所有油耗数据吗？")
                .setNegativeButton("确定", onClickListener)
                .setNeutralButton("取消", onClickListener)
                .create()
                .show();

    }

    /**
     * 第三方分享
     */
    private void showShare() {

        OnekeyShare oks = new OnekeyShare();
        //关闭sso授权
        oks.disableSSOWhenAuthorize();
        // title标题，印象笔记、邮箱、信息、微信、人人网、QQ和QQ空间使用
        oks.setTitle("标题");
        // titleUrl是标题的网络链接，仅在Linked-in,QQ和QQ空间使用
        oks.setTitleUrl("http://sharesdk.cn");
        // text是分享文本，所有平台都需要这个字段
        oks.setText("我是分享文本");
        //分享网络图片，新浪微博分享网络图片需要通过审核后申请高级写入接口，否则请注释掉测试新浪微博
        oks.setImageUrl("http://f1.sharesdk.cn/imgs/2014/02/26/owWpLZo_638x960.jpg");
        // imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
        //oks.setImagePath("/sdcard/test.jpg");//确保SDcard下面存在此张图片
        // url仅在微信（包括好友和朋友圈）中使用
        oks.setUrl("http://sharesdk.cn");
        // comment是我对这条分享的评论，仅在人人网和QQ空间使用
        oks.setComment("我是测试评论文本");
        // site是分享此内容的网站名称，仅在QQ空间使用
        oks.setSite("ShareSDK");
        // siteUrl是分享此内容的网站地址，仅在QQ空间使用
        oks.setSiteUrl("http://sharesdk.cn");

        // 启动分享GUI
        oks.show(this);
    }

}
