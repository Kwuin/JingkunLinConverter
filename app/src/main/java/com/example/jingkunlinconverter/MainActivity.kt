package com.example.jingkunlinconverter

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.SeekBar
import kotlin.math.max

//Reference
//https://chat.openai.com/share/ea729446-37a2-4f3a-a15e-db547cd056d4
//https://chat.openai.com/share/6c0d3ebe-087b-4604-aafe-b8eb7537cb08
//https://chat.openai.com/share/76c59a4a-e677-4001-8fe4-6eaca7452643
//https://chat.openai.com/share/d78f8654-3deb-4be3-9776-b2c6b5115728
//https://chat.openai.com/share/b60a3be7-324a-4b99-81ea-56837126b571
//https://chat.openai.com/share/9d7d1113-093b-44c6-9613-6d2c86754d9a
//https://chat.openai.com/share/1a959b78-cd0f-4884-9d28-f6dce24196c7


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
        var interseting_message: TextView = findViewById(R.id.Message)

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

                    if (doubleValue < 20.0){
                        interseting_message.setText("I hope it were Warmer")
                    }else{
                        interseting_message.setText("I hope it were Colder")
                    }
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

                    if (doubleValue < 20.0){
                        interseting_message.setText("I hope it were Warmer")
                    }else{
                        interseting_message.setText("I hope it were Colder")
                    }
                }
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {}

            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        })
    }
}