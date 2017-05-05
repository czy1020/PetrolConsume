package com.example.czy.petrolconsume.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.czy.petrolconsume.R;

import java.util.List;

/**
 * Created by CZY on 2017/4/18.
 */

public class MainViewPagerAdapter extends FragmentPagerAdapter {

    private Context context;
    private List<Fragment> fragments;
    private String[] titles;
    private int[] imgs;

    public MainViewPagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.context = context;
    }

    public void setFragments(List<Fragment> fragments) {
        this.fragments = fragments;
        notifyDataSetChanged();
    }

    public void setTitles(String[] titles) {
        this.titles = titles;
        notifyDataSetChanged();
    }

    public void setImgs(int[] imgs) {
        this.imgs = imgs;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    public View getTabView(int position) {
        View view = LayoutInflater.from(context).inflate(R.layout.tab_view, null);
        TextView textView = (TextView) view.findViewById(R.id.tab_tv);
        textView.setText(titles[position]);
        ImageView imageView = (ImageView) view.findViewById(R.id.tab_iv);
        imageView.setImageResource(imgs[position]);
        return view;
    }
}
