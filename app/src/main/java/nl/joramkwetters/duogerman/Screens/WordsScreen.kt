package nl.joramkwetters.duogerman.Screens

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import nl.joramkwetters.duogerman.data.names

@Composable
fun WordsScreen() {
    val nameList = names.map{
        Category(
            name = it.key.toString(),
            items = it.value
        )
    }
    Box(modifier = Modifier
        .fillMaxSize(),
        contentAlignment = Alignment.Center
    ){
        CategorizedLazyColumn(
            categories = nameList
        )
    }
}


data class Category(
    val name: String,
    val items: List<Pair<String, String>>
)

@Composable
fun CategoryHeader(
    text: String,
    modifier: Modifier = Modifier
) {
    Text(
        text = text,
        fontSize = 16.sp,
        fontWeight = FontWeight.Bold,
        modifier = modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.primaryContainer)
            .padding(16.dp)
    )
}


@Composable
fun CategoryItem(
    textFrom: String,
    textTo: String,
    modifier: Modifier = Modifier
) {

    Row(
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            text = textFrom,
            fontSize = 14.sp,
            modifier = Modifier
                .weight(1f) // Neemt de helft van de beschikbare ruimte in beslag
                .padding(16.dp)
                .background(MaterialTheme.colorScheme.background)
        )
        Text(
            text = textTo,
            fontSize = 14.sp,
            modifier = Modifier
                .weight(1f) // Neemt de helft van de beschikbare ruimte in beslag
                .padding(16.dp)
                .background(MaterialTheme.colorScheme.background)
        )
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CategorizedLazyColumn(
    categories: List<Category>,
    modifier: Modifier = Modifier
) {
    LazyColumn(modifier){
        categories.forEach {category ->
            stickyHeader {
                CategoryHeader(category.name)
            }
            items(category.items) { text ->
                CategoryItem(text.first, text.second)
            }
        }
    }
}