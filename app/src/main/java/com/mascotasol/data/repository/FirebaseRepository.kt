package com.mascotasol.data.repository

import com.mascotasol.data.model.Animal

interface FirebaseRepository {
    suspend fun addAnimals(name: String, raza: String)
    suspend fun getAnimals(): List<Animal>
    fun listenAnimals(animal: (MutableList<Animal>) -> Unit)
}
