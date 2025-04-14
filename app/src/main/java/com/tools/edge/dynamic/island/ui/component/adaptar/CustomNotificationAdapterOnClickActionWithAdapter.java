package com.tools.edge.dynamic.island.ui.component.adaptar;

import android.view.View;
import android.widget.LinearLayout;

import com.tools.edge.dynamic.island.ui.component.entity.Notification;


public final  class CustomNotificationAdapterOnClickActionWithAdapter implements View.OnClickListener {
    public final  CustomNotificationAdapter customNotificationAdapter;
    public final Notification notification;
    public final  int anInt;
    public final  LinearLayout linearLayout;

    public CustomNotificationAdapterOnClickActionWithAdapter(CustomNotificationAdapter customNotificationAdapter, Notification notification, int i, LinearLayout linearLayout) {
        this.customNotificationAdapter = customNotificationAdapter;
        this.notification = notification;
        this.anInt = i;
        this.linearLayout = linearLayout;
    }

    public final void onClick(View view) {
        this.customNotificationAdapter.click(this.notification, this.anInt, this.linearLayout, view);
    }
}
