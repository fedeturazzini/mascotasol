package com.mascotasol.data.repository

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot
import com.mascotasol.data.model.Animal
import kotlinx.coroutines.tasks.await

class FirebaseRepositoryImp(
    firebaseDatabase: FirebaseFirestore
): FirebaseRepository {
    private val animalRef = firebaseDatabase.collection("Animal")

    override suspend fun addAnimals(name: String, raza: String) {
        animalRef.add(
            Animal(
                1,
                name,
                raza,
                "sexo",
                1,
                1,
            "hola")
        ).await()
    }

    override suspend fun getAnimals(): List<Animal> {
        val querySnapshot = animalRef.get().await()
        return addAnimalList(querySnapshot)
    }

    override fun listenAnimals(animal: (MutableList<Animal>) -> Unit) {
        animalRef.addSnapshotListener { value, _ ->
            value?.let { animal.invoke(addAnimalList(it)) }
        }
    }

    private fun addAnimalList(querySnapshot: QuerySnapshot): MutableList<Animal> {
        val animals = mutableListOf<Animal>()
        for (document in querySnapshot.documents) {
            document.toObject(Animal::class.java)?.apply {
                animals.add(this)
            }
        }
        return animals
    }
}