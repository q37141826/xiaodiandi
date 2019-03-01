package com.qixiu.qixiu.utils.video;

import android.app.Activity;
import android.graphics.Bitmap;
import android.media.MediaMetadataRetriever;
import android.media.ThumbnailUtils;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;

import com.qixiu.qixiu.utils.ImgHelper;
import com.qixiu.qixiu.utils.PictureCut;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class VideoThumbUtils {


    /**
     * 获取视频文件截图
     *
     * @param path 视频文件的路径
     * @return Bitmap 返回获取的Bitmap
     */

    private static Bitmap getVideoThumb(String path) {

        MediaMetadataRetriever media = new MediaMetadataRetriever();

        media.setDataSource(path);

        return media.getFrameAtTime();

    }


//    private static Bitmap getFirstImage(){
//        //获取第一帧原尺寸图片
//        mmrc.getFrameAtTime();
//
////获取指定位置的原尺寸图片 注意这里传的timeUs是微秒
//        mmrc.getFrameAtTime(timeUs, option);
//
////获取指定位置指定宽高的缩略图
//        mmrc.getScaledFrameAtTime(timeUs, MediaMetadataRetrieverCompat.OPTION_CLOSEST, width, height);
//
////获取指定位置指定宽高并且旋转的缩略图
//        mmrc.getScaledFrameAtTime(timeUs, MediaMetadataRetrieverCompat.OPTION_CLOSEST, width, height, rotate);
//
//    }


    /**
     * 获取视频的缩略图
     * 先通过ThumbnailUtils来创建一个视频的缩略图，然后再利用ThumbnailUtils来生成指定大小的缩略图。
     * 如果想要的缩略图的宽和高都小于MICRO_KIND，则类型要使用MICRO_KIND作为kind的值，这样会节省内存。
     *
     * @param videoPath 视频的路径
     * @param width     指定输出视频缩略图的宽度
     * @param height    指定输出视频缩略图的高度度
     * @param kind      参照MediaStore.Images(Video).Thumbnails类中的常量MINI_KIND和MICRO_KIND。
     *                  其中，MINI_KIND: 512 x 384，MICRO_KIND: 96 x 96
     * @return 指定大小的视频缩略图
     */
    private static Bitmap getVideoThumbnail(String videoPath, int width, int height, int kind) {
        Bitmap bitmap = null;
        // 获取视频的缩略图
        bitmap = ThumbnailUtils.createVideoThumbnail(videoPath, kind); //調用ThumbnailUtils類的靜態方法createVideoThumbnail獲取視頻的截圖；
        if (bitmap != null) {
            bitmap = ThumbnailUtils.extractThumbnail(bitmap, width, height,
                    ThumbnailUtils.OPTIONS_RECYCLE_INPUT);//調用ThumbnailUtils類的靜態方法extractThumbnail將原圖片（即上方截取的圖片）轉化為指定大小；
        }
        return bitmap;
    }

    public interface ResultListenner {
        void result(String path);
    }


    public static void getVideoThumbnail(String videoPath, ResultListenner listenner) {
        ExecutorService executorService = Executors.newFixedThreadPool(1);
        Handler handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                if (!TextUtils.isEmpty((String) msg.obj)) {
                    listenner.result((String) msg.obj);
                }
            }
        };
        executorService.execute(new Runnable() {
            @Override
            public void run() {
//                MediaMetadataRetriever retr = new MediaMetadataRetriever();
//                retr.setDataSource(videoPath);
//                String height = retr.extractMetadata(MediaMetadataRetriever.METADATA_KEY_VIDEO_HEIGHT); // 视频高度
//                String width = retr.extractMetadata(MediaMetadataRetriever.METADATA_KEY_VIDEO_WIDTH); // 视频宽度
//                Bitmap videoThumbnail = getVideoThumbnail(videoPath, Integer.parseInt(width), Integer.parseInt(height), MINI_KIND);
                Bitmap videoThumbnail = getVideoThumb(videoPath);
                String path = PictureCut.saveBitmapToSdcard(videoThumbnail);
                Message mess = handler.obtainMessage(0, path);
                handler.sendMessage(mess);
                handler.handleMessage(mess);
            }
        });
    }

    static final ExecutorService executorService = Executors.newFixedThreadPool(1);

    public static void getVideoThumbnailBase64(Activity activity, String videoPath, ResultListenner listenner) {
        final Handler handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (!TextUtils.isEmpty((String) msg.obj)) {
                            listenner.result((String) msg.obj);
                        }
                    }
                });
            }
        };
        executorService.execute(new Runnable() {
            @Override
            public void run() {
//                MediaMetadataRetriever retr = new MediaMetadataRetriever();
//                retr.setDataSource(videoPath);
//                String height = retr.extractMetadata(MediaMetadataRetriever.METADATA_KEY_VIDEO_HEIGHT); // 视频高度
//                String width = retr.extractMetadata(MediaMetadataRetriever.METADATA_KEY_VIDEO_WIDTH); // 视频宽度
//                Bitmap videoThumbnail = getVideoThumbnail(videoPath, Integer.parseInt(width), Integer.parseInt(height), MINI_KIND);
                Bitmap videoThumbnail = getVideoThumb(videoPath);
                String base64 = ImgHelper.bitmap2StrByBase64NoCompress(videoThumbnail);
                Message mess = handler.obtainMessage(0, base64);
                handler.sendMessage(mess);
                handler.handleMessage(mess);
            }
        });

    }

}
