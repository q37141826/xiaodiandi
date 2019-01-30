package com.qixiu.xiaodiandi.model.mine.ticket;

import com.qixiu.qixiu.request.bean.BaseBean;

import java.util.List;

public class TicketListBean extends BaseBean< List<TicketListBean.OBean> > {


    public static class OBean {
        /**
         * id : 136
         * cid : 90
         * uid : 118
         * coupon_title : 满100减10元
         * coupon_price : 10
         * use_min_price : 100
         * add_time : 1546682882
         * end_time : 1547287682
         * use_time : 1546682999
         * type : get
         * status : 1
         * is_fail : 0
         * _add_time : 2019/01/05
         * _end_time : 2019/01/12
         * _type : 0
         * _msg : 已使用
         */

        private int id;
        private int cid;
        private int uid;
        private String coupon_title;
        private int coupon_price;
        private int use_min_price;
        private int add_time;
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

        public int getCid() {
            return cid;
        }

        public void setCid(int cid) {
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

        public int getCoupon_price() {
            return coupon_price;
        }

        public void setCoupon_price(int coupon_price) {
            this.coupon_price = coupon_price;
        }

        public int getUse_min_price() {
            return use_min_price;
        }

        public void setUse_min_price(int use_min_price) {
            this.use_min_price = use_min_price;
        }

        public int getAdd_time() {
            return add_time;
        }

        public void setAdd_time(int add_time) {
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
