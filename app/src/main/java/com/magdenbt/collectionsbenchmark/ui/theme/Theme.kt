package com.magdenbt.collectionsbenchmark.ui.theme

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp

// Material 3 color schemes
private val LightColors = lightColorScheme(
    primary = amber_700,
    onPrimary = white,
    background = white,
    onBackground = black,
    surface = amber_700,
    onSurface = black,
    surfaceVariant = gray_10,
    onSurfaceVariant = gray_400,
    error = red,
    onError = black,

)

val shapes = Shapes(
    extraSmall = RoundedCornerShape(10.dp),
)

@Composable
fun AppTheme(
    content: @Composable () -> Unit,
) {
//    val view = LocalView.current
//    if (!view.isInEditMode) {
//        SideEffect {
//            val window = (view.context as Activity).window
//            window.statusBarColor = LightColors.primary.toArgb()
//            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = useDarkTheme
//        }
//    }

    MaterialTheme(
        colorScheme = LightColors,
        typography = typography,
        shapes = shapes,
        content = content,
    )
}
