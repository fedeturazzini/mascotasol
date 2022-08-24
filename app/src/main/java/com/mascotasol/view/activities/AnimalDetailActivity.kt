package com.mascotasol.view.activities

import android.os.Bundle
import android.transition.Explode
import android.view.Window
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity

class AnimalDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        with(window) { explodeAnimate() }
        setContent {

        }
    }

    private fun Window.explodeAnimate() {
        requestFeature(Window.FEATURE_CONTENT_TRANSITIONS)
        enterTransition = Explode()
        exitTransition = Explode()
        exitTransition.duration = 2000
    }
}