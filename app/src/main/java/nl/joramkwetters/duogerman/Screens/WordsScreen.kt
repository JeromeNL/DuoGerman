package nl.joramkwetters.duogerman.Screens

import android.content.Context
import android.content.Intent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

@Composable
fun WordsScreen(intent: Intent) {
    val context = LocalContext.current
    val sharedPreferences = context.getSharedPreferences("nl.joramkwetters.duogerman", Context.MODE_PRIVATE)
    val gson = Gson()
    val type = object : TypeToken<MutableList<Pair<String, String>>>() {}.type
    val savedWordsString = sharedPreferences.getString("words_list", null)
    val words = remember {
        mutableStateListOf<Pair<String, String>>().apply {
            if (savedWordsString != null) {
                addAll(gson.fromJson(savedWordsString, type))
            }
        }
    }

    var newDutchWord by remember { mutableStateOf("") }
    var newGermanWord by remember { mutableStateOf("") }

    Column(modifier = Modifier.padding(16.dp)) {
        OutlinedTextField(
            value = newDutchWord,
            onValueChange = { newDutchWord = it },
            label = { Text("Nederlands woord") },
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = newGermanWord,
            onValueChange = { newGermanWord = it },
            label = { Text("Duitse vertaling") },
            modifier = Modifier.fillMaxWidth().padding(top = 8.dp)
        )
        Button(
            onClick = {
                if (newDutchWord.isNotBlank() && newGermanWord.isNotBlank()) {
                    words.add(Pair(newDutchWord, newGermanWord))
                    newDutchWord = ""
                    newGermanWord = ""
                    val json = gson.toJson(words)
                    sharedPreferences.edit().putString("words_list", json).apply()
                }
            },
            modifier = Modifier.padding(top = 8.dp)
        ) {
            Text("Toevoegen")
        }

        LazyColumn(modifier = Modifier.fillMaxSize().padding(top = 8.dp)) {
            itemsIndexed(words) { index, wordPair ->
                var isEditing by remember { mutableStateOf(false) }
                var editedDutchWord by remember { mutableStateOf(wordPair.first) }
                var editedGermanWord by remember { mutableStateOf(wordPair.second) }

                if (isEditing) {
                    Row(modifier = Modifier.fillMaxWidth().padding(8.dp)) {
                        OutlinedTextField(
                            value = editedDutchWord,
                            onValueChange = { editedDutchWord = it },
                            label = { Text("Nederlands woord") },
                            modifier = Modifier.weight(1f)
                        )
                        OutlinedTextField(
                            value = editedGermanWord,
                            onValueChange = { editedGermanWord = it },
                            label = { Text("Duitse vertaling") },
                            modifier = Modifier.weight(1f)
                        )
                        Button(onClick = {
                            words[index] = editedDutchWord to editedGermanWord
                            isEditing = false
                            val json = gson.toJson(words)
                            sharedPreferences.edit().putString("words_list", json).apply()
                        }) {
                            Text("Opslaan")
                        }
                    }
                } else {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(text = "${wordPair.first} - ${wordPair.second}")
                        Button(onClick = { isEditing = true }) {
                            Text("Bewerk")
                        }
                        Button(onClick = {
                            words.removeAt(index)
                            val json = gson.toJson(words)
                            sharedPreferences.edit().putString("words_list", json).apply()
                        }) {
                            Text("Verwijder")
                        }
                    }
                }
            }
        }
    }
}
