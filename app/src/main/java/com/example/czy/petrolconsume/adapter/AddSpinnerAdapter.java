package com.example.czy.petrolconsume.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckedTextView;
import android.widget.TextView;

import com.example.czy.petrolconsume.bean.BrandBean;

/**
 * Created by dllo on 17/4/24.
 */

public class AddSpinnerAdapter extends BaseAdapter {

    private BrandBean data;
    private Context context;

    public AddSpinnerAdapter(Context context) {
        this.context = context;
    }

    public void setData(BrandBean brandEntity) {
        this.data = brandEntity;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return data == null ? 0 : data.getNames().size();
    }

    @Override
    public Object getItem(int position) {
        return data.getNames().get(position);
    }

    @Override
    public long getItemId(int position) {
        return data.getIdxes().get(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(android.R.layout.simple_spinner_item, parent, false);
        }
        TextView textView = (TextView) convertView;
        textView.setText(data.getNames().get(position));
        return convertView;
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        convertView = LayoutInflater.from(context).inflate(android.R.layout.simple_spinner_dropdown_item, parent, false);
        CheckedTextView textView = (CheckedTextView) convertView;
        textView.setText(data.getNames().get(position));
        return convertView;
    }
}
