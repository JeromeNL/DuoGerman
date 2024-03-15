package nl.joramkwetters.duogerman.ViewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
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
                // Filter artikelen waarbij 'topline' en 'firstSentence' niet null zijn
                val validNewsItems = newsResponse.news?.filterNotNull()?.filter {
                    it.topline != null &&  it.firstSentence != ""
                }
                if (validNewsItems != null) {
                    _newsState.value = validNewsItems
                }
            } catch (e: Exception) {
                // Foutafhandeling toevoegen
            }
        }
    }
}
