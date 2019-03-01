package com.qixiu.qixiu.camera;

import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;

import com.qixiu.qixiu.R;

public class PopVideoCode {
    Context context;
    private View contentView;
    private PopupWindow popwindow;
    private RelativeLayout relativeLayout_pop_dismiss;
    ImageView imageViewConfirm,
            imageViewGoback;

    public PopVideoCode(Context context) {
        this.context = context;
        setPop();
    }

    public void setPop() {
        contentView = View.inflate(context, R.layout.pop_video_code, null);
        popwindow = new PopupWindow(contentView);
        popwindow.setFocusable(true);
        popwindow.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        popwindow.setHeight(ViewGroup.LayoutParams.MATCH_PARENT);
        imageViewConfirm=contentView.findViewById(R.id.imageViewConfirm);
        imageViewGoback=contentView.findViewById(R.id.imageViewGoback);

    }

    public void show() {
        popwindow.showAtLocation(contentView, Gravity.BOTTOM, 0, 0);
    }

    public void dismiss() {
        popwindow.dismiss();
    }

    public void setConfirmListenner(View.OnClickListener onClickListener) {
            imageViewConfirm.setOnClickListener(onClickListener);
    }

    public void setCancleListenner(View.OnClickListener onClickListener){
        imageViewGoback.setOnClickListener(onClickListener);
    }


}
