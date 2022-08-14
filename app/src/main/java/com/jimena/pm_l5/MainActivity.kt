package com.jimena.pm_l5

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import com.google.android.material.textfield.TextInputLayout
import com.jimena.corecomponents.data.Res



class MainActivity : AppCompatActivity() {
    //Declaracion de variables
    lateinit var btnToDetailsActivity: Button
    lateinit var btnToastmssg: Button //lateinit es para que no se genere un error de que no se ha inicializado la variable
    lateinit var btnTerminarjornada: Button
    lateinit var btnDescargar: Button
    lateinit var btnNavigate: ImageView


    override fun onCreate(savedInstanceState: Bundle?) { //onCreate es el primer metodo que se ejecuta al iniciar la actividad
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btnToastmssg = findViewById(R.id.button3)
        btnTerminarjornada= findViewById(R.id.button_MainActivity_Terminarjornada)
        btnToDetailsActivity=findViewById(R.id.ToDetailsAct)
        btnDescargar=findViewById(R.id.button2)
        btnNavigate=findViewById(R.id.direccion)
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
        btnDescargar.setOnClickListener {
            //abrir
            val app= "https://play.google.com/store/apps/details?id=com.instagram.android"
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse(app)
            startActivity(intent)
        }
        btnNavigate.setOnClickListener {
            // Explicito. Estamos pidiendo que use google maps
            val location = "https://www.google.com/maps/place/Nacionsushi+%7C+Forum+Zona+Viva/@14.6024927,-90.5154333,17z/data=!3m1!4b1!4m5!3m4!1s0x8589a35101321ac1:0x7cd6ffe1e9586b6e!8m2!3d14.6024875!4d-90.5132446"
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(location))
            startActivity(intent)
        }
    }
}