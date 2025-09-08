package it.datalux.homeworktest.core.utils

import androidx.compose.foundation.clickable
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier

fun String?.toStringOrEmpty() = this ?: ""
fun Double?.toDoubleOrZero() = this ?: 0.0
fun Int?.toIntOrZero() = this ?: 0
fun Long?.toLongOrZero() = this ?: 0
fun Int?.toIntOr(or: Int) = this ?: or
fun Boolean?.toBooleanOrFalse() = this ?: false

@Composable
fun Modifier.debounceClickable(
    debounceInterval: Long = 1,
    onClick: () -> Unit,
): Modifier {
    var lastClickTime by remember { mutableLongStateOf(0L) }
    val currentOnClick by rememberUpdatedState { onClick() }
    return clickable {
        val currentTime = System.currentTimeMillis()
        if ((currentTime - lastClickTime) < debounceInterval) return@clickable
        lastClickTime = currentTime
        currentOnClick()
    }
}