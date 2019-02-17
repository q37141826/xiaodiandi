package com.qixiu.qixiu.camera;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.qixiu.qixiu.R;


public class CommonDialog extends Dialog {

    private OnDismissListener dismissListener;
    private OnKeyListener keyListener;

    public CommonDialog(Context context) {
        super(context);
    }

    public CommonDialog(Context context, int theme) {
        super(context, theme);
    }

    public static abstract class Builder {
        private Context context;
        final CommonDialog dialog;

        public Builder(Context context) {
            this.context = context;
            dialog = new CommonDialog(context, R.style.Dialog);
        }

        public View getView(int layoutId) {
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View layout = inflater.inflate(layoutId, null);
            dialog.addContentView(layout, new ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            return layout;
        }

        public CommonDialog build(int layoutId) {
            onViewCreated(getView(layoutId), dialog);
            return dialog;
        }

        abstract public void onViewCreated(View view, Dialog dialog);


    }

    public  void showDialog(Context context) {
        CommonDialog dialog = new CommonDialog.Builder(context) {
            @Override
            public void onViewCreated(View view, Dialog dialog) {
//在这里可以初始化布局中的控件，以及点击事件等，所需要在对话框中的操作。。。
// findViewById().....
                TextView textViewCancle = view.findViewById(R.id.textViewCancle);
                TextView textViewConfirm = view.findViewById(R.id.textViewConfirm);
                textViewConfirm.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dismiss();
                    }
                });
                textViewCancle.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if(keyListener!=null){
                            keyListener.onKey(null,0,null);
                        }
                    }
                });
            }
        }.build(R.layout.dialog_waiting);    //这里是自定义的布局文件资源
        dialog.setCancelable(true);
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();
    }

    public void setOnDismissListener( DialogInterface.OnDismissListener listener) {
        super.setOnDismissListener(listener);
        dismissListener = listener;
    }

    public void setOnKeyListener( DialogInterface.OnKeyListener onKeyListener) {
        super.setOnKeyListener(onKeyListener);
        keyListener = onKeyListener;
    }
}



