package com.qixiu.xiaodiandi.presenter.home;

public class MarketPresenter {
    public static final int DELETE_CART = 0x1001;
    public static final int ADD_NUM = 0x1002;
    public static final int MINUS_NUM = 0x1003;

    public interface RefreshInterf {
        void refresh();

        void delete(Object data);

        void add(Object data);

        void minus(Object data);

        void selected(Object data);

    }


}
