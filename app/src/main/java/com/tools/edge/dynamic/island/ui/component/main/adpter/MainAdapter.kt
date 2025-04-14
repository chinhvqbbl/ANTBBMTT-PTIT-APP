package com.tools.edge.dynamic.island.ui.component.main.adpter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter


class MainAdapter(fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity) {

    private val fragmentList = ArrayList<Fragment>()
    override fun getItemCount(): Int {
        return fragmentList.size
    }

    fun addFragment(fragment: Fragment?) {
        fragmentList.add(fragment!!)
    }

    override fun createFragment(position: Int): Fragment {
        return fragmentList.get(position);
    }
}