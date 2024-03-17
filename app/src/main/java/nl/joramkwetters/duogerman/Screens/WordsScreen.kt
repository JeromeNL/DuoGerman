package nl.joramkwetters.duogerman.Screens

import android.content.Context
import android.content.Intent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

@Composable
fun WordsScreen(intent: Intent) {
    val context = LocalContext.current
    val sharedPreferences =
        context.getSharedPreferences("nl.joramkwetters.duogerman", Context.MODE_PRIVATE)
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

    LaunchedEffect(Unit) {
        if (intent.action == Intent.ACTION_SEND && intent.type == "text/plain") {
            val sharedText = intent.getStringExtra(Intent.EXTRA_TEXT) ?: return@LaunchedEffect
            // Gebruik `sharedText` als het Duitse woord.
            // Je kunt de UI hier bijwerken of het woord toevoegen aan je woordenlijst
            newGermanWord = sharedText
        }
    }


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
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp),
            horizontalArrangement = Arrangement.End
        ) {
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
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.onBackground
                )
            ) {
                Text(
                    text = "Toevoegen",
                    color = MaterialTheme.colorScheme.surface
                )
            }
        }
        Text(
            text = "Woordenlijst",
            color = MaterialTheme.colorScheme.secondary,
            modifier = Modifier.padding(top = 20.dp),
            style = TextStyle(
                fontSize = 26.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.secondary,
            )

        )


        LazyColumn(modifier = Modifier.fillMaxSize().padding(top = 8.dp)) {
            itemsIndexed(words) { index, wordPair ->
                var isEditing by remember { mutableStateOf(false) }
                var editedDutchWord by remember { mutableStateOf(wordPair.first) }
                var editedGermanWord by remember { mutableStateOf(wordPair.second) }

                if (isEditing) {
                    Row(
                        modifier = Modifier.fillMaxWidth().padding(8.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        OutlinedTextField(
                            value = editedDutchWord,
                            onValueChange = { editedDutchWord = it },
                            label = { Text("Nederlands woord") },
                            modifier = Modifier.weight(1f)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        OutlinedTextField(
                            value = editedGermanWord,
                            onValueChange = { editedGermanWord = it },
                            label = { Text("Duitse vertaling") },
                            modifier = Modifier.weight(1f)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Button(
                            onClick = {
                                words[index] = editedDutchWord to editedGermanWord
                                isEditing = false
                                val json = gson.toJson(words)
                                sharedPreferences.edit().putString("words_list", json).apply()
                            },
                            colors = ButtonDefaults.buttonColors(
                                containerColor = MaterialTheme.colorScheme.onBackground
                            )
                        ) {
                            Text(
                                text = "Opslaan",
                                color = MaterialTheme.colorScheme.surface,
                            )
                        }
                    }
                } else {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "${wordPair.first} - ${wordPair.second}",
                            modifier = Modifier.weight(1f),
                            color = MaterialTheme.colorScheme.secondary
                        )
                        Row {
                            Button(
                                onClick = { isEditing = true },
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = MaterialTheme.colorScheme.onBackground
                                ),
                            ) {
                                Text(
                                    text = "Bewerk",
                                    color = MaterialTheme.colorScheme.surface
                                )
                            }
                            Spacer(modifier = Modifier.width(8.dp))
                            Button(
                                onClick = {
                                    words.removeAt(index)
                                    val json = gson.toJson(words)
                                    sharedPreferences.edit().putString("words_list", json).apply()
                                },
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = MaterialTheme.colorScheme.onBackground
                                ),

                                ) {
                                Text(
                                    text = "Verwijder",
                                    color = MaterialTheme.colorScheme.surface
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}