package com.example.android2mynotesapp

import android.view.View

class CustomOnItemClickListener(
    private val i: Int,
    private val onItemClickCallback: OnItemClickCallback
) : View.OnClickListener {
    override fun onClick(v: View?) {
        onItemClickCallback.onItemClicked(v, i)
    }

    interface OnItemClickCallback {
        fun onItemClicked(v: View?, i: Int)
    }
}