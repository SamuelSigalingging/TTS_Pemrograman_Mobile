package com.example.ttuaplication

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {
    private val _nama = MutableLiveData<String>()
    val nama: LiveData<String> = _nama

    private val _nim = MutableLiveData<String>()
    val nim: LiveData<String> = _nim

    private val _ipk = MutableLiveData<String>()
    val ipk: LiveData<String> = _ipk

    fun setProfile(nama: String, nim: String, ipk: String) {
        _nama.value = nama
        _nim.value = nim
        _ipk.value = ipk
    }
}