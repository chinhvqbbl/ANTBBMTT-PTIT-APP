package com.tools.edge.dynamic.island.ui.component.Controllers;


import android.content.Context;
import android.content.Intent;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;
import com.tools.edge.dynamic.island.R;
import com.tools.edge.dynamic.island.ui.component.entity.ButtonState;
import com.tools.edge.dynamic.island.ui.component.entity.ButtonState;

public class AlarmsController extends ButtonState {
    private final Context context;
    private final Intent intent = new Intent("android.intent.action.SHOW_ALARMS");
    private String name;

    public boolean getState() {
        return false;
    }

    public boolean hasSystemFeature() {
        return true;
    }

    public void setState(boolean z, LottieAnimationView lottieAnimationView, TextView textView, TextView textView2) {
    }

    public AlarmsController(Context context2) {
        super(context2);
        this.context = context2;
    }

    public Intent getIntent() {
        return this.intent;
    }

    public String getName() {
        return this.context.getResources().getString(R.string.clock);
    }
}
