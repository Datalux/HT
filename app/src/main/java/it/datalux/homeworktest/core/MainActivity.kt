package it.datalux.homeworktest.core

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import dagger.hilt.android.AndroidEntryPoint
import it.datalux.homeworktest.core.theme.HomeworkTestTheme
import it.datalux.homeworktest.presentation.screen.main.MainScreen
import it.datalux.homeworktest.presentation.screen.photosList.PhotosViewModel

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val photosViewModel by viewModels<PhotosViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        installSplashScreen().apply {
            setKeepOnScreenCondition {
                photosViewModel.loading.value
            }
        }

        setContent {
            HomeworkTestTheme {
                MainScreen()
            }
        }
    }
}
