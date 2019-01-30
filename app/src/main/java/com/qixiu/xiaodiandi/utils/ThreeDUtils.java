package com.qixiu.xiaodiandi.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

import com.qixiu.xiaodiandi.R;

/**
 * Created by my on 2019/1/4.
 */

public class ThreeDUtils {


    public static Bitmap create3DImg(Context context) {
        Bitmap bm = BitmapFactory.decodeResource(context.getResources(), R.drawable.timg);
//        Bitmap bm = getBitmapByWidth(R.drawable.bb, 2000, 0);
        int w = bm.getWidth();
        int h = bm.getHeight();

        Bitmap bitmap1 = Bitmap.createBitmap(w/2, h, Bitmap.Config.RGB_565);

        Canvas canvas = new Canvas(bitmap1);
        Rect mSrcRect = new Rect(0, 0, w/2, h);
        Rect mDestRect = new Rect(0, 0, w/2, h);
        Paint paint = new Paint();
        paint.setAlpha(80);
        canvas.drawBitmap(bm, mSrcRect, mDestRect, null);
        int ww = 1;
        for (int i = 0; i < (((w / 2)/ww)/2); i++) {
            Rect mRightSrcRect = new Rect((w / 2)+i*ww*2, 0, (w / 2)+i*ww*2+ww, h);
            Rect mDestRect2 = new Rect((i+1)*ww*2, 0, (i+1)*ww*2+ww, h);
            canvas.drawBitmap(bm, mRightSrcRect, mDestRect2, null);
        }
        return bitmap1;
    }

}
