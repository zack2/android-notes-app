package com.olivierloukombo.android_notes_app.views

import android.view.animation.OvershootInterpolator
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import kotlinx.coroutines.delay

import androidx.compose.material.Text
import com.olivierloukombo.android_notes_app.BuildConfig
import com.olivierloukombo.android_notes_app.R
import com.olivierloukombo.android_notes_app.navigation.Screen

@Composable
fun SplashScreen(navController: NavHostController) = Box(
    Modifier
        .fillMaxWidth()
        .fillMaxHeight()
) {

    val scale = remember {
        androidx.compose.animation.core.Animatable(0.0f)
    }

    LaunchedEffect(key1 = true) {
        scale.animateTo(
            targetValue = 0.7f,
            animationSpec = tween(800, easing = {
                OvershootInterpolator(4f).getInterpolation(it)
            })
        )
        delay(1000)
        navController.navigate(Screen.ListNoteScreen.route) {
            popUpTo(Screen.SplashScreen.route) {
                inclusive = true
            }
        }
    }


    Image(
        painter = painterResource(id = R.drawable.lengevity_in_time),
        contentDescription = "The Logo",
        alignment = Alignment.Center, modifier = Modifier
            .fillMaxSize().padding(40.dp)
            .scale(scale.value)
    )

    Text(
        text = "Version - ${BuildConfig.VERSION_NAME}",
        textAlign = TextAlign.Center,
        style = MaterialTheme.typography.h2,
        modifier = Modifier.align(Alignment.BottomCenter).padding(16.dp)
    )
}