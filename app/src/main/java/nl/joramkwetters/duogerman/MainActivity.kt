package nl.joramkwetters.duogerman

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import nl.joramkwetters.duogerman.Navigation.AppNavigation
import nl.joramkwetters.duogerman.ui.theme.DuoGermanTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            var darkTheme by remember { mutableStateOf(false) }

            DuoGermanTheme(darkTheme = darkTheme) {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Column(){
                        Row(){
                            ThemeSwitcher(darkTheme = darkTheme ) {
                                darkTheme = !darkTheme
                            }
                        }
                        Row(){
                            AppNavigation()
                        }
                    }

                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    DuoGermanTheme {
        AppNavigation()
    }
}

@Composable
fun ThemeSwitcher(darkTheme: Boolean, onThemeChange : () -> Unit) {
    Switch(
        checked = darkTheme,
        onCheckedChange = { onThemeChange() },
        colors = SwitchDefaults.colors(
            checkedTrackColor = MaterialTheme.colorScheme.secondaryContainer,
            checkedThumbColor = MaterialTheme.colorScheme.primary,
            uncheckedTrackColor = MaterialTheme.colorScheme.secondaryContainer,
            uncheckedThumbColor = MaterialTheme.colorScheme.primary
        )
    )
}