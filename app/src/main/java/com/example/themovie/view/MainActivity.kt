package com.example.themovie.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.themovie.R
import com.example.themovie.model.Filme
import com.example.themovie.model.RepositorioFilme
import com.example.themovie.viewmodel.FilmesAdapter

class MainActivity : AppCompatActivity() {

    private lateinit var popularFilmes: RecyclerView
    private lateinit var popularFilmesAdapter: FilmesAdapter
    private lateinit var popularFilmesLayoutMgr: LinearLayoutManager

    private var popularFilmesPage = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        popularFilmes = findViewById(R.id.popular_filmes)
        popularFilmes.layoutManager = LinearLayoutManager(
            this,
            LinearLayoutManager.HORIZONTAL,
            false
        )
        popularFilmesAdapter = FilmesAdapter(mutableListOf())
        popularFilmes.adapter = popularFilmesAdapter

        //api_key = ec69d673e213c2849cd207f63b94a622
        RepositorioFilme.getPopularMovies(
            onSuccess = ::onPopularMoviesFetched,
            onError = ::onError
        )
    }

    private fun onPopularMoviesFetched(filmes: MutableList<Filme>) {
        popularFilmesAdapter.updateFilmes(filmes)

    }

    private fun onError() {
        Toast.makeText(this, getString(R.string.error_fetch_movies), Toast.LENGTH_SHORT).show()
    }
}