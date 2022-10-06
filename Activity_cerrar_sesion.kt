package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.myapplication.databinding.ActivityCerrarSesionBinding
import com.google.firebase.auth.FirebaseAuth

class Activity_cerrar_sesion : AppCompatActivity() {
    private lateinit var binding: ActivityCerrarSesionBinding
    private lateinit var firebaseAuth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCerrarSesionBinding.inflate(layoutInflater)
        setContentView(binding.root)
        firebaseAuth = FirebaseAuth.getInstance()

        binding.button2.setOnClickListener {
            firebaseAuth.signOut()
            val intent: Intent = Intent(this, MainActivity::class.java)
            this.startActivity(intent)


        }
    }
}