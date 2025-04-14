package com.tools.edge.dynamic.island.databinding;
import com.tools.edge.dynamic.island.R;
import com.tools.edge.dynamic.island.BR;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class FragmentConfigsBindingImpl extends FragmentConfigsBinding  {

    @Nullable
    private static final androidx.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.tv_configs, 1);
        sViewsWithIds.put(R.id.rlt_keep_notch, 2);
        sViewsWithIds.put(R.id.tv_keep_notch_visible, 3);
        sViewsWithIds.put(R.id.when_nothing, 4);
        sViewsWithIds.put(R.id.tv_always, 5);
        sViewsWithIds.put(R.id.img_arrow, 6);
        sViewsWithIds.put(R.id.switch_auto_adjust, 7);
        sViewsWithIds.put(R.id.tv_force_your, 8);
        sViewsWithIds.put(R.id.switch_hide_in_landscape_mode, 9);
        sViewsWithIds.put(R.id.switch_notification_anim, 10);
        sViewsWithIds.put(R.id.rlt_notch_click_mode, 11);
        sViewsWithIds.put(R.id.tv_notch_click_mode, 12);
        sViewsWithIds.put(R.id.tv_normal, 13);
        sViewsWithIds.put(R.id.img_arrow_notch, 14);
    }
    // views
    @NonNull
    private final android.widget.ScrollView mboundView0;
    // variables
    // values
    // listeners
    // Inverse Binding Event Handlers

    public FragmentConfigsBindingImpl(@Nullable androidx.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 15, sIncludes, sViewsWithIds));
    }
    private FragmentConfigsBindingImpl(androidx.databinding.DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 0
            , (androidx.appcompat.widget.AppCompatImageView) bindings[6]
            , (androidx.appcompat.widget.AppCompatImageView) bindings[14]
            , (android.widget.RelativeLayout) bindings[2]
            , (android.widget.RelativeLayout) bindings[11]
            , (androidx.appcompat.widget.SwitchCompat) bindings[7]
            , (androidx.appcompat.widget.SwitchCompat) bindings[9]
            , (androidx.appcompat.widget.SwitchCompat) bindings[10]
            , (androidx.appcompat.widget.AppCompatTextView) bindings[5]
            , (androidx.appcompat.widget.AppCompatTextView) bindings[1]
            , (androidx.appcompat.widget.AppCompatTextView) bindings[8]
            , (androidx.appcompat.widget.AppCompatTextView) bindings[3]
            , (androidx.appcompat.widget.AppCompatTextView) bindings[13]
            , (androidx.appcompat.widget.AppCompatTextView) bindings[12]
            , (androidx.appcompat.widget.AppCompatTextView) bindings[4]
            );
        this.mboundView0 = (android.widget.ScrollView) bindings[0];
        this.mboundView0.setTag(null);
        setRootTag(root);
        // listeners
        invalidateAll();
    }

    @Override
    public void invalidateAll() {
        synchronized(this) {
                mDirtyFlags = 0x1L;
        }
        requestRebind();
    }

    @Override
    public boolean hasPendingBindings() {
        synchronized(this) {
            if (mDirtyFlags != 0) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean setVariable(int variableId, @Nullable Object variable)  {
        boolean variableSet = true;
            return variableSet;
    }

    @Override
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        switch (localFieldId) {
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
    }
    // Listener Stub Implementations
    // callback impls
    // dirty flag
    private  long mDirtyFlags = 0xffffffffffffffffL;
    /* flag mapping
        flag 0 (0x1L): null
    flag mapping end*/
    //end
}