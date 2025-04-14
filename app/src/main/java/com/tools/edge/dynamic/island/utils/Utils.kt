package com.tools.edge.dynamic.island.utils

import android.animation.ValueAnimator
import android.app.ActivityOptions
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.PowerManager
import android.view.View
import android.view.animation.AccelerateDecelerateInterpolator
import android.view.animation.Interpolator
import com.tools.edge.dynamic.island.R
import com.tools.edge.dynamic.island.ui.component.service.ITGAccessibilityService

object Utils {

    fun isSubstringPresent(originalString: String, substringToFind: String): Boolean {
        return originalString.contains(substringToFind)
    }

    fun isIgnoringBatteryOptimizations(context: Context): Boolean {
        val powerManager =
            context.applicationContext.getSystemService(Context.POWER_SERVICE) as PowerManager
        val name = context.applicationContext.packageName
        return powerManager.isIgnoringBatteryOptimizations(name)
    }

    fun showIntentAccessibilityService(context: Context) {
        var intent = Intent("com.samsung.accessibility.installed_service")
        if (intent.resolveActivity(context.packageManager) == null) {
            intent = Intent("android.settings.ACCESSIBILITY_SETTINGS")
            intent.addFlags(1342177280);
        }
        val bundle = Bundle()
        val str: String =
            context.packageName + "/" + com.tools.edge.dynamic.island.ui.component.service.ITGAccessibilityService::class.java.getName()
        bundle.putString(":settings:fragment_args_key", str)
        intent.putExtra(":settings:fragment_args_key", str)
        intent.putExtra(":settings:show_fragment_args", bundle)
        startPermissionActivity(context, intent)
    }

    private fun startPermissionActivity(context: Context, intent: Intent?) {
        context.startActivity(
            intent,
            ActivityOptions.makeCustomAnimation(context, R.anim.fade_in, R.anim.fade_out).toBundle()
        )
    }

    fun animateHorizontalShake(
        view: View,
        offset: Float,
        repeatCount: Int = 3,
        dampingRatio: Float? = null,
        duration: Long = 1000L,
        interpolator: Interpolator = AccelerateDecelerateInterpolator()
    ) {
        val defaultDampingRatio = dampingRatio ?: (1f / (repeatCount + 1))
        val animValues = mutableListOf<Float>()
        repeat(repeatCount) { index ->
            animValues.add(0f)
            animValues.add(-offset * (1 - defaultDampingRatio * index))
            animValues.add(0f)
            animValues.add(offset * (1 - defaultDampingRatio * index))
        }
        animValues.add(0f)

        val anim: ValueAnimator = ValueAnimator.ofFloat(*animValues.toFloatArray())
        anim.addUpdateListener {
            view.translationX = it.animatedValue as Float
        }
        anim.interpolator = interpolator
        anim.duration = duration
        anim.start()
    }
}