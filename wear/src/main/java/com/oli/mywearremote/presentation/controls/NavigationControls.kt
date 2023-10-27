package com.oli.mywearremote.presentation.controls

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.wear.compose.material.MaterialTheme
import com.oli.mywearremote.R
import com.oli.mywearremote.network.ControlAction
import org.koin.androidx.compose.koinViewModel

@Composable
fun NavigationControls(
    controlsViewModel: NavigationControlViewModel = koinViewModel()
){
    val right = Control(
        action = ControlAction.NEXT,
        icon = R.drawable.baseline_keyboard_arrow_right_24,
        onClick = controlsViewModel::right,
        controlsViewModel.rightRequestState
    )

    val left = Control(
        action = ControlAction.PREVIOUS,
        icon = R.drawable.baseline_keyboard_arrow_left_24,
        onClick = controlsViewModel::left,
        controlsViewModel.leftRequestState
    )

    val up = Control(
        action = ControlAction.NAVIGATE_UP,
        icon = R.drawable.baseline_keyboard_arrow_up_24,
        onClick = controlsViewModel::up,
        controlsViewModel.upRequestState
    )

    val down = Control(
        action = ControlAction.NAVIGATE_DOWN,
        icon = R.drawable.baseline_keyboard_arrow_down_24,
        onClick = controlsViewModel::down,
        controlsViewModel.downRequestState
    )

    val select = Control(
        action = ControlAction.SELECT,
        icon = R.drawable.baseline_circle_24,
        onClick = controlsViewModel::select,
        controlsViewModel.selectRequestState
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.background),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ControlButton(up)
        Row(
            Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            ControlButton(left)
            ControlButton(select)
            ControlButton(right)
        }
        ControlButton(down)
    }
}