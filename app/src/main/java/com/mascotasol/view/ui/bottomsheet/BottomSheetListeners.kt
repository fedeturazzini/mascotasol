package com.mascotasol.view.ui.bottomsheet

import com.mascotasol.databinding.BottomSheetLayoutAnimalBinding
import com.mascotasol.viewmodel.AnimalViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

fun BottomSheetLayoutAnimalBinding.initListeners(animalViewModel: AnimalViewModel?) {
    this.button.setOnClickListener {
        CoroutineScope(Dispatchers.IO).launch {
            animalViewModel?.apply {
                addAnimal(
                    this@initListeners.name.text.toString(),
                    this@initListeners.raza.text.toString()
                )
            }
        }
    }
}