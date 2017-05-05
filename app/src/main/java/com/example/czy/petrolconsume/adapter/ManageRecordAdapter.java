package com.example.czy.petrolconsume.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.czy.petrolconsume.bean.RecordBean;

import java.util.List;

/**
 * Created by CZY on 2017/4/28.
 */

public class ManageRecordAdapter extends BaseAdapter {

    private List<RecordBean> data;
    private Context context;

    public ManageRecordAdapter(Context context) {
        this.context = context;
    }

    public void setData(List<RecordBean> data) {
        this.data = data;
        notifyDataSetChanged();
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
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return null;
    }

    class Holder{
        TextView textView;

        public Holder(View view) {

        }
    }
}
