package com.example.bmiviewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class BmiViewModel : ViewModel() {

    private val _height = MutableLiveData<String>("")
    val height: LiveData<String> = _height

    private val _weight = MutableLiveData<String>("")
    val weight: LiveData<String> = _weight

    private val _bmiResult = MutableLiveData<String>("")
    val bmiResult: LiveData<String> = _bmiResult

    fun updateHeight(newHeight: String) {
        _height.value = newHeight
        calculateBmi()
    }

    fun updateWeight(newWeight: String) {
        _weight.value = newWeight
        calculateBmi()
    }

    private fun calculateBmi() {
        val heightVal = _height.value?.toDoubleOrNull() ?: return
        val weightVal = _weight.value?.toDoubleOrNull() ?: return
        val bmi = weightVal / (heightVal * heightVal)
        _bmiResult.value = String.format("%.2f", bmi)
    }
}