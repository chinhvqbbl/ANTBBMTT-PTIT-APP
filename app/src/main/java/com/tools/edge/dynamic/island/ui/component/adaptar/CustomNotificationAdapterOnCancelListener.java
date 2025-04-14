package com.tools.edge.dynamic.island.ui.component.adaptar;

import android.view.View;

import com.tools.edge.dynamic.island.ui.component.entity.Notification;


public final  class CustomNotificationAdapterOnCancelListener implements View.OnClickListener {
    public final  CustomNotificationAdapter customNotificationAdapter;
    public final Notification notification;

    public CustomNotificationAdapterOnCancelListener(CustomNotificationAdapter customNotificationAdapter, Notification notification) {
        this.customNotificationAdapter = customNotificationAdapter;
        this.notification = notification;
    }

    public final void onClick(View view) {
        this.customNotificationAdapter.onCancelNotification(this.notification, view);
    }
}
