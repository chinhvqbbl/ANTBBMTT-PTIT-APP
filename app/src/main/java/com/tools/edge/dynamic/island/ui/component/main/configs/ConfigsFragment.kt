package com.tools.edge.dynamic.island.ui.component.main.configs

import android.content.Intent
import com.tools.edge.dynamic.island.R
import com.tools.edge.dynamic.island.databinding.FragmentConfigsBinding
import com.tools.edge.dynamic.island.ui.bases.BaseFragment
import com.tools.edge.dynamic.island.ui.bases.ext.click
import com.tools.edge.dynamic.island.ui.component.background.AppConst
import com.tools.edge.dynamic.island.ui.component.main.configs.dialog.NotchClickDialog
import com.tools.edge.dynamic.island.ui.component.main.configs.dialog.VisibilityDialog
import com.tools.edge.dynamic.island.utils.SharePreferenceUtil

class ConfigsFragment : BaseFragment<FragmentConfigsBinding>() {

    override fun getLayoutFragment(): Int = R.layout.fragment_configs


    override fun initViews() {
        super.initViews()
        val id = SharePreferenceUtil(requireContext()).getIntValue(AppConst.NOTCH_VISIBILITY, 0)
        initKeepNotch(id)
        mBinding.rltKeepNotch.click {
            val visibilityDialog = VisibilityDialog(requireContext()) { id ->
                val intent = Intent(requireContext().packageName + AppConst.INTENT_NOTCH_VISIBILITY)
                requireContext().sendBroadcast(intent)
                initKeepNotch(id)
            }
            visibilityDialog.show()
        }
        val hideInLandscape = SharePreferenceUtil(requireContext()).getBooleanValue(
            AppConst.HIDE_IN_LANDSCAPE_MODE, true
        )
        mBinding.switchHideInLandscapeMode.isChecked = hideInLandscape
        mBinding.switchHideInLandscapeMode.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                SharePreferenceUtil(requireContext()).setBooleanValue(
                    AppConst.HIDE_IN_LANDSCAPE_MODE, true
                )
            } else {
                SharePreferenceUtil(requireContext()).setBooleanValue(
                    AppConst.HIDE_IN_LANDSCAPE_MODE, false
                )
            }

        }
        val notificationAnim = SharePreferenceUtil(requireContext()).getBooleanValue(
            AppConst.NOTIFICATION_ANIMATION, false
        )
        mBinding.switchNotificationAnim.isChecked = notificationAnim
        mBinding.switchNotificationAnim.setOnCheckedChangeListener { _, isChecked ->
            if(isChecked){
                val intent = Intent(requireContext().packageName + AppConst.SHAKE_ANIMATION)
                requireContext().sendBroadcast(intent)
                SharePreferenceUtil(requireContext()).setBooleanValue(
                    AppConst.NOTIFICATION_ANIMATION, true
                )
            }else{
                SharePreferenceUtil(requireContext()).setBooleanValue(
                    AppConst.NOTIFICATION_ANIMATION, false
                )
            }
        }
        val clickMode =
            SharePreferenceUtil(requireContext()).getBooleanValue(AppConst.CLICK_MODE, true)
        if (clickMode) {
            mBinding.tvNormal.text = requireContext().getString(R.string.normal_click)
        } else {
            mBinding.tvNormal.text = requireContext().getString(R.string.long_click)
        }
        mBinding.rltNotchClickMode.click {
            val dialogNotification = NotchClickDialog(requireContext()) {
                when (it) {
                    true -> {
                        val intent = Intent(requireContext().packageName + AppConst.INTENT_LONG_CLICK)
                        requireContext().sendBroadcast(intent)
                        mBinding.tvNormal.text = requireContext().getString(R.string.normal_click)
                    }
                    false -> {
                        val intent = Intent(requireContext().packageName + AppConst.INTENT_LONG_CLICK)
                        requireContext().sendBroadcast(intent)
                        mBinding.tvNormal.text = requireContext().getString(R.string.long_click)
                    }
                }
            }
            dialogNotification.show()
        }
        val adjustNotch = SharePreferenceUtil(requireContext()).getBooleanValue(AppConst.AUTO_ADJUST_NOTCH,true)
        mBinding.switchAutoAdjust.isChecked = adjustNotch
        mBinding.switchAutoAdjust.setOnCheckedChangeListener { _, isChecked ->
            if(isChecked){
                SharePreferenceUtil(requireContext()).setBooleanValue(AppConst.AUTO_ADJUST_NOTCH,true)
            }else{
                SharePreferenceUtil(requireContext()).setBooleanValue(AppConst.AUTO_ADJUST_NOTCH,false)
            }
        }
    }

    private fun initKeepNotch(id: Int) {
        when (id) {
            0 -> mBinding.tvAlways.text = requireContext().getString(R.string.always)

            3 -> mBinding.tvAlways.text = requireContext().getString(R.string.seconds_3)

            10 -> mBinding.tvAlways.text = requireContext().getString(R.string.seconds_10)

            30 -> mBinding.tvAlways.text = requireContext().getString(R.string.seconds_30)

            60 -> mBinding.tvAlways.text = requireContext().getString(R.string.minute_1)

            300 -> mBinding.tvAlways.text = requireContext().getString(R.string.minute_5)

            600 -> mBinding.tvAlways.text = requireContext().getString(R.string.minute_10)
        }
    }
}