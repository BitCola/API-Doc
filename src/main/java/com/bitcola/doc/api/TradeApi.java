package com.bitcola.doc.api;


import com.bitcola.doc.http.OkHttpUtil;
import com.bitcola.doc.util.EncryptDigestUtil;

import java.math.BigDecimal;
import java.util.TreeMap;

/**
 * @author
 * @create 2019-06-29 17:03
 **/
public class TradeApi {

    public static String accessKey = "Your accessKey";
    public static String encoderSecretKey = "Your encrypted secretKey";

    public static final String HOST = "https://api.bitcola.io";

    public static final String GET_ACCOUNT = "/api/trade/getAccountInfo";
    public static final String ORDER = "/api/trade/order";
    public static final String CANCEL_ORDER = "/api/trade/cancelOrder";
    public static final String GET_ORDER = "/api/trade/getOrder";
    public static final String GET_ORDERS = "/api/trade/getOrders";



    public static void main(String[] args) {
        System.out.println(getAccount());
    }



    public static String getAccount() {
        return signGet(GET_ACCOUNT, null);
    }


    public static String order(String pair,String type,String direction,BigDecimal price,BigDecimal amount){
        TreeMap<String,String> params = new TreeMap<>();
        params.put("pair",pair);
        params.put("type",type);
        params.put("direction",direction);
        params.put("price",price.stripTrailingZeros().toPlainString());
        params.put("amount",amount.stripTrailingZeros().toPlainString());
        return signGet(ORDER,params);
    }

    public static String cancelOrder(String id){
        TreeMap<String,String> params = new TreeMap<>();
        params.put("id",id);
        return signGet(CANCEL_ORDER,params);
    }

    public static String getOrder(String id){
        TreeMap<String,String> params = new TreeMap<>();
        params.put("id",id);
        return signGet(GET_ORDER,params);
    }

    public static String getOrders(String pair,String status,String direction,String type,int page){
        TreeMap<String,String> params = new TreeMap<>();
        params.put("pair",pair);
        params.put("status",status);
        params.put("direction",direction);
        params.put("type",type);
        params.put("page",String.valueOf(page));
        return signGet(GET_ORDERS,params);
    }




    private static String signGet(String uri, TreeMap<String,String> params){
        if (params == null) params = new TreeMap<>();
        params.put("accessKey",accessKey);
        params.put("reqTime",String.valueOf(System.currentTimeMillis()));
        String queryString = parameterMapToString(params);
        String sign = EncryptDigestUtil.hmacSign(queryString, encoderSecretKey);
        queryString = queryString + "&sign="+sign;
        return OkHttpUtil.httpGet(HOST+uri+"?"+queryString);
    }

    private static String parameterMapToString(TreeMap<String,String> params){
        StringBuilder builder = new StringBuilder();
        for (String key : params.keySet()) {
            String value = params.get(key);
            if (value!=null){
                builder.append("&").append(key).append("=").append(params.get(key));
            }
        }
        return builder.toString().replaceFirst("&","");
    }

}
