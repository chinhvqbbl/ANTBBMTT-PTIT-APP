package com.tools.edge.dynamic.island.databinding;
import com.tools.edge.dynamic.island.R;
import com.tools.edge.dynamic.island.BR;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class ActivityLanguageBindingImpl extends ActivityLanguageBinding  {

    @Nullable
    private static final androidx.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = new androidx.databinding.ViewDataBinding.IncludedLayouts(8);
        sIncludes.setIncludes(1, 
            new String[] {"ads_shimmer_native_large"},
            new int[] {2},
            new int[] {com.tools.edge.dynamic.island.R.layout.ads_shimmer_native_large});
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.linear_language, 3);
        sViewsWithIds.put(R.id.toolbar_language, 4);
        sViewsWithIds.put(R.id.iv_done, 5);
        sViewsWithIds.put(R.id.rcl_language, 6);
        sViewsWithIds.put(R.id.relay_ads, 7);
    }
    // views
    @NonNull
    private final android.widget.RelativeLayout mboundView0;
    // variables
    // values
    // listeners
    // Inverse Binding Event Handlers

    public ActivityLanguageBindingImpl(@Nullable androidx.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 8, sIncludes, sViewsWithIds));
    }
    private ActivityLanguageBindingImpl(androidx.databinding.DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 1
            , (android.widget.FrameLayout) bindings[1]
            , (android.widget.TextView) bindings[5]
            , (android.widget.LinearLayout) bindings[3]
            , (androidx.recyclerview.widget.RecyclerView) bindings[6]
            , (android.widget.RelativeLayout) bindings[7]
            , (com.tools.edge.dynamic.island.databinding.AdsShimmerNativeLargeBinding) bindings[2]
            , (android.widget.RelativeLayout) bindings[4]
            );
        this.frAds.setTag(null);
        this.mboundView0 = (android.widget.RelativeLayout) bindings[0];
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