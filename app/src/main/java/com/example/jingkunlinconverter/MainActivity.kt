package com.example.jingkunlinconverter

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.SeekBar
import kotlin.math.max


class MainActivity : AppCompatActivity() {

    private lateinit var FSeekBar: SeekBar
    private lateinit var CSeekBar: SeekBar
    private lateinit var Ctext: TextView
    private lateinit var Ftext: TextView
    private lateinit var Cvalue: TextView
    private lateinit var Fvalue: TextView

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout)
        Ctext = findViewById(R.id.Ctext)
        Ftext = findViewById(R.id.Ftext)
        Cvalue = findViewById(R.id.Cvalue)
        Fvalue = findViewById(R.id.Fvalue)
        FSeekBar = findViewById(R.id.FSeekBar)
        CSeekBar = findViewById(R.id.CSeekBar)

        val scaleFactor = 100.0

        CSeekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                if (fromUser) {
                    val doubleValue = progress / scaleFactor
                    // Format the double value to a string with two decimal places
                    val transformedValue = progress * 1.8 + 3200


                    val scaleValue = transformedValue / scaleFactor
                    val formattedValue = String.format("%.2f", scaleValue)

                    Cvalue.text = "Celsius: $doubleValue"
                    FSeekBar.progress = transformedValue.toInt()
                    Fvalue.text = "Fahrenheit: $formattedValue"
                }
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {}

            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        })

        FSeekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                if (fromUser) {
                    var doubleValue = progress / scaleFactor
                    // Format the double value to a string with two decimal places
                    if (doubleValue < 32) {
                        doubleValue = 32.0
                        // Calculate what the SeekBar progress should be to represent 32 Fahrenheit
                        // Assuming your scaleFactor and formula correctly map progress to Fahrenheit,
                        // adjust this calculation to correctly reflect the progress for 32 Fahrenheit.
                        val progressFor32F = (32 * scaleFactor).toInt()
                        FSeekBar.progress = progressFor32F
                    } else {
                        // If not below 32, just use the calculated progress
                        FSeekBar.progress = progress
                    }
                    val transformedValue = max( (progress - 3200)/(1.8), 0.0)


                    val scaleValue = transformedValue / scaleFactor
                    val formattedValue = String.format("%.2f", scaleValue)

                    Cvalue.text = "Celsius: $formattedValue"
                    CSeekBar.progress = transformedValue.toInt()
                    Fvalue.text = "Fahrenheit: $doubleValue"
                }
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {}

            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        })
    }
}