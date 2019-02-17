package com.qixiu.qixiu.engine.oss;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.alibaba.sdk.android.oss.ClientConfiguration;
import com.alibaba.sdk.android.oss.ClientException;
import com.alibaba.sdk.android.oss.OSS;
import com.alibaba.sdk.android.oss.OSSClient;
import com.alibaba.sdk.android.oss.ServiceException;
import com.alibaba.sdk.android.oss.callback.OSSCompletedCallback;
import com.alibaba.sdk.android.oss.callback.OSSProgressCallback;
import com.alibaba.sdk.android.oss.common.auth.OSSCredentialProvider;
import com.alibaba.sdk.android.oss.common.auth.OSSStsTokenCredentialProvider;
import com.alibaba.sdk.android.oss.internal.OSSAsyncTask;
import com.alibaba.sdk.android.oss.model.PutObjectRequest;
import com.alibaba.sdk.android.oss.model.PutObjectResult;
import com.qixiu.qixiu.utils.TimeDataUtil;

import java.io.File;
import java.util.Date;


/**
 * Created by Administrator on 2017/9/30 0030.
 */

public class AliOssEngine {

    public static String endpoint = "oss-cn-shanghai.aliyuncs.com";
    public static String key = "LTAI0j4WxFlrrgC3";
    public static String keySec = "Lhg3UoXXwtnRsTXD0SgpjJWxmlzj7u";
    public static String bcname = "tz1";
    public static OSS oss;
    public String finnalUrl;
    private SendSuccess call;

    //路径前缀
    private static String before = "Upload/vedio/" + TimeDataUtil.getTimeStamp(new Date().getTime());


    public SendSuccess getCall() {
        return call;
    }

    public void setCall(SendSuccess call) {
        this.call = call;
    }

    public AliOssEngine(SendSuccess call) {
        this.call = call;
    }

    public static void initAliYun(Context context, String endpoint, String key, String keySec) {
// 在移动端建议使用STS的方式初始化OSSClient，更多信息参考：[访问控制]
        setfirst(context);
    }

    public static void initAliYun(Context context) {
// 在移动端建议使用STS的方式初始化OSSClient，更多信息参考：[访问控制]
        setfirst(context);
    }

    private static void setfirst(Context context) {
        OSSCredentialProvider credentialProvider = new OSSStsTokenCredentialProvider(key, keySec, "");//先不传token
        ClientConfiguration conf = new ClientConfiguration();
        conf.setConnectionTimeout(15 * 1000); // 连接超时，默认15秒
        conf.setSocketTimeout(15 * 1000); // socket超时，默认15秒
        conf.setMaxConcurrentRequest(5); // 最大并发请求书，默认5个
        conf.setMaxErrorRetry(2); // 失败后最大重试次数，默认2次
        oss = new OSSClient(context, endpoint, credentialProvider, conf);
    }

    public void startUpload(String uploadFilePath, final UploadProgress call) {

        //设置上传路径，按照此格式，datetime是为了让文件不重复所加上的时间戳
        String objectKey = before + new File(uploadFilePath).getName().toLowerCase();
        //设置回传的阿里云url
        finnalUrl = "http://" + bcname + "." + endpoint + "/" + objectKey;
        Log.e("objectKey", objectKey);
        final Handler handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                // 将返回值回调到callBack的参数中
                Log.e("LOGCAT", "success");
                String s = (String) msg.obj;
                if (s.contains("finish")) {
                    call.onSucccess(s);
                } else if (s.contains("error")) {
                    call.onFailure(s);
                } else {
                    call.sendProgress(s);
                }
            }
        };
        PutObjectRequest put = new PutObjectRequest(bcname, objectKey, uploadFilePath);
// 异步上传时可以设置进度回调
        put.setProgressCallback(new OSSProgressCallback<PutObjectRequest>() {
            @Override
            public void onProgress(PutObjectRequest request, long currentSize, long totalSize) {
                Log.e("PutObject", "currentSize: " + currentSize + " totalSize: " + totalSize);
                Message mess = handler.obtainMessage(0, (int) ((((double) currentSize / (double) totalSize) * 100)) + "%");
                handler.sendMessage(mess);
            }
        });
        OSSAsyncTask task = oss.asyncPutObject(put, new OSSCompletedCallback<PutObjectRequest, PutObjectResult>() {
            @Override
            public void onSuccess(PutObjectRequest request, PutObjectResult result) {
                Log.e("PutObject", "UploadSuccess");
                Log.e("ETag", result.getETag());
                Log.e("RequestId", result.getRequestId());
                startNoticeManager();
                Message mess = handler.obtainMessage(0, "finish");
                handler.sendMessage(mess);
            }

            @Override
            public void onFailure(PutObjectRequest request, ClientException clientExcepion, ServiceException serviceException) {
                // 请求异常
                if (clientExcepion != null) {
                    // 本地异常如网络异常等
                    clientExcepion.printStackTrace();
                }
                if (serviceException != null) {
                    // 服务异常
                    Log.e("ErrorCode", serviceException.getErrorCode());
                    Log.e("RequestId", serviceException.getRequestId());
                    Log.e("HostId", serviceException.getHostId());
                    Log.e("RawMessage", serviceException.getRawMessage());
                }
                Message mess = handler.obtainMessage(0, "error");
                handler.sendMessage(mess);
            }
        });
    }

    private void startNoticeManager() {
        if (this.call != null) {
            call.onSuccess(finnalUrl);
        }
    }

    public interface SendSuccess {
        void onSuccess(String url);

    }

    public interface UploadProgress {
        void sendProgress(String progress);

        void onSucccess(String msg);

        void onFailure(String msg);
    }

}
