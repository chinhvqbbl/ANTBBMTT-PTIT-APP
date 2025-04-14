package com.tools.edge.dynamic.island.ui.bases

import android.content.Context
import android.content.SharedPreferences
import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.tools.edge.dynamic.island.R
import com.tools.edge.dynamic.island.app.AppConstants
import com.tools.edge.dynamic.island.utils.EasyPreferences
import java.util.Locale

abstract class BaseBottomSheetDialog<VB : ViewDataBinding>(
    context: Context, themeResId: Int = R.style.ThemeBottomSheetDialog
) : BottomSheetDialog(context, themeResId) {
    lateinit var mBinding: VB
    lateinit var prefs: SharedPreferences

    init {
        createContentView()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        prefs = EasyPreferences.defaultPrefs(context)
        setLocal()
        initViews()
        onResizeViews()
        onClickViews()
    }

    private fun createContentView() {
        val layoutView = getLayoutDialog()
        if (!::mBinding.isInitialized) {
            mBinding =
                DataBindingUtil.inflate(LayoutInflater.from(context), layoutView, null, false)
        }
        setContentView(mBinding.root)
    }

    abstract fun getLayoutDialog(): Int

    open fun initViews() {}

    open fun onResizeViews() {}

    open fun onClickViews() {}

    private fun setLocal() {
        val language: String? = prefs.getString(AppConstants.KEY_LANGUAGE, "")
        if (language == "") {
            val config = Configuration()
            val locale = Locale.getDefault()
            Locale.setDefault(locale)
            config.locale = locale
            context.resources.updateConfiguration(config, context.resources.displayMetrics)
        } else {
            if (language.equals("", ignoreCase = true)) return
            val locale = Locale(language)
            Locale.setDefault(locale)
            val config = Configuration()
            config.locale = locale
            context.resources.updateConfiguration(config, context.resources.displayMetrics)
        }
    }
}