package com.example.themovie.viewmodel

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.example.themovie.R
import com.example.themovie.model.Filme

class FilmesAdapter(
    private var filmes: MutableList<Filme>
) : RecyclerView.Adapter<FilmesAdapter.FilmeViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilmeViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.item_filme, parent, false)
        return FilmeViewHolder(view)
    }

    override fun getItemCount(): Int = filmes.size

    override fun onBindViewHolder(holder: FilmeViewHolder, position: Int) {
        holder.bind(filmes[position])
    }

    fun updateFilmes(filmes: MutableList<Filme>) {
        this.filmes = filmes
        notifyDataSetChanged()
    }
    fun appendMovies(filmes: MutableList<Filme>) {
        this.filmes.addAll(filmes)
        notifyItemRangeInserted(
            this.filmes.size,
            filmes.size - 1
        )
    }

    inner class FilmeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val poster: ImageView = itemView.findViewById(R.id.item_movie_poster)

        fun bind(filme: Filme) {
            Glide.with(itemView)
                .load("https://image.tmdb.org/t/p/w342${filme.posterPath}")
                .transform(CenterCrop())
                .into(poster)
        }
    }
}