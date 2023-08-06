package com.nx.nxfcd.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nx.nxfcd.data.AppRepositoryImpl
import com.nx.nxfcd.domain.model.Response
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class CdViewModel(
    private val appRepositoryImpl: AppRepositoryImpl
): ViewModel() {

    val booksStateFlow = MutableStateFlow<Response?>(null)

    init {
        viewModelScope.launch {
            appRepositoryImpl.getDataFromFirestore().collect {
                booksStateFlow.value = it
            }
        }
    }
}