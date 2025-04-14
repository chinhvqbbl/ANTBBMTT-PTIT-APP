package com.tools.edge.dynamic.island.databinding;
import com.tools.edge.dynamic.island.R;
import com.tools.edge.dynamic.island.BR;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class ActivityMainBindingImpl extends ActivityMainBinding  {

    @Nullable
    private static final androidx.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = new androidx.databinding.ViewDataBinding.IncludedLayouts(21);
        sIncludes.setIncludes(1, 
            new String[] {"ads_shimmer_native_large"},
            new int[] {4},
            new int[] {com.tools.edge.dynamic.island.R.layout.ads_shimmer_native_large});
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.include, 3);
        sViewsWithIds.put(R.id.relay_ads, 5);
        sViewsWithIds.put(R.id.vpg_main, 6);
        sViewsWithIds.put(R.id.linear_bottom, 7);
        sViewsWithIds.put(R.id.linear_home, 8);
        sViewsWithIds.put(R.id.img_home, 9);
        sViewsWithIds.put(R.id.tv_home, 10);
        sViewsWithIds.put(R.id.linear_module, 11);
        sViewsWithIds.put(R.id.img_module, 12);
        sViewsWithIds.put(R.id.tv_module, 13);
        sViewsWithIds.put(R.id.linear_configs, 14);
        sViewsWithIds.put(R.id.img_configs, 15);
        sViewsWithIds.put(R.id.tv_configs, 16);
        sViewsWithIds.put(R.id.constraint_tutor, 17);
        sViewsWithIds.put(R.id.switch_display_notch_tutor, 18);
        sViewsWithIds.put(R.id.img_display_tutor, 19);
        sViewsWithIds.put(R.id.tutor_details, 20);
    }
    // views
    @NonNull
    private final androidx.constraintlayout.widget.ConstraintLayout mboundView0;
    // variables
    // values
    // listeners
    // Inverse Binding Event Handlers

    public ActivityMainBindingImpl(@Nullable androidx.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 21, sIncludes, sViewsWithIds));
    }
    private ActivityMainBindingImpl(androidx.databinding.DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 1
            , (androidx.constraintlayout.widget.ConstraintLayout) bindings[17]
            , (android.widget.FrameLayout) bindings[1]
            , (android.widget.FrameLayout) bindings[2]
            , (android.widget.ImageView) bindings[15]
            , (androidx.appcompat.widget.AppCompatImageView) bindings[19]
            , (android.widget.ImageView) bindings[9]
            , (android.widget.ImageView) bindings[12]
            , (android.view.View) bindings[3]
            , (android.widget.LinearLayout) bindings[7]
            , (android.widget.LinearLayout) bindings[14]
            , (android.widget.LinearLayout) bindings[8]
            , (android.widget.LinearLayout) bindings[11]
            , (android.widget.RelativeLayout) bindings[5]
            , (com.tools.edge.dynamic.island.databinding.AdsShimmerNativeLargeBinding) bindings[4]
            , (com.google.android.material.switchmaterial.SwitchMaterial) bindings[18]
            , (androidx.appcompat.widget.AppCompatTextView) bindings[20]
            , (androidx.appcompat.widget.AppCompatTextView) bindings[16]
            , (androidx.appcompat.widget.AppCompatTextView) bindings[10]
            , (androidx.appcompat.widget.AppCompatTextView) bindings[13]
            , (androidx.viewpager2.widget.ViewPager2) bindings[6]
            );
        this.frAds.setTag(null);
        this.frBanner.setTag(null);
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