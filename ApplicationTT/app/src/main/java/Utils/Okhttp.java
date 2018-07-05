package Utils;

import android.util.Log;

import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.Map;

import okhttp3.Call;

/**
 * Created by 他的猫 on 2017/5/10.
 */

public class Okhttp {


    public static void post(String url, Map<String, String> paramters,
                            final Objectcallback callback) {
        try {
            OkHttpUtils
                    .post()
                    .addHeader("Content-Type","application/x-www-form-urlencoded")
                    .url(url)
                    .params(paramters)
                    .build()
                    .execute(new StringCallback() {
                        @Override
                        public void onError(Call call, Exception e, int id) {
                            Log.e("888", "失败！");
                            callback.onFalia(id, e.toString());


                        }

                        @Override
                        public void onResponse(String responseString, int id) {
                            if (callback != null) {
                                callback.onsuccess(responseString);
                            }
                        }
                    });
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    public static void get(String url, Map<String, String> paramters,
                           final Objectcallback callback) {
        try {
            OkHttpUtils
                    .get()
                    .url(url)
                    .params(paramters)
                    .build()
                    .execute(new StringCallback() {
                        @Override
                        public void onError(Call call, Exception e, int id) {
                            callback.onFalia(id, e.toString());
                        }

                        @Override
                        public void onResponse(String responseString, int id) {
                            if (callback != null) {
                                callback.onsuccess(responseString);
                            }
                        }
                    });
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    public interface Objectcallback {
        void onsuccess(String st);

        void onFalia(int code, String errst);

    }


}
