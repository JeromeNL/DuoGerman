package nl.joramkwetters.duogerman.Network
import nl.joramkwetters.duogerman.Data.NewsResponse
import retrofit2.http.GET

interface TagesschauApiService {
    @GET("api2/news")
    suspend fun getNews(): NewsResponse
}
