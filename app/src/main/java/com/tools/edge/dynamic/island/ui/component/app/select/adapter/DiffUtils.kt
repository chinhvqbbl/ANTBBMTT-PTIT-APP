package com.tools.edge.dynamic.island.ui.component.app.select.adapter

import androidx.recyclerview.widget.DiffUtil
import com.tools.edge.dynamic.island.ui.component.entity.AppDetail

class DiffUtils(val oldList: List<com.tools.edge.dynamic.island.ui.component.entity.AppDetail>, val newList: List<com.tools.edge.dynamic.island.ui.component.entity.AppDetail>) : DiffUtil.Callback() {
    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] == newList[newItemPosition]
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].isSelected == newList[newItemPosition].isSelected && oldList[oldItemPosition].isCurrentUser == newList[newItemPosition].isCurrentUser && oldList[oldItemPosition].isSorted == newList[newItemPosition].isSorted && oldList[oldItemPosition].activityInfoName == newList[newItemPosition].activityInfoName && oldList[oldItemPosition].label == newList[newItemPosition].label && oldList[oldItemPosition].pkg == newList[newItemPosition].pkg && oldList[oldItemPosition].image == newList[newItemPosition].image
    }
}