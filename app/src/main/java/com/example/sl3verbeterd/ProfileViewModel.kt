package com.example.sl3verbeterd

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
class ProfileViewModel(private val repository: HireHubRepository) : ViewModel()  {

    private val _profileData = MutableLiveData<List<Profile>>()

    val profileData: LiveData<List<Profile>> get() = _profileData



    fun loadProfiles() {

        viewModelScope.launch {

            _profileData.value = repository.getAllProfiles()

        }

    }

}