package it.datalux.homeworktest.core.shared

import android.content.Context
import it.datalux.homeworktest.core.utils.InternetManager

fun checkInternetConnection(context: Context) = InternetManager.Companion.isInternetAvailable(context)
