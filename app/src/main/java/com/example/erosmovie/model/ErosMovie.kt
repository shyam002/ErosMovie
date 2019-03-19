package com.example.erosmovie.model

import com.google.gson.annotations.SerializedName

data class ErosMovie(
    @SerializedName("page")
    var page: Int?,
    @SerializedName("results")
    var results: List<Result?>?,
    @SerializedName("total_pages")
    var totalPages: Int?,
    @SerializedName("total_results")
    var totalResults: Int?
)