package com.tools.edge.dynamic.island.ui.component.adaptar;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Handler;
import android.os.SystemClock;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.Chronometer;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.airbnb.lottie.LottieAnimationView;
import com.bumptech.glide.Glide;
import com.tools.edge.dynamic.island.R;
import com.tools.edge.dynamic.island.ui.component.background.AppConst;
import com.tools.edge.dynamic.island.ui.component.background.PrefManager;
import com.tools.edge.dynamic.island.ui.component.entity.Notification;
import com.tools.edge.dynamic.island.ui.component.service.ITGAccessibilityService;
import com.tools.edge.dynamic.island.ui.component.service.NotificationListener;
import com.tools.edge.dynamic.island.ui.component.utils.Constants;
import com.tools.edge.dynamic.island.utils.SharePreferenceUtil;


import java.util.ArrayList;

public class CustomNotificationIconAdapter extends RecyclerView.Adapter<CustomNotificationIconAdapter.ViewHolder> {
    private final Context mContext;

    public final NotificationListener notificationListener;

    public ArrayList<Notification> notifications;
    ViewGroup viewGroup;

    private PrefManager prefManager;

    public CustomNotificationIconAdapter(Context context, ArrayList<Notification> arrayList, NotificationListener notificationListener2, PrefManager prefManager) {
        this.mContext = context;
        this.notifications = arrayList;
        this.notificationListener = notificationListener2;
        this.prefManager = prefManager;
    }

    public int getItemCount() {
        ArrayList<Notification> arrayList = this.notifications;
        if (arrayList != null) {
            return arrayList.size();
        }
        return 0;
    }

    public void submitData(ArrayList<Notification> arrayList) {
        notifications = arrayList;
//        notifications.clear();
//        notifications.addAll(arrayList);
        notifyDataSetChanged();
    }

    public ViewHolder onCreateViewHolder(ViewGroup viewGroup2, int i) {
        LayoutInflater from = LayoutInflater.from(this.mContext);
        this.viewGroup = viewGroup2;
        return new ViewHolder(from.inflate(R.layout.notification_icon_item, viewGroup2, false));
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public void onBindViewHolder(final ViewHolder viewHolder, @SuppressLint("RecyclerView") int i) {
        boolean z;
        Notification notification = this.notifications.get(viewHolder.getAbsoluteAdapterPosition());
        viewHolder.mChronometer.setVisibility(View.GONE);
        try {
            if (!notification.isChronometerRunning) {
                viewHolder.mChronometer.setBase(SystemClock.elapsedRealtime());
                viewHolder.mChronometer.stop();
            }
        } catch (Exception e) {

        }
        viewHolder.itemView.setOnLongClickListener((View.OnLongClickListener) null);
        viewHolder.itemView.setLongClickable(false);
        viewHolder.mLottieAnimationView.setVisibility(View.GONE);
        viewHolder.mLottieAnimationView.pauseAnimation();
        viewHolder.island_small_image_left.clearColorFilter();
        viewHolder.island_small_image_right.clearColorFilter();
        viewHolder.island_small_image_left.setImageResource(0);
        viewHolder.island_small_image_right.setImageResource(0);
        viewHolder.island_small_image_left.setVisibility(View.VISIBLE);
        viewHolder.island_small_image_right.setVisibility(View.VISIBLE);
        viewHolder.island_small_text_left.setVisibility(View.VISIBLE);
        viewHolder.island_small_text_right.setVisibility(View.VISIBLE);
        viewHolder.island_small_text_right.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
        viewHolder.island_small_text_left.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
        viewHolder.island_small_text_left.setTextColor(((ITGAccessibilityService) this.mContext).utils.getTileColor());
        viewHolder.island_small_text_right.setTextColor(((ITGAccessibilityService) this.mContext).utils.getTileColor());
        if (notification.type != null && notification.type.equalsIgnoreCase(Constants.TYPE_AIRBUDS)) {
            viewHolder.island_small_image_left.setImageResource(R.drawable.earbuds);
            viewHolder.island_small_text_right.setTextColor(notification.color);
            int airpodsBattery = ((ITGAccessibilityService) this.mContext).utils.getAirpodsBattery();
            if (airpodsBattery != -1) {
                viewHolder.island_small_text_right.setCompoundDrawablesWithIntrinsicBounds(0, 0, airpodsBattery, 0);
                viewHolder.island_small_text_right.setCompoundDrawablePadding((int) Constants.convertDpToPixel(5.0f, this.mContext));
                viewHolder.island_small_text_right.setText(((ITGAccessibilityService) this.mContext).utils.getAirPodLevel() + this.mContext.getString(R.string.percent));
            } else {
                viewHolder.island_small_text_right.setText(R.string.airpods);
            }
            viewHolder.island_small_text_left.setVisibility(View.GONE);
            viewHolder.island_small_image_right.setVisibility(View.GONE);
            z = false;
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    notificationListener.onDeleteItem(notification, i);
                    notifyDataSetChanged();
                }
            }, 2000);
        } else {
            z = true;
        }

        if (notification.type != null && notification.type.equalsIgnoreCase(Constants.TYPE_CHARGING)) {
            viewHolder.island_small_image_left.setVisibility(View.GONE);
            viewHolder.island_small_text_right.setTextColor(notification.color);
            viewHolder.island_small_text_right.setText(notification.tv_text);
            viewHolder.island_small_text_right.setCompoundDrawablesWithIntrinsicBounds(0, 0, ((ITGAccessibilityService) this.mContext).utils.getBatteryImage(), 0);
            viewHolder.island_small_text_right.setCompoundDrawablePadding((int) Constants.convertDpToPixel(5.0f, this.mContext));
            viewHolder.island_small_text_left.setTextColor(notification.color);
            viewHolder.island_small_text_left.setText(notification.tv_title);
            viewHolder.island_small_image_right.setVisibility(View.GONE);
            z = false;
            Handler handler = new Handler();
            handler.postDelayed(() -> {
                notificationListener.onDeleteItem(notification, i);
                notifyDataSetChanged();
            }, 2000);
        }

        if (notification.type != null && notification.type.equalsIgnoreCase(Constants.TYPE_SILENT)) {
            viewHolder.island_small_image_left.clearColorFilter();
            viewHolder.island_small_image_left.setImageResource(notification.local_left_icon);
            viewHolder.island_small_text_left.setText("");
            viewHolder.island_small_text_right.setText(notification.tv_title);
            viewHolder.island_small_text_right.setTextColor(notification.color);
            viewHolder.island_small_image_right.setImageResource(0);
            viewHolder.island_small_image_right.setVisibility(View.GONE);
            z = false;
            Handler handler = new Handler();
            handler.postDelayed(() -> {
                notifications.remove(notification);
                notifyDataSetChanged();
            }, 1000);
        }

        if (z) {
            stopRotateImageViewInfinity(viewHolder.island_small_image_left);
            if (notification.icon != null) {
                viewHolder.island_small_image_left.setImageBitmap(notification.icon);
                //dong ho dem gio
                if (notification.showChronometer) {
                    viewHolder.mChronometer.setVisibility(View.VISIBLE);
                    viewHolder.mChronometer.start();
                    notification.isChronometerRunning = true;
                    viewHolder.island_small_text_left.setText("");
                    viewHolder.island_small_image_left.setColorFilter(this.mContext.getColor(R.color.green_500));
                } else {
                    viewHolder.island_small_text_left.setVisibility(View.GONE);
                    viewHolder.island_small_text_right.setVisibility(View.GONE);
                }
                Log.e("TAG", "onBindViewHolder: MediaStyle 6");
            } else {
                Log.e("TAG", "onBindViewHolder: MediaStyle 7");
                viewHolder.island_small_image_left.setImageBitmap((Bitmap) null);
            }

            if (notification.senderIcon == null) {
                viewHolder.island_small_image_right.setImageResource(0);
                viewHolder.island_small_image_right.setVisibility(View.GONE);
                if (notification.tv_title == null || notification.tv_title.length() <= 0) {
                    viewHolder.island_small_text_right.setVisibility(View.GONE);
                } else {
                    viewHolder.island_small_text_right.setVisibility(View.GONE);
                    String[] split = notification.tv_title.toString().split(" ");
                    if (split.length > 0) {
                        viewHolder.island_small_text_right.setText(split[0]);
                    } else {
                        viewHolder.island_small_text_right.setText("");
                    }
                }
                Log.e("TAG", "onBindViewHolder: MediaStyle 5");
            } else if (notification.showChronometer && notification.category.equalsIgnoreCase(NotificationCompat.CATEGORY_CALL) && notification.isOngoing) {
                if (prefManager.getShowTimeCall()) {
                    viewHolder.island_small_text_right.setVisibility(View.GONE);
                    viewHolder.island_small_image_right.setVisibility(View.GONE);
                    viewHolder.mChronometer.setVisibility(View.VISIBLE);
                    viewHolder.island_small_text_right.setText("");
                    viewHolder.mLottieAnimationView.setAnimation((int) R.raw.wave_call01);
                    viewHolder.mLottieAnimationView.setVisibility(View.VISIBLE);
                    if (!viewHolder.mLottieAnimationView.isAnimating()) {
                        viewHolder.mLottieAnimationView.playAnimation();
                    }
                } else {
                    viewHolder.mChronometer.setVisibility(View.GONE);
                    viewHolder.island_small_text_left.setText("");
                    viewHolder.island_small_text_right.setText("");
                }
                Log.e("TAG", "onBindViewHolder: MediaStyle 4");
            } else if (!notification.template.equals("MediaStyle") || notification.isClearable) {
                viewHolder.island_small_text_right.setVisibility(View.GONE);
                viewHolder.island_small_image_right.setImageBitmap(notification.senderIcon);
                Log.e("TAG", "onBindViewHolder: MediaStyle 2");
                if (notification.category.equals("navigation")) {
                    viewHolder.island_small_image_right.setVisibility(View.GONE);
                    viewHolder.island_small_text_left.setVisibility(View.GONE);
                    viewHolder.island_small_image_left.setVisibility(View.VISIBLE);
                    viewHolder.island_small_image_left.setImageBitmap(notification.senderIcon);
                    Log.e("TAG", "onBindViewHolder: MediaStyle navigation");
                    if (notification.tv_title == null || notification.tv_title.length() <= 0) {
                        viewHolder.island_small_text_right.setVisibility(View.GONE);
                    } else {
                        viewHolder.island_small_text_right.setVisibility(View.VISIBLE);
                        String split = notification.tv_title.toString();
                        if (!split.isEmpty()) {
                            viewHolder.island_small_text_right.setText(split);
                        } else {
                            viewHolder.island_small_text_right.setText("");
                        }
                    }
                }
            } else {
                //Notification_MediaView
                Log.e("TAG", "onBindViewHolder: MediaStyle 1");
                viewHolder.island_small_image_right.setVisibility(View.GONE);
                viewHolder.island_small_image_left.setVisibility(View.VISIBLE);
                Glide.with(mContext).load(notification.senderIcon).circleCrop().into(viewHolder.island_small_image_left);
                if (prefManager.getShowNotificationMusicRotate()) {
                    if (notification.template.equals("MediaStyle")) {
                        rotateImageViewInfinity(viewHolder.island_small_image_left);
                    }
                }
                viewHolder.mLottieAnimationView.setAnimation((int) R.raw.call_anim);
                viewHolder.mLottieAnimationView.setVisibility(View.VISIBLE);
                if (!viewHolder.mLottieAnimationView.isAnimating()) {
                    viewHolder.mLottieAnimationView.playAnimation();
                }
                viewHolder.island_small_text_right.setVisibility(View.GONE);
                viewHolder.island_small_text_left.setVisibility(View.GONE);

            }
            boolean isClickItemNotification = new SharePreferenceUtil(mContext).getBooleanValue(AppConst.CLICK_MODE, true);
            if (isClickItemNotification) {
                viewHolder.itemView.setOnClickListener(view -> {
                    int absoluteAdapterPosition = viewHolder.getAbsoluteAdapterPosition();
                    if (absoluteAdapterPosition >= 0 && absoluteAdapterPosition < CustomNotificationIconAdapter.this.notifications.size()) {
                        CustomNotificationIconAdapter.this.notificationListener.onItemClicked((Notification) CustomNotificationIconAdapter.this.notifications.get(absoluteAdapterPosition));
                        CustomNotificationIconAdapter.this.notificationListener.onItemClicked((Notification) CustomNotificationIconAdapter.this.notifications.get(absoluteAdapterPosition), absoluteAdapterPosition, false);
                    }
                });
            } else {
                viewHolder.itemView.setOnClickListener(view -> {
                    int absoluteAdapterPosition = viewHolder.getAbsoluteAdapterPosition();
                    if (absoluteAdapterPosition >= 0 && absoluteAdapterPosition < CustomNotificationIconAdapter.this.notifications.size()) {
                        CustomNotificationIconAdapter.this.notificationListener.onItemClicked((Notification) CustomNotificationIconAdapter.this.notifications.get(absoluteAdapterPosition));
                        CustomNotificationIconAdapter.this.notificationListener.onItemClicked((Notification) CustomNotificationIconAdapter.this.notifications.get(absoluteAdapterPosition), absoluteAdapterPosition, true);
                    }
                });
            }
        }
    }

    ObjectAnimator animator = null;

    public void rotateImageViewInfinity(ImageView imageView) {
        animator = ObjectAnimator.ofFloat(imageView, "rotation", 0f, 360f);
        animator.setDuration(3000L);
        animator.setRepeatCount(Animation.INFINITE);
        animator.setRepeatMode(ValueAnimator.RESTART);
        animator.start();
    }

    public void stopRotateImageViewInfinity(ImageView imageView) {
        if (animator != null) {
            animator.cancel();
            imageView.setRotation(0f);
        }
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {

        public final ImageView island_small_image_left;

        public final ImageView island_small_image_right;

        public final TextView island_small_text_left;

        public final TextView island_small_text_right;

        public final Chronometer mChronometer;

        public final LottieAnimationView mLottieAnimationView;
        private final View mRootLayout;

        public ViewHolder(View view) {
            super(view);
            this.mRootLayout = view;
            this.island_small_image_left = (ImageView) view.findViewById(R.id.island_small_left_iv);
            this.island_small_text_left = (TextView) view.findViewById(R.id.island_small_text_left);
            this.mLottieAnimationView = (LottieAnimationView) view.findViewById(R.id.right_lottie);
            this.island_small_image_right = (ImageView) view.findViewById(R.id.island_small_image_right);
            this.island_small_text_right = (TextView) view.findViewById(R.id.island_small_text_right);
            this.mChronometer = (Chronometer) view.findViewById(R.id.chronometer);
        }
    }

    public void onViewDetachedFromWindow(ViewHolder viewHolder) {
        super.onViewDetachedFromWindow(viewHolder);
    }
}
