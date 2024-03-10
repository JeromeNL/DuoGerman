package nl.joramkwetters.duogerman

import android.content.Context
import android.content.res.Configuration
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import nl.joramkwetters.duogerman.Navigation.AppNavigation
import nl.joramkwetters.duogerman.ViewModels.ThemeViewModel
import nl.joramkwetters.duogerman.data.DataStoreUtil
import nl.joramkwetters.duogerman.ui.theme.DuoGermanTheme

class MainActivity : ComponentActivity() {

    private val themeViewModel: ThemeViewModel by viewModels()
    private lateinit var dataStoreUtil: DataStoreUtil

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dataStoreUtil = DataStoreUtil(applicationContext)

        val systemTheme = when (resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK) {
            Configuration.UI_MODE_NIGHT_YES -> { true }
            Configuration.UI_MODE_NIGHT_NO -> { false }
            else -> { false }
        }

        val context: Context = this;

        setContent {
            val theme = dataStoreUtil.getTheme(systemTheme).collectAsState(initial = systemTheme)
            DuoGermanTheme(
                darkTheme = theme.value
            ) {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Column(){
                        AppNavigation(context, dataStoreUtil, themeViewModel, intent)
                    }
                }
            }
        }
    }
}



