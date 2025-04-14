package com.tools.edge.dynamic.island.ui.component.language

import android.R.attr.data
import android.app.Activity
import android.view.View
import androidx.core.content.ContextCompat
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import com.tools.edge.dynamic.island.R
import com.tools.edge.dynamic.island.databinding.ItemLanguageBinding
import com.tools.edge.dynamic.island.ui.bases.BaseRecyclerView


class LanguageAdapter(
    var activity: Activity, val onClickItemLanguage: (LanguageModel) -> Unit
) : BaseRecyclerView<LanguageModel>() {

    override fun getItemLayout() = R.layout.item_language

    override fun submitData(newData: List<LanguageModel>) {
        val diffResult = DiffUtil.calculateDiff(com.tools.edge.dynamic.island.ui.component.language.DiffUtil(newData, list))
        diffResult.dispatchUpdatesTo(this)
        list.clear()
        list.addAll(newData)
//        notifyDataSetChanged()
    }

    override fun setData(binding: ViewDataBinding, item: LanguageModel, layoutPosition: Int) {
        if (binding is ItemLanguageBinding) {
            context?.let { ctx ->
                binding.imgLanguage.setImageDrawable(ctx.getDrawable(item.image!!))
                binding.imgLanguage.borderColor = ContextCompat.getColor(ctx, R.color.color_9E9E9E)
                binding.tvTitleLanguage.text = item.languageName
                binding.checkboxLanguage.isChecked = item.isCheck
            }
        }
    }


    override fun getItemCount(): Int {
        return list.size
    }

    override fun onClickViews(binding: ViewDataBinding, obj: LanguageModel, layoutPosition: Int) {
        super.onClickViews(binding, obj, layoutPosition)
        if (binding is ItemLanguageBinding) {
            binding.root.setOnClickListener { v: View? ->
                onClickItemLanguage(obj)
            }
            binding.checkboxLanguage.setOnClickListener { v: View? ->
                onClickItemLanguage(obj)
            }
        }
    }

    fun setSelectLanguage(model: LanguageModel) {
        for (data in list) {
            data.isCheck = data.languageName == model.languageName
        }
        notifyDataSetChanged()
    }

}