package com.tools.edge.dynamic.island.ui.component.main.configs.dialog

import android.content.Context
import androidx.core.view.isVisible
import com.tools.edge.dynamic.island.R
import com.tools.edge.dynamic.island.databinding.DialogNotchClickBinding
import com.tools.edge.dynamic.island.ui.bases.BaseDialog
import com.tools.edge.dynamic.island.ui.bases.ext.click
import com.tools.edge.dynamic.island.ui.component.background.AppConst
import com.tools.edge.dynamic.island.utils.SharePreferenceUtil

class NotchClickDialog(context: Context, val callback: (isNormalClick: Boolean) -> Unit) :
    BaseDialog<DialogNotchClickBinding>(context) {

    override fun getLayoutDialog(): Int = R.layout.dialog_notch_click

    override fun initViews() {
        super.initViews()
        val isClick = SharePreferenceUtil(context).getBooleanValue(AppConst.CLICK_MODE, true)
        if (isClick) {
            mBinding.imgTickNormalClick.isVisible = true
            mBinding.imgTickLongClick.isVisible = false
        } else {
            mBinding.imgTickNormalClick.isVisible = false
            mBinding.imgTickLongClick.isVisible = true
        }
        mBinding.rltNormalClick.click {
            mBinding.imgTickNormalClick.isVisible = true
            mBinding.imgTickLongClick.isVisible = false
            SharePreferenceUtil(context).setBooleanValue(AppConst.CLICK_MODE, true)
            callback.invoke(true)
        }
        mBinding.rltLongClick.click {
            SharePreferenceUtil(context).setBooleanValue(AppConst.CLICK_MODE, false)
            mBinding.imgTickNormalClick.isVisible = false
            mBinding.imgTickLongClick.isVisible = true
            callback.invoke(false)
        }
    }
}