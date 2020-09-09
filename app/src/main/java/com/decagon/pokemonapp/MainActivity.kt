package com.decagon.pokemonapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.decagon.pokemonapp.overview.OverviewFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


            supportFragmentManager.beginTransaction()
                    .replace(R.id.pokemonFrag, OverviewFragment())
                    .addToBackStack(null)
                    .commit()
    }
}