package com.example.corutinasviewmodel

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.corutinasviewmodel.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var model: MainActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Model inicializacion

        model = ViewModelProvider(this).get(MainActivityViewModel::class.java)
        downloadAll()

    }

    fun downloadAll(){
        CoroutineScope(IO).launch { // IO significa que es en segundo planos , es el scope
            val lista = model.getFilms()
            setTextOnMainThread(lista)

        }
    }

    private suspend fun setTextOnMainThread(lista: MutableList<Films>){
        withContext(Main){
            lista.forEach{
                binding.tvList.append("${it.name}\n")
            }
            binding.pbLoading.visibility = View.GONE
        }
    }

}