package com.tools.edge.dynamic.island.ui.component.main.configs.dialog

import android.content.Context
import androidx.core.view.isVisible
import com.tools.edge.dynamic.island.R
import com.tools.edge.dynamic.island.databinding.DialogNotificationBinding
import com.tools.edge.dynamic.island.ui.bases.BaseDialog
import com.tools.edge.dynamic.island.ui.bases.ext.click
import com.tools.edge.dynamic.island.ui.component.background.AppConst
import com.tools.edge.dynamic.island.utils.SharePreferenceUtil

class NotificationDialog(context: Context, val callback: (pos: Int)->Unit) : BaseDialog<DialogNotificationBinding>(context) {

    override fun getLayoutDialog(): Int = R.layout.dialog_notification

    override fun initViews() {
        super.initViews()
        val neon = SharePreferenceUtil(context).getBooleanValue(AppConst.NOTIFICATION_ANIMATION,true)
        if(neon){
            mBinding.imgTickNeon.isVisible = true
            mBinding.imgTickShake.isVisible = false
        }else{
            mBinding.imgTickNeon.isVisible = false
            mBinding.imgTickShake.isVisible = true
        }
        mBinding.rltNeon.click {
            SharePreferenceUtil(context).setBooleanValue(AppConst.NOTIFICATION_ANIMATION,true)
            mBinding.imgTickNeon.isVisible = true
            mBinding.imgTickShake.isVisible = false
            callback.invoke(0)
        }
        mBinding.rltShake.click {
            SharePreferenceUtil(context).setBooleanValue(AppConst.NOTIFICATION_ANIMATION,false)
            mBinding.imgTickNeon.isVisible = false
            mBinding.imgTickShake.isVisible = true
            callback.invoke(1)
        }
    }
}