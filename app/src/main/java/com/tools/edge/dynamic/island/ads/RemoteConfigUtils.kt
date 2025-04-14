package com.tools.edge.dynamic.island.ads

import com.google.firebase.ktx.Firebase
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.google.firebase.remoteconfig.ktx.remoteConfig
import com.google.firebase.remoteconfig.ktx.remoteConfigSettings
import com.tools.edge.dynamic.island.BuildConfig

object RemoteConfigUtils {

    private val ON_INTER_MODULES_BACK: String = "on_inter_modules_back"
    private val ON_NATIVE_EXIT: String = "on_native_exit"
    private val ON_NATIVE_MODULES: String = "on_native_modules"
    private val ON_INTER_MAIN: String = "on_inter_main"
    private const val TAG = "RemoteConfigUtils"

    private const val ON_NATIVE_INTER_SPLASH = "on_inter_splash"
    private const val ON_NATIVE_LANGUAGE = "on_native_language"
    private const val ON_NATIVE_ON_BOARDING = "on_native_on_boarding"
    private const val ON_NATIVE_PERMISSION = "on_native_permission"
    private const val ON_SHOW_DIALOG_CONSENT = "on_show_dialog_consent"
    private const val ON_NATIVE_HOME = "on_native_home"
    private const val ON_IS_SHOW_RATE = "on_is_show_rate"

    var completed = false
    private val DEFAULTS: HashMap<String, Any> = hashMapOf(
        ON_NATIVE_LANGUAGE to true,
        ON_NATIVE_ON_BOARDING to true,
        ON_NATIVE_PERMISSION to true,
        ON_NATIVE_INTER_SPLASH to true,
        ON_SHOW_DIALOG_CONSENT to true,
        ON_NATIVE_HOME to true,
        ON_INTER_MAIN to true,
        ON_NATIVE_MODULES to true,
        ON_NATIVE_EXIT to true,
        ON_IS_SHOW_RATE to true,
    )

    interface Listener {
        fun loadSuccess()
    }

    lateinit var listener: Listener
    private lateinit var remoteConfig: FirebaseRemoteConfig

    fun init(mListener: Listener) {
        listener = mListener
        remoteConfig = getFirebaseRemoteConfig()
    }

    private fun getFirebaseRemoteConfig(): FirebaseRemoteConfig {
        remoteConfig = Firebase.remoteConfig

        val configSettings = remoteConfigSettings {
            minimumFetchIntervalInSeconds = if (BuildConfig.DEBUG) {
                0
            } else {
                60 * 60
            }
        }
        remoteConfig.apply {
            setConfigSettingsAsync(configSettings)
            setDefaultsAsync(DEFAULTS)
            fetchAndActivate().addOnCompleteListener {
                listener.loadSuccess()
                completed = true
            }
        }
        return remoteConfig
    }


    //native language
    fun getOnNativeLanguage(): Boolean {
        try {
            return if (!completed) {
                false
            } else {
                remoteConfig.getBoolean(ON_NATIVE_LANGUAGE)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return false
    }

    fun getOnNativeOnBoarding(): Boolean {
        try {
            return if (!completed) {
                false
            } else {
                remoteConfig.getBoolean(ON_NATIVE_ON_BOARDING)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return false
    }

    fun getOnNativePermission(): Boolean {
        try {
            return if (!completed) {
                false
            } else {
                remoteConfig.getBoolean(ON_NATIVE_PERMISSION)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return false
    }

    fun getOnInterSplash(): Boolean {
        try {
            return if (!completed) {
                false
            } else {
                remoteConfig.getBoolean(ON_NATIVE_INTER_SPLASH)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return false
    }

    fun getOnShowDialogConsent(): Boolean {
        try {
            return if (!completed) {
                return true
            } else {
                remoteConfig.getBoolean(ON_SHOW_DIALOG_CONSENT)
            }
        } catch (e: java.lang.Exception) {
            e.printStackTrace()
        }
        return false
    }

    fun getOnNativeHome(): Boolean {
        try {
            return if (!completed) {
                false
            } else {
                remoteConfig.getBoolean(ON_NATIVE_HOME)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return false
    }

    fun getOnInterMain(): Boolean {
        try {
            return if (!completed) {
                false
            } else {
                remoteConfig.getBoolean(ON_INTER_MAIN)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return false
    }


    fun getOnInterModulesBack(): Boolean {
        try {
            return if (!completed) {
                false
            } else {
                remoteConfig.getBoolean(ON_INTER_MODULES_BACK)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return false
    }

    fun getOnNativeModules(): Boolean {
        try {
            return if (!completed) {
                false
            } else {
                remoteConfig.getBoolean(ON_NATIVE_MODULES)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return false
    }

    fun getOnNativeExit(): Boolean {
        try {
            return if (!completed) {
                false
            } else {
                remoteConfig.getBoolean(ON_NATIVE_EXIT)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return false
    }

    fun getIsShowRate(): Boolean {
        return try {
            if (!completed) {
                false
            } else {
                remoteConfig.getBoolean(
                    ON_IS_SHOW_RATE
                )
            }
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
    }


}