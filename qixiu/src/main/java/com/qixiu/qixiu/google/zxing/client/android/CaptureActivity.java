/*
 * Copyright (C) 2008 ZXing authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.qixiu.qixiu.google.zxing.client.android;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.preference.PreferenceManager;
import android.text.ClipboardManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.DecodeHintType;
import com.google.zxing.Result;
import com.google.zxing.ResultMetadataType;
import com.google.zxing.ResultPoint;
import com.qixiu.qixiu.R;
import com.qixiu.qixiu.google.zxing.client.android.camera.CameraManager;
import com.qixiu.qixiu.google.zxing.client.android.history.HistoryItem;
import com.qixiu.qixiu.google.zxing.client.android.history.HistoryManager;
import com.qixiu.qixiu.google.zxing.client.android.result.ResultHandler;
import com.qixiu.qixiu.google.zxing.client.android.result.ResultHandlerFactory;
import com.qixiu.qixiu.titleview.TitleView;
import com.qixiu.wigit.show_dialog.ArshowContextUtil;
import com.qixiu.wigit.zprogress.ZProgressHUD;

import org.greenrobot.eventbus.EventBus;

import java.io.IOException;
import java.util.Collection;
import java.util.EnumSet;
import java.util.Map;
import java.util.Set;

/**
 * This activity opens the camera and does the actual scanning on a background thread. It draws a
 * viewfinder to activity_zxing_help the user place the barcode correctly, shows feedback as the
 * image processing is happening, and then overlays the results when a scan is successful.
 *
 * @author dswitkin@google.com (Daniel Switkin)
 * @author Sean Owen
 */
public final class CaptureActivity extends Activity
        implements SurfaceHolder.Callback {

    private static final String ERROR_CODE = "未知的二维码哦，请试着先扫描产品二维码吧";
    private static final String activationNewReg = "^[A-Za-z0-9]{16}$";
    private static final String activationOldReg = "^[A-Za-z0-9]{10}$";
    private static final String TAG = CaptureActivity.class.getSimpleName();

    private static final long DEFAULT_INTENT_RESULT_DURATION_MS = 1500L;
    private static final long BULK_MODE_SCAN_DELAY_MS = 1000L;

    private static final String PACKAGE_NAME = "com.google.zxing.client.android";
    private static final String PRODUCT_SEARCH_URL_PREFIX = "http://www.google";
    private static final String PRODUCT_SEARCH_URL_SUFFIX = "/m/products/scan";
    private static final String[] ZXING_URLS = {"http://zxing.appspot.com/scan", "zxing://scan/"};

    public static final int HISTORY_REQUEST_CODE = 0x0000bacc;
    public static final String BROADCAST_ZXING = "BROADCAST_ZXING";
    public static final String ZXING_VALUE = "ZXING_VALUE";
    public static final int ZXING_INTENT = 0x1001;


    private static final Set<ResultMetadataType> DISPLAYABLE_METADATA_TYPES =
            EnumSet.of(ResultMetadataType.ISSUE_NUMBER, ResultMetadataType.SUGGESTED_PRICE,
                    ResultMetadataType.ERROR_CORRECTION_LEVEL, ResultMetadataType.POSSIBLE_COUNTRY);

    private CameraManager cameraManager;
    private CaptureActivityHandler handler;
    private Result savedResultToShow;
    private ViewfinderView viewfinderView;
    private Result lastResult;
    private boolean hasSurface;
    private boolean copyToClipboard;
    private IntentSource source;
    private String sourceUrl;
    private ScanFromWebPageManager scanFromWebPageManager;
    private Collection<BarcodeFormat> decodeFormats;
    private Map<DecodeHintType, ?> decodeHints;
    private String characterSet;
    private HistoryManager historyManager;
    private InactivityTimer inactivityTimer;
    private BeepManager beepManager;
    private AmbientLightManager ambientLightManager;
    /**
     * 是否手动
     */
    private int type;
    private TextView tv_scan_hint;
    private ZProgressHUD zProgressHUD;

    ViewfinderView getViewfinderView() {
        return viewfinderView;
    }

    public Handler getHandler() {
        return handler;
    }

    CameraManager getCameraManager() {
        return cameraManager;
    }

    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);

        Window window = getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        setContentView(R.layout.activity_zxing_capture);

        hasSurface = false;
        historyManager = new HistoryManager(this);
        historyManager.trimHistory();
        inactivityTimer = new InactivityTimer(this);
        beepManager = new BeepManager(this);
        ambientLightManager = new AmbientLightManager(this);

        PreferenceManager.setDefaultValues(this, R.xml.preferences, false);

        TitleView titleView = new TitleView(this);
        RelativeLayout vg_title = (RelativeLayout) findViewById(R.id.vg_title);
        vg_title.addView(titleView.getView());
        titleView.setTitle(R.string.scan_title);
        titleView.setBackListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    /**
     * 初始数据
     */
    private void init() {
        String typeName = getIntent().getStringExtra("typeName");
        type = getIntent().getIntExtra("type", -1);
        if (!TextUtils.isEmpty(typeName)) {
            tv_scan_hint.setText(getString(R.string.scan_hint));
            //// TODO: 2017/8/21 按照需求进行设置
            tv_scan_hint.setVisibility(View.GONE);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        // CameraManager must be initialized here, not in onCreate(). This is necessary because we don't
        // want to open the camera driver and measure the screen size if we're going to show the activity_zxing_help on
        // first launch. That led to bugs where the scanning rectangle was the wrong size and partially
        // off screen.
        cameraManager = new CameraManager(getApplication());

        viewfinderView = (ViewfinderView) findViewById(R.id.viewfinder_view);
        viewfinderView.setCameraManager(cameraManager);

        tv_scan_hint = (TextView) findViewById(R.id.tv_scan_hint);
//        Toolbar toolbar_capture = (Toolbar) findViewById(R.id.toolbar_capture);
//        toolbar_capture.setNavigationOnClickListener(new View.OnClickListener()
//        {
//            @Override
//            public void onClick(View v)
//            {
//                finish();
//            }
//        });
        zProgressHUD = new ZProgressHUD(this);

        handler = null;
        lastResult = null;

        resetStatusView();

        SurfaceView surfaceView = (SurfaceView) findViewById(R.id.preview_view);
        SurfaceHolder surfaceHolder = surfaceView.getHolder();
        if (hasSurface) {
            // The activity was paused but not stopped, so the surface still exists. Therefore
            // surfaceCreated() won't be called, so init the camera here.
            initCamera(surfaceHolder);
        } else {
            // Install the callback and wait for surfaceCreated() to init the camera.
            surfaceHolder.addCallback(this);
            surfaceHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
        }

        beepManager.updatePrefs();
        ambientLightManager.start(cameraManager);

        inactivityTimer.onResume();

        Intent intent = getIntent();

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        copyToClipboard = prefs.getBoolean(PreferencesActivity.KEY_COPY_TO_CLIPBOARD, true) &&
                (intent == null || intent.getBooleanExtra(Intents.Scan.SAVE_HISTORY, true));

        source = IntentSource.NONE;
        decodeFormats = null;
        characterSet = null;

        if (intent != null) {

            String action = intent.getAction();
            String dataString = intent.getDataString();

            if (Intents.Scan.ACTION.equals(action)) {
                // Scan the formats the intent requested, and return the result to the calling activity.
                source = IntentSource.NATIVE_APP_INTENT;
                decodeFormats = DecodeFormatManager.parseDecodeFormats(intent);
                decodeHints = DecodeHintManager.parseDecodeHints(intent);

                if (intent.hasExtra(Intents.Scan.WIDTH) && intent.hasExtra(Intents.Scan.HEIGHT)) {
                    int width = intent.getIntExtra(Intents.Scan.WIDTH, 0);
                    int height = intent.getIntExtra(Intents.Scan.HEIGHT, 0);
                    if (width > 0 && height > 0) {
                        cameraManager.setManualFramingRect(width, height);
                    }
                }
            } else if (dataString != null &&
                    dataString.contains(PRODUCT_SEARCH_URL_PREFIX) &&
                    dataString.contains(PRODUCT_SEARCH_URL_SUFFIX)) {

                // Scan only products and send the result to mobile Product Search.
                source = IntentSource.PRODUCT_SEARCH_LINK;
                sourceUrl = dataString;
                decodeFormats = DecodeFormatManager.PRODUCT_FORMATS;

            } else if (isZXingURL(dataString)) {

                // Scan formats requested in query string (all formats if none specified).
                // If a return URL is specified, send the results there. Otherwise, handle it ourselves.
                source = IntentSource.ZXING_LINK;
                sourceUrl = dataString;
                Uri inputUri = Uri.parse(dataString);
                scanFromWebPageManager = new ScanFromWebPageManager(inputUri);
                decodeFormats = DecodeFormatManager.parseDecodeFormats(inputUri);
                // Allow a sub-set of the hints to be specified by the caller.
                decodeHints = DecodeHintManager.parseDecodeHints(inputUri);

            }

            characterSet = intent.getStringExtra(Intents.Scan.CHARACTER_SET);

        }
        init();
    }

    private static boolean isZXingURL(String dataString) {
        if (dataString == null) {
            return false;
        }
        for (String url : ZXING_URLS) {
            if (dataString.startsWith(url)) {
                return true;
            }
        }
        return false;
    }

    @Override
    protected void onPause() {
        if (handler != null) {
            handler.quitSynchronously();
            handler = null;
        }
        inactivityTimer.onPause();
        ambientLightManager.stop();
        cameraManager.closeDriver();
        if (!hasSurface) {
            SurfaceView surfaceView = (SurfaceView) findViewById(R.id.preview_view);
            SurfaceHolder surfaceHolder = surfaceView.getHolder();
            surfaceHolder.removeCallback(this);
        }
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        inactivityTimer.shutdown();
        super.onDestroy();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        switch (keyCode) {
            case KeyEvent.KEYCODE_BACK:
                if (source == IntentSource.NATIVE_APP_INTENT) {
                    setResult(RESULT_CANCELED);
                    finish();
                    return true;
                }
                if ((source == IntentSource.NONE || source == IntentSource.ZXING_LINK) &&
                        lastResult != null) {
                    restartPreviewAfterDelay(0L);
                    return true;
                }
                break;
            case KeyEvent.KEYCODE_FOCUS:
            case KeyEvent.KEYCODE_CAMERA:
                // Handle these events so they don't launch the Camera app
                return true;
            // Use volume up/down to turn on light
            case KeyEvent.KEYCODE_VOLUME_DOWN:
                cameraManager.setTorch(false);
                return true;
            case KeyEvent.KEYCODE_VOLUME_UP:
                cameraManager.setTorch(true);
                return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        if (resultCode == RESULT_OK) {
            String result = intent.getExtras().getString("result");


            if (requestCode == HISTORY_REQUEST_CODE) {
                int itemNumber = intent.getIntExtra(Intents.History.ITEM_NUMBER, -1);
                if (itemNumber >= 0) {
                    HistoryItem historyItem = historyManager.buildHistoryItem(itemNumber);
                    decodeOrStoreSavedBitmap(historyItem.getResult());
                }
            }
        }
    }

    /**
     * 解码或保存图片
     *
     * @param result
     */
    private void decodeOrStoreSavedBitmap(Result result) {
        // Bitmap isn't used yet -- will be used soon
        if (handler == null) {
            savedResultToShow = result;
        } else {
            if (result != null) {
                savedResultToShow = result;
            }
            if (savedResultToShow != null) {
                Message message = Message.obtain(handler, R.id.decode_succeeded, savedResultToShow);
                handler.sendMessage(message);
            }
            savedResultToShow = null;
        }
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        if (holder == null) {
            Log.e(TAG, "*** WARNING *** surfaceCreated() gave us a null surface!");
        }
        if (!hasSurface) {
            hasSurface = true;
            initCamera(holder);
        }
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        hasSurface = false;
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    /**
     * A valid barcode has been found, so give an indication of success and show the results.
     *
     * @param rawResult   The contents of the barcode.
     * @param scaleFactor amount by which thumbnail was scaled
     * @param barcode     A greyscale bitmap of the camera data which was decoded.
     */
    public void handleDecode(Result rawResult, Bitmap barcode, float scaleFactor) {
        Log.e("zxing", rawResult.toString());
//        Intent intent =new Intent();
//        intent.setAction(BROADCAST_ZXING);
//        intent.putExtra(ZXING_VALUE,rawResult.toString());
//        sendBroadcast(intent);//发送广播
        setFinish(rawResult.toString());
        EventBus.getDefault().post(rawResult.toString());//本质上都是广播，没有集成eventbus的用上面的原始方法
        inactivityTimer.onActivity();
        lastResult = rawResult;
        ResultHandler resultHandler = ResultHandlerFactory.makeResultHandler(this, rawResult);

        boolean fromLiveScan = barcode != null;
        if (fromLiveScan) {
            historyManager.addHistoryItem(rawResult, resultHandler);
            // Then not from history, so beep/vibrate and we have an image to draw on
            beepManager.playBeepSoundAndVibrate();
            drawResultPoints(barcode, scaleFactor, rawResult);
        }

        switch (source) {
            case NATIVE_APP_INTENT:
            case PRODUCT_SEARCH_LINK:
                handleDecodeExternally(rawResult, resultHandler, barcode);
                break;
            case ZXING_LINK:
                if (scanFromWebPageManager == null || !scanFromWebPageManager.isScanFromWebPage()) {
                    handleDecodeInternally(rawResult, resultHandler, barcode);
                } else {
                    handleDecodeExternally(rawResult, resultHandler, barcode);
                }
                break;
            case NONE:
                SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
                if (fromLiveScan && prefs.getBoolean(PreferencesActivity.KEY_BULK_MODE, false)) {
                    String message =
                            getResources().getString(R.string.msg_bulk_mode_scanned) + " (" +
                                    rawResult.getText() + ')';
                    Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
                    // Wait a moment or else it will scan the same barcode continuously about 3 times
                    restartPreviewAfterDelay(BULK_MODE_SCAN_DELAY_MS);
                } else {
                    handleDecodeInternally(rawResult, resultHandler, barcode);
                }
                break;
        }
    }

    /**
     * Superimpose a line for 1D or dots for 2D to highlight the key features of the barcode.
     *
     * @param barcode     A bitmap of the captured image.
     * @param scaleFactor amount by which thumbnail was scaled
     * @param rawResult   The decoded results which contains the points to draw.
     */
    private void drawResultPoints(Bitmap barcode, float scaleFactor, Result rawResult) {
        ResultPoint[] points = rawResult.getResultPoints();
        if (points != null && points.length > 0) {
            Canvas canvas = new Canvas(barcode);
            Paint paint = new Paint();
            paint.setColor(getResources().getColor(R.color.result_points));
            if (points.length == 2) {
                paint.setStrokeWidth(4.0f);
                drawLine(canvas, paint, points[0], points[1], scaleFactor);
            } else if (points.length == 4 && (rawResult.getBarcodeFormat() == BarcodeFormat.UPC_A ||
                    rawResult.getBarcodeFormat() == BarcodeFormat.EAN_13)) {
                // Hacky special case -- draw two lines, for the barcode and metadata
                drawLine(canvas, paint, points[0], points[1], scaleFactor);
                drawLine(canvas, paint, points[2], points[3], scaleFactor);
            } else {
                paint.setStrokeWidth(10.0f);
                for (ResultPoint point : points) {
                    canvas.drawPoint(scaleFactor * point.getX(), scaleFactor * point.getY(), paint);
                }
            }
        }
    }

    private static void drawLine(Canvas canvas, Paint paint, ResultPoint a, ResultPoint b,
                                 float scaleFactor) {
        if (a != null && b != null) {
            canvas.drawLine(scaleFactor * a.getX(), scaleFactor * a.getY(), scaleFactor * b.getX(),
                    scaleFactor * b.getY(), paint);
        }
    }

    // Put up our own UI for how to handle the decoded contents.
    private void handleDecodeInternally(Result rawResult, ResultHandler resultHandler,
                                        Bitmap barcode) {
        Log.e("zxing", rawResult.toString());
        Map<ResultMetadataType, Object> metadata = rawResult.getResultMetadata();
        if (metadata != null) {
            StringBuilder metadataText = new StringBuilder(20);
            for (Map.Entry<ResultMetadataType, Object> entry : metadata.entrySet()) {
                if (DISPLAYABLE_METADATA_TYPES.contains(entry.getKey())) {
                    metadataText.append(entry.getValue()).append('\n');
                }
            }
            if (metadataText.length() > 0) {
                metadataText.setLength(metadataText.length() - 1);
            }
        }
        //为1表示是主动的激活
        if (type == 1) {
            returnParseCode(rawResult.getText());
        } else {
            String parseCode = parseCode(rawResult.getText());
            //为空说明产品码错误，那有可能是激活码
            if (TextUtils.isEmpty(parseCode)) {
                checkActivationCode(rawResult.getText());
            } else {
                returnParseCode(parseCode);
            }
        }
        setFinish(rawResult.toString());
    }

    /**
     * 请求服务器激活
     *
     * @param acode
     */
    private void activateCode(String acode) {

    }

    /**
     * 校验激活码
     *
     * @param code
     */
    private void checkActivationCode(String code) {
        //新的激活码的正则
//        if (Pattern.compile(activationNewReg).matcher(code).matches())
//        {
//            activateCode(code);
//        } else
//        {
//            if (Pattern.compile(activationOldReg).matcher(code).matches())
//            {
//                new ArshowDialog.Builder(this).setMessage("请找到您要激活的AR资源包").setNegativeButton("取消",
//                        new DialogInterface.OnClickListener()
//                        {
//                            @Override
//                            public void onClick(DialogInterface dialog, int which)
//                            {
//                                dialog.dismiss();
//                                restartPreviewAfterDelay(100);
//                            }
//                        }).setPositiveButton("确定", new DialogInterface.OnClickListener()
//                {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which)
//                    {
//                        dialog.dismiss();
//                        startActivity(new Intent(getApplicationContext(), RankActivity.class));
//                        finish();
//                    }
//                }).show();
//            } else
//            {
//                ArshowContextUtil.toast(ERROR_CODE);
//                restartPreviewAfterDelay(100);
//            }
//        }
    }


    /**
     * 返回正确的二维码
     *
     * @param rawResult
     */
    public void returnParseCode(String rawResult) {
        inactivityTimer.onActivity();
        Intent resultIntent = new Intent();
        resultIntent.putExtra("result", rawResult);
        this.setResult(RESULT_OK, resultIntent);
        this.finish();
    }

    /**
     * 解析扫描的产品码
     */
    private String parseCode(String code) {
        // 获取扫描后的密文然后解密
//        String prid = new AESUtil(getPackageName()).decrypt(code);
//
//        if (TextUtils.isEmpty(prid))
//        {
//            return null;
//        }
//        try
//        {
//            List<Integer> arrayList = CodeUtil.parseProductId(Integer.valueOf(prid));
//            return arrayList.get(arrayList.size() - 1) + "";
//        } catch (NumberFormatException e)
//        {
//            Toast.makeText(this, ERROR_CODE, Toast.LENGTH_SHORT).show();
//        }
        return null;
    }


    // Briefly show the contents of the barcode, then handle the result outside Barcode Scanner.
    private void handleDecodeExternally(Result rawResult, ResultHandler resultHandler,
                                        Bitmap barcode) {
        Log.e("zxing", rawResult.toString());
        if (barcode != null) {// TODO: 2018/11/23 这个地方注意处理消息 
            viewfinderView.drawResultBitmap(barcode);
        }

        long resultDurationMS;
        if (getIntent() == null) {
            resultDurationMS = DEFAULT_INTENT_RESULT_DURATION_MS;
        } else {
            resultDurationMS = getIntent().getLongExtra(Intents.Scan.RESULT_DISPLAY_DURATION_MS,
                    DEFAULT_INTENT_RESULT_DURATION_MS);
        }

        if (resultDurationMS > 0) {
            String rawResultString = String.valueOf(rawResult);
            if (rawResultString.length() > 32) {
                rawResultString = rawResultString.substring(0, 32) + " ...";
            }
        }

        if (copyToClipboard && !resultHandler.areContentsSecure()) {
            ClipboardManager clipboard = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
            CharSequence text = resultHandler.getDisplayContents();
            if (text != null) {
                try {
                    clipboard.setText(text);
                } catch (NullPointerException npe) {
                    // Some kind of bug inside the clipboard implementation, not due to null input
                    Log.w(TAG, "Clipboard bug", npe);
                }
            }
        }

        if (source == IntentSource.NATIVE_APP_INTENT) {

            // Hand back whatever action they requested - this can be changed to Intents.Scan.ACTION when
            // the deprecated intent is retired.
            Intent intent = new Intent(getIntent().getAction());
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);
            intent.putExtra(Intents.Scan.RESULT, rawResult.toString());
            intent.putExtra(Intents.Scan.RESULT_FORMAT, rawResult.getBarcodeFormat().toString());
            byte[] rawBytes = rawResult.getRawBytes();
            if (rawBytes != null && rawBytes.length > 0) {
                intent.putExtra(Intents.Scan.RESULT_BYTES, rawBytes);
            }
            Map<ResultMetadataType, ?> metadata = rawResult.getResultMetadata();
            if (metadata != null) {
                if (metadata.containsKey(ResultMetadataType.UPC_EAN_EXTENSION)) {
                    intent.putExtra(Intents.Scan.RESULT_UPC_EAN_EXTENSION,
                            metadata.get(ResultMetadataType.UPC_EAN_EXTENSION).toString());
                }
                Integer orientation = (Integer) metadata.get(ResultMetadataType.ORIENTATION);
                if (orientation != null) {
                    intent.putExtra(Intents.Scan.RESULT_ORIENTATION, orientation.intValue());
                }
                String ecLevel = (String) metadata.get(ResultMetadataType.ERROR_CORRECTION_LEVEL);
                if (ecLevel != null) {
                    intent.putExtra(Intents.Scan.RESULT_ERROR_CORRECTION_LEVEL, ecLevel);
                }
                Iterable<byte[]> byteSegments =
                        (Iterable<byte[]>) metadata.get(ResultMetadataType.BYTE_SEGMENTS);
                if (byteSegments != null) {
                    int i = 0;
                    for (byte[] byteSegment : byteSegments) {
                        intent.putExtra(Intents.Scan.RESULT_BYTE_SEGMENTS_PREFIX + i, byteSegment);
                        i++;
                    }
                }
            }
            sendReplyMessage(R.id.return_scan_result, intent, resultDurationMS);

        } else if (source == IntentSource.PRODUCT_SEARCH_LINK) {

            // Reformulate the URL which triggered us into a query, so that the request goes to the same
            // TLD as the scan URL.
            int end = sourceUrl.lastIndexOf("/scan");
            String replyURL =
                    sourceUrl.substring(0, end) + "?q=" + resultHandler.getDisplayContents() +
                            "&source=zxing";
            sendReplyMessage(R.id.launch_product_query, replyURL, resultDurationMS);

        } else if (source == IntentSource.ZXING_LINK) {

            if (scanFromWebPageManager != null && scanFromWebPageManager.isScanFromWebPage()) {
                String replyURL = scanFromWebPageManager.buildReplyURL(rawResult, resultHandler);
                sendReplyMessage(R.id.launch_product_query, replyURL, resultDurationMS);
            }

        }
        finish();
    }


    private void sendReplyMessage(int id, Object arg, long delayMS) {
        Log.e("zxing", id + "");
        Message message = Message.obtain(handler, id, arg);
        if (delayMS > 0L) {
            handler.sendMessageDelayed(message, delayMS);
        } else {
            handler.sendMessage(message);
        }
//        Intent intent =new Intent(this, StudentManageActivity.class);
//        intent.putExtra(ConstantString.RESULT,id+"");
//        setResult(10000,intent);
        finish();
    }

    /**
     * 初始化相机
     *
     * @param surfaceHolder
     */
    private void initCamera(SurfaceHolder surfaceHolder) {
        if (surfaceHolder == null) {
            throw new IllegalStateException("No SurfaceHolder provided");
        }
        if (cameraManager.isOpen()) {
            Log.w(TAG, "initCamera() while already open -- late SurfaceView callback?");
            return;
        }
        try {
            cameraManager.openDriver(surfaceHolder);
            // Creating the handler starts the preview, which can also throw a RuntimeException.
            if (handler == null) {
                handler = new CaptureActivityHandler(this, decodeFormats, decodeHints, characterSet,
                        cameraManager);
            }
            decodeOrStoreSavedBitmap(null);
        } catch (IOException ioe) {
            Log.w(TAG, ioe);
            displayFrameworkBugMessageAndExit();
        } catch (RuntimeException e) {
            // Barcode Scanner has seen crashes in the wild of this variety:
            // java.?lang.?RuntimeException: Fail to connect to camera service
            Log.w(TAG, "Unexpected error initializing camera", e);
            displayFrameworkBugMessageAndExit();
        }

        //设置提示信息的位置
        FrameLayout.LayoutParams params = (FrameLayout.LayoutParams) tv_scan_hint.getLayoutParams();
        params.topMargin = cameraManager.getFramingRect().bottom + ArshowContextUtil.dp2px(28);
        tv_scan_hint.setLayoutParams(params);
    }

    private void displayFrameworkBugMessageAndExit() {
        Log.e("zxing", "error");
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(getString(R.string.app_name));
        builder.setMessage(getString(R.string.msg_camera_framework_bug));
        builder.setPositiveButton(R.string.button_ok, new FinishListener(this));
        builder.setOnCancelListener(new FinishListener(this));
        builder.show();
    }

    /**
     * 延时重置
     *
     * @param delayMS
     */
    public void restartPreviewAfterDelay(long delayMS) {
        if (handler != null) {
            handler.sendEmptyMessageDelayed(R.id.restart_preview, delayMS);
        }
        resetStatusView();
    }

    private void resetStatusView() {
        lastResult = null;
    }

    public void drawViewfinder() {
        viewfinderView.drawViewfinder();
    }


    public void setFinish(String data) {
        Intent intent =new Intent();
        intent.putExtra(ZXING_VALUE,data);
//        intent.putExtra(ZXING_VALUE,"50BB2ACD-7694-436F-8BE7-08D64C5BA3EC");//测试数据
        setResult(ZXING_INTENT,intent);
        finish();
    }
}


//package com.google.zxing.client.android;
//
//import android.app.Activity;
//import android.app.AlertDialog;
//import android.content.Intent;
//import android.graphics.Bitmap;
//import android.os.Bundle;
//import android.os.Handler;
//import android.util.Log;
//import android.view.SurfaceHolder;
//import android.view.SurfaceView;
//import android.view.Window;
//import android.view.WindowManager;
//import android.widget.ImageButton;
//import android.widget.Toast;
//
//import com.google.zxing.BarcodeFormat;
//import com.google.zxing.DecodeHintType;
//import com.google.zxing.Result;
//import com.google.zxing.client.android.camera.CameraManager;
//import com.qixiu.tongditrain.R;
//
//import java.io.IOException;
//import java.util.Collection;
//import java.util.Map;
//
///**
// * 这个activity打开相机，在后台线程做常规的扫描；它绘制了一个结果view来帮助正确地显示条形码，在扫描的时候显示反馈信息，
// * 然后在扫描成功的时候覆盖扫描结果
// *
// */
//public final class CaptureActivity extends Activity implements
//        SurfaceHolder.Callback {
//
//    private static final String TAG = CaptureActivity.class.getSimpleName();
//
//    // 相机控制
//    private CameraManager cameraManager;
//    private CaptureActivityHandler handler;
//    private ViewfinderView viewfinderView;
//    private boolean hasSurface;
//    private IntentSource source;
//    private Collection<BarcodeFormat> decodeFormats;
//    private Map<DecodeHintType, ?> decodeHints;
//    private String characterSet;
//    // 电量控制
//    private InactivityTimer inactivityTimer;
//    // 声音、震动控制
//    private BeepManager beepManager;
//
//    private ImageButton imageButton_back;
//    Result   lastResult;
//    public ViewfinderView getViewfinderView() {
//        return viewfinderView;
//    }
//
//    public Handler getHandler() {
//        return handler;
//    }
//
//    public CameraManager getCameraManager() {
//        return cameraManager;
//    }
//
//    public void drawViewfinder() {
//        viewfinderView.drawViewfinder();
//    }
//
//    /**
//     * OnCreate中初始化一些辅助类，如InactivityTimer（休眠）、Beep（声音）以及AmbientLight（闪光灯）
//     */
//    @Override
//    public void onCreate(Bundle icicle) {
//        super.onCreate(icicle);
//        // 保持Activity处于唤醒状态
//        Window window = getWindow();
//        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
//        setContentView(R.layout.capture);
//
//        hasSurface = false;
//
//        inactivityTimer = new InactivityTimer(this);
//        beepManager = new BeepManager(this);
//
////        imageButton_back = (ImageButton) findViewById(R.id.capture_imageview_back);
////        imageButton_back.setOnClickListener(new View.OnClickListener() {
////
////            @Override
////            public void onClick(View v) {
////                finish();
////            }
////        });
//    }
//
//    @Override
//    protected void onResume() {
//        super.onResume();
//
//        // CameraManager必须在这里初始化，而不是在onCreate()中。
//        // 这是必须的，因为当我们第一次进入时需要显示帮助页，我们并不想打开Camera,测量屏幕大小
//        // 当扫描框的尺寸不正确时会出现bug
//        cameraManager = new CameraManager(getApplication());
//
//        viewfinderView = (ViewfinderView) findViewById(R.id.viewfinder_view);
//        viewfinderView.setCameraManager(cameraManager);
//
//        handler = null;
//
//        SurfaceView surfaceView = (SurfaceView) findViewById(R.id.preview_view);
//        SurfaceHolder surfaceHolder = surfaceView.getHolder();
//        if (hasSurface) {
//            // activity在paused时但不会stopped,因此surface仍旧存在；
//            // surfaceCreated()不会调用，因此在这里初始化camera
//            initCamera(surfaceHolder);
//        } else {
//            // 重置callback，等待surfaceCreated()来初始化camera
//            surfaceHolder.addCallback(this);
//        }
//
//        beepManager.updatePrefs();
//        inactivityTimer.onResume();
//
//        source = IntentSource.NONE;
//        decodeFormats = null;
//        characterSet = null;
//    }
//
//    @Override
//    protected void onPause() {
//        if (handler != null) {
//            handler.quitSynchronously();
//            handler = null;
//        }
//        inactivityTimer.onPause();
////        beepManager.close();
//        cameraManager.closeDriver();
//        if (!hasSurface) {
//            SurfaceView surfaceView = (SurfaceView) findViewById(R.id.preview_view);
//            SurfaceHolder surfaceHolder = surfaceView.getHolder();
//            surfaceHolder.removeCallback(this);
//        }
//        super.onPause();
//    }
//
//    @Override
//    protected void onDestroy() {
//        inactivityTimer.shutdown();
//        super.onDestroy();
//    }
//
//    @Override
//    public void surfaceCreated(SurfaceHolder holder) {
//        if (!hasSurface) {
//            hasSurface = true;
//            initCamera(holder);
//        }
//    }
//
//    @Override
//    public void surfaceDestroyed(SurfaceHolder holder) {
//        hasSurface = false;
//    }
//
//    @Override
//    public void surfaceChanged(SurfaceHolder holder, int format, int width,
//                               int height) {
//
//    }
//
//    /**
//     * 扫描成功，处理反馈信息
//     *
//     * @param rawResult
//     * @param barcode
//     * @param scaleFactor
//     */
//    public void handleDecode(Result rawResult, Bitmap barcode, float scaleFactor) {
//        inactivityTimer.onActivity();
//        lastResult=rawResult;
//        boolean fromLiveScan = barcode != null;
//        //这里处理解码完成后的结果，此处将参数回传到Activity处理
//        if (fromLiveScan) {
//            beepManager.playBeepSoundAndVibrate();
//
//            Toast.makeText(this, "扫描成功", Toast.LENGTH_SHORT).show();
//
//            Intent intent = getIntent();
//            intent.putExtra("codedContent", rawResult.getText());
//            intent.putExtra("codedBitmap", barcode);
//            setResult(RESULT_OK, intent);
//            finish();
//        }
//
//    }
//
//    /**
//     * 初始化Camera
//     *
//     * @param surfaceHolder
//     */
//    private void initCamera(SurfaceHolder surfaceHolder) {
//        if (surfaceHolder == null) {
//            throw new IllegalStateException("No SurfaceHolder provided");
//        }
//        if (cameraManager.isOpen()) {
//            return;
//        }
//        try {
//            // 打开Camera硬件设备
//            cameraManager.openDriver(surfaceHolder);
//            // 创建一个handler来打开预览，并抛出一个运行时异常
//            if (handler == null) {
//                handler = new CaptureActivityHandler(this, decodeFormats,
//                        decodeHints, characterSet, cameraManager);
//            }
//        } catch (IOException ioe) {
//            Log.w(TAG, ioe);
//            displayFrameworkBugMessageAndExit();
//        } catch (RuntimeException e) {
//            Log.w(TAG, "Unexpected error initializing camera", e);
//            displayFrameworkBugMessageAndExit();
//        }
//    }
//
//    /**
//     * 显示底层错误信息并退出应用
//     */
//    private void displayFrameworkBugMessageAndExit() {
//        AlertDialog.Builder builder = new AlertDialog.Builder(this);
//        builder.setTitle(getString(R.string.app_name));
//        builder.setMessage(getString(R.string.msg_camera_framework_bug));
//        builder.setPositiveButton(R.string.button_ok, new FinishListener(this));
//        builder.setOnCancelListener(new FinishListener(this));
//        builder.show();
//    }
//        /**
//     * 延时重置
//     *
//     * @param delayMS
//     */
//    public void restartPreviewAfterDelay(long delayMS) {
//        if (handler != null) {
//            handler.sendEmptyMessageDelayed(R.id.restart_preview, delayMS);
//        }
//        resetStatusView();
//    }
//
//    private void resetStatusView() {
//        lastResult = null;
//    }


//}
