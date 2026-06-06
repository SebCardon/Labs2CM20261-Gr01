package co.edu.udea.compumovil.gr01_20261.lab2.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorScheme = darkColorScheme(
    primary = WaGreenDark,
    onPrimary = WaTopBarText,
    secondary = WaTealDark,
    onSecondary = WaTopBarText,
    background = Color(0xFF111B21),
    surface = WaBubbleDark,
    surfaceVariant = Color(0xFF1E2A30),
    onSurface = Color.White,
    onSurfaceVariant = Color(0xFFAEBBC1)
)

private val LightColorScheme = lightColorScheme(
    primary = WaGreen,
    onPrimary = WaTopBarText,
    primaryContainer = WaLightGreen,
    secondary = WaTeal,
    onSecondary = WaTopBarText,
    background = Color.White,
    surface = Color.White,
    surfaceVariant = WaReceivedBubble,
    onSurface = Color(0xFF111B21),
    onSurfaceVariant = Color(0xFF667781)
)

@Composable
fun Labs20261Gr01Theme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
