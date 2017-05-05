package com.example.czy.petrolconsume.database;

import com.example.czy.petrolconsume.bean.CarBean;
import com.example.czy.petrolconsume.bean.CostBean;
import com.example.czy.petrolconsume.bean.RecordBean;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by CZY on 2017/4/18.
 */

public class RxSQLite {

    /**
     * 添加车辆
     *
     * @param carBean
     */
    public static void addCar(CarBean carBean) {
        Observable.just(carBean).map(new Function<CarBean, String>() {
            @Override
            public String apply(@NonNull CarBean carBean) throws Exception {
                DBToolSingleton.getInstance().addCar(carBean);
                return null;
            }
        }).subscribeOn(Schedulers.io()).subscribe();
    }

    /**
     * 删除车辆
     *
     * @param id
     */
    public static void removeCar(final int id) {
        Observable.just(id).map(new Function<Integer, String>() {
            @Override
            public String apply(@NonNull Integer integer) throws Exception {
                DBToolSingleton.getInstance().removeCar(id);
                return null;
            }
        }).subscribeOn(Schedulers.io()).subscribe();
    }

    /**
     * 修改车辆信息
     *
     * @param carBean
     */
    public static void updateCar(CarBean carBean) {
        Observable.just(carBean).map(new Function<CarBean, String>() {
            @Override
            public String apply(@NonNull CarBean carBean) throws Exception {
                DBToolSingleton.getInstance().updateCar(carBean);
                return null;
            }
        }).subscribeOn(Schedulers.io()).subscribe();
    }

    /**
     * 查询所有车辆信息
     *
     * @return
     */
    public static Observable<List<CarBean>> queryCars() {
        return Observable.just("").map(new Function<String, List<CarBean>>() {
            @Override
            public List<CarBean> apply(@NonNull String s) throws Exception {
                return DBToolSingleton.getInstance().queryCars();
            }
        });
    }

    /**
     * 查询选中车辆信息
     *
     * @return
     */
    public static Observable<CarBean> querySelectedCar() {
        Observable.just("").map(new Function<String, CarBean>() {
            @Override
            public CarBean apply(@NonNull String s) throws Exception {
                return DBToolSingleton.getInstance().querySelectedCar();
            }
        }).subscribeOn(Schedulers.io()).subscribe();
        return null;
    }

    /**
     * 改变选中车辆状态
     *
     * @param id
     */
    public static void changeSelectedCar(final int id) {
        Observable.just("").map(new Function<String, CarBean>() {
            @Override
            public CarBean apply(@NonNull String s) throws Exception {
                DBToolSingleton.getInstance().changeSelectedCar(id);
                return null;
            }
        }).subscribeOn(Schedulers.io()).subscribe();
    }

    /**
     * 改变选中车辆状态
     *
     * @param newSelectedCar
     */
    public static void changeSelectedCar(final CarBean newSelectedCar) {
        Observable.just("").map(new Function<String, CarBean>() {
            @Override
            public CarBean apply(@NonNull String s) throws Exception {
                DBToolSingleton.getInstance().changeSelectedCar(newSelectedCar);
                return null;
            }
        }).subscribeOn(Schedulers.io()).subscribe();
    }

    /**
     * 添加选中车辆记录
     *
     * @param RecordBean
     */
    public static void addRecord(RecordBean RecordBean) {
        Observable.just(RecordBean).map(new Function<RecordBean, String>() {
            @Override
            public String apply(@NonNull RecordBean RecordBean) throws Exception {
                DBToolSingleton.getInstance().addRecord(RecordBean);
                return null;
            }
        }).subscribeOn(Schedulers.io()).subscribe();
    }

    /**
     * 删除选中车辆记录
     *
     * @param id
     */
    public static void removeRecord(final int id) {
        Observable.just("").map(new Function<String, RecordBean>() {
            @Override
            public RecordBean apply(@NonNull String s) throws Exception {
                DBToolSingleton.getInstance().removeRecord(id);
                return null;
            }
        }).subscribeOn(Schedulers.io()).subscribe();
    }

    /**
     * 修改选中车辆记录
     *
     * @param RecordBean
     */
    public static void updateRecord(RecordBean RecordBean) {
        Observable.just(RecordBean).map(new Function<RecordBean, String>() {
            @Override
            public String apply(@NonNull RecordBean RecordBean) throws Exception {
                DBToolSingleton.getInstance().updateRecord(RecordBean);
                return null;
            }
        }).subscribeOn(Schedulers.io()).subscribe();
    }

    /**
     * 查询选中车辆所有记录
     *
     * @return
     */
    public static Observable<List<RecordBean>> querySelectedRecord() {
        return Observable.just("").map(new Function<String, List<RecordBean>>() {
            @Override
            public List<RecordBean> apply(@NonNull String s) throws Exception {
                return DBToolSingleton.getInstance().querySelectedRecord();
            }
        });
    }

    /**
     * 查询选中车辆三个月的记录
     *
     * @return
     */
    public static Observable<List<RecordBean>> queryRecordThreeMonth() {
        return Observable.just("").map(new Function<String, List<RecordBean>>() {
            @Override
            public List<RecordBean> apply(@NonNull String s) throws Exception {
                return DBToolSingleton.getInstance().queryRecordThreeMonth();
            }
        });
    }

    /**
     * 查询选中车辆半年的记录
     *
     * @return
     */
    public static Observable<List<RecordBean>> queryRecordHalfYear() {
        return Observable.just("").map(new Function<String, List<RecordBean>>() {
            @Override
            public List<RecordBean> apply(@NonNull String s) throws Exception {
                return DBToolSingleton.getInstance().queryRecordHalfYear();
            }
        });
    }

    /**
     * 查询选中车辆一年的记录
     *
     * @return
     */
    public static Observable<List<RecordBean>> queryRecordOneYear() {
        return Observable.just("").map(new Function<String, List<RecordBean>>() {
            @Override
            public List<RecordBean> apply(@NonNull String s) throws Exception {
                return DBToolSingleton.getInstance().queryRecordOneYear();
            }
        });
    }

    /**
     * 查询每个月花费的油钱
     *
     * @return
     */
    public static Observable<List<CostBean>> queryEveryMonthMoney() {
        return Observable.just("").map(new Function<String, List<CostBean>>() {
            @Override
            public List<CostBean> apply(@NonNull String s) throws Exception {
                return DBToolSingleton.getInstance().queryEveryMonthMoney();
            }
        });
    }

    /**
     * 查询每年花费的油钱
     *
     * @return
     */
    public static Observable<List<CostBean>> queryEveryYearMoney() {
        return Observable.just("").map(new Function<String, List<CostBean>>() {
            @Override
            public List<CostBean> apply(@NonNull String s) throws Exception {
                return DBToolSingleton.getInstance().queryEveryYearMoney();
            }
        });
    }

}
