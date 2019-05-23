package br.edu.ifpr.jonatas.appnews

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.edu.ifpr.jonatas.appnews.entities.Article
import br.edu.ifpr.jonatas.appnews.entities.News
import br.edu.ifpr.jonatas.appnews.network.ArticleServices
import br.edu.ifpr.jonatas.appnews.ui.ArticleAdapter
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    lateinit var adapter: ArticleAdapter
    lateinit var retrofit: Retrofit
    lateinit var services: ArticleServices

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        retrofitSetup()

        searchFilter.setOnEditorActionListener { v, actionId, event ->
            TODO()
        }
    }

    private fun retrofitSetup() {
        retrofit = Retrofit.Builder()
            .baseUrl("https://newsapi.org/v2/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        services = retrofit.create<ArticleServices>(ArticleServices::class.java)

        services.getNews("br","").enqueue(object : Callback<News> {
            override fun onFailure(call: Call<News>, t: Throwable) {
                Log.e("ERRO", "ERRO ", t)
            }

            override fun onResponse(call: Call<News>, response: Response<News>) {
                val articles = response.body()
                if(articles != null)
                    loadRecyclerView(articles.articles)


            }
        })
    }

    private fun loadRecyclerView(news : List<Article>) {
//        val tasks = taskDao.getAll()
        adapter = ArticleAdapter(news.toMutableList())
        listArticles.adapter = adapter
        listArticles.layoutManager = LinearLayoutManager(
            this,
            RecyclerView.VERTICAL, false
        )
    }
}
