package com.qixiu.xiaodiandi.model.mine.points;

import com.qixiu.qixiu.request.bean.BaseBean;

import java.util.List;

public class GetCatshRecordBean extends BaseBean< List<GetCatshRecordBean.OBean>> {



    public static class OBean {
        /**
         * id : 17
         * uid : 118
         * real_name :
         * extract_type : alipay
         * bank_code : 0
         * bank_address :
         * alipay_code :
         * extract_price : 0.00
         * mark : 申请提现
         * balance : 0.00
         * fail_msg :
         * fail_time :
         * add_time : 1547650390
         * status : 0
         * wechat :
         */

        private int id;
        private int uid;
        private String real_name;
        private String extract_type;
        private String bank_code;
        private String bank_address;
        private String alipay_code;
        private String extract_price;
        private String mark;
        private String balance;
        private String fail_msg;
        private String fail_time;
        private String add_time;
        private int status;
        private String wechat;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getUid() {
            return uid;
        }

        public void setUid(int uid) {
            this.uid = uid;
        }

        public String getReal_name() {
            return real_name;
        }

        public void setReal_name(String real_name) {
            this.real_name = real_name;
        }

        public String getExtract_type() {
            return extract_type;
        }

        public void setExtract_type(String extract_type) {
            this.extract_type = extract_type;
        }

        public String getBank_code() {
            return bank_code;
        }

        public void setBank_code(String bank_code) {
            this.bank_code = bank_code;
        }

        public String getBank_address() {
            return bank_address;
        }

        public void setBank_address(String bank_address) {
            this.bank_address = bank_address;
        }

        public String getAlipay_code() {
            return alipay_code;
        }

        public void setAlipay_code(String alipay_code) {
            this.alipay_code = alipay_code;
        }

        public String getExtract_price() {
            return extract_price;
        }

        public void setExtract_price(String extract_price) {
            this.extract_price = extract_price;
        }

        public String getMark() {
            return mark;
        }

        public void setMark(String mark) {
            this.mark = mark;
        }

        public String getBalance() {
            return balance;
        }

        public void setBalance(String balance) {
            this.balance = balance;
        }

        public String getFail_msg() {
            return fail_msg;
        }

        public void setFail_msg(String fail_msg) {
            this.fail_msg = fail_msg;
        }

        public String getFail_time() {
            return fail_time;
        }

        public void setFail_time(String fail_time) {
            this.fail_time = fail_time;
        }

        public String getAdd_time() {
            return add_time;
        }

        public void setAdd_time(String add_time) {
            this.add_time = add_time;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getWechat() {
            return wechat;
        }

        public void setWechat(String wechat) {
            this.wechat = wechat;
        }
    }
}
