package com.capstone.bloomy.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.capstone.bloomy.data.response.FavoriteData
import com.capstone.bloomy.databinding.ActivityFavoriteBinding
import com.capstone.bloomy.ui.adapter.FavoriteAdapter
import com.capstone.bloomy.ui.viewmodel.FavoriteViewModel
import com.capstone.bloomy.ui.viewmodelfactory.FavoriteViewModelFactory

class FavoriteActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFavoriteBinding

    private val favoriteViewModelFactory: FavoriteViewModelFactory = FavoriteViewModelFactory.getInstance(this@FavoriteActivity)
    private val favoriteViewModel: FavoriteViewModel by viewModels { favoriteViewModelFactory }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavoriteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.materialToolBarFavorite)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        favoriteViewModel.getFavorite()
        favoriteViewModel.favorite.observe(this) { favorite ->
            setFavorite(favorite)
        }
    }

    override fun onResume() {
        super.onResume()

        favoriteViewModel.getFavorite()
        favoriteViewModel.favorite.observe(this) { favorite ->
            setFavorite(favorite)
        }
    }

    private fun setFavorite(favoriteData: List<FavoriteData>) {
        val layoutManager = GridLayoutManager(this, 2)
        binding.recyclerViewFavorite.layoutManager = layoutManager

        val adapter = FavoriteAdapter()
        adapter.submitList(favoriteData)
        binding.recyclerViewFavorite.adapter = adapter

        val itemCount = adapter.itemCount
        if (itemCount != 0) {
            binding.tvDescriptionFavorite.text = "$itemCount Product"
        }
    }
}