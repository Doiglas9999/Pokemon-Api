package com.example.pokemonapi.ui.pokeinfo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.pokemonapi.R
import com.example.pokemonapi.model.api.Pokemon
import kotlinx.android.synthetic.main.activity_pokemon_info.*

class PokemonInfoActivity : AppCompatActivity() {

    lateinit var viewModel: PokemonInfoViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pokemon_info)

        viewModel = ViewModelProvider(this).get(PokemonInfoViewModel::class.java)

        initUI()

    }

    private fun initUI() {
        val id = intent.extras?.get("id") as Int

        viewModel.getPokemonInfo(id)

        viewModel.pokemonInfo.observe(this,{pokemon ->
            name_pokemon_text_view.text = pokemon.name
            height_pokemon_text_view.text = "Altura: ${pokemon.height/10.0}m"
            weight_pokemon_text_view.text = "Peso: ${pokemon.weight/10.0}kg"

            Glide.with(this).load(pokemon.sprites.frontDefault).into(pokemon_image_view)
        })
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId){
            R.id.share ->{

                val sendIntent: Intent = Intent().apply {
                    action = Intent.ACTION_SEND
                    putExtra(Intent.EXTRA_TEXT, name_pokemon_text_view.text)
                    type = "text/plain"

                }

                val shareIntent = Intent.createChooser(sendIntent, null)
                startActivity(shareIntent)

                return true
            }


            else -> true
        }
    }

}