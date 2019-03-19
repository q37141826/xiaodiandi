package com.qixiu.xiaodiandi.utils;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

public class InputUtils {

    public static void setEdittextLimit(EditText editText, double limite) {
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                try {
                    if (NumUtils.getDouble(editable.toString()) > limite) {
                        editText.setText(limite + "");
                    }
                } catch (Exception e) {

                }
            }
        });
    }
}
