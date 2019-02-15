package com.qixiu.xiaodiandi.model.mine.points;

import com.qixiu.qixiu.request.bean.BaseBean;

import java.util.List;

//
public class PointsRecordsBean extends BaseBean<List<PointsRecordsBean.OBean>> {


    public static class OBean {
        /**
         * id : 11
         * uid : 181
         * type : 5
         * oid : 0
         * integral : 1
         * add : 1
         * content : 签到获得1积分
         * addtime : 1550197936
         * state : 1
         */

        private int id;
        private int uid;
        private int type;
        private int oid;
        private Double integral;
        private int add;
        private String content;
        private String addtime;
        private int state;

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

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public int getOid() {
            return oid;
        }

        public void setOid(int oid) {
            this.oid = oid;
        }

        public Double getIntegral() {
            return integral;
        }

        public void setIntegral(Double integral) {
            this.integral = integral;
        }

        public int getAdd() {
            return add;
        }

        public void setAdd(int add) {
            this.add = add;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getAddtime() {
            return addtime;
        }

        public void setAddtime(String addtime) {
            this.addtime = addtime;
        }

        public int getState() {
            return state;
        }

        public void setState(int state) {
            this.state = state;
        }
    }
}