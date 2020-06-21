package com.example.themovie.model

import android.util.Log
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RepositorioFilme {

    //Obtendo retrofit atravez do singleton
    companion object {

        private val apiTheMovie: ApiTheMovie

        init {
            val retrofit = Retrofit.Builder()
                .baseUrl("https://api.themoviedb.org/3/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            apiTheMovie = retrofit.create(ApiTheMovie::class.java)
        }

        fun getPopularMovies(page: Int = 1,
                             onSuccess: (movies: MutableList<Filme>) -> Unit,
                             onError: () -> Unit) {

            apiTheMovie.getPopularMovies(page = page)
                .enqueue(object : Callback<RespostaFilme> {

                    override fun onFailure(call: Call<RespostaFilme>, t: Throwable) {
                        onError.invoke()
                    }

                    override fun onResponse(
                        call: Call<RespostaFilme>,
                        response: Response<RespostaFilme>
                    ) {
                        if (response.isSuccessful) {
                            val respostaBody = response.body()

                            if (respostaBody != null) {
                                onSuccess.invoke(respostaBody.results)
                            } else {
                                onError.invoke()
                            }
                        } else {
                            onError.invoke()
                        }
                    }

                })
        }

    }
}