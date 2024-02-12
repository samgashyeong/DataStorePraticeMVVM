package com.example.datastorepratice

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class DataStoreViewModel(application: Application, val isSwitchOn : MutableLiveData<Boolean>) : AndroidViewModel(application){
    private val dataRepo = DataStoreRepo(application)


    fun getData(){
        GlobalScope.launch(Dispatchers.IO) {
            dataRepo.getPhoneBook().collect(){
                isSwitchOn.postValue(it)
            }
        }
    }
    fun saveData(isSwitchOn: Boolean){
        GlobalScope.launch(Dispatchers.IO) {
            dataRepo.saveSWITCH(isSwitchOn)
        }
    }



}