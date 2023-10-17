package com.example.myapplicationtest.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ZinaViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is zina Fragment"
    }
    val text: LiveData<String> = _text
}