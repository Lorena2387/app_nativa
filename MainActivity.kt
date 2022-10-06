package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.databinding.ActivityMainBinding
import com.google.firebase.auth.FirebaseAuth
import java.util.regex.Pattern


val EditText.editText: Any
    get() = Unit

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseAuth = FirebaseAuth.getInstance()

        binding.tvIniciarSesion.setOnClickListener{
            val intent: Intent = Intent(this, Activity_inicio_sesion::class.java)
            startActivity(intent)
        }


        binding.button.setOnClickListener{
            val editCorreo = binding.editCorreo.editText?.toString()
            val editPassword = binding.editPassword.editText?.toString()
            val passwordRegex = Pattern.compile("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#\$%!\\-_?&])(?=\\S+\$).{8,}")
            if (editCorreo != null) {
                if (editCorreo.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(editCorreo).matches()){
                    Toast.makeText(this, "Correo no válido", Toast.LENGTH_SHORT).show()
                }else if (editPassword != null) {
                    if (editPassword.isEmpty() || !passwordRegex.matcher(editPassword).matches()){
                        Toast.makeText(this, "Contraseña no válida", Toast.LENGTH_SHORT)

                    }else{
                        registro(editCorreo, editPassword)
                    }
                }

            }

        }
}

    private fun registro(editCorreo: String, editPassword: String) {
        firebaseAuth.createUserWithEmailAndPassword(editCorreo, editPassword)
            .addOnCompleteListener(this){ task ->
                println("mostrando el resultado de task"+ task)
                if (task.isSuccessful){
                    val intent:Intent = Intent(this, Activity_inicio_sesion::class.java)
                    this.startActivity(intent)
                }else{
                    Toast.makeText(this, "Error al crear la cuenta", Toast.LENGTH_SHORT).show()
                }

            }

    }

}










