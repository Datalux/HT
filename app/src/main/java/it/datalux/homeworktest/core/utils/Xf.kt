package it.datalux.homeworktest.core.utils

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.util.Date

fun String?.toStringOrEmpty() = this ?: ""
fun Double?.toDoubleOrZero() = this ?: 0.0
fun Int?.toIntOrZero() = this ?: 0
fun Long?.toLongOrZero() = this ?: 0
fun Int?.toIntOr(or: Int) = this ?: or
fun Boolean?.toBooleanOrFalse() = this ?: false

inline fun Modifier.debounceClickable(
    debounceInterval: Long = 1,
    crossinline onClick: () -> Unit,
): Modifier {
    var lastClickTime = 0L
    return clickable() {
        val currentTime = System.currentTimeMillis()
        if ((currentTime - lastClickTime) < debounceInterval) return@clickable
        lastClickTime = currentTime
        onClick()
    }
}