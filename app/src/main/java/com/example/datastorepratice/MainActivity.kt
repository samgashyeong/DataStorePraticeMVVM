package com.example.datastorepratice

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.datastorepratice.databinding.ActivityMainBinding
import kotlinx.coroutines.flow.flow

class MainActivity : AppCompatActivity() {
    // Context에 dataStore 속성 추가
    private lateinit var binding : ActivityMainBinding
    private lateinit var viewModel : DataStoreViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        setContentView(binding.root)
        viewModel = ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory(application))[DataStoreViewModel::class.java]

        binding.lifecycleOwner = this

        binding.testSwitch.setOnCheckedChangeListener { compoundButton, b ->
            viewModel.saveData(b)
        }
        viewModel.isSwitchOn.observe(this, Observer {
            binding.testSwitch.isChecked = it
        })
    }
}