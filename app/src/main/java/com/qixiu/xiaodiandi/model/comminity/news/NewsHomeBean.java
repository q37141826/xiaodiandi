package com.qixiu.xiaodiandi.model.comminity.news;

import android.os.Parcel;
import android.os.Parcelable;

import com.qixiu.qixiu.recyclerview_lib.ItemTypesInterf;
import com.qixiu.qixiu.request.bean.BaseBean;

import java.util.List;

public class NewsHomeBean extends BaseBean<NewsHomeBean.OBean> {



    public static class OBean {
        private List<BannerBean> banner;
        private List<VideoBean> video;
        private List<DynamicBean> dynamic;
        private List<InforBean> infor;

        public List<BannerBean> getBanner() {
            return banner;
        }

        public void setBanner(List<BannerBean> banner) {
            this.banner = banner;
        }

        public List<VideoBean> getVideo() {
            return video;
        }

        public void setVideo(List<VideoBean> video) {
            this.video = video;
        }

        public List<DynamicBean> getDynamic() {
            return dynamic;
        }

        public void setDynamic(List<DynamicBean> dynamic) {
            this.dynamic = dynamic;
        }

        public List<InforBean> getInfor() {
            return infor;
        }

        public void setInfor(List<InforBean> infor) {
            this.infor = infor;
        }

        public static class BannerBean {
            /**
             * titile : banner1
             * typeName : 跳转链接
             * type : 2
             * url : http://xdd.qixiuu.com/admin/index/index.html
             * pic : http://xdd.qixiuu.com/public/uploads/editor/20181203/5c04cea177b7c.png
             */

            private String titile;
            private String typeName;
            private String type;
            private String url;
            private String pic;

            public String getTitile() {
                return titile;
            }

            public void setTitile(String titile) {
                this.titile = titile;
            }

            public String getTypeName() {
                return typeName;
            }

            public void setTypeName(String typeName) {
                this.typeName = typeName;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }

            public String getPic() {
                return pic;
            }

            public void setPic(String pic) {
                this.pic = pic;
            }
        }

        public static class VideoBean implements ItemTypesInterf, Parcelable {
            /**
             * id : 158
             * title : hahahhaha
             * image_input : http://xdd.qixiuu.com/public/uploads/attach/2018/12/03/s_5c04ade0c362a.jpg
             * visit : 0
             */
            private int type;//adapter viewtype
            private  int layoutRes;
            private int id;
            private String title;
            private String image_input;
            private String visit;

            @Override
            public int getType() {
                return type;
            }

            @Override
            public void setType(int type) {
                this.type = type;
            }

            @Override
            public int getLayoutRes() {
                return layoutRes;
            }

            @Override
            public void setLayoutRes(int layoutRes) {
                this.layoutRes = layoutRes;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getImage_input() {
                return image_input;
            }

            public void setImage_input(String image_input) {
                this.image_input = image_input;
            }

            public String getVisit() {
                return visit;
            }

            public void setVisit(String visit) {
                this.visit = visit;
            }

            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(Parcel dest, int flags) {
                dest.writeInt(this.type);
                dest.writeInt(this.layoutRes);
                dest.writeInt(this.id);
                dest.writeString(this.title);
                dest.writeString(this.image_input);
                dest.writeString(this.visit);
            }

            public VideoBean() {
            }

            protected VideoBean(Parcel in) {
                this.type = in.readInt();
                this.layoutRes = in.readInt();
                this.id = in.readInt();
                this.title = in.readString();
                this.image_input = in.readString();
                this.visit = in.readString();
            }

            public static final Parcelable.Creator<VideoBean> CREATOR = new Parcelable.Creator<VideoBean>() {
                @Override
                public VideoBean createFromParcel(Parcel source) {
                    return new VideoBean(source);
                }

                @Override
                public VideoBean[] newArray(int size) {
                    return new VideoBean[size];
                }
            };
        }

        public static class DynamicBean  implements NewsListInterf, Parcelable {
            /**
             * id : 156
             * title : ff
             * image_input : http://xdd.qixiuu.com/public/uploads/editor/20181203/s_5c04ce7d95575.png
             */

            private int id;
            private String title;
            private String image_input;
            private String type;

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getImage_input() {
                return image_input;
            }

            public void setImage_input(String image_input) {
                this.image_input = image_input;
            }

            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(Parcel dest, int flags) {
                dest.writeInt(this.id);
                dest.writeString(this.title);
                dest.writeString(this.image_input);
                dest.writeString(this.type);
            }

            public DynamicBean() {
            }

            protected DynamicBean(Parcel in) {
                this.id = in.readInt();
                this.title = in.readString();
                this.image_input = in.readString();
                this.type = in.readString();
            }

            public static final Parcelable.Creator<DynamicBean> CREATOR = new Parcelable.Creator<DynamicBean>() {
                @Override
                public DynamicBean createFromParcel(Parcel source) {
                    return new DynamicBean(source);
                }

                @Override
                public DynamicBean[] newArray(int size) {
                    return new DynamicBean[size];
                }
            };
        }

        public static class InforBean  implements NewsListInterf, Parcelable {
            /**
             * id : 159
             * title : ceshiyixia
             * image_input : http://xdd.qixiuu.com/public/uploads/attach/2018/12/03/s_5c04acb26dd9c.jpg
             * add_time : 2019-02-18
             */


            private String type;
            private int id;
            private String title;
            private String image_input;
            private String add_time;

            @Override
            public String getType() {
                return type;
            }

            @Override
            public void setType(String type) {
                this.type = type;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getImage_input() {
                return image_input;
            }

            public void setImage_input(String image_input) {
                this.image_input = image_input;
            }

            public String getAdd_time() {
                return add_time;
            }

            public void setAdd_time(String add_time) {
                this.add_time = add_time;
            }

            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(Parcel dest, int flags) {
                dest.writeString(this.type);
                dest.writeInt(this.id);
                dest.writeString(this.title);
                dest.writeString(this.image_input);
                dest.writeString(this.add_time);
            }

            public InforBean() {
            }

            protected InforBean(Parcel in) {
                this.type = in.readString();
                this.id = in.readInt();
                this.title = in.readString();
                this.image_input = in.readString();
                this.add_time = in.readString();
            }

            public static final Parcelable.Creator<InforBean> CREATOR = new Parcelable.Creator<InforBean>() {
                @Override
                public InforBean createFromParcel(Parcel source) {
                    return new InforBean(source);
                }

                @Override
                public InforBean[] newArray(int size) {
                    return new InforBean[size];
                }
            };
        }
    }
}
