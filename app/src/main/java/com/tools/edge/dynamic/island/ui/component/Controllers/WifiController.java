package com.tools.edge.dynamic.island.ui.component.Controllers;

import android.content.Context;
import android.content.Intent;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;
import com.tools.edge.dynamic.island.R;
import com.tools.edge.dynamic.island.ui.component.entity.ButtonState;
import com.tools.edge.dynamic.island.ui.component.entity.ButtonState;

public class WifiController extends ButtonState {
    private final Context context;
    private final Intent intent;
    private final WifiManager wifiManager;

    public String getName() {
        return null;
    }

    public boolean getState() {
        return false;
    }

    public boolean hasSystemFeature() {
        return true;
    }

    public WifiController(Context context2) {
        super(context2);
        this.context = context2;
        if (Build.VERSION.SDK_INT >= 29) {
            this.intent = new Intent("android.settings.panel.action.INTERNET_CONNECTIVITY");
        } else {
            this.intent = new Intent("android.net.wifi.PICK_WIFI_NETWORK");
        }
        this.wifiManager = (WifiManager) context2.getApplicationContext().getSystemService(Context.WIFI_SERVICE);
    }

    public void setState(boolean z, LottieAnimationView lottieAnimationView, TextView textView, TextView textView2) {
        if (z) {
            lottieAnimationView.setImageResource(R.drawable.on);
            textView2.setText(this.context.getString(R.string.text_on));
        } else {
            lottieAnimationView.setImageResource(R.drawable.off);
            textView2.setText(this.context.getString(R.string.text_off));
        }
        textView.setText(this.context.getString(R.string.quick_settings_wifi_label));
    }

    public Intent getIntent() {
        return this.intent;
    }
}
