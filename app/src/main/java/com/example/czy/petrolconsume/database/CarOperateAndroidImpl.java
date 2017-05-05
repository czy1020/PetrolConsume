package com.example.czy.petrolconsume.database;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.czy.petrolconsume.bean.CarBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by CZY on 2017/4/13.
 */

public class CarOperateAndroidImpl implements TableCarOperate {

    private SQLiteOpenHelper helper;

    public CarOperateAndroidImpl(SQLiteOpenHelper helper) {
        this.helper = helper;
    }

    @Override
    public void addCar(CarBean carBean) {

        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues values = new ContentValues();
        if (carBean.getId() != 0) {
            values.put(DBHelper.CAR_ID, carBean.getId());
        }
        values.put(DBHelper.CAR_NAME, carBean.getName());
        values.put(DBHelper.CAR_SELECTED, carBean.getSelected());
        values.put(DBHelper.CAR_MODEL, carBean.getModel());
        values.put(DBHelper.CAR_UUID, carBean.getUuid());
        db.insert(DBHelper.CAR_TBL, null, values);
        db.close();

    }

    @Override
    public void removeCar(int id) {

        SQLiteDatabase db = helper.getWritableDatabase();
        String whereClause = DBHelper.CAR_ID + " = ?";
        String[] whereArgs = new String[]{String.valueOf(id)};
        db.delete(DBHelper.CAR_TBL, whereClause, whereArgs);
        db.close();

    }

    @Override
    public void updateCar(CarBean carBean) {
        updateCar(openDatabase(), true, carBean);
    }

    private void updateCar(SQLiteDatabase db, boolean isClose, CarBean carBean) {
        ContentValues values = new ContentValues();
        values.put(DBHelper.CAR_NAME, carBean.getName());
        values.put(DBHelper.CAR_SELECTED, carBean.getSelected());
        values.put(DBHelper.CAR_MODEL, carBean.getModel());
        values.put(DBHelper.CAR_UUID, carBean.getUuid());
        String whereClause = DBHelper.CAR_ID + " = ?";
        String[] whereArgs = new String[]{String.valueOf(carBean.getId())};
        db.update(DBHelper.CAR_TBL, values, whereClause, whereArgs);
        if (isClose) {
            closeDatabase(db);
        }
    }

    @Override
    public List<CarBean> queryCars() {

        List<CarBean> cars = new ArrayList<>(5);
        SQLiteDatabase db = helper.getWritableDatabase();
        Cursor cursor = db.query(DBHelper.CAR_TBL, null, null, null, null, null, null);

        if (cursor != null && cursor.moveToFirst()) {
            int indexId = cursor.getColumnIndex(DBHelper.CAR_ID);
            int indexName = cursor.getColumnIndex(DBHelper.CAR_NAME);
            int indexSelected = cursor.getColumnIndex(DBHelper.CAR_SELECTED);
            int indexModel = cursor.getColumnIndex(DBHelper.CAR_MODEL);
            int indexUUID = cursor.getColumnIndex(DBHelper.CAR_UUID);
            do {
                int id = cursor.getInt(indexId);
                String name = cursor.getString(indexName);
                int selected = cursor.getInt(indexSelected);
                int model = cursor.getInt(indexModel);
                int uuid = cursor.getInt(indexUUID);
                CarBean carBean = new CarBean();
                carBean.setId(id);
                carBean.setName(name);
                carBean.setSelected(selected);
                carBean.setModel(model);
                carBean.setUuid(uuid);

                cars.add(carBean);
            } while (cursor.moveToNext());
            cursor.close();
        }
        db.close();
        return cars;
    }

    @Override
    public CarBean querySelectedCar() {
        return querySelectedCar(openDatabase(), true);
    }

    // 查询出当前选中的小车
    private CarBean querySelectedCar(SQLiteDatabase db, boolean isClose) {
        String selection = DBHelper.CAR_SELECTED + " = ?";
        String[] selectionArgs = new String[]{String.valueOf(1)};
        Cursor cursor = db.query(
                DBHelper.CAR_TBL,
                null,
                selection,
                selectionArgs,
                null,
                null,
                null
        );
        if (cursor != null && cursor.moveToFirst()) {
            int indexId = cursor.getColumnIndex(DBHelper.CAR_ID);
            int indexName = cursor.getColumnIndex(DBHelper.CAR_NAME);
            int indexSelected = cursor.getColumnIndex(DBHelper.CAR_SELECTED);
            int indexModel = cursor.getColumnIndex(DBHelper.CAR_MODEL);
            int indexUUID = cursor.getColumnIndex(DBHelper.CAR_UUID);
            int id = cursor.getInt(indexId);
            String name = cursor.getString(indexName);
            int selected = cursor.getInt(indexSelected);
            int model = cursor.getInt(indexModel);
            int uuid = cursor.getInt(indexUUID);
            CarBean carBean = new CarBean();
            carBean.setId(id);
            carBean.setName(name);
            carBean.setSelected(selected);
            carBean.setModel(model);
            carBean.setUuid(uuid);
            cursor.close();
            return carBean;
        }
        if (isClose) {
            closeDatabase(db);
        }
        return null;
    }

    @Override
    public void changeSelectedCar(int id) {

        SQLiteDatabase db = openDatabase();
        // 先把原来选中的车辆设置为不选中
        CarBean currentSelectedCar = querySelectedCar(db, false);
        currentSelectedCar.setSelected(0);
        updateCar(currentSelectedCar);
        // 将新选中的车设置为选中状态
        ContentValues values = new ContentValues();
        values.put(DBHelper.CAR_SELECTED, 1);
        String whereClause = DBHelper.CAR_ID + " = ?";
        String[] whereArgs = new String[]{String.valueOf(id)};
        db.update(DBHelper.CAR_TBL,
                values,
                whereClause,
                whereArgs);
        closeDatabase(db);
    }

    @Override
    public void changeSelectedCar(CarBean newSelectedCar) {

        SQLiteDatabase db = openDatabase();
        // 先把原来选中的车辆设置为不选中
        CarBean currentSelectedCar = querySelectedCar(db, false);
        currentSelectedCar.setSelected(0);
        updateCar(db, false, currentSelectedCar);
        // 将新选中的车设置为选中状态
        newSelectedCar.setSelected(1);
        updateCar(db, false, newSelectedCar);
        closeDatabase(db);
    }

    private SQLiteDatabase openDatabase() {
        return helper.getWritableDatabase();
    }

    private void closeDatabase(SQLiteDatabase db) {
        if (db != null) {
            db.close();
        }
    }

}
