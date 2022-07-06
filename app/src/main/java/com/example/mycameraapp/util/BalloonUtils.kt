package com.example.mycameraapp.util

import android.content.Context
import androidx.lifecycle.LifecycleOwner
import com.skydoves.balloon.*


object BalloonUtils {
//    interface OnClickBalloon {
////        fun onClick()
//    }

    fun getAllBalloon(
        context: Context,
        lifecycleOwner: LifecycleOwner,
        message: String,
        icon: Int,
        listener: OnBalloonClickListener
    ): Balloon {

        return Balloon.Builder(context)
            .setTextIsHtml(true)
            .setText(message)
            .setArrowSize(16)
//            .setArrowTopPadding(8)
//            .setWidthRatio(0.5f)
            .setWidthRatio(0.25f)
            .setHeight(250)
            .setWidth(BalloonSizeSpec.WRAP)
            .setArrowPositionRules(ArrowPositionRules.ALIGN_ANCHOR)
            .setArrowPosition(0.5f)
            .setTextGravity(0)
            .setMarginRight(6)
            .setMarginLeft(6)
            .setPadding(8)
            .setTextSize(16f)
            .setCornerRadius(8f)
            .setTextColorResource(com.example.mycameraapp.R.color.white)
            .setIconDrawableResource(icon)
//            .setIconForm(form)
            .setIconWidth(60)
            .setIconHeight(90)
            .setBackgroundColorResource(com.example.mycameraapp.R.color.gray_400)
            .setAlpha(0.95f)
            .setOnBalloonDismissListener {
//                Toast.makeText(context.applicationContext, "dismissed", Toast.LENGTH_SHORT).show()
            }
            .setOnBalloonClickListener {
                listener.onBalloonClick(it)
            }

            .setBalloonAnimation(BalloonAnimation.ELASTIC)
            .setLifecycleOwner(lifecycleOwner)
            .build()
    }


//    fun getAllBalloon(context: Context, lifecycleOwner: LifecycleOwner,message:String, icon: Int ): Balloon {
//        return Balloon.Builder(context)
////            .setTextIsHtml(true)
//            .setText(message)
//            .setArrowSize(16)
////            .setArrowSize(10)
////            .setWidthRatio(1.0f)
//            .setHeight(BalloonSizeSpec.WRAP)
//            .setWidth(BalloonSizeSpec.WRAP)
//            .setArrowPositionRules(ArrowPositionRules.ALIGN_ANCHOR)
//            .setArrowPosition(0.5f)
//            .setPadding(6)
//            .setMarginRight(4)
////            .setMarginRight(6)
//            .setMarginLeft(4)
////            .setMarginLeft(6)
//            .setTextSize(15f)
//            .setCornerRadius(8f)
//            .setTextColorResource(R.color.white)
////            .setIconDrawableResource(icon)
//            .setBackgroundColorResource(R.color.colorPrimary)
//            .setOnBalloonDismissListener {
////                Toast.makeText(context.applicationContext, "dismissed", Toast.LENGTH_SHORT).show()
//            }
//            .setBalloonAnimation(BalloonAnimation.ELASTIC)
//            .setLifecycleOwner(lifecycleOwner)
//            .build()
//    }


}