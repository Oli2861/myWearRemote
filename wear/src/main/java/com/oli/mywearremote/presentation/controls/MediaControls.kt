package com.oli.mywearremote.presentation.controls

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.wear.compose.material.MaterialTheme
import com.oli.mywearremote.R
import com.oli.mywearremote.network.ControlAction
import org.koin.androidx.compose.koinViewModel

@Composable
fun MediaControls(
    controlsViewModel: MediaControlsViewModel = koinViewModel()
) {

    val volumeUp = Control(
        action = ControlAction.VOLUME_UP,
        icon = R.drawable.baseline_volume_up_24,
        onClick = controlsViewModel::volumeUp,
        controlsViewModel.volumeUpRequestState
    )

    val volumeDown = Control(
        action = ControlAction.VOLUME_DOWN,
        icon = R.drawable.baseline_volume_down_24,
        onClick = controlsViewModel::volumeDown,
        controlsViewModel.volumeDownRequestState
    )

    val togglePlay = Control(
        action = ControlAction.PLAY,
        icon = R.drawable.baseline_play_arrow_24,
        onClick = controlsViewModel::play,
        controlsViewModel.playRequestState
    )

    val pause = Control(
        action = ControlAction.PAUSE,
        icon = R.drawable.baseline_pause_24,
        onClick = controlsViewModel::pause,
        controlsViewModel.pauseRequestState
    )

    val next = Control(
        action = ControlAction.NEXT,
        icon = R.drawable.baseline_skip_next_24,
        onClick = controlsViewModel::next,
        controlsViewModel.nextRequestState
    )

    val previous = Control(
        action = ControlAction.PREVIOUS,
        icon = R.drawable.baseline_skip_previous_24,
        onClick = controlsViewModel::previous,
        controlsViewModel.previousRequestState
    )

    var playPauseControl by remember { mutableStateOf(togglePlay) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.background),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = androidx.compose.ui.Alignment.CenterHorizontally
    ) {
        ControlButton(volumeUp)
        Row(
            Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            ControlButton(previous)
            ControlButton(
                control = playPauseControl,
                onClick = { playPauseControl = if (playPauseControl == togglePlay) pause else togglePlay }
            )
            ControlButton(next)
        }
        ControlButton(volumeDown)
    }
}

