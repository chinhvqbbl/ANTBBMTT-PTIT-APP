package com.tools.edge.dynamic.island.ui.component.service;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.PendingIntent;
import android.app.RemoteInput;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.service.notification.NotificationListenerService;
import android.service.notification.StatusBarNotification;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.WindowManager;

import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;
import androidx.core.content.ContextCompat;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import com.google.android.gms.common.internal.BaseGmsClient;
import com.tools.edge.dynamic.island.R;
import com.tools.edge.dynamic.island.ui.component.background.PrefManager;
import com.tools.edge.dynamic.island.ui.component.providers.InAppPurchaseProvider;
import com.tools.edge.dynamic.island.ui.component.utils.Constants;
import com.tools.edge.dynamic.island.ui.component.utils.Utils;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class NotificationService extends NotificationListenerService {
    private static final String TAG = "NotificationService";
    @SuppressLint("StaticFieldLeak")
    static NotificationService notificationService;
    Context context;
    Handler handler; // = new Handler();

    PrefManager prefManager;
    SharedPreferences preferences;


    public static NotificationService getInstance() {
        return notificationService;
    }

    public void initNotification() {
        StatusBarNotification[] statusBarNotifications = notificationService.getActiveNotifications();
        for (StatusBarNotification statusBarNotification : statusBarNotifications) {
            sendNotification(statusBarNotification, true);
        }
//        try {
//
//            Handler handler = new Handler();
//            long delayMillis = 500;
//            Runnable delayedRunnable = new Runnable() {
//                @Override
//                public void run() {
//                ITGAccessibilityService.getInstance().scrollToMusic();
//                }
//            };
//
//            handler.postDelayed(delayedRunnable, delayMillis);
//
//        } catch (Exception e) {
//
//        }

    }

    public String getPackageMusicPlaying() {
        StatusBarNotification[] statusBarNotifications = notificationService.getActiveNotifications();
        String packageNameMusic = "";
        for (StatusBarNotification statusBarNotification : statusBarNotifications) {
            if (statusBarNotification.getNotification().extras != null) {
                Bundle bundle = statusBarNotification.getNotification().extras;
                String str8;
                if (!bundle.containsKey(NotificationCompat.EXTRA_TEMPLATE) || bundle.get(NotificationCompat.EXTRA_TEMPLATE) == null) {
                    str8 = "";
                } else {
                    str8 = statusBarNotification.getNotification().extras.get(NotificationCompat.EXTRA_TEMPLATE).toString();
                    if (!TextUtils.isEmpty(str8)) {
                        str8 = str8.substring(str8.indexOf("$") + 1);
                    }
                }
                if (str8.equals("MediaStyle")) {
                    packageNameMusic = str8;
                    break;
                }


            }
        }
        return packageNameMusic;
    }

    public void onDestroy() {
        super.onDestroy();
    }

    public void onCreate() {
        super.onCreate();
        this.context = getApplicationContext();
        this.prefManager = new PrefManager(this);
        notificationService = this;
        handler = new Handler();
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void onListenerDisconnected() {
        super.onListenerDisconnected();
        NotificationListenerService.requestRebind(new ComponentName(this, NotificationListenerService.class));
    }

    public void onNotificationPosted(StatusBarNotification statusBarNotification, RankingMap rankingMap) {
        super.onNotificationPosted(statusBarNotification, rankingMap);
        this.handler.postDelayed(new NotificationServiceExternalSynthetic(this, statusBarNotification), 100);
    }


    public void cancelNotifications() {
        try {
            cancelAllNotifications();
        } catch (SecurityException e) {
            e.printStackTrace();
        }
    }

    public void cancelNotificationById(String str) {
        try {
            cancelNotification(str);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void onNotificationRemoved(StatusBarNotification statusBarNotification) {
        this.handler.postDelayed(new NotificationServiceExternalSynthetic(this, statusBarNotification, false), 100);
    }


    public void postNotification(StatusBarNotification statusBarNotification, boolean isAdded) {
        Log.d("post_notification", "postNotification: " + isAdded);
        sendNotification(statusBarNotification, isAdded);
    }
    private Set<String> loggedMessages = new HashSet<>();

    private void sendNotification(StatusBarNotification statusBarNotification, boolean z) {
        Log.d("chin_chinh", "sendNotification: " + statusBarNotification.toString());
        String str;
        String str2;
        int i;
        int i2;
        boolean z2;
        boolean z3;
        boolean z4;
        String str3;
        String str4;
        String str5;
        String str6;
        String str7;
        String str8;
        String str9;
        String str10;
        String str11;
        String str12;
        CharSequence charSequence;
        Bitmap bmIcon = null;
        Intent intent;
        Bitmap bitmap2;
        Bitmap bitmap3 = null;
        String str13;
        CharSequence charSequence2;
        CharSequence charSequence3;
        String str14;
        String str15;
        String str16;
        String str17;
        String str18;
        try {
            str = (String) getPackageManager().getApplicationLabel(getPackageManager().getApplicationInfo(statusBarNotification.getPackageName(), PackageManager.GET_META_DATA));
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            str = "";
        }
        if (statusBarNotification.getNotification() != null) {
            String packageName = statusBarNotification.getPackageName();
            if (statusBarNotification.getNotification().category != null) {
                str2 = statusBarNotification.getNotification().category;
            } else {
                str2 = "";
            }
            String obj = statusBarNotification.getNotification().tickerText != null ? statusBarNotification.getNotification().tickerText.toString() : null;
            Bitmap drawableToBmp = (Build.VERSION.SDK_INT < 23 || statusBarNotification.getNotification().getLargeIcon() == null) ? null : drawableToBmp(this.context, statusBarNotification.getNotification().getLargeIcon().loadDrawable(this.context), 50);
            if (statusBarNotification.getNotification().extras != null) {
                Bundle bundle = statusBarNotification.getNotification().extras;
                if (!bundle.containsKey(NotificationCompat.EXTRA_TEMPLATE) || bundle.get(NotificationCompat.EXTRA_TEMPLATE) == null) {
                    str8 = "";
                } else {
                    str8 = statusBarNotification.getNotification().extras.get(NotificationCompat.EXTRA_TEMPLATE).toString();
                    if (!TextUtils.isEmpty(str8)) {
                        str8 = str8.substring(str8.indexOf("$") + 1);
                    }
                }
                if (!bundle.containsKey(NotificationCompat.EXTRA_INFO_TEXT) || bundle.getString(NotificationCompat.EXTRA_INFO_TEXT) == null) {
                    str13 = "";
                } else {
                    str13 = bundle.getString(NotificationCompat.EXTRA_INFO_TEXT, "");
                }
                if (!bundle.containsKey(NotificationCompat.EXTRA_TITLE) || bundle.getCharSequence(NotificationCompat.EXTRA_TITLE) == null) {
                    charSequence2 = "";
                } else {
                    charSequence2 = bundle.getCharSequence(NotificationCompat.EXTRA_TITLE, "");
                }
                int i3 = bundle.containsKey(NotificationCompat.EXTRA_PROGRESS_MAX) ? bundle.getInt(NotificationCompat.EXTRA_PROGRESS_MAX, 0) : 0;
                int i4 = bundle.containsKey(NotificationCompat.EXTRA_PROGRESS) ? bundle.getInt(NotificationCompat.EXTRA_PROGRESS, 0) : 0;
                boolean z5 = bundle.containsKey(NotificationCompat.EXTRA_PROGRESS_INDETERMINATE) ? bundle.getBoolean(NotificationCompat.EXTRA_PROGRESS_INDETERMINATE, false) : false;
                boolean z6 = bundle.containsKey(NotificationCompat.EXTRA_SHOW_CHRONOMETER) ? bundle.getBoolean(NotificationCompat.EXTRA_SHOW_CHRONOMETER, false) : false;
                if (!bundle.containsKey(NotificationCompat.EXTRA_SUMMARY_TEXT) || bundle.getCharSequence(NotificationCompat.EXTRA_SUMMARY_TEXT) == null) {
                    charSequence3 = "";
                } else {
                    charSequence3 = bundle.getCharSequence(NotificationCompat.EXTRA_SUMMARY_TEXT, "");
                }
                boolean z7 = z5;
                if (!bundle.containsKey(NotificationCompat.EXTRA_TITLE_BIG) || bundle.getCharSequence(NotificationCompat.EXTRA_TITLE_BIG) == null) {
                    str14 = "";
                } else {
                    str14 = String.valueOf(bundle.getCharSequence(NotificationCompat.EXTRA_TITLE_BIG, ""));
                }
                if (!bundle.containsKey("android.substName") || bundle.getString("android.substName") == null) {
                    str15 = "";
                } else {
                    str15 = bundle.getString("android.substName", "");
                }
                if (!bundle.containsKey(NotificationCompat.EXTRA_SUB_TEXT) || bundle.getCharSequence(NotificationCompat.EXTRA_SUB_TEXT) == null) {
                    str16 = "";
                } else {
                    str16 = String.valueOf(bundle.getCharSequence(NotificationCompat.EXTRA_SUB_TEXT, ""));
                }
                if (!bundle.containsKey(NotificationCompat.EXTRA_TEXT) || bundle.getCharSequence(NotificationCompat.EXTRA_TEXT) == null) {
                    str17 = "";
                } else {
                    str17 = String.valueOf(bundle.getCharSequence(NotificationCompat.EXTRA_TEXT, ""));
                }
                if (!bundle.containsKey(NotificationCompat.EXTRA_BIG_TEXT) || bundle.getCharSequence(NotificationCompat.EXTRA_BIG_TEXT) == null) {
                    str18 = "";
                } else {
                    str18 = String.valueOf(bundle.getCharSequence(NotificationCompat.EXTRA_BIG_TEXT, ""));
                }
                boolean z8 = z6;
                if (Build.VERSION.SDK_INT >= 24 && bundle.containsKey(NotificationCompat.EXTRA_CONVERSATION_TITLE)) {
                    str = str + " . " + bundle.getCharSequence(NotificationCompat.EXTRA_CONVERSATION_TITLE, "");
                }
                if (Build.VERSION.SDK_INT < 28 || !bundle.containsKey(NotificationCompat.EXTRA_IS_GROUP_CONVERSATION)) {
                    str12 = str;
                    z4 = false;
                    i2 = i3;
                    i = i4;
                    z3 = z7;
                    str10 = str15;
                    str3 = str17;
                    str11 = str18;
                    z2 = z8;
                } else {
                    z4 = bundle.getBoolean(NotificationCompat.EXTRA_IS_GROUP_CONVERSATION, false);
                    i2 = i3;
                    i = i4;
                    z3 = z7;
                    str10 = str15;
                    str3 = str17;
                    str11 = str18;
                    z2 = z8;
                    str12 = str;
                }
                str5 = str13;
                str4 = String.valueOf(charSequence2);
                str7 = str14;
                str6 = String.valueOf(charSequence3);
                str9 = str16;
            } else {
                str11 = "";
                str10 = str11;
                str9 = str10;
                str8 = str9;
                str7 = str8;
                str6 = str7;
                str5 = str6;
                str4 = str5;
                str3 = str4;
                z4 = false;
                z3 = false;
                z2 = false;
                i2 = 0;
                i = 0;
                str12 = str;
            }
            String valueOf = String.valueOf(statusBarNotification.getId());
            String str19 = str5;
            try {
                Drawable drawableIcon = ContextCompat.getDrawable(createPackageContext(packageName, 0), statusBarNotification.getNotification().icon);

                if (drawableIcon != null) {
                    charSequence = str6;
                    try {
                        bmIcon = drawableToBmp((Context) this, drawableIcon, 20);
                    } catch (Exception | OutOfMemoryError e2) {
                    }
                } else {
                    bmIcon = BitmapFactory.decodeResource(context.getResources(), R.drawable.ic_launcher_foreground);
                }
                charSequence = str6;
                intent = new Intent(Utils.FROM_NOTIFICATION_SERVICE + this.context.getPackageName());
                intent.putExtra("isGroupConversation", z4);
                intent.putExtra("icon", getByteArrayFromBitmap(bmIcon));
                intent.putExtra("isOngoing", statusBarNotification.isOngoing());
                intent.putExtra("tag", statusBarNotification.getTag());
                intent.putExtra("category", str2);
                intent.putExtra("template", str8);
                intent.putExtra("group_key", statusBarNotification.getGroupKey());
                intent.putExtra("key", statusBarNotification.getKey());
                intent.putExtra(InAppPurchaseProvider.ID, valueOf);
                intent.putExtra("package", packageName);
                intent.putExtra("ticker", obj);
                intent.putExtra("appName", str12);
                intent.putExtra("title", str4);
                intent.putExtra("isAdded", z);
                intent.putExtra("postTime", statusBarNotification.getNotification().when);
                intent.putExtra("text", str3);
                intent.putExtra("bigText", str11);
                intent.putExtra("isClearable", statusBarNotification.isClearable());
                intent.putExtra("color", statusBarNotification.getNotification().color);
                intent.putExtra("largeIcon", getByteArrayFromBitmap(drawableToBmp));
                intent.putExtra("substName", str10);
                intent.putExtra("subText", str9);
                intent.putExtra("titleBig", str7);
                intent.putExtra("summaryText", charSequence);
                intent.putExtra("info_text", str19);
                intent.putExtra("progressMax", i2);
                intent.putExtra("progress", i);
                intent.putExtra("progressIndeterminate", z3);
                intent.putExtra("showChronometer", z2);
                intent.putExtra("picture", getByteArrayFromBitmap2(bitmap3));
                intent.putExtra(BaseGmsClient.KEY_PENDING_INTENT, statusBarNotification.getNotification().contentIntent);
                intent.putExtra("actions", getParsableActions(statusBarNotification.getNotification().actions));

                if (!TextUtils.isEmpty(str3) && !loggedMessages.contains(str3)) {
                    loggedMessages.add(str3); // Đánh dấu đã log

                    StringBuilder fullMsgBuilder = new StringBuilder();
                    fullMsgBuilder.append("Package: ").append(packageName);

                    if (!TextUtils.isEmpty(str12)) {
                        fullMsgBuilder.append(" | App Name: ").append(str12);
                    }
                    if (!TextUtils.isEmpty(str4)) {
                        fullMsgBuilder.append(" | From: ").append(str4);
                    }

                    fullMsgBuilder.append(" | Text: ").append(str3); // Bắt buộc có

                    if (!TextUtils.isEmpty(str11)) {
                        fullMsgBuilder.append(" | BigText: ").append(str11);
                    }
                    if (!TextUtils.isEmpty(str9)) {
                        fullMsgBuilder.append(" | SubText: ").append(str9);
                    }
                    if (!TextUtils.isEmpty(String.valueOf(charSequence))) {
                        fullMsgBuilder.append(" | Summary: ").append(charSequence);
                    }
                    if (!TextUtils.isEmpty(str19)) {
                        fullMsgBuilder.append(" | Info: ").append(str19);
                    }

                    Log.d("Notify_logger", "sendNotification:\n" + fullMsgBuilder.toString());
                }


                if (prefManager.getListDisable().contains(packageName)) {
                    return;
                }

                Log.d("chinh_dep_trai", "packageName: " + packageName);

                if (statusBarNotification.getPackageName().contains("com.google.android.apps.maps")) {
                    LocalBroadcastManager.getInstance(this.context).sendBroadcast(intent);
                    return;
                }

                if (Objects.equals(str8, "MediaStyle")) {
                    LocalBroadcastManager.getInstance(this.context).sendBroadcast(intent);
                } else if (statusBarNotification.getGroupKey().contains("CHARGING")) {
                    Log.e(TAG, "sendNotification: vinhtqkkkk2");
                } else if (statusBarNotification.getPackageName().contains("com.google.android.gms")) {
                    Log.e(TAG, "sendNotification: vinhtqkkkk3");
                } else if (statusBarNotification.getPackageName().contains("com.android.vending")) {
                    Log.e(TAG, "sendNotification: vinhtqkkkk4");
                } else if (statusBarNotification.isOngoing()) {
                    Log.e(TAG, "sendNotification: vinhtqkkkk5");
                } else {
                    LocalBroadcastManager.getInstance(this.context).sendBroadcast(intent);
                }


            } catch (Exception | OutOfMemoryError ignored) {

            }
        }
    }

    private byte[] getByteArrayFromBitmap2(Bitmap bitmap) {
        if (bitmap == null) {
            return null;
        }
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 70, byteArrayOutputStream);
        return byteArrayOutputStream.toByteArray();
    }

    private byte[] getByteArrayFromBitmap(Bitmap bitmap) {
        if (bitmap == null) {
            return null;
        }
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
        return byteArrayOutputStream.toByteArray();
    }

    public static ArrayList<ActionParsable> getParsableActions(Notification.Action[] actionArr) {
        int i;
        Notification.Action[] actionArr2 = actionArr;
        ArrayList<ActionParsable> arrayList = new ArrayList<>(3);
        int length1;
        try {
            length1 = actionArr2.length;
        } catch (NullPointerException ex) {
            length1 = 0;
        }

        int i2 = 0;
        int i3 = 0;
        boolean z = false;
        boolean z2 = false;
        while (i3 < length1) {
            Notification.Action action = actionArr2[i3];
            int i4 = Build.VERSION.SDK_INT;
            if (i4 >= 24) {
                z = action.getAllowGeneratedReplies();
            } else {
                try {
                    z = action.getExtras().getBoolean("android.support.allowGeneratedReplies");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (i4 >= 28) {
                i = action.getSemanticAction();
            } else {
                i = action.getExtras().getInt("android.support.action.semanticAction", i2);
            }
            int i5 = i;
            int i6 = action.icon;
            CharSequence charSequence = action.title;
            PendingIntent pendingIntent = action.actionIntent;
            Bundle extras = action.getExtras();
            RemoteInput[] remoteInputs = action.getRemoteInputs();
            if (Build.VERSION.SDK_INT >= 29) {
                z2 = action.isContextual();
                arrayList.add(new ActionParsable(charSequence, pendingIntent, extras, remoteInputs, z, i5, z2, i6));
                i3++;
            } else {
                ActionParsable actionParsable = new ActionParsable(charSequence, pendingIntent, extras, remoteInputs, z, i5, z2, i6);
                arrayList.add(actionParsable);
                i3++;
                i2 = 0;
            }
        }
        return arrayList;
    }

    private Bitmap scaleDownImage(Bitmap bitmap, int i, int i2) {
        try {
            float width = (float) bitmap.getWidth();
            float height = (float) bitmap.getHeight();
            float f = (float) i;
            if (width <= f) {
                return bitmap;
            }
            float f2 = width / height;
            float f3 = (float) i2;
            float f4 = f / f3;
            if (height <= width) {
                if (f4 < 1.0f) {
                    f3 = (float) ((int) (f2 * f));
                } else {
                    f = (float) ((int) (f3 / f2));
                }
                float f5 = f3;
                f3 = f;
                f = f5;
            } else if (f4 > 1.0f) {
                f = (float) ((int) (f2 * f3));
            } else {
                f3 = (float) ((int) (f / f2));
            }
            return Bitmap.createScaledBitmap(bitmap, (int) f, (int) f3, true);
        } catch (OutOfMemoryError e) {
            e.printStackTrace();
            return null;
        }
    }

    private Bitmap getCroppedBitmap(Drawable drawable) {
        try {
            Display defaultDisplay = ((WindowManager) getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
            DisplayMetrics displayMetrics = new DisplayMetrics();
            defaultDisplay.getRealMetrics(displayMetrics);
            int i = displayMetrics.widthPixels;
            int i2 = displayMetrics.heightPixels;
            Bitmap createBitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_4444);
            Canvas canvas = new Canvas(createBitmap);
            drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
            drawable.draw(canvas);
            if (createBitmap.getWidth() <= i || createBitmap.getHeight() <= i2) {
                return scaleUpImage(createBitmap, i, i2);
            }
            return scaleDownImage(createBitmap, i, i2);
        } catch (Exception | OutOfMemoryError e) {
            e.printStackTrace();
            return null;
        }
    }

    private Bitmap scaleUpImage(Bitmap bitmap, int i, int i2) {
        Bitmap bitmap2 = null;
        Bitmap createBitmap;
        try {
            float width = (float) bitmap.getWidth();
            if (bitmap.getWidth() > i) {
                createBitmap = Bitmap.createBitmap(bitmap, (int) ((width / 2.0f) - ((float) (i / 2))), 0, i, bitmap.getHeight());
                bitmap.recycle();
            } else {
                if (bitmap.getHeight() > i2) {
                    createBitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), i2);
                    bitmap.recycle();
                }
                if (bitmap != null) {
                    return null;
                }
                if (bitmap.getWidth() <= i) {
                    bitmap2 = Bitmap.createScaledBitmap(bitmap, i, (int) ((((float) bitmap.getHeight()) / ((float) bitmap.getWidth())) * ((float) i)), true);
                } else {
                    bitmap2 = bitmap.getHeight() < i2 ? Bitmap.createScaledBitmap(bitmap, (int) ((((float) bitmap.getHeight()) / ((float) bitmap.getWidth())) * ((float) i2)), i2, true) : null;
                }
                bitmap.recycle();
                return bitmap2;
            }
            bitmap = createBitmap;
            if (bitmap != null) {
            }
        } catch (OutOfMemoryError e) {
            e.printStackTrace();
            return null;
        }
        return bitmap2;
    }

    public Bitmap drawableToBmp(Context context2, Drawable drawable, int i) {
        Bitmap bitmap;
        if (drawable == null) {
            return null;
        }
        if (drawable.getIntrinsicWidth() <= 0 || drawable.getIntrinsicHeight() <= 0) {
            bitmap = Bitmap.createBitmap(1, 1, Bitmap.Config.ARGB_4444);
        } else if (i <= 0) {
            return getCroppedBitmap(drawable);
        } else {
            int convertDpToPixel = (int) Constants.convertDpToPixel((float) i, context2);
            bitmap = Bitmap.createBitmap(convertDpToPixel, convertDpToPixel, Bitmap.Config.ARGB_4444);
        }
        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        drawable.draw(canvas);
        return bitmap;
    }

    private class NotificationServiceExternalSynthetic implements Runnable {
        StatusBarNotification statusBarNotification11;
        boolean isAdd = true;

        public NotificationServiceExternalSynthetic(NotificationService notificationService, StatusBarNotification statusBarNotification) {
            Log.d("chinh_chinh", "notificationService" + statusBarNotification.toString());
            statusBarNotification11 = statusBarNotification;
            isAdd = true;
        }

        public NotificationServiceExternalSynthetic(NotificationService notificationService, StatusBarNotification statusBarNotification, Boolean isAdd) {
            Log.d("chinh_chinh", "notificationService" + statusBarNotification.toString());
            statusBarNotification11 = statusBarNotification;
            this.isAdd = isAdd;
        }

        @Override
        public void run() {
            postNotification(statusBarNotification11, isAdd);
        }
    }

}
