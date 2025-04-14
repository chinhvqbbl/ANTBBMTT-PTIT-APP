package com.tools.edge.dynamic.island.ui.component.app.select

import android.annotation.SuppressLint
import android.content.pm.ApplicationInfo
import android.util.Log
import android.widget.CheckBox
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.lifecycleScope
import com.tools.edge.dynamic.island.R
import com.tools.edge.dynamic.island.databinding.ActivitySelectAppBinding
import com.tools.edge.dynamic.island.ui.bases.BaseActivity
import com.tools.edge.dynamic.island.ui.bases.ext.goneView
import com.tools.edge.dynamic.island.ui.bases.ext.visibleView
import com.tools.edge.dynamic.island.ui.component.app.select.adapter.SelectAppAdapter
import com.tools.edge.dynamic.island.ui.component.service.ITGAccessibilityService
import com.tools.edge.dynamic.island.ui.component.utils.Constants
import com.tools.edge.dynamic.island.utils.AppManager
import com.tools.edge.dynamic.island.utils.Utils
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

private const val TAG = "SelectAppActivity"

class SelectAppActivity : BaseActivity<ActivitySelectAppBinding>() {
    private var selectAppAdapter: SelectAppAdapter? = null
    private var listApps: MutableLiveData<HashSet<com.tools.edge.dynamic.island.ui.component.entity.AppDetail>> = MutableLiveData(hashSetOf())
    private var prefManager: com.tools.edge.dynamic.island.ui.component.background.PrefManager? = null

    override fun getLayoutActivity(): Int {
        return R.layout.activity_select_app
    }


    override fun initViews() {
        super.initViews()
        prefManager = com.tools.edge.dynamic.island.ui.component.background.PrefManager(this)
        initAdapter()
        lifecycleScope.launch(Dispatchers.IO) {
            loadData()
        }
        checkSelectAll()
    }

    private fun checkSelectAll() {
        val listAppDisable = prefManager?.listDisable
        mBinding.ckbSelectAllApps.isChecked = listAppDisable?.trim() == ""
    }

    @SuppressLint("SuspiciousIndentation")
    private fun loadData() {
        mBinding.layoutLoading.visibleView()
        val list = prefManager?.listDisable
        val apps = hashSetOf<com.tools.edge.dynamic.island.ui.component.entity.AppDetail>()
        AppManager.getInstalledApps(this).forEach { app ->
            val appDetail = com.tools.edge.dynamic.island.ui.component.entity.AppDetail()
            appDetail.label = app.loadLabel(packageManager).toString()
            appDetail.pkg = app.packageName
            appDetail.activityInfoName = app.name
            appDetail.isSorted = false
            appDetail.isSelected = !Utils.isSubstringPresent(list ?: "", appDetail.pkg)
            if (app.flags and ApplicationInfo.FLAG_SYSTEM == 0 && app.packageName != packageName) {
                apps.add(appDetail)
            }
        }
        listApps.postValue(apps)
    }


    private fun initAdapter() {
        selectAppAdapter = SelectAppAdapter(onClickItem = { appDetail, isShowNotify ->

            if (!isShowNotify) {
                mBinding.ckbSelectAllApps.isChecked = false
                prefManager?.listDisable = prefManager?.listDisable + " ${appDetail.pkg}"
            } else {

                prefManager?.listDisable =
                    prefManager?.listDisable?.replace("${appDetail.pkg}", "")?.trim()

            }

            appDetail.isSelected = isShowNotify
            val newList = listApps.value
            newList?.let { it.add(appDetail) }
            listApps.postValue(newList!!)
            checkSelectAll()
            Log.d(
                TAG,
                "listApps disable + ${prefManager?.listDisable}"
            )
            if (com.tools.edge.dynamic.island.ui.component.utils.Utils.isServiceRunning(this, ITGAccessibilityService::class.java.name)){
                ITGAccessibilityService.getInstance().initNotification()
            }
        }, prefManager?.listDisable ?: "")
        mBinding.rcvAppSelected.adapter = selectAppAdapter
        mBinding.rcvAppSelected.setHasFixedSize(true)
    }

    override fun observerData() {
        super.observerData()
        listApps.observe(this) {

            if (it.isNotEmpty()) {
                selectAppAdapter?.submitData(it.toMutableList().sortedBy { it.label })
                mBinding.layoutLoading.goneView()
            }
        }
    }


    override fun onClickViews() {
        super.onClickViews()
        mBinding.ckbSelectAllApps.setOnClickListener {
            if (it is CheckBox) {
                prefManager?.listDisable = ""
                if (listApps.value?.isNotEmpty() == true) {
                    loadData()
                }

            }
        }

        mBinding.imvBack.setOnClickListener {
            setResult(RESULT_OK)
            finish()
        }
    }
}