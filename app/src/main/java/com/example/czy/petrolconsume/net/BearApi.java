package com.example.czy.petrolconsume.net;

import com.example.czy.petrolconsume.bean.BrandBean;
import com.example.czy.petrolconsume.bean.CarDetailBean;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by dllo on 17/4/24.
 */

public interface BearApi {

    String BEAR_URL = "http://www.xiaoxiongyouhao.com/";

    @GET("models/pinpai.json")
    Observable<BrandBean> getBrands();

    @GET("models/{index}/che_xi.json")
    Observable<BrandBean> getCarSeries(@Path("index") int index);

    @GET("models/{indexBrand}/che_xing_{indexSeries}.json")
    Observable<BrandBean> getCarType(@Path("indexBrand") int brand, @Path("indexSeries") int series);

    @GET("models/spec.php")
    Observable<CarDetailBean> getCarDetail(@Query("cheXing") int type);
}
