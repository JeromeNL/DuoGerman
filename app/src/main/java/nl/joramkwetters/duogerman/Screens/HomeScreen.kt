package nl.joramkwetters.duogerman.Screens

import android.content.Context
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

@Composable
fun HomeScreen() {
    val context = LocalContext.current
    val sharedPreferences = context.getSharedPreferences("nl.joramkwetters.duogerman", Context.MODE_PRIVATE)
    val gson = Gson()
    val type = object : TypeToken<MutableList<Pair<String, String>>>() {}.type
    val savedWordsString = sharedPreferences.getString("words_list", null)

    val words = remember {
        if (savedWordsString != null) {
            gson.fromJson<MutableList<Pair<String, String>>>(savedWordsString, type)
        } else {
            mutableListOf<Pair<String, String>>()
        }
    }

    var currentIndex by remember { mutableStateOf(0) }
    var showTranslation by remember { mutableStateOf(false) }

    Box(modifier = Modifier.fillMaxSize()) {
        if (words.isNotEmpty()) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.align(Alignment.Center)
            ) {
                Text(
                    text = if (!showTranslation) "Wat is de vertaling van: " else "Vertaling: ",
                    modifier = Modifier.padding(PaddingValues(bottom = 8.dp)),
                    style = TextStyle(fontSize = 25.sp),
                    color = MaterialTheme.colorScheme.secondary,
                )
                Text(
                    text = if (!showTranslation) words[currentIndex].first else words[currentIndex].second,
                    modifier = Modifier.padding(PaddingValues(bottom = 8.dp)),
                    style = TextStyle(
                        fontSize = 45.sp,
                        fontWeight = FontWeight.Bold
                    ),
                    color = MaterialTheme.colorScheme.tertiary,
                )

                Button(
                    onClick = {
                        if (showTranslation) {
                            currentIndex = (currentIndex + 1) % words.size
                        }
                        showTranslation = !showTranslation
                    },
                    modifier = Modifier.padding(PaddingValues(top = 8.dp))
                ) {
                    Text(if (!showTranslation) "Toon vertaling" else "Volgende")
                }
            }
        } else {
            Text("Geen woorden om te leren.", modifier = Modifier.align(Alignment.Center))
        }
    }
}
