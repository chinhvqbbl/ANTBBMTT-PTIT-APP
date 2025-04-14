package com.tools.edge.dynamic.island.ui.component.service

import android.app.Service
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Build
import android.os.Handler
import android.os.IBinder
import android.os.Looper
import android.provider.Settings
import android.util.Log
import com.bbl.module_ads.admob.AppOpenManager

import com.tools.edge.dynamic.island.app.AppConstants.keyCheckAccessibility
import com.tools.edge.dynamic.island.app.AppConstants.keyCheckNotification
import com.tools.edge.dynamic.island.ui.component.background.AppConst
import com.tools.edge.dynamic.island.ui.component.main.MainActivity
import com.tools.edge.dynamic.island.ui.component.onboarding.OnBoardingActivity
import com.tools.edge.dynamic.island.ui.component.utils.Constants
import com.tools.edge.dynamic.island.ui.component.utils.Utils
import com.tools.edge.dynamic.island.utils.EasyPreferences
import com.tools.edge.dynamic.island.utils.EasyPreferences.set
import com.tools.edge.dynamic.island.utils.SharePreferenceUtil


class PermissionService : Service() {

    private var handlerNotification: Handler? = null
    private var isRunningNotification = false

    private var handlerAccessibility: Handler? = null
    private var isRunningAccessibility = false
    lateinit var prefs: SharedPreferences

    private val checkRunnableNotification = object : Runnable {
        override fun run() {
            // Thực hiện các hành động kiểm tra ở đây
            if (isRunningNotification) {
                // Gọi lại để kiểm tra mỗi giây
                val isNotificationEnable = checkNotificationEnabled(this@PermissionService)
                if (isNotificationEnable) {
                    prefs[keyCheckNotification] = true
                    stopCheckingNotification()
                    AppOpenManager.getInstance()
                        .disableAppResumeWithActivity(MainActivity::class.java)
                    val intentMain = Intent(this@PermissionService, MainActivity::class.java)
                    intentMain.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
                    intentMain.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                    intentMain.putExtra(AppConst.IS_FROM_SERVICE, true)
                    Log.d("chinh_dep_trai_kk_5", "run() called")
                    startActivity(intentMain)
                    if (Constants.checkAccessibilityEnabled(this@PermissionService)) {
                        if (Utils.isServiceRunning(
                                this@PermissionService,
                                NotificationService::class.java.name
                            )
                        ) {
                           NotificationService.notificationService.initNotification()
                        }
                    }
                    stopSelf()
                }
                handlerNotification?.postDelayed(this, 1000) // 1000 milliseconds = 1 giây
            }
        }
    }

    private val checkRunnableAccessibility = object : Runnable {
        override fun run() {
            // Thực hiện các hành động kiểm tra ở đây
            if (isRunningAccessibility) {
                // Gọi lại để kiểm tra mỗi giây
                if (Constants.checkAccessibilityEnabled(this@PermissionService)) {
                    prefs[keyCheckAccessibility] = true
                    stopCheckingAccessibility()
                    AppOpenManager.getInstance()
                        .disableAppResumeWithActivity(MainActivity::class.java)
                    Log.e("TAG", "checkRunnable ")
                    val dialogIntent = Intent(this@PermissionService, MainActivity::class.java)

                    dialogIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
                    dialogIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                    dialogIntent.putExtra(AppConst.IS_FROM_SERVICE, true)
                    Log.d("chinh_dep_trai_kk_6", "run() called")
                    startActivity(dialogIntent)
                    if (checkNotificationEnabled(this@PermissionService)) {
                        if (Utils.isServiceRunning(
                                this@PermissionService,
                                NotificationService::class.java.name
                            )
                        ) {
                            NotificationService.notificationService.initNotification()
                        }
                    }
                    stopSelf()
                }
                handlerAccessibility?.postDelayed(this, 1000) // 1000 milliseconds = 1 giây
            }
        }
    }

    private fun startCheckingNotification() {
        if (!isRunningNotification) {
            isRunningNotification = true
            // Bắt đầu kiểm tra mỗi giây
            handlerNotification = Handler(Looper.getMainLooper())
            handlerNotification?.post(checkRunnableNotification)
        }

    }

    private fun startCheckingAccessibility() {
        if (!isRunningAccessibility) {
            isRunningAccessibility = true
            // Bắt đầu kiểm tra mỗi giây
            handlerAccessibility = Handler(Looper.getMainLooper())
            handlerAccessibility?.post(checkRunnableAccessibility)
        }

    }

    fun stopCheckingNotification() {
        if (isRunningNotification) {
            isRunningNotification = false
            // Dừng kiểm tra
            handlerNotification?.removeCallbacks(checkRunnableNotification)
            handlerNotification = null
        }
    }

    fun stopCheckingAccessibility() {
        if (isRunningAccessibility) {
            isRunningAccessibility = false
            // Dừng kiểm tra
            handlerAccessibility?.removeCallbacks(checkRunnableAccessibility)
            handlerAccessibility = null
        }
    }

    override fun onBind(p0: Intent?): IBinder? {
        return null
    }


    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        prefs = EasyPreferences.defaultPrefs(this)
        val isNotificationEnable =
            SharePreferenceUtil(this).getBooleanValue(AppConst.IS_ENABLE_NOTIFICATION, true)
        if (isNotificationEnable) {
            Log.e("TAG", "onStartCommand isNotificationEnable: ")
            SharePreferenceUtil(this).setBooleanValue(AppConst.IS_ENABLE_NOTIFICATION, false)

                startCheckingNotification()

        }
        val isAccessibilityEnable =
            SharePreferenceUtil(this).getBooleanValue(AppConst.IS_ACCESSIBILITY_NOTIFICATION, true)
        if (isAccessibilityEnable) {
            Log.e("TAG", "onStartCommand isAccessibilityEnable: ")
            SharePreferenceUtil(this).setBooleanValue(AppConst.IS_ACCESSIBILITY_NOTIFICATION, false)
            if (!Constants.checkAccessibilityEnabled(this@PermissionService)) {
                startCheckingAccessibility()
            }
        }
        Log.e("TAG", "onStartCommand 1: ")
        return START_NOT_STICKY
    }

    companion object {
        fun checkNotificationEnabled(context: Context): Boolean {
            return try {
                Settings.Secure.getString(context.contentResolver, "enabled_notification_listeners")
                    .contains(context.packageName)
            } catch (e: Exception) {
                e.printStackTrace()
                false
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
    }
}