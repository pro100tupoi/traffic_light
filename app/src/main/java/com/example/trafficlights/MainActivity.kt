package com.example.trafficlights

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class MainActivity : AppCompatActivity() {
    private lateinit var redLight: View
    private lateinit var yellowLight: View
    private lateinit var greenLight: View
    private var currentState = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        redLight = findViewById(R.id.red_light)
        yellowLight = findViewById(R.id.yellow_light)
        greenLight = findViewById(R.id.green_light)

        // Восстанавливаем состояние при повороте
        if (savedInstanceState != null) {
            currentState = savedInstanceState.getInt("STATE", 0)
        }
        updateLights()

        findViewById<View>(R.id.change_button).setOnClickListener {
            changeLight()
        }
    }

    private fun changeLight() {
        currentState = (currentState + 1) % 4
        updateLights()
    }

    private fun updateLights() {
        // Все серые
        redLight.setBackgroundResource(R.drawable.gray)
        yellowLight.setBackgroundResource(R.drawable.gray)
        greenLight.setBackgroundResource(R.drawable.gray)

        when (currentState) {
            0 -> redLight.setBackgroundResource(R.drawable.red)
            1, 3 -> yellowLight.setBackgroundResource(R.drawable.yellow)
            2 -> greenLight.setBackgroundResource(R.drawable.green)
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt("STATE", currentState)
    }
}