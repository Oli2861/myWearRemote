/* While this template provides a good starting point for using Wear Compose, you can always
 * take a look at https://github.com/android/wear-os-samples/tree/main/ComposeStarter and
 * https://github.com/android/wear-os-samples/tree/main/ComposeAdvanced to find the most up to date
 * changes to the libraries and their usages.
 */

package com.oli.mywearremote

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.wear.compose.navigation.SwipeDismissableNavHost
import androidx.wear.compose.navigation.composable
import androidx.wear.compose.navigation.rememberSwipeDismissableNavController
import com.oli.mywearremote.presentation.controls.ControlsOverviewComponent
import com.oli.mywearremote.presentation.controls.MediaControls
import com.oli.mywearremote.presentation.controls.NavigationControls
import com.oli.mywearremote.presentation.theme.MyWearRemoteTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WearApp("Android")
        }
    }
}

@Composable
fun WearApp(greetingName: String) {
    MyWearRemoteTheme {
        MyNavHost()
    }
}

@Composable
fun MyNavHost(){
    val navController = rememberSwipeDismissableNavController()

    SwipeDismissableNavHost(
        navController = navController,
        startDestination = Screen.ControlsOverview.route
    ) {
        composable(Screen.MediaControls.route){
            MediaControls()
        }
        composable(Screen.NavigationControls.route){
            NavigationControls()
        }
        composable(Screen.ControlsOverview.route) {
            ControlsOverviewComponent(
                availableControlScreens = listOf(
                    Screen.MediaControls,
                    Screen.NavigationControls
                ),
                onScreenSelect = {screen ->  navController.navigate(screen.route)}
            )
        }
    }
}

sealed class Screen(val route: String, val description: String){
    object ControlsOverview: Screen("controls_overview","Overview over all available screens")
    object MediaControls: Screen("media_controls", "Media controls")
    object NavigationControls: Screen("navigation_controls", "Navigation controls")
}
