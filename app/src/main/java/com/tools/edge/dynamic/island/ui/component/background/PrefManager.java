package com.tools.edge.dynamic.island.ui.component.background;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.preference.PreferenceManager;
import com.google.gson.Gson;
import com.tools.edge.dynamic.island.ui.component.entity.AppPackageList;
import com.tools.edge.dynamic.island.ui.component.utils.Constants;


public class PrefManager {
    public static final String DEFAULT_SELECTED_COLOR = "#ffffff";
    public static final String CAM_COUNT = "CAM_COUNT";
    public static final String CAM_POS = "CAM_POS";
    public static final String DATA_DEFAULT_COLOR = "DATA_DEFAULT_COLOR";
    public static final String FILTER_PKG = "FILTER_PKG";
    public static final String SHOW_DESIGN_CALL = "IPHONE_CALL";
    public static final String KEY_ALBUMS = "albums";
    public static final String KEY_DEFAULT_COLOR = "default_color";
    public static final String KEY_GALLERY_NAME = "gallery_name";
    public static final String KEY_GOOGLE_USERNAME = "google_username";
    public static final String KEY_NO_OF_COLUMNS = "no_of_columns";
    public static final String KEY_NO_OF_COLUMNS_CATEGORY = "no_of_columns_category";
    public static final String PREF_NAME = "AwesomeWallpapers";
    public static final String SELECTED_PKG = "SELECTED_PKG";
    public static final String TAG = "PrefManager";
    public static final String TORCH_DEFAULT_COLOR = "TORCH_DEFAULT_COLOR";
    public static final String WIFI_DEFAULT_COLOR = "WIFI_DEFAULT_COLOR";
    public static final String Y_HEIGHT = "Y_HEIGHT";
    public static final String Y_POS = "Y_POS";
    public static final String HINDDEN_SHOW_DESIGN_CALL_DEFAULT = "hindden_show_design_call_default";
    public static final String SHOW_TIME_CALL = "show_time_call";
    public static final String SHOW_ACTION = "show_action";
    public static final String SHOW_NOTIFICATION = "show_notification";
    public static final String SHOW_QUICK_DISPLAY = "show_quick_display";
    public static final String CLICK_ABLE = "clickable";

    int PRIVATE_MODE = 0;
    Context _context;
    SharedPreferences.Editor editor;
    public SharedPreferences pref;

    public PrefManager(Context context) {
        this._context = context;
        this.pref = PreferenceManager.getDefaultSharedPreferences(context);
    }

    public static void setCallPkg(Context context, AppPackageList appPackageList) {
        SharedPreferences.Editor edit = androidx.preference.PreferenceManager.getDefaultSharedPreferences(context.getApplicationContext()).edit();
        edit.putString(SELECTED_PKG, new Gson().toJson((Object) appPackageList));
        edit.apply();
    }

    public static AppPackageList getCallPkg(Context context) {
        return (AppPackageList) new Gson().fromJson(androidx.preference.PreferenceManager.getDefaultSharedPreferences(context.getApplicationContext()).getString(SELECTED_PKG, ""), AppPackageList.class);
    }

    public static void setFilterPkg(Context context, AppPackageList appPackageList) {
        SharedPreferences.Editor edit = androidx.preference.PreferenceManager.getDefaultSharedPreferences(context.getApplicationContext()).edit();
        edit.putString(FILTER_PKG, new Gson().toJson((Object) appPackageList));
        edit.apply();
    }

    public static AppPackageList getSelectedFilterPkg(Context context) {
        return (AppPackageList) new Gson().fromJson(androidx.preference.PreferenceManager.getDefaultSharedPreferences(context.getApplicationContext()).getString(FILTER_PKG, ""), AppPackageList.class);
    }

    public void setKeyDefaultColor(int i) {
        this.pref.edit().putInt(KEY_DEFAULT_COLOR, i).apply();
    }

    public int getDefaultColor() {
        return this.pref.getInt(KEY_DEFAULT_COLOR, Color.parseColor(DEFAULT_SELECTED_COLOR));
    }

    public void setWifiDefaultColor(int i) {
        this.pref.edit().putInt(WIFI_DEFAULT_COLOR, i).apply();
    }

    public int getWifiDefaultColor() {
        return this.pref.getInt(WIFI_DEFAULT_COLOR, Color.parseColor("#1690fe"));
    }

    public void setTorchDefaultColor(int i) {
        this.pref.edit().putInt(TORCH_DEFAULT_COLOR, i).apply();
    }

    public int getTorchDefaultColor() {
        return this.pref.getInt(TORCH_DEFAULT_COLOR, Color.parseColor("#FF9A1E"));
    }

    public void setDataDefaultColor(int i) {
        this.pref.edit().putInt(DATA_DEFAULT_COLOR, i).apply();
    }

    public int getDataDefaultColor() {
        return this.pref.getInt(DATA_DEFAULT_COLOR, Color.parseColor("#04CB69"));
    }

    public int getNoOfGridColumns() {
        return this.pref.getInt(KEY_NO_OF_COLUMNS, 3);
    }

    public int getNoOfGridColumnsCategory() {
        return this.pref.getInt(KEY_NO_OF_COLUMNS_CATEGORY, 3);
    }

    public String getGalleryName() {
        return this.pref.getString(KEY_GALLERY_NAME, AppConst.SDCARD_DIR_NAME);
    }

    public void setCameraCount(int i) {
        this.pref.edit().putInt(CAM_COUNT, i).apply();
    }

    public int getCameraCount() {
        return this.pref.getInt(CAM_COUNT, 1);
    }

    public void setCameraPos(int i) {
        this.pref.edit().putInt(CAM_POS, i).apply();
    }

    public int getCameraPos() {
        return this.pref.getInt(CAM_POS, 2);
    }

    public void setYPosOfIsland(int i) {
        this.pref.edit().putInt(Y_POS, i).apply();
    }

    public int getYPosOfIsland() {
        return this.pref.getInt(Y_POS, 5);
    }

    public void setHeightOfIsland(int i) {
        this.pref.edit().putInt(Y_HEIGHT, i).apply();
    }

    public int getHeightOfIsland() {
        return this.pref.getInt(Y_HEIGHT, 25);
    }

    public boolean getShowCall() {
        return this.pref.getBoolean(SHOW_DESIGN_CALL, true);
    }

    public void setShowCall(boolean z) {
        this.pref.edit().putBoolean(SHOW_DESIGN_CALL, z).apply();
    }


    public boolean getShowTimeCall() {
        return this.pref.getBoolean(SHOW_TIME_CALL, true);
    }

    public void setShowTimeCall(boolean z) {
        this.pref.edit().putBoolean(SHOW_TIME_CALL, z).apply();
    }


    public boolean getHiddenCallDefault() {
        return this.pref.getBoolean(HINDDEN_SHOW_DESIGN_CALL_DEFAULT, false);
    }

    public void setHiddenCallDefault(boolean z) {
        this.pref.edit().putBoolean(HINDDEN_SHOW_DESIGN_CALL_DEFAULT, z).apply();
    }

    public boolean getShowNotificationMusic() {
        return this.pref.getBoolean(Constants.KEY_SHOW_MUSIC, true);
    }

    public void setShowNotificationMusic(boolean isShow) {
        this.pref.edit().putBoolean(Constants.KEY_SHOW_MUSIC, isShow).apply();
    }

    public boolean getShowNotificationMusicRotate() {
        return this.pref.getBoolean(Constants.KEY_SHOW_MUSIC_ROATE, true);
    }

    public void setShowNotificationMusicRotate(boolean isShow) {
        this.pref.edit().putBoolean(Constants.KEY_SHOW_MUSIC_ROATE, isShow).apply();
    }


    public boolean getShowNotificationBatteryChange() {
        return this.pref.getBoolean(Constants.KEY_SHOW_BATTERYCHANGE, true);
    }

    public void setShowNotificationBatteryChange(boolean isShow) {
        this.pref.edit().putBoolean(Constants.KEY_SHOW_BATTERYCHANGE, isShow).apply();
    }


    public boolean getShowNotificationRingMode() {
        return this.pref.getBoolean(Constants.KEY_SHOW_RINGTONE_MODE, true);
    }

    public void setShowNotificationRingMode(boolean isShow) {
        this.pref.edit().putBoolean(Constants.KEY_SHOW_RINGTONE_MODE, isShow).apply();
    }


    public boolean getShowNotificationHeadset() {
        return this.pref.getBoolean(Constants.KEY_SHOW_RINGTONE_MODE, true);
    }

    public void setShowNotificationHeadset(boolean isShow) {
        this.pref.edit().putBoolean(Constants.KEY_SHOW_RINGTONE_MODE, isShow).apply();
    }

    public boolean getShowNotificationNavigation() {
        return this.pref.getBoolean(Constants.KEY_SHOW_NAVIGATION, true);
    }

    public void setShowNotificationNavigation(boolean isShow) {
        this.pref.edit().putBoolean(Constants.KEY_SHOW_NAVIGATION, isShow).apply();
    }


    public void setListDisable(String listPackage) {
        this.pref.edit().putString(Constants.LIST_PACKAGE_DISABLE, listPackage).apply();
    }

    public String getListDisable() {
        return this.pref.getString(Constants.LIST_PACKAGE_DISABLE, "");
    }


    public boolean getShowOnLock() {
        return this.pref.getBoolean(Constants.SHOW_IN_LOCK, true);
    }

    public void setShowOnLock(boolean isShowOnLock) {
        pref.edit().putBoolean(Constants.SHOW_IN_LOCK, isShowOnLock).apply();
    }

    public boolean getShowAction() {
        return this.pref.getBoolean(SHOW_ACTION, true);
    }

    public void setShowAction(boolean isShowAction) {
        pref.edit().putBoolean(SHOW_ACTION, isShowAction).apply();
    }

    public boolean getShowNotification() {
        return this.pref.getBoolean(SHOW_NOTIFICATION, true);
    }

    public void setShowNotification(boolean isShowAction) {
        pref.edit().putBoolean(SHOW_NOTIFICATION, isShowAction).apply();
    }


    public boolean getShowQuickDisplay() {
        return this.pref.getBoolean(SHOW_QUICK_DISPLAY, true);
    }

    public void setShowQuickDisplay(boolean isShowAction) {
        pref.edit().putBoolean(SHOW_QUICK_DISPLAY, isShowAction).apply();
    }


    public boolean getClickAble() {
        return this.pref.getBoolean(CLICK_ABLE, true);
    }

    public void setClickAble(boolean isClickAble) {
        pref.edit().putBoolean(CLICK_ABLE, isClickAble).apply();
    }
}
