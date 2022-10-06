package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import com.example.myapplication.databinding.ActivityInicioSesionBinding
import com.google.firebase.auth.FirebaseAuth

private val String.editCorreo: Any
    get() = String

class Activity_inicio_sesion : AppCompatActivity() {
    private lateinit var binding: ActivityInicioSesionBinding
    private lateinit var firebaseAuth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInicioSesionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseAuth = FirebaseAuth.getInstance()

        binding.btnIniciarSesion.setOnClickListener {
            val editCorreo = binding.editCorreo.editText.toString()
            val editPassword = binding.editPassword.editText.toString()
            when{
                editCorreo.isEmpty() || editPassword.isEmpty() ->{
                    Toast.makeText(this, "Correo y/o contraseña incorrectos", Toast.LENGTH_SHORT).show()
                }
                else -> {
                    IniciarSesion(editCorreo, editPassword)
                }
            }
        }

        }

   private fun IniciarSesion(editCorreo: String, editPassword: String){
       firebaseAuth.createUserWithEmailAndPassword(editCorreo, editPassword)
           .addOnCompleteListener(this) { task ->
              if (task.isSuccessful){
                  val intent: Intent = Intent(this, Activity_cerrar_sesion::class.java)
                  this.startActivity(intent)
              }

           }
   }
}






private fun String.isEmpty(editPassword: String): Boolean =true
