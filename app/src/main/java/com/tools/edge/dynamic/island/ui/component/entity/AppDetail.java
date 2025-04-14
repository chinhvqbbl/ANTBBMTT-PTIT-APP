package com.tools.edge.dynamic.island.ui.component.entity;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class AppDetail implements Parcelable {
    public static final Creator<AppDetail> CREATOR = new Creator<AppDetail>() {
        public AppDetail createFromParcel(Parcel parcel) {
            return new AppDetail(parcel);
        }

        public AppDetail[] newArray(int i) {
            return new AppDetail[i];
        }
    };
    public String activityInfoName;
    public int image;
    public boolean isCurrentUser;
    public boolean isSelected;
    public boolean isSorted;
    public String label;
    public String pkg;

    public Drawable iconImage;

    public AppDetail(String activityInfoName, int image, boolean isCurrentUser, boolean isSelected, boolean isSorted, String label, String pkg, Drawable iconImage) {
        this.activityInfoName = activityInfoName;
        this.image = image;
        this.isCurrentUser = isCurrentUser;
        this.isSelected = isSelected;
        this.isSorted = isSorted;
        this.label = label;
        this.pkg = pkg;
        this.iconImage = iconImage;
    }

    public String getActivityInfoName() {
        return activityInfoName;
    }

    public void setActivityInfoName(String activityInfoName) {
        this.activityInfoName = activityInfoName;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public boolean isCurrentUser() {
        return isCurrentUser;
    }

    public void setCurrentUser(boolean currentUser) {
        isCurrentUser = currentUser;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public boolean isSorted() {
        return isSorted;
    }

    public void setSorted(boolean sorted) {
        isSorted = sorted;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getPkg() {
        return pkg;
    }

    public void setPkg(String pkg) {
        this.pkg = pkg;
    }

    public Drawable getIconImage() {
        return iconImage;
    }

    public void setIconImage(Drawable iconImage) {
        this.iconImage = iconImage;
    }

    public int describeContents() {
        return 0;
    }

    public AppDetail() {
//       //  this.isSelected = false;
//        this.isCurrentUser = true;
    }

    public AppDetail(String str, String str2, String str3, String str4, boolean z, boolean z2) {
//       //  this.isSelected = false;
        this.label = str2;
        this.pkg = str3;
        this.activityInfoName = str4;
        this.isCurrentUser = z2;
    }

    public AppDetail(String str, String str2, String str3, String str4, boolean z, boolean z2, int i) {
       //  this.isSelected = false;
        this.pkg = str;
        this.label = str2;
        this.activityInfoName = str3;
        this.isCurrentUser = z;
        this.image = i;
    }

    public AppDetail(long j, String str, String str2, String str3, String str4, boolean z, boolean z2, boolean z3) {
       //  this.isSelected = false;
        this.label = str2;
        this.pkg = str3;
        this.activityInfoName = str4;
        this.isCurrentUser = z3;
    }

    private AppDetail(Parcel parcel) {
        boolean z = false;
       //  this.isSelected = false;
        this.isCurrentUser = true;
        this.label = parcel.readString();
        this.pkg = parcel.readString();
        this.activityInfoName = parcel.readString();
        this.image = parcel.readInt();
        this.isSorted = parcel.readByte() != 0;
        this.isSelected = parcel.readByte() != 0;
        this.isCurrentUser = parcel.readByte() != 0 ? true : z;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.label);
        parcel.writeString(this.pkg);
        parcel.writeString(this.activityInfoName);
        parcel.writeInt(this.image);
        parcel.writeByte(this.isSorted ? (byte) 1 : 0);
        parcel.writeByte(this.isSelected ? (byte) 1 : 0);
        parcel.writeByte(this.isCurrentUser ? (byte) 1 : 0);
    }

    @Override
    public String toString() {
        return "AppDetail{" +
                "activityInfoName='" + activityInfoName + '\'' +
                ", image=" + image +
                ", isCurrentUser=" + isCurrentUser +
                ", isSelected=" + isSelected +
                ", isSorted=" + isSorted +
                ", label='" + label + '\'' +
                ", pkg='" + pkg + '\'' +
                ", iconImage=" + iconImage +
                '}';
    }
}
