package com.qixiu.xiaodiandi.ui.wigit;

import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.qixiu.xiaodiandi.R;

/**
 * Created by my on 2019/1/16.
 */

public class ApkDownloadProgressPop {

    private View contentView;
    private PopupWindow popwindow;
    private ProgressBar progressBar;
    private TextView textViewProgress;


    public ApkDownloadProgressPop(Context context) {
        creatPop(context);
    }

    public void creatPop(Context context) {
        contentView = View.inflate(context, R.layout.apk_download_pop, null);
        popwindow = new PopupWindow(contentView);
        popwindow.setFocusable(true);
        popwindow.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        popwindow.setHeight(ViewGroup.LayoutParams.MATCH_PARENT);
        progressBar = contentView.findViewById(R.id.progressBar);
        textViewProgress = contentView.findViewById(R.id.textViewProgress);
    }

    public void setProgress(int progress) {
        progressBar.setMax(100);
        progressBar.setProgress(progress);
    }

    public void setProgress(long currentSize, long totalSize) {
        progressBar.setMax((int) (totalSize/1000));
        progressBar.setProgress((int) (currentSize/1000));
    }

    public void setTextProgress(long currentSize, long totalSize){
        textViewProgress.setText(totalSize/1000+"  "+currentSize*100/totalSize+"%");
    }

    public void dismiss() {
        popwindow.dismiss();
    }

    public void show() {
        popwindow.showAtLocation(contentView, Gravity.BOTTOM, 0, 0);
    }


}
