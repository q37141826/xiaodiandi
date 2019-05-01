package com.qixiu.xiaodiandi.ui.wigit;

import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.qixiu.xiaodiandi.R;

public class TransportPop {

    Context context;
    PopupWindow popwindow;
    View contentView;
    RelativeLayout relativeLayout_pop_dismiss;
    TextView textViewCompany, textViewTransportId;

    public TransportPop(Context context) {
        this.context = context;
        setPop();
    }

    private void setPop() {
        contentView = View.inflate(context, R.layout.pop_kuaidi, null);
        relativeLayout_pop_dismiss = contentView.findViewById(R.id.relativeLayout_pop_dismiss);
        relativeLayout_pop_dismiss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        textViewCompany = contentView.findViewById(R.id.textViewCompany);
        textViewTransportId = contentView.findViewById(R.id.textViewTransportId);
        popwindow = new PopupWindow(contentView);
        popwindow.setFocusable(true);
        popwindow.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        popwindow.setHeight(ViewGroup.LayoutParams.MATCH_PARENT);
        popwindow.showAtLocation(contentView, Gravity.CENTER, 0, 0);
    }

    public void setData(String name, String id) {
        if(textViewCompany==null||textViewTransportId==null){
            return;
        }
        textViewCompany.setText(name);
        textViewTransportId.setText(id);
    }



    public void dismiss() {
        popwindow.dismiss();
    }

    public void show() {
        popwindow.showAtLocation(contentView, Gravity.CENTER, 0, 0);
    }


}
