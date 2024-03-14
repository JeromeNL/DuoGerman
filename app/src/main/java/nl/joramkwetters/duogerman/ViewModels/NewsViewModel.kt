package nl.joramkwetters.duogerman.ViewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import nl.joramkwetters.duogerman.Network.RetrofitInstance
import nl.joramkwetters.duogerman.data.NewsItem

class NewsViewModel : ViewModel() {
    private val _newsState = MutableStateFlow<List<NewsItem>>(emptyList())
    val newsState: StateFlow<List<NewsItem>> = _newsState

    fun fetchNews() {
        viewModelScope.launch {
            try {
                val newsResponse = RetrofitInstance.api.getNews()
                // Filter alle null waarden uit de lijst
                val nonNullNewsItems = newsResponse.news?.filterNotNull()
                if (nonNullNewsItems != null) {
                    _newsState.value = nonNullNewsItems
                }
            } catch (e: Exception) {
                // Foutafhandeling toevoegen
            }
        }
    }
}
