package com.mascotasol.view.ui.fabButton

import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import com.google.android.material.bottomsheet.BottomSheetBehavior

fun View.initBottomSheetListener(bottomSheetBehavior: BottomSheetBehavior<ConstraintLayout>) {
    setOnClickListener {
        when (bottomSheetBehavior.state) {
            BottomSheetBehavior.STATE_COLLAPSED -> {
                bottomSheetBehavior.state = BottomSheetBehavior.STATE_EXPANDED
            }
            BottomSheetBehavior.STATE_EXPANDED -> {
                bottomSheetBehavior.state = BottomSheetBehavior.STATE_COLLAPSED
            }
            BottomSheetBehavior.STATE_HIDDEN -> {
                bottomSheetBehavior.state = BottomSheetBehavior.STATE_EXPANDED
            }
        }
    }
}