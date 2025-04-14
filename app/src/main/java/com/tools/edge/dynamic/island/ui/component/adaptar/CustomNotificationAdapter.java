package com.tools.edge.dynamic.island.ui.component.adaptar;

import android.annotation.SuppressLint;
import android.app.PendingIntent;
import android.app.RemoteInput;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.ColorFilter;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Chronometer;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.core.app.NotificationCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.res.ResourcesCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.airbnb.lottie.LottieAnimationView;
import com.google.android.material.imageview.ShapeableImageView;
import com.tools.edge.dynamic.island.R;
import com.tools.edge.dynamic.island.ui.component.entity.Notification;
import com.tools.edge.dynamic.island.ui.component.service.ActionParsable;
import com.tools.edge.dynamic.island.ui.component.service.ITGAccessibilityService;
import com.tools.edge.dynamic.island.ui.component.service.NotificationListener;
import com.tools.edge.dynamic.island.ui.component.service.NotificationService;
import com.tools.edge.dynamic.island.ui.component.utils.Constants;
import com.tools.edge.dynamic.island.ui.component.utils.Utils;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Objects;

import de.hdodenhof.circleimageview.CircleImageView;

public class CustomNotificationAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final int DAY_MILLIS = 86400000;
    private static final int HOUR_MILLIS = 3600000;
    private static final int MINUTE_MILLIS = 60000;
    private static final int SECOND_MILLIS = 1000;
    private static final int VIEW_TYPE_CALL = 2;
    private static final int VIEW_TYPE_NORMAL = 1;
    private static final DecimalFormat df = new DecimalFormat("00");
    int acceptIndex = 1;
    int declineIndex = 0;

    public final Context mContext;
    ViewHolder mediaViewHolder;

    public NotificationListener notificationListener;
    public ArrayList<Notification> notifications;
    private Utils utils;
    ViewGroup viewGroup;

    public CustomNotificationAdapter(Context context, ArrayList<Notification> arrayList, NotificationListener notificationListener2) {
        this.mContext = context;
        this.notifications = arrayList;
        this.notificationListener = notificationListener2;
        this.utils = ((ITGAccessibilityService) context).utils;
    }

    public void submitData(ArrayList<Notification> notifications) {
        this.notifications.clear();
        this.notifications.addAll(notifications);
        notifyDataSetChanged();
    }

    public int getItemCount() {
        ArrayList<Notification> arrayList = this.notifications;
        if (arrayList != null) {
            return arrayList.size();
        }
        return 0;
    }

    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup2, int i) {
        LayoutInflater from = LayoutInflater.from(this.mContext);
        this.viewGroup = viewGroup2;
        if (i == 2) {
            return new ViewHolderCall(from.inflate(R.layout.notification_call_items, viewGroup2, false));
        }
        return new ViewHolder(from.inflate(R.layout.notification_list_items, viewGroup2, false));
    }

    public int getItemViewType(int i) {
        if (!this.notifications.get(i).category.equalsIgnoreCase(NotificationCompat.CATEGORY_CALL) || !this.notifications.get(i).isOngoing) {
            return super.getItemViewType(i);
        }
        return 2;
    }

    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
        if (!this.notifications.get(i).category.equalsIgnoreCase(NotificationCompat.CATEGORY_CALL) || !this.notifications.get(i).isOngoing) {
            ((ViewHolder) viewHolder).bind(this.notifications);
        } else {
            ((ViewHolderCall) viewHolder).bind(this.notifications);
        }
    }


    public String getFormattedTime(long j) {
        long j2 = j / 1000;
        DecimalFormat decimalFormat = df;
        String format = decimalFormat.format(j2 / 60);
        return format + ":" + decimalFormat.format(j2 % 60);
    }


    public void addTitleAndTextSubItems(Notification notification, LinearLayout linearLayout) {
        for (String str : notification.keyMap.keySet()) {
            Notification notification2 = notification.keyMap.get(str);
            if (notification.keyMap.size() != 1) {
                linearLayout.addView(getTitleAndTextViewForSubItems(notification2.tv_title, notification2.tv_text));
            } else if (!notification.tv_text.equals(notification2.tv_text)) {
                linearLayout.addView(getTitleAndTextViewForSubItems(notification2.tv_title, notification2.tv_text));
            }
        }
    }


    public void addSubItemsToGroupContainer(Notification notification, LinearLayout linearLayout) {
        CustomNotificationAdapter customNotificationAdapter = this;
        Notification notification2 = notification;
        LinearLayout linearLayout2 = linearLayout;
        for (String next : notification2.keyMap.keySet()) {
            if (notification2.keyMap.get(next) != null) {
                View inflate = LayoutInflater.from(customNotificationAdapter.mContext).inflate(R.layout.notification_list_sub_item, (ViewGroup) null);
                TextView textView = (TextView) inflate.findViewById(R.id.tv_app_title);
                TextView textView2 = (TextView) inflate.findViewById(R.id.tv_text);
                final TextView textView3 = (TextView) inflate.findViewById(R.id.sub_tv_text);
                CircleImageView circleImageView = (CircleImageView) inflate.findViewById(R.id.civ_senderIcon);
                LinearLayout linearLayout3 = (LinearLayout) inflate.findViewById(R.id.notification_action_container);
                RelativeLayout relativeLayout = (RelativeLayout) inflate.findViewById(R.id.notification_material_reply_container);
                ImageView imageView = (ImageView) inflate.findViewById(R.id.arrow_iv);
                ImageView imageView2 = (ImageView) inflate.findViewById(R.id.notification_picture);
                if (((Notification) Objects.requireNonNull(notification2.keyMap.get(next))).senderIcon != null) {
                    circleImageView.setVisibility(View.VISIBLE);
                    circleImageView.setImageBitmap(((Notification) Objects.requireNonNull(notification2.keyMap.get(next))).senderIcon);
                } else {
                    circleImageView.setImageResource(0);
                    circleImageView.setVisibility(View.GONE);
                }
                if (((Notification) Objects.requireNonNull(notification2.keyMap.get(next))).picture != null) {
                    imageView2.setImageBitmap(((Notification) Objects.requireNonNull(notification2.keyMap.get(next))).picture);
                } else {
                    imageView2.setImageBitmap((Bitmap) null);
                }
                if (((Notification) Objects.requireNonNull(notification2.keyMap.get(next))).picture != null) {
                    ((ImageView) inflate.findViewById(R.id.notification_picture)).setImageBitmap(((Notification) Objects.requireNonNull(notification2.keyMap.get(next))).picture);
                } else {
                    ((ImageView) inflate.findViewById(R.id.notification_picture)).setImageBitmap((Bitmap) null);
                }
                textView.setText(((ITGAccessibilityService) customNotificationAdapter.mContext).utils.getFormatedDate(((Notification) Objects.requireNonNull(notification2.keyMap.get(next))).postTime));
                textView2.setText(((Notification) Objects.requireNonNull(notification2.keyMap.get(next))).tv_title);
                textView3.setText(((Notification) Objects.requireNonNull(notification2.keyMap.get(next))).tv_text.toString());
                if (((Notification) Objects.requireNonNull(notification2.keyMap.get(next))).actions != null || !((Notification) Objects.requireNonNull(notification2.keyMap.get(next))).bigText.toString().isEmpty()) {
                    imageView.setVisibility(View.VISIBLE);
                } else {
                    imageView.setVisibility(View.GONE);
                }
                final LinearLayout linearLayout4 = linearLayout3;
                final ImageView imageView3 = imageView;
                final RelativeLayout relativeLayout2 = relativeLayout;
                final Notification notification3 = notification;
                final String str = next;
                imageView.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View view) {
                        if (linearLayout4.getVisibility() == View.VISIBLE) {
                            imageView3.setImageResource(R.drawable.arrow_down);
                            linearLayout4.setVisibility(View.GONE);
                            relativeLayout2.setVisibility(View.GONE);
                            textView3.setText(((Notification) Objects.requireNonNull(notification3.keyMap.get(str))).tv_text.toString());
                            return;
                        }
                        if (!((Notification) Objects.requireNonNull(notification3.keyMap.get(str))).bigText.toString().isEmpty()) {
                            textView3.setText(((Notification) Objects.requireNonNull(notification3.keyMap.get(str))).bigText.toString());
                        }
                        imageView3.setImageResource(R.drawable.arrow_up);
                        linearLayout4.setVisibility(View.VISIBLE);
                        linearLayout4.removeAllViews();
                        if (((Notification) Objects.requireNonNull(notification3.keyMap.get(str))).actions != null && ITGAccessibilityService.getInstance().prefManager.getShowAction()) {
                            new Handler().post(new Runnable() {
                                public void run() {
                                    linearLayout4.setPadding(0, (int) Constants.convertDpToPixel(10.0f, CustomNotificationAdapter.this.mContext), 0, (int) Constants.convertDpToPixel(5.0f, CustomNotificationAdapter.this.mContext));
                                    CustomNotificationAdapter.this.addViewToActionContainer((Notification) Objects.requireNonNull(notification3.keyMap.get(str)), linearLayout4);

                                }
                            });
                        } else {
                            linearLayout4.setPadding(0, 0, 0, 0);
                        }
                    }
                });
                inflate.setOnClickListener(new CustomNotificationAdapterOnclick(notification2, next, linearLayout2, inflate));
                linearLayout2.addView(inflate);
            }
            customNotificationAdapter = this;
        }
    }

    static void lambda$addSubItemsToGroupContainer$0(Notification notification, String str, LinearLayout linearLayout, View view, View view2) {
        try {
            if (((Notification) Objects.requireNonNull(notification.keyMap.get(str))).pendingIntent != null) {
                ((Notification) Objects.requireNonNull(notification.keyMap.get(str))).pendingIntent.send();
                linearLayout.removeView(view);
                notification.keyMap.remove(str);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private View getTitleAndTextViewForSubItems(CharSequence charSequence, CharSequence charSequence2) {
        LinearLayout linearLayout = (LinearLayout) LayoutInflater.from(this.mContext).inflate(R.layout.notification_min_row_item, (ViewGroup) null);
        int convertDpToPixel = (int) Constants.convertDpToPixel(5.0f, this.mContext);
        if (charSequence.toString().isEmpty()) {
            linearLayout.findViewById(R.id.item_text_a).setVisibility(View.GONE);
            linearLayout.findViewById(R.id.item_text_b).setPadding(0, convertDpToPixel, convertDpToPixel, convertDpToPixel);
        } else {
            ((TextView) linearLayout.findViewById(R.id.item_text_a)).setText(charSequence.toString());
        }
        if (charSequence2.toString().isEmpty()) {
            linearLayout.findViewById(R.id.item_text_b).setVisibility(View.GONE);
        } else {
            ((TextView) linearLayout.findViewById(R.id.item_text_b)).setText(charSequence2.toString());
        }
        return linearLayout;
    }


    public void addViewToActionContainer(Notification notification, LinearLayout linearLayout) {
        if (notification.actions != null) {
            for (int i = 0; i < notification.actions.size(); i++) {
                TextView textView = (TextView) LayoutInflater.from(this.mContext).inflate(R.layout.notification_action_item, (ViewGroup) null);
                Drawable drawable = ResourcesCompat.getDrawable(this.mContext.getResources(), R.drawable.ic_bright, (Resources.Theme) null);
                try {
                    drawable = ContextCompat.getDrawable(this.mContext.createPackageContext(notification.pack, 0), notification.actions.get(i).actionIcon);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                textView.setText(notification.actions.get(i).charSequence);
                textView.setTextColor(notification.color);
                if (notification.template.equals("MediaStyle")) {
                    drawable.setTint(notification.color);
                    textView.setCompoundDrawablesWithIntrinsicBounds(drawable, (Drawable) null, (Drawable) null, (Drawable) null);
                    textView.setText("");
                }
                if (i > 0) {
                    textView.setPadding(40, 5, 5, 5);
                }
                linearLayout.addView(textView);

                if (notification.actions.size() > 1 && i == notification.actions.size() - 1) {
                    textView.setOnClickListener(new CustomNotificationAdapterOnClickActionWithAdapterClear(this, notification, i, linearLayout));

                } else {

                    textView.setOnClickListener(new CustomNotificationAdapterOnClickActionWithAdapter(this, notification, i, linearLayout));
                }
            }
            if (notification.isClearable) {
//                ImageView imageView = new ImageView(this.mContext);
//                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-2, -2);
//                layoutParams.setMarginStart((int) this.utils.convertPixelsToDp(70f, this.mContext));
//                imageView.setLayoutParams(layoutParams);
//                imageView.setImageResource(R.drawable.ic_clear);
//                imageView.setColorFilter(notification.color);
//                imageView.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        ITGAccessibilityService.iTGAccessibilityService.closeFullNotificationIsland();
//                        new CustomNotificationAdapterOnCancelListener(CustomNotificationAdapter.this, notification);
//                    }
//                });
//                linearLayout.addView(imageView);
            }
        }
    }


    /* renamed from: lambda$addViewToActionContainer$2$com-lock-adaptar-CustomNotificationAdapter  reason: not valid java name */
    public void click(Notification notification, int i, LinearLayout linearLayout, View view) {
        new Handler().post(new CustomNotificationAdapterRunable(this, notification, i, linearLayout));
    }

    public void clickClear(Notification notification, int i, LinearLayout linearLayout, View view) {
        if (Utils.isServiceRunning(mContext, ITGAccessibilityService.class.getName())) {
            ITGAccessibilityService.getInstance().closeFullNotificationIsland();
            ITGAccessibilityService.getInstance().showSmallIslandNotification();
        }
        new Handler().post(new CustomNotificationAdapterRunable(this, notification, i, linearLayout));


    }


    /* renamed from: lambda$addViewToActionContainer$1$com-lock-adaptar-CustomNotificationAdapter  reason: not valid java name */
    public void sendPendingAction(Notification notification, int i, LinearLayout linearLayout) {
        try {
            if (notification.actions.get(i).remoteInputs != null) {
                showReplyBox(linearLayout, notification.actions, i);
                return;
            }
        } catch (Exception e) {

        }

        try {
            if (notification.actions.get(i).pendingIntent != null) {
                notification.actions.get(i).pendingIntent.send();
                notificationListener.onCloseFullNotification(notification);
            }
            if (!notification.template.equals("MediaStyle")) {
                ((ImageView) ((RelativeLayout) linearLayout.getParent().getParent()).findViewById(R.id.arrow_iv)).setImageResource(R.drawable.arrow_down);
                linearLayout.setVisibility(View.GONE);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        notifyDataSetChanged();
    }

    public void onCancelNotification(Notification notification, View view) {
        ((ITGAccessibilityService) this.mContext).closeFullNotificationIsland();
        NotificationService.getInstance().cancelNotificationById(notification.key);
        notificationListener.onCloseFullNotification(notification);
    }


    public void addViewToCallActionContainer(Notification notification, LinearLayout linearLayout) {
        boolean z;
        boolean z2;
        if (notification.actions != null) {
            boolean z3 = true;
            if (notification.pack.equalsIgnoreCase("com.skype.raider") || ((ITGAccessibilityService) this.mContext).isCallPkgFound(notification.pack)) {
                this.declineIndex = 1;
                this.acceptIndex = 0;
            } else {
                this.declineIndex = 0;
                this.acceptIndex = 1;
            }
            if (notification.actions.size() == 2) {
                z2 = false;
                z = false;
                for (int i = 0; i < notification.actions.size(); i++) {
                    if (i == 0) {
                        linearLayout.findViewById(R.id.action_cancel).setVisibility(View.VISIBLE);
                        linearLayout.findViewById(R.id.action_cancel).setOnClickListener(new CustomNotificationAdapterCancelListener(this, notification));
                        z2 = true;
                    }
                    if (i == 1) {
                        linearLayout.findViewById(R.id.action_accept).setVisibility(View.VISIBLE);
                        linearLayout.findViewById(R.id.action_accept).setOnClickListener(new CustomNotificationAdapterAcceptListerner(this, notification));
                        z = true;
                    }
                }
            } else {
                z2 = false;
                z = false;
            }
            if (notification.actions.size() == 1) {
                linearLayout.findViewById(R.id.action_cancel).setVisibility(View.VISIBLE);
                linearLayout.findViewById(R.id.action_accept).setVisibility(View.VISIBLE);
                linearLayout.findViewById(R.id.action_accept).setOnClickListener(new CustomNotificationAdapterCancelListener(this, notification));
                linearLayout.findViewById(R.id.action_cancel).setOnClickListener(new CustomNotificationAdapterCancelListener(this, notification));
            } else {
                z3 = z2;
            }
            if (!z3) {
                linearLayout.findViewById(R.id.action_cancel).setVisibility(View.GONE);
            }

        }
    }


    /* renamed from: lambda$addViewToCallActionContainer$4$com-lock-adaptar-CustomNotificationAdapter  reason: not valid java name */
    public void m89xa6aec4e(Notification notification, View view) {
        if (notification.actions.get(this.declineIndex).pendingIntent != null) {
            try {
                notification.actions.get(this.declineIndex).pendingIntent.send();
                ((ITGAccessibilityService) this.mContext).closeFullNotificationIsland();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    /* renamed from: lambda$addViewToCallActionContainer$5$com-lock-adaptar-CustomNotificationAdapter  reason: not valid java name */
    public void m90xcd5755ad(Notification notification, View view) {
        if (notification.actions.get(this.acceptIndex).pendingIntent != null) {
            try {
                notification.actions.get(this.acceptIndex).pendingIntent.send();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        ((ITGAccessibilityService) this.mContext).closeFullNotificationIsland();
    }


    /* renamed from: lambda$addViewToCallActionContainer$6$com-lock-adaptar-CustomNotificationAdapter  reason: not valid java name */
    public void m91x9043bf0c(Notification notification, View view) {
        if (notification.actions.get(0).pendingIntent != null) {
            try {
                notification.actions.get(0).pendingIntent.send();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        ((ITGAccessibilityService) this.mContext).closeFullNotificationIsland();
    }

    private void showReplyBox(View view, final ArrayList<ActionParsable> arrayList, final int i) {
        final View childAt = ((LinearLayout) view.getParent()).getChildAt(0);
        if (childAt != null) {
            childAt.setVisibility(View.VISIBLE);
            childAt.findViewById(R.id.iv_send_reply).setVisibility(View.VISIBLE);
            childAt.findViewById(R.id.iv_send_reply).setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    CustomNotificationAdapter.this.sendRemoteInput(((ActionParsable) arrayList.get(i)).pendingIntent, ((ActionParsable) arrayList.get(i)).remoteInputs, ((ActionParsable) arrayList.get(i)).remoteInputs[0], ((EditText) childAt.findViewById(R.id.ed_reply)).getText().toString());
                    ((EditText) childAt.findViewById(R.id.ed_reply)).setText("");
                    childAt.setVisibility(View.GONE);
                    view.setVisibility(View.GONE);
                }
            });
            final ArrayList<ActionParsable> arrayList2 = arrayList;
            final int i2 = i;
            final View view2 = view;
            ((EditText) childAt.findViewById(R.id.ed_reply)).setOnEditorActionListener(new TextView.OnEditorActionListener() {
                public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                    if (i != 4) {
                        return false;
                    }
                    CustomNotificationAdapter.this.sendRemoteInput(((ActionParsable) arrayList2.get(i2)).pendingIntent, ((ActionParsable) arrayList2.get(i2)).remoteInputs, ((ActionParsable) arrayList2.get(i2)).remoteInputs[0], ((EditText) childAt.findViewById(R.id.ed_reply)).getText().toString());
                    ((EditText) childAt.findViewById(R.id.ed_reply)).setText("");
                    childAt.setVisibility(View.GONE);
                    view2.setVisibility(View.GONE);
                    return true;
                }
            });
        }
    }


    public void sendRemoteInput(PendingIntent pendingIntent, RemoteInput[] remoteInputArr, RemoteInput remoteInput, String str) {
        Bundle bundle = new Bundle();
        bundle.putString(remoteInput.getResultKey(), str);
        Intent addFlags = new Intent().addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        RemoteInput.addResultsToIntent(remoteInputArr, addFlags, bundle);
        if (Build.VERSION.SDK_INT >= 28) {
            if (remoteInput.getAllowFreeFormInput()) {
                RemoteInput.setResultsSource(addFlags, RemoteInput.SOURCE_FREE_FORM_INPUT);
            } else {
                RemoteInput.setResultsSource(addFlags, RemoteInput.SOURCE_CHOICE);
            }
        }
        try {
            pendingIntent.send(this.mContext, 0, addFlags);
        } catch (PendingIntent.CanceledException e) {
            e.printStackTrace();
        }
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        public final ImageView arrow_iv;
        private final Chronometer chronometer;

        public final LinearLayout group_message_parent;

        public ViewHolder holder = this;
        private final ImageView iv_app_icon;
        ImageView iv_senderIcon2;
        ShapeableImageView iv_sender_icon;
        LottieAnimationView lottieAnimationView;

        public final LinearLayout notification_action_container;

        public final RelativeLayout notification_material_reply_container;
        private final ImageView picture_iv;

        public final ProgressBar progressBar;

        public final TextView sub_text;

        public final TextView tv_duration;

        public final TextView tv_position;
        private final TextView tv_text;
        public final TextView tv_title;

        public ViewHolder(View view) {
            super(view);
            mediaViewHolder = holder;
            this.tv_position = (TextView) view.findViewById(R.id.mediaPosText);
            this.tv_duration = (TextView) view.findViewById(R.id.mediaDurationText);
            this.progressBar = (ProgressBar) view.findViewById(R.id.sub_progressbar);
            this.chronometer = (Chronometer) view.findViewById(R.id.chronometer);
            this.iv_app_icon = (ImageView) view.findViewById(R.id.iv_app_icon);
            this.lottieAnimationView = (LottieAnimationView) view.findViewById(R.id.lottie_music);
            this.tv_title = (TextView) view.findViewById(R.id.tv_app_title);
            this.tv_text = (TextView) view.findViewById(R.id.tv_text);
            this.iv_sender_icon = (ShapeableImageView) view.findViewById(R.id.civ_senderIcon);
            this.notification_action_container = (LinearLayout) view.findViewById(R.id.notification_action_container);
            this.notification_material_reply_container = (RelativeLayout) view.findViewById(R.id.notification_material_reply_container);
            this.arrow_iv = (ImageView) view.findViewById(R.id.arrow_iv);
            this.sub_text = (TextView) view.findViewById(R.id.sub_tv_text);
            this.picture_iv = (ImageView) view.findViewById(R.id.notification_picture);
            this.group_message_parent = (LinearLayout) view.findViewById(R.id.group_message_parent);
            this.iv_senderIcon2 = (ImageView) view.findViewById(R.id.civ_senderIcon2);
        }

        public void bind(final ArrayList<Notification> arrayList) {
            this.holder.itemView.setOnLongClickListener((View.OnLongClickListener) null);
            this.holder.itemView.setLongClickable(false);
            final Notification notification = arrayList.get(0);
            if (notification.picture == null || notification.keyMap.size() != 0) {
                this.holder.picture_iv.setImageBitmap((Bitmap) null);
            } else {
//                this.holder.picture_iv.setImageBitmap(notification.picture);
            }
            this.holder.tv_title.setText(notification.app_name);
            this.holder.tv_title.setTag(Boolean.valueOf(notification.isClearable));
            this.holder.tv_text.setText(notification.tv_title);
            this.holder.sub_text.setText(notification.tv_text.toString());
            this.holder.group_message_parent.removeAllViews();
            CustomNotificationAdapter.this.addTitleAndTextSubItems(notification, this.holder.group_message_parent);
            this.holder.notification_action_container.setVisibility(View.GONE);
            this.holder.notification_material_reply_container.setVisibility(View.GONE);
            if (notification.icon != null) {
                if (notification.template.equals("MediaStyle")) {
                    this.holder.iv_app_icon.setVisibility(View.GONE);
                    this.holder.lottieAnimationView.setVisibility(View.VISIBLE);
                    this.holder.lottieAnimationView.setAnimation(R.raw.call_anim);
                    this.holder.lottieAnimationView.setVisibility(View.VISIBLE);
                    if (!this.holder.lottieAnimationView.isAnimating()) {
                        this.holder.lottieAnimationView.playAnimation();
                    }
                } else {
                    this.holder.lottieAnimationView.setVisibility(View.GONE);
                    this.holder.iv_app_icon.setVisibility(View.VISIBLE);
                    this.holder.iv_app_icon.setImageBitmap(notification.icon);
                }
            }
            if (notification.senderIcon != null) {
                this.holder.iv_senderIcon2.setVisibility(View.INVISIBLE);
                this.holder.iv_sender_icon.setVisibility(View.VISIBLE);
                this.holder.iv_sender_icon.setImageBitmap(notification.senderIcon);
            } else {
                this.holder.iv_senderIcon2.setImageBitmap(notification.icon);
                this.holder.iv_senderIcon2.setColorFilter(-1);
                this.holder.iv_senderIcon2.setVisibility(View.VISIBLE);
                this.holder.iv_sender_icon.setVisibility(View.INVISIBLE);
                this.holder.iv_app_icon.setVisibility(View.INVISIBLE);
            }
            if (notification.progressMax > 0) {
                if (notification.showChronometer) {
                    this.holder.chronometer.setVisibility(View.VISIBLE);
                    this.holder.chronometer.start();
                } else {
                    this.holder.chronometer.setVisibility(View.GONE);
                    this.holder.chronometer.setBase(SystemClock.elapsedRealtime());
                    this.holder.chronometer.stop();
                }
                this.holder.progressBar.setVisibility(View.VISIBLE);
                this.holder.progressBar.setMax(notification.progressMax);
                this.holder.progressBar.setProgress(notification.progress);
                this.holder.progressBar.setIndeterminate(notification.progressIndeterminate);
            } else {
                this.holder.progressBar.setVisibility(View.GONE);
                this.holder.chronometer.setVisibility(View.GONE);
                this.holder.chronometer.setBase(SystemClock.elapsedRealtime());
                this.holder.chronometer.stop();
            }
            this.holder.itemView.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    Log.e("TAG", "onClick item dynamic: ");
                    CustomNotificationAdapter.this.notificationListener.onItemClicked((Notification) arrayList.get(ViewHolder.this.holder.getAbsoluteAdapterPosition()));
                }
            });
            if (notification.keyMap.size() > 0 || ((!notification.bigText.toString().isEmpty() && !notification.bigText.toString().equals(notification.tv_text.toString())) || notification.actions != null)) {
                this.holder.arrow_iv.setVisibility(View.VISIBLE);
                this.holder.arrow_iv.setImageResource(R.drawable.arrow_down);
            } else {
                this.holder.arrow_iv.setVisibility(View.INVISIBLE);
            }
            if (arrayList.get(this.holder.getAbsoluteAdapterPosition()).actions == null || arrayList.get(this.holder.getAbsoluteAdapterPosition()).actions.size() <= 0) {
                this.holder.tv_duration.setVisibility(View.GONE);
                this.holder.tv_position.setVisibility(View.GONE);
            } else {
                this.holder.arrow_iv.setVisibility(View.INVISIBLE);
                this.holder.notification_action_container.setVisibility(View.VISIBLE);
                this.holder.notification_action_container.removeAllViews();
                if (ITGAccessibilityService.getInstance().prefManager.getShowAction()) {
                    addViewToActionContainer(notification, this.holder.notification_action_container);
                } else {
//                    this.holder.notification_action_container.setVisibility(View.GONE);
                    this.holder.notification_action_container.removeAllViews();
                }
                if (notification.template.equals("MediaStyle")) {
                    this.holder.tv_duration.setVisibility(View.VISIBLE);
                    this.holder.tv_position.setVisibility(View.VISIBLE);
                } else {
                    this.holder.tv_duration.setVisibility(View.GONE);
                    this.holder.tv_position.setVisibility(View.GONE);
                }
            }
            this.holder.arrow_iv.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    int absoluteAdapterPosition = ViewHolder.this.holder.getAbsoluteAdapterPosition();
                    if (absoluteAdapterPosition >= 0 && absoluteAdapterPosition < arrayList.size()) {
                        if (ViewHolder.this.holder.notification_action_container.getVisibility() == View.VISIBLE) {
                            ViewHolder.this.holder.arrow_iv.setImageResource(R.drawable.arrow_down);
                            ViewHolder.this.holder.notification_action_container.setVisibility(View.GONE);
                            ViewHolder.this.holder.notification_material_reply_container.setVisibility(View.GONE);
                            ViewHolder.this.holder.sub_text.setText(((Notification) arrayList.get(ViewHolder.this.holder.getAbsoluteAdapterPosition())).tv_text.toString());
                            ViewHolder.this.holder.group_message_parent.removeAllViews();
                            CustomNotificationAdapter.this.addTitleAndTextSubItems(notification, ViewHolder.this.holder.group_message_parent);
                            return;
                        }
                        if (!notification.bigText.toString().isEmpty()) {
                            ViewHolder.this.holder.sub_text.setText(((Notification) arrayList.get(ViewHolder.this.holder.getAbsoluteAdapterPosition())).bigText.toString());
                        }
                        ViewHolder.this.holder.arrow_iv.setImageResource(R.drawable.arrow_up);
                        ViewHolder.this.holder.notification_action_container.setVisibility(View.VISIBLE);
                        ViewHolder.this.holder.notification_action_container.removeAllViews();
                        ViewHolder.this.holder.group_message_parent.removeAllViews();
                        if (notification.actions != null && ITGAccessibilityService.getInstance().prefManager.getShowAction()) {
                            addViewToActionContainer(notification, ViewHolder.this.holder.notification_action_container);
                            ViewHolder.this.holder.notification_action_container.setPadding(0, (int) Constants.convertDpToPixel(10.0f, CustomNotificationAdapter.this.mContext), 0, (int) Constants.convertDpToPixel(5.0f, CustomNotificationAdapter.this.mContext));
                            return;
                        }
                        CustomNotificationAdapter.this.addSubItemsToGroupContainer(notification, ViewHolder.this.holder.group_message_parent);
                        ViewHolder.this.holder.notification_action_container.setPadding(0, 0, 0, 0);
                    }
                }
            });
            this.holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                public boolean onLongClick(View view) {
                    return false;
                }
            });
        }
    }

    public void updateMediaProgress() {
        try {
            Log.e("map", "updateMediaProgress mediaViewHolder: " + mediaViewHolder);
            if (this.mediaViewHolder != null) {
                Log.e("map", "updateMediaProgress: ");
                Log.e("map", "updateMediaProgress position: " + this.notifications.get(0).position);
                String formattedTime = getFormattedTime(this.notifications.get(0).duration);
                String formattedTime2 = getFormattedTime(this.notifications.get(0).position);
                Log.e("map", "updateMediaProgress formattedTime: " + formattedTime);
                Log.e("map", "updateMediaProgress formattedTime2: " + formattedTime2);
                this.mediaViewHolder.tv_duration.setText(formattedTime);
                this.mediaViewHolder.tv_position.setText(formattedTime2);
                this.mediaViewHolder.progressBar.setProgress(this.notifications.get(0).progress);
                this.mediaViewHolder.progressBar.setVisibility(View.VISIBLE);
                this.mediaViewHolder.progressBar.setMax(this.notifications.get(0).progressMax);
            }
        } catch (Exception e) {
            Log.e("map", "updateMediaProgress: " + e.toString());
        }

    }

    public class ViewHolderCall extends RecyclerView.ViewHolder {
        CircleImageView iv_sender_icon;
        private final LinearLayout notification_action_container;
        private final TextView tv_text;
        public final TextView tv_title;

        public ViewHolderCall viewHolder = this;

        public ViewHolderCall(View view) {
            super(view);
            this.tv_title = (TextView) view.findViewById(R.id.tv_app_title);
            this.tv_text = (TextView) view.findViewById(R.id.tv_text);
            this.iv_sender_icon = (CircleImageView) view.findViewById(R.id.civ_senderIcon);
            this.notification_action_container = (LinearLayout) view.findViewById(R.id.notification_action_container2);
        }

        public void bind(final ArrayList<Notification> arrayList) {
            this.viewHolder.itemView.setOnLongClickListener((View.OnLongClickListener) null);
            this.viewHolder.itemView.setLongClickable(false);
            Notification notification = arrayList.get(this.viewHolder.getAbsoluteAdapterPosition());
            this.viewHolder.tv_title.setText("mobile");
            this.viewHolder.tv_title.setTag(Boolean.valueOf(notification.isClearable));
            this.viewHolder.tv_text.setText(notification.tv_title);
            if (notification.senderIcon != null) {
                this.viewHolder.iv_sender_icon.setVisibility(View.VISIBLE);
                this.viewHolder.iv_sender_icon.setImageBitmap(notification.senderIcon);
                this.viewHolder.iv_sender_icon.setColorFilter((ColorFilter) null);
            } else {
                this.viewHolder.iv_sender_icon.setVisibility(View.VISIBLE);
            }
            this.viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @SuppressLint("NotifyDataSetChanged")
                public void onClick(View view) {
                    try {
                        if (((Notification) arrayList.get(ViewHolderCall.this.viewHolder.getAbsoluteAdapterPosition())).pendingIntent != null) {
                            ((Notification) arrayList.get(ViewHolderCall.this.viewHolder.getAbsoluteAdapterPosition())).pendingIntent.send();
                            CustomNotificationAdapter.this.notificationListener.onItemClicked((Notification) arrayList.get(ViewHolderCall.this.viewHolder.getAbsoluteAdapterPosition()));
                        }
                        if (((Notification) arrayList.get(ViewHolderCall.this.viewHolder.getAbsoluteAdapterPosition())).isClearable) {
                            arrayList.remove(ViewHolderCall.this.viewHolder.getAbsoluteAdapterPosition());
                            CustomNotificationAdapter.this.notifyDataSetChanged();
                        }
                        ((ITGAccessibilityService) CustomNotificationAdapter.this.mContext).closeFullNotificationIsland();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
            if (arrayList.get(this.viewHolder.getAbsoluteAdapterPosition()).actions != null && arrayList.get(this.viewHolder.getAbsoluteAdapterPosition()).actions.size() > 0) {
                CustomNotificationAdapter.this.addViewToCallActionContainer(notification, this.viewHolder.notification_action_container);
            }
        }
    }
}
