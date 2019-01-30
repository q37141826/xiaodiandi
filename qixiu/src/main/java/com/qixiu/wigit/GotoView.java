package com.qixiu.wigit;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.qixiu.qixiu.R;
import com.qixiu.qixiu.utils.DrawableUtils;

/**
 * Created by my on 2019/1/3.
 */

public class GotoView extends RelativeLayout {
    TextView textViewTitle, textViewName;

    public GotoView(Context context) {
        super(context);
    }

    public GotoView(Context context, AttributeSet attrs) {
        super(context, attrs);
        View view = View.inflate(context, R.layout.view_goto, this);
        textViewTitle = view.findViewById(R.id.textViewTitle);
        textViewName = view.findViewById(R.id.textViewName);
        //增加xml里面的可编辑属性
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.GotoView);

        int firsttextcolor = array.getColor(R.styleable.GotoView_first_text_color, getResources().getColor(R.color.font_grey));
        float firstsize = array.getDimensionPixelSize(R.styleable.GotoView_first_size, 15);
        textViewTitle.setTextColor(firsttextcolor);
        textViewTitle.setTextSize(firstsize);
        int firstres = array.getResourceId(R.styleable.GotoView_first_drawable, 0);
        if (firstres != 0) {
            DrawableUtils.setLeftDrawableResouce(textViewTitle, context, firstres);
        }
        String firstText = array.getString(R.styleable.GotoView_first_text);
        setFirstText(firstText);
        float firstdrawblepadding = array.getDimensionPixelSize(R.styleable.GotoView_first_drawble_padding, 15);
        textViewTitle.setCompoundDrawablePadding((int) firstdrawblepadding);


        int secondtextcolor = array.getColor(R.styleable.GotoView_second_text_color, getResources().getColor(R.color.font_grey));
        float secondsize = array.getDimensionPixelSize(R.styleable.GotoView_second_size, 15);
        int secondres = array.getResourceId(R.styleable.GotoView_second_drawable, 0);
        if (secondres != 0) {
            DrawableUtils.setRightDrawableResouce(textViewName, context, secondres);
        }
        textViewName.setTextSize(secondsize);
        textViewName.setTextColor(secondtextcolor);
        String secondText = array.getString(R.styleable.GotoView_second_text);
        setSecondText(secondText);
        float seconddrawblepadding = array.getDimensionPixelSize(R.styleable.GotoView_second_drawble_padding, 15);
        textViewName.setCompoundDrawablePadding((int) seconddrawblepadding);


        //是否显示
        boolean showGoto = array.getBoolean(R.styleable.GotoView_show_goto, true);
        setGotoVisble(showGoto);
    }

    public void setGotoVisble(boolean visble) {
        if (visble) {
        } else {
            DrawableUtils.setRightDrawableResouce(textViewName, getContext(), 0);
        }
    }

    public void setSecondText(String secondText) {
        textViewName.setText(secondText);
    }

    public void setFirstText(String firstText) {
        textViewTitle.setText(firstText);
    }
    public TextView getFirstView(){
        return textViewTitle;
    }
    public TextView getSecondView(){
        return textViewName;
    }
}
