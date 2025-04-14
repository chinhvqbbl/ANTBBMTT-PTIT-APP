package com.tools.edge.dynamic.island.ui.component.service;

import android.content.SharedPreferences;

public final  class MAccessibilityServiceOnSharedPreferenceChanged implements SharedPreferences.OnSharedPreferenceChangeListener {
    public final  ITGAccessibilityService mAccessibilityService;

    public MAccessibilityServiceOnSharedPreferenceChanged(ITGAccessibilityService mAccessibilityService) {
        this.mAccessibilityService = mAccessibilityService;
    }

    public final void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String str) {
        this.mAccessibilityService.onSharedPreferenceChanged(sharedPreferences, str);
    }
}
