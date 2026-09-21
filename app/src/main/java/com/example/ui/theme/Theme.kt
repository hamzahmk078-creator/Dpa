package com.example.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorScheme =
  darkColorScheme(
    primary = DpaGreenDarkTheme,
    onPrimary = DpaGreenDark,
    primaryContainer = DpaGreenDark,
    onPrimaryContainer = DpaGreenContainer,
    secondary = DpaGoldDarkTheme,
    onSecondary = Color.Black,
    secondaryContainer = DpaOnGoldContainer,
    onSecondaryContainer = DpaGoldContainer,
    tertiary = DpaCrimsonTertiary,
    background = DpaBackgroundDark,
    onBackground = Color(0xFFE2E8E4),
    surface = DpaSurfaceDark,
    onSurface = Color(0xFFE2E8E4),
    surfaceVariant = DpaSurfaceVariantDark,
    onSurfaceVariant = Color(0xFFC0CDC4),
    outline = Color(0xFF4A554D)
  )

private val LightColorScheme =
  lightColorScheme(
    primary = DpaGreenPrimary,
    onPrimary = Color.White,
    primaryContainer = DpaGreenContainer,
    onPrimaryContainer = DpaOnGreenContainer,
    secondary = DpaGoldSecondary,
    onSecondary = Color.White,
    secondaryContainer = DpaGoldContainer,
    onSecondaryContainer = DpaOnGoldContainer,
    tertiary = DpaCrimsonTertiary,
    onTertiary = Color.White,
    tertiaryContainer = DpaCrimsonContainer,
    background = DpaBackgroundLight,
    onBackground = DpaTextPrimaryLight,
    surface = DpaSurfaceLight,
    onSurface = DpaTextPrimaryLight,
    surfaceVariant = DpaSurfaceVariantLight,
    onSurfaceVariant = DpaTextSecondaryLight,
    outline = DpaOutlineLight
  )

@Composable
fun MyApplicationTheme(
  darkTheme: Boolean = isSystemInDarkTheme(),
  dynamicColor: Boolean = false, // Use our handcrafted national identity palette
  content: @Composable () -> Unit,
) {
  val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme
  MaterialTheme(colorScheme = colorScheme, typography = Typography, content = content)
}

