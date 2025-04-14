package com.tools.edge.dynamic.island.ui.component.service;

public final  class MAccessibilityServiceRunable implements Runnable {
    public final  ITGAccessibilityService mAccessibilityService;

    public MAccessibilityServiceRunable(ITGAccessibilityService mAccessibilityService) {
        this.mAccessibilityService = mAccessibilityService;
    }

    public final void run() {
        this.mAccessibilityService.run();
    }
}
