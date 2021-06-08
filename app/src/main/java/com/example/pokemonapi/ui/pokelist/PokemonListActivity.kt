package com.example.pokemonapi.ui.pokelist

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pokemonapi.R
import com.example.pokemonapi.ui.pokeinfo.PokemonInfoActivity
import kotlinx.android.synthetic.main.activity_pokemon_list.*

class PokemonListActivity : AppCompatActivity() {

    private lateinit var viewModel: PokemonListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pokemon_list)

        viewModel = ViewModelProvider (this).get(PokemonListViewModel::class.java)

        initUI()
    }

    private fun initUI(){
        recyclerview_poke_list.layoutManager=LinearLayoutManager(this)
        recyclerview_poke_list.adapter = PokemonListAdapter{
            val intent = Intent(this,PokemonInfoActivity::class.java)
            intent.putExtra("id", it)
            startActivity(intent)
        }

        viewModel.getPokemonList()

        viewModel.pokemonList.observe( this, Observer { list ->

            (recyclerview_poke_list.adapter as PokemonListAdapter).setData(list)

        } )

    }
}