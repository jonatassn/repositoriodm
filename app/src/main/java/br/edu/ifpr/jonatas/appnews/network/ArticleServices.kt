package br.edu.ifpr.jonatas.appnews.network

import br.edu.ifpr.jonatas.appnews.entities.News
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface ArticleServices {
    @Headers("Accept: application/json")
    @GET( "top-headlines")
    fun getNews(
        @Query("country")
        country : String,

        @Query("q")
        filter : String,

        @Query("apiKey")
        apiKey : String = "b33df61620d748f999485a04c5e28d4d"
    ) : Call<News>
}
