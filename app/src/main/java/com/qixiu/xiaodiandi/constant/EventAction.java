package com.qixiu.xiaodiandi.constant;

public class EventAction {
    public static final int GOTO_CARTS=0x1001;//切换到购物车

    public static class Action{
        private int action;

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
