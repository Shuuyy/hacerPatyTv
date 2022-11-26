package com.example.myapplicationtv.ui.main

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.leanback.app.BrowseSupportFragment
import androidx.leanback.widget.*
import androidx.lifecycle.lifecycleScope
import com.example.myapplicationtv.R
import com.example.myapplicationtv.ui.data.MoviesRepository
import com.example.myapplicationtv.ui.data.server.RemoteConnection
import com.example.myapplicationtv.ui.data.server.toDomainMovie
import com.example.myapplicationtv.ui.detail.DetailActivity
import com.example.myapplicationtv.ui.domain.Movie
import kotlinx.coroutines.launch



class MainFragment : BrowseSupportFragment() {

    private lateinit var  moviesRepository: MoviesRepository

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        moviesRepository = MoviesRepository(getString(R.string.api_key))
        title = "Peliculas"
        viewLifecycleOwner.lifecycleScope.launch(){
            adapter=buildAdapter();
        }
      onItemViewClickedListener= OnItemViewClickedListener{_,movie,_,_->
          val intent =  Intent(requireContext(),DetailActivity::class.java).apply {
              putExtra(DetailActivity.MOVIE_EXTRA,movie as Movie)
          }
          startActivity(intent)
      }


    }
   private suspend fun buildAdapter(): ArrayObjectAdapter {
        val rowsAdapter = ArrayObjectAdapter(ListRowPresenter())
        val cardPresenter = CardPresenter()
        moviesRepository.getCategories().forEach { (category, movies) ->
            val listRowAdapter = ArrayObjectAdapter(cardPresenter)
            listRowAdapter.addAll(0, movies)

            val header = HeaderItem(category.ordinal.toLong(), category.name)
            rowsAdapter.add(ListRow(header, listRowAdapter))
        }
        return rowsAdapter
    }

}

