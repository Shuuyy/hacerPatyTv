package com.example.myapplicationtv.ui.data

import com.example.myapplicationtv.ui.data.server.RemoteConnection
import com.example.myapplicationtv.ui.data.server.toDomainMovie
import com.example.myapplicationtv.ui.domain.Category
import com.example.myapplicationtv.ui.domain.Movie

class MoviesRepository(private val apiKey:String) {
    suspend fun getCategories():Map<Category,List<Movie>>{
        return  Category.values().associateWith { category ->
            RemoteConnection.service.listPopularMovies(apiKey,category.id).results
                .map { it.toDomainMovie() }
        }
    }
}