package com.magdenbt.collectionsbenchmark.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MovableContent
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.unit.dp
import androidx.core.view.WindowCompat

// Material 3 color schemes
private val LightColors = lightColorScheme(
    primary = amber_700,
    onPrimary = white,
    background = white,
    onBackground = black,
    surface = white,
    //surfaceVariant = black,

    onSurfaceVariant = white,
    scrim = gray_400,
    error = red,
    onError = black,

)

val shapes = Shapes(
    extraSmall = RoundedCornerShape(8.dp)
)

@Composable
fun AppTheme(content: @Composable () -> Unit
) {
//    val view = LocalView.current
//    if (!view.isInEditMode) {
//        SideEffect {
//            val window = (view.context as Activity).window
//            window.statusBarColor = LightColors.primary.toArgb()
//            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = useDarkTheme
//        }
//    }

    MaterialTheme(colorScheme = LightColors, typography = typography, shapes = shapes, content = content )

}


