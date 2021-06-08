package com.example.pokemonapi.ui.pokelist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.pokemonapi.R
import com.example.pokemonapi.model.api.PokemonResult
import kotlinx.android.synthetic.main.card_pokemon_search.view.*

class PokemonListAdapter(val pokemonClick: (Int) -> Unit ):RecyclerView.Adapter<PokemonListAdapter.SearchViewHolder>() {
    var pokemonList : List <PokemonResult> = emptyList<PokemonResult>()

    fun setData(list : List<PokemonResult>){
        pokemonList = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        return SearchViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.card_pokemon_search,parent,false))
    }

    override fun getItemCount(): Int {
        return pokemonList.size
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        val pokemon = pokemonList [position]
        holder.itemView.text_pokemon.text="#${position + 1} - ${pokemon.name}"

        holder.itemView.setOnClickListener{pokemonClick(position+1)}
    }

    class SearchViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView)
}