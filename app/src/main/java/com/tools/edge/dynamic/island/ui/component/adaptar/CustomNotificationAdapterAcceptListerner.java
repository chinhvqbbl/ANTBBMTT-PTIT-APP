package com.tools.edge.dynamic.island.ui.component.adaptar;

import android.view.View;

import com.tools.edge.dynamic.island.ui.component.entity.Notification;

public final  class CustomNotificationAdapterAcceptListerner implements View.OnClickListener {
    public final  CustomNotificationAdapter f$0;
    public final Notification f$1;

    public CustomNotificationAdapterAcceptListerner(CustomNotificationAdapter customNotificationAdapter, Notification notification) {
        this.f$0 = customNotificationAdapter;
        this.f$1 = notification;
    }

    public final void onClick(View view) {
        this.f$0.m90xcd5755ad(this.f$1, view);
    }
}
