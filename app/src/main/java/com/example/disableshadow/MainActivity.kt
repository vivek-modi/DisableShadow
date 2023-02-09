package com.example.disableshadow

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.ripple.LocalRippleTheme
import androidx.compose.material.ripple.RippleAlpha
import androidx.compose.material.ripple.RippleTheme
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LocalMinimumTouchTargetEnforcement
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.example.disableshadow.ui.theme.DisableShadowTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DisableShadowTheme {
                // A surface container using the 'background' color from the theme
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Greeting()
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Greeting() {
    val interactionSource = remember { MutableInteractionSource() }
    CompositionLocalProvider(
        LocalMinimumTouchTargetEnforcement provides false,
        LocalRippleTheme provides CustomRippleTheme(Color.Unspecified),
        content = {
            Surface(
                onClick = { Log.e("ItemDisablePreview", "ItemClicked") },
                interactionSource = interactionSource,
                color = Color.White,
            ) {
                Text(text = "Item Name")
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    DisableShadowTheme {
        Greeting()
    }
}

class CustomRippleTheme(private val rippleColor: Color) : RippleTheme {

    @Composable
    override fun defaultColor(): Color {
        return if (rippleColor == Color.Unspecified) {
            Color.Unspecified
        } else {
            RippleTheme.defaultRippleColor(
                contentColor = rippleColor,
                lightTheme = true
            )
        }
    }

    @Composable
    override fun rippleAlpha() = when (rippleColor) {
        Color.Red -> {
            RippleAlpha(1f, 1f, 1f, 1f)
        }
        Color.Unspecified -> {
            RippleAlpha(0.0f, 0.0f, 0.0f, 0.0f)
        }
        else -> {
            RippleTheme.defaultRippleAlpha(
                contentColor = rippleColor,
                lightTheme = true
            )
        }
    }
}