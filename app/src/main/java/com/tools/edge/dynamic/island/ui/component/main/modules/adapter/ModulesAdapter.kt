package com.tools.edge.dynamic.island.ui.component.main.modules.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.tools.edge.dynamic.island.databinding.ItemModulesBinding
import com.tools.edge.dynamic.island.ui.bases.ext.click
import com.tools.edge.dynamic.island.ui.component.model.ModulesModel
import com.tools.edge.dynamic.island.ui.component.model.ModulesType

class ModulesAdapter(val listModules: MutableList<ModulesModel>) :
    RecyclerView.Adapter<ModulesAdapter.ModulesViewHolder>() {
    var onItemModulesClickedListener: ((ModulesType) -> Unit)? = null

    inner class ModulesViewHolder(val binding: ItemModulesBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(position: Int) {
            val modulesItem = listModules[position]
            binding.imgModule.setImageResource(modulesItem.icon)
            binding.tvNameModule.text = modulesItem.name
            binding.tvDetailsModule.text = modulesItem.details
            binding.constraintContainer.click {
                onItemModulesClickedListener?.invoke(modulesItem.type)
            }
        }
    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ModulesViewHolder {
        val binding = ItemModulesBinding.inflate(LayoutInflater.from(p0.context), p0, false)
        return ModulesViewHolder(binding)
    }

    override fun getItemCount(): Int = listModules.size

    override fun onBindViewHolder(p0: ModulesViewHolder, p1: Int) {
        p0.onBind(p1)
    }
}