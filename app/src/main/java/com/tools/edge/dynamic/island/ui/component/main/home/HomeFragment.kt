package com.tools.edge.dynamic.island.ui.component.main.home

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.widget.SeekBar
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import com.tools.edge.dynamic.island.R
import com.tools.edge.dynamic.island.app.AppConstants
import com.tools.edge.dynamic.island.app.GlobalApp
import com.tools.edge.dynamic.island.app.GlobalApp.Companion.isback
import com.tools.edge.dynamic.island.databinding.FragmentHomeBinding
import com.tools.edge.dynamic.island.ui.bases.BaseFragment
import com.tools.edge.dynamic.island.ui.bases.ext.click
import com.tools.edge.dynamic.island.ui.bases.ext.goneView
import com.tools.edge.dynamic.island.ui.component.background.AppConst
import com.tools.edge.dynamic.island.ui.component.main.MainActivity
import com.tools.edge.dynamic.island.ui.component.permision.PermissionActivity
import com.tools.edge.dynamic.island.ui.component.service.ITGAccessibilityService
import com.tools.edge.dynamic.island.ui.component.service.NotificationService
import com.tools.edge.dynamic.island.ui.component.service.PermissionService
import com.tools.edge.dynamic.island.ui.component.utils.Constants
import com.tools.edge.dynamic.island.ui.component.utils.Utils
import com.tools.edge.dynamic.island.utils.EasyPreferences
import com.tools.edge.dynamic.island.utils.Routes
import com.tools.edge.dynamic.island.utils.SharePreferenceUtil
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HomeFragment : BaseFragment<FragmentHomeBinding>() {

    private var sharedPreferences: SharedPreferences? = null
    private var x: Int = 0
    private var y: Int = 0
    private var width: Int = 0
    private var height: Int = 0

    override fun getLayoutFragment(): Int = R.layout.fragment_home

    override fun onResume() {
        super.onResume()

        if (SharePreferenceUtil(requireContext()).getBooleanValue(
                AppConst.DYNAMIC_TURN_ON, false
            )
        ) {
            checkAccessibilityServiceActive()
        }


    }

    override fun initViews() {
        super.initViews()
        prefs = activity?.let { EasyPreferences.defaultPrefs(it) }!!
        sharedPreferences = requireContext().getSharedPreferences(
            requireContext().packageName, Context.MODE_PRIVATE
        )
        checkAccessibilityServiceActive()
        width = SharePreferenceUtil(requireContext()).getIntValue(AppConst.OVERLAY_WIDTH, 130)
        height = SharePreferenceUtil(requireContext()).getIntValue(AppConst.OVERLAY_HEIGHT, 25)
        x = SharePreferenceUtil(requireContext()).getIntValue(AppConst.OVERLAY_X, 0)
        y = SharePreferenceUtil(requireContext()).getIntValue(AppConst.OVERLAY_Y, 5)
        if (width == 130) {
            mBinding.seekbarWidth.progress = 130
        } else {
            mBinding.seekbarWidth.progress = width
        }

        if (height == 25) {
            mBinding.seekbarHeight.progress = 33
        } else {
            mBinding.seekbarHeight.progress = height
        }

        if (x == 0) {
            mBinding.seekbarHorizontal.progress = 0
        } else {
            mBinding.seekbarHorizontal.progress = x
        }
        if (y == 5) {
            mBinding.seekbarVertical.progress = 5
        } else {
            mBinding.seekbarVertical.progress = y
        }

        mBinding.switchDisplayNotch.setOnCheckedChangeListener { _, isChecked ->

            activity?.let { activity ->
                if (isChecked)
                {
                    if (Constants.checkAccessibilityEnabled(activity) && PermissionService.checkNotificationEnabled(
                            activity
                        ) && prefs.getBoolean(
                            AppConstants.keyCheckNotification, false
                        ) && prefs.getBoolean(AppConstants.keyCheckAccessibility, false)
                    ) {
                        lifecycleScope.launch {
                            mBinding.viewFake.isVisible = false
                            delay(100L)
                            if (width == 0) {
                                width = 130
                            }
                            if (height == 0) {
                                height = 25
                            }
                            if (x == 0) {
                                x = 0
                            }
                            if (y == 0) {
                                y = 5
                            }
                            sharedPreferences?.edit()?.putInt("overlay_w", width)?.apply()
                            sharedPreferences?.edit()?.putInt("overlay_h", height)?.apply()
                            sharedPreferences?.edit()?.putInt("overlay_x", x)?.apply()
                            sharedPreferences?.edit()?.putInt("overlay_y", y)?.apply()
                            SharePreferenceUtil(activity).setBooleanValue(
                                AppConst.DYNAMIC_TURN_ON, true
                            )
                            val intent = Intent(activity.packageName + AppConst.START_DYNAMIC)
                            val b = Bundle()
                            sharedPreferences?.all?.forEach { (key: String?, value: Any?) ->
                                if (value is Int) {
                                    b.putInt(key, value)
                                }
                            }
                            intent.putExtra("default_dynamic", b)
                            activity.sendBroadcast(intent)
                        }
                        if (Utils.isServiceRunning(
                                activity,
                                com.tools.edge.dynamic.island.ui.component.service.NotificationService::class.java.name
                            )
                        ) {
                            NotificationService.getInstance().initNotification()
                        }
                    } else {
                        Log.d(
                            "chinh_dep_trai_kk_initview",
                            "initViews() called with: activity = $activity"
                        )
                        mBinding.switchDisplayNotch.isChecked = false
                        if (isback == true) {
                            isback = false
                            Log.d(
                                "chinh_dep_trai_kk_3",
                                "initViews() called with: activity = $activity"
                            )
                            Routes.startPermissionActivity(activity, resultLauncher)
                        }
                    }
                } else {
                    mBinding.viewFake.isVisible = true
                    if (GlobalApp.instance.isAccessibilityServiceRunning) {

                        if (Utils.isServiceRunning(
                                activity, ITGAccessibilityService::class.java.getName()
                            )
                        ) {
                            ITGAccessibilityService.getInstance().closeFullNotificationIsland()
                            ITGAccessibilityService.getInstance().closeSmallIslandNotification()
                        }
                        SharePreferenceUtil(activity).setBooleanValue(
                            AppConst.DYNAMIC_TURN_ON, false
                        )
                        val intent = Intent(activity.packageName + AppConst.CLOSE_DYNAMIC)
                        activity.sendBroadcast(intent)
                    }
                }
            }

        }

        mBinding.seekbarVertical.setOnSeekBarChangeListener(object :
            SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                if (fromUser) {
                    y = progress
                    onChange()
                }
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {}

            override fun onStopTrackingTouch(seekBar: SeekBar?) {}

        })

        mBinding.seekbarHorizontal.setOnSeekBarChangeListener(object :
            SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                if (fromUser) {
                    x = progress
                    onChange()
                }
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {}

            override fun onStopTrackingTouch(seekBar: SeekBar?) {}

        })

        mBinding.seekbarWidth.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                if (fromUser) {
                    width = progress
                    onChange()
                }
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {}

            override fun onStopTrackingTouch(seekBar: SeekBar?) {}

        })

        mBinding.seekbarHeight.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                if (fromUser) {
                    height = progress
                    onChange()
                }
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {}

            override fun onStopTrackingTouch(seekBar: SeekBar?) {}

        })

        mBinding.imgVerticalBottom.click {
            var v = mBinding.seekbarVertical.progress
            v -= 1
            y = v
            mBinding.seekbarVertical.progress = v
            onChange()
        }

        mBinding.imgVerticalTop.click {
            var v = mBinding.seekbarVertical.progress
            v += 1
            y = v
            mBinding.seekbarVertical.progress = v
            onChange()
        }

        mBinding.imgHorizontalLeft.click {
            var v = mBinding.seekbarHorizontal.progress
            v -= 1
            x = v
            mBinding.seekbarHorizontal.progress = v
            onChange()
        }

        mBinding.imgHorizontalRight.click {
            var v = mBinding.seekbarHorizontal.progress
            v += 1
            x = v
            mBinding.seekbarHorizontal.progress = v
            onChange()
        }

        mBinding.imgWidthBottom.click {
            var v = mBinding.seekbarWidth.progress
            v -= 1
            width = v
            mBinding.seekbarWidth.progress = v
            onChange()
        }

        mBinding.imgWidthTop.click {
            var v = mBinding.seekbarWidth.progress
            v += 1
            width = v
            mBinding.seekbarWidth.progress = v
            onChange()
        }

        mBinding.imgHeightBottom.click {
            var v = mBinding.seekbarHeight.progress
            v -= 1
            height = v
            mBinding.seekbarHeight.progress = v
            onChange()
        }

        mBinding.imgHeightTop.click {
            var v = mBinding.seekbarHeight.progress
            v += 1
            height = v
            mBinding.seekbarHeight.progress = v
            onChange()
        }

        mBinding.rltOval.click {
            sharedPreferences?.edit()?.putBoolean("oval_notification", true)?.apply()
            val intent = Intent(requireContext().packageName + AppConst.DYNAMIC_STYLE)
            val b = Bundle()
            sharedPreferences?.all?.forEach { (key: String?, value: Any?) ->
                if (value is Boolean) {
                    b.putBoolean(key, value)
                }
            }
            mBinding.tvApplyBunny.text = requireContext().getString(R.string.apply)
            mBinding.tvApplyOval.text = requireContext().getString(R.string.applied)
            intent.putExtra("dynamic_style", b)
            requireContext().sendBroadcast(intent)
            SharePreferenceUtil(requireContext()).setBooleanValue(AppConst.DYNAMIC_STYLE_OVAL, true)
            initApplyOval()
        }

        mBinding.rltBunny.click {
            sharedPreferences?.edit()?.putBoolean("oval_notification", false)?.apply()
            val intent = Intent(requireContext().packageName + AppConst.DYNAMIC_STYLE)
            val b = Bundle()
            sharedPreferences?.all?.forEach { (key: String?, value: Any?) ->
                if (value is Boolean) {
                    b.putBoolean(key, value)
                }
            }
            intent.putExtra("dynamic_style", b)
            requireContext().sendBroadcast(intent)
            SharePreferenceUtil(requireContext()).setBooleanValue(
                AppConst.DYNAMIC_STYLE_OVAL, false
            )
            mBinding.tvApplyBunny.text = requireContext().getString(R.string.applied)
            mBinding.tvApplyOval.text = requireContext().getString(R.string.apply)
            initApplyBunny()
        }

        val isDynamicStyle =
            SharePreferenceUtil(requireContext()).getBooleanValue(AppConst.DYNAMIC_STYLE_OVAL, true)
        if (isDynamicStyle) {
            initApplyOval()
        } else {
            initApplyBunny()
        }

        mBinding.tvResetPosition.click {
            val intent = Intent(requireContext().packageName + AppConst.RESET_POSITION)
            requireContext().sendBroadcast(intent)
            mBinding.seekbarVertical.progress = 5
            mBinding.seekbarHorizontal.progress = 0
            y = 5
            x = 0
        }

        mBinding.tvResetSize.click {
            val intent = Intent(requireContext().packageName + AppConst.RESET_SIZE)
            requireContext().sendBroadcast(intent)
            mBinding.seekbarWidth.progress = 130
            mBinding.seekbarHeight.progress = 33
            width = 130
            height = 25
        }


    }

    fun checkAccessibilityServiceActive() {
        Log.d("chinh_dep_trai_kk_enable", "checkAccessibilityServiceActive() called")
        val isNotificationEnable = PermissionService.checkNotificationEnabled(requireContext())
        val isAccessibility = Constants.checkAccessibilityEnabled(requireContext())
        //Constants.checkAccessibilityEnabled(activity)
        if (isNotificationEnable && isAccessibility && prefs.getBoolean(
                AppConstants.keyCheckAccessibility, false
            ) && prefs.getBoolean(AppConstants.keyCheckNotification, false) && SharePreferenceUtil(
                requireContext()
            ).getBooleanValue(
                AppConst.DYNAMIC_TURN_ON, false
            )
        ) {
            mBinding.switchDisplayNotch.isChecked = true
            Log.d("chinh_dep_trai_kk_enable_true", "checkAccessibilityServiceActive() called")
            mBinding.viewFake.isVisible = context?.let {
                SharePreferenceUtil(it).getBooleanValue(
                    AppConst.DYNAMIC_TURN_ON, false
                )
            } == false
            SharePreferenceUtil(requireContext()).setBooleanValue(
                AppConst.DYNAMIC_TURN_ON, true
            )


        } else {
            SharePreferenceUtil(requireContext()).setBooleanValue(
                AppConst.DYNAMIC_TURN_ON, false
            )
            mBinding.viewFake.isVisible = true
            Log.d("chinh_dep_trai_kk_enable_false", "checkAccessibilityServiceActive() called")
            mBinding.switchDisplayNotch.isChecked = false
        }

    }

    private fun initApplyOval() {
        mBinding.rltOval.setBackgroundResource(R.drawable.bg_text_apply_active)
        mBinding.rltBunny.setBackgroundResource(R.drawable.bg_text_apply_inactive)
        mBinding.imgTick.isVisible = true
        mBinding.imgTickBunny.isVisible = false
        mBinding.tvApplyBunny.text = requireContext().getString(R.string.apply)
        mBinding.tvApplyOval.text = requireContext().getString(R.string.applied)
        mBinding.tvApplyBunny.setTextColor(this.resources.getColor(R.color.color_00A03A))
        mBinding.tvApplyOval.setTextColor(this.resources.getColor(R.color.white))
    }

    private fun initApplyBunny() {
        mBinding.rltOval.setBackgroundResource(R.drawable.bg_text_apply_inactive)
        mBinding.rltBunny.setBackgroundResource(R.drawable.bg_text_apply_active)
        mBinding.imgTick.isVisible = false
        mBinding.tvApplyBunny.text = requireContext().getString(R.string.applied)
        mBinding.tvApplyOval.text = requireContext().getString(R.string.apply)
        mBinding.tvApplyBunny.setTextColor(this.resources.getColor(R.color.white))
        mBinding.tvApplyOval.setTextColor(this.resources.getColor(R.color.color_00A03A))
        mBinding.imgTickBunny.isVisible = true
    }


    val resultLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            if (it.resultCode == Activity.RESULT_OK) {
//                checkAccessibilityServiceActive()
                (activity as MainActivity).mBinding.constraintTutor.goneView()

                val isNotificationEnable =
                    PermissionService.checkNotificationEnabled(requireContext())
                val isAccessibility = Constants.checkAccessibilityEnabled(requireContext())
                Log.d("chinh_dep_trai_kk_result", ": onresult ")
                mBinding.switchDisplayNotch.isChecked =
                    isNotificationEnable && isAccessibility && prefs.getBoolean(
                        AppConstants.keyCheckAccessibility,
                        false
                    ) && prefs.getBoolean(AppConstants.keyCheckNotification, false)

                lifecycleScope.launch(Dispatchers.IO) {
                    delay(2000)
                    withContext(Dispatchers.Main) {
                        GlobalApp.isback = true
                    }
                }
//                Handler().postDelayed({
//
//                }, 2000)
            }
        }


    override fun onClickViews() {
        super.onClickViews()

        mBinding.imvHomeSettings.setOnClickListener {
            activity?.let { it1 -> Routes.startSettingActivity(it1, resultLauncher) }
        }
    }

    private fun onChange() {
        lifecycleScope.launch {
            delay(100L)
            if (width == 0) {
                width = 130
            }
            if (height == 0) {
                height = 25
            }
            if (x == 0) {
                x = 0
            }
            if (y == 0) {
                y = 5
            }
            sharedPreferences?.edit()?.putInt("overlay_w", width)?.apply()
            sharedPreferences?.edit()?.putInt("overlay_h", height)?.apply()
            sharedPreferences?.edit()?.putInt("overlay_x", x)?.apply()
            sharedPreferences?.edit()?.putInt("overlay_y", y)?.apply()
            SharePreferenceUtil(requireContext()).setIntValue(AppConst.OVERLAY_WIDTH, width)
            SharePreferenceUtil(requireContext()).setIntValue(AppConst.OVERLAY_HEIGHT, height)
            SharePreferenceUtil(requireContext()).setIntValue(AppConst.OVERLAY_X, x)
            SharePreferenceUtil(requireContext()).setIntValue(AppConst.OVERLAY_Y, y)
            val intent = Intent(requireContext().packageName + AppConst.OVERLAY_LAYOUT_CHANGE)
            val b = Bundle()
            sharedPreferences?.all?.forEach { (key: String?, value: Any?) ->
                if (value is Int) {
                    b.putInt(key, value)
                }
            }
            intent.putExtra("settings", b)
            requireContext().sendBroadcast(intent)
        }
    }
}