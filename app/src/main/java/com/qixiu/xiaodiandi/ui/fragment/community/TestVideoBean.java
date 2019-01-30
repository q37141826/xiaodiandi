package com.qixiu.xiaodiandi.ui.fragment.community;

import com.qixiu.qixiu.recyclerview_lib.ItemTypesInterf;

/**
 * Created by my on 2019/1/14.
 */

public class TestVideoBean  implements ItemTypesInterf{
    private int layoutRes;
    private int type;

    public TestVideoBean() {
    }

    public TestVideoBean(int layoutRes, int type) {
        this.layoutRes = layoutRes;
        this.type = type;
    }

    public int getLayoutRes() {
        return layoutRes;
    }

    public void setLayoutRes(int layoutRes) {
        this.layoutRes = layoutRes;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
