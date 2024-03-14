package nl.joramkwetters.duogerman.Screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberAsyncImagePainter
import coil.compose.rememberImagePainter
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import kotlinx.coroutines.launch
import nl.joramkwetters.duogerman.ViewModels.NewsViewModel
import nl.joramkwetters.duogerman.data.NewsItem
import nl.joramkwetters.duogerman.ui.theme.CustomAlmostWhite
import nl.joramkwetters.duogerman.ui.theme.CustomBackgroundLightGray
import nl.joramkwetters.duogerman.ui.theme.CustomTextRed
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Locale
import kotlinx.coroutines.delay
import nl.joramkwetters.duogerman.ui.theme.CustomDefaultRed

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
        LazyColumn(modifier = Modifier.background(CustomBackgroundLightGray)) {
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
    Box(
        modifier = Modifier
            .padding(8.dp)
            .background(CustomAlmostWhite, shape = RoundedCornerShape(6.dp))
            .padding(16.dp)
            .clip(RoundedCornerShape(6.dp))
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
                color = CustomTextRed,
                modifier = Modifier
                    .padding(top = 4.dp, bottom = 2.dp)
            )
        }
        newsItem.title?.let {
            Text(
                text = it,
                fontSize = 22.sp,
                textAlign = TextAlign.Left,
                modifier = Modifier
                    .padding(top = 2.dp, bottom = 2.dp)
            )
        }
        newsItem.firstSentence?.let {
            Text(
                text = it,
                fontSize = 14.sp,
                textAlign = TextAlign.Left,
                modifier = Modifier
                    .padding(top = 4.dp, bottom = 4.dp)
            )
        }
        newsItem.date?.let {
           FormatDate(it)
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
        modifier = Modifier
            .padding(4.dp)
    )
}