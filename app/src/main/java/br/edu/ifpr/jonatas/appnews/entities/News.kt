package br.edu.ifpr.jonatas.appnews.entities

class News(
    var status : String,
    var totalResults: Int,
    var articles: List<Article>
)