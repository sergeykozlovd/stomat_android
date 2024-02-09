package com.example.stomat

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.View
import android.widget.TextView
import android.widget.Toolbar


object AlertUtils {

    fun alert(
        context: Context,
        message: String,
        title: String? = null,
        callbackOk: (() -> Unit)? = null,
        callbackCancel: (() -> Unit)? = null
    ) {

        val dialog = Dialog(context,R.style.Dialog).apply {
            setContentView(R.layout.dialog)
            window?.apply {
                setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
                setLayout(
                    Toolbar.LayoutParams.MATCH_PARENT,
                    Toolbar.LayoutParams.MATCH_PARENT
                )
            }
        }

        val tvTitle = dialog.findViewById<TextView>(R.id.tvTitle)
        val tvMessage = dialog.findViewById<TextView>(R.id.tvMessage)
        val tvOK = dialog.findViewById<TextView>(R.id.tvOK)
        val tvCancel = dialog.findViewById<TextView>(R.id.tvCancel)
        val dev = dialog.findViewById<View>(R.id.dev)


        title?.let { tvTitle.text = it }
        tvMessage.text = message

        tvOK.setOnClickListener {
            dialog.dismiss()
            if (callbackOk != null) {
                callbackOk()
            }
        }

        callbackCancel?.let {
            dev.visibility = View.VISIBLE
            tvCancel.visibility = View.VISIBLE

            tvCancel.setOnClickListener {
                dialog.dismiss()
                callbackCancel()
            }
        }

        dialog.show()
    }
}