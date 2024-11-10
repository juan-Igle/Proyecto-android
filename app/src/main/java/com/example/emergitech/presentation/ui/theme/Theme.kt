package com.example.emergitech.presentation.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorPalette = darkColors(
    primary = BluePrimary, // Cambiado a BluePrimary para un acento tecnológico en el modo oscuro
    primaryVariant = BlueDark, // Usando BlueDark como una variante más intensa
    secondary = GreenSecondary, // Verde secundario para elementos interactivos
    background = DarkGray700, // Fondo oscuro para el modo oscuro
    onPrimary = White // Blanco para un buen contraste con el color primario
)

private val LightColorPalette = lightColors(
    primary = Purple500, // Manteniendo Purple500 para un acento vibrante en el modo claro
    primaryVariant = Purple700, // Variante más oscura de Purple
    secondary = Teal200, // Teal como color secundario en el modo claro
    background = Color.White, // Fondo blanco para el modo claro
    onPrimary = DarkGray700 // Texto oscuro para buen contraste en elementos primarios




    /* Other default colors to override
    background = Color.White,
    surface = Color.White,
    onPrimary = Color.White,
    onSecondary = Color.Black,
    onBackground = Color.Black,
    onSurface = Color.Black,
    */
)

@Composable
fun EmergiTechTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}