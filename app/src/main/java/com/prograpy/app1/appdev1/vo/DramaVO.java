package com.prograpy.app1.appdev1.vo;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class DramaVO implements Parcelable{

    @SerializedName("d_id")
    public int d_id = 0;
    @SerializedName("channel")
    public String channel = "";
    @SerializedName("d_name")
    public String d_name = "";
    @SerializedName("d_img")
    public String d_img = "";
    @SerializedName("d_act")
    public String d_act = "";


    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public String getD_name() {
        return d_name;
    }

    public void setD_name(String d_name) {
        this.d_name = d_name;
    }

    public String getD_img() {
        return d_img;
    }

    public void setD_img(String d_img) {
        this.d_img = d_img;
    }

    public String getD_act() {
        return d_act;
    }

    public void setD_act(String d_act) {
        this.d_act = d_act;
    }

    public int getD_id() {

        return d_id;
    }

    public void setD_id(int d_id) {
        this.d_id = d_id;
    }


    protected DramaVO(Parcel in) {
        d_id = in.readInt();
        channel = in.readString();
        d_name = in.readString();
        d_img = in.readString();
        d_act = in.readString();
    }

    public static final Creator<DramaVO> CREATOR = new Creator<DramaVO>() {
        @Override
        public DramaVO createFromParcel(Parcel in) {
            return new DramaVO(in);
        }

        @Override
        public DramaVO[] newArray(int size) {
            return new DramaVO[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(d_id);
        dest.writeString(channel);
        dest.writeString(d_name);
        dest.writeString(d_img);
        dest.writeString(d_act);
    }
}
