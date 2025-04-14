package com.tools.edge.dynamic.island.ui.component.app.select.adapter

import android.content.Context
import android.content.pm.PackageManager
import android.graphics.drawable.Drawable
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import com.bumptech.glide.Glide
import com.tools.edge.dynamic.island.R
import com.tools.edge.dynamic.island.databinding.ItemAppSelectedBinding
import com.tools.edge.dynamic.island.ui.bases.BaseRecyclerView
import com.tools.edge.dynamic.island.ui.bases.ext.click

private const val TAG = "SelectAppAdapter"

class SelectAppAdapter(val onClickItem: (com.tools.edge.dynamic.island.ui.component.entity.AppDetail, Boolean) -> Unit, val dataString: String) :
    BaseRecyclerView<com.tools.edge.dynamic.island.ui.component.entity.AppDetail>() {


    override fun getItemLayout(): Int {
        return R.layout.item_app_selected
    }

    override fun getItemId(position: Int): Long {
        return list[position].image.toLong()
    }

    override fun setData(binding: ViewDataBinding, item: com.tools.edge.dynamic.island.ui.component.entity.AppDetail, layoutPosition: Int) {

        if (binding is ItemAppSelectedBinding) {

            binding.tvNameApp.text = item.label
            try {
                context?.let {
                    Glide.with(it).load(getApplicationIcon(it, item.pkg))
                        .placeholder(R.drawable.ic_launcher_foreground).override(128)
                        .into(binding.shapeableImageView)
                }
            } catch (e: Exception) {

            }

            binding.switchDisplayNotch.setImageResource(if (item.isSelected) R.drawable.ic_select_app else R.drawable.ic_un_select_app)

            binding.switchDisplayNotch.click {
                val selected = !item.isSelected
                onClickItem(item,selected)
                binding.switchDisplayNotch.setImageResource(if (item.isSelected) R.drawable.ic_select_app else R.drawable.ic_un_select_app)
            }
//            binding.switchDisplayNotch.setOnCheckedChangeListener { buttonView, isChecked ->
////                Log.d(
////                    TAG,
////                    "isChecked = $isChecked + position ${layoutPosition}"
////                )
////
////                lastTimeClick = System.currentTimeMillis()
////                onClickItem(item, isChecked)
//                item.isSelected = isChecked
//            }
        }
    }

    fun getApplicationIcon(context: Context, packageName: String?): Drawable? {
        try {
            val packageManager: PackageManager = context.getPackageManager()
            val appInfo =
                packageManager.getApplicationInfo(packageName!!, PackageManager.GET_META_DATA)
            return appInfo.loadIcon(packageManager)
        } catch (e: PackageManager.NameNotFoundException) {
            e.printStackTrace()
            // Handle exception if the package name is not found
        }
        return null
    }

    override fun submitData(newData: List<com.tools.edge.dynamic.island.ui.component.entity.AppDetail>) {
        val diffResult = DiffUtil.calculateDiff(DiffUtils(newData, list))
        diffResult.dispatchUpdatesTo(this)
        list.clear()
        this.list.addAll(newData)
    }
}