package com.example.myapplicationtv.ui.domain

enum class Category(val id: String) {
    POPULARES("popularity.desc"),
    NUEVAS("release_date.desc"),
    MEJOR_VOTADAS("vote_average.desc"),
    MAS_TAQUILLERAS("revenue.desc")
}