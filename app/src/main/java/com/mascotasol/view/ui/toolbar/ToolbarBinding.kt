package com.mascotasol.view.ui.toolbar

import android.content.Context
import android.widget.Toast
import com.mascotasol.databinding.CustomToolbarBinding

fun CustomToolbarBinding.initListeners(context: Context) {
    this.customToolbar.setNavigationOnClickListener {
        Toast.makeText(context, "Toolbar", Toast.LENGTH_LONG).show()
    }
}