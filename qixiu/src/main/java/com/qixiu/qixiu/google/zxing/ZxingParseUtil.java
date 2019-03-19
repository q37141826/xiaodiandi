package com.qixiu.qixiu.google.zxing;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.google.zxing.BinaryBitmap;
import com.google.zxing.DecodeHintType;
import com.google.zxing.RGBLuminanceSource;
import com.google.zxing.Result;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.multi.qrcode.QRCodeMultiReader;
import com.google.zxing.qrcode.QRCodeReader;

import java.util.Hashtable;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class ZxingParseUtil {
    private static final ExecutorService executorService = Executors.newFixedThreadPool(1);
    private static final String DATA = "DATA";

    public static void getParseResult(Activity activity, String path, QrParseInterf listenner) {
        final Handler handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        String result = msg.getData().getString(DATA);
                        listenner.result(result);
                    }
                });
            }
        };
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                QRResult decode = decode(path);
                Message message = new Message();
                Bundle bundle = new Bundle();
                if (decode == null) {
                    bundle.putString(DATA, "");
                } else {
                    bundle.putString(DATA, decode.getTxt());
                }
                message.setData(bundle);
                handler.handleMessage(message);
            }
        });
    }


    //解析二维码图片,返回结果封装在Result对象中
    private static Result parseQRcodeBitmap(String bitmapPath) {
//解析转换类型UTF-8
        Hashtable<DecodeHintType, String> hints = new Hashtable<DecodeHintType, String>();
//        hints.put(DecodeHintType.CHARACTER_SET, "utf-8");//用这个就错误了
        // 解码设置编码方式为：utf-8，
        hints.put(DecodeHintType.CHARACTER_SET, "UTF-8");
//优化精度
        hints.put(DecodeHintType.TRY_HARDER, String.valueOf(Boolean.TRUE));
//复杂模式，开启PURE_BARCODE模式
        hints.put(DecodeHintType.PURE_BARCODE, String.valueOf(Boolean.TRUE));

//获取到待解析的图片
        BitmapFactory.Options options = new BitmapFactory.Options();
//如果我们把inJustDecodeBounds设为true，那么BitmapFactory.decodeFile(String path, Options opt)
//并不会真的返回一个Bitmap给你，它仅仅会把它的宽，高取回来给你
        options.inJustDecodeBounds = true;
//此时的bitmap是null，这段代码之后，options.outWidth 和 options.outHeight就是我们想要的宽和高了
        Bitmap bitmap = BitmapFactory.decodeFile(bitmapPath, options);
//我们现在想取出来的图片的边长（二维码图片是正方形的）设置为400像素
/**
 options.outHeight = 400;
 options.outWidth = 400;
 options.inJustDecodeBounds = false;
 bitmap = BitmapFactory.decodeFile(bitmapPath, options);
 */
//以上这种做法，虽然把bitmap限定到了我们要的大小，但是并没有节约内存，如果要节约内存，我们还需要使用inSimpleSize这个属性
        options.inSampleSize = options.outHeight / 400;
        if (options.inSampleSize <= 0) {
            options.inSampleSize = 1; //防止其值小于或等于0
        }
/**
 * 辅助节约内存设置
 *
 * options.inPreferredConfig = Bitmap.Config.ARGB_4444; // 默认是Bitmap.Config.ARGB_8888
 * options.inPurgeable = true;
 * options.inInputShareable = true;
 */

        options.inPreferredConfig = Bitmap.Config.ARGB_4444; // 默认是Bitmap.Config.ARGB_8888
        options.inPurgeable = true;
        options.inInputShareable = true;


        options.inJustDecodeBounds = false;
        bitmap = BitmapFactory.decodeFile(bitmapPath, options);
        int[] data = new int[bitmap.getWidth() * bitmap.getHeight()];

//新建一个RGBLuminanceSource对象，将bitmap图片传给此对象
        RGBLuminanceSource rgbLuminanceSource = new RGBLuminanceSource(bitmap.getWidth(), bitmap.getHeight(), data);
//将图片转换成二进制图片
        BinaryBitmap binaryBitmap = new BinaryBitmap(new HybridBinarizer(rgbLuminanceSource));
//初始化解析对象
        QRCodeReader reader = new QRCodeMultiReader();
//开始解析
        Result result = null;
        try {
            result = reader.decode(binaryBitmap, hints);
//            result =new MultiFormatReader().decode(new BinaryBitmap(new HybridBinarizer(rgbLuminanceSource)), hints);
        } catch (Exception e) {
// TODO: handle exception
            e.printStackTrace();
        }

        return result;
    }


    public interface QrParseInterf<T> {
        void result(T t);
    }


    public static QRResult decode(String path) {
        try {
            if (null == path) {
                return new QRResult("得到的文件不存在！", 300);
            }
            //获取到待解析的图片
            BitmapFactory.Options options = new BitmapFactory.Options();
//如果我们把inJustDecodeBounds设为true，那么BitmapFactory.decodeFile(String path, Options opt)
//并不会真的返回一个Bitmap给你，它仅仅会把它的宽，高取回来给你
            options.inJustDecodeBounds = true;
//我们现在想取出来的图片的边长（二维码图片是正方形的）设置为400像素
/**
 options.outHeight = 400;
 options.outWidth = 400;
 options.inJustDecodeBounds = false;
 bitmap = BitmapFactory.decodeFile(bitmapPath, options);
 */
//以上这种做法，虽然把bitmap限定到了我们要的大小，但是并没有节约内存，如果要节约内存，我们还需要使用inSimpleSize这个属性
            options.inSampleSize = options.outHeight / 400;
            if (options.inSampleSize <= 0) {
                options.inSampleSize = 1; //防止其值小于或等于0
            }
/**
 * 辅助节约内存设置
 *
 * options.inPreferredConfig = Bitmap.Config.ARGB_4444; // 默认是Bitmap.Config.ARGB_8888
 * options.inPurgeable = true;
 * options.inInputShareable = true;*/

            options.inPreferredConfig = Bitmap.Config.ARGB_4444; // 默认是Bitmap.Config.ARGB_8888
            options.inPurgeable = true;
            options.inInputShareable = true;
            options.inJustDecodeBounds = false;

//此时的bitmap是null，这段代码之后，options.outWidth 和 options.outHeight就是我们想要的宽和高了
            Bitmap bitmaps = BitmapFactory.decodeFile(path, options);
            int[] data = new int[bitmaps.getWidth() * bitmaps.getHeight()];
//新建一个RGBLuminanceSource对象，将bitmap图片传给此对象
            RGBLuminanceSource rgbLuminanceSource = new RGBLuminanceSource(bitmaps.getWidth(), bitmaps.getHeight(), data);
            BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(rgbLuminanceSource));
            Map<DecodeHintType, Object> hints = new LinkedHashMap<DecodeHintType, Object>();
// 解码设置编码方式为：utf-8，
            hints.put(DecodeHintType.CHARACTER_SET, "UTF-8");
//优化精度
            hints.put(DecodeHintType.TRY_HARDER, Boolean.TRUE);
//复杂模式，开启PURE_BARCODE模式
            hints.put(DecodeHintType.PURE_BARCODE, Boolean.TRUE);
            Result result = new QRCodeMultiReader().decode(bitmap, hints);
            String txt = result.getText();
            return new QRResult("成功解码！", 200, txt);
        } catch (Exception e) {
            Log.e("", "解码失败。", e);
            return new QRResult("解码失败，请确认的你二维码是否正确，或者图片有多个二维码！", 500);
        }
    }

    /**
     * 返回值处理
     *
     * @author zhou-baicheng
     */
    public static class QRResult {
        public QRResult(String message, int status) {
            this.message = message;
            this.status = status;
            this.txt = "";
        }

        public QRResult(String message, int status, String txt) {
            this.message = message;
            this.status = status;
            this.txt = txt;
        }

        //解码内容
        private String txt;
        //返回的消息内容
        private String message;
        //返回的状态码，200：成功，500：错误
        private int status;

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getTxt() {
            return txt;
        }

        public void setTxt(String txt) {
            this.txt = txt;
        }
    }


}
