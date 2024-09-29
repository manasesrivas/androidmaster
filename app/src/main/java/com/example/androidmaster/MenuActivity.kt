package com.example.androidmaster

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

import com.example.androidmaster.firstapp.FirstAppActivity
import com.example.androidmaster.imccalculator.imcCalculatorActivity
import com.example.androidmaster.settings.SettingsActivity
import com.example.androidmaster.superheroapp.SuperHeroListActivity
import com.example.androidmaster.todoapp.TodoActivity

class MenuActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_menu)
        val btnSaludApp = findViewById<Button>(R.id.btnSaludApp)
        val btnIMCApp = findViewById<Button>(R.id.btnIMCApp)
        val btnTODO = findViewById<Button>(R.id.btnTODO)
        val btnSuperhero=findViewById<Button>(R.id.btnSuperhero)
        val btnSetting=findViewById<Button>(R.id.btnSettings)


        btnSaludApp.setOnClickListener{ navigateToSaludApp() }
        btnIMCApp.setOnClickListener{ navigateToIMCApp()}
        btnTODO.setOnClickListener{ navigateTOTodoApp() }
        btnSuperhero.setOnClickListener{ navigateToSuperhero() }
        btnSetting.setOnClickListener{ navigateToSettings() }
    }

    private fun navigateToSettings(){
        val intent=Intent(this, SettingsActivity::class.java)
        startActivity(intent)
    }

    private fun navigateToSuperhero(){
        val intent=Intent(this, SuperHeroListActivity::class.java)
        startActivity(intent)
    }

    private fun navigateTOTodoApp() {
        val intent = Intent(this, TodoActivity::class.java)
        startActivity(intent)
    }

    private fun navigateToSaludApp(){
        val intent = Intent(this, FirstAppActivity::class.java)
        startActivity(intent)
    }
    private fun navigateToIMCApp(){
        val intent = Intent(this, imcCalculatorActivity::class.java)
        startActivity(intent)
    }
}