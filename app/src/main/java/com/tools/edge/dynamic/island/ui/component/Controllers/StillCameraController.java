package com.tools.edge.dynamic.island.ui.component.Controllers;

import android.content.Context;
import android.content.Intent;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;
import com.tools.edge.dynamic.island.R;
import com.tools.edge.dynamic.island.ui.component.entity.ButtonState;
import com.tools.edge.dynamic.island.ui.component.entity.ButtonState;

public class StillCameraController extends ButtonState {
    private final Context context;

    public boolean getState() {
        return false;
    }

    public boolean hasSystemFeature() {
        return true;
    }

    public void setState(boolean z, LottieAnimationView lottieAnimationView, TextView textView, TextView textView2) {
    }

    public StillCameraController(Context context2) {
        super(context2);
        this.context = context2;
    }

    public Intent getIntent() {
        return new Intent("android.media.action.STILL_IMAGE_CAMERA").addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
    }

    public String getName() {
        return this.context.getResources().getString(R.string.camera);
    }
}
