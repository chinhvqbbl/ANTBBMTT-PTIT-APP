package com.tools.edge.dynamic.island;

import android.util.SparseArray;
import android.util.SparseIntArray;
import android.view.View;
import androidx.databinding.DataBinderMapper;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import com.tools.edge.dynamic.island.databinding.ActivityLanguageBindingImpl;
import com.tools.edge.dynamic.island.databinding.ActivityMainBindingImpl;
import com.tools.edge.dynamic.island.databinding.ActivityModulDetailsBindingImpl;
import com.tools.edge.dynamic.island.databinding.ActivityOnboardingBindingImpl;
import com.tools.edge.dynamic.island.databinding.ActivityPermissionBindingImpl;
import com.tools.edge.dynamic.island.databinding.ActivitySelectAppBindingImpl;
import com.tools.edge.dynamic.island.databinding.ActivitySettingBindingImpl;
import com.tools.edge.dynamic.island.databinding.ActivitySplashBindingImpl;
import com.tools.edge.dynamic.island.databinding.AdsShimmerNativeLargeBindingImpl;
import com.tools.edge.dynamic.island.databinding.AdsShimmerNativePermissionBindingImpl;
import com.tools.edge.dynamic.island.databinding.DialogExitBindingImpl;
import com.tools.edge.dynamic.island.databinding.DialogKeepNotchVisibleBindingImpl;
import com.tools.edge.dynamic.island.databinding.DialogNotchClickBindingImpl;
import com.tools.edge.dynamic.island.databinding.DialogNotificationBindingImpl;
import com.tools.edge.dynamic.island.databinding.DialogPermissionRequiredBindingImpl;
import com.tools.edge.dynamic.island.databinding.DialogRateAppBindingImpl;
import com.tools.edge.dynamic.island.databinding.FragmentConfigsBindingImpl;
import com.tools.edge.dynamic.island.databinding.FragmentHomeBindingImpl;
import com.tools.edge.dynamic.island.databinding.FragmentModuleBindingImpl;
import com.tools.edge.dynamic.island.databinding.ItemAppSelectedBindingImpl;
import com.tools.edge.dynamic.island.databinding.ItemLanguageBindingImpl;
import com.tools.edge.dynamic.island.databinding.ItemModulesBindingImpl;
import com.tools.edge.dynamic.island.databinding.ItemOnBoardingBindingImpl;
import com.tools.edge.dynamic.island.databinding.ItemVisibilityBindingImpl;
import com.tools.edge.dynamic.island.databinding.NotificationIconItemBindingImpl;
import com.tools.edge.dynamic.island.databinding.ShimmerAdsSmallBindingImpl;
import java.lang.IllegalArgumentException;
import java.lang.Integer;
import java.lang.Object;
import java.lang.Override;
import java.lang.RuntimeException;
import java.lang.String;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DataBinderMapperImpl extends DataBinderMapper {
  private static final int LAYOUT_ACTIVITYLANGUAGE = 1;

  private static final int LAYOUT_ACTIVITYMAIN = 2;

  private static final int LAYOUT_ACTIVITYMODULDETAILS = 3;

  private static final int LAYOUT_ACTIVITYONBOARDING = 4;

  private static final int LAYOUT_ACTIVITYPERMISSION = 5;

  private static final int LAYOUT_ACTIVITYSELECTAPP = 6;

  private static final int LAYOUT_ACTIVITYSETTING = 7;

  private static final int LAYOUT_ACTIVITYSPLASH = 8;

  private static final int LAYOUT_ADSSHIMMERNATIVELARGE = 9;

  private static final int LAYOUT_ADSSHIMMERNATIVEPERMISSION = 10;

  private static final int LAYOUT_DIALOGEXIT = 11;

  private static final int LAYOUT_DIALOGKEEPNOTCHVISIBLE = 12;

  private static final int LAYOUT_DIALOGNOTCHCLICK = 13;

  private static final int LAYOUT_DIALOGNOTIFICATION = 14;

  private static final int LAYOUT_DIALOGPERMISSIONREQUIRED = 15;

  private static final int LAYOUT_DIALOGRATEAPP = 16;

  private static final int LAYOUT_FRAGMENTCONFIGS = 17;

  private static final int LAYOUT_FRAGMENTHOME = 18;

  private static final int LAYOUT_FRAGMENTMODULE = 19;

  private static final int LAYOUT_ITEMAPPSELECTED = 20;

  private static final int LAYOUT_ITEMLANGUAGE = 21;

  private static final int LAYOUT_ITEMMODULES = 22;

  private static final int LAYOUT_ITEMONBOARDING = 23;

  private static final int LAYOUT_ITEMVISIBILITY = 24;

  private static final int LAYOUT_NOTIFICATIONICONITEM = 25;

  private static final int LAYOUT_SHIMMERADSSMALL = 26;

  private static final SparseIntArray INTERNAL_LAYOUT_ID_LOOKUP = new SparseIntArray(26);

  static {
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.tools.edge.dynamic.island.R.layout.activity_language, LAYOUT_ACTIVITYLANGUAGE);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.tools.edge.dynamic.island.R.layout.activity_main, LAYOUT_ACTIVITYMAIN);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.tools.edge.dynamic.island.R.layout.activity_modul_details, LAYOUT_ACTIVITYMODULDETAILS);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.tools.edge.dynamic.island.R.layout.activity_onboarding, LAYOUT_ACTIVITYONBOARDING);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.tools.edge.dynamic.island.R.layout.activity_permission, LAYOUT_ACTIVITYPERMISSION);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.tools.edge.dynamic.island.R.layout.activity_select_app, LAYOUT_ACTIVITYSELECTAPP);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.tools.edge.dynamic.island.R.layout.activity_setting, LAYOUT_ACTIVITYSETTING);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.tools.edge.dynamic.island.R.layout.activity_splash, LAYOUT_ACTIVITYSPLASH);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.tools.edge.dynamic.island.R.layout.ads_shimmer_native_large, LAYOUT_ADSSHIMMERNATIVELARGE);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.tools.edge.dynamic.island.R.layout.ads_shimmer_native_permission, LAYOUT_ADSSHIMMERNATIVEPERMISSION);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.tools.edge.dynamic.island.R.layout.dialog_exit, LAYOUT_DIALOGEXIT);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.tools.edge.dynamic.island.R.layout.dialog_keep_notch_visible, LAYOUT_DIALOGKEEPNOTCHVISIBLE);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.tools.edge.dynamic.island.R.layout.dialog_notch_click, LAYOUT_DIALOGNOTCHCLICK);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.tools.edge.dynamic.island.R.layout.dialog_notification, LAYOUT_DIALOGNOTIFICATION);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.tools.edge.dynamic.island.R.layout.dialog_permission_required, LAYOUT_DIALOGPERMISSIONREQUIRED);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.tools.edge.dynamic.island.R.layout.dialog_rate_app, LAYOUT_DIALOGRATEAPP);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.tools.edge.dynamic.island.R.layout.fragment_configs, LAYOUT_FRAGMENTCONFIGS);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.tools.edge.dynamic.island.R.layout.fragment_home, LAYOUT_FRAGMENTHOME);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.tools.edge.dynamic.island.R.layout.fragment_module, LAYOUT_FRAGMENTMODULE);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.tools.edge.dynamic.island.R.layout.item_app_selected, LAYOUT_ITEMAPPSELECTED);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.tools.edge.dynamic.island.R.layout.item_language, LAYOUT_ITEMLANGUAGE);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.tools.edge.dynamic.island.R.layout.item_modules, LAYOUT_ITEMMODULES);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.tools.edge.dynamic.island.R.layout.item_on_boarding, LAYOUT_ITEMONBOARDING);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.tools.edge.dynamic.island.R.layout.item_visibility, LAYOUT_ITEMVISIBILITY);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.tools.edge.dynamic.island.R.layout.notification_icon_item, LAYOUT_NOTIFICATIONICONITEM);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.tools.edge.dynamic.island.R.layout.shimmer_ads_small, LAYOUT_SHIMMERADSSMALL);
  }

  @Override
  public ViewDataBinding getDataBinder(DataBindingComponent component, View view, int layoutId) {
    int localizedLayoutId = INTERNAL_LAYOUT_ID_LOOKUP.get(layoutId);
    if(localizedLayoutId > 0) {
      final Object tag = view.getTag();
      if(tag == null) {
        throw new RuntimeException("view must have a tag");
      }
      switch(localizedLayoutId) {
        case  LAYOUT_ACTIVITYLANGUAGE: {
          if ("layout/activity_language_0".equals(tag)) {
            return new ActivityLanguageBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for activity_language is invalid. Received: " + tag);
        }
        case  LAYOUT_ACTIVITYMAIN: {
          if ("layout/activity_main_0".equals(tag)) {
            return new ActivityMainBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for activity_main is invalid. Received: " + tag);
        }
        case  LAYOUT_ACTIVITYMODULDETAILS: {
          if ("layout/activity_modul_details_0".equals(tag)) {
            return new ActivityModulDetailsBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for activity_modul_details is invalid. Received: " + tag);
        }
        case  LAYOUT_ACTIVITYONBOARDING: {
          if ("layout/activity_onboarding_0".equals(tag)) {
            return new ActivityOnboardingBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for activity_onboarding is invalid. Received: " + tag);
        }
        case  LAYOUT_ACTIVITYPERMISSION: {
          if ("layout/activity_permission_0".equals(tag)) {
            return new ActivityPermissionBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for activity_permission is invalid. Received: " + tag);
        }
        case  LAYOUT_ACTIVITYSELECTAPP: {
          if ("layout/activity_select_app_0".equals(tag)) {
            return new ActivitySelectAppBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for activity_select_app is invalid. Received: " + tag);
        }
        case  LAYOUT_ACTIVITYSETTING: {
          if ("layout/activity_setting_0".equals(tag)) {
            return new ActivitySettingBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for activity_setting is invalid. Received: " + tag);
        }
        case  LAYOUT_ACTIVITYSPLASH: {
          if ("layout/activity_splash_0".equals(tag)) {
            return new ActivitySplashBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for activity_splash is invalid. Received: " + tag);
        }
        case  LAYOUT_ADSSHIMMERNATIVELARGE: {
          if ("layout/ads_shimmer_native_large_0".equals(tag)) {
            return new AdsShimmerNativeLargeBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for ads_shimmer_native_large is invalid. Received: " + tag);
        }
        case  LAYOUT_ADSSHIMMERNATIVEPERMISSION: {
          if ("layout/ads_shimmer_native_permission_0".equals(tag)) {
            return new AdsShimmerNativePermissionBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for ads_shimmer_native_permission is invalid. Received: " + tag);
        }
        case  LAYOUT_DIALOGEXIT: {
          if ("layout/dialog_exit_0".equals(tag)) {
            return new DialogExitBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for dialog_exit is invalid. Received: " + tag);
        }
        case  LAYOUT_DIALOGKEEPNOTCHVISIBLE: {
          if ("layout/dialog_keep_notch_visible_0".equals(tag)) {
            return new DialogKeepNotchVisibleBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for dialog_keep_notch_visible is invalid. Received: " + tag);
        }
        case  LAYOUT_DIALOGNOTCHCLICK: {
          if ("layout/dialog_notch_click_0".equals(tag)) {
            return new DialogNotchClickBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for dialog_notch_click is invalid. Received: " + tag);
        }
        case  LAYOUT_DIALOGNOTIFICATION: {
          if ("layout/dialog_notification_0".equals(tag)) {
            return new DialogNotificationBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for dialog_notification is invalid. Received: " + tag);
        }
        case  LAYOUT_DIALOGPERMISSIONREQUIRED: {
          if ("layout/dialog_permission_required_0".equals(tag)) {
            return new DialogPermissionRequiredBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for dialog_permission_required is invalid. Received: " + tag);
        }
        case  LAYOUT_DIALOGRATEAPP: {
          if ("layout/dialog_rate_app_0".equals(tag)) {
            return new DialogRateAppBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for dialog_rate_app is invalid. Received: " + tag);
        }
        case  LAYOUT_FRAGMENTCONFIGS: {
          if ("layout/fragment_configs_0".equals(tag)) {
            return new FragmentConfigsBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for fragment_configs is invalid. Received: " + tag);
        }
        case  LAYOUT_FRAGMENTHOME: {
          if ("layout/fragment_home_0".equals(tag)) {
            return new FragmentHomeBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for fragment_home is invalid. Received: " + tag);
        }
        case  LAYOUT_FRAGMENTMODULE: {
          if ("layout/fragment_module_0".equals(tag)) {
            return new FragmentModuleBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for fragment_module is invalid. Received: " + tag);
        }
        case  LAYOUT_ITEMAPPSELECTED: {
          if ("layout/item_app_selected_0".equals(tag)) {
            return new ItemAppSelectedBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for item_app_selected is invalid. Received: " + tag);
        }
        case  LAYOUT_ITEMLANGUAGE: {
          if ("layout/item_language_0".equals(tag)) {
            return new ItemLanguageBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for item_language is invalid. Received: " + tag);
        }
        case  LAYOUT_ITEMMODULES: {
          if ("layout/item_modules_0".equals(tag)) {
            return new ItemModulesBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for item_modules is invalid. Received: " + tag);
        }
        case  LAYOUT_ITEMONBOARDING: {
          if ("layout/item_on_boarding_0".equals(tag)) {
            return new ItemOnBoardingBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for item_on_boarding is invalid. Received: " + tag);
        }
        case  LAYOUT_ITEMVISIBILITY: {
          if ("layout/item_visibility_0".equals(tag)) {
            return new ItemVisibilityBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for item_visibility is invalid. Received: " + tag);
        }
        case  LAYOUT_NOTIFICATIONICONITEM: {
          if ("layout/notification_icon_item_0".equals(tag)) {
            return new NotificationIconItemBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for notification_icon_item is invalid. Received: " + tag);
        }
        case  LAYOUT_SHIMMERADSSMALL: {
          if ("layout/shimmer_ads_small_0".equals(tag)) {
            return new ShimmerAdsSmallBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for shimmer_ads_small is invalid. Received: " + tag);
        }
      }
    }
    return null;
  }

  @Override
  public ViewDataBinding getDataBinder(DataBindingComponent component, View[] views, int layoutId) {
    if(views == null || views.length == 0) {
      return null;
    }
    int localizedLayoutId = INTERNAL_LAYOUT_ID_LOOKUP.get(layoutId);
    if(localizedLayoutId > 0) {
      final Object tag = views[0].getTag();
      if(tag == null) {
        throw new RuntimeException("view must have a tag");
      }
      switch(localizedLayoutId) {
      }
    }
    return null;
  }

  @Override
  public int getLayoutId(String tag) {
    if (tag == null) {
      return 0;
    }
    Integer tmpVal = InnerLayoutIdLookup.sKeys.get(tag);
    return tmpVal == null ? 0 : tmpVal;
  }

  @Override
  public String convertBrIdToString(int localId) {
    String tmpVal = InnerBrLookup.sKeys.get(localId);
    return tmpVal;
  }

  @Override
  public List<DataBinderMapper> collectDependencies() {
    ArrayList<DataBinderMapper> result = new ArrayList<DataBinderMapper>(1);
    result.add(new androidx.databinding.library.baseAdapters.DataBinderMapperImpl());
    return result;
  }

  private static class InnerBrLookup {
    static final SparseArray<String> sKeys = new SparseArray<String>(1);

    static {
      sKeys.put(0, "_all");
    }
  }

  private static class InnerLayoutIdLookup {
    static final HashMap<String, Integer> sKeys = new HashMap<String, Integer>(26);

    static {
      sKeys.put("layout/activity_language_0", com.tools.edge.dynamic.island.R.layout.activity_language);
      sKeys.put("layout/activity_main_0", com.tools.edge.dynamic.island.R.layout.activity_main);
      sKeys.put("layout/activity_modul_details_0", com.tools.edge.dynamic.island.R.layout.activity_modul_details);
      sKeys.put("layout/activity_onboarding_0", com.tools.edge.dynamic.island.R.layout.activity_onboarding);
      sKeys.put("layout/activity_permission_0", com.tools.edge.dynamic.island.R.layout.activity_permission);
      sKeys.put("layout/activity_select_app_0", com.tools.edge.dynamic.island.R.layout.activity_select_app);
      sKeys.put("layout/activity_setting_0", com.tools.edge.dynamic.island.R.layout.activity_setting);
      sKeys.put("layout/activity_splash_0", com.tools.edge.dynamic.island.R.layout.activity_splash);
      sKeys.put("layout/ads_shimmer_native_large_0", com.tools.edge.dynamic.island.R.layout.ads_shimmer_native_large);
      sKeys.put("layout/ads_shimmer_native_permission_0", com.tools.edge.dynamic.island.R.layout.ads_shimmer_native_permission);
      sKeys.put("layout/dialog_exit_0", com.tools.edge.dynamic.island.R.layout.dialog_exit);
      sKeys.put("layout/dialog_keep_notch_visible_0", com.tools.edge.dynamic.island.R.layout.dialog_keep_notch_visible);
      sKeys.put("layout/dialog_notch_click_0", com.tools.edge.dynamic.island.R.layout.dialog_notch_click);
      sKeys.put("layout/dialog_notification_0", com.tools.edge.dynamic.island.R.layout.dialog_notification);
      sKeys.put("layout/dialog_permission_required_0", com.tools.edge.dynamic.island.R.layout.dialog_permission_required);
      sKeys.put("layout/dialog_rate_app_0", com.tools.edge.dynamic.island.R.layout.dialog_rate_app);
      sKeys.put("layout/fragment_configs_0", com.tools.edge.dynamic.island.R.layout.fragment_configs);
      sKeys.put("layout/fragment_home_0", com.tools.edge.dynamic.island.R.layout.fragment_home);
      sKeys.put("layout/fragment_module_0", com.tools.edge.dynamic.island.R.layout.fragment_module);
      sKeys.put("layout/item_app_selected_0", com.tools.edge.dynamic.island.R.layout.item_app_selected);
      sKeys.put("layout/item_language_0", com.tools.edge.dynamic.island.R.layout.item_language);
      sKeys.put("layout/item_modules_0", com.tools.edge.dynamic.island.R.layout.item_modules);
      sKeys.put("layout/item_on_boarding_0", com.tools.edge.dynamic.island.R.layout.item_on_boarding);
      sKeys.put("layout/item_visibility_0", com.tools.edge.dynamic.island.R.layout.item_visibility);
      sKeys.put("layout/notification_icon_item_0", com.tools.edge.dynamic.island.R.layout.notification_icon_item);
      sKeys.put("layout/shimmer_ads_small_0", com.tools.edge.dynamic.island.R.layout.shimmer_ads_small);
    }
  }
}
