package it.datalux.homeworktest.presentation.screen.main

import android.app.Application
import android.content.Context
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import it.datalux.homeworktest.core.utils.AppAlert
import it.datalux.homeworktest.core.utils.InternetManager
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    application: Application
): ViewModel() {

    private val _appAlert = MutableStateFlow<AppAlert>(AppAlert.Idle)
    val appAlert = _appAlert.asStateFlow()

    init {
        checkInternetConnection(application)
    }

    private fun checkInternetConnection(context: Context) {
        val hasInternetConnection = InternetManager.Companion.isInternetAvailable(context)

        if (!hasInternetConnection) _appAlert.value = AppAlert.NetworkFailure
        else _appAlert.value = AppAlert.Idle
    }

}