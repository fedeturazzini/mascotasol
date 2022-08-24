package com.mascotasol.view.activities

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.mascotasol.databinding.ActivityAnimalBinding
import com.mascotasol.view.ui.adapters.AnimalAdapter
import com.mascotasol.view.ui.bottomsheet.initListeners
import com.mascotasol.view.ui.fabButton.initBottomSheetListener
import com.mascotasol.view.ui.toolbar.initListeners
import com.mascotasol.viewmodel.AnimalViewModel
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.lifecycle.lifecycleScope
import com.mascotasol.data.model.Animal
import com.mascotasol.view.ui.compose.AnimalListContent
import com.mascotasol.view.ui.compose.AnimalListItem
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class AnimalActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAnimalBinding
    private lateinit var bottomSheetBehavior: BottomSheetBehavior<ConstraintLayout>
    private lateinit var animalViewModel: AnimalViewModel
    private lateinit var animalAdapter: AnimalAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAnimalBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initViewModel()
        initToolbar()
        initAnimalList()
        initBottomSheet()
        initFabButton()
    }

    private fun initAnimalList() {
        binding.myComposable.setContent {
            MyApp(animalViewModel) {
                startActivity(AnimalDetailActivity.newIntent(this, it))
            }
        }
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

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MyApp(animalViewModel: AnimalViewModel, navigateToAnimalDetail: (Animal) -> Unit) {
    Scaffold(
        content = {
            AnimalListContent(animalViewModel = animalViewModel , navigateToAnimalDetail = navigateToAnimalDetail)
        }
    )
}


