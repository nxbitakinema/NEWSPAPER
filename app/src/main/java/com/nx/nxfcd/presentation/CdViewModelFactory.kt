package com.nx.nxfcd.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.nx.nxfcd.data.AppRepositoryImpl

class CdViewModelFactory(private val appRepositoryImpl: AppRepositoryImpl) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CdViewModel::class.java)) {
            return CdViewModel(appRepositoryImpl) as T
        }
        throw IllegalStateException()
    }
}