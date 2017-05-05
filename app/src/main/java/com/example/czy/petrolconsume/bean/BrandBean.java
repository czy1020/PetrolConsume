package com.example.czy.petrolconsume.bean;

import java.util.List;

/**
 * Created by CZY on 17/4/24.
 */

public class BrandBean {
    private int total;
    private int pinpai;
    private int chexi;
    private List<Integer> idxes;
    private List<String> names;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getPinpai() {
        return pinpai;
    }

    public void setPinpai(int pinpai) {
        this.pinpai = pinpai;
    }

    public int getChexi() {
        return chexi;
    }

    public void setChexi(int chexi) {
        this.chexi = chexi;
    }

    public List<Integer> getIdxes() {
        return idxes;
    }

    public void setIdxes(List<Integer> idxes) {
        this.idxes = idxes;
    }

    public List<String> getNames() {
        return names;
    }

    public void setNames(List<String> names) {
        this.names = names;
    }
}
