package com.qixiu.qixiu.utils.html_utils;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.text.Html;
import android.text.TextUtils;
import android.util.Log;
import android.widget.TextView;

import com.qixiu.qixiu.utils.DownLoadFileUtils;
import com.qixiu.qixiu.utils.PictureCut;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class HtmlUtils {
    public int windowWith;
    private static HtmlUtils htmlUtils;

    public static HtmlUtils getInstance() {
        if (htmlUtils == null) {
            htmlUtils = new HtmlUtils();
        }
        return htmlUtils;
    }


    public void setWindowWith(int windowWith) {
        this.windowWith = windowWith;
    }

    Drawable getImageFromNetwork(String imageUrl) {
        URL myFileUrl = null;
        Drawable drawable = null;
        try {
            myFileUrl = new URL(imageUrl);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        try {
            HttpURLConnection conn = (HttpURLConnection) myFileUrl
                    .openConnection();
            conn.setDoInput(true);
            conn.connect();
            InputStream is = conn.getInputStream();
            drawable = Drawable.createFromStream(is, null);
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        DrawbleCacheUtils.getInstance().saveCache(imageUrl, drawable);
        return drawable;
    }

    Drawable getImageFromNetWork01(String imageUrl) {
//        Bitmap bitmap = PictureCut.returnNetBitmap(imageUrl);
        String code = PictureCut.getCode(imageUrl);
        String path = PictureCut.getPath(code, DownLoadFileUtils.path);
        if (TextUtils.isEmpty(path)) {
            path = DownLoadFileUtils.InitFile.download(imageUrl, null, DownLoadFileUtils.InitFile.getFileTag(imageUrl));
        }
        //下面取消的是流转换drawble
//        FileInputStream fis = null;
//        try {
//            fis = new FileInputStream(new File(path));
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
//        Drawable src = Drawable.createFromResourceStream(BaseApplication.getContext().getResources(), null, fis, "src", null);
////        drawable = Drawable.createFromPath(newPath);
        //用低配bimap转换为drawble
        Bitmap bitmap = ImageFactory.getBitmap(path);
        Drawable drawable = new BitmapDrawable(bitmap);
        return drawable;
    }


//    public static Bitmap readBitMap(Context context, int resId) {
//        BitmapFactory.Options opt = new BitmapFactory.Options();
//        opt.inPreferredConfig = Bitmap.Config.RGB_565;
//        opt.inPurgeable = true;
//        opt.inInputShareable = true;
//        //获取资源图片
//        InputStream is = context.getResources().openRawResource(resId);
//        return BitmapFactory.decodeStream(is, , opt);
//    }


    //    Handler handler;
    public void setHtml(TextView text, String html, Activity activity) {
        if (TextUtils.isEmpty(html)) {
            return;
        }
        new Thread(new Runnable() {
            @Override
            public void run() {
                Html.ImageGetter imageGetter = new Html.ImageGetter() {
                    @Override
                    public Drawable getDrawable(String source) {
                        Drawable drawable = DrawbleCacheUtils.getInstance().getCache(source);
                        if (drawable == null) {
//                            drawable = getImageFromNetwork(source);//两种办法都试一下
                            drawable = getImageFromNetWork01(source);
                        }
                        setDrwableSize(drawable, windowWith);
                        return drawable;
                    }
                };
                try {
                    CharSequence charSequence = Html.fromHtml(html, imageGetter, null);
                    activity.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            text.setText(charSequence);
                        }
                    });
                } catch (Exception e) {
                    Log.e("exception", "后台标签出现了问题");
                }

            }
        }).start();
    }

    private void setDrwableSize(Drawable drawable, int windowWith) {
        float w = windowWith - 30;
        float h = w / ((float) drawable.getIntrinsicWidth() / (float) drawable.getIntrinsicHeight());
        drawable.setBounds(0, 0, (int) w, (int) h);
    }
}
