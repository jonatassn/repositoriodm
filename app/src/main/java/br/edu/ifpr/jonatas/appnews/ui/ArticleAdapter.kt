package br.edu.ifpr.jonatas.appnews.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.edu.ifpr.jonatas.appnews.R
import br.edu.ifpr.jonatas.appnews.entities.Article
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.article_item.view.*

class ArticleAdapter(var articles : MutableList<Article>) : RecyclerView.Adapter<ArticleAdapter.ArticleViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ArticleViewHolder(
            LayoutInflater
                .from(parent.context)
                .inflate(R.layout.article_item,parent,false)
        )

    override fun getItemCount() = articles.size

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        holder.fillUi(articles[position])
    }

    inner class ArticleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun fillUi(article: Article) {
            itemView.tvArticleTitle.text = article.title
            itemView.tvArticleAuthor.text = article.author
            itemView.tvArticleDescription.text = article.description
            itemView.tvArticleSource.text = article.source.name
            itemView.tvArticleDate.text = article.publishedAt
            Picasso.get().load(article.urlToImage).into(itemView.imgArticle)
        }
    }
}