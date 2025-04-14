package com.tools.edge.dynamic.island.databinding;
import com.tools.edge.dynamic.island.R;
import com.tools.edge.dynamic.island.BR;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class ActivityPermissionBindingImpl extends ActivityPermissionBinding  {

    @Nullable
    private static final androidx.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = new androidx.databinding.ViewDataBinding.IncludedLayouts(27);
        sIncludes.setIncludes(1, 
            new String[] {"ads_shimmer_native_permission"},
            new int[] {2},
            new int[] {com.tools.edge.dynamic.island.R.layout.ads_shimmer_native_permission});
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.img_back, 3);
        sViewsWithIds.put(R.id.img_number, 4);
        sViewsWithIds.put(R.id.tv_accessibility, 5);
        sViewsWithIds.put(R.id.tv_accessibility_details, 6);
        sViewsWithIds.put(R.id.switch_accessibility, 7);
        sViewsWithIds.put(R.id.imv_check_accessibility, 8);
        sViewsWithIds.put(R.id.img_number2, 9);
        sViewsWithIds.put(R.id.tv_notification, 10);
        sViewsWithIds.put(R.id.tv_notification_details, 11);
        sViewsWithIds.put(R.id.switch_notification, 12);
        sViewsWithIds.put(R.id.imv_check_notification, 13);
        sViewsWithIds.put(R.id.img_number3, 14);
        sViewsWithIds.put(R.id.tv_battery, 15);
        sViewsWithIds.put(R.id.tv_battery_details, 16);
        sViewsWithIds.put(R.id.switch_battery, 17);
        sViewsWithIds.put(R.id.imv_check_battery, 18);
        sViewsWithIds.put(R.id.layout_bluetooth, 19);
        sViewsWithIds.put(R.id.img_number4, 20);
        sViewsWithIds.put(R.id.tv_bluetooth, 21);
        sViewsWithIds.put(R.id.tv_bluetooth_details, 22);
        sViewsWithIds.put(R.id.switch_bluetooth, 23);
        sViewsWithIds.put(R.id.textView6, 24);
        sViewsWithIds.put(R.id.imv_check_bluetooth, 25);
        sViewsWithIds.put(R.id.relay_ads, 26);
    }
    // views
    @NonNull
    private final androidx.constraintlayout.widget.ConstraintLayout mboundView0;
    // variables
    // values
    // listeners
    // Inverse Binding Event Handlers

    public ActivityPermissionBindingImpl(@Nullable androidx.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 27, sIncludes, sViewsWithIds));
    }
    private ActivityPermissionBindingImpl(androidx.databinding.DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 1
            , (android.widget.FrameLayout) bindings[1]
            , (androidx.appcompat.widget.AppCompatImageView) bindings[3]
            , (androidx.appcompat.widget.AppCompatImageView) bindings[4]
            , (androidx.appcompat.widget.AppCompatImageView) bindings[9]
            , (androidx.appcompat.widget.AppCompatImageView) bindings[14]
            , (androidx.appcompat.widget.AppCompatImageView) bindings[20]
            , (android.widget.ImageView) bindings[8]
            , (android.widget.ImageView) bindings[18]
            , (android.widget.ImageView) bindings[25]
            , (android.widget.ImageView) bindings[13]
            , (androidx.constraintlayout.widget.ConstraintLayout) bindings[19]
            , (com.tools.edge.dynamic.island.databinding.AdsShimmerNativePermissionBinding) bindings[2]
            , (android.widget.RelativeLayout) bindings[26]
            , (androidx.constraintlayout.widget.ConstraintLayout) bindings[7]
            , (androidx.constraintlayout.widget.ConstraintLayout) bindings[17]
            , (androidx.constraintlayout.widget.ConstraintLayout) bindings[23]
            , (androidx.constraintlayout.widget.ConstraintLayout) bindings[12]
            , (android.widget.TextView) bindings[24]
            , (androidx.appcompat.widget.AppCompatTextView) bindings[5]
            , (androidx.appcompat.widget.AppCompatTextView) bindings[6]
            , (androidx.appcompat.widget.AppCompatTextView) bindings[15]
            , (androidx.appcompat.widget.AppCompatTextView) bindings[16]
            , (androidx.appcompat.widget.AppCompatTextView) bindings[21]
            , (androidx.appcompat.widget.AppCompatTextView) bindings[22]
            , (androidx.appcompat.widget.AppCompatTextView) bindings[10]
            , (androidx.appcompat.widget.AppCompatTextView) bindings[11]
            );
        this.frAds.setTag(null);
        setContainedBinding(this.layoutShimmer);
        this.mboundView0 = (androidx.constraintlayout.widget.ConstraintLayout) bindings[0];
        this.mboundView0.setTag(null);
        setRootTag(root);
        // listeners
        invalidateAll();
    }

    @Override
    public void invalidateAll() {
        synchronized(this) {
                mDirtyFlags = 0x2L;
        }
        layoutShimmer.invalidateAll();
        requestRebind();
    }

    @Override
    public boolean hasPendingBindings() {
        synchronized(this) {
            if (mDirtyFlags != 0) {
                return true;
            }
        }
        if (layoutShimmer.hasPendingBindings()) {
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
        layoutShimmer.setLifecycleOwner(lifecycleOwner);
    }

    @Override
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        switch (localFieldId) {
            case 0 :
                return onChangeLayoutShimmer((com.tools.edge.dynamic.island.databinding.AdsShimmerNativePermissionBinding) object, fieldId);
        }
        return false;
    }
    private boolean onChangeLayoutShimmer(com.tools.edge.dynamic.island.databinding.AdsShimmerNativePermissionBinding LayoutShimmer, int fieldId) {
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
        executeBindingsOn(layoutShimmer);
    }
    // Listener Stub Implementations
    // callback impls
    // dirty flag
    private  long mDirtyFlags = 0xffffffffffffffffL;
    /* flag mapping
        flag 0 (0x1L): layoutShimmer
        flag 1 (0x2L): null
    flag mapping end*/
    //end
}