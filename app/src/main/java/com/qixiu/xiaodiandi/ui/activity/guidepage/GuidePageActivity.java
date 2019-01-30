package com.qixiu.xiaodiandi.ui.activity.guidepage;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;

import com.qixiu.qixiu.utils.Preference;
import com.qixiu.xiaodiandi.R;
import com.qixiu.xiaodiandi.constant.ConstantString;
import com.qixiu.xiaodiandi.ui.activity.Loginactivity;
import com.qixiu.xiaodiandi.ui.activity.MainActivity;

import java.util.ArrayList;
import java.util.List;

public class GuidePageActivity extends AppCompatActivity {
    private ViewPager viewPager_guidepage;
    PagerAdapter adapter;
    List<View> images=new ArrayList<>();
    int currentPosition;
    int imageresourses[]={R.mipmap.guide01, R.mipmap.guide02, R.mipmap.guide03};


    public static void start(Context context){
        Intent intent =new Intent(context,GuidePageActivity.class);
        context.startActivity(intent);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //隐藏状态栏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide_page2);
        viewPager_guidepage = (ViewPager) findViewById(R.id.viewPager_guidepage);
        for (int i = 0; i < 3; i++) {
            ImageView imageView = new ImageView(this);
            ViewGroup.LayoutParams param = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            imageView.setLayoutParams(param);
            imageView.setImageResource(imageresourses[i]);
            images.add(imageView);
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(currentPosition==2){
                        if (Preference.get(ConstantString.USERID,"").equals("")) {
                            Loginactivity.start(GuidePageActivity.this,Loginactivity.class);
                        }else {
                            MainActivity.start(GuidePageActivity.this,MainActivity.class);
                        }
                        GuidePageActivity.this.finish();
                    }
                }
            });
        }
        adapter=new VpAdapter(images);
        viewPager_guidepage.setAdapter(adapter);
        viewPager_guidepage.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                currentPosition=position;
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }


}
