package com.example.czy.petrolconsume.database;

import com.example.czy.petrolconsume.bean.CostBean;
import com.example.czy.petrolconsume.bean.RecordBean;

import java.util.List;

/**
 * Created by CZY on 2017/4/13.
 */

public interface TableRecordOperate {

    void addRecord(RecordBean recordBean);

    void removeRecord(int id);

    void updateRecord(RecordBean recordBean);

    List<RecordBean> querySelectedRecord();

    List<RecordBean> queryRecordThreeMonth();

    List<RecordBean> queryRecordHalfYear();

    List<RecordBean> queryRecordOneYear();

    List<CostBean> queryEveryMonthMoney();

    List<CostBean> queryEveryYearMoney();
}
