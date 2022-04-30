package com.example.geometry

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Vibrator
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import com.example.geometry.databinding.ActivityCilindroBinding
import com.example.geometry.databinding.ActivityMainBinding

class Cilindro : AppCompatActivity() {

    private lateinit var binding: ActivityCilindroBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityCilindroBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val spinner = findViewById<Spinner>(R.id.spnElementos)
        val lista = resources.getStringArray(R.array.formulas)
        val adaptador = ArrayAdapter(this, android.R.layout.simple_spinner_item,lista)
        spinner.adapter = adaptador
        spinner.onItemSelectedListener = object:
            AdapterView.OnItemSelectedListener{
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {

                if(lista[p2] == lista[1]){//Condicional para que solo pase a Activities dependiendo de lo que se escoja en arreglo.
                    val intentArea1 = Intent(this@Cilindro,MainActivity::class.java)
                    startActivity(intentArea1)
                    finish()

                } else if(lista[p2] == lista[2]) {
                    val intentAltura1 = Intent(this@Cilindro,Cilindro::class.java)
                    startActivity(intentAltura1)
                    finish()

                } else if(lista[p2] == lista[3]) {
                    val intentAncho = Intent(this@Cilindro,Paralel::class.java)
                    startActivity(intentAncho)
                    finish()
                }
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("Not yet implemented")
            }
        }
    }

    fun click(view: View) {
        if(!binding.etNumero.text.toString().isEmpty() && !binding.etNumero2.text.toString().isEmpty() && binding.etNumero.text.toString() !="0" ){
            val radio = binding.etNumero.text.toString().toInt()
            val volumen = binding.etNumero2.text.toString().toInt()

            val altura = (volumen)/(3.1416*radio*radio)

            val intentResAltura = Intent(this, ResultadosCilindro::class.java)

            //Se pasa valor de area a activity ResultadosTriangulo
            val parametros1 = Bundle()
            parametros1.putDouble("altura",altura)
            intentResAltura.putExtras(parametros1)

            startActivity(intentResAltura) //Se pasa a activity ResultadosTriangulo en caso de presionar boton.

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

        } else if(binding.etNumero.text.toString() == "0"){
            binding.etNumero.error = getString(R.string.valor_no_puede_ser_cero)
            binding.etNumero.requestFocus()
            val vibratorService = getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
            vibratorService.vibrate(400)

        }
    }
}