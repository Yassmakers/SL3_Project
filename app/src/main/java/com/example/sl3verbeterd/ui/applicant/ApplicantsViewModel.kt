package com.example.sl3verbeterd.ui.applicant

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.sl3verbeterd.HireHubDao
import com.example.sl3verbeterd.HireHubRepository
import com.example.sl3verbeterd.Profile
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

@OptIn(ExperimentalCoroutinesApi::class)
class ApplicantsViewModel(
     private val repository: HireHubRepository
) : ViewModel() {



    val allProfiles: LiveData<List<Profile>> = repository.allProfiles.asLiveData()

    fun insertProfile(profile: Profile) = viewModelScope.launch {
        repository.insertProfile(profile)

    }

    fun showProfile(id: Int) = viewModelScope.launch {
        repository.showProfile(id)

    }

    fun deleteProfile(profile: Profile) = viewModelScope.launch {
        repository.deleteProfile(profile)
    }

    fun updateProfile(profile: Profile) = viewModelScope.launch {
        repository.updateProfile(profile)
    }



    private val _text = MutableLiveData<String>().apply {
        value = "This is applicants Fragment"
    }
    val text: LiveData<String> = _text

}

class ApplicantsViewModelFactory(private val repository: HireHubRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ApplicantsViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ApplicantsViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}