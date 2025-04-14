package com.tools.edge.dynamic.island.ui.component.main.modules.viewmodel

import android.app.Activity
import com.tools.edge.dynamic.island.R
import com.tools.edge.dynamic.island.ui.bases.BaseViewModel
import com.tools.edge.dynamic.island.ui.component.model.ModulesModel
import com.tools.edge.dynamic.island.ui.component.model.ModulesType

class ModulesViewModel : BaseViewModel() {
    var listModules = mutableListOf<ModulesModel>()

    fun initListModules(context: Activity): MutableList<ModulesModel> {
        listModules.clear()
        listModules.add(
            ModulesModel(
                R.drawable.ic_music,
                context.getString(R.string.txt_music),
                context.getString(R.string.display_listening_music_on_your_notch),
                ModulesType.MUSIC
            )
        )
        listModules.add(
            ModulesModel(
                R.drawable.ic_notifications,
                context.getString(R.string.notifications),
                context.getString(R.string.get_animation_notifications_on_your_notch),
                ModulesType.NOTIFICATION
            )
        )
        listModules.add(
            ModulesModel(
                R.drawable.ic_calls,
                context.getString(R.string.calls),
                context.getString(R.string.incoming_calls_directly_on_your_notch),
                ModulesType.CALLS
            )
        )
        listModules.add(
            ModulesModel(
                R.drawable.ic_navigation,
                context.getString(R.string.navigation),
                context.getString(R.string.navigation_helper_with_google_map),
                ModulesType.NAVIGATION
            )
        )
        listModules.add(
            ModulesModel(
                R.drawable.ic_headset,
                context.getString(R.string.headset),
                context.getString(R.string.headset_connect_animation),
                ModulesType.HEADSET
            )
        )
        listModules.add(
            ModulesModel(
                R.drawable.ic_battery_change,
                context.getString(R.string.battery_Charge),
                context.getString(R.string.battery_charging_animation),
                ModulesType.BATTERY_CHANGE
            )
        )
        listModules.add(
            ModulesModel(
                R.drawable.ic_ringing_state,
                context.getString(R.string.ringing_state),
                context.getString(R.string.ringing_state_change_animation),
                ModulesType.RINGING_STATE
            )
        )
        return listModules
    }
}