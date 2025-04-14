package com.tools.edge.dynamic.island.ui.component.moduldetails

import android.content.Context
import com.tools.edge.dynamic.island.R
import com.tools.edge.dynamic.island.databinding.DialogPermissionRequiredBinding
import com.tools.edge.dynamic.island.ui.bases.BaseDialog
import com.tools.edge.dynamic.island.ui.bases.ext.click

class PermissionRequiredDialog(context: Context, private val gotoSettings : ()->Unit) : BaseDialog<DialogPermissionRequiredBinding>(context) {
    override fun getLayoutDialog(): Int = R.layout.dialog_permission_required

    override fun initViews() {
        super.initViews()
        mBinding.tvGoToSettings.click {
            gotoSettings.invoke()
        }
        mBinding.tvCancel.click {
            dismiss()
        }
    }

}