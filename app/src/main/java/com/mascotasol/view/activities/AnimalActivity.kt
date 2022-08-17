package com.mascotasol.view.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.mascotasol.databinding.ActivityAnimalBinding
import com.mascotasol.data.model.Animal
import com.mascotasol.view.ui.adapters.AnimalAdapter
import com.mascotasol.view.ui.bottomsheet.initListeners
import com.mascotasol.view.ui.toolbar.initListeners
import com.mascotasol.viewmodel.AnimalViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

@AndroidEntryPoint
class AnimalActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAnimalBinding
    private lateinit var bottomSheetBehavior: BottomSheetBehavior<ConstraintLayout>
   // private val animalRef = Firebase.firestore.collection("Animal")

    private lateinit var animalViewModel: AnimalViewModel

    private val animalAdapter = AnimalAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAnimalBinding.inflate(layoutInflater)
        setContentView(binding.root)

        animalViewModel = ViewModelProvider(this)[AnimalViewModel::class.java]
        animalViewModel.apply {
            getAnimals()
            startListenerForAnimals()
        }

        binding.toolbar.apply {
            setSupportActionBar(customToolbar)
            initListeners(binding.root.context)
        }

        binding.bottomSheetLayout.apply {
            bottomSheetBehavior = BottomSheetBehavior.from(this.bottomSheet)

            this.initListeners(animalViewModel)

            bottomSheetBehavior.addBottomSheetCallback(object: BottomSheetBehavior.BottomSheetCallback(){
                override fun onStateChanged(bottomSheet: View, newState: Int) {}

                override fun onSlide(bottomSheet: View, slideOffset: Float) {
                    binding.fab.rotation = slideOffset * 180
                }
            })
        }

//        animalRef.addSnapshotListener { value, error ->
//            value?.let {
//                val animals = addAnimalList(it)
//                animalAdapter.addAnimals(animals)
//            }
//        }

        binding.recyclerView.let {
            it.layoutManager = GridLayoutManager(it.context, 2)
            it.adapter = animalAdapter
        }

        lifecycleScope.launch {
            animalViewModel.animalsSate.collect { animals ->
                animalAdapter.addAnimals(animals)
            }
        }


        binding.fab.setOnClickListener {
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
                BottomSheetBehavior.STATE_DRAGGING -> {
                    Toast.makeText(this, "Aca1", Toast.LENGTH_LONG).show()
                }
                BottomSheetBehavior.STATE_HALF_EXPANDED -> {
                    Toast.makeText(this, "Aca2", Toast.LENGTH_LONG).show()
                }
                BottomSheetBehavior.STATE_SETTLING -> {
                    Toast.makeText(this, "Aca3", Toast.LENGTH_LONG).show()
                }
            }
        }
    }

}


