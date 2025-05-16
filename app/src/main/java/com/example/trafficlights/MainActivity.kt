package com.example.trafficlights

import android.content.res.Configuration
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import com.example.trafficlights.databinding.ActivityMainBinding

lateinit var binding: ActivityMainBinding
class MainActivity : AppCompatActivity() {

    private lateinit var redLight: View
    private lateinit var yellowLight: View
    private lateinit var greenLight: View
    private lateinit var trafficLightsContainer: LinearLayout
    private var currentState = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        redLight = binding.redLight
        yellowLight = binding.yellowLight
        greenLight = binding.greenLight
        trafficLightsContainer = binding.trafficLightsContainer

        if (savedInstanceState != null) {
            currentState = savedInstanceState.getInt("STATE", 0)
        }

        updateTrafficLightsOrientation()
        updateLights()

        // Обработчик кнопки
        binding.changeButton.setOnClickListener {
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