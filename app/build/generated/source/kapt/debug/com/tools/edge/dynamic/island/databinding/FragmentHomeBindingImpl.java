package com.tools.edge.dynamic.island.databinding;
import com.tools.edge.dynamic.island.R;
import com.tools.edge.dynamic.island.BR;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class FragmentHomeBindingImpl extends FragmentHomeBinding  {

    @Nullable
    private static final androidx.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.constraint_container, 1);
        sViewsWithIds.put(R.id.tv_dynamic_island, 2);
        sViewsWithIds.put(R.id.imv_home_settings, 3);
        sViewsWithIds.put(R.id.switch_display_notch, 4);
        sViewsWithIds.put(R.id.tv_notch_style, 5);
        sViewsWithIds.put(R.id.constraint_oval, 6);
        sViewsWithIds.put(R.id.img_dynamic_oval, 7);
        sViewsWithIds.put(R.id.rlt_oval, 8);
        sViewsWithIds.put(R.id.tv_apply_oval, 9);
        sViewsWithIds.put(R.id.img_tick, 10);
        sViewsWithIds.put(R.id.constraint_bunny, 11);
        sViewsWithIds.put(R.id.img_dynamic_bunny, 12);
        sViewsWithIds.put(R.id.rlt_bunny, 13);
        sViewsWithIds.put(R.id.tv_apply_bunny, 14);
        sViewsWithIds.put(R.id.img_tick_bunny, 15);
        sViewsWithIds.put(R.id.tv_notch_position, 16);
        sViewsWithIds.put(R.id.tv_vertical, 17);
        sViewsWithIds.put(R.id.rlt_vertical, 18);
        sViewsWithIds.put(R.id.img_vertical_bottom, 19);
        sViewsWithIds.put(R.id.seekbar_vertical, 20);
        sViewsWithIds.put(R.id.img_vertical_top, 21);
        sViewsWithIds.put(R.id.tv_horizontal, 22);
        sViewsWithIds.put(R.id.rlt_horizontal, 23);
        sViewsWithIds.put(R.id.img_horizontal_left, 24);
        sViewsWithIds.put(R.id.seekbar_horizontal, 25);
        sViewsWithIds.put(R.id.img_horizontal_right, 26);
        sViewsWithIds.put(R.id.tv_reset_position, 27);
        sViewsWithIds.put(R.id.tv_notch_size, 28);
        sViewsWithIds.put(R.id.tv_width, 29);
        sViewsWithIds.put(R.id.rlt_width, 30);
        sViewsWithIds.put(R.id.img_width_bottom, 31);
        sViewsWithIds.put(R.id.seekbar_width, 32);
        sViewsWithIds.put(R.id.img_width_top, 33);
        sViewsWithIds.put(R.id.tv_height, 34);
        sViewsWithIds.put(R.id.rlt_height, 35);
        sViewsWithIds.put(R.id.img_height_bottom, 36);
        sViewsWithIds.put(R.id.seekbar_height, 37);
        sViewsWithIds.put(R.id.img_height_top, 38);
        sViewsWithIds.put(R.id.tv_reset_size, 39);
        sViewsWithIds.put(R.id.view_fake, 40);
    }
    // views
    @NonNull
    private final androidx.core.widget.NestedScrollView mboundView0;
    // variables
    // values
    // listeners
    // Inverse Binding Event Handlers

    public FragmentHomeBindingImpl(@Nullable androidx.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 41, sIncludes, sViewsWithIds));
    }
    private FragmentHomeBindingImpl(androidx.databinding.DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 0
            , (androidx.constraintlayout.widget.ConstraintLayout) bindings[11]
            , (androidx.constraintlayout.widget.ConstraintLayout) bindings[1]
            , (androidx.constraintlayout.widget.ConstraintLayout) bindings[6]
            , (androidx.appcompat.widget.AppCompatImageView) bindings[12]
            , (androidx.appcompat.widget.AppCompatImageView) bindings[7]
            , (androidx.appcompat.widget.AppCompatImageView) bindings[36]
            , (androidx.appcompat.widget.AppCompatImageView) bindings[38]
            , (androidx.appcompat.widget.AppCompatImageView) bindings[24]
            , (androidx.appcompat.widget.AppCompatImageView) bindings[26]
            , (androidx.appcompat.widget.AppCompatImageView) bindings[10]
            , (androidx.appcompat.widget.AppCompatImageView) bindings[15]
            , (androidx.appcompat.widget.AppCompatImageView) bindings[19]
            , (androidx.appcompat.widget.AppCompatImageView) bindings[21]
            , (androidx.appcompat.widget.AppCompatImageView) bindings[31]
            , (androidx.appcompat.widget.AppCompatImageView) bindings[33]
            , (androidx.appcompat.widget.AppCompatImageView) bindings[3]
            , (android.widget.RelativeLayout) bindings[13]
            , (android.widget.RelativeLayout) bindings[35]
            , (android.widget.RelativeLayout) bindings[23]
            , (android.widget.RelativeLayout) bindings[8]
            , (android.widget.RelativeLayout) bindings[18]
            , (android.widget.RelativeLayout) bindings[30]
            , (android.widget.SeekBar) bindings[37]
            , (android.widget.SeekBar) bindings[25]
            , (android.widget.SeekBar) bindings[20]
            , (android.widget.SeekBar) bindings[32]
            , (com.google.android.material.switchmaterial.SwitchMaterial) bindings[4]
            , (androidx.appcompat.widget.AppCompatTextView) bindings[14]
            , (androidx.appcompat.widget.AppCompatTextView) bindings[9]
            , (androidx.appcompat.widget.AppCompatTextView) bindings[2]
            , (androidx.appcompat.widget.AppCompatTextView) bindings[34]
            , (androidx.appcompat.widget.AppCompatTextView) bindings[22]
            , (androidx.appcompat.widget.AppCompatTextView) bindings[16]
            , (androidx.appcompat.widget.AppCompatTextView) bindings[28]
            , (androidx.appcompat.widget.AppCompatTextView) bindings[5]
            , (androidx.appcompat.widget.AppCompatTextView) bindings[27]
            , (androidx.appcompat.widget.AppCompatTextView) bindings[39]
            , (androidx.appcompat.widget.AppCompatTextView) bindings[17]
            , (androidx.appcompat.widget.AppCompatTextView) bindings[29]
            , (android.view.View) bindings[40]
            );
        this.mboundView0 = (androidx.core.widget.NestedScrollView) bindings[0];
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