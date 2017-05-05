package com.example.czy.petrolconsume.database;

import com.example.czy.petrolconsume.app.BaseApplication;
import com.example.czy.petrolconsume.bean.CarBean;
import com.example.czy.petrolconsume.bean.CostBean;
import com.example.czy.petrolconsume.bean.RecordBean;

import java.util.List;

/**
 * Created by CZY on 2017/4/13.
 */

public class DBToolSingleton implements TableCarOperate, TableRecordOperate {

    private static final DBToolSingleton INSTANCE = new DBToolSingleton();

    /**
     * 供给外界调用的方法
     *
     * @return DBToolSingleton类的对象
     */
    public static DBToolSingleton getInstance() {
        return INSTANCE;
    }

    //数据库对象
    private DBHelper dbHelper;
    //car表接口对象
    private TableCarOperate tableCarOperate;
    //record表接口对象
    private TableRecordOperate tableRecordOperate;

    /**
     * 私有的构造方法
     */
    private DBToolSingleton() {
        dbHelper = new DBHelper(BaseApplication.getContext());
        tableCarOperate = new CarOperateAndroidImpl(dbHelper);
        tableRecordOperate = new RecordOperateSQLImpl(dbHelper);
    }

    /**
     * 添加车辆
     *
     * @param carBean
     */
    @Override
    public void addCar(CarBean carBean) {
        tableCarOperate.addCar(carBean);
    }

    /**
     * 删除车辆
     *
     * @param id
     */
    @Override
    public void removeCar(int id) {
        tableCarOperate.removeCar(id);
    }

    /**
     * 修改车辆信息
     *
     * @param carBean
     */
    @Override
    public void updateCar(CarBean carBean) {
        tableCarOperate.updateCar(carBean);
    }

    /**
     * 查询所有车辆信息
     *
     * @return
     */
    @Override
    public List<CarBean> queryCars() {
        return tableCarOperate.queryCars();
    }

    /**
     * 查询选中车辆信息
     *
     * @return
     */
    @Override
    public CarBean querySelectedCar() {
        return tableCarOperate.querySelectedCar();
    }

    /**
     * 更改选中车辆信息
     *
     * @param id
     */
    @Override
    public void changeSelectedCar(int id) {
        tableCarOperate.changeSelectedCar(id);
    }

    /**
     * 更改选中车辆信息
     *
     * @param newSelectedCar
     */
    @Override
    public void changeSelectedCar(CarBean newSelectedCar) {
        tableCarOperate.changeSelectedCar(newSelectedCar);
    }

    /**
     * 添加选中车辆的记录
     *
     * @param recordBean
     */
    @Override
    public void addRecord(RecordBean recordBean) {
        tableRecordOperate.addRecord(recordBean);
    }

    /**
     * 删除选中车辆的记录
     *
     * @param id
     */
    @Override
    public void removeRecord(int id) {
        tableRecordOperate.removeRecord(id);
    }

    /**
     * 修改选中车辆的记录
     *
     * @param recordBean
     */
    @Override
    public void updateRecord(RecordBean recordBean) {
        tableRecordOperate.updateRecord(recordBean);
    }

    /**
     * 查询选中车辆的所有记录
     *
     * @return
     */
    @Override
    public List<RecordBean> querySelectedRecord() {
        return tableRecordOperate.querySelectedRecord();
    }

    /**
     * 查询选中车辆三个月内的记录
     *
     * @return
     */
    @Override
    public List<RecordBean> queryRecordThreeMonth() {
        return tableRecordOperate.queryRecordThreeMonth();
    }

    /**
     * 查询选中车辆半年内的记录
     *
     * @return
     */
    @Override
    public List<RecordBean> queryRecordHalfYear() {
        return tableRecordOperate.queryRecordHalfYear();
    }

    /**
     * 查询选中车辆一年内的记录
     *
     * @return
     */
    @Override
    public List<RecordBean> queryRecordOneYear() {
        return tableRecordOperate.queryRecordOneYear();
    }

    /**
     * 查询选中车辆的每月的加油费用
     *
     * @return
     */
    @Override
    public List<CostBean> queryEveryMonthMoney() {
        return tableRecordOperate.queryEveryMonthMoney();
    }

    /**
     * 查询选中车辆的每年的加油费用
     *
     * @return
     */
    @Override
    public List<CostBean> queryEveryYearMoney() {
        return tableRecordOperate.queryEveryYearMoney();
    }
}
