package com.example.androidmaster.imccalculator

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.androidmaster.R
import com.example.androidmaster.imccalculator.imcCalculatorActivity.Companion.IMC_KEY

class ResultIMCActivity : AppCompatActivity() {

    private lateinit var tvResult: TextView
    private lateinit var tvIMC: TextView
    private lateinit var tvDescription: TextView
    private lateinit var btnReCalculate: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_result_imcactivity)


        val result:Double = intent.extras?.getDouble(IMC_KEY) ?: -1.0
        initComponents()
        initUI(result)
        initListeners()
    }

    private fun initListeners(){
        btnReCalculate.setOnClickListener{
            onBackPressed()
        }
    }

    private fun initUI(result: Double){
        tvIMC.text=result.toString()
        when(result){
            in 0.00..18.50 -> { // bajo peso
                tvResult.setTextColor(ContextCompat.getColor(this, R.color.peso_bajo))
                tvResult.text=getString(R.string.title_bajo_peso)
                tvDescription.text=getString(R.string.description_bajo_peso)
            }
            in 18.51..24.99 -> { // peso normal
                tvResult.setTextColor(ContextCompat.getColor(this, R.color.peso_normal))

                tvResult.text=getString(R.string.title_peso_normal)
                tvDescription.text=getString(R.string.description_peso_normal)
            }
            in 25.00..29.99 -> { // sobrepeso
                tvResult.setTextColor(ContextCompat.getColor(this, R.color.sobrepeso))
                tvResult.text=getString(R.string.title_sobrepeso)
                tvDescription.text=getString(R.string.description_sobrepeso)
            }
            in 30.00..99.00 -> { // obecidad
                tvResult.setTextColor(ContextCompat.getColor(this, R.color.obecidad))
                tvResult.text=getString(R.string.title_obecidad)
                tvDescription.text=getString(R.string.description_obecidad)
            }
            else->{ // error
                tvIMC.text=getString(R.string.error)
                tvResult.text=getString(R.string.error)
                tvDescription.text=getString(R.string.error)
            }
        }
    }

    private fun initComponents(){
        tvResult=findViewById(R.id.tvResult)
        tvIMC=findViewById(R.id.tvIMC)
        tvDescription=findViewById(R.id.tvDescription)
        btnReCalculate=findViewById(R.id.btnReCalculate)
    }


}