package com.example.hasee.applicationtt;
import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.TranslateAnimation;

import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView item_ask, item_history;
    private ViewPager vp;
    private Fragment askFragment;
    private Fragment historyFragment;
    private List<Fragment> mFragmentList = new ArrayList<Fragment>();
    private FragmentAdapter mFragmentAdapter;
    private View viewIndicator;
    int screenWidth;
    int screenHeight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //去除工具栏
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);
        viewIndicator = (View)findViewById(R.id.view_indicator);
        initViews();
        initCursorPosition(this);

        mFragmentAdapter = new FragmentAdapter(this.getSupportFragmentManager(), mFragmentList);
        //vp.setOffscreenPageLimit(2);//ViewPager的缓存为2帧
        vp.setAdapter(mFragmentAdapter);
        vp.setCurrentItem(0);//初始设置ViewPager选中第一帧
        item_ask.setTextColor(Color.parseColor("#e51423"));
        //ViewPager的监听事件
        vp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                /*此方法在页面被选中时调用*/
                changeTextColor(position);
                translateAnimation(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                /*此方法是在状态改变的时候调用，其中arg0这个参数有三种状态（0，1，2）。
                arg0 ==1的时辰默示正在滑动，
                arg0==2的时辰默示滑动完毕了，
                arg0==0的时辰默示什么都没做。*/
            }
        });
    }

    /**
     * 初始化布局View
     */
    private void initViews() {
        item_ask = (TextView) findViewById(R.id.item_ask);
        item_history = (TextView) findViewById(R.id.item_history);

        item_ask.setOnClickListener(this);
        item_history.setOnClickListener(this);

        vp = (ViewPager) findViewById(R.id.viewpager);
        askFragment = new AskFragment();
        historyFragment = new HistoryFragment();
        //给FragmentList添加数据
        mFragmentList.add(askFragment);
        mFragmentList.add(historyFragment);
    }
    private void initCursorPosition(Activity context){
        //初始化指示器位置，获取屏幕宽高
        DisplayMetrics dm = new DisplayMetrics();
        context.getWindowManager().getDefaultDisplay().getMetrics(dm);
        screenWidth = dm.widthPixels;
        screenHeight = dm.heightPixels;
        ViewGroup.LayoutParams layoutParams=viewIndicator.getLayoutParams();
        layoutParams.width=screenWidth/2;
        viewIndicator.setLayoutParams(layoutParams);
        TranslateAnimation animation = new TranslateAnimation(-screenWidth/2,0,0,0);
        animation.setFillAfter(true);
        viewIndicator.startAnimation(animation);
    }
    /**
     * 点击底部Text 动态修改ViewPager的内容
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.item_ask:
                vp.setCurrentItem(0, true);
                break;
            case R.id.item_history:
                vp.setCurrentItem(1, true);
                break;
        }
    }


    public class FragmentAdapter extends FragmentPagerAdapter {

        List<Fragment> fragmentList = new ArrayList<Fragment>();

        public FragmentAdapter(FragmentManager fm, List<Fragment> fragmentList) {
            super(fm);
            this.fragmentList = fragmentList;
        }

        @Override
        public Fragment getItem(int position) {
            return fragmentList.get(position);
        }

        @Override
        public int getCount() {
            return fragmentList.size();
        }

    }

    /*
     *由ViewPager的滑动修改底部导航Text的颜色
     */
    private void changeTextColor(int position) {
        if (position == 0) {
            item_ask.setTextColor(Color.parseColor("#e84e40"));
            item_history.setTextColor(Color.parseColor("#9e9e9e"));
        } else if (position == 1) {
            item_ask.setTextColor(Color.parseColor("#9e9e9e"));
            item_history.setTextColor(Color.parseColor("#e84e40"));
        }
    }

    private void translateAnimation(int index){
        TranslateAnimation animation = null;
        if(index==0){
            animation=new TranslateAnimation(screenWidth/2,0,0,0);
        }
        else if(index==1){
            animation=new TranslateAnimation(0,screenWidth/2,0,0);
        }
        animation.setFillAfter(true);
        animation.setDuration(300);
        viewIndicator.startAnimation(animation);
    }

//    private void initHistoryList(){
//        ListView historyDisplay = (ListView)findViewById(R.id.history_display);
//        List<Map<String,?>> dataList = new ArrayList<>();
//        for(Integer i = 1; i <= 20; i++){
//            Map<String, String> map = new HashMap<>();
//            map.put("num", i.toString());
//            map.put("name", "82年拉菲");
//            map.put("place", "中国产");
//            dataList.add(map);
//        }
//        String[] from = {"num", "name", "place"};
//        int[] to = {R.id.history_item_num,R.id.history_item_name,R.id.history_item_place};
//
//        SimpleAdapter simpleAdapter = new SimpleAdapter(this, dataList ,R.layout.history_item, from, to);
//        historyDisplay.setAdapter(simpleAdapter);
//    }

}
