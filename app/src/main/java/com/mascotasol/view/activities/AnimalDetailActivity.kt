package com.mascotasol.view.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.transition.Explode
import android.view.Window
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import com.mascotasol.data.model.Animal
import com.mascotasol.view.ui.compose.AnimalDetailScreen

class AnimalDetailActivity : AppCompatActivity() {

    private val animal: Animal by lazy {
        intent?.getSerializableExtra(ANIMAL_ID) as Animal
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        with(window) { explodeAnimate() }
        setContent {
            AnimalDetailScreen(animal = animal)
        }
    }

    private fun Window.explodeAnimate() {
        requestFeature(Window.FEATURE_CONTENT_TRANSITIONS)
        enterTransition = Explode()
        exitTransition = Explode()
        exitTransition.duration = 2000
    }

    companion object {
        private const val ANIMAL_ID = "animal_id"
        fun newIntent(context: Context, animal: Animal) =
            Intent(context, AnimalDetailActivity::class.java).apply {
                putExtra(ANIMAL_ID, animal)
            }
    }
}