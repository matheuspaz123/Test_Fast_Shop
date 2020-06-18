package com.example.themovie.model

import com.google.gson.annotations.SerializedName

data class RespostaFilme (
    @SerializedName("page") val page: Int,
    @SerializedName("results") val results: List<Filme>,
    @SerializedName("total_pages") val totalPages: Int
)