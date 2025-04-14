package com.tools.edge.dynamic.island.ui.component.adaptar;

import android.widget.LinearLayout;

import com.tools.edge.dynamic.island.ui.component.entity.Notification;


public final  class CustomNotificationAdapterRunable implements Runnable {
    public final  CustomNotificationAdapter customNotificationAdapter;
    public final Notification notification;
    public final  int position;
    public final  LinearLayout linearLayout;

    public CustomNotificationAdapterRunable(CustomNotificationAdapter customNotificationAdapter, Notification notification, int i, LinearLayout linearLayout) {
        this.customNotificationAdapter = customNotificationAdapter;
        this.notification = notification;
        this.position = i;
        this.linearLayout = linearLayout;
    }

    public final void run() {
        this.customNotificationAdapter.sendPendingAction(this.notification, this.position, this.linearLayout);
    }
}
