package com.example.tictactoe

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.Gravity
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

@Suppress("DEPRECATION")
class MainActivity : AppCompatActivity() {
    private var flag = 0
    private var temp = 0
    private var toCheck = IntArray(9)
    private lateinit var turn: ImageView
    private lateinit var xScore : TextView
    private lateinit var oScore : TextView
    private lateinit var xoTies : TextView
    private var xScr : Int = 0
    private var oScr : Int = 0
    private var scr  : Int = 0
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        turn = findViewById(R.id.imageView)

        val b1button = findViewById<ImageButton>(R.id.imageButton1)
        val b2button = findViewById<ImageButton>(R.id.imageButton2)
        val b3button = findViewById<ImageButton>(R.id.imageButton3)
        val b4button = findViewById<ImageButton>(R.id.imageButton4)
        val b5button = findViewById<ImageButton>(R.id.imageButton5)
        val b6button = findViewById<ImageButton>(R.id.imageButton6)
        val b7button = findViewById<ImageButton>(R.id.imageButton7)
        val b8button = findViewById<ImageButton>(R.id.imageButton8)
        val b9button = findViewById<ImageButton>(R.id.imageButton9)
        val resButton = findViewById<ImageButton>(R.id.restart)
        val backtoHome = findViewById<ImageButton>(R.id.backBtn)

        backtoHome.setOnClickListener{
            finish()
        }
        xScore = findViewById(R.id.xScore)
        oScore = findViewById(R.id.oScore)
        xoTies = findViewById(R.id.oTies)
        xScr = xScore.text.toString().toIntOrNull() ?: 0
        oScr = oScore.text.toString().toIntOrNull() ?: 0
        scr = xoTies.text.toString().toIntOrNull() ?: 0
        val buttons = arrayOf(
            b1button, b2button, b3button,
            b4button, b5button, b6button,
            b7button, b8button, b9button
        )
        resButton.setOnClickListener {
           for(button in buttons)
           {
               val btInd = buttons.indexOf(button)
               if(buttons[btInd].drawable!=null) {
                   buttons[btInd].setImageDrawable(null)

                   --temp
                   toCheck[btInd]=0
               }
               buttons[btInd].isEnabled=true
               println(btInd)
         }
        }


        for (button in buttons) {
            button.setOnClickListener { onButtonClick(buttons, button) }
        }
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    fun onButtonClick(buttons: Array<ImageButton>, clickedButton: ImageButton) {
        val buttonIndex = buttons.indexOf(clickedButton)

        when (flag) {
            1 -> {
                turn.setImageDrawable(resources.getDrawable(R.drawable.x1))
                flag--
                val newImage = resources.getDrawable(R.drawable.o2,null)
                buttons[buttonIndex].setImageDrawable(newImage)
                toCheck[buttonIndex] = 1
            }

            else -> {
                turn.setImageDrawable(resources.getDrawable(R.drawable.o1))
                flag++
                val newImage = resources.getDrawable(R.drawable.x2,null)
                buttons[buttonIndex].setImageDrawable(newImage)
                toCheck[buttonIndex] = 4
            }
        }

        ++temp
        if (temp >= 3) {
            if (!checkWin(toCheck)) {
                if (temp == 9) {
                    scr++
                    makeToast()
                }
            }
            else
                disableAllButtons(buttons)
        }

        buttons[buttonIndex].isEnabled = false
    }

    private fun disableAllButtons(buttons: Array<ImageButton>) {
        for (button in buttons) {
            button.isEnabled = false
        }
    }

    private fun checkWin(x: IntArray): Boolean {
        val sums = arrayOf(
            x[0] + x[1] + x[2],
            x[3] + x[4] + x[5],
            x[6] + x[7] + x[8],
            x[0] + x[3] + x[6],
            x[1] + x[4] + x[7],
            x[2] + x[5] + x[8],
            x[0] + x[4] + x[8],
            x[2] + x[4] + x[6]
        )

        for (sum in sums) {
            when (sum) {
                3 -> {
                    Toast.makeText(this, "PLAYER O WON", Toast.LENGTH_SHORT).show()
                    oScr++
                    xScore.text=xScr.toString()
                    oScore.text=oScr.toString()
                    xoTies.text=scr.toString()
                    return true
                }
                12 -> {
                    Toast.makeText(this, "PLAYER X WON", Toast.LENGTH_SHORT).show()
                    xScr++
                    xScore.text=xScr.toString()
                    oScore.text=oScr.toString()
                    xoTies.text=scr.toString()
                    return true
                }
            }
        }

        return false
    }

    private fun makeToast() {
        val toast: Toast = Toast.makeText(this, "GAME OVER!!", Toast.LENGTH_LONG)
        toast.setGravity(Gravity.CENTER, 0, 0)
        toast.show()
        xScore.text=xScr.toString()
        oScore.text=oScr.toString()
        xoTies.text=scr.toString()
    }
}
