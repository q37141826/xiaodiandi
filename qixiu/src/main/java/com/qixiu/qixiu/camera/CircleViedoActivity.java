package com.qixiu.qixiu.camera;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.hardware.Camera;
import android.media.MediaMetadataRetriever;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.github.rahatarmanahmed.cpv.CircularProgressView;
import com.qixiu.qixiu.R;
import com.qixiu.qixiu.utils.PictureCut;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class CircleViedoActivity extends Activity implements View.OnClickListener, MediaRecorder.OnErrorListener {

    public static final String FILE_PATH = "FILE_PATH";
    public static final String BITMAP = "BITMAP";


    private SurfaceView mSurfaceView;
    private SurfaceHolder mSurfaceHolder;
    private CircularProgressView mProgressBar;
    private Button shoot_button;
    private ImageView circle_light_btn;
    private ImageView circle_change_camera_btn;
    private TextView circle_camera_time;

    private MediaRecorder mMediaRecorder;
    private Camera mCamera;
    private Timer mTimer;// 计时器
    private OnRecordFinishListener mOnRecordFinishListener;// 录制完成回调接口
    private int mWidth;// 视频分辨率宽度
    private int mHeight;// 视频分辨率高度
    private boolean isOpenCamera;// 是否一开始就打开摄像头
    private int mRecordMaxTime;// 一次拍摄最长时间
    private int mTimeCount;// 时间计数
    private int mNumberOfCameras = 0;//手机摄像头的数量
    Bitmap bitmap;//图片缩略图
    private int screenWidth;
    public static File mVecordFile = null;// 文件
    private boolean isOpenFlash = false;
    private boolean isBackCamera = true;
    private int mbackCamera;
    private int mfrontCamera;

    private static final int MOVICE_SUCCESS = 1000;//录制完成
    private static final int MOVICE_FILE = 1001;//录制失败
    private int screenHeight;


    public static void start(Context context) {
        Intent intent = new Intent(context, CircleViedoActivity.class);
        context.startActivity(intent);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_super_video);
        init_datas();
        init_view();
        set_datas();
        init_event();
    }

    private void init_datas() {
        isOpenCamera = true;//默认一开始就打开相机
        mRecordMaxTime = 15;//设置录制的时间

        //获取手机摄像头的数量
        Camera.CameraInfo cameraInfo = new Camera.CameraInfo();
        mNumberOfCameras = Camera.getNumberOfCameras();
        for (int camidx = 0; camidx < mNumberOfCameras; camidx++) {
            Camera.getCameraInfo(camidx, cameraInfo);
            if (cameraInfo.facing == Camera.CameraInfo.CAMERA_FACING_BACK) {
                mbackCamera = camidx;
            } else if (cameraInfo.facing == Camera.CameraInfo.CAMERA_FACING_FRONT) {
                mfrontCamera = camidx;
            }
        }


    }

    private void init_view() {
        //获取屏幕的宽度
        screenWidth = getWindowManager().getDefaultDisplay().getWidth();
        screenHeight = getWindowManager().getDefaultDisplay().getHeight();
        mWidth = 640;
        mHeight = 480;


        mSurfaceView = (SurfaceView) findViewById(R.id.surfaceview);//预览界面
        mProgressBar = findViewById(R.id.progressBar);//进度条
        shoot_button = findViewById(R.id.shoot_button);//拍摄按钮
        circle_camera_time = (TextView) findViewById(R.id.circle_camera_time);
        circle_change_camera_btn = (ImageView) findViewById(R.id.circle_change_camera_btn);//切换摄像头
        circle_light_btn = (ImageView) findViewById(R.id.circle_light_btn);//开启闪光灯
        ViewGroup.LayoutParams params = mSurfaceView.getLayoutParams();
        params.height = (screenWidth * 4) / 3;
        params.width = screenWidth;
        mSurfaceView.setLayoutParams(params);


        ImageView top_model2_left_img = (ImageView) findViewById(R.id.top_model2_left_img);
        top_model2_left_img.setOnClickListener(this);
        ImageView top_model2_right_img = (ImageView) findViewById(R.id.top_model2_right_img);
        top_model2_right_img.setVisibility(View.INVISIBLE);
        TextView top_model2_txt = (TextView) findViewById(R.id.top_model2_txt);
        top_model2_txt.setText("视频录制");
    }


    private void set_datas() {
        mSurfaceHolder = mSurfaceView.getHolder();
        mProgressBar.setMaxProgress(mRecordMaxTime);// 设置进度条最大量
        mSurfaceHolder.setKeepScreenOn(true);//设置屏幕常亮
        mSurfaceHolder.addCallback(new CustomCallBack());
        mSurfaceHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
        circle_camera_time.setText("");
    }

    private void init_event() {
        circle_light_btn.setOnClickListener(this);
        circle_change_camera_btn.setOnClickListener(this);
        shoot_button.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    Log.e("action", "ACTION_DOWN");
                    record(new OnRecordFinishListener() {
                        @Override
                        public void onRecordFinish() {
                            //录制时间达到最大值
                            handler.sendEmptyMessage(MOVICE_SUCCESS);
                        }
                    });
                } else if (event.getAction() == MotionEvent.ACTION_UP || event.getAction() == MotionEvent.ACTION_CANCEL) {
                    Log.e("action", "ACTION" + event.getAction());
                    shoot_button.setEnabled(false);
                    if (mTimeCount > 1 && mTimeCount < 15) { //防止达到最大值up事件
                        //录制时间大于一秒
                        handler.sendEmptyMessage(MOVICE_SUCCESS);
                        shoot_button.setEnabled(true);
                    } else if (mTimeCount <= 1) {
                        //删除小于一秒的视频
                        if (getmVecordFile() != null) {
                            getmVecordFile().delete();
                            mVecordFile = null;
                        }
                        handler.sendEmptyMessage(MOVICE_FILE);
                    }
                }
                return true;
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mCamera != null) {
            freeCameraResource();
        }
        try {
            initCamera();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
//           mRecorderView.stop();
    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            //停止拍摄
            stopRecord();
            switch (msg.what) {
                case MOVICE_SUCCESS:
                    mProgressBar.setProgress(0);
                    PopVideoCode popVideoCode = new PopVideoCode(CircleViedoActivity.this);
                    popVideoCode.setConfirmListenner(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            finishActivity();
                        }
                    });
                    popVideoCode.setCancleListenner(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            getmVecordFile().delete();
                            finish();
                        }
                    });
                    popVideoCode.show();
                    break;
                case MOVICE_FILE:

                    mTimeCount = 0;
                    shoot_button.setEnabled(true);
                    mProgressBar.setProgress(0);
                    stop();
                    try {
                        initCamera();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    Toast.makeText(CircleViedoActivity.this, "视频录制时间太短", Toast.LENGTH_SHORT).show();
                    break;
            }

        }
    };

    private void finishActivity() {
        stop();
//        Bitmap bitmap = null;
//        if (getmVecordFile() != null) {
//            //得到文件 File类型
//            File mfile = getmVecordFile();
//            bitmap = getVideoThumbnail(mfile.toString());
//        }
//----------
//部分Android手机缩略图拉不到，找到一种解决办法不是太满意 有没有大神提供思路
//----------
        // VideoPlayerActivity.startActivity(this, mRecorderView.getVecordFile().toString());
        Intent intent = new Intent();
        intent.putExtra(FILE_PATH, mVecordFile.getPath());
//        intent.putExtra(BITMAP, bitmap);
        setResult(RESULT_OK, intent);
        finish();
    }

    @Override
    public void onError(MediaRecorder mr, int what, int extra) {
        try {
            if (mr != null)
                mr.reset();
        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.circle_light_btn) {//开启关闭闪光灯 默认关闭
            if (isOpenFlash) {
                isOpenFlash = false;
                circle_light_btn.setImageResource(R.drawable.camera_light);
            } else {
                isOpenFlash = true;
                circle_light_btn.setImageResource(R.drawable.camera_no_light);
            }
            try {
                initCamera();
            } catch (IOException e) {
                e.printStackTrace();
            }

        } else if (i == R.id.circle_change_camera_btn) {
            if (isBackCamera) {
                isBackCamera = false;
            } else {
                isBackCamera = true;
            }
            try {
                initCamera();
            } catch (IOException e) {
                e.printStackTrace();
            }

        } else if (i == R.id.top_model2_left_img) {
            stop();
            finish();

        }
    }

    private class CustomCallBack implements SurfaceHolder.Callback {
        @Override
        public void surfaceCreated(SurfaceHolder holder) {
            if (!isOpenCamera)
                return;
            try {
                initCamera();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

        }

        @Override
        public void surfaceDestroyed(SurfaceHolder holder) {

        }
    }

    /**
     * 初始化摄像头
     */
    private void initCamera() throws IOException {
        if (mCamera != null) {
            freeCameraResource();
        }
        try {
            if (isBackCamera) {
                mCamera = Camera.open(mbackCamera);//打开后摄像头
                setCameraParams(isOpenFlash);
            } else {
                mCamera = Camera.open(mfrontCamera);//打开前摄像头
            }
        } catch (Exception e) {
            e.printStackTrace();
            freeCameraResource();
        }
        if (mCamera == null)
            return;

        mCamera.setDisplayOrientation(90);
        mCamera.setPreviewDisplay(mSurfaceHolder);
        mCamera.startPreview();
        mCamera.unlock();
    }


    /**
     * 设置摄像头为竖屏
     */
    private void setCameraParams(Boolean isOpenFlash) {
        if (mCamera != null) {
            Camera.Parameters params = mCamera.getParameters();
            //获取预览的各种分辨率
            List<Camera.Size> supportedPreviewSizes = params.getSupportedPreviewSizes();
            params.set("orientation", "portrait");//竖屏录制
            params.setPreviewSize(mWidth, mHeight);//默认640*480
            params.setFocusMode(Camera.Parameters.FOCUS_MODE_CONTINUOUS_PICTURE);//持续对焦
            if (isBackCamera) {
                if (isOpenFlash) {
                    //开启闪光灯
                    params.setFlashMode(Camera.Parameters.FLASH_MODE_TORCH);

                } else {
                    //关闭闪光灯
                    params.setFlashMode(Camera.Parameters.FLASH_MODE_OFF);
                }

            } else {

            }
            mCamera.setParameters(params);
        }
    }


    /**
     * 释放摄像头资源
     */
    private void freeCameraResource() {
        if (mCamera != null) {
            mCamera.setPreviewCallback(null);
            mCamera.stopPreview();
            mCamera.lock();
            mCamera.release();
            mCamera = null;
        }
    }

    private void createRecordDir() {
        File sampleDir = new File(Environment.getExternalStorageDirectory() + File.separator + "atomimg/video/");
        PictureCut.deleteFileWith(sampleDir, "recording");
        if (!sampleDir.exists()) {
            sampleDir.mkdirs();
        }
        File vecordDir = sampleDir;
        // 创建文件
//        try {
//            mVecordFile = File.createTempFile("recording", ".mp4", vecordDir);//mp4格式
//        } catch (IOException e) {
//        }
        mVecordFile = new File(sampleDir.getAbsolutePath(),new Date().getTime()+".mp4");//mp4格式
    }

    /**
     * 初始化
     */
    private void initRecord() throws IOException {
        mMediaRecorder = new MediaRecorder();
        mMediaRecorder.reset();
        if (mCamera != null)
            mMediaRecorder.setCamera(mCamera);
        mMediaRecorder.setOnErrorListener(this);
        mMediaRecorder.setPreviewDisplay(mSurfaceHolder.getSurface());
        mMediaRecorder.setVideoSource(MediaRecorder.VideoSource.CAMERA);// 视频源
        mMediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);// 音频源
        mMediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.MPEG_4);// 视频输出格式
        mMediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AAC);// 音频格式
        mMediaRecorder.setVideoSize(mWidth, mHeight);// 设置分辨率：
        // mMediaRecorder.setVideoFrameRate(16);// 这个我把它去掉了，感觉没什么用
        mMediaRecorder.setVideoEncodingBitRate(1 * 1024 * 1024);// 设置帧频率，然后就清晰了
        if (isBackCamera) {
            mMediaRecorder.setOrientationHint(90);// 输出旋转90度，保持竖屏录制
        } else {
            mMediaRecorder.setOrientationHint(270);
        }
        mMediaRecorder.setVideoEncoder(MediaRecorder.VideoEncoder.H264);// 视频录制格式


//----------


/**
 这里是配置摄像头的重点 因为我们录制视频需要适配IOS开发和Android不同版本的手机 而IOS和Android通配的视频格式不多 我们这里采用H264格式 它的兼容范围更广阔 主要解决的就是部分OPPO手机不适配问题
 */

//
//----------


//        mMediaRecorder.setMaxDuration(Constant.MAXVEDIOTIME * 1000);
        mMediaRecorder.setOutputFile(mVecordFile.getAbsolutePath());
        mMediaRecorder.prepare();
        try {
            mMediaRecorder.start();
        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (RuntimeException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 录制完成回调接口
     */
    public interface OnRecordFinishListener {
        public void onRecordFinish();
    }

    /**
     * 录制
     *
     * @param onRecordFinishListener
     */
    public void record(final OnRecordFinishListener onRecordFinishListener) {
        this.mOnRecordFinishListener = onRecordFinishListener;
        createRecordDir();//创建目录
//        mProgressBar.startAnimation();//start没看出效果
        try {
            if (!isOpenCamera)// 如果未打开摄像头，则打开
                initCamera();//初始化摄像头
            initRecord();//初始化录制参数
            mTimeCount = 0;// 时间计数器重新赋值

            mTimer = new Timer();//创建一个定时器

            mTimer.schedule(new TimerTask() {
                @Override
                public void run() {
                    // TODO Auto-generated method stub
                    mTimeCount++;
                    //  handler.sendEmptyMessage(TIME_CHANGW);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            mProgressBar.setProgress(mTimeCount);// 设置进度条
                            circle_camera_time.setText(mTimeCount + "″");
                        }
                    });
                    if (mTimeCount == mRecordMaxTime) {// 达到指定时间，停止拍摄
                        //录制完成调用录制回调接口
                        if (mOnRecordFinishListener != null)
                            mOnRecordFinishListener.onRecordFinish();
                    }
                }
            }, 0, 1000);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 停止拍摄
     */
    public void stop() {
        stopRecord();
        releaseRecord();
        freeCameraResource();
    }

    /**
     * 停止录制
     */
    public void stopRecord() {
        mProgressBar.resetAnimation();
        circle_camera_time.setText("");
        if (mTimer != null)
            mTimer.cancel();
        if (mMediaRecorder != null) {
            // 设置后不会崩
            mMediaRecorder.setOnErrorListener(null);
            mMediaRecorder.setPreviewDisplay(null);
            try {
                mMediaRecorder.stop();
            } catch (IllegalStateException e) {
                e.printStackTrace();
            } catch (RuntimeException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 获取的视频的位置
     *
     * @return
     */
    public File getmVecordFile() {
        return mVecordFile;
    }

    /**
     * 释放资源
     */
    private void releaseRecord() {
        if (mMediaRecorder != null) {
            mMediaRecorder.setOnErrorListener(null);
            try {
                mMediaRecorder.release();
            } catch (IllegalStateException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        mMediaRecorder = null;
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK)) {
            stop();
            finish();
            return false;
        } else {
            return super.onKeyDown(keyCode, event);
        }

    }

    /**
     * 获取视频缩略图
     *
     * @param filePath
     * @return
     */
    public Bitmap getVideoThumbnail(String filePath) {
        Bitmap bitmap = null;
        MediaMetadataRetriever retriever = new MediaMetadataRetriever();
        try {
            retriever.setDataSource(filePath);
            bitmap = retriever.getFrameAtTime();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (RuntimeException e) {
            e.printStackTrace();
        } finally {
            try {
                retriever.release();
            } catch (RuntimeException e) {
                e.printStackTrace();
            }
        }
        return bitmap;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (bitmap != null) {
            try {
                bitmap.recycle();
            } catch (Exception e) {
                e.printStackTrace();
            }
            bitmap = null;
        }

    }
}
