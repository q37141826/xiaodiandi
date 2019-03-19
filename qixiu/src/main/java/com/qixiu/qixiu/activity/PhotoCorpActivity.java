package com.qixiu.qixiu.activity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RelativeLayout;

import com.qixiu.qixiu.R;
import com.qixiu.qixiu.titleview.TitleView;
import com.qixiu.qixiu.utils.PictureCut;
import com.qixiu.qixiu.utils.corp.CropImageView;



public class PhotoCorpActivity extends AppCompatActivity {
    CropImageView imgeView_corp;
    private Bitmap bitmap;
    public static final int CORP_DATA=11111;
    public static final String PATH="PATH";
        TitleView titleView;
        RelativeLayout relativeLayout;
    protected void onInitViewNew() {
        relativeLayout=findViewById(R.id.vg_title);
        imgeView_corp=findViewById(R.id.imgeView_corp);
        titleView=new TitleView(this);
        relativeLayout.addView(titleView.getView());
        titleView.setTitle("裁剪");
        titleView.setRightText("完成");
        titleView.setRightListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bitmap cropBitMap = imgeView_corp.getCroppedImage(bitmap);
                imgeView_corp.setImageBitmap(cropBitMap);
                String s = PictureCut.saveBitmapToSdcard(cropBitMap);
                Intent intent =new Intent();
                intent.putExtra(PATH,s);
                setResult(CORP_DATA,intent);
                finish();
            }
        });
    }




    protected void onInitData() {
        String path = getIntent().getStringExtra(PATH);
//        Glide.with(this).load(stringExtra).into(imgeView_corp);
        PictureCut.CompressImageWithThumb.callpath(path, new PictureCut.CompressImageWithThumb.CallBack() {
            @Override
            public void callBack(String path) {
                bitmap = BitmapFactory.decodeFile(path);
                imgeView_corp.setImageBitmap(bitmap);
            }
        });
    }



    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_corp);
        setScreenOrentation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        onInitViewNew();
        onInitData();
    }
    //提示，参数在ActivityInfo里面
    public void setScreenOrentation(int screenOrentation) {
        setRequestedOrientation(screenOrentation);
    }
}
