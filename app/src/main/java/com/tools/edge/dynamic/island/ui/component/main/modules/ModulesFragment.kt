package com.tools.edge.dynamic.island.ui.component.main.modules

import android.Manifest
import android.app.Activity
import android.content.ActivityNotFoundException
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.core.app.ActivityCompat.shouldShowRequestPermissionRationale
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.startActivity
import androidx.fragment.app.activityViewModels
import com.bbl.module_ads.admob.AppOpenManager

import com.tools.edge.dynamic.island.R
import com.tools.edge.dynamic.island.app.GlobalApp
import com.tools.edge.dynamic.island.databinding.FragmentModuleBinding
import com.tools.edge.dynamic.island.ui.bases.BaseFragment
import com.tools.edge.dynamic.island.ui.component.main.MainActivity
import com.tools.edge.dynamic.island.ui.component.main.modules.adapter.ModulesAdapter
import com.tools.edge.dynamic.island.ui.component.main.modules.viewmodel.ModulesViewModel
import com.tools.edge.dynamic.island.ui.component.model.ModulesModel
import com.tools.edge.dynamic.island.ui.component.model.ModulesType
import com.tools.edge.dynamic.island.ui.component.moduldetails.PermissionRequiredDialog
import com.tools.edge.dynamic.island.ui.component.onboarding.OnBoardingActivity
import com.tools.edge.dynamic.island.ui.component.utils.Constants
import com.tools.edge.dynamic.island.utils.Routes

class ModulesFragment : BaseFragment<FragmentModuleBinding>() {
    private val modulesViewModel: ModulesViewModel by activityViewModels()
    private var modulesAdapter: ModulesAdapter? = null
    private val TAG = "ModulesFragment"
    private var listModules = mutableListOf<ModulesModel>()
    val launcher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
        if (it.resultCode == Activity.RESULT_OK) {
            GlobalApp.isShowAds = true
//            (activity as MainActivity).showNativeAds(true)
        }
    }

    var dialogPermissionRequiredCall: PermissionRequiredDialog? = null


    private val requestReadPhoneState =
        registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { permission ->
            if (permission != null && permission.isNotEmpty()) {
                activity?.let {
                    //permission[Manifest.permission.READ_CONTACTS] == true &&
                    if (permission[Manifest.permission.READ_PHONE_STATE] == true) {

                        Routes.startModulDetailsActivity(it, Bundle().apply {
                            putString(Constants.KEY_MODULES, ModulesType.CALLS.name)
                        }, launcher)

                    } else {
                        AppOpenManager.getInstance()
                            .disableAppResumeWithActivity(MainActivity::class.java)
                        dialogPermissionRequiredCall?.show()
                    }
                }

            }
        }

    companion object {
        var checkEnabledAds = 0
    }

    override fun onResume() {
        super.onResume()

        if (dialogPermissionRequiredCall?.isShowing == true) {
            activity?.let {
                if (
                    it.checkSelfPermission(Manifest.permission.READ_PHONE_STATE) == PackageManager.PERMISSION_GRANTED
                ) {

                if (checkEnabledAds >0){

                    AppOpenManager.getInstance()
                        .enableAppResumeWithActivity(MainActivity::class.java)
                }
                    checkEnabledAds++
                }

                dialogPermissionRequiredCall?.dismiss()
            }
        }
    }

    private fun startAppInfo() {
        try {
            //Open the specific App Info page:
            val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
            intent.setData(Uri.parse("package:${requireContext().packageName}"))
            startActivity(intent)
        } catch (e: ActivityNotFoundException) {
            //e.printStackTrace();

            //Open the generic Apps page:
            val intent = Intent(Settings.ACTION_MANAGE_APPLICATIONS_SETTINGS)
            startActivity(intent)
        }
    }

    override fun getLayoutFragment(): Int = R.layout.fragment_module

    override fun initViews() {
        super.initViews()
        initRcv()

        dialogPermissionRequiredCall = context?.let {
            PermissionRequiredDialog(context = it) {
                startAppInfo()
            }
        }

        dialogPermissionRequiredCall?.setOnDismissListener {
            AppOpenManager.getInstance()
                .enableAppResumeWithActivity(MainActivity::class.java)
        }

        dialogPermissionRequiredCall?.setOnShowListener {
            AppOpenManager.getInstance()
                .disableAppResumeWithActivity(MainActivity::class.java)
        }

//        dialogPermissionRequiredCall?.setOnDismissListener {
//            AppOpenManager.getInstance()
//                .enableAppResumeWithActivity(MainActivity::class.java)
//        }

    }

    private fun initRcv() {
        listModules.clear()
        listModules = modulesViewModel.initListModules(requireActivity())
        modulesAdapter = ModulesAdapter((listModules))
        mBinding.rcvModules.adapter = modulesAdapter
        modulesAdapter?.onItemModulesClickedListener = { type ->
            activity?.let {


                if (type == ModulesType.CALLS) {

                    when {
                        ContextCompat.checkSelfPermission(
                            it,
                            Manifest.permission.READ_PHONE_STATE
                        ) == PackageManager.PERMISSION_GRANTED -> {
                            Routes.startModulDetailsActivity(it, Bundle().apply {
                                putString(Constants.KEY_MODULES, type.name)
                            }, launcher)
                        }

                        shouldShowRequestPermissionRationale(Manifest.permission.READ_PHONE_STATE) -> {
                            AppOpenManager.getInstance()
                                .disableAppResumeWithActivity(MainActivity::class.java)
                            dialogPermissionRequiredCall?.show()
                        }

                        else -> {


                            requestReadPhoneState.launch(
                                arrayOf(

                                    Manifest.permission.READ_PHONE_STATE,
                                )
                            )
                        }
                    }


                } else {
                    Routes.startModulDetailsActivity(it, Bundle().apply {
                        putString(Constants.KEY_MODULES, type.name)
                    }, launcher)
                }

            }
        }
    }

    override fun onClickViews() {
        super.onClickViews()
    }
}