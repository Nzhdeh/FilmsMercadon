package com.yvnzhdeh.filmsmercadon.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.yvnzhdeh.filmsmercadon.R
import com.yvnzhdeh.filmsmercadon.model.domain.Film
import com.yvnzhdeh.filmsmercadon.ui.holders.ListFilmItemHolder

class ListFilmsAdapter (private val items: List<Film>, private val listener: (Film) -> Unit) : RecyclerView.Adapter<ListFilmItemHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ListFilmItemHolder
    {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.row_item_list_films, parent, false)

        return ListFilmItemHolder(view)
    }

    override fun onBindViewHolder(holder: ListFilmItemHolder, position: Int)
    {
        holder.bind(items[position], listener)
    }

    override fun getItemCount() = items.size
}