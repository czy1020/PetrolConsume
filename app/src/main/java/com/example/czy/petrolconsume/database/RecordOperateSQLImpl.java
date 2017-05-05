package com.example.czy.petrolconsume.database;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.czy.petrolconsume.bean.CostBean;
import com.example.czy.petrolconsume.bean.RecordBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by CZY on 2017/4/13.
 */

public class RecordOperateSQLImpl implements TableRecordOperate {

    private SQLiteOpenHelper helper;

    public RecordOperateSQLImpl(SQLiteOpenHelper helper) {
        this.helper = helper;
    }

    @Override
    public void addRecord(RecordBean recordBean) {

        SQLiteDatabase db = helper.getWritableDatabase();
        String sql;
        if (recordBean.getId() == 0) {
            sql = "insert into " + DBHelper.RECORD_TBL + " ("
                    + DBHelper.RECORD_DATE + ","
                    + DBHelper.RECORD_ODOMETER + ","
                    + DBHelper.RECORD_PRICE + ","
                    + DBHelper.RECORD_YUAN + ","
                    + DBHelper.RECORD_TYPE + ","
                    + DBHelper.RECORD_GASSUP + ","
                    + DBHelper.RECORD_REMARK + ","
                    + DBHelper.RECORD_FORGET + ","
                    + DBHelper.RECORD_LIGHT_ON + ","
                    + DBHelper.RECORD_STATION_ID + ") values ("
                    + recordBean.getDate() + ", "
                    + recordBean.getOdometer() + ", "
                    + recordBean.getPrice() + ", "
                    + recordBean.getYuan() + ", "
                    + recordBean.getType() + ", "
                    + recordBean.getGassup() + ", "
                    + recordBean.getRemark() + ", "
                    + recordBean.getForget() + ", "
                    + recordBean.getLightOn() + ", "
                    + recordBean.getStationId() + ");";
        } else {
            sql = "insert into " + DBHelper.RECORD_TBL + " values ("
                    + recordBean.getId() + ", "
                    + recordBean.getDate() + ", "
                    + recordBean.getOdometer() + ", "
                    + recordBean.getPrice() + ", "
                    + recordBean.getYuan() + ", "
                    + recordBean.getType() + ", "
                    + recordBean.getGassup() + ", "
                    + recordBean.getRemark() + ", "
                    + recordBean.getForget() + ", "
                    + recordBean.getLightOn() + ", "
                    + recordBean.getStationId() + ");";
        }
        db.execSQL(sql);
    }

    @Override
    public void removeRecord(int id) {
        SQLiteDatabase db = helper.getWritableDatabase();
        String SQL = "delete from " + DBHelper.RECORD_TBL + " where " + DBHelper.RECORD_ID + " = " + id;
        db.execSQL(SQL);
    }

    @Override
    public void updateRecord(RecordBean recordBean) {
        SQLiteDatabase db = helper.getReadableDatabase();
        String SQL = "update " + DBHelper.RECORD_TBL + " set";
    }

    @Override
    public List<RecordBean> querySelectedRecord() {

        List<RecordBean> beanList = new ArrayList<>();

        String sql = "select*from " + DBHelper.RECORD_TBL;
        SQLiteDatabase db = helper.getWritableDatabase();
        Cursor cursor = db.rawQuery(sql, null);

        if (cursor != null && cursor.moveToFirst()) {

            int date = cursor.getColumnIndex("date");
            int odometer = cursor.getColumnIndex("odometer");
            int price = cursor.getColumnIndex("price");
            int yuan = cursor.getColumnIndex("yuan");
            int type = cursor.getColumnIndex("type");
            int gassup = cursor.getColumnIndex("gassup");
            int remark = cursor.getColumnIndex("remark");
            int carId = cursor.getColumnIndex("carId");
            int forget = cursor.getColumnIndex("forget");
            int lightOn = cursor.getColumnIndex("lightOn");
            int stationId = cursor.getColumnIndex("stationId");

            do {

                RecordBean recordBean = new RecordBean();

                recordBean.setDate(cursor.getInt(date));
                recordBean.setOdometer(cursor.getInt(odometer));
                recordBean.setPrice(cursor.getFloat(price));
                recordBean.setYuan(cursor.getFloat(yuan));
                recordBean.setType(cursor.getInt(type));
                recordBean.setGassup(cursor.getInt(gassup));
                recordBean.setRemark(cursor.getString(remark));
                recordBean.setCarId(cursor.getInt(carId));
                recordBean.setForget(cursor.getInt(forget));
                recordBean.setLightOn(cursor.getInt(lightOn));
                recordBean.setStationId(cursor.getInt(stationId));

                beanList.add(recordBean);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return beanList;
    }

    @Override
    public List<RecordBean> queryRecordThreeMonth() {
        List<RecordBean> beanList = new ArrayList<>();

        String sql = "select A.date, A.odometer, A.price, A.yuan, A.carId, datetime(A.date / 1000, 'unixepoch') " +
                "from record as A, car as B " +
                "where A.date > ( " +
                "    strftime('%s', datetime('now', '-3 month')) * 1000 " +
                ") " +
                "and " +
                "A.carId = B._id" +
                "and" +
                "B.selected = 1;";

        SQLiteDatabase db = helper.getWritableDatabase();
        Cursor cursor = db.rawQuery(sql, null);

        if (cursor != null && cursor.moveToFirst()) {

            int date = cursor.getColumnIndex("date");
            int odometer = cursor.getColumnIndex("odometer");
            int price = cursor.getColumnIndex("price");
            int yuan = cursor.getColumnIndex("yuan");
            int type = cursor.getColumnIndex("type");
            int gassup = cursor.getColumnIndex("gassup");
            int remark = cursor.getColumnIndex("remark");
            int carId = cursor.getColumnIndex("carId");
            int forget = cursor.getColumnIndex("forget");
            int lightOn = cursor.getColumnIndex("lightOn");
            int stationId = cursor.getColumnIndex("stationId");

            do {

                RecordBean recordBean = new RecordBean();

                recordBean.setDate(cursor.getInt(date));
                recordBean.setOdometer(cursor.getInt(odometer));
                recordBean.setPrice(cursor.getFloat(price));
                recordBean.setYuan(cursor.getFloat(yuan));
                recordBean.setType(cursor.getInt(type));
                recordBean.setGassup(cursor.getInt(gassup));
                recordBean.setRemark(cursor.getString(remark));
                recordBean.setCarId(cursor.getInt(carId));
                recordBean.setForget(cursor.getInt(forget));
                recordBean.setLightOn(cursor.getInt(lightOn));
                recordBean.setStationId(cursor.getInt(stationId));

                beanList.add(recordBean);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return beanList;
    }

    @Override
    public List<RecordBean> queryRecordHalfYear() {
        List<RecordBean> beanList = new ArrayList<>();

        String sql = "select A.date, A.odometer, A.price, A.yuan, A.carId, datetime(A.date / 1000, 'unixepoch') " +
                "from record as A, car as B " +
                "where A.date > ( " +
                "    strftime('%s', datetime('now', '-6 month')) * 1000 " +
                ") " +
                "and " +
                "A.carId = B._id" +
                "and" +
                "B.selected = 1;";

        SQLiteDatabase db = helper.getWritableDatabase();
        Cursor cursor = db.rawQuery(sql, null);

        if (cursor != null && cursor.moveToFirst()) {

            int date = cursor.getColumnIndex("date");
            int odometer = cursor.getColumnIndex("odometer");
            int price = cursor.getColumnIndex("price");
            int yuan = cursor.getColumnIndex("yuan");
            int type = cursor.getColumnIndex("type");
            int gassup = cursor.getColumnIndex("gassup");
            int remark = cursor.getColumnIndex("remark");
            int carId = cursor.getColumnIndex("carId");
            int forget = cursor.getColumnIndex("forget");
            int lightOn = cursor.getColumnIndex("lightOn");
            int stationId = cursor.getColumnIndex("stationId");

            do {

                RecordBean recordBean = new RecordBean();

                recordBean.setDate(cursor.getInt(date));
                recordBean.setOdometer(cursor.getInt(odometer));
                recordBean.setPrice(cursor.getFloat(price));
                recordBean.setYuan(cursor.getFloat(yuan));
                recordBean.setType(cursor.getInt(type));
                recordBean.setGassup(cursor.getInt(gassup));
                recordBean.setRemark(cursor.getString(remark));
                recordBean.setCarId(cursor.getInt(carId));
                recordBean.setForget(cursor.getInt(forget));
                recordBean.setLightOn(cursor.getInt(lightOn));
                recordBean.setStationId(cursor.getInt(stationId));

                beanList.add(recordBean);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return beanList;
    }

    @Override
    public List<RecordBean> queryRecordOneYear() {

        List<RecordBean> beanList = new ArrayList<>();

        String sql = "select A.date, A.odometer, A.price, A.yuan, A.carId, datetime(A.date / 1000, 'unixepoch') " +
                "from record as A, car as B " +
                "where A.date > ( " +
                "    strftime('%s', datetime('now', '-12 month')) * 1000 " +
                ") " +
                "and " +
                "A.carId = B._id" +
                "and" +
                "B.selected = 1;";

        SQLiteDatabase db = helper.getWritableDatabase();
        Cursor cursor = db.rawQuery(sql, null);

        if (cursor != null && cursor.moveToFirst()) {

            int date = cursor.getColumnIndex("date");
            int odometer = cursor.getColumnIndex("odometer");
            int price = cursor.getColumnIndex("price");
            int yuan = cursor.getColumnIndex("yuan");
            int type = cursor.getColumnIndex("type");
            int gassup = cursor.getColumnIndex("gassup");
            int remark = cursor.getColumnIndex("remark");
            int carId = cursor.getColumnIndex("carId");
            int forget = cursor.getColumnIndex("forget");
            int lightOn = cursor.getColumnIndex("lightOn");
            int stationId = cursor.getColumnIndex("stationId");

            do {

                RecordBean recordBean = new RecordBean();

                recordBean.setDate(cursor.getInt(date));
                recordBean.setOdometer(cursor.getInt(odometer));
                recordBean.setPrice(cursor.getFloat(price));
                recordBean.setYuan(cursor.getFloat(yuan));
                recordBean.setType(cursor.getInt(type));
                recordBean.setGassup(cursor.getInt(gassup));
                recordBean.setRemark(cursor.getString(remark));
                recordBean.setCarId(cursor.getInt(carId));
                recordBean.setForget(cursor.getInt(forget));
                recordBean.setLightOn(cursor.getInt(lightOn));
                recordBean.setStationId(cursor.getInt(stationId));

                beanList.add(recordBean);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return beanList;
    }


    @Override
    public List<CostBean> queryEveryMonthMoney() {
        List<CostBean> costBeen = new ArrayList<>();
        SQLiteDatabase db = helper.getWritableDatabase();
        String sql = "select " +
                "strftime('%Y-%m',datetime(A.date / 1000, 'unixepoch'),'localtime') date_t, " +
                "sum(A.yuan) money " +
                "from records_tbl as A, cars_tbl as B " +
                "    where A.carId = B._id and B.selected = 1 " +
                "GROUP BY date_t " +
                "ORDER BY date_t;";
        Cursor cursor = db.rawQuery(sql, null);
        if (cursor != null && cursor.moveToFirst()) {

            int indexTime = cursor.getColumnIndex("time");
            int indexMoney = cursor.getColumnIndex("money");

            do {
                String time = cursor.getColumnName(indexTime);
                float money = cursor.getFloat(indexMoney);
                CostBean costBean = new CostBean();
                costBean.setTime(time);
                costBean.setMoney(money);
                costBeen.add(costBean);
            } while (cursor.moveToNext());
            cursor.close();
        }
        return costBeen;
    }

    @Override
    public List<CostBean> queryEveryYearMoney() {
        List<CostBean> costBeen = new ArrayList<>();
        SQLiteDatabase db = helper.getWritableDatabase();
        String sql = "select " +
                "strftime('%Y', datetime(A.date / 1000, 'unixepoch'),'localtime') date_t, " +
                "sum(A.yuan) money " +
                "from records_tbl as A, cars_tbl as B " +
                "where A.carId = B._id and B.selected = 1 " +
                "GROUP BY date_t " +
                "ORDER BY date_t;";
        Cursor cursor = db.rawQuery(sql, null);
        if (cursor != null && cursor.moveToFirst()) {

            int indexTime = cursor.getColumnIndex("time");
            int indexMoney = cursor.getColumnIndex("money");

            do {
                String time = cursor.getColumnName(indexTime);
                float money = cursor.getFloat(indexMoney);
                CostBean costBean = new CostBean();
                costBean.setTime(time);
                costBean.setMoney(money);
                costBeen.add(costBean);
            } while (cursor.moveToNext());
            cursor.close();
        }
        return costBeen;
    }
}
