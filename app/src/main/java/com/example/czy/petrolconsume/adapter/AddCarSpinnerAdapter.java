package com.example.czy.petrolconsume.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckedTextView;
import android.widget.TextView;

import com.example.czy.petrolconsume.bean.BrandBean;

import java.util.List;

/**
 * Created by CZY on 17/4/24.
 */

public class AddCarSpinnerAdapter extends BaseAdapter {

    private BrandBean data;
    private List<Integer> idxes;
    private List<String> names;
    private Context context;

    public AddCarSpinnerAdapter(Context context) {
        this.context = context;
    }

    public void setData(BrandBean data) {
        idxes = data.getIdxes();
        names = data.getNames();
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        int count = 0;
        try {
            count = idxes.size();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return count;
    }

    @Override
    public Object getItem(int position) {
        return names.get(position);
    }

    @Override
    public long getItemId(int position) {
        return idxes.get(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(android.R.layout.simple_spinner_item, parent, false);
        }
        TextView textView = (TextView) convertView;
        textView.setText(names.get(position));
        return convertView;
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        convertView = LayoutInflater.from(context).inflate(android.R.layout.simple_spinner_dropdown_item, parent, false);
        CheckedTextView textView = (CheckedTextView) convertView;
        textView.setText(names.get(position));
        return convertView;
    }
}
