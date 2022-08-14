package com.jimena.pm_l5

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.app.ActivityCompat
import com.google.android.material.button.MaterialButton
import com.google.android.material.snackbar.Snackbar
import com.jimena.corecomponents.data.Res
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat



class DetailsActivity : AppCompatActivity() {
    private lateinit var nombre: TextView
    private lateinit var direc: TextView
    private lateinit var hora: TextView
    private lateinit var buttonCall: MaterialButton
    private lateinit var buttonCamera: MaterialButton
    private lateinit var rootLayout: ConstraintLayout

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        nombre = findViewById(R.id.nombrerestaurante)
        direc = findViewById(R.id.direccionrestaurante)
        hora = findViewById(R.id.horariorestaurante)
        buttonCall = findViewById(R.id.buttoncall)
        buttonCamera = findViewById(R.id.permissionActivity_camera)
        rootLayout = findViewById(R.id.root_detailsActivity)

        // Cuando obtienen un objeto de tipo serializable, deben castearlo antes.
        // Deben asegurarse que el name "EXTRA_USER" sea el mismo que el que usaron en el acitvity previa
        val res: Res = intent.getSerializableExtra("EXTRA_RESTAURANTE") as Res
        nombre.text = "${res.nombre}"
        direc.text = "${res.direccion}"
        hora.text = "${res.horario}"

        setListeners()
    }

    override fun onRequestPermissionsResult(
        requestCode: Int, // El codigo que mandamos nosotros en el metodo requestPermissions
        permissions: Array<out String>, // Estos son los permisos que solicitamos
        grantResults: IntArray // Aqui vienen los valores (GRANTED o DENIED) de los N permisos que solicitamos
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        // Usamos el requestCode que enviamos en requestPermissions, en nuestro caso fue 0
        if (requestCode == 0 && grantResults.isNotEmpty()) {
            for (i in grantResults.indices) {
                if (grantResults[i] == PackageManager.PERMISSION_GRANTED) {
                    Log.d("TAG", "${permissions[i]} was granted")
                }
            }
        }
    }

    private fun setListeners() {
        buttonCamera.setOnClickListener {
            checkCameraPermission()
        }
        buttonCall.setOnClickListener {
            val phoneNum = "+50223146666"
            val intent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:$phoneNum"))
            startActivity(intent)
        }
    }

    // Método que retorna true en caso el usuario ya haya aceptado el permiso. False si no.
    private fun hasCameraPermission() =
        ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED

    private fun checkCameraPermission() {
        if (!hasCameraPermission()) {
            checkRequestRationale()
        } else {
            Toast.makeText(this, "Permiso otorgado, abrir camara", Toast.LENGTH_LONG).show()
        }
    }

    private fun checkRequestRationale() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            // Verificamos si debemos mostrar algun mensale racional
            if (shouldShowRequestPermissionRationale(Manifest.permission.CAMERA)) {

                // Si debemos mostrar un mensaje, lo mostramos
                Snackbar.make(rootLayout, "Necesitamos permisos para acceder a la camara", Snackbar.LENGTH_INDEFINITE)
                    .setAction("OK") {
                        requestPermissions(arrayOf(Manifest.permission.CAMERA), 0)
                    }.show()
            } else {
                // Si no debemos mostrar un mensaje, solicitamos permisos
                requestCameraPermission()
            }
        }
    }

    private fun requestCameraPermission() {
        ActivityCompat.requestPermissions(
            this, // Siempre deben poner el contexto del activity
            arrayOf(Manifest.permission.CAMERA), // La lista con 1 o mas permisos a solicitar
            0 // Codigo que usan en onRequestPermissionsResult
        )
    }

}