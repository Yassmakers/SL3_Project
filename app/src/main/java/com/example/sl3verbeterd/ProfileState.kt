package com.example.sl3verbeterd

data class ProfileState(
    val profiles: List<Profile> = emptyList(),
    val id: String = "",
    val firstName: String = "",
    val lastName: String = "",
    val phoneNumber: String = "",
    val isAddingProfile: Boolean = false,
    val isUpdatingProfile: Boolean = false,
    val sortType: SortType = SortType.FIRST_NAME
)
