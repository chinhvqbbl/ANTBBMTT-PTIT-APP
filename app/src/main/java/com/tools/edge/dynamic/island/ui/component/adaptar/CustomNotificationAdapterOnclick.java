package com.tools.edge.dynamic.island.ui.component.adaptar;

import android.view.View;
import android.widget.LinearLayout;

import com.tools.edge.dynamic.island.ui.component.entity.Notification;


public final  class CustomNotificationAdapterOnclick implements View.OnClickListener {
    public final Notification f$0;
    public final  String f$1;
    public final  LinearLayout f$2;
    public final  View f$3;

    public CustomNotificationAdapterOnclick(Notification notification, String str, LinearLayout linearLayout, View view) {
        this.f$0 = notification;
        this.f$1 = str;
        this.f$2 = linearLayout;
        this.f$3 = view;
    }

    public final void onClick(View view) {
        CustomNotificationAdapter.lambda$addSubItemsToGroupContainer$0(this.f$0, this.f$1, this.f$2, this.f$3, view);
    }
}
