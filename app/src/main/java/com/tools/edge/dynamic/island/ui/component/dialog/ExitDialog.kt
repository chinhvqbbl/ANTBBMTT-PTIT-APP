package com.tools.edge.dynamic.island.ui.component.dialog

import android.app.Activity

import com.tools.edge.dynamic.island.R
import com.tools.edge.dynamic.island.ads.AdsManager
import com.tools.edge.dynamic.island.databinding.DialogExitBinding
import com.tools.edge.dynamic.island.ui.bases.BaseBottomSheetDialog

class ExitDialog(val mContext: Activity, val onClickExit: () -> Unit) :
    BaseBottomSheetDialog<DialogExitBinding>(mContext) {

    override fun getLayoutDialog(): Int {
        return R.layout.dialog_exit
    }


    override fun initViews() {
        super.initViews()
    }


    fun setAds() {

        showNativeExit()

    }

    private fun showNativeExit() {
        if (AdsManager.nativeExit != null) {

        } else {
            mBinding.frAds.removeAllViews()
        }
    }


    override fun onClickViews() {
        super.onClickViews()
        mBinding.textExit.setOnClickListener {
            onClickExit.invoke()
        }

        mBinding.tvCancel.setOnClickListener {
            dismiss()
        }
    }
}