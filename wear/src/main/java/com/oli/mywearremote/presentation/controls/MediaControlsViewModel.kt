package com.oli.mywearremote.presentation.controls

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.oli.mywearremote.network.ControlAction
import com.oli.mywearremote.network.ActionClient
import com.oli.mywearremote.network.RequestState
import kotlinx.coroutines.launch

class MediaControlsViewModel(
    private val actionClient: ActionClient
) : ViewModel() {
    private val TAG = "MediaControlsViewModel"
    val volumeRequestState: MutableState<RequestState> = mutableStateOf(RequestState.INIT)
    val playRequestState: MutableState<RequestState> = mutableStateOf(RequestState.INIT)
    val pauseRequestState: MutableState<RequestState> = mutableStateOf(RequestState.INIT)
    val nextRequestState: MutableState<RequestState> = mutableStateOf(RequestState.INIT)
    val previousRequestState: MutableState<RequestState> = mutableStateOf(RequestState.INIT)


    fun volumeUp() = viewModelScope.launch { requestControl(volumeRequestState, actionClient::requestAction, ControlAction.VOLUME_UP) }
    fun volumeDown() = viewModelScope.launch { requestControl(volumeRequestState, actionClient::requestAction, ControlAction.VOLUME_DOWN) }
    fun play() = viewModelScope.launch { requestControl(playRequestState, actionClient::requestAction, ControlAction.PLAY) }
    fun pause() = viewModelScope.launch { requestControl(pauseRequestState, actionClient::requestAction, ControlAction.PAUSE) }
    fun next() = viewModelScope.launch { requestControl(nextRequestState, actionClient::requestAction, ControlAction.NEXT) }
    fun previous() = viewModelScope.launch { requestControl(previousRequestState, actionClient::requestAction, ControlAction.PREVIOUS) }
}