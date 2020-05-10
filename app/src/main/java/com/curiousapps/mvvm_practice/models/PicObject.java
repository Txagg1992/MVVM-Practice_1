package com.curiousapps.mvvm_practice.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PicObject {
//    private String user;//publisher
//    private String tags;//ingredients
//    private String id; //recipe_id
//    private String webformatURL;// image_url
//    private String pageURL;
//    private int likes; //social_rank
//
//    public PicObject(String user, String tags, String id, String webformatURL, String pageURL, int likes) {
//        this.user = user;
//        this.tags = tags;
//        this.id = id;
//        this.webformatURL = webformatURL;
//        this.pageURL = pageURL;
//        this.likes = likes;
//    }
//
//    public PicObject() {
//    }
//
//    protected PicObject(Parcel in) {
//        user = in.readString();
//        tags = in.readString();
//        id = in.readString();
//        webformatURL = in.readString();
//        pageURL = in.readString();
//        likes = in.readInt();
//    }
//
//    public static final Parcelable.Creator<PicObject> CREATOR = new Parcelable.Creator<PicObject>() {
//        @Override
//        public PicObject createFromParcel(Parcel in) {
//            return new PicObject(in);
//        }
//
//        @Override
//        public PicObject[] newArray(int size) {
//            return new PicObject[size];
//        }
//    };
//
//    public String getUser() {
//        return user;
//    }
//
//    public void setUser(String user) {
//        this.user = user;
//    }
//
//    public String getTags() {
//        return tags;
//    }
//
//    public void setTags(String tags) {
//        this.tags = tags;
//    }
//
//    public String getId() {
//        return id;
//    }
//
//    public void setId(String id) {
//        this.id = id;
//    }
//
//    public String getWebformatURL() {
//        return webformatURL;
//    }
//
//    public void setWebformatURL(String webformatURL) {
//        this.webformatURL = webformatURL;
//    }
//
//    public String getPageURL() {
//        return pageURL;
//    }
//
//    public void setPageURL(String pageURL) {
//        this.pageURL = pageURL;
//    }
//
//    public int getLikes() {
//        return likes;
//    }
//
//    public void setLikes(int likes) {
//        this.likes = likes;
//    }
//
//    @Override
//    public String toString() {
//        return "PicObject{" +
//                "user='" + user + '\'' +
//                ", tags='" + tags + '\'' +
//                ", id='" + id + '\'' +
//                ", webformatURL='" + webformatURL + '\'' +
//                ", pageUrl='" + pageURL + '\'' +
//                ", likes=" + likes +
//                '}';
//    }
//
////    @Override
////    public int describeContents() {
////        return 0;
////    }
////
////    @Override
////    public void writeToParcel(Parcel dest, int flags) {
////        dest.writeString(user);
////        dest.writeString(tags);
////        dest.writeString(id);
////        dest.writeString(webformatURL);
////        dest.writeString(pageURL);
////        dest.writeInt(likes);
////    }
//
////    @SerializedName("id")
////    @Expose
////    private int id;
////    @SerializedName("username")
////    @Expose
////    private String userName;
////
////    public TypiCode(int id, String userName) {
////        this.id = id;
////        this.userName = userName;
////    }
////
////    public TypiCode() {
////    }
////
////    public int getId() {
////        return id;
////    }
////
////    public void setId(int id) {
////        this.id = id;
////    }
////
////    public String getUserName() {
////        return userName;
////    }
////
////    public void setUserName(String userName) {
////        this.userName = userName;
////    }
////
////    @Override
////    public String toString() {
////        return "TypiCode{" +
////                "id=" + id +
////                ", userName='" + userName + '\'' +
////                '}';
////    }
}
