package com.example.corutinasviewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivityViewModel: ViewModel() {
    private val films = mutableListOf<Films>()

    suspend fun getFilms(): MutableList<Films>{
        return withContext(Dispatchers.IO){
            delay(5000)
            if(films.isEmpty()){
                downloadFilms()
            }
            return@withContext films
        }

    }
    private fun downloadFilms(){
        viewModelScope.launch {
            films.addAll(
                mutableListOf(
                    Films(1, "La Amenaza Fantasma", "aaaa"),
                    Films(2, "El Ataque de los Clones", "aaaa"),
                    Films(3, "La Venganza de los Sith", "aaaa"),
                    Films(4, "Una Nueva Esperanza", "aaaa"),
                    Films(5, "El Imperio Contraataca", "aaaa"),
                    Films(6, "El Retorno del Jedi", "aaaa"),
                    Films(4, "El Despertar de la Fuerza", "aaaa"),
                    Films(5, "Los Útimos Jedi", "aaaa"),
                    Films(6, "El Ascenso de Skywalker", "aaaa")
                )
            )
        }
    }

}