package com.uz.mvvm.utils

import android.content.Context
import android.content.DialogInterface
import androidx.appcompat.app.AlertDialog


/**
 * Created by Eldor Turgunov on 24.08.2022.
 * Mvvm
 * eldorturgunov777@gmail.com
 */
object Utils {

    fun customDialog(context: Context?, title: String, message: String, dialogListener: DialogListener) {
        context?.let { context ->
            val builder = AlertDialog.Builder(context)
            builder.setTitle(title)
            builder.setMessage(message)
            builder.setCancelable(true)
            builder.setPositiveButton("OK") { dialogInterface: DialogInterface?, i: Int ->
                dialogListener.onPositiveClick()
                dialogInterface?.dismiss()
            }
            builder.setNegativeButton("NO")
            { dialogInterface: DialogInterface?, i: Int ->
                dialogListener.onNegativeClick()
                dialogInterface?.dismiss()
            }
            val alertDialog = builder.create()
            alertDialog.show()
        }
    }

    interface DialogListener {
        fun onPositiveClick()
        fun onNegativeClick()
    }
}