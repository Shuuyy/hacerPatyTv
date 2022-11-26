package com.example.myapplicationtv.ui.detail

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.View
import androidx.leanback.app.DetailsSupportFragment
import androidx.leanback.app.DetailsSupportFragmentBackgroundController
import androidx.leanback.widget.AbstractDetailsDescriptionPresenter
import androidx.leanback.widget.ArrayObjectAdapter
import androidx.leanback.widget.DetailsOverviewRow
import androidx.leanback.widget.FullWidthDetailsOverviewRowPresenter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.example.myapplicationtv.ui.domain.Movie

class DetailFragment :DetailsSupportFragment(){
    private val detailsBackgroundState = DetailsBackgroundState(this)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        val movie= requireActivity()
            .intent.getParcelableExtra<Movie>(DetailActivity.MOVIE_EXTRA)
            ?:throw java.lang.IllegalStateException("Error en la pelicula")

        val presenter = FullWidthDetailsOverviewRowPresenter(detailsDescription())

        val rowsAdapter = ArrayObjectAdapter(presenter)
        val detailsOverviewRow = DetailsOverviewRow(movie)
        Glide.with(requireContext())
            .load(movie.poster)
            .centerCrop()
            .into(object :CustomTarget<Drawable>(){
                override fun onResourceReady(
                    resource: Drawable,
                    transition: Transition<in Drawable>?
                ) {
                    detailsOverviewRow.imageDrawable=resource;
                }

                override fun onLoadCleared(placeholder: Drawable?) {

                }

            })

        rowsAdapter.add(detailsOverviewRow)
        adapter = rowsAdapter
        detailsBackgroundState.loadUrl(movie.backdrop)
    }
}

class detailsDescription:AbstractDetailsDescriptionPresenter(){
    override fun onBindDescription(vh: ViewHolder, item: Any?) {
     val movie  = item as Movie
        vh.title.text = movie.title
        vh.subtitle.text=movie.releaseDate
        vh.body.text=movie.summary

    }
}

class DetailsBackgroundState(private val fragment: DetailsSupportFragment){
    private val target = object :CustomTarget<Bitmap>(){
        override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) {
            detailsBackground.coverBitmap = resource
            detailsBackground.enableParallax()
        }

        override fun onLoadCleared(placeholder: Drawable?) {

        }

    }

    private val detailsBackground by lazy{ DetailsSupportFragmentBackgroundController(fragment)}
    fun loadUrl(url:String?){
        Glide.with(fragment.requireContext())
            .asBitmap()
            .centerCrop()
            .load(url)
            .into(target)
    }
}