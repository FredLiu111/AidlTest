package com.example.ecarx.aidldemo.bean;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.ArrayList;

/**
 * Created by ecarx on 2017/3/28.
 */

public class Albums implements Parcelable {

    private int albumsId;    //专辑ID
    private String albumsName;   //专辑名字
    private ArrayList<String> singsList;   //专辑歌曲列表
    private int playSingingId;   //当前正在播放的歌曲id

    public Albums() {

    }
    private Albums(Parcel in) {
        albumsId = in.readInt();
        albumsName = in.readString();
        singsList = in.readArrayList(ArrayList.class.getClassLoader());
        playSingingId = in.readInt();
    }

    public Albums(int albumsId, String albumsName, ArrayList<String> singsList, int playSingingId) {
        this.albumsId = albumsId;
        this.albumsName = albumsName;
        this.singsList = singsList;
        this.playSingingId = playSingingId;
    }

    public String getAlbumsName() {
        return albumsName;
    }

    public void setAlbumsName(String albumsName) {
        this.albumsName = albumsName;
    }

    public int getAlbumsId() {
        return albumsId;
    }

    public void setAlbumsId(int albumsId) {
        this.albumsId = albumsId;
    }

    public ArrayList<String> getSingsList() {
        return singsList;
    }

    public void setSingsList(ArrayList<String> singsList) {
        this.singsList = singsList;
    }

    public int getPlaySingingId() {
        return playSingingId;
    }

    public void setPlaySingingId(int playSingingId) {
        this.playSingingId = playSingingId;
    }


    public static final Creator<Albums> CREATOR = new Creator<Albums>() {
        @Override
        public Albums createFromParcel(Parcel in) {
            return new Albums(in);
        }

        @Override
        public Albums[] newArray(int size) {
            return new Albums[size];
        }
    };

    /**
     * 重写describeContents方法，内容接口描述，默认返回0就可以
     */
    @Override
    public int describeContents() {
        return 0;
    }

    /**
     * 重写writeToParcel方法，将你的对象序列化为一个Parcel对象，
     * 即：将类的数据写入外部提供的Parcel中，打包需要传递的数据到Parcel容器保存，以便从 Parcel容器获取数据
     */
    @Override
    public void writeToParcel(Parcel out, int i) {
        out.writeInt(albumsId);
        out.writeString(albumsName);
        out.writeList(singsList);
        out.writeInt(playSingingId);
    }
}
