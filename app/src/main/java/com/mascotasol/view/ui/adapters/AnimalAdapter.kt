package com.mascotasol.view.ui.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mascotasol.databinding.AnimalRowBinding
import com.mascotasol.data.model.Animal

class AnimalAdapter: RecyclerView.Adapter<AnimalAdapter.ViewHolder>() {

    private lateinit var animalRowBinding: AnimalRowBinding
    private var animalsList: List<Animal?> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        animalRowBinding = AnimalRowBinding.inflate(LayoutInflater.from(parent.context))
        return ViewHolder(animalRowBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        animalsList[position]?.let { holder.bind(it) }
    }

    override fun getItemCount() = animalsList.size

    @SuppressLint("NotifyDataSetChanged")
    fun addAnimals(animals: List<Animal?>) {
        animals.apply { animalsList = this }
        notifyDataSetChanged()
    }

    inner class ViewHolder(private val animalRow: AnimalRowBinding) : RecyclerView.ViewHolder(animalRow.root) {
        fun bind(animal: Animal) {
            animalRow.apply {
                name.text = animal.name
                raza.text = animal.raza
            }
        }
    }
}