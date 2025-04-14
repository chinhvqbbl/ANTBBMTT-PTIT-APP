package com.tools.edge.dynamic.island.ui.component.dialog

import android.app.Activity
import android.widget.Toast
import com.tools.edge.dynamic.island.R
import com.tools.edge.dynamic.island.databinding.DialogRateAppBinding
import com.tools.edge.dynamic.island.ui.bases.BaseDialog

class DialogRateApp(
    var activity: Activity,
    var onRating: () -> Unit,
    var exitApp : () ->Unit
) : BaseDialog<DialogRateAppBinding>(activity) {

    override fun getLayoutDialog(): Int = R.layout.dialog_rate_app

    override fun initViews() {
        super.initViews()
        mBinding.simpleRatingBar.rating = 5F

        mBinding.simpleRatingBar.setOnRatingChangeListener { ratingBar, rating, fromUser ->

            if (rating <= 1) {
                mBinding.ivRate.setImageResource(R.drawable.img_rate_1)
            } else if (rating <= 2) {
                mBinding.ivRate.setImageResource(R.drawable.img_rate_2)
            } else if (rating <= 3) {
                mBinding.ivRate.setImageResource(R.drawable.img_rate_3)
            } else if (rating <= 4) {
                mBinding.ivRate.setImageResource(R.drawable.img_rate_4)
            } else if (rating <= 5) {
                mBinding.ivRate.setImageResource(R.drawable.img_rate_5)
            }

            if (rating < 4) {
                mBinding.tvRateNow.text = context.getString(R.string.thank_you)
            } else {
                mBinding.tvRateNow.text = context.getString(R.string.text_rate_now)
            }
        }

    }

    override fun onClickViews() {
        mBinding.tvRateNow.setOnClickListener {
            if (mBinding.simpleRatingBar.rating == 0f) {
                Toast.makeText(
                    activity,
                    activity.resources.getString(R.string.please_feedback),
                    Toast.LENGTH_SHORT
                ).show()
            }
            if (mBinding.simpleRatingBar.rating <= 3.0) {
                Toast.makeText(activity, R.string.thank_for_your_feedback, Toast.LENGTH_SHORT)
                    .show()
                dismiss()
            } else {
                onRating.invoke()
                dismiss()
            }

            exitApp.invoke()
        }
    }
}