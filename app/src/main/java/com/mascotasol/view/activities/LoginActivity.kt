package com.mascotasol.view.activities

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.UserProfileChangeRequest
import com.mascotasol.R
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext

@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {

    lateinit var auth: FirebaseAuth

    override fun onStart() {
        super.onStart()
        checkLogginState()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        auth = FirebaseAuth.getInstance()
        // registerUser()
        uploadProfileImage()
    }


    private fun registerUser() {
        lifecycleScope.launch {
            auth.createUserWithEmailAndPassword(
                "fede.turazzini@gmail.com",
                "123456"
            ).await()

            withContext(Dispatchers.Main) {
                checkLogginState()
            }
        }

    }

    private fun uploadProfileImage() {
        auth.currentUser?.let {
            val profileUpdate = UserProfileChangeRequest.Builder()
                .setDisplayName("Tura")
                .setPhotoUri( Uri.parse(
                    "android.resource://$packageName/${R.drawable.ic_launcher_background}"
                ))
                .build()

            lifecycleScope.launch {
                it.updateProfile(profileUpdate).await()
                withContext(Dispatchers.Main) {
                    //Toast.makeText(this@LoginActivity, "Update fotito", Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    private fun checkLogginState() {
        if (auth.currentUser == null) {
            //Toast.makeText(this, "Loggin Nulo", Toast.LENGTH_LONG).show()
        } else {
            //Toast.makeText(this, "Loggin Exitoso", Toast.LENGTH_LONG).show()
            Intent(this, AnimalActivity::class.java).also {
                startActivity(it)
            }
        }
    }
}