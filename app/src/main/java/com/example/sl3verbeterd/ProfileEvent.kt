package com.example.sl3verbeterd



// This file is used to handle events happening on the UI screen
sealed interface ProfileEvent {
    object SaveProfile: ProfileEvent
    object UpdateTheProfile: ProfileEvent
    object HideUpdateProfile: ProfileEvent
    data class SetFirstName(val firstName: String): ProfileEvent
    data class SetLastName(val lastName: String): ProfileEvent
    data class SetPhoneNumber(val phoneNumber: String): ProfileEvent
    object ShowDialog: ProfileEvent
    object HideDialog: ProfileEvent
    data class ShowUpdateProfile(val profile: Profile): ProfileEvent
    data class SortProfiles(val sortType: SortType): ProfileEvent
    data class DeleteProfile(val profile: Profile): ProfileEvent



}