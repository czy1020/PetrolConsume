package com.example.czy.petrolconsume.database;

import com.example.czy.petrolconsume.bean.CarBean;

import java.util.List;

/**
 * Created by CZY on 2017/4/13.
 */

public interface TableCarOperate {

    void addCar(CarBean carBean);

    void removeCar(int id);

    void updateCar(CarBean carBean);

    List<CarBean> queryCars();

    CarBean querySelectedCar();

    void changeSelectedCar(int id);

    void changeSelectedCar(CarBean newSelectedCar);

}
