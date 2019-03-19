package com.qixiu.qixiu.google.zxing.client;

import android.graphics.Bitmap;

import com.google.zxing.Result;

/**
 * 二维码解码回调
 * <p>
 * Created by liyujiang on 2017/7/19 11:39.
 */
public interface DecodeCallback {

    void onDecoded(Result rawResult, Bitmap barcode);

}
