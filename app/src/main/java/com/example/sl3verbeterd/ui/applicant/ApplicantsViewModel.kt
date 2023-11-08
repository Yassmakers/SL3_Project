package com.example.sl3verbeterd.ui.applicant
import com.example.sl3verbeterd.ui.applicant.ApplicantsViewModelFactory
import androidx.appcompat.app.AppCompatActivity
import androidx.activity.viewModels
import com.example.sl3verbeterd.ui.applicant.ApplicantsViewModel


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.sl3verbeterd.Account
import com.example.sl3verbeterd.HireHubDao
import com.example.sl3verbeterd.HireHubRepository
import com.example.sl3verbeterd.Profile
import com.example.sl3verbeterd.ui.auth.AccountState
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

@OptIn(ExperimentalCoroutinesApi::class)
class ApplicantsViewModel(private val repository: HireHubRepository) : ViewModel() {
    private val _loginSuccess = MutableLiveData<Boolean>()
    val loginSuccess: LiveData<Boolean> get() = _loginSuccess

    private val _userRole = MutableLiveData<String>()
    val userRole: LiveData<String> get() = _userRole

    fun loginUser(username: String, password: String) {
        viewModelScope.launch {
            val user = repository.getUserByUsername(username)
            _loginSuccess.value = user != null && user.password == password
            if (_loginSuccess.value == true) {
                _userRole.value = user?.role ?: "guest"
            } else {
                _userRole.value = "guest"
            }
        }
    }
    private val state = MutableStateFlow(AccountState())

    val allProfiles: LiveData<List<Profile>> = repository.allProfiles.asLiveData()
//    val allAccounts: LiveData<List<Account>> = repository.allAccounts.asLiveData()

    fun insertProfile(profile: Profile) = viewModelScope.launch {
        repository.insertProfile(profile)

    }

    fun insertAccount(account: Account) = viewModelScope.launch {
        repository.insertAccount(account)

    }

    fun showProfile(id: Int) = viewModelScope.launch {
        repository.showProfile(id)

    }

    fun deleteProfile(profile: Profile) = viewModelScope.launch {
        repository.deleteProfile(profile)
    }

    fun deleteAccount(account: Account) = viewModelScope.launch {
        repository.deleteAccount(account)
    }

    fun deleteAccountToo(id: Int) = viewModelScope.launch {
        repository.deleteAccountToo(id)
    }

    fun updateProfile(profile: Profile) = viewModelScope.launch {
        repository.updateProfile(profile)
    }

    fun updateAccount(account: Account) = viewModelScope.launch {
        repository.updateAccount(account)
    }

    suspend fun getUserByUsername(username: String) = viewModelScope.launch {
        repository.getUserByUsername(username)
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