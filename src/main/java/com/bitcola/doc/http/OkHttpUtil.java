package com.bitcola.doc.http;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * @author
 * @create 2019-06-29 16:50
 **/
public class OkHttpUtil {
    private static OkHttpClient client = new OkHttpClient();
    public static String httpGet(String url) {
        String result = null;
        Request request = new Request.Builder().url(url).build();
        try {
            Response response = client.newCall(request).execute();
            result = response.body().string();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

}
