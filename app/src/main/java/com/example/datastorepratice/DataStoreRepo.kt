package com.example.datastorepratice

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.dataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map


class DataStoreRepo(private val context: Context) {

    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore("datastore")


    suspend fun getPhoneBook() : Flow<Boolean?> = context.dataStore.data.map { it->
        it[DataStoreKey.IS_SWITCH_ON]
    }

    suspend fun saveSWITCH(switch : Boolean){
        context.dataStore.edit { it->
            it[DataStoreKey.IS_SWITCH_ON] = switch
        }
    }
}