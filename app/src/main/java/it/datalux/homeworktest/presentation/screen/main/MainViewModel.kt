package it.datalux.homeworktest.presentation.screen.main

import android.app.Application
import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import it.datalux.homeworktest.core.shared.checkInternetConnection
import it.datalux.homeworktest.core.utils.AppAlert
import it.datalux.homeworktest.core.utils.InternetManager
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    val globalErrorHandler: GlobalErrorHandler
): ViewModel() {

}