package com.tools.edge.dynamic.island.ui.component.main.configs.dialog

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.tools.edge.dynamic.island.databinding.ItemVisibilityBinding
import com.tools.edge.dynamic.island.ui.bases.ext.click
import com.tools.edge.dynamic.island.ui.component.entity.VisibilityModel

class VisibilityAdapter : RecyclerView.Adapter<VisibilityAdapter.VisibilityViewHolder>() {
    private var listVisibility = mutableListOf<VisibilityModel>()
    var onItemVisibilityClickListener: ((pos: Int,id: Int) -> Unit)? = null

    inner class VisibilityViewHolder(val binding: ItemVisibilityBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(position: Int) {
            val item = listVisibility[position]
            binding.tvName.text = item.name
            binding.rltContainer.click {
                onItemVisibilityClickListener?.invoke(position,item.id)
            }
            if (item.isSelected) {
                binding.imgTickVisibility.isVisible = true
            } else {
                binding.imgTickVisibility.isVisible = false
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VisibilityViewHolder {
        val binding =
            ItemVisibilityBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return VisibilityViewHolder(binding)
    }

    override fun getItemCount(): Int = listVisibility.size

    override fun onBindViewHolder(holder: VisibilityViewHolder, position: Int) {
        holder.onBind(position)
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setData(list: MutableList<VisibilityModel>) {
        listVisibility.clear()
        listVisibility.addAll(list)
        notifyDataSetChanged()
    }
}


