package nl.joramkwetters.duogerman.Screens

import android.Manifest
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material.icons.rounded.DarkMode
import androidx.compose.material.icons.rounded.LightMode
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import kotlinx.coroutines.launch
import nl.joramkwetters.duogerman.GermanNotificationService
import nl.joramkwetters.duogerman.ViewModels.ThemeViewModel
import nl.joramkwetters.duogerman.data.DataStoreUtil

@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun SettingsScreen(context: Context, dataStoreUtil: DataStoreUtil, themeViewModel: ThemeViewModel) {
    val postNotificationPermission =
        rememberPermissionState(permission = Manifest.permission.POST_NOTIFICATIONS)

    val germanNotificationService = GermanNotificationService(context)


    LaunchedEffect(key1 = true){
        if (!postNotificationPermission.status.isGranted) {
            postNotificationPermission.launchPermissionRequest()
        }
    }

    Column {
        Row(
            modifier = Modifier.fillMaxSize(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Button(
                    onClick = {
                        germanNotificationService.showPracticeNotification()
                    }
                ){
                    Text("Show practice notification")
                }

                Button(
                    onClick = {
                        germanNotificationService.showNewsNotification()
                    }
                ){
                    Text("Show news notification")
                }
                DarkmodeSwitch(themeViewModel = themeViewModel, dataStoreUtil = dataStoreUtil)

            }
        }


    }
}

@Composable
fun DarkmodeSwitch(themeViewModel: ThemeViewModel, dataStoreUtil: DataStoreUtil) {
    var switchState by remember {themeViewModel.isDarkThemeEnabled }
    val coroutineScope = rememberCoroutineScope()


    Switch(
        checked = switchState,
        onCheckedChange = {
            switchState = it

            coroutineScope.launch {
                dataStoreUtil.saveTheme(it)
            }
        },
        thumbContent = {
            Icon(
                modifier = Modifier
                    .size(SwitchDefaults.IconSize),
                imageVector = if (switchState) Icons.Rounded.DarkMode else Icons.Rounded.LightMode,
                contentDescription = "Switch Icon"
            )
        },
        colors = SwitchDefaults.colors(
            checkedTrackColor = MaterialTheme.colorScheme.primary,
            checkedThumbColor = MaterialTheme.colorScheme.onPrimary,
        ),
    )


}
