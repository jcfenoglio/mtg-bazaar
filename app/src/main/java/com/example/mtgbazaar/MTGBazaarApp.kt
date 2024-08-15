package com.example.mtgbazaar

import android.content.res.Resources
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.Surface
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import androidx.navigation.NavHostController
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.mtgbazaar.common.snackbar.SnackbarManager
import com.example.mtgbazaar.screens.binder.BinderScreen
import com.example.mtgbazaar.screens.collection.CollectionScreen
import com.example.mtgbazaar.screens.edit_binder.EditBinderScreen
import com.example.mtgbazaar.screens.login.LoginScreen
import com.example.mtgbazaar.screens.settings.SettingsScreen
import com.example.mtgbazaar.screens.sign_up.SignUpScreen
import com.example.mtgbazaar.screens.splash.SplashScreen
import com.example.mtgbazaar.ui.theme.MTGBazaarTheme
import kotlinx.coroutines.CoroutineScope

@Composable
fun MTGBazaarApp() {
    MTGBazaarTheme {
        Surface(color = MaterialTheme.colorScheme.surface) {
            val appState = rememberAppState()

            Scaffold(
                snackbarHost = {
                    SnackbarHost(
                        hostState = appState.snackbarHostState,
                        modifier = Modifier.padding(8.dp),
                        snackbar = { snackbarData ->
                            Snackbar(snackbarData, contentColor = MaterialTheme.colorScheme.onPrimary)
                        }
                    )
                }
            ) { innerPaddingModifier ->
                NavHost(
                    navController = appState.navController,
                    startDestination = SPLASH_SCREEN,
                    modifier = Modifier.padding(innerPaddingModifier)
                ) {
                    mtgBazaarGraph(appState)
                }

            }
        }
    }

}

@Composable
fun rememberAppState(
    snackbarHostState: SnackbarHostState = SnackbarHostState(),
    navController: NavHostController = rememberNavController(),
    snackbarManager: SnackbarManager = SnackbarManager,
    resources: Resources = resources(),
    coroutineScope: CoroutineScope = rememberCoroutineScope()
) =
    remember(snackbarHostState, navController, snackbarManager, resources, coroutineScope) {
        MTGBazaarAppState(snackbarHostState, navController, snackbarManager, resources, coroutineScope)
    }

@Composable
@ReadOnlyComposable
fun resources(): Resources {
    LocalConfiguration.current
    return LocalContext.current.resources
}

fun NavGraphBuilder.mtgBazaarGraph(appState: MTGBazaarAppState) {
    composable(SPLASH_SCREEN) {
        SplashScreen(openAndPopUp = { route, popUp -> appState.navigateAndPopUp(route, popUp)})
    }

    composable(SETTINGS_SCREEN) {
        SettingsScreen(
            restartApp = { route -> appState.clearAndNavigate(route) },
            openScreen = { route -> appState.navigate(route) }
        )
    }

    composable(LOGIN_SCREEN) {
        LoginScreen(openAndPopUp = { route, popUp -> appState.navigateAndPopUp(route, popUp) })
    }

    composable(SIGN_UP_SCREEN) {
        SignUpScreen(openAndPopUp = { route, popUp -> appState.navigateAndPopUp(route, popUp) })
    }

    composable(COLLECTION_SCREEN) {
        CollectionScreen(openScreen = { route -> appState.navigate(route) })
    }
    
    composable(
        route = "$BINDER_SCREEN$BINDER_ID_ARG",
        arguments = listOf(navArgument(BINDER_ID) {
            nullable = true
            defaultValue = null
        })
    ) {
        BinderScreen(
            openScreen = { route -> appState.navigate(route) },
            popUpScreen = { appState.popUp() })
    }

    composable(
        route = "$EDIT_BINDER_SCREEN$BINDER_ID_ARG",
        arguments = listOf(navArgument(BINDER_ID) {
            nullable = true
            defaultValue = null
        })
    ) {
        EditBinderScreen(popUpScreen = { appState.popUp() })
    }
}