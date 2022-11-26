package com.example.myapplicationtv.ui.main

import android.view.ViewGroup
import androidx.leanback.widget.ImageCardView
import androidx.leanback.widget.Presenter
import com.example.myapplicationtv.ui.common.loadUrl
import com.example.myapplicationtv.ui.domain.Movie


class CardPresenter : Presenter(){

    override fun onCreateViewHolder(parent: ViewGroup): ViewHolder {
        val cardView = ImageCardView(parent.context).apply {
            isFocusable=true
            isFocusableInTouchMode=true
            setMainImageDimensions(176,313)
        }

        return  ViewHolder(cardView)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, item: Any?) {

        val movie = item as Movie
        with( viewHolder.view as ImageCardView){
            titleText = movie.title
            contentText = movie.releaseDate

            mainImageView.loadUrl(movie.poster)

        }



    }

    override fun onUnbindViewHolder(viewHolder: ViewHolder) {
        with(viewHolder.view as ImageCardView){
            mainImage=null
        }
    }
}