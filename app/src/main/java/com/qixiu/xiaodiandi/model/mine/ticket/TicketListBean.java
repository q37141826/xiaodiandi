package com.qixiu.xiaodiandi.model.mine.ticket;

import com.qixiu.qixiu.request.bean.BaseBean;

import java.util.List;

//
public class TicketListBean extends BaseBean< List<TicketListBean.OBean> > {



    public static class OBean {
        /**
         * id : 138
         * cid : 93
         * uid : 181
         * coupon_title : 测试优惠
         * coupon_price : 0.01
         * use_min_price : 0.02
         * add_time : 1550198707
         * end_time : 1551926707
         * use_time : 0
         * type : get
         * status : 0
         * is_fail : 0
         * _add_time : 2019/02/15
         * _end_time : 2019/03/07
         * _type : 2
         * _msg : 可使用
         */

        private int id;
        private String cid;
        private int uid;
        private String coupon_title;
        private double coupon_price;
        private double use_min_price;
        private String add_time;
        private int end_time;
        private int use_time;
        private String type;
        private int status;
        private int is_fail;
        private String _add_time;
        private String _end_time;
        private int _type;
        private String _msg;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getCid() {
            return cid;
        }

        public void setCid(String cid) {
            this.cid = cid;
        }

        public int getUid() {
            return uid;
        }

        public void setUid(int uid) {
            this.uid = uid;
        }

        public String getCoupon_title() {
            return coupon_title;
        }

        public void setCoupon_title(String coupon_title) {
            this.coupon_title = coupon_title;
        }

        public double getCoupon_price() {
            return coupon_price;
        }

        public void setCoupon_price(double coupon_price) {
            this.coupon_price = coupon_price;
        }

        public double getUse_min_price() {
            return use_min_price;
        }

        public void setUse_min_price(double use_min_price) {
            this.use_min_price = use_min_price;
        }

        public String getAdd_time() {
            return add_time;
        }

        public void setAdd_time(String add_time) {
            this.add_time = add_time;
        }

        public int getEnd_time() {
            return end_time;
        }

        public void setEnd_time(int end_time) {
            this.end_time = end_time;
        }

        public int getUse_time() {
            return use_time;
        }

        public void setUse_time(int use_time) {
            this.use_time = use_time;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public int getIs_fail() {
            return is_fail;
        }

        public void setIs_fail(int is_fail) {
            this.is_fail = is_fail;
        }

        public String get_add_time() {
            return _add_time;
        }

        public void set_add_time(String _add_time) {
            this._add_time = _add_time;
        }

        public String get_end_time() {
            return _end_time;
        }

        public void set_end_time(String _end_time) {
            this._end_time = _end_time;
        }

        public int get_type() {
            return _type;
        }

        public void set_type(int _type) {
            this._type = _type;
        }

        public String get_msg() {
            return _msg;
        }

        public void set_msg(String _msg) {
            this._msg = _msg;
        }
    }
}
