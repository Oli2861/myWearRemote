package com.oli.mywearremote.presentation.controls

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.wear.compose.material.Button
import androidx.wear.compose.material.ButtonDefaults
import androidx.wear.compose.material.Icon
import androidx.wear.compose.material.MaterialTheme
import com.oli.mywearremote.network.RequestState

@Composable
fun ControlButton(
    control: Control,
    onClick: () -> Unit = { },
    modifier: Modifier = Modifier
) {
    val color = getColor(control.requestState.value)

    Button(
        onClick = {
            onClick()
            control.onClick()
        },
        modifier = modifier,
        colors = ButtonDefaults.buttonColors(
            backgroundColor = color
        )
    ) {
        Icon(
            painter = painterResource(id = control.icon),
            contentDescription = control.action.name
        )
    }
}

@Composable
private fun getColor(requestState: RequestState): Color {
    return when (requestState) {
        RequestState.INIT -> MaterialTheme.colors.primary
        RequestState.PENDING -> MaterialTheme.colors.secondary
        RequestState.FAILED -> MaterialTheme.colors.error
        RequestState.COMPLETED -> MaterialTheme.colors.primary
    }
}