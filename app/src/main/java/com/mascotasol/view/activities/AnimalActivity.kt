package com.mascotasol.view.activities

import android.app.ActivityOptions
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.transition.Explode
import android.view.View
import android.view.Window
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
import com.mascotasol.view.ui.fabButton.initBottomSheetListener
import com.mascotasol.view.ui.toolbar.initListeners
import com.mascotasol.viewmodel.AnimalViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

@AndroidEntryPoint
class AnimalActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAnimalBinding
    private lateinit var bottomSheetBehavior: BottomSheetBehavior<ConstraintLayout>
    private lateinit var animalViewModel: AnimalViewModel
    private val animalAdapter = AnimalAdapter(onClick = {
        val intent2 = Intent(this, AnimalDetailActivity::class.java)
        startActivity(
            intent2,
            ActivityOptions.makeSceneTransitionAnimation(
                this
            ).toBundle()
        )
    })

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAnimalBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initViewModel()
        initToolbar()
        initRecyclerView()
        initBottomSheet()
        initFabButton()
    }

    private fun initViewModel() {
        animalViewModel = ViewModelProvider(this)[AnimalViewModel::class.java]
        animalViewModel.apply {
            getAnimals()
            startListenerForAnimals()
        }
    }

    private fun initToolbar() {
        binding.toolbar.apply {
            setSupportActionBar(customToolbar)
            initListeners(binding.root.context)
        }
    }

    private fun initRecyclerView() {
        binding.recyclerView.let {
            it.layoutManager = GridLayoutManager(it.context, 2)
            it.adapter = animalAdapter
            lifecycleScope.launch {
                animalViewModel.animalsSate.collect { animals ->
                    animalAdapter.addAnimals(animals)
                }
            }
        }
    }

    private fun initBottomSheet() {
        binding.bottomSheetLayout.apply {
            bottomSheetBehavior = BottomSheetBehavior.from(this.bottomSheet)

            this.initListeners(animalViewModel)

            bottomSheetBehavior.addBottomSheetCallback(object :
                BottomSheetBehavior.BottomSheetCallback() {
                override fun onStateChanged(bottomSheet: View, newState: Int) {}

                override fun onSlide(bottomSheet: View, slideOffset: Float) {
                    binding.fab.rotation = slideOffset * 180
                }
            })
        }
    }

    private fun initFabButton() {
        binding.fab.apply { this.initBottomSheetListener(bottomSheetBehavior) }
    }
}


