package com.example.sl3verbeterd.ui.applicant

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.sl3verbeterd.HireHubDao
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*

@OptIn(ExperimentalCoroutinesApi::class)
class ApplicantsViewModel(
//    private val hireHubDao: HireHubDao
//    , private val repository: HireHubRepository
) : ViewModel() {



//    val allProfiles: LiveData<List<Profile>> = repository.allProfiles.asLiveData()
//
//    fun insertProfile(profile: Profile) = viewModelScope.launch {
//        repository.insertProfile(profile)
//    }

//    private val _sortType = MutableStateFlow(SortType.FIRST_NAME)
//    private val _profiles = _sortType
//        .flatMapLatest { sortType ->
//            when(sortType) {
//                SortType.FIRST_NAME -> hireHubDao.getProfilesOrderedByFirstName()
//                SortType.LAST_NAME -> hireHubDao.getProfilesOrderedByLastName()
//                SortType.PHONE_NUMBER -> hireHubDao.getProfilesOrderedByPhoneNumber()
//            }
//        }
//        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(), emptyList())
//
//    private val _state = MutableStateFlow(ProfileState())
//    val state = combine(_state, _sortType, _profiles) { state, sortType, profiles ->
//        state.copy(
//            profiles = profiles,
//            sortType = sortType
//        )
//    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), ProfileState())
//
//    fun onEvent(event: ProfileEvent) {
//        when(event) {
//            is ProfileEvent.DeleteProfile -> {
//                viewModelScope.launch {
//                    hireHubDao.deleteProfile(event.profile)
//                }
//            }
//
//
//
//            // This is where the Update Function gets trigger when you press the update button
//            // Which
//            is ProfileEvent.ShowUpdateProfile ->  {
//
//
//                _state.update { it.copy(
//
//                    isUpdatingProfile = true,
//                    firstName = event.profile.firstName,
//                    lastName = event.profile.lastName,
//                    phoneNumber = event.profile.phoneNumber,
//                    id = event.profile.id.toString()
//
//                ) }
//            }
//            //
//
//            ProfileEvent.HideDialog -> {
//                _state.update { it.copy(
//                    isAddingProfile = false
//                ) }
//            }
//
//            ProfileEvent.HideUpdateProfile -> {
//                _state.update { it.copy(
//                    isUpdatingProfile = false
//                ) }
//            }
//
//            ProfileEvent.SaveProfile -> {
//                val firstName = state.value.firstName
//                val lastName = state.value.lastName
//                val phoneNumber = state.value.phoneNumber
//
//                if(firstName.isBlank() || lastName.isBlank() || phoneNumber.isBlank()) {
//                    return
//                }
//
//                val profile = Profile(
//                    firstName = firstName,
//                    lastName = lastName,
//                    phoneNumber = phoneNumber
//                )
//                viewModelScope.launch {
//                    hireHubDao.upsertProfile(profile)
//                }
//                _state.update { it.copy(
//                    isAddingProfile = false,
//                    firstName = "",
//                    lastName = "",
//                    phoneNumber = ""
//                ) }
//            }
//
//            //UPDATE
//            //UPDATE
//            //UPDATE
//
//            ProfileEvent.UpdateTheProfile -> {
//                val firstName = state.value.firstName
//                val lastName = state.value.lastName
//                val phoneNumber = state.value.phoneNumber
//                val id = state.value.id.toInt()
//
//                if(firstName.isBlank() || lastName.isBlank() || phoneNumber.isBlank()) {
//                    return
//                }
//
////                val contact = Profile(
////                    firstName = "Yep",
////                    lastName = lastName,
////                    phoneNumber = "Lol",
////                    id = 49
////                )
//
//                viewModelScope.launch {
//                    hireHubDao.updateProfile(firstName, id)
//                }
//
//                _state.update { it.copy(
//                    isUpdatingProfile = false,
//                    firstName = "",
//                    lastName = "",
//                    phoneNumber = ""
//                ) }
//            }
//
//
//
//
//
//
//            is ProfileEvent.SetFirstName -> {
//                _state.update { it.copy(
//                    firstName = event.firstName
//                ) }
//            }
//            is ProfileEvent.SetLastName -> {
//                _state.update { it.copy(
//                    lastName = event.lastName
//                ) }
//            }
//            is ProfileEvent.SetPhoneNumber -> {
//                _state.update { it.copy(
//                    phoneNumber = event.phoneNumber
//                ) }
//            }
//            ProfileEvent.ShowDialog -> {
//                _state.update { it.copy(
//                    isAddingProfile = true
//                ) }
//            }
//            is ProfileEvent.SortProfiles -> {
//                _sortType.value = event.sortType
//            }
//        }
//    }

    private val _text = MutableLiveData<String>().apply {
        value = "This is applicants Fragment"
    }
    val text: LiveData<String> = _text

}

//class ProfileViewModelFactory(private val repository: HireHubRepository) : ViewModelProvider.Factory {
//    override fun <T : ViewModel> create(modelClass: Class<T>): T {
//        if (modelClass.isAssignableFrom(ProfileViewModel::class.java)) {
//            @Suppress("UNCHECKED_CAST")
//            return ProfileViewModel(repository) as T
//        }
//        throw IllegalArgumentException("Unknown ViewModel class")
//    }
//}