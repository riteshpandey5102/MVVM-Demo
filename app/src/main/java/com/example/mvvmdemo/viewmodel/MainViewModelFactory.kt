package com.example.mvvmdemo.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mvpdemo.model.MainModel


class MainViewModelFactory(val mainModel: MainModel) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(MainViewModel::class.java!!)) {
            MainViewModel(mainModel) as T
        } else {
            throw IllegalArgumentException("ViewModel Not Found")
        }
    }
}