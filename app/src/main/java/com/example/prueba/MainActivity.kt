package com.example.prueba

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ArrayAdapter
import androidx.lifecycle.Lifecycle
import com.example.prueba.core.Constants
import com.example.prueba.databinding.ActivityMainBinding
import com.example.prueba.models.Especie
import com.example.prueba.models.Fase
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {

    private val especiesList = mutableListOf(
        Especie(1, "Conejo"),
        Especie(2, "Cerdo"),
        Especie(3, "Cerda"),
        Especie(4, "Pollo"),
        Especie(5, "Cuy"),
        Especie(6, "Gallina"),
    )

    private val faseList = mutableListOf(
        Fase(
            1,
            "Cerdos Iniciacion",
            "",
            "13.00",
            "3240",
            "18.00",
            "4.00",
            "3.00",
            "4.00",
            "1.00",
            "0,3-0,4",
            "0,70",
            "0.71",
            "0.30",
            "0.58",
            "0,54",
            "0,20",
            "10,00",
            "10,00",
            "10,00",
            2
        ),
        Fase(
            2,
            "Cerdos Levante",
            "",
            "",
            "3250",
            "18,00",
            "13,00",
            "3,00",
            "0,8-1",
            "0.5-0.65",
            "0,3-0,4",
            "0,70",
            "0,72",
            "0,32",
            "0,57",
            "0,54",
            "0,20",
            "10,00",
            "10,00",
            "10,00",
            2
        ),
        Fase(
            3,
            "Cerdos Ceba",
            "",
            "",
            "3250",
            "14,00",
            "13,00",
            "3,00",
            "0,8-1",
            "0.5-0.65",
            "0,3-0,4",
            "0,70",
            "0,72",
            "0,32",
            "0,57",
            "0,54",
            "0,20",
            "10,00",
            "10,00",
            "10,00",
            2
        ),
    )

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        this.supportActionBar?.hide()

        setDisable(true)

        setUpEspecies(especiesList)
        clicks()
    }

    private fun clicks() {
        binding.actvEspecie.setOnItemClickListener { parent, view, position, id ->
            obtainFases(
                position
            )
        }
        binding.actvFase.setOnItemClickListener { parent, view, position, id ->
            val data =  faseList[position]
            Constants.faseActual = data
            setTable(data)
            setDisable(false)
        }
        binding.btnContinuar.setOnClickListener {
            if (binding.tIEDTcantidad.text.toString().isNotEmpty()){
                Constants.cantidadMezcla = binding.tIEDTcantidad.text.toString().toInt()
            }
            val i = Intent(applicationContext,Tabla2Activity::class.java)
            startActivity(i)
        }
        binding.lLProfile.setOnClickListener {
            val i = Intent(applicationContext,ProfileActivity::class.java)
            startActivity(i)
        }
    }

    private fun setTable(data: Fase) {
        binding.tvProteina.text = data.proteina
        binding.tvEnergia.text = data.e_m_ave
        binding.tvFosforo.text = data.fosf_disp
        binding.tvCalcio.text = data.calcio
        binding.tvLisina.text = data.lisina
        binding.tvMetionina.text = data.metionina
    }

    private fun obtainFases(position: Int) {
        val especie = especiesList[position]
        val list = faseList.filter { x-> x.especie_id == especie.id }

        setUpFase(list.toMutableList())

    }

    private fun setUpFase(fase: MutableList<Fase>) {
        val list = mutableListOf<String>()
        fase.forEach {
            list.add(it.nombreFase)
        }
        val adapter = ArrayAdapter(applicationContext, R.layout.list_item, list)
        binding.actvFase.setAdapter(adapter)
    }

    private fun setDisable(b: Boolean) {
        if (b){
            binding.btnContinuar.isEnabled = false
            binding.lLTable.visibility = View.GONE
        } else{
            binding.btnContinuar.isEnabled = true
            binding.lLTable.visibility = View.VISIBLE
        }
    }


    private fun setUpEspecies(especies: MutableList<Especie>) {
        val list = mutableListOf<String>()
        especies.forEach {
            list.add(it.nombreEspecie)
        }
        val adapter = ArrayAdapter(applicationContext, R.layout.list_item, list)
        binding.actvEspecie.setAdapter(adapter)
    }

}