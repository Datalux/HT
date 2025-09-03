package it.datalux.homeworktest.core.theme

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

val primary = Color(0xFFFFFFFF)
val secondary = Color(0xFFFFFFFF)
val tertiary = Color(0xAAFFFFFF)

val Color.Companion.background: Color
    @Composable
    get() = Color(0xFFFFFFFF)

val Color.Companion.onBackground: Color
    @Composable
    get() = Color(0xFF000000)

val Color.Companion.backgroundSecondary: Color
    @Composable
    get() = Color(0xFFEDEDF4)

val Color.Companion.onBackgroundSecondary: Color
    @Composable
    get() = Color(0xFF49454F)