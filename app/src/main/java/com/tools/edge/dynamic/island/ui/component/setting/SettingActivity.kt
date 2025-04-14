package com.tools.edge.dynamic.island.ui.component.setting

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.databinding.DataBindingUtil.setContentView
import com.bumptech.glide.load.resource.bitmap.Rotate
import com.tools.edge.dynamic.island.R
import com.tools.edge.dynamic.island.ads.RemoteConfigUtils
import com.tools.edge.dynamic.island.app.AppConstants
import com.tools.edge.dynamic.island.databinding.ActivitySettingBinding
import com.tools.edge.dynamic.island.ui.bases.BaseActivity
import com.tools.edge.dynamic.island.ui.bases.ext.click
import com.tools.edge.dynamic.island.ui.bases.ext.showRateDialog
import com.tools.edge.dynamic.island.ui.component.language.LanguageActivity
import com.tools.edge.dynamic.island.utils.EasyPreferences.get
import com.tools.edge.dynamic.island.utils.Routes

class SettingActivity : BaseActivity<ActivitySettingBinding>() {

    override fun getLayoutActivity(): Int {
        return R.layout.activity_setting
    }


    override fun onClickViews() {
        super.onClickViews()

        mBinding.layoutLanguage.click {
            Routes.startLanguageActivity(this, null)
        }


        mBinding.layoutRate.click {

            if (RemoteConfigUtils.getIsShowRate()) {
                if (prefs[AppConstants.KEY_SET_SHOW_DIALOD_RATE, false] == false) {
                    showRateDialog(this@SettingActivity, false)
                } else {
                    Toast.makeText(
                        this@SettingActivity,
                        getString(R.string.txt_thanks_you_for_rating),
                        Toast.LENGTH_SHORT
                    ).show()
                }
            } else {
                Toast.makeText(
                    this@SettingActivity,
                    getString(R.string.txt_thanks_you_for_rating),
                    Toast.LENGTH_SHORT
                ).show()
            }
        }



        mBinding.layoutMoreApp.click {

            try {
                startActivity(
                    Intent(
                        Intent.ACTION_VIEW, Uri.parse("market://search?q=pub:JP+Software+Studio")
                    )
                )
            } catch (anfe: ActivityNotFoundException) {
                startActivity(
                    Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse("https://play.google.com/store/apps/developer?id=JP+Software+Studio")
                    )
                )
            }
        }


        mBinding.layoutShare.click {
            shareApp()
        }


        mBinding.imvBack.click {
            onBackPressedDispatcher.onBackPressed()
        }

        onBackPressedDispatcher.addCallback(object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                setResult(RESULT_OK)
                finish()
            }

        })

    }


}