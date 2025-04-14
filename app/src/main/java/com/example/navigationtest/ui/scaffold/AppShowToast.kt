package com.example.navigationtest.ui.scaffold

import android.content.Context
import android.widget.Toast
import androidx.annotation.StringRes

fun showToast(
    context: Context,
    @StringRes textRes: Int
) {
    Toast.makeText(
        context,
        textRes,
        Toast.LENGTH_LONG
    ).show()
}