package com.example.topcelebritieslistview;

import android.os.Parcel;
import android.os.Parcelable;

class Celebrity implements Parcelable {
    private long id;
    private String aName;
    private String aLastName;
    private String aDate;
    //private int aImageRes;

    public Celebrity( String pName, String pLastName, String pDate) {
        aName = pName;
        aLastName = pLastName;
        aDate = pDate;
        //aImageRes = pImageRes;
    }
    public Celebrity( long id, String pName, String pLastName, String pDate) {
        this(pName,pLastName,pDate);
        this.id = id;
    }


    protected Celebrity(Parcel in) {
        id = in.readLong();
        aName = in.readString();
        aLastName = in.readString();
        aDate = in .readString();
        //aImageRes = in.readInt();
    }

    public static final Creator<Celebrity> CREATOR = new Creator<Celebrity>() {
        @Override
        public Celebrity createFromParcel(Parcel in) {
            return new Celebrity(in);
        }

        @Override
        public Celebrity[] newArray(int size) {
            return new Celebrity[size];
        }
    };

    public String getName() {
        return aName;
    }

    public void setName(String pName) {
        aName = pName;
    }

    public String getLastName() {
        return aLastName;
    }

    public void setLastName(String pLastName) {
        aLastName = pLastName;
    }

    public long getId() {
        return id;
    }

    public void setId(long pId) {
        id = pId;
    }

    public String getDate() {
        return aDate;
    }

    public void setDate(String pDate) {
        aDate = pDate;
    }

    //public int getImageRes() {
        //return aImageRes;
    //}

    //public void setImageRes(int pImageRes) {
       // aImageRes = pImageRes;
    //}


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(id);
        dest.writeString(aName);
        dest.writeString(aLastName);
        dest.writeString(aDate);
        //dest.writeInt(aImageRes);
    }


}
