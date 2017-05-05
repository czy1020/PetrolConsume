package com.example.czy.petrolconsume.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckedTextView;
import android.widget.TextView;

import com.example.czy.petrolconsume.bean.CarBean;

import java.util.List;

/**
 * Created by CZY on 2017/4/18.
 */

public class SpinnerAdapter extends BaseAdapter {

    private List<CarBean> data;
    private Context context;
    private int currentPos;

    public SpinnerAdapter(Context context) {
        this.context = context;
    }

    public void setData(List<CarBean> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    public int getCurrentPos() {
        return currentPos;
    }

    public void setCurrentPos(int currentPos) {
        this.currentPos = currentPos;
    }

    @Override
    public int getCount() {
        return data != null ? data.size() : 0;
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return data.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(android.R.layout.simple_spinner_item, parent, false);
        }
        TextView textView = (TextView) convertView;
        CarBean carBean = (CarBean) getItem(position);
        textView.setText(carBean.getName());
        return convertView;
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        convertView = LayoutInflater.from(context).inflate(android.R.layout.simple_spinner_dropdown_item, parent, false);
        CheckedTextView checkedTextView = (CheckedTextView) convertView;
        CarBean carBean = (CarBean) getItem(position);
        checkedTextView.setText(carBean.getName());
        return convertView;
    }
}
