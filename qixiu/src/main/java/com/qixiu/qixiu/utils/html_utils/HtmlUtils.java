package com.qixiu.qixiu.utils.html_utils;

import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Message;
import android.text.Html;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class HtmlUtils {
    public int windowWith;
    public static HtmlUtils htmlUtils;

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


//    Drawable getImageFromNetWork01(String imageUrl){
//        Bitmap bitmap = PictureCut.returnNetBitmap(imageUrl);
//        PictureCut.deleteAllImage();
//        String path = PictureCut.saveBitmapToSdcard(bitmap, "");
//        return  Drawable.createFromPath(path);
//    }

    Handler handler;
    public void setHtml(TextView text, String html) {
        if (handler == null) {
            handler = new Handler() {
                @Override
                public void handleMessage(Message msg) {
                    super.handleMessage(msg);
                    CharSequence charSequence = (CharSequence) msg.obj;
                    text.setText(charSequence);
                }
            };
        }
        new Thread(new Runnable() {
            Message msg = Message.obtain();
            @Override
            public void run() {
                Html.ImageGetter imageGetter = new Html.ImageGetter() {
                    @Override
                    public Drawable getDrawable(String source) {
                        Drawable drawable = DrawbleCacheUtils.getInstance().getCache(source);
                        if (drawable == null) {
                            drawable = getImageFromNetwork(source);
                        }
//                        Drawable drawable = getImageFromNetWork01(source);
                        setDrwableSize(drawable, windowWith);
                        return drawable;
                    }
                };
                CharSequence charSequence = Html.fromHtml(html, imageGetter, null);
                msg.what = 0x101;
                msg.obj = charSequence;
                handler.sendMessage(msg);
            }
        }).start();
    }

    private void setDrwableSize(Drawable drawable, int windowWith) {
        float w = windowWith - 30;
        float h = w / ((float) drawable.getIntrinsicWidth() / (float) drawable.getIntrinsicHeight());
        drawable.setBounds(0, 0, (int) w, (int) h);
    }
}
