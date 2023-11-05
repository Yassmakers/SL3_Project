package com.example.sl3verbeterd

data class AccountState(
    val accounts: List<Account> = emptyList(),
    val id: String = "",
    val firstName: String = "",
    val lastName: String = "",
    val phoneNumber: String = "",
    val isAddingAccount: Boolean = false,
    val isUpdatingAccount: Boolean = false,
    val sortType: SortType = SortType.FIRST_NAME
)
