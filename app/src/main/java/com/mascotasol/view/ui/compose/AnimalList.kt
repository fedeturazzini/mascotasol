package com.mascotasol.view.ui.compose

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.unit.dp
import com.mascotasol.data.model.Animal
import com.mascotasol.viewmodel.AnimalViewModel

@Composable
fun AnimalListContent(animalViewModel: AnimalViewModel, navigateToAnimalDetail: (Animal) -> Unit) {
    val animals by animalViewModel.animalsSate.collectAsState()

    LazyColumn(
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
    ) {
        items(
            items = animals,
            itemContent = {
                AnimalListItem(animal = it, navigateToAnimalDetail)
            }
        )

    }
}