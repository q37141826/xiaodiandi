package com.qixiu.qixiu.utils;

import android.os.CountDownTimer;
import android.text.TextUtils;
import android.util.Log;
import android.widget.TextView;

public class MyTimer extends CountDownTimer {
    private TextView textView;//显示倒计时的文本
    private String originalText;
    private TimeStateListenner listenner;

    public void setTextBefore(String textBefore) {
        this.textBefore = textBefore;
    }

    public void setTextAfter(String textAfter) {
        this.textAfter = textAfter;
    }

    private String textBefore;
    private String textAfter;


    public void setListenner(TimeStateListenner listenner) {
        this.listenner = listenner;
    }

    public void setOriginalText(String originalText) {
        this.originalText = originalText;
    }

    public MyTimer(long millisInFuture, long countDownInterval) {
        super(millisInFuture, countDownInterval);
    }

    public void startTimeCountDown(TextView codeText) {
        this.textView = codeText;
        if (!TextUtils.isEmpty(codeText.getText())) {
            this.originalText = codeText.getText().toString();
        }
        start();
    }

    public MyTimer() {
        super(60 * 1000, 1000);
    }
    public MyTimer(int totoal,int per) {
        super(totoal, per);
    }
    @Override
    public void onTick(long millisUntilFinished) {
        Log.i("test", "??");
        if (textView != null) {
            textView.setText(millisUntilFinished / 1000 + "秒后重发");
            if(!TextUtils.isEmpty(textBefore)&&!TextUtils.isEmpty(textAfter)){
                textView.setText(textBefore+millisUntilFinished / 1000 + textAfter);
            }
            textView.setEnabled(false);
        }
        if (listenner != null) {
            listenner.onRunning(millisUntilFinished);
        }
    }

    @Override
    public void onFinish() {
        if (textView != null) {
            if (!TextUtils.isEmpty(originalText)) {
                textView.setText(originalText);
            } else {
                textView.setText("发送验证码");
            }
            textView.setEnabled(true);
        }
        if (listenner != null) {
            listenner.onFinished();
        }
    }


    public interface TimeStateListenner {
        void onRunning(long lastTime);

        void onFinished();

    }

    public void cancleTimer(){
        cancel();
    }

}