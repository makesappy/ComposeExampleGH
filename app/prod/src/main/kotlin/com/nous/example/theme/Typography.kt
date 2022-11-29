package com.nous.example.theme

import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.googlefonts.Font
import androidx.compose.ui.text.googlefonts.GoogleFont
import com.nous.example.prod.R

internal val provider = GoogleFont.Provider(
    providerAuthority = "com.google.android.gms.fonts",
    providerPackage = "com.google.android.gms",
    certificates = R.array.com_google_android_gms_fonts_certs
)

internal val RobotoFont = GoogleFont(name = "Roboto")

internal val RobotoFontFamily = FontFamily(
    Font(googleFont = RobotoFont, fontProvider = provider),
    Font(googleFont = RobotoFont, fontProvider = provider, weight = FontWeight.Light),
    Font(googleFont = RobotoFont, fontProvider = provider, weight = FontWeight.Medium),
    Font(googleFont = RobotoFont, fontProvider = provider, weight = FontWeight.SemiBold),
)

@Stable
internal class CustomTypography(
    display: TextStyle,
    header1: TextStyle,
    header2: TextStyle,
    header3: TextStyle,
    header4: TextStyle,
    header5: TextStyle,
    header6: TextStyle,
    body1: TextStyle,
    body1Link: TextStyle,
    body2: TextStyle,
    disclaimer: TextStyle,
    disclaimerLink: TextStyle,
    label: TextStyle
) {
    var display by mutableStateOf(display, structuralEqualityPolicy())
        internal set
    var header1 by mutableStateOf(header1, structuralEqualityPolicy())
        internal set
    var header2 by mutableStateOf(header2, structuralEqualityPolicy())
        internal set
    var header3 by mutableStateOf(header3, structuralEqualityPolicy())
        internal set
    var header4 by mutableStateOf(header4, structuralEqualityPolicy())
        internal set
    var header5 by mutableStateOf(header5, structuralEqualityPolicy())
        internal set
    var header6 by mutableStateOf(header6, structuralEqualityPolicy())
        internal set
    var body1 by mutableStateOf(body1, structuralEqualityPolicy())
        internal set
    var body1Link by mutableStateOf(body1Link, structuralEqualityPolicy())
        internal set
    var body2 by mutableStateOf(body2, structuralEqualityPolicy())
        internal set
    var disclaimer by mutableStateOf(disclaimer, structuralEqualityPolicy())
        internal set
    var disclaimerLink by mutableStateOf(disclaimerLink, structuralEqualityPolicy())
        internal set
    var label by mutableStateOf(label, structuralEqualityPolicy())
        internal set

    internal fun copy(
        display: TextStyle = this.display,
        header1: TextStyle = this.header1,
        header2: TextStyle = this.header2,
        header3: TextStyle = this.header3,
        header4: TextStyle = this.header4,
        header5: TextStyle = this.header5,
        header6: TextStyle = this.header6,
        body1: TextStyle = this.body1,
        body1Link: TextStyle = this.body1Link,
        body2: TextStyle = this.body2,
        disclaimer: TextStyle = this.disclaimer,
        disclaimerLink: TextStyle = this.disclaimerLink,
        label: TextStyle = this.label,
    ) = CustomTypography(
        display = display,
        header1 = header1,
        header2 = header2,
        header3 = header3,
        header4 = header4,
        header5 = header5,
        header6 = header6,
        body1 = body1,
        body1Link = body1Link,
        body2 = body2,
        disclaimer = disclaimer,
        disclaimerLink = disclaimerLink,
        label = label,
    )

    override fun toString(): String = "CustomTypography"
}

internal fun customInitialTypography() = CustomTypography(
    display = TextStyle(
        fontFamily = RobotoFontFamily,
        fontSize = 40.sp,
        fontWeight = FontWeight.W700,
        lineHeight = 48.sp,
        letterSpacing = 0.02.em
    ),
    header1 = TextStyle(
        fontFamily = RobotoFontFamily,
        fontSize = 28.sp,
        fontWeight = FontWeight.W700,
        lineHeight = 36.sp,
        letterSpacing = 0.02.em
    ),
    header2 = TextStyle(
        fontFamily = RobotoFontFamily,
        fontSize = 24.sp,
        fontWeight = FontWeight.W700,
        lineHeight = 32.sp,
        letterSpacing = 0.02.em
    ),
    header3 = TextStyle(
        fontFamily = RobotoFontFamily,
        fontSize = 20.sp,
        fontWeight = FontWeight.W700,
        lineHeight = 24.sp,
        letterSpacing = 0.02.em
    ),
    header4 = TextStyle(
        fontFamily = RobotoFontFamily,
        fontSize = 16.sp,
        fontWeight = FontWeight.W700,
        lineHeight = 24.sp,
        letterSpacing = 0.02.em
    ),
    header5 = TextStyle(
        fontFamily = RobotoFontFamily,
        fontSize = 14.sp,
        fontWeight = FontWeight.W700,
        lineHeight = 20.sp,
        letterSpacing = 0.02.em
    ),
    header6 = TextStyle(
        fontFamily = RobotoFontFamily,
        fontSize = 14.sp,
        fontWeight = FontWeight.W400,
        lineHeight = 20.sp,
        letterSpacing = 0.02.em
    ),
    body1 = TextStyle(
        fontFamily = RobotoFontFamily,
        fontSize = 16.sp,
        fontWeight = FontWeight.W400,
        lineHeight = 24.sp,
        letterSpacing = 0.02.em
    ),
    body1Link = TextStyle(
        fontFamily = RobotoFontFamily,
        fontSize = 16.sp,
        fontWeight = FontWeight.W400,
        lineHeight = 24.sp,
        letterSpacing = 0.02.em,
        textDecoration = TextDecoration.Underline
    ),
    body2 = TextStyle(
        fontFamily = RobotoFontFamily,
        fontSize = 10.sp,
        fontWeight = FontWeight.W400,
        lineHeight = 14.sp,
        letterSpacing = 0.02.em
    ),
    disclaimer = TextStyle(
        fontFamily = RobotoFontFamily,
        fontSize = 12.sp,
        fontWeight = FontWeight.W400,
        lineHeight = 16.sp,
        letterSpacing = 0.02.em
    ),
    disclaimerLink = TextStyle(
        fontFamily = RobotoFontFamily,
        fontSize = 12.sp,
        fontWeight = FontWeight.W400,
        lineHeight = 16.sp,
        letterSpacing = 0.02.em,
        textDecoration = TextDecoration.Underline
    ),
    label = TextStyle(
        fontFamily = RobotoFontFamily,
        fontSize = 11.sp,
        fontWeight = FontWeight.W900,
        lineHeight = 14.sp,
        letterSpacing = 0.26.em,
    )
)

internal fun customColorAwareTypography(
    textPrimary: Color,
    textSecondary: Color
) = with(customInitialTypography()) {
    copy(
        display = display.copy(color = textPrimary),
        header1 = header1.copy(color = textPrimary),
        header2 = header2.copy(color = textPrimary),
        header3 = header3.copy(color = textPrimary),
        header4 = header4.copy(color = textPrimary),
        header5 = header5.copy(color = textPrimary),
        header6 = header6.copy(color = textPrimary),
        body1 = body1.copy(color = textSecondary),
        body1Link = body1Link.copy(color = textSecondary),
        body2 = body2.copy(color = textSecondary),
        disclaimer = disclaimer.copy(color = textPrimary),
        disclaimerLink = disclaimerLink.copy(color = textPrimary),
        label = label.copy(color = textPrimary),
    )
}

internal val LocalCustomTypography = staticCompositionLocalOf { customInitialTypography() }