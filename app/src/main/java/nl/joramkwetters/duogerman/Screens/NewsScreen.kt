package nl.joramkwetters.duogerman.Screens

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberAsyncImagePainter
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import nl.joramkwetters.duogerman.ViewModels.NewsViewModel
import nl.joramkwetters.duogerman.Data.NewsItem
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Locale

@Composable
fun NewsScreen(newsViewModel: NewsViewModel = viewModel()) {
    val newsList = newsViewModel.newsState.collectAsState().value
    val swipeRefreshState = rememberSwipeRefreshState(isRefreshing = false)

    SwipeRefresh(
        state = swipeRefreshState,
        onRefresh = {
            newsViewModel.fetchNews()
        }
    ) {
        LazyColumn(modifier = Modifier.background(MaterialTheme.colorScheme.surface)) {
            items(newsList) { newsItem ->
                NewsItemView(newsItem)
            }
        }
    }

    LaunchedEffect(key1 = true) {
        newsViewModel.fetchNews()
    }
}

@Composable
fun NewsItemView(newsItem: NewsItem) {
    val context = LocalContext.current
    Box(
        modifier = Modifier
            .padding(8.dp)
            .background(MaterialTheme.colorScheme.background, shape = RoundedCornerShape(6.dp))
            .padding(16.dp)
            .clip(RoundedCornerShape(6.dp))
            .clickable {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(newsItem.shareURL))
            context.startActivity(intent)
        }
    ) {
    Column() {
        newsItem.teaserImage?.imageVariants?.jsonMember16x9512?.let { imageUrl ->
            Image(
                painter = rememberAsyncImagePainter(imageUrl),
                contentDescription = null,
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .fillMaxWidth()
                    .size(200.dp)
                    .clip(RoundedCornerShape(6.dp))
            )
        }
        newsItem.topline?.let {
            Text(
                text = it,
                fontSize = 18.sp,
                textAlign = TextAlign.Left,
                color = MaterialTheme.colorScheme.tertiary,
                modifier = Modifier
                    .padding(top = 4.dp, bottom = 2.dp)
            )
        }
        newsItem.title?.let {
            Text(
                text = it,
                fontSize = 22.sp,
                textAlign = TextAlign.Left,
                color = MaterialTheme.colorScheme.secondary,
                modifier = Modifier
                    .padding(top = 2.dp, bottom = 2.dp)
            )
        }
        newsItem.firstSentence?.let {
            Text(
                text = it,
                fontSize = 14.sp,
                textAlign = TextAlign.Left,
                color = MaterialTheme.colorScheme.secondary,
                modifier = Modifier
                    .padding(top = 4.dp, bottom = 4.dp)
            )
        }
        Row(){
            newsItem.date?.let {
                FormatDate(it)
            }
        }
    }
    }
}

@Composable
fun FormatDate(dateString: String) {
    val inputFormatter = DateTimeFormatter.ISO_OFFSET_DATE_TIME
    val outputFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm", Locale.GERMANY)

    val date = LocalDateTime.parse(dateString, inputFormatter)
    val formattedDate = date.format(outputFormatter)

    Text(
        text = formattedDate,
        fontSize = 10.sp,
        textAlign = TextAlign.Left,
        color = MaterialTheme.colorScheme.primary,
        modifier = Modifier
            .padding(4.dp)
    )
}