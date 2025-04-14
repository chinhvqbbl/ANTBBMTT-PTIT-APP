package com.tools.edge.dynamic.island.ui.component.entity;

import android.app.PendingIntent;
import android.graphics.Bitmap;


import com.tools.edge.dynamic.island.ui.component.service.ActionParsable;

import java.util.ArrayList;
import java.util.HashMap;

public class Notification {
    public ArrayList<ActionParsable> actions;
    public String app_name;
    public CharSequence bigText;
    public String category = "";
    public int color;
    public int count;
    public long duration;
    public String groupKey;
    public Bitmap icon;
    public String id;
    public CharSequence info_text;
    public boolean isAppGroup;
    public boolean isChronometerRunning = false;
    public boolean isClearable = false;
    public boolean isGroup;
    public boolean isGroupConversation;
    public boolean isLocal = false;
    public boolean isOngoing;
    public String key;
    public HashMap<String, Notification> keyMap;
    public int local_left_icon;
    public int local_right_icon;
    public String pack = "";
    public PendingIntent pendingIntent;
    public Bitmap picture;
    public long position;
    public long postTime;
    public int progress;
    public boolean progressIndeterminate;
    public int progressMax = 0;
    public Bitmap senderIcon;
    public boolean showChronometer = false;
    public CharSequence subText;
    public CharSequence substName;
    public CharSequence summaryText;
    public String tag;
    public String template = "";
    public CharSequence titleBig;
    public CharSequence tv_text;
    public CharSequence tv_title;
    public String type = "";
    public int uId;
    public boolean useIphoneCallDesign;

    public Notification(String str, Bitmap bmIcon, Bitmap bitmap2, CharSequence charSequence, CharSequence charSequence2, int i, String pack, long j, PendingIntent pendingIntent2, ArrayList<ActionParsable> arrayList, CharSequence charSequence3, String appName, boolean isClearable, int i2, Bitmap bmPicture, String str4, String str5, boolean z2, boolean z3, boolean z4, boolean z5, String str6, int i3, String str7, CharSequence substName, CharSequence subText, CharSequence titleBig, CharSequence charSequence7, int i4, int i5, boolean z6, CharSequence charSequence8, boolean z7, String category) {
        this.keyMap = new HashMap<>();
        this.type = "";
        this.local_left_icon = 0;
        this.local_right_icon = 0;
        this.isLocal = false;
        this.useIphoneCallDesign = false;
        this.isChronometerRunning = false;
        this.id = str;
        this.icon = bmIcon;
        this.senderIcon = bitmap2;
        this.tv_title = charSequence;
        this.tv_text = charSequence2;
        this.count = i;
        this.pack = pack;
        this.postTime = j;
        this.pendingIntent = pendingIntent2;
        this.actions = arrayList;
        this.bigText = charSequence3;
        this.app_name = appName;
        this.isClearable = isClearable;
        this.color = i2;
        this.picture = bmPicture;
        this.groupKey = str4;
        this.key = str5;
        this.isGroupConversation = z2;
        this.isAppGroup = z3;
        this.isGroup = z4;
        this.isOngoing = z5;
        this.tag = str6;
        this.uId = i3;
        this.template = str7;
        this.substName = substName;
        this.subText = subText;
        this.titleBig = titleBig;
        this.info_text = charSequence7;
        this.progressMax = i4;
        this.progress = i5;
        this.progressIndeterminate = z6;
        this.summaryText = charSequence8;
        this.showChronometer = z7;
        this.category = category;
    }

    public ArrayList<ActionParsable> getActions() {
        return actions;
    }

    public void setActions(ArrayList<ActionParsable> actions) {
        this.actions = actions;
    }

    public String getApp_name() {
        return app_name;
    }

    public void setApp_name(String app_name) {
        this.app_name = app_name;
    }

    public CharSequence getBigText() {
        return bigText;
    }

    public void setBigText(CharSequence bigText) {
        this.bigText = bigText;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }

    public String getGroupKey() {
        return groupKey;
    }

    public void setGroupKey(String groupKey) {
        this.groupKey = groupKey;
    }

    public Bitmap getIcon() {
        return icon;
    }

    public void setIcon(Bitmap icon) {
        this.icon = icon;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public CharSequence getInfo_text() {
        return info_text;
    }

    public void setInfo_text(CharSequence info_text) {
        this.info_text = info_text;
    }

    public boolean isAppGroup() {
        return isAppGroup;
    }

    public void setAppGroup(boolean appGroup) {
        isAppGroup = appGroup;
    }

    public boolean isChronometerRunning() {
        return isChronometerRunning;
    }

    public void setChronometerRunning(boolean chronometerRunning) {
        isChronometerRunning = chronometerRunning;
    }

    public boolean isClearable() {
        return isClearable;
    }

    public void setClearable(boolean clearable) {
        isClearable = clearable;
    }

    public boolean isGroup() {
        return isGroup;
    }

    public void setGroup(boolean group) {
        isGroup = group;
    }

    public boolean isGroupConversation() {
        return isGroupConversation;
    }

    public void setGroupConversation(boolean groupConversation) {
        isGroupConversation = groupConversation;
    }

    public boolean isLocal() {
        return isLocal;
    }

    public void setLocal(boolean local) {
        isLocal = local;
    }

    public boolean isOngoing() {
        return isOngoing;
    }

    public void setOngoing(boolean ongoing) {
        isOngoing = ongoing;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public HashMap<String, Notification> getKeyMap() {
        return keyMap;
    }

    public void setKeyMap(HashMap<String, Notification> keyMap) {
        this.keyMap = keyMap;
    }

    public int getLocal_left_icon() {
        return local_left_icon;
    }

    public void setLocal_left_icon(int local_left_icon) {
        this.local_left_icon = local_left_icon;
    }

    public int getLocal_right_icon() {
        return local_right_icon;
    }

    public void setLocal_right_icon(int local_right_icon) {
        this.local_right_icon = local_right_icon;
    }

    public String getPack() {
        return pack;
    }

    public void setPack(String pack) {
        this.pack = pack;
    }

    public PendingIntent getPendingIntent() {
        return pendingIntent;
    }

    public void setPendingIntent(PendingIntent pendingIntent) {
        this.pendingIntent = pendingIntent;
    }

    public Bitmap getPicture() {
        return picture;
    }

    public void setPicture(Bitmap picture) {
        this.picture = picture;
    }

    public long getPosition() {
        return position;
    }

    public void setPosition(long position) {
        this.position = position;
    }

    public long getPostTime() {
        return postTime;
    }

    public void setPostTime(long postTime) {
        this.postTime = postTime;
    }

    public int getProgress() {
        return progress;
    }

    public void setProgress(int progress) {
        this.progress = progress;
    }

    public boolean isProgressIndeterminate() {
        return progressIndeterminate;
    }

    public void setProgressIndeterminate(boolean progressIndeterminate) {
        this.progressIndeterminate = progressIndeterminate;
    }

    public int getProgressMax() {
        return progressMax;
    }

    public void setProgressMax(int progressMax) {
        this.progressMax = progressMax;
    }

    public Bitmap getSenderIcon() {
        return senderIcon;
    }

    public void setSenderIcon(Bitmap senderIcon) {
        this.senderIcon = senderIcon;
    }

    public boolean isShowChronometer() {
        return showChronometer;
    }

    public void setShowChronometer(boolean showChronometer) {
        this.showChronometer = showChronometer;
    }

    public CharSequence getSubText() {
        return subText;
    }

    public void setSubText(CharSequence subText) {
        this.subText = subText;
    }

    public CharSequence getSubstName() {
        return substName;
    }

    public void setSubstName(CharSequence substName) {
        this.substName = substName;
    }

    public CharSequence getSummaryText() {
        return summaryText;
    }

    public void setSummaryText(CharSequence summaryText) {
        this.summaryText = summaryText;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getTemplate() {
        return template;
    }

    public void setTemplate(String template) {
        this.template = template;
    }

    public CharSequence getTitleBig() {
        return titleBig;
    }

    public void setTitleBig(CharSequence titleBig) {
        this.titleBig = titleBig;
    }

    public CharSequence getTv_text() {
        return tv_text;
    }

    public void setTv_text(CharSequence tv_text) {
        this.tv_text = tv_text;
    }

    public CharSequence getTv_title() {
        return tv_title;
    }

    public void setTv_title(CharSequence tv_title) {
        this.tv_title = tv_title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getuId() {
        return uId;
    }

    public void setuId(int uId) {
        this.uId = uId;
    }

    public boolean isUseIphoneCallDesign() {
        return useIphoneCallDesign;
    }

    public void setUseIphoneCallDesign(boolean useIphoneCallDesign) {
        this.useIphoneCallDesign = useIphoneCallDesign;
    }

    public Notification(String str, int i, int i2) {
        this.keyMap = new HashMap<>();
        this.useIphoneCallDesign = false;
        this.isChronometerRunning = false;
        this.type = str;
        this.isLocal = true;
        this.local_left_icon = i;
        this.local_right_icon = i2;
    }

    @Override
    public String toString() {
        return "Notification{" +
                "actions=" + actions +
                ", app_name='" + app_name + '\'' +
                ", bigText=" + bigText +
                ", category='" + category + '\'' +
                ", color=" + color +
                ", count=" + count +
                ", duration=" + duration +
                ", groupKey='" + groupKey + '\'' +
                ", icon=" + icon +
                ", id='" + id + '\'' +
                ", info_text=" + info_text +
                ", isAppGroup=" + isAppGroup +
                ", isChronometerRunning=" + isChronometerRunning +
                ", isClearable=" + isClearable +
                ", isGroup=" + isGroup +
                ", isGroupConversation=" + isGroupConversation +
                ", isLocal=" + isLocal +
                ", isOngoing=" + isOngoing +
                ", key='" + key + '\'' +
                ", keyMap=" + keyMap +
                ", local_left_icon=" + local_left_icon +
                ", local_right_icon=" + local_right_icon +
                ", pack='" + pack + '\'' +
                ", pendingIntent=" + pendingIntent +
                ", picture=" + picture +
                ", position=" + position +
                ", postTime=" + postTime +
                ", progress=" + progress +
                ", progressIndeterminate=" + progressIndeterminate +
                ", progressMax=" + progressMax +
                ", senderIcon=" + senderIcon +
                ", showChronometer=" + showChronometer +
                ", subText=" + subText +
                ", substName=" + substName +
                ", summaryText=" + summaryText +
                ", tag='" + tag + '\'' +
                ", template='" + template + '\'' +
                ", titleBig=" + titleBig +
                ", tv_text=" + tv_text +
                ", tv_title=" + tv_title +
                ", type='" + type + '\'' +
                ", uId=" + uId +
                ", useIphoneCallDesign=" + useIphoneCallDesign +
                '}';
    }
}
