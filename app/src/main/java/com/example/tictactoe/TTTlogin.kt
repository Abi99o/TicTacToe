package com.example.tictactoe

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract.CommonDataKinds.Email
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.tictactoe.databinding.ActivityTttloginBinding
import com.example.tictactoe.databinding.ActivityTttsignupBinding
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.FirebaseAuth
import org.w3c.dom.Text

class TTTlogin : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tttlogin)
        val logBtn = findViewById<Button>(R.id.loginbutton)
        val binding:ActivityTttloginBinding = ActivityTttloginBinding.inflate(layoutInflater)

        setContentView(binding.root)
        val fireBase: FirebaseAuth = FirebaseAuth.getInstance()
        binding.createaccount.setOnClickListener(){
            val intentSignUP = Intent(this,TTTsignup::class.java)
            startActivity(intentSignUP)
        }
        binding.loginbutton.setOnClickListener() {
            val logEmail = binding.EmailAddressLogin.text.toString()
            val logPass = binding.loginPassword2.text.toString()
            if (logEmail.isNotEmpty() && logPass.isNotEmpty()) {
                fireBase.signInWithEmailAndPassword(logEmail, logPass).addOnCompleteListener() {
                    if (it.isSuccessful) {
                        Toast.makeText(this, "Login Successful!!", Toast.LENGTH_SHORT).show()
                        finish()
                    } else {
                        Toast.makeText(this, "Wrong credentials!", Toast.LENGTH_LONG).show()
                    }
                }
            } else {
                Toast.makeText(this, "Please fill all the following credentials", Toast.LENGTH_LONG)
                    .show()
            }
        }


    }
}