package com.curiousapps.mvvm_practice.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SchoolList implements Parcelable {

    @SerializedName("dbn")
    @Expose
    private String dbn;
    @SerializedName("school_name")
    @Expose
    private String school_name;
    @SerializedName("phone_number")
    @Expose
    private String phone_number;
    @SerializedName("website")
    @Expose
    private String website;
    @SerializedName("primary_address_line_1")
    @Expose
    private String primary_address_line_1;
    @SerializedName("city")
    @Expose
    private String city;
    @SerializedName("zip")
    @Expose
    private String zip;
    @SerializedName("state_code")
    @Expose
    private String state_code;
    @SerializedName("overview_paragraph")
    @Expose
    private String overview_paragraph;
    @SerializedName("school_email")
    @Expose
    private String school_email;

    public SchoolList(List<SchoolList> schoolList, String dbn, String school_name,
                      String phone_number, String website, String primary_address_line_1,
                      String city, String zip, String state_code, String overview_paragraph,
                      String school_email) {
        this.schoolList = schoolList;
        this.dbn = dbn;
        this.school_name = school_name;
        this.phone_number = phone_number;
        this.website = website;
        this.primary_address_line_1 = primary_address_line_1;
        this.city = city;
        this.zip = zip;
        this.state_code = state_code;
        this.overview_paragraph = overview_paragraph;
        this.school_email = school_email;
    }

    public SchoolList() {
    }

    protected SchoolList(Parcel in) {
        schoolList = in.createTypedArrayList(SchoolList.CREATOR);
        dbn = in.readString();
        school_name = in.readString();
        phone_number = in.readString();
        website = in.readString();
        primary_address_line_1 = in.readString();
        city = in.readString();
        zip = in.readString();
        state_code = in.readString();
        overview_paragraph = in.readString();
        school_email = in.readString();
    }

    public static final Creator<SchoolList> CREATOR = new Creator<SchoolList>() {
        @Override
        public SchoolList createFromParcel(Parcel in) {
            return new SchoolList(in);
        }

        @Override
        public SchoolList[] newArray(int size) {
            return new SchoolList[size];
        }
    };
    private List<SchoolList> schoolList;

    public List<SchoolList> getSchoolList() {
        return schoolList;
    }

    public void setSchoolList(List<SchoolList> schoolList) {
        this.schoolList = schoolList;
    }

    public String getDbn() {
        return dbn;
    }

    public void setDbn(String dbn) {
        this.dbn = dbn;
    }

    public String getSchool_name() {
        return school_name;
    }

    public void setSchool_name(String school_name) {
        this.school_name = school_name;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getPrimary_address_line_1() {
        return primary_address_line_1;
    }

    public void setPrimary_address_line_1(String primary_address_line_1) {
        this.primary_address_line_1 = primary_address_line_1;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getState_code() {
        return state_code;
    }

    public void setState_code(String state_code) {
        this.state_code = state_code;
    }

    public String getOverview_paragraph() {
        return overview_paragraph;
    }

    public void setOverview_paragraph(String overview_paragraph) {
        this.overview_paragraph = overview_paragraph;
    }

    public String getSchool_email() {
        return school_email;
    }

    public void setSchool_email(String school_email) {
        this.school_email = school_email;
    }

    @Override
    public String toString() {
        return "SchoolList{" +
                "schoolList=" + schoolList +
                ", dbn='" + dbn + '\'' +
                ", school_name='" + school_name + '\'' +
                ", phone_number='" + phone_number + '\'' +
                ", website='" + website + '\'' +
                ", primary_address_line_1='" + primary_address_line_1 + '\'' +
                ", city='" + city + '\'' +
                ", zip='" + zip + '\'' +
                ", state_code='" + state_code + '\'' +
                ", overview_paragraph='" + overview_paragraph + '\'' +
                ", school_email='" + school_email + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(schoolList);
        dest.writeString(dbn);
        dest.writeString(school_name);
        dest.writeString(phone_number);
        dest.writeString(website);
        dest.writeString(primary_address_line_1);
        dest.writeString(city);
        dest.writeString(zip);
        dest.writeString(state_code);
        dest.writeString(overview_paragraph);
        dest.writeString(school_email);
    }
}
