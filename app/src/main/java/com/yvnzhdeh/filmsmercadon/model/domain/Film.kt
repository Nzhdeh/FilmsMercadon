package com.yvnzhdeh.filmsmercadon.model.domain

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "Films")
data class Film (

    @PrimaryKey
    @SerializedName("id")
    var id: String = "",
    @SerializedName("title") var title: String? = "",
    @SerializedName("original_title") var originalTitle: String? = "",
    @SerializedName("original_title_romanised") var originalTitleRomanised : String? = "",
    @SerializedName("image") var image: String?           = "",
    @SerializedName("movie_banner") var movieBanner            : String?           = "",
    @SerializedName("description") var description            : String?           = "",
    @SerializedName("director") var director               : String?           = "",
    @SerializedName("producer") var producer               : String?           = "",
    @SerializedName("release_date") var releaseDate            : String?           = "",
    @SerializedName("running_time") var runningTime            : String?           = "",
    @SerializedName("rt_score") var rtScore                : String?           = "",
//    @SerializedName("people") var people                 : ArrayList<String> = arrayListOf(),
//    @SerializedName("species") var species                : ArrayList<String> = arrayListOf(),
//    @SerializedName("locations") var locations              : ArrayList<String> = arrayListOf(),
//    @SerializedName("vehicles") var vehicles               : ArrayList<String> = arrayListOf(),
    @SerializedName("url") var url                    : String?           = ""

)

