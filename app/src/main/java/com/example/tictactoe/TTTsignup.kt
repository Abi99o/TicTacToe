package com.example.tictactoe

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.tictactoe.databinding.ActivityTttsignupBinding
import com.google.firebase.auth.FirebaseAuth

class TTTsignup : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tttsignup)

        val binding:ActivityTttsignupBinding = ActivityTttsignupBinding.inflate(layoutInflater)

        setContentView(binding.root)
        val fireBase:FirebaseAuth=FirebaseAuth.getInstance()
        binding.signUp.setOnClickListener {
            val userName = binding.inputUsername.text.toString()
            val userEmail = binding.inputEmail.text.toString()
            val userPhn = binding.inputPhno.text.toString()
            val userPass1 = binding.inputPass1.text.toString()
            val userPass2 = binding.inputPass2.text.toString()

            if (userName.isNotEmpty() && userEmail.isNotEmpty() && userPhn.isNotEmpty()
                && userPass1.isNotEmpty() && userPass2.isNotEmpty()) {
                if (userPass1 == userPass2) {
                    fireBase.createUserWithEmailAndPassword(userEmail,userPass1).addOnCompleteListener {
                        if(it.isSuccessful) {
                            Toast.makeText(
                                this,
                                "Signup Successful! Please login",
                                Toast.LENGTH_LONG
                            ).show()
                            finish()
                        }
                        else
                            Toast.makeText(this,it.exception.toString(),Toast.LENGTH_LONG).show()
                    }
                } else
                    Toast.makeText(this, "Passwords are not matching", Toast.LENGTH_LONG).show()
            }
            else
                Toast.makeText(this,"Please fill all the fields!!",Toast.LENGTH_LONG).show()
        }

       binding.userLogin.setOnClickListener {
           finish()
       }
        binding.backArrow.setOnClickListener {
           finish()
        }



    }
}