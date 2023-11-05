package com.example.sl3verbeterd

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

@OptIn(ExperimentalCoroutinesApi::class)
class AccountViewModel(
    private val dao: AccountDao
): ViewModel() {

    private val _sortType = MutableStateFlow(SortType.FIRST_NAME)
    private val _accounts = _sortType
        .flatMapLatest { sortType ->
            when(sortType) {
                SortType.FIRST_NAME -> dao.getAccountsOrderedByFirstName()
                SortType.LAST_NAME -> dao.getAccountsOrderedByLastName()
                SortType.PHONE_NUMBER -> dao.getAccountsOrderedByPhoneNumber()
            }
        }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(), emptyList())

    private val _state = MutableStateFlow(AccountState())
    val state = combine(_state, _sortType, _accounts) { state, sortType, accounts ->
        state.copy(
            accounts = accounts,
            sortType = sortType
        )
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), AccountState())

    fun onEvent(event: AccountEvent) {
        when(event) {
            is AccountEvent.DeleteAccount -> {
                viewModelScope.launch {
                    dao.deleteAccount(event.account)
                }
            }



            // This is where the Update Function gets trigger when you press the update button
            // Which
            is AccountEvent.ShowUpdateAccount ->  {


                _state.update { it.copy(

                    isUpdatingAccount = true,
                    firstName = event.account.firstName,
                    lastName = event.account.lastName,
                    phoneNumber = event.account.phoneNumber,
                    id = event.account.id.toString()

                ) }
            }
            //

            AccountEvent.HideDialog -> {
                _state.update { it.copy(
                    isAddingAccount = false
                ) }
            }

            AccountEvent.HideUpdateAccount -> {
                _state.update { it.copy(
                    isUpdatingAccount = false
                ) }
            }

            AccountEvent.SaveAccount -> {
                val firstName = state.value.firstName
                val lastName = state.value.lastName
                val phoneNumber = state.value.phoneNumber

                if(firstName.isBlank() || lastName.isBlank() || phoneNumber.isBlank()) {
                    return
                }

                val account = Account(
                    firstName = firstName,
                    lastName = lastName,
                    phoneNumber = phoneNumber
                )
                viewModelScope.launch {
                    dao.upsertAccount(account)
                }
                _state.update { it.copy(
                    isAddingAccount = false,
                    firstName = "",
                    lastName = "",
                    phoneNumber = ""
                ) }
            }

            //UPDATE
            //UPDATE
            //UPDATE

            AccountEvent.UpdateTheAccount -> {
                val firstName = state.value.firstName
                val lastName = state.value.lastName
                val phoneNumber = state.value.phoneNumber
                    val id = state.value.id.toInt()

                if(firstName.isBlank() || lastName.isBlank() || phoneNumber.isBlank()) {
                    return
                }

//                val contact = Account(
//                    firstName = "Yep",
//                    lastName = lastName,
//                    phoneNumber = "Lol",
//                    id = 49
//                )

                    viewModelScope.launch {
                        dao.updateAccount(firstName, id)
                    }

                _state.update { it.copy(
                    isUpdatingAccount = false,
                    firstName = "",
                    lastName = "",
                    phoneNumber = ""
                ) }
            }






            is AccountEvent.SetFirstName -> {
                _state.update { it.copy(
                    firstName = event.firstName
                ) }
            }
            is AccountEvent.SetLastName -> {
                _state.update { it.copy(
                    lastName = event.lastName
                ) }
            }
            is AccountEvent.SetPhoneNumber -> {
                _state.update { it.copy(
                    phoneNumber = event.phoneNumber
                ) }
            }
            AccountEvent.ShowDialog -> {
                _state.update { it.copy(
                    isAddingAccount = true
                ) }
            }
            is AccountEvent.SortAccounts -> {
                _sortType.value = event.sortType
            }
        }
    }
}