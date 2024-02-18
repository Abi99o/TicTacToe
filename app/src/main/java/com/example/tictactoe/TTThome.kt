package com.example.tictactoe

import android.content.Intent
import android.content.res.ColorStateList
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.Toast
import androidx.core.content.ContextCompat

class TTThome : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val xBtn = findViewById<ImageButton>(R.id.xButton)
        val oBtn = findViewById<ImageButton>(R.id.oButton)
        val cpuBtn = findViewById<Button>(R.id.cpuButton)
        val plBtn = findViewById<Button>(R.id.plButton)

        plBtn.setOnClickListener{
            val intentGame = Intent(this,MainActivity::class.java)
            startActivity(intentGame)
        }
        cpuBtn.setOnClickListener{
            Toast.makeText(this, "Coming soon!",Toast.LENGTH_SHORT).show()
        }

        xBtn.setOnClickListener{
            xBtn.backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(this, R.color.grey))
            xBtn.setColorFilter(ContextCompat.getColor(this, R.color.darkgreen))
            oBtn.backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(this, R.color.darkgreen))
            oBtn.setColorFilter(ContextCompat.getColor(this, R.color.grey))
        }
        oBtn.setOnClickListener{
            xBtn.backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(this, R.color.darkgreen))
            xBtn.setColorFilter(ContextCompat.getColor(this, R.color.grey))
            oBtn.backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(this, R.color.grey))
            oBtn.setColorFilter(ContextCompat.getColor(this, R.color.darkgreen))
        }
    }
}

