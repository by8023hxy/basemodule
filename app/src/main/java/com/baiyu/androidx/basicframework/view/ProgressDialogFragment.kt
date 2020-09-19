package com.baiyu.androidx.basicframework.view

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.*
import android.view.animation.AnimationUtils
import android.view.animation.LinearInterpolator
import android.widget.ImageView
import androidx.fragment.app.DialogFragment
import com.baiyu.androidx.basicframework.R

class ProgressDialogFragment : DialogFragment() {

    var dialogView: View? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, R.style.Anim_fade)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dialog?.run {
            setCanceledOnTouchOutside(false)
            window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            window?.addFlags(
                WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN or
                        WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS or
                        WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM
            )
        }
        if (dialogView == null) {
            dialogView = inflater.inflate(R.layout.dialog_progress, container, false)
        }
        dialogView?.isClickable = true
        val imgRoll = dialogView!!.findViewById<ImageView>(R.id.img_roll)
        val circleAnim = AnimationUtils.loadAnimation(activity, R.anim.anim_round_rotate)
        val interpolator = LinearInterpolator()
        if (circleAnim != null) {
            circleAnim.interpolator = interpolator
            imgRoll.startAnimation(circleAnim)
        }
        return dialogView
    }

    override fun onResume() {
        super.onResume()
        getFocus()
    }

    override fun onStart() {
        super.onStart()
        dialog?.let {
            val window = it.window
            val windowParams = window?.attributes
            windowParams?.dimAmount = 0.3f
            window?.attributes = windowParams
        }
    }

    private fun getFocus() {
        requireView().isFocusableInTouchMode = true
        requireView().requestFocus()
        requireView().setOnKeyListener { v: View?, keyCode: Int, event: KeyEvent -> event.action == KeyEvent.ACTION_UP && keyCode == KeyEvent.KEYCODE_BACK }
    }

}