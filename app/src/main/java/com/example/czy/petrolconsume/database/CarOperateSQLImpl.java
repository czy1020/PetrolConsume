package com.example.czy.petrolconsume.database;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.czy.petrolconsume.bean.CarBean;

import java.util.List;

/**
 * Created by CZY on 2017/4/13.
 */

public class CarOperateSQLImpl implements TableCarOperate {

    private SQLiteOpenHelper helper;

    public CarOperateSQLImpl(SQLiteOpenHelper helper) {
        this.helper = helper;
    }

    @Override
    public void addCar(CarBean carBean) {

        SQLiteDatabase db = helper.getWritableDatabase();
        String sql;
        if (carBean.getId() == 0) {
            sql = "insert into cars (name, selected, model, uuid) values (" + carBean.getName() + ", " + carBean.getSelected() + ", " + carBean.getModel() + ", " + carBean.getUuid() + ");";
        } else {
            sql = "insert into cars values (" + carBean.getId() + ", " + carBean.getName() + ", " + carBean.getSelected() + ", " + carBean.getModel() + ", " + carBean.getUuid() + ");";
        }
        db.execSQL(sql);
    }

    @Override
    public void removeCar(int id) {

    }

    @Override
    public void updateCar(CarBean carBean) {

    }

    @Override
    public List<CarBean> queryCars() {
        return null;
    }

    @Override
    public CarBean querySelectedCar() {
        return null;
    }

    @Override
    public void changeSelectedCar(int id) {

    }

    @Override
    public void changeSelectedCar(CarBean newSelectedCar) {

    }
}
