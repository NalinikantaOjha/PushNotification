package com.example.pushnotification.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pushnotification.local.entity.Message
import com.example.pushnotification.local.entity.User
import com.example.pushnotification.repository.FRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FViewModel @Inject constructor(private val repository:FRepository):ViewModel(){

//    fun getMessage(id:Int): LiveData<List<Message>> {
//        return repository.getMessage(id)
//    }
//        fun addUser(user: User){
//            viewModelScope.launch (Dispatchers.IO) {
//                repository.addUser(user)
//
//            }
//    }
//        fun addMessage(message: Message){
//            viewModelScope.launch (Dispatchers.IO) {
//                repository.addMessage(message)
//            }
//    }
//
//
//     fun deleteAll() {
//         viewModelScope.launch(Dispatchers.IO) {
//             repository.deleteAll()
//         }
//     }
//
//     fun deleteAllS(id:Int) {
//         viewModelScope.launch(Dispatchers.IO) {
//             repository.deleteAllS(id)
//         }
//     }
    fun postData(){
        viewModelScope.launch (Dispatchers.IO) {
            repository.postData()
        }
    }
}