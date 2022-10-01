package com.yvnzhdeh.filmsmercadon.ui.holders

import android.view.View
import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.yvnzhdeh.filmsmercadon.R
import com.yvnzhdeh.filmsmercadon.model.domain.Film

class ListFilmItemHolder (itemView: View) : RecyclerView.ViewHolder(itemView)
{
    private val tvTitleFilmItem: TextView
    private val tvDirectorFilmItem: TextView
    private val tvDateFilmItem: TextView
    private val ivImageFilmItem: AppCompatImageView

    init {
        tvTitleFilmItem = itemView.findViewById(R.id.tvTitleFilmItem)
        tvDirectorFilmItem = itemView.findViewById(R.id.tvDirectorFilmItem)
        tvDateFilmItem = itemView.findViewById(R.id.tvDateFilmItem)
        ivImageFilmItem = itemView.findViewById(R.id.ivImageFilmItem)
    }

    fun bind(item: Film, listener: (Film) -> Unit) = with(itemView) {
        tvTitleFilmItem.text = item.title
        tvDirectorFilmItem.text = item.director
        tvDateFilmItem.text = item.releaseDate
        Picasso.with(context).load(item.image).into(ivImageFilmItem)

        setOnClickListener { listener(item) }
    }
}