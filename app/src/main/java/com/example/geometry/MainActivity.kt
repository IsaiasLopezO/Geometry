package com.example.geometry

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Vibrator
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.geometry.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val spinner = findViewById<Spinner>(R.id.spnElementos)
        val lista = resources.getStringArray(R.array.formulas)
        val adaptador = ArrayAdapter(this, android.R.layout.simple_spinner_item,lista)
        spinner.adapter = adaptador
        spinner.onItemSelectedListener = object:

            AdapterView.OnItemSelectedListener{
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {

               if(lista[p2] == lista[1]){//Condicional para que solo pase a Activities dependiendo de lo que se escoja en arreglo.
                    val intentArea3 = Intent(this@MainActivity,MainActivity::class.java)
                    startActivity(intentArea3)
                    finish()

                } else if(lista[p2] == lista[2]) {
                    val intentAltura3 = Intent(this@MainActivity,Cilindro::class.java)
                    startActivity(intentAltura3)
                    finish()

                } else if(lista[p2] == lista[3]) {
                    val intentAncho = Intent(this@MainActivity,Paralel::class.java)
                    startActivity(intentAncho)
                }
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("Not yet implemented")
            }
        }
    }

    fun click(view: View) {
        if(!binding.etNumero.text.toString().isEmpty() && !binding.etNumero2.text.toString().isEmpty()){
            val base = binding.etNumero.text.toString().toInt()
            val altura = binding.etNumero2.text.toString().toInt()

            val area = (base*altura)*0.5
            val intentResArea = Intent(this, ResultadosTriangulo::class.java)

            //Se pasa valor de area a activity ResultadosTriangulo
            val parametros1 = Bundle()
            parametros1.putDouble("area",area)
            intentResArea.putExtras(parametros1)

            startActivity(intentResArea) //Se pasa a activity ResultadosTriangulo en caso de presionar boton.

        }else if (binding.etNumero.text.toString().isEmpty() && !binding.etNumero2.text.toString().isEmpty()){
            binding.etNumero.error = getString(R.string.valor_requerido)
            binding.etNumero.requestFocus()
            val vibratorService = getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
            vibratorService.vibrate(400)

        } else if (binding.etNumero2.text.toString().isEmpty() && !binding.etNumero.text.toString().isEmpty()){
            binding.etNumero2.error = getString(R.string.valor_requerido)
            binding.etNumero2.requestFocus()
            val vibratorService = getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
            vibratorService.vibrate(400)

        } else if (binding.etNumero.text.toString().isEmpty() && binding.etNumero2.text.toString().isEmpty()) {
            Toast.makeText(this, getString(R.string.ambos_campos_se_encuentran_vacios), Toast.LENGTH_SHORT).show()
            val vibratorService = getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
            vibratorService.vibrate(400)

        }
    }
}