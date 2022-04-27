package com.example.geometry

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.geometry.databinding.ActivityResultadosTrianguloBinding

class ResultadosTriangulo : AppCompatActivity() {

    private lateinit var binding1: ActivityResultadosTrianguloBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding1 = ActivityResultadosTrianguloBinding.inflate(layoutInflater)
        setContentView(binding1.root)

        val spinner1 = findViewById<Spinner>(R.id.spnElementos)
        val lista1 = resources.getStringArray(R.array.formulas)
        val adaptador1 = ArrayAdapter(this, android.R.layout.simple_spinner_item,lista1)
        spinner1.adapter = adaptador1
        spinner1.onItemSelectedListener = object:

            AdapterView.OnItemSelectedListener{
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {

                if(lista1[p2] == lista1[1]){//Condicional para que solo pase a Activities dependiendo de lo que se escoja en arreglo.
                    val intentArea4 = Intent(this@ResultadosTriangulo, MainActivity::class.java)
                    startActivity(intentArea4)
                    finish()

                }else if(lista1[p2] == lista1[2]) {
                    val intentAltura4 = Intent(this@ResultadosTriangulo,Cilindro::class.java)
                    startActivity(intentAltura4)
                    finish()

                } else if(lista1[p2] == lista1[3]) {
                    val intentAltura4 = Intent(this@ResultadosTriangulo,Paralel::class.java)
                    startActivity(intentAltura4)
                    finish()

                }
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("Not yet implemented")
            }
        }

        val bundle = intent.extras //Variable que importa resultado de MainActivity
        val area = bundle?.getDouble("area")

        binding1.tvArea1.visibility = View.VISIBLE
        binding1.tvArea1.text = getString(R.string.el_area_del_triangulo_es) + " $area " + getString(R.string.metros_cuadrados)

    }
}