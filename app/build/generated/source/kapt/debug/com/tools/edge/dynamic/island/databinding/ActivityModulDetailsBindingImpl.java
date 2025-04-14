package com.tools.edge.dynamic.island.databinding;
import com.tools.edge.dynamic.island.R;
import com.tools.edge.dynamic.island.BR;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class ActivityModulDetailsBindingImpl extends ActivityModulDetailsBinding  {

    @Nullable
    private static final androidx.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = new androidx.databinding.ViewDataBinding.IncludedLayouts(31);
        sIncludes.setIncludes(1, 
            new String[] {"ads_shimmer_native_large"},
            new int[] {2},
            new int[] {com.tools.edge.dynamic.island.R.layout.ads_shimmer_native_large});
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.layout_tool_bar, 3);
        sViewsWithIds.put(R.id.imv_back, 4);
        sViewsWithIds.put(R.id.tv_name_module, 5);
        sViewsWithIds.put(R.id.layout_music, 6);
        sViewsWithIds.put(R.id.switch_display_music, 7);
        sViewsWithIds.put(R.id.switch_rotate_music, 8);
        sViewsWithIds.put(R.id.layout_notification, 9);
        sViewsWithIds.put(R.id.switch_display_notification, 10);
        sViewsWithIds.put(R.id.btn_app_notifications, 11);
        sViewsWithIds.put(R.id.btn_icon_notification, 12);
        sViewsWithIds.put(R.id.imv_icon_next, 13);
        sViewsWithIds.put(R.id.switch_quick_display, 14);
        sViewsWithIds.put(R.id.switch_hide_on_lock_screen, 15);
        sViewsWithIds.put(R.id.switch_show_action, 16);
        sViewsWithIds.put(R.id.btn_notification_color, 17);
        sViewsWithIds.put(R.id.imv_icon_color, 18);
        sViewsWithIds.put(R.id.layout_call, 19);
        sViewsWithIds.put(R.id.switch_display_call, 20);
        sViewsWithIds.put(R.id.switch_display_call_timer, 21);
        sViewsWithIds.put(R.id.layout_navigation, 22);
        sViewsWithIds.put(R.id.switch_display_navigation, 23);
        sViewsWithIds.put(R.id.layout_haedset, 24);
        sViewsWithIds.put(R.id.switch_display_haedset, 25);
        sViewsWithIds.put(R.id.layout_battery_change, 26);
        sViewsWithIds.put(R.id.switch_display_battery_change, 27);
        sViewsWithIds.put(R.id.layout_ringing_state, 28);
        sViewsWithIds.put(R.id.switch_display_ringing_state, 29);
        sViewsWithIds.put(R.id.relay_ads, 30);
    }
    // views
    @NonNull
    private final androidx.constraintlayout.widget.ConstraintLayout mboundView0;
    // variables
    // values
    // listeners
    // Inverse Binding Event Handlers

    public ActivityModulDetailsBindingImpl(@Nullable androidx.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 31, sIncludes, sViewsWithIds));
    }
    private ActivityModulDetailsBindingImpl(androidx.databinding.DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 1
            , (android.widget.RelativeLayout) bindings[11]
            , (android.widget.RelativeLayout) bindings[12]
            , (android.widget.RelativeLayout) bindings[17]
            , (android.widget.FrameLayout) bindings[1]
            , (android.widget.ImageView) bindings[4]
            , (com.google.android.material.imageview.ShapeableImageView) bindings[18]
            , (android.widget.ImageView) bindings[13]
            , (android.widget.LinearLayout) bindings[26]
            , (android.widget.LinearLayout) bindings[19]
            , (android.widget.LinearLayout) bindings[24]
            , (android.widget.LinearLayout) bindings[6]
            , (android.widget.LinearLayout) bindings[22]
            , (android.widget.LinearLayout) bindings[9]
            , (android.widget.LinearLayout) bindings[28]
            , (android.widget.RelativeLayout) bindings[3]
            , (android.widget.RelativeLayout) bindings[30]
            , (com.tools.edge.dynamic.island.databinding.AdsShimmerNativeLargeBinding) bindings[2]
            , (android.widget.Switch) bindings[27]
            , (android.widget.Switch) bindings[20]
            , (android.widget.Switch) bindings[21]
            , (android.widget.Switch) bindings[25]
            , (android.widget.Switch) bindings[7]
            , (android.widget.Switch) bindings[23]
            , (android.widget.Switch) bindings[10]
            , (android.widget.Switch) bindings[29]
            , (android.widget.Switch) bindings[15]
            , (android.widget.Switch) bindings[14]
            , (android.widget.Switch) bindings[8]
            , (android.widget.Switch) bindings[16]
            , (android.widget.TextView) bindings[5]
            );
        this.frAds.setTag(null);
        this.mboundView0 = (androidx.constraintlayout.widget.ConstraintLayout) bindings[0];
        this.mboundView0.setTag(null);
        setContainedBinding(this.shimmerAds);
        setRootTag(root);
        // listeners
        invalidateAll();
    }

    @Override
    public void invalidateAll() {
        synchronized(this) {
                mDirtyFlags = 0x2L;
        }
        shimmerAds.invalidateAll();
        requestRebind();
    }

    @Override
    public boolean hasPendingBindings() {
        synchronized(this) {
            if (mDirtyFlags != 0) {
                return true;
            }
        }
        if (shimmerAds.hasPendingBindings()) {
            return true;
        }
        return false;
    }

    @Override
    public boolean setVariable(int variableId, @Nullable Object variable)  {
        boolean variableSet = true;
            return variableSet;
    }

    @Override
    public void setLifecycleOwner(@Nullable androidx.lifecycle.LifecycleOwner lifecycleOwner) {
        super.setLifecycleOwner(lifecycleOwner);
        shimmerAds.setLifecycleOwner(lifecycleOwner);
    }

    @Override
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        switch (localFieldId) {
            case 0 :
                return onChangeShimmerAds((com.tools.edge.dynamic.island.databinding.AdsShimmerNativeLargeBinding) object, fieldId);
        }
        return false;
    }
    private boolean onChangeShimmerAds(com.tools.edge.dynamic.island.databinding.AdsShimmerNativeLargeBinding ShimmerAds, int fieldId) {
        if (fieldId == BR._all) {
            synchronized(this) {
                    mDirtyFlags |= 0x1L;
            }
            return true;
        }
        return false;
    }

    @Override
    protected void executeBindings() {
        long dirtyFlags = 0;
        synchronized(this) {
            dirtyFlags = mDirtyFlags;
            mDirtyFlags = 0;
        }
        // batch finished
        executeBindingsOn(shimmerAds);
    }
    // Listener Stub Implementations
    // callback impls
    // dirty flag
    private  long mDirtyFlags = 0xffffffffffffffffL;
    /* flag mapping
        flag 0 (0x1L): shimmerAds
        flag 1 (0x2L): null
    flag mapping end*/
    //end
}