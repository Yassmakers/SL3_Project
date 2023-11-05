package com.example.sl3verbeterd.ui.applicant

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ApplicantsViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "Sollicitanten"
    }
    val text: LiveData<String> = _text
}