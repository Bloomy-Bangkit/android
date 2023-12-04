package com.capstone.bloomy.ui.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.capstone.bloomy.R
import com.capstone.bloomy.data.model.TodayNewsModel
import com.capstone.bloomy.data.model.TopNewsModel
import com.capstone.bloomy.ui.activity.NewsDetailActivity
import com.capstone.bloomy.ui.adapter.TodayNewsAdapter
import com.capstone.bloomy.ui.adapter.TopNewsAdapter

class DashboardFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_dashboard, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerViewTopNews = view.findViewById<RecyclerView>(R.id.recycler_view_top_news)
        val recyclerViewTodayNews = view.findViewById<RecyclerView>(R.id.recycler_view_today_news)

        recyclerViewTopNews.setHasFixedSize(true)
        recyclerViewTodayNews.setHasFixedSize(true)

        val topNewsList = getListTopNews()
        val todayNewsList = getListTodayNews()

        showTopNewsList(recyclerViewTopNews, topNewsList)
        showTodayNewsList(recyclerViewTodayNews, todayNewsList)
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

    private fun getListTodayNews(): ArrayList<TodayNewsModel> {
        val dataTitleTodayNews = resources.getStringArray(R.array.data_title_today_news)
        val dataDateTodayNews = resources.getStringArray(R.array.data_date_today_news)
        val dataImageUrlTodayNews = resources.getStringArray(R.array.data_image_url_today_news)
        val dataWebUrlTodayNews = resources.getStringArray(R.array.data_web_url_today_news)
        val todayNewsList = ArrayList<TodayNewsModel>()

        val minLength = minOf(dataTitleTodayNews.size, dataDateTodayNews.size, dataImageUrlTodayNews.size, dataWebUrlTodayNews.size)

        for (i in 0 until minLength) {
            val todayNews = TodayNewsModel(dataTitleTodayNews[i], dataDateTodayNews[i], dataImageUrlTodayNews[i], dataWebUrlTodayNews[i])
            todayNewsList.add(todayNews)
        }

        return todayNewsList
    }

    private fun showTopNewsList(recyclerView: RecyclerView, topNewsList: ArrayList<TopNewsModel>) {
        recyclerView.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)

        val topNewsAdapter = TopNewsAdapter(topNewsList)

        recyclerView.adapter = topNewsAdapter

        topNewsAdapter.setOnItemClickCallback(object : TopNewsAdapter.OnItemClickCallback {
            override fun onItemClicked(topNews: TopNewsModel) {
                val detailTopNewsIntent = Intent(requireActivity(), NewsDetailActivity::class.java)
                detailTopNewsIntent.putExtra("selected_top_news", topNews)
                startActivity(detailTopNewsIntent)
            }
        })
    }

    private fun showTodayNewsList(recyclerView: RecyclerView, todayNewsList: ArrayList<TodayNewsModel>) {
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        val todayNewsAdapter = TodayNewsAdapter(todayNewsList)

        recyclerView.adapter = todayNewsAdapter

        todayNewsAdapter.setOnItemClickCallback(object : TodayNewsAdapter.OnItemClickCallback {
            override fun onItemClicked(todayNews: TodayNewsModel) {
                val detailTodayNewsIntent = Intent(requireActivity(), NewsDetailActivity::class.java)
                detailTodayNewsIntent.putExtra("selected_today_news", todayNews)
                startActivity(detailTodayNewsIntent)
            }
        })
    }
}