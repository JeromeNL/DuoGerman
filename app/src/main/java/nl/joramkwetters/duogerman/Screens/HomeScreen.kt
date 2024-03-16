package nl.joramkwetters.duogerman.Screens

import android.content.Context
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
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

    if (words.isNotEmpty()) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = if (!showTranslation) "Wat is de vertaling van: ${words[currentIndex].first}?" else "Vertaling: ${words[currentIndex].second}",
                modifier = Modifier.padding(PaddingValues(bottom = 8.dp)))

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
        Text("Geen woorden om te leren.", modifier = Modifier.padding(16.dp))
    }
}
