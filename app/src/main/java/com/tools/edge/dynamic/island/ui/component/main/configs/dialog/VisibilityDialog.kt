package com.tools.edge.dynamic.island.ui.component.main.configs.dialog

import android.annotation.SuppressLint
import android.content.Context
import com.tools.edge.dynamic.island.R
import com.tools.edge.dynamic.island.databinding.DialogKeepNotchVisibleBinding
import com.tools.edge.dynamic.island.ui.bases.BaseDialog
import com.tools.edge.dynamic.island.ui.component.background.AppConst
import com.tools.edge.dynamic.island.ui.component.entity.VisibilityModel
import com.tools.edge.dynamic.island.utils.SharePreferenceUtil

class VisibilityDialog(context: Context, val callback: (id: Int) -> Unit) :
    BaseDialog<DialogKeepNotchVisibleBinding>(context) {
    private val listVisibilityModel = mutableListOf<VisibilityModel>()
    private var adapter: VisibilityAdapter? = null

    init {
        listVisibilityModel.add(VisibilityModel(0, context.getString(R.string.always), true))
        listVisibilityModel.add(VisibilityModel(3, context.getString(R.string.seconds_3), false))
        listVisibilityModel.add(VisibilityModel(10, context.getString(R.string.seconds_10), false))
        listVisibilityModel.add(VisibilityModel(30, context.getString(R.string.seconds_30), false))
        listVisibilityModel.add(VisibilityModel(60, context.getString(R.string.minute_1), false))
        listVisibilityModel.add(VisibilityModel(300, context.getString(R.string.minute_5), false))
        listVisibilityModel.add(VisibilityModel(600, context.getString(R.string.minute_10), false))
    }

    override fun getLayoutDialog(): Int = R.layout.dialog_keep_notch_visible

    @SuppressLint("NotifyDataSetChanged")
    override fun initViews() {
        super.initViews()
        adapter = VisibilityAdapter()
        val id = SharePreferenceUtil(context).getIntValue(AppConst.NOTCH_VISIBILITY, 0)
        listVisibilityModel.forEach { visibilityModel ->
            if (visibilityModel.id == id) {
                visibilityModel.isSelected = true
            } else {
                visibilityModel.isSelected = false
            }
        }
        adapter?.setData(listVisibilityModel)
        mBinding.rcvVisibility.adapter = adapter
        adapter?.onItemVisibilityClickListener = { pos, id ->
            val item = listVisibilityModel[pos]
            listVisibilityModel.forEach { visibility ->
                if (item == visibility) {
                    visibility.isSelected = true
                } else {
                    visibility.isSelected = false
                }
            }
            adapter?.setData(listVisibilityModel)

            when (id) {
                0 -> {
                    SharePreferenceUtil(context).setIntValue(AppConst.NOTCH_VISIBILITY, 0)
                    callback.invoke(0)
                }

                3 -> {
                    SharePreferenceUtil(context).setIntValue(AppConst.NOTCH_VISIBILITY, 3)
                    callback.invoke(3)
                }

                10 -> {
                    SharePreferenceUtil(context).setIntValue(AppConst.NOTCH_VISIBILITY, 10)
                    callback.invoke(10)
                }

                30 -> {
                    SharePreferenceUtil(context).setIntValue(AppConst.NOTCH_VISIBILITY, 30)
                    callback.invoke(30)
                }

                60 -> {
                    SharePreferenceUtil(context).setIntValue(AppConst.NOTCH_VISIBILITY, 60)
                    callback.invoke(60)
                }

                300 -> {
                    SharePreferenceUtil(context).setIntValue(AppConst.NOTCH_VISIBILITY, 300)
                    callback.invoke(300)
                }

                600 -> {
                    SharePreferenceUtil(context).setIntValue(AppConst.NOTCH_VISIBILITY, 600)
                    callback.invoke(600)
                }
            }
        }
    }
}