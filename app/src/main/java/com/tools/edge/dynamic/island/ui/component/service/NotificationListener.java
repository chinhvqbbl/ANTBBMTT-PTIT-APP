package com.tools.edge.dynamic.island.ui.component.service;


import com.tools.edge.dynamic.island.ui.component.entity.Notification;
import com.tools.edge.dynamic.island.ui.component.entity.Notification;

public interface NotificationListener {
    void onItemClicked(Notification notification);

    void onItemClicked(Notification notification, int i,boolean longClick);

    void onLongItemClicked(Notification notification, int i);

    void onDeleteItem(Notification notification, int i);
    void onCloseFullNotification(Notification notification);
}
