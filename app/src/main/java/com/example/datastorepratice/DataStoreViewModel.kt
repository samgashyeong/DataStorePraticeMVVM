package com.example.datastorepratice

import android.app.Application
import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class DataStoreViewModel(application: Application) : AndroidViewModel(application){
    private val dataRepo = DataStoreRepo(application)
    var isSwitchOn : MutableLiveData<Boolean> = MutableLiveData()

    init {
        getData()
    }

    class Factory(val application: Application) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return DataStoreViewModel(application) as T
        }
    }

    fun getData(){
        GlobalScope.launch(Dispatchers.IO) {
            dataRepo.getPhoneBook().collect(){
                Log.d(TAG, "getData: ${it}")
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