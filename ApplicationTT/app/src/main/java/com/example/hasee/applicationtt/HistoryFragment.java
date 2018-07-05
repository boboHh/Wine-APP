package com.example.hasee.applicationtt;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import Object.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Utils.MySQLUtil;
import Utils.SQLiteUtil;

/**
 * Created by hasee on 2018/5/18.
 */

public class HistoryFragment extends Fragment{
    private ListView historyDisplay;
    private SQLiteUtil dbHelper;
    public HistoryFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //test
//        new Thread( new Runnable() {
//            @Override
//            public void run () {
//                Connection conn = null;
//                try
//                { conn = MySQLUtil.getConnection();
//                    conn.close();
//                    Log.d("s" ,"succeed");
//                } catch (SQLException e) {
//                    Log.d("s" ,"fail");
//                    e.printStackTrace();
//                }
//            }
//        }).start();


        // Inflate the layout for this fragment
        dbHelper = new SQLiteUtil(getActivity(), "LocalDataBase.db",null,1);

        View view = inflater.inflate(R.layout.layout2, container, false);
        historyDisplay = (ListView)view.findViewById(R.id.history_display);

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        final List<WineInfo> list = new ArrayList<WineInfo>();
        Cursor cursor = db.rawQuery("select * from HistoryData order by id desc",null);
        while(cursor.moveToNext()){
            WineInfo wineInfo = new WineInfo();
            wineInfo.setId(cursor.getString(0));
            wineInfo.setName(cursor.getString(1));
            wineInfo.setYear(cursor.getString(2));
            wineInfo.setRaw_material(cursor.getString(3));
            wineInfo.setCountry(cursor.getString(4));
            wineInfo.setWinery(cursor.getString(5));
            wineInfo.setTemperature(cursor.getString(6));
            wineInfo.setTime(cursor.getString(7));
            wineInfo.setVolume(cursor.getString(8));
            wineInfo.setJiujd(cursor.getString(9));
            wineInfo.setColour(cursor.getString(10));
            wineInfo.setXiangqi(cursor.getString(11));
            wineInfo.setKougan(cursor.getString(12));
            wineInfo.setType(cursor.getString(13));
            wineInfo.setPicture1(cursor.getBlob(14));
            wineInfo.setPicture2(cursor.getBlob(15));
            wineInfo.setPicture3(cursor.getBlob(16));
            list.add(wineInfo);
        }
        cursor.close();
        db.close();
        if(list.isEmpty())
            Toast.makeText(getActivity(),"您还没有历史记录哦", Toast.LENGTH_LONG).show();
        else {
            final List<Map<String, ?>> dataList = new ArrayList<>();
            for (Integer i = 0; i < list.size(); i++) {
                Map<String, String> map = new HashMap<>();
                i++;
                map.put("num", i.toString());
                i--;
                map.put("name", list.get(i).getName());
                map.put("place", list.get(i).getCountry());
                dataList.add(map);
            }
            String[] from = {"num", "name", "place"};
            int[] to = {R.id.history_item_num, R.id.history_item_name, R.id.history_item_place};

            SimpleAdapter simpleAdapter = new SimpleAdapter(getActivity(), dataList, R.layout.history_item, from, to);
            historyDisplay.setAdapter(simpleAdapter);

            historyDisplay.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                    Intent intent = new Intent(getActivity(), WinePageActivity.class);
                    intent.putExtra("wine_info", list.get(position));
                    startActivity(intent);
                }
            });
        }
        return view;
    }
}