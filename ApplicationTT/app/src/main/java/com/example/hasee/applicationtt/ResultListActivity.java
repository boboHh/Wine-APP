package com.example.hasee.applicationtt;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Object.*;
import Utils.*;

public class ResultListActivity extends AppCompatActivity {
    ListView resultDisplay;
    String picture;
    String json;
    String TAG = "ResultListActivity";
    List<WineInfo> list = new ArrayList<WineInfo>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_list);
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        if(actionBar != null){
            actionBar.setTitle("识别结果");
            actionBar.setHomeButtonEnabled(true);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }


        Intent intent = getIntent();
        picture = intent.getStringExtra("picture");
        qingqiu();
        Log.d("ResultListActivity", "xx" + json);
//        JSONBean jsonBean = JSONUtils.parseListJSON(json);
//        Log.d("s", json);
//        for(int i = 0; i < jsonBean.getResult().size(); i++){
//            Log.d(TAG, jsonBean.getResult().get(i).getBrief());
//        }
        //loadResultList(jsonBean);

    }
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish(); // back button
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void qingqiu() {
        API.TJ(picture, new Okhttp.Objectcallback() {
            @Override
            public void onsuccess(String st) {
                Log.e("ResultListActivity", "xx" + st);
//                if (dialog != null) {
//                    dialog.dismiss();
//                }

                //PictureFileUtils.deleteCacheDirFile(TJ_Activity.this);
                Toast.makeText(ResultListActivity.this, st, Toast.LENGTH_SHORT).show();
                JSONBean jsonBean = JSONUtils.parseListJSON(st);
                loadResultList(jsonBean);

//                if (!TextUtils.isEmpty(st)) {
//                    QS_FH_Bean qs_fh_bean = JSONUtils.parseJSON(st, QS_FH_Bean.class);
//                    if (qs_fh_bean.isSucess()) {
//                        Toast.makeText(TJ_Activity.this, "退件成功", Toast.LENGTH_SHORT).show();
//
//                        finish();
//                    } else {
//                        Toast.makeText(TJ_Activity.this, "退件失败", Toast.LENGTH_SHORT).show();
//                    }
//
//                } else {
//                    Toast.makeText(TJ_Activity.this, "系统错误", Toast.LENGTH_SHORT).show();
//                }
            }

            @Override
            public void onFalia(int code, String errst) {
//                if (dialog != null) {
//                    dialog.dismiss();
//                }
                Toast.makeText(ResultListActivity.this, "网络错误" + errst, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void loadResultList(final JSONBean resultdata){
        new Thread(new Runnable() {

            @Override
            public void run() {
                Connection conn = null;
                List<WineInfo> list = new ArrayList<WineInfo>();
                try {
                    conn = MySQLUtil.getConnection();
                    Log.e(TAG, "run: getconnection");
                    String sql = "select id,name,year,raw_material,country,winery,temperature,time,volume,jiujd,colour,xiangqi,kougan,type,picture1 from wine where id=?";
                    for(int i = 1; i < resultdata.getResult().size(); i++){
                        sql += " or id=?";
                    }
                    Log.e(TAG, "run: getconnection2" );
                    PreparedStatement ps = conn.prepareStatement(sql);
                    for(int i = 0; i < resultdata.getResult().size(); i++){
                        ps.setString(i+1, resultdata.getResult().get(i).getBrief());
                    }
                    Log.e(TAG, "run: getconnection3" );
                    ResultSet rs = ps.executeQuery();
                    Log.e(TAG, "run: getconnection4" );
                    while (rs.next()) {
                        WineInfo wineInfo = new WineInfo();
                        for (int i = 0; i < resultdata.getResult().size(); i++) {
                            if (resultdata.getResult().get(i).getBrief().equals(rs.getString(1))) {
                                wineInfo.setLike_rate(resultdata.getResult().get(i).getScore());
                                Log.d(TAG, Double.toString(resultdata.getResult().get(i).getScore()) );
                            }
                        }
                        wineInfo.setId(rs.getString(1));
                        wineInfo.setName(rs.getString(2));
                        wineInfo.setYear(rs.getString(3));
                        wineInfo.setRaw_material(rs.getString(4));
                        wineInfo.setCountry(rs.getString(5));
                        wineInfo.setWinery(rs.getString(6));
                        wineInfo.setTemperature(rs.getString(7));
                        wineInfo.setTime(rs.getString(8));
                        wineInfo.setVolume(rs.getString(9));
                        wineInfo.setJiujd(rs.getString(10));
                        wineInfo.setColour(rs.getString(11));
                        wineInfo.setXiangqi(rs.getString(12));
                        wineInfo.setKougan(rs.getString(13));
                        wineInfo.setType(rs.getString(14));
//                        ByteArrayOutputStream out = new ByteArrayOutputStream();
//                        byte[] bt=rs.getBytes(15);
//                        Bitmap bitmap = ChageTypeUtils.byteToBitmap(bt);
//                        bitmap.compress(Bitmap.CompressFormat.JPEG, 20, out);
                        wineInfo.setPicture1(rs.getBytes(15));
                        //wineInfo.setPicture2(rs.getBytes(16));
                        //wineInfo.setPicture3(rs.getBytes(17));
                        list.add(wineInfo);
                    }
                    Log.e(TAG, "run: getconnection5" );
                    ps.close();
                    rs.close();
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                //根据相似度排序
                likeRateSort(list);
                disPlayOnTheResultList(list);
            }
        }).start();
    }

    private void disPlayOnTheResultList(final List<WineInfo> list) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                resultDisplay = (ListView)findViewById(R.id.result_display);
                final List<Map<String, ?>> dataList = new ArrayList<>();
                for (Integer i = 0; i < list.size(); i++) {
                    Map<String, Object> map = new HashMap<>();
                    map.put("image", ChageTypeUtils.byteToBitmap(list.get(i).getPicture1()));
                    map.put("score", Integer.toString((int)(list.get(i).getLike_rate()*100))+"%");
                    map.put("name", list.get(i).getName());
                    map.put("year", list.get(i).getYear());
                    map.put("place", list.get(i).getCountry());
                    dataList.add(map);
                }
                String[] from = {"image", "score", "name", "year", "place"};
                int[] to = {R.id.result_image, R.id.result_score, R.id.result_wine_name, R.id.result_wine_year, R.id.result_wine_place};

                SimpleAdapter simpleAdapter = new SimpleAdapter(ResultListActivity.this, dataList, R.layout.result_item, from, to);

                simpleAdapter.setViewBinder(new SimpleAdapter.ViewBinder() {

                    @Override
                    public boolean setViewValue(View view, Object data,
                                                String textRepresentation) {
                        if ((view instanceof ImageView) & (data instanceof Bitmap)) {
                            ImageView iv = (ImageView) view;
                            Bitmap bm = (Bitmap) data;
                            iv.setImageBitmap(bm);
                            return true;
                        }
                        return false;

                    }

                });
                resultDisplay.setAdapter(simpleAdapter);

                resultDisplay.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                        Intent intent = new Intent(ResultListActivity.this, WinePageActivity.class);
                        intent.putExtra("wine_info", list.get(position));
                        startActivity(intent);
                    }
                });
            }
        });
    }

    private void likeRateSort(List<WineInfo> list){
        Collections.sort(list, new Comparator<WineInfo>() {    //以相似度进行降序排序
            // @Override
            public int compare(WineInfo o1, WineInfo o2) {
                Double one = o1.getLike_rate();
                Double two = o2.getLike_rate();
                return two.compareTo(one);
            }
        });
    }


}
