package com.tools.edge.dynamic.island.utils

import android.content.Context
import android.content.pm.ApplicationInfo
import android.content.pm.PackageManager


object AppManager {
    fun getInstalledApps(context: Context): List<ApplicationInfo> {
        val packageManager: PackageManager = context.packageManager
        return packageManager.getInstalledApplications(PackageManager.GET_META_DATA)
    }



    fun getAllInstalledAppNames(context: Context): List<String> {
        val packageManager: PackageManager = context.getPackageManager()
        val appsList = packageManager.getInstalledApplications(PackageManager.GET_META_DATA)
        val appNames: MutableList<String> = ArrayList()
        for (appInfo in appsList) {
            val appName = packageManager.getApplicationLabel(appInfo!!).toString()
            appNames.add(appName)
        }
        return appNames
    }
}