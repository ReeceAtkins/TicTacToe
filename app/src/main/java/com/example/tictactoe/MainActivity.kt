package com.example.tictactoe

import android.annotation.SuppressLint
import android.media.AsyncPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import org.w3c.dom.Text

class MainActivity : AppCompatActivity() {
    private lateinit var buttons: Array<Button>
    private var currentPlayerX = true

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val button1 = findViewById<Button>(R.id.btnTopLeft)
        val button2 = findViewById<Button>(R.id.btnTopCenter)
        val button3 = findViewById<Button>(R.id.btnTopRight)
        val button4 = findViewById<Button>(R.id.btnMiddleLeft)
        val button5 = findViewById<Button>(R.id.btnMiddleCenter)
        val button6 = findViewById<Button>(R.id.btnMiddleRight)
        val button7 = findViewById<Button>(R.id.btnBottomLeft)
        val button8 = findViewById<Button>(R.id.btnBottomCenter)
        val button9 = findViewById<Button>(R.id.btnBottomRight)
        buttons = arrayOf(button1, button2, button3, button4, button5, button6, button7, button8, button9)

        currentPlayerX = true
        newGame(buttons)
    }

    fun btnNewGameClick(view: View) {
        newGame(buttons)
    }

    private fun newGame(buttons : Array<Button> ) {
        for (button in buttons) {
            button.isEnabled = true
            button.setText("")
        }

        currentPlayerX = true
        findViewById<TextView>(R.id.tvPlayer).text = "Player X's turn"

    }

    fun btnClick(view: View) {
        val buttonSelected = view as Button
        var player = if (currentPlayerX) "X" else "O"
        playGame(buttonSelected)

        findViewById<TextView>(R.id.tvPlayer).text = if (currentPlayerX) "Player X's turn" else "Player O's turn"

        if (checkIfBoardIsFull()) {
            findViewById<TextView>(R.id.tvPlayer).text = "Game ended in a Tie"
            disableButtons(buttons)
        }
        if (checkWinner(player)) {
            findViewById<TextView>(R.id.tvPlayer).text = "Winner: player $player"
            disableButtons(buttons)
        }
    }

    private fun playGame(buttonSelected: Button) {
        if (buttonSelected.text.equals("")) {
            if (currentPlayerX) {
                buttonSelected.text = "X"
                currentPlayerX = false
            } else {
                buttonSelected.text = "O"
                currentPlayerX = true
            }
        }
    }

    private fun disableButtons(buttons: Array<Button>) {
        for (button in buttons) {
            button.isEnabled = false
        }
    }

    private fun checkWinner(player: String) : Boolean {

        // Check rows
        if (buttons[0].text == player && buttons[1].text == player && buttons[2].text == player ||
            buttons[3].text == player && buttons[4].text == player && buttons[5].text == player ||
            buttons[6].text == player && buttons[7].text == player && buttons[8].text == player) {
            return true
        }
        // Check Columns
        else if (buttons[0].text == player && buttons[3].text == player && buttons[6].text == player ||
            buttons[1].text == player && buttons[4].text == player && buttons[7].text == player ||
            buttons[2].text == player && buttons[5].text == player && buttons[8].text == player) {
            return true
        }
        // Check diagonals
        else if (buttons[0].text == player && buttons[4].text == player && buttons[8].text == player ||
            buttons[2].text == player && buttons[4].text == player && buttons[6].text == player) {
            return true
        }

        return false
    }


    private fun checkIfBoardIsFull() : Boolean {
        var isBoardFull = true
        for (button in buttons) {
            if (button.text == "") isBoardFull = false
        }

        return isBoardFull
    }
}