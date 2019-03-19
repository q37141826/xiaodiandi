package com.qixiu.wigit;

import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.PopupWindow;

import com.qixiu.qixiu.R;
import com.qixiu.qixiu.utils.NavagationUtils;

public class WechatTakeCameraSelectPop {
    Context context;
    private PopupWindow popwindow;
    private View contentView;
    ClickListenner clickListenner;

    public void setClickListenner(ClickListenner clickListenner) {
        this.clickListenner = clickListenner;
    }

    public WechatTakeCameraSelectPop(Context context) {
        this.context = context;
        creatPop(context);
    }


    private void creatPop(Context context) {
        contentView = View.inflate(context, R.layout.pop_wechat_take_camera, null);
        View viewTake = contentView.findViewById(R.id.viewTake);
        View viewPhone = contentView.findViewById(R.id.viewPhone);
        View viewCancle = contentView.findViewById(R.id.viewCancle);
        View relativeLayout_back_for_dismiss = contentView.findViewById(R.id.relativeLayout_back_for_dismiss);
        viewTake.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (clickListenner != null) {
                    clickListenner.takeVideo();
                }
            }
        });
        viewPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (clickListenner != null) {
                    clickListenner.takePhoto();
                }
            }
        });
        viewCancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
        relativeLayout_back_for_dismiss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
        setAndShowPop(contentView);
    }

    private void setAndShowPop(View contentView) {
        popwindow = new PopupWindow(contentView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT,
                true);
        popwindow.setInputMethodMode(PopupWindow.INPUT_METHOD_NEEDED);
        popwindow.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        popwindow.setClippingEnabled(false);
    }

    public void show() {
        popwindow.showAtLocation(contentView, Gravity.BOTTOM, 0, 0);
    }

    public void showContainsBar(Context context, boolean navigationBarIsUp) {
        int[] location = new int[2];
        contentView.getLocationOnScreen(location);
        int x = location[0];
        int y = location[1];
        if (navigationBarIsUp && NavagationUtils.hasNavBar(context)) {
            popwindow.showAtLocation(contentView, Gravity.BOTTOM, 0, NavagationUtils.getNavigationBarHeight(context));
        } else {
            popwindow.showAtLocation(contentView, Gravity.BOTTOM, 0, 0);
        }
    }

    public boolean isShowing() {
        return popwindow.isShowing();
    }

    public void dismiss() {
        popwindow.dismiss();
    }

    public interface ClickListenner {
        void takeVideo();

        void takePhoto();

    }

}
