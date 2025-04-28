package com.example.trafficlights

import android.content.res.Configuration
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private lateinit var redLight: View
    private lateinit var yellowLight: View
    private lateinit var greenLight: View
    private lateinit var trafficLightsContainer: LinearLayout
    private var currentState = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Инициализация элементов
        redLight = findViewById(R.id.red_light)
        yellowLight = findViewById(R.id.yellow_light)
        greenLight = findViewById(R.id.green_light)
        trafficLightsContainer = findViewById(R.id.traffic_lights_container)

        // Восстановление состояния
        if (savedInstanceState != null) {
            currentState = savedInstanceState.getInt("STATE", 0)
        }

        // Обновление ориентации и светофора
        updateTrafficLightsOrientation()
        updateLights()

        // Обработчик кнопки
        findViewById<View>(R.id.change_button).setOnClickListener {
            changeLight()
        }
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        updateTrafficLightsOrientation()
    }

    private fun updateTrafficLightsOrientation() {
        trafficLightsContainer.orientation = when (resources.configuration.orientation) {
            Configuration.ORIENTATION_LANDSCAPE -> LinearLayout.HORIZONTAL
            else -> LinearLayout.VERTICAL
        }
    }

    private fun changeLight() {
        currentState = (currentState + 1) % 4
        updateLights()
    }

    private fun updateLights() {
        // Сброс всех цветов
        redLight.setBackgroundResource(R.drawable.gray)
        yellowLight.setBackgroundResource(R.drawable.gray)
        greenLight.setBackgroundResource(R.drawable.gray)

        // Установка активного цвета
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