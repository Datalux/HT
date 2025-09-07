package it.datalux.homeworktest.presentation.screen.main

import android.annotation.SuppressLint
import android.app.Application
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import it.datalux.homeworktest.presentation.navigation.NavigationGraph
import kotlinx.coroutines.flow.receiveAsFlow

@Composable
fun MainScreen(
    mainViewModel: MainViewModel = hiltViewModel(),
) {
    MainContent(mainViewModel)
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainContent(
    mainViewModel: MainViewModel
) {
    val navController = rememberNavController()
    val snackbarHostState = remember { SnackbarHostState() }


    LaunchedEffect(mainViewModel.globalErrorHandler) {
        mainViewModel.globalErrorHandler.errorEvents.receiveAsFlow().collect { alert ->
            snackbarHostState.showSnackbar(alert.text, duration = SnackbarDuration.Short)
        }
    }

    Scaffold(
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState)
        },
    ) {
        NavigationGraph(
            navController = navController
        )
    }
}

class MockApplication : Application()

@SuppressLint("ViewModelConstructorInComposable")
@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    MainScreen(MainViewModel(GlobalErrorHandler()))
}
