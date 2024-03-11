package nl.joramkwetters.duogerman.ViewModels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import nl.joramkwetters.duogerman.data.APIService
import nl.joramkwetters.duogerman.data.NewsArticle

class NewsArticleViewModel : ViewModel() {
    private val _todoList = mutableStateListOf<NewsArticle>()
    var errorMessage: String by mutableStateOf("")
    val todoList: List<NewsArticle>
        get() = _todoList

    fun getTodoList() {
        viewModelScope.launch {
            val apiService = APIService.getInstance()
            try {
                _todoList.clear()
                _todoList.addAll(apiService.getNewsArticles())

            } catch (e: Exception) {
                errorMessage = e.message.toString()
            }
        }
    }
}