package com.oli.mywearremote.presentation.controls

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.oli.mywearremote.network.ControlAction
import com.oli.mywearremote.network.ActionClient
import com.oli.mywearremote.network.RequestState
import kotlinx.coroutines.launch

class NavigationControlViewModel(
    private val actionClient: ActionClient
): ViewModel() {
    val rightRequestState: MutableState<RequestState> = mutableStateOf(RequestState.INIT)
    val leftRequestState: MutableState<RequestState> = mutableStateOf(RequestState.INIT)
    val upRequestState: MutableState<RequestState> = mutableStateOf(RequestState.INIT)
    val downRequestState: MutableState<RequestState> = mutableStateOf(RequestState.INIT)
    val selectRequestState: MutableState<RequestState> = mutableStateOf(RequestState.INIT)

    fun right() = viewModelScope.launch { requestControl(rightRequestState, actionClient::requestAction, ControlAction.NAVIGATE_RIGHT) }
    fun left() = viewModelScope.launch { requestControl(leftRequestState, actionClient::requestAction, ControlAction.NAVIGATE_LEFT) }
    fun up() = viewModelScope.launch { requestControl(upRequestState, actionClient::requestAction, ControlAction.NAVIGATE_UP) }
    fun down() = viewModelScope.launch { requestControl(downRequestState, actionClient::requestAction, ControlAction.NAVIGATE_DOWN) }
    fun select() = viewModelScope.launch { requestControl(selectRequestState, actionClient::requestAction, ControlAction.SELECT) }

}
