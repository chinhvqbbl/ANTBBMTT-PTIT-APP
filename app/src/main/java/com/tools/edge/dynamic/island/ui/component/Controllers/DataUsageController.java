package com.tools.edge.dynamic.island.ui.component.Controllers;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Build;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;
import com.tools.edge.dynamic.island.R;
import com.tools.edge.dynamic.island.ui.component.entity.ButtonState;
import com.tools.edge.dynamic.island.ui.component.entity.ButtonState;

public class DataUsageController extends ButtonState {
    ConnectivityManager connectivity;
    private Context context;

    public boolean hasSystemFeature() {
        return true;
    }

    public void setState(boolean z, LottieAnimationView lottieAnimationView, TextView textView, TextView textView2) {
    }

    public DataUsageController(Context context2) {
        super(context2);
        this.context = context2;
        this.connectivity = (ConnectivityManager) context2.getSystemService(Context.CONNECTIVITY_SERVICE);
    }

    public boolean getState() {
        return Build.VERSION.SDK_INT >= 24 && this.connectivity.getRestrictBackgroundStatus() == ConnectivityManager.RESTRICT_BACKGROUND_STATUS_ENABLED;
    }

    public Intent getIntent() {
        return new Intent("android.settings.DATA_USAGE_SETTINGS");
    }

    public String getName() {
        return this.context.getString(R.string.data_saver);
    }
}
