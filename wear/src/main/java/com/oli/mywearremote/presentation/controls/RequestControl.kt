package com.oli.mywearremote.presentation.controls

import android.util.Log
import androidx.compose.runtime.MutableState
import com.oli.mywearremote.network.ControlAction
import com.oli.mywearremote.network.RequestState

suspend fun requestControl(
    requestState: MutableState<RequestState>,
    handlerFunction: suspend (ControlAction) -> Boolean,
    controlAction: ControlAction,
) {
    if (requestState.value != RequestState.PENDING) {
        requestState.value = RequestState.PENDING
        val success = handlerFunction(controlAction)
        requestState.value = if (success)
            RequestState.COMPLETED
        else
            RequestState.FAILED

        Log.d("RequestState", requestState.value.toString())
    }
}
