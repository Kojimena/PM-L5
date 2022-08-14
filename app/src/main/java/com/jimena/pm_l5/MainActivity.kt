package com.jimena.pm_l5

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.google.android.material.textfield.TextInputLayout
import com.jimena.corecomponents.data.Res



class MainActivity : AppCompatActivity() {
    //Declaracion de variables
    lateinit var btnToDetailsActivity: Button
    lateinit var btnToastmssg: Button //lateinit es para que no se genere un error de que no se ha inicializado la variable
    lateinit var btnTerminarjornada: Button


    override fun onCreate(savedInstanceState: Bundle?) { //onCreate es el primer metodo que se ejecuta al iniciar la actividad
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btnToastmssg = findViewById(R.id.button3)
        btnTerminarjornada= findViewById(R.id.button_MainActivity_Terminarjornada)
        btnToDetailsActivity=findViewById(R.id.ToDetailsAct)
        initListeners()
    }
    //Inicializacion de listeners
    fun initListeners() {

        btnToastmssg.setOnClickListener {
            Toast.makeText(this, getString(R.string.name), Toast.LENGTH_LONG).show()
        }
        btnTerminarjornada.setOnClickListener {
            Toast.makeText(this, "Jornada finalizada", Toast.LENGTH_LONG).show()
        }
        btnToDetailsActivity.setOnClickListener {
            val nombrerestaurante= getString(R.string.nombreres)
            val direccionrestaurante= getString(R.string.Lugarcomercial)
            val horariorestaurante= getString(R.string.hora)
            val res= Res(nombrerestaurante,direccionrestaurante,horariorestaurante)

            val intent = Intent(this, DetailsActivity::class.java)
            intent.putExtra("EXTRA_RESTAURANTE", res)
            startActivity(intent)
        }
    }
}