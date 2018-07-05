package com.example.hasee.applicationtt;

import android.content.ComponentName;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import Object.*;
import Utils.ChageTypeUtils;
import Utils.SQLiteUtil;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TextView;

import java.io.File;
import java.net.URI;

public class WinePageActivity extends AppCompatActivity {
    ImageView wineImage;
    TextView wineName;
    TextView wineYear;
    TextView wineRawMaterial;
    TextView wineCountry;
    TextView wineWinery;
    TextView wineTemperature;
    TextView wineTime;
    TextView wineJiujd;
    TextView wineVolume;
    TextView wineColour;
    TextView wineXiangqi;
    TextView wineKougan;
    TextView wineType;
    Button collect;
    Button share;
    private SQLiteUtil dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wine_page);

        wineImage = (ImageView) findViewById(R.id.wine_image);
        wineName = (TextView) findViewById(R.id.wine_name);
        wineYear = (TextView) findViewById(R.id.wine_year);
        wineRawMaterial = (TextView) findViewById(R.id.wine_raw_material);
        wineCountry = (TextView) findViewById(R.id.wine_country);
        wineWinery = (TextView) findViewById(R.id.wine_winery);
        wineTemperature = (TextView) findViewById(R.id.wine_temperature);
        wineTime = (TextView) findViewById(R.id.wine_time);
        wineJiujd = (TextView) findViewById(R.id.wine_jiujd);
        wineVolume = (TextView) findViewById(R.id.wine_volume);
        wineColour = (TextView) findViewById(R.id.wine_colour);
        wineXiangqi = (TextView) findViewById(R.id.wine_xiangqi);
        wineKougan = (TextView) findViewById(R.id.wine_kougan);
        wineType = (TextView) findViewById(R.id.wine_type);

        share = (Button) findViewById(R.id.wine_share);
        collect = (Button) findViewById(R.id.wine_collect);

        final WineInfo wineInfo = (WineInfo)getIntent().getSerializableExtra("wine_info");

        wineImage.setImageBitmap(ChageTypeUtils.byteToBitmap(wineInfo.getPicture1()));
        wineName.setText(wineInfo.getName());
        wineYear.setText(wineInfo.getYear());
        wineRawMaterial.setText(wineInfo.getRaw_material());
        wineCountry.setText(wineInfo.getCountry());
        wineWinery.setText(wineInfo.getWinery());
        wineTemperature.setText(wineInfo.getTemperature());
        wineTime.setText(wineInfo.getTime());
        wineJiujd.setText(wineInfo.getJiujd());
        wineVolume.setText(wineInfo.getVolume());
        wineColour.setText(wineInfo.getColour());
        wineXiangqi.setText(wineInfo.getXiangqi());
        wineKougan.setText(wineInfo.getKougan());
        wineType.setText(wineInfo.getType());



        collect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbHelper = new SQLiteUtil(WinePageActivity.this, "LocalDataBase.db",null,1);
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                Object[] set = {null, wineInfo.getName(), wineInfo.getYear(), wineInfo.getRaw_material(), wineInfo.getCountry(),
                        wineInfo.getWinery(), wineInfo.getTemperature(), wineInfo.getTime(), wineInfo.getJiujd(), wineInfo.getVolume(),
                        wineInfo.getColour(), wineInfo.getXiangqi(), wineInfo.getKougan(), wineInfo.getType(), wineInfo.getPicture1(),null,null};
                db.execSQL("insert into HistoryData values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)",set);
                db.close();
            }
        });

        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Uri uri = Uri.parse(MediaStore.Images.Media.insertImage(
//                        WinePageActivity.this.getContentResolver(), BitmapFactory.decodeResource(WinePageActivity.this.getResources(), wineImage.getId()), null, null));
//                Intent imageIntent = new Intent(Intent.ACTION_SEND);
//                //imageIntent.setPackage("com.tencent.mobileqq");
//                imageIntent.setType("image/*");
//                imageIntent.putExtra(Intent.EXTRA_STREAM, uri);
//                imageIntent.putExtra(Intent.EXTRA_TEXT,"您的好友邀请您进入问酒");
//                imageIntent.putExtra(Intent.EXTRA_TITLE,"问酒");
//                imageIntent.setComponent(new ComponentName("com.tencent.mobileqq", "com.tencent.mobileqq.activity.JumpActivity"));
//                startActivity(imageIntent);

//                Intent intent = new Intent("android.intent.action.SEND");
//                intent.setType("text/plain");
//                intent.putExtra(Intent.EXTRA_SUBJECT, "消息标题");
//                intent.putExtra(Intent.EXTRA_TEXT, "消息内容");
//                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                intent.setComponent(new ComponentName("com.tencent.mobileqq", "com.tencent.mobileqq.activity.JumpActivity"));//若是分享到QQ，可将包名改为com.tencent.mobileqq，分享页面名改为com.tencent.mobileqq.activity.JumpActivity
//                startActivity(intent);

//                Bitmap decodeRecource = BitmapFactory.decodeResource(getResources(), R.drawable.app_icon);
//                File file = wri(decodeRecource);
//                Uri img = Uri.fromFile(file);//获得一张图片的Uri
//                Intent sendIntent = new Intent();
//                sendIntent.setAction(Intent.ACTION_SEND);
//                sendIntent.putExtra(Intent.EXTRA_TEXT, "memememeemememeda");
//                sendIntent.setType("text/plain");
////          sendIntent.setClassName("com.tencent.mm", "com.tencent.mm.ui.tools.ShareImgUI");//微信朋友
////          sendIntent.setClassName("com.tencent.mobileqq", "cooperation.qqfav.widget.QfavJumpActivity");//保存到QQ收藏
////          sendIntent.setClassName("com.tencent.mobileqq", "cooperation.qlink.QlinkShareJumpActivity");//QQ面对面快传
////          sendIntent.setClassName("com.tencent.mobileqq", "com.tencent.mobileqq.activity.qfileJumpActivity");//传给我的电脑
//                sendIntent.setClassName("com.tencent.mobileqq", "com.tencent.mobileqq.activity.JumpActivity");//QQ好友或QQ群
////          sendIntent.setClassName("com.tencent.mm", "com.tencent.mm.ui.tools.ShareToTimeLineUI");//微信朋友圈，仅支持分享图片
//                startActivity(sendIntent);

                Intent textIntent = new Intent(Intent.ACTION_SEND);
                textIntent.setType("text/plain");
                textIntent.putExtra(Intent.EXTRA_TEXT, "名称："+wineInfo.getName()+ "\n年份："+wineInfo.getYear()+"\n原料："+wineInfo.getRaw_material()+"\n国家："+wineInfo.getCountry()
                                +"\n酒庄："+wineInfo.getWinery()+"\n适宜温度："+wineInfo.getTemperature()+"\n醒酒时间："+ wineInfo.getTime()+"\n酒精度："+wineInfo.getJiujd()+"\n容量："+wineInfo.getVolume()
                        +"\n色泽："+wineInfo.getColour()+"\n香气："+wineInfo.getXiangqi()+"\n口感："+wineInfo.getKougan()+"\n类型："+wineInfo.getType()+"\n来自“问酒——让你更懂酒");
                startActivity(Intent.createChooser(textIntent, "分享"));

//                Bitmap bitmap = wineImage.getDrawingCache();
////                Uri uri = ChageTypeUtils.bitmap2uri(WinePageActivity.this,bitmap);
////
////                Intent shareIntent = new Intent(Intent.ACTION_SEND);
////                if(uri!=null){
////                    shareIntent.putExtra(Intent.EXTRA_STREAM, uri);
////                    shareIntent.setType("image/*");
////                    //当用户选择短信时使用sms_body取得文字
////                    shareIntent.putExtra("sms_body", WinePageActivity.class);
////                }else{
////                    shareIntent.setType("text/plain");
////                }
////                shareIntent.putExtra(Intent.EXTRA_TEXT, WinePageActivity.class);
////                //自定义选择框的标题
////                startActivity(Intent.createChooser(shareIntent, "邀请好友"));
////                //系统默认标题

//                Uri uri = ChageTypeUtils.bitmap2uri(WinePageActivity.this,ChageTypeUtils.byteToBitmap(wineInfo.getPicture1()));
//                Intent imageIntent = new Intent(Intent.ACTION_SEND);
//                imageIntent.setType("image/*");
//                imageIntent.putExtra(Intent.EXTRA_STREAM, uri);
//                startActivity(Intent.createChooser(imageIntent, "分享"));

            }
        });
    }
}
