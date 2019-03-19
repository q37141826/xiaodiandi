package com.qixiu.xiaodiandi.constant;

import android.os.Parcelable;

public class EventAction {
    public static final int GOTO_CARTS=0x1001;//切换到购物车
    public static final int GOTO_TYPE=0x1002;//切换到分类列表

    public static class Action{
        private int action;
        private String id="";
        private Parcelable data;

        public Parcelable getData() {
            return data;
        }

        public void setData(Parcelable data) {
            this.data = data;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public Action(int action) {
            this.action = action;
        }

        public int getAction() {
            return action;
        }

        public void setAction(int action) {
            this.action = action;
        }
    }
}
