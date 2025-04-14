package com.tools.edge.dynamic.island.ui.component.language

import androidx.recyclerview.widget.DiffUtil

class DiffUtil(var oldListLanguage: List<LanguageModel>, var newListLanguage: List<LanguageModel>) :
    DiffUtil.Callback() {
    override fun getOldListSize(): Int {
        return oldListLanguage.size
    }

    override fun getNewListSize(): Int {
        return newListLanguage.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return newListLanguage[newItemPosition] == oldListLanguage[oldItemPosition]
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
      return newListLanguage[newItemPosition].isCheck == oldListLanguage[oldItemPosition].isCheck &&
              newListLanguage[newItemPosition].languageName == oldListLanguage[oldItemPosition].languageName &&
              newListLanguage[newItemPosition].isoLanguage == oldListLanguage[oldItemPosition].isoLanguage &&

              newListLanguage[newItemPosition].isCheck == oldListLanguage[oldItemPosition].isCheck
    }
}