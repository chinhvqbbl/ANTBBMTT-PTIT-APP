package com.tools.edge.dynamic.island.utils

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.result.ActivityResultLauncher
import com.tools.edge.dynamic.island.app.AppConstants
import com.tools.edge.dynamic.island.ui.component.app.select.SelectAppActivity
import com.tools.edge.dynamic.island.ui.component.language.LanguageActivity
import com.tools.edge.dynamic.island.ui.component.main.MainActivity
import com.tools.edge.dynamic.island.ui.component.moduldetails.ModulDetailsActivity
import com.tools.edge.dynamic.island.ui.component.onboarding.OnBoardingActivity
import com.tools.edge.dynamic.island.ui.component.permision.PermissionActivity
import com.tools.edge.dynamic.island.ui.component.setting.SettingActivity
import com.tools.edge.dynamic.island.ui.component.splash.SplashActivity

object Routes {
    fun startMainActivity(fromActivity: Activity) =
        Intent(fromActivity, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            putExtra(AppConstants.KEY_TRACKING_SCREEN_FROM, fromActivity::class.java.simpleName)
            fromActivity.startActivity(this)
        }


    fun startSettingActivity(
        fromActivity: Activity, resultLauncher: ActivityResultLauncher<Intent>
    ) {
        val intent = Intent(fromActivity, SettingActivity::class.java).apply {
            putExtra(AppConstants.KEY_TRACKING_SCREEN_FROM, fromActivity::class.java.simpleName)

        }

        resultLauncher.launch(intent)
    }


    fun startPermissionActivity(
        fromActivity: Activity,
        resultLauncher: ActivityResultLauncher<Intent>,
    ) {
        val intent = Intent(fromActivity, PermissionActivity::class.java).apply {
            putExtra(AppConstants.KEY_TRACKING_SCREEN_FROM, fromActivity::class.java.simpleName)
        }
        resultLauncher.launch(intent)
    }


    fun startOnBoardingActivity(fromActivity: Activity) =
        Intent(fromActivity, OnBoardingActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            putExtra(AppConstants.KEY_TRACKING_SCREEN_FROM, fromActivity::class.java.simpleName)
            fromActivity.startActivity(this)

        }

    fun startLanguageActivity(fromActivity: Activity, bundle: Bundle?) =
        Intent(fromActivity, LanguageActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK
            putExtra(AppConstants.KEY_TRACKING_SCREEN_FROM, fromActivity::class.java.simpleName)
            bundle?.let { putExtras(it) }
            fromActivity.startActivity(this)

        }


    fun startModulDetailsActivity(
        fromActivity: Activity, bundle: Bundle?, launcher: ActivityResultLauncher<Intent>
    ) = Intent(fromActivity, ModulDetailsActivity::class.java).apply {
        putExtra(AppConstants.KEY_TRACKING_SCREEN_FROM, fromActivity::class.java.simpleName)
        bundle?.let { putExtras(it) }
        launcher.launch(this)
    }


    fun startSelectAppsActivity(
        fromActivity: Activity, bundle: Bundle?, launcher: ActivityResultLauncher<Intent>
    ) = Intent(fromActivity, SelectAppActivity::class.java).apply {
        putExtra(AppConstants.KEY_TRACKING_SCREEN_FROM, fromActivity::class.java.simpleName)
        bundle?.let { putExtras(it) }
        launcher.launch(this)
    }

    fun startSplashActivity(fromActivity: Activity) =
        Intent(fromActivity, SplashActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            putExtra(AppConstants.KEY_TRACKING_SCREEN_FROM, fromActivity::class.java.simpleName)
            fromActivity.startActivity(this)
        }


    fun addTrackingMoveScreen(fromActivity: String, toActivity: String) {
        ITGTrackingHelper.fromScreenToScreen(fromActivity, toActivity)
    }

}