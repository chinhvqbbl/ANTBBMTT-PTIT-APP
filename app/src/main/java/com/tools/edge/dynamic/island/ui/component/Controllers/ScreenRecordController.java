package com.tools.edge.dynamic.island.ui.component.Controllers;

import android.content.Context;
import android.content.Intent;
import android.widget.TextView;
import com.airbnb.lottie.LottieAnimationView;
import com.tools.edge.dynamic.island.R;
import com.tools.edge.dynamic.island.ui.component.entity.ButtonState;
import com.tools.edge.dynamic.island.ui.component.entity.ButtonState;

public class ScreenRecordController extends ButtonState {
    private Context context;

    public Intent getIntent() {
        return null;
    }

    public boolean getState() {
        return false;
    }

    public boolean hasSystemFeature() {
        return true;
    }

    public ScreenRecordController(Context context2) {
        super(context2);
        this.context = context2;
    }

    public void setState(boolean z, LottieAnimationView lottieAnimationView, TextView textView, TextView textView2) {
        if (z) {
            lottieAnimationView.setImageResource(R.drawable.on);
        } else {
            lottieAnimationView.setImageResource(R.drawable.off);
        }
    }

    public String getName() {
        return this.context.getResources().getString(R.string.screen_record_name);
    }
}
