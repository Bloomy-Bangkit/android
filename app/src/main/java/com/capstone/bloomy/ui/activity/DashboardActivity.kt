package com.capstone.bloomy.ui.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.capstone.bloomy.R
import com.capstone.bloomy.data.model.TopNewsModel
import com.capstone.bloomy.databinding.ActivityDashboardBinding
import com.capstone.bloomy.ui.adapter.TopNewsAdapter

class DashboardActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDashboardBinding

    private val topNewsList = ArrayList<TopNewsModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDashboardBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.recyclerViewTopNews.setHasFixedSize(true)

        topNewsList.addAll(getListTopNews())
        showTopNewsList()
    }

    private fun getListTopNews(): ArrayList<TopNewsModel> {
        val dataTitleTopNews = resources.getStringArray(R.array.data_title_top_news)
        val dataImageUrlTopNews = resources.getStringArray(R.array.data_image_url_top_news)
        val dataWebUrlTopNews = resources.getStringArray(R.array.data_web_url_top_news)
        val topNewsList = ArrayList<TopNewsModel>()

        val minLength = minOf(dataTitleTopNews.size, dataImageUrlTopNews.size, dataWebUrlTopNews.size)

        for (i in 0 until minLength) {
            val topNews = TopNewsModel(dataTitleTopNews[i], dataImageUrlTopNews[i], dataWebUrlTopNews[i])
            topNewsList.add(topNews)
        }

        return topNewsList
    }

    private fun showTopNewsList() {
        binding.recyclerViewTopNews.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        val topNewsAdapter = TopNewsAdapter(topNewsList)

        binding.recyclerViewTopNews.adapter = topNewsAdapter

        topNewsAdapter.setOnItemClickCallback(object : TopNewsAdapter.OnItemClickCallback {
            override fun onItemClicked(topNews: TopNewsModel) {
                val detailTopNewsIntent = Intent(this@DashboardActivity, NewsDetailActivity::class.java)
                detailTopNewsIntent.putExtra("selected_top_news", topNews)
                startActivity(detailTopNewsIntent)
            }
        })
    }
}