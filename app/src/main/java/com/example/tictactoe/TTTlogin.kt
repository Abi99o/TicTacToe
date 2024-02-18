package com.example.tictactoe

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.example.tictactoe.databinding.ActivityTttloginBinding
import com.google.firebase.auth.FirebaseAuth
class TTTlogin : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Thread.sleep(2000)
        installSplashScreen()
        setContentView(R.layout.activity_tttlogin)
        findViewById<Button>(R.id.loginbutton)
        val binding:ActivityTttloginBinding = ActivityTttloginBinding.inflate(layoutInflater)

        setContentView(binding.root)
        val fireBase: FirebaseAuth = FirebaseAuth.getInstance()
        if(fireBase.currentUser != null)
        {
            val intent1=Intent(this,TTThome::class.java)
            startActivity(intent1)
        }

        binding.createaccount.setOnClickListener {
            val intentSignUP = Intent(this,TTTsignup::class.java)
            startActivity(intentSignUP)
        }
        binding.loginbutton.setOnClickListener {
            val logEmail = binding.EmailAddressLogin.text.toString()
            val logPass = binding.loginPassword2.text.toString()
            if (logEmail.isNotEmpty() && logPass.isNotEmpty()) {
                fireBase.signInWithEmailAndPassword(logEmail, logPass).addOnCompleteListener {
                    if (it.isSuccessful) {
                        Toast.makeText(this, "Login Successful!!", Toast.LENGTH_SHORT).show()
                        val intent1=Intent(this,TTThome::class.java)
                        startActivity(intent1)
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