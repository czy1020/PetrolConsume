package com.example.czy.petrolconsume.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by CZY on 2017/4/12.
 */

public class DBHelper extends SQLiteOpenHelper {

    static final String DB_NAME = "petrol";
    static final int DB_VERSION = 1;
    static final String CAR_TBL = "car";
    static final String RECORD_TBL = "record";
    static final String CAR_ID = "id";
    static final String CAR_NAME = "name";
    static final String CAR_SELECTED = "selected";
    static final String CAR_MODEL = "model";
    static final String CAR_UUID = "uuid";
    static final String RECORD_ID = "id";
    static final String RECORD_DATE = "date";
    static final String RECORD_ODOMETER = "odometer";
    static final String RECORD_PRICE = "price";
    static final String RECORD_YUAN = "yuan";
    static final String RECORD_TYPE = "type";
    static final String RECORD_GASSUP = "gassup";
    static final String RECORD_REMARK = "remark";
    static final String RECORD_FORGET = "forget";
    static final String RECORD_LIGHT_ON = "lightOn";
    static final String RECORD_STATION_ID = "stationId";

    /**
     * 创建数据库
     *
     * @param context
     */
    public DBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    /**
     * 当数据库成功被创建之后调用此方法，创建表
     *
     * @param db
     */
    @Override
    public void onCreate(SQLiteDatabase db) {

        //创建car表
        db.execSQL("create table if not exists " + CAR_TBL + " ("
                + CAR_ID + " integer primary key autoincrement, "
                + CAR_NAME + " text, "
                + CAR_SELECTED + " integer, "
                + CAR_MODEL + " integer, "
                + CAR_UUID + " integer);");

        //创建record表
        db.execSQL("create table if not exists " + RECORD_TBL + " ("
                + RECORD_ID + " integer primary key autoincrement, "
                + RECORD_DATE + " integer, "
                + RECORD_ODOMETER + " integer, "
                + RECORD_PRICE + " real, "
                + RECORD_YUAN + " real, "
                + RECORD_TYPE + " integer, "
                + RECORD_GASSUP + " integer, "
                + RECORD_REMARK + " string, "
                + RECORD_FORGET + " integer, "
                + RECORD_LIGHT_ON + " integer, "
                + RECORD_STATION_ID + " integer);");
    }

    /**
     * 更新数据库版本
     *
     * @param db
     * @param oldVersion
     * @param newVersion
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

}
