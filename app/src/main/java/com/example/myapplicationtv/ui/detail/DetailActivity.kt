package com.example.myapplicationtv.ui.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import com.example.myapplicationtv.R

class DetailActivity : FragmentActivity() {

    companion object{
        const val MOVIE_EXTRA="extra:movie"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
    }
}