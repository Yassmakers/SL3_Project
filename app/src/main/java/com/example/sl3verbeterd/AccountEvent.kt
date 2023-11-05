package com.example.sl3verbeterd



// This file is used to handle events happening on the UI screen
sealed interface AccountEvent {
    object SaveAccount: AccountEvent
    object UpdateTheAccount: AccountEvent
    object HideUpdateAccount: AccountEvent
    data class SetFirstName(val firstName: String): AccountEvent
    data class SetLastName(val lastName: String): AccountEvent
    data class SetPhoneNumber(val phoneNumber: String): AccountEvent
    object ShowDialog: AccountEvent
    object HideDialog: AccountEvent
    data class ShowUpdateAccount(val account: Account): AccountEvent
    data class SortAccounts(val sortType: SortType): AccountEvent
    data class DeleteAccount(val account: Account): AccountEvent



}