package com.example.themovie.model

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiTheMovie {
    @GET("movie/popular")
    fun getPopularMovies(
        @Query("api_key") apiKey: String = "ec69d673e213c2849cd207f63b94a622",
        @Query("page") page: Int
    ): Call<RespostaFilme>
}