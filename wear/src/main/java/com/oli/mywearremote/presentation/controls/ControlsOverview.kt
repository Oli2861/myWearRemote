package com.oli.mywearremote.presentation.controls

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.wear.compose.foundation.lazy.AutoCenteringParams
import androidx.wear.compose.foundation.lazy.ScalingLazyColumn
import androidx.wear.compose.foundation.lazy.ScalingLazyColumnDefaults
import androidx.wear.compose.foundation.lazy.rememberScalingLazyListState
import androidx.wear.compose.material.Card
import androidx.wear.compose.material.MaterialTheme
import androidx.wear.compose.material.Text
import com.oli.mywearremote.Screen

@Composable
fun ControlsOverviewComponent(
    availableControlScreens: List<Screen>,
    onScreenSelect: (Screen) -> Unit
) {
    val listState = rememberScalingLazyListState()
    val cardModifier = Modifier
        .padding(4.dp)

    ScalingLazyColumn(
        modifier = Modifier.fillMaxSize(),
        state = listState,
        autoCentering = AutoCenteringParams(itemIndex = 0),
        flingBehavior = ScalingLazyColumnDefaults.snapFlingBehavior(
            state = listState,
            snapOffset = 0.dp
        ),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        availableControlScreens.forEach {
            item {
                Card(
                    onClick = { onScreenSelect(it) },
                    modifier = cardModifier,
                    contentColor = MaterialTheme.colors.primary,
                ) {
                        Text(
                            text = it.description,
                            textAlign = TextAlign.Center,
                            modifier = Modifier.fillMaxWidth()
                        )
                }
            }
        }
    }
}
