package com.qixiu.xiaodiandi.ui.wigit;

import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;

import com.qixiu.xiaodiandi.R;

/**
 * Created by my on 2019/1/16.
 */

public class WritePop {

    private View contentView;
    private PopupWindow popwindow;
    private RelativeLayout relativeLayout_pop_dismiss;
    private EditText edittext;
    private ImageView imageViewSend;
    private RelativeLayout relativeLayout;

    public WritePop(Context context) {
        creatPop(context);
    }

    public void creatPop(Context context) {
        contentView = View.inflate(context, R.layout.write_pop, null);
        relativeLayout_pop_dismiss = contentView.findViewById(R.id.relativeLayout_pop_dismiss);
        relativeLayout = contentView.findViewById(R.id.relativeLayout);
        edittext = contentView.findViewById(R.id.edittext);
        imageViewSend = contentView.findViewById(R.id.imageViewSend);
        relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        relativeLayout_pop_dismiss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        popwindow = new PopupWindow(contentView);
        popwindow.setFocusable(true);
        popwindow.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        popwindow.setHeight(ViewGroup.LayoutParams.MATCH_PARENT);
        popwindow.showAtLocation(contentView, Gravity.BOTTOM, 0, 0);
    }

    public void dismiss() {
        popwindow.dismiss();
    }

    public void show() {
        popwindow.showAtLocation(contentView, Gravity.BOTTOM, 0, 0);
    }

    public String getText() {
        return edittext.getText().toString();
    }

    public void setSendListenner(View.OnClickListener onClickListener) {
        imageViewSend.setOnClickListener(onClickListener);
    }

    public int getViewId() {
        return imageViewSend.getId();
    }
}
