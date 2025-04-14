package com.tools.edge.dynamic.island.ui.component.background;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

public class WallpaperCategory implements Serializable {
    @SerializedName("category")
    ArrayList<WallpaperCategoryList> category;

    public ArrayList<WallpaperCategoryList> getCategory() {
        return this.category;
    }

    public void setCategory(ArrayList<WallpaperCategoryList> arrayList) {
        this.category = arrayList;
    }
}
