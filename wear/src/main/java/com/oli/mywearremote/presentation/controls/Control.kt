package com.oli.mywearremote.presentation.controls

import androidx.compose.runtime.MutableState
import com.oli.mywearremote.network.ControlAction
import com.oli.mywearremote.network.RequestState

data class Control(
    val action: ControlAction,
    val icon: Int,
    val onClick: () -> Unit,
    val requestState: MutableState<RequestState>
)
