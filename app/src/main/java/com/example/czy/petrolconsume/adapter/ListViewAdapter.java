package com.example.czy.petrolconsume.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.czy.petrolconsume.tool.OnViewClickListener;
import com.example.czy.petrolconsume.R;
import com.example.czy.petrolconsume.bean.CarBean;

import java.util.List;

/**
 * Created by CZY on 17/4/19.
 */

public class ListViewAdapter extends BaseAdapter implements OnViewClickListener {
    private List<CarBean> cars;
    private Context context;
    private OnViewClickListener onViewClickListener;

    public void setOnViewClickListener(OnViewClickListener onViewClickListener) {
        this.onViewClickListener = onViewClickListener;
    }

    public ListViewAdapter(Context context) {
        this.context = context;
    }

    public void setCars(List<CarBean> cars) {
        this.cars = cars;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return cars == null ? 0 : cars.size();
    }

    @Override
    public Object getItem(int position) {
        return cars.get(position);
    }

    @Override
    public long getItemId(int position) {
        return cars == null ? 0 : position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        Holder holder = null;
        if (convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.item_list,parent,false);
            holder = new Holder(convertView);
            convertView.setTag(holder);
        }else {
            holder = (Holder) convertView.getTag();
        }
        CarBean carBean = cars.get(position);
        holder.textView.setText(carBean.getName());

        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onViewClickListener.click(v,position);
            }
        });
        return convertView;
    }

    @Override
    public void click(View view,int position) {

    }

    class Holder{
        TextView textView;
        ImageView imageView;
        public Holder(View view){
            textView = (TextView) view.findViewById(R.id.name_car);
            imageView = (ImageView) view.findViewById(R.id.delete_iv);
        }
    }
}
