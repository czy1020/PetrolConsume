package com.example.czy.petrolconsume.bean;

/**
 * Created by CZY on 2017/4/12.
 */

public class RecordBean {
    private int id;
    private int date;
    private int odometer;
    private float price;
    private float yuan;
    private int type;
    private int gassup;
    private String remark;
    private int forget;
    private int lightOn;
    private int stationId;
    private int carId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDate() {
        return date;
    }

    public void setDate(int date) {
        this.date = date;
    }

    public int getOdometer() {
        return odometer;
    }

    public void setOdometer(int odometer) {
        this.odometer = odometer;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public float getYuan() {
        return yuan;
    }

    public void setYuan(float yuan) {
        this.yuan = yuan;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getGassup() {
        return gassup;
    }

    public void setGassup(int gassup) {
        this.gassup = gassup;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public int getForget() {
        return forget;
    }

    public void setForget(int forget) {
        this.forget = forget;
    }

    public int getLightOn() {
        return lightOn;
    }

    public void setLightOn(int lightOn) {
        this.lightOn = lightOn;
    }

    public int getStationId() {
        return stationId;
    }

    public void setStationId(int stationId) {
        this.stationId = stationId;
    }

    public int getCarId() {
        return carId;
    }

    public void setCarId(int carId) {
        this.carId = carId;
    }
}
