package com.example.sl3verbeterd

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
class AccountViewModel(private val repository: HireHubRepository, val image: Int) : ViewModel() {

    private val _accountData = MutableLiveData<List<Account>>()

    val accountData: LiveData<List<Account>> get() = _accountData



    fun loadAccounts() {

        viewModelScope.launch {

            _accountData.value = repository.getAllAccounts()

        }

    }
    private val _text = MutableLiveData<String>().apply {
        value = "This is notifications Fragment"
    }
    val text: LiveData<String> = _text

}