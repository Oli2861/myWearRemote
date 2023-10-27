package com.oli.mywearremote.network

class ActionClient(
    private val mediaControlsAPI: MediaControlsAPI,
    private val navigationControlsAPI: NavigationControlsAPI
) {

    suspend fun requestAction(controlAction: ControlAction): Boolean {
        return try {
            when (controlAction) {
                ControlAction.VOLUME_UP -> mediaControlsAPI.volumeUp().isSuccessful
                ControlAction.VOLUME_DOWN -> mediaControlsAPI.volumeDown().isSuccessful
                ControlAction.PLAY -> mediaControlsAPI.play().isSuccessful
                ControlAction.PAUSE -> mediaControlsAPI.pause().isSuccessful
                ControlAction.NEXT -> mediaControlsAPI.next().isSuccessful
                ControlAction.PREVIOUS -> mediaControlsAPI.previous().isSuccessful

                ControlAction.NAVIGATE_RIGHT -> navigationControlsAPI.right().isSuccessful
                ControlAction.NAVIGATE_LEFT -> navigationControlsAPI.left().isSuccessful
                ControlAction.NAVIGATE_UP -> navigationControlsAPI.up().isSuccessful
                ControlAction.NAVIGATE_DOWN -> navigationControlsAPI.down().isSuccessful
                ControlAction.SELECT -> navigationControlsAPI.select().isSuccessful
            }
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
    }

}