package com.jimena.pm_l5

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.jimena.corecomponents.data.Res


class DetailsActivity : AppCompatActivity() {
    private lateinit var nombre: TextView
    private lateinit var direc: TextView
    private lateinit var hora: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)
        nombre = findViewById(R.id.nombrerestaurante)
        direc = findViewById(R.id.direccionrestaurante)
        hora = findViewById(R.id.horariorestaurante)
        // Cuando obtienen un objeto de tipo serializable, deben castearlo antes.
        // Deben asegurarse que el name "EXTRA_USER" sea el mismo que el que usaron en el acitvity previa
        val res: Res = intent.getSerializableExtra("EXTRA_RESTAURANTE") as Res
        nombre.text = "${res.nombre}"
        direc.text = "${res.direccion}"
        hora.text = "${res.horario}"



    }
}