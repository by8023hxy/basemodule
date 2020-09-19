package com.baiyu.androidx.basicmodule.ext

import android.widget.SeekBar

/**
 * Add an action which will be invoked when the SeekBar onProgressChanged
 * @see SeekBar.setOnSeekBarChangeListener
 */
fun SeekBar.onProgressBarChanged(onChange: (Int, Boolean) -> Unit) {
    setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
        override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
            onChange(progress, fromUser)
        }
        override fun onStartTrackingTouch(seekBar: SeekBar?) {}

        override fun onStopTrackingTouch(seekBar: SeekBar?) {}
    })
}