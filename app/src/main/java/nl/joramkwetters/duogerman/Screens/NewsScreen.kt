package nl.joramkwetters.duogerman.Screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.launch
import nl.joramkwetters.duogerman.ViewModels.NewsViewModel
import nl.joramkwetters.duogerman.data.NewsItem

@Composable
fun NewsScreen(newsViewModel: NewsViewModel = viewModel()) {
    val newsList = newsViewModel.newsState.collectAsState().value

    // Gebruik LaunchedEffect om de coroutine uit te voeren
    LaunchedEffect(key1 = true) {
        newsViewModel.fetchNews()
    }

    LazyColumn {
        items(newsList) { newsItem ->
            // Zorg ervoor dat newsItem niet null is of gebruik een fallback
            NewsItemView(newsItem)
        }
    }
}

@Composable
fun NewsItemView(newsItem: NewsItem) {
    Column {
        newsItem.title?.let { Text(text = it) }
        newsItem.date?.let { Text(text = it) }
        // Hier kun je de afbeelding toevoegen met een Image composable
    }
}
