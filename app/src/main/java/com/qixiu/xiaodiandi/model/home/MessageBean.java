package com.qixiu.xiaodiandi.model.home;

import android.os.Parcel;
import android.os.Parcelable;

import com.qixiu.qixiu.request.bean.BaseBean;

import java.util.List;

public class MessageBean extends BaseBean<MessageBean.OBean> {




    public static class OBean {
        /**
         * lastpage : 1
         * list : [{"id":43,"user":"系统管理员","title":"22","content":"22","add_time":"2019-01-20 23:02:42","is_see":1},{"id":42,"user":"系统管理员","title":"11","content":"11","add_time":"2019-01-20 22:44:32","is_see":0}]
         */

        private int lastpage;
        private List<ListBean> list;

        public int getLastpage() {
            return lastpage;
        }

        public void setLastpage(int lastpage) {
            this.lastpage = lastpage;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean implements Parcelable {
            /**
             * id : 43
             * user : 系统管理员
             * title : 22
             * content : 22
             * add_time : 2019-01-20 23:02:42
             * is_see : 1
             */

            private int id;
            private String user;
            private String title;
            private String content;
            private String add_time;
            private int is_see;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getUser() {
                return user;
            }

            public void setUser(String user) {
                this.user = user;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public String getAdd_time() {
                return add_time;
            }

            public void setAdd_time(String add_time) {
                this.add_time = add_time;
            }

            public int getIs_see() {
                return is_see;
            }

            public void setIs_see(int is_see) {
                this.is_see = is_see;
            }

            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(Parcel dest, int flags) {
                dest.writeInt(this.id);
                dest.writeString(this.user);
                dest.writeString(this.title);
                dest.writeString(this.content);
                dest.writeString(this.add_time);
                dest.writeInt(this.is_see);
            }

            public ListBean() {
            }

            protected ListBean(Parcel in) {
                this.id = in.readInt();
                this.user = in.readString();
                this.title = in.readString();
                this.content = in.readString();
                this.add_time = in.readString();
                this.is_see = in.readInt();
            }

            public static final Parcelable.Creator<ListBean> CREATOR = new Parcelable.Creator<ListBean>() {
                @Override
                public ListBean createFromParcel(Parcel source) {
                    return new ListBean(source);
                }

                @Override
                public ListBean[] newArray(int size) {
                    return new ListBean[size];
                }
            };
        }
    }
}
