package com.mascotasol.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mascotasol.data.model.Animal
import com.mascotasol.data.repository.FirebaseRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AnimalViewModel @Inject constructor(
    private val firebaseRepository: FirebaseRepository
) : ViewModel() {

    private val _animalsState = MutableStateFlow(emptyList<Animal>())
    val animalsSate: StateFlow<List<Animal>> = _animalsState

    fun addAnimal(name: String, raza: String) {
        viewModelScope.launch {
            firebaseRepository.addAnimals(name, raza)
        }
    }

    fun getAnimals() {
        viewModelScope.launch {
            _animalsState.value = firebaseRepository.getAnimals()
        }
    }

    fun startListenerForAnimals() {
        viewModelScope.launch {
            _animalsState.value = firebaseRepository.listenAnimals()
        }
    }

}