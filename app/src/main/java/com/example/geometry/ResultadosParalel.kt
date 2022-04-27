package com.example.geometry

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import com.example.geometry.databinding.ActivityResultadosParalelBinding
import kotlin.math.roundToInt

class ResultadosParalel : AppCompatActivity() {
    private lateinit var binding1: ActivityResultadosParalelBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding1 = ActivityResultadosParalelBinding.inflate(layoutInflater)
        setContentView(binding1.root)

        val spinner1 = findViewById<Spinner>(R.id.spnElementos)
        val lista1 = resources.getStringArray(R.array.formulas)
        val adaptador1 = ArrayAdapter(this, android.R.layout.simple_spinner_item,lista1)
        spinner1.adapter = adaptador1
        spinner1.onItemSelectedListener = object:
            AdapterView.OnItemSelectedListener{
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {

                if(lista1[p2] == lista1[1]){//Condicional para que solo pase a Activities dependiendo de lo que se escoja en arreglo.
                    val intentArea2 = Intent(this@ResultadosParalel, MainActivity::class.java)
                    startActivity(intentArea2)
                    finish()

                }else if(lista1[p2] == lista1[2]) {
                    val intentAltura2 = Intent(this@ResultadosParalel,Cilindro::class.java)
                    startActivity(intentAltura2)
                    finish()

                } else if(lista1[p2] == lista1[3]) {
                    val intentAltura2 = Intent(this@ResultadosParalel,Paralel::class.java)
                    startActivity(intentAltura2)
                    finish()

                }
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("Not yet implemented")
            }
        }

        val bundle = intent.extras //Variable que importa resultado
        val volumen = bundle?.getDouble("volumen")
        val volumen1 = volumen!!
        val volumen2 = (volumen1 * 100.0).roundToInt() / 100.0

        binding1.tvArea1.visibility = View.VISIBLE
        binding1.tvArea1.text = getString(R.string.El_volumen_de_piramide_es) + " $volumen2 " + getString(R.string.metros_cubicos)
    }
}