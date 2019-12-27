package com.bitcola.doc.api;


import com.bitcola.doc.http.OkHttpUtil;

/**
 * @author
 * @create 2019-06-29 17:03
 **/
public class MarketApi {

    public static final String HOST = "https://api.bitcola.io";

    public static final String GET_CONFIG = "/api/market/config";
    public static final String GET_TICKER = "/api/market/ticker";
    public static final String GET_ALL_TICKER = "/api/market/all_ticker";
    public static final String GET_DEPTH = "/api/market/depth";
    public static final String GET_TRADES = "/api/market/trades";
    public static final String GET_KLINE = "/api/market/kline";


    public static String getConfig(){
        return OkHttpUtil.httpGet(HOST+MarketApi.GET_CONFIG);
    }
    public static String getTicker(String pair){
        return OkHttpUtil.httpGet(HOST+MarketApi.GET_TICKER+"?pair="+pair);
    }
    public static String getAllTicker(){
        return OkHttpUtil.httpGet(HOST+MarketApi.GET_ALL_TICKER);
    }
    public static String getDepth(String pair){
        return OkHttpUtil.httpGet(HOST+MarketApi.GET_DEPTH+"?pair="+pair);
    }
    public static String getTrades(String pair){
        return OkHttpUtil.httpGet(HOST+MarketApi.GET_TRADES+"?pair="+pair);
    }
    public static String getKline(String pair,String type){
        return OkHttpUtil.httpGet(HOST+MarketApi.GET_KLINE+"?pair="+pair+"&type="+type);
    }


}
