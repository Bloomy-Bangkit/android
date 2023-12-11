package com.capstone.bloomy.ui.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.capstone.bloomy.R
import com.capstone.bloomy.data.model.TodayNewsModel
import com.capstone.bloomy.data.model.TopNewsModel
import com.capstone.bloomy.data.remote.currentweather.CurrentWeatherConfig
import com.capstone.bloomy.data.response.ProfileData
import com.capstone.bloomy.databinding.FragmentDashboardBinding
import com.capstone.bloomy.ui.activity.NewsDetailActivity
import com.capstone.bloomy.ui.adapter.TodayNewsAdapter
import com.capstone.bloomy.ui.adapter.TopNewsAdapter
import com.capstone.bloomy.ui.viewmodel.ProfileViewModel
import com.capstone.bloomy.ui.viewmodel.SailDecisionViewModel
import com.capstone.bloomy.ui.viewmodelfactory.ProfileViewModelFactory
import com.capstone.bloomy.ui.viewmodelfactory.SailDecisionViewModelFactory
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.io.IOException

class DashboardFragment : Fragment() {

    private var _binding: FragmentDashboardBinding? = null

    private val sailDecisionViewModelFactory: SailDecisionViewModelFactory = SailDecisionViewModelFactory.getInstance()
    private val sailDecisionViewModel: SailDecisionViewModel by viewModels { sailDecisionViewModelFactory }
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val profileViewModelFactory: ProfileViewModelFactory = ProfileViewModelFactory.getInstance(requireContext())
        val profileViewModel: ProfileViewModel by viewModels { profileViewModelFactory }

        profileViewModel.getProfile()

        profileViewModel.profile.observe(viewLifecycleOwner) { profile ->
            setProfile(profile)
        }

        val recyclerViewTopNews = view.findViewById<RecyclerView>(R.id.recycler_view_top_news)
        val recyclerViewTodayNews = view.findViewById<RecyclerView>(R.id.recycler_view_today_news)

        recyclerViewTopNews.setHasFixedSize(true)
        recyclerViewTodayNews.setHasFixedSize(true)

        val topNewsList = getListTopNews()
        val todayNewsList = getListTodayNews()

        showTopNewsList(recyclerViewTopNews, topNewsList)
        showTodayNewsList(recyclerViewTodayNews, todayNewsList)

        getCurrentWeather()

        sailDecisionViewModel.sailDecision(0, 0, 0, 0)
        sailDecisionViewModel.sailDecisionResponse.observe(viewLifecycleOwner) { response ->
            val code = response?.status?.code
            val sailDecisionData = response?.status?.sailDecisionData
            val message = response?.status?.message.toString()

            if (code != null) {
                if (code == 405) {
                    Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
                    sailDecisionViewModel.defaultSailDecision()
                } else {
                    binding.tvDescriptionSailDecision.text = sailDecisionData?.decision
                    sailDecisionViewModel.defaultSailDecision()
                }
            }
        }
    }

    private fun setProfile(profile: ProfileData) {
        with(binding) {
            Glide.with(this@DashboardFragment)
                .load(profile.photo)
                .into(imgProfileDashboard)

            val tvHelloUsernameDashboardText = if (profile.username.isNotEmpty()) {
            "Hello, ${profile.username}"
            } else {
                getString(R.string.tv_hello_username)
            }

            tvHelloUsernameDashboard.text = tvHelloUsernameDashboardText
        }
    }

    @OptIn(DelicateCoroutinesApi::class)
    private fun getCurrentWeather() {
        GlobalScope.launch(Dispatchers.IO) {
            val response = try {
                CurrentWeatherConfig.api.getCurrentWeather("jakarta", "metric", "29d730ce50152e2ad59f0da578b33a43")
            } catch (e: IOException) {
                Toast.makeText(context, "App Error ${e.message}", Toast.LENGTH_SHORT).show()
                return@launch
            } catch (e: HttpException) {
                Toast.makeText(context, "Http Error ${e.message}", Toast.LENGTH_SHORT).show()
                return@launch
            }

            if (response.isSuccessful && response.body() != null) {
                withContext(Dispatchers.Main) {
                    val weatherMain = response.body()!!.weather?.get(0)?.main ?: "N/A"

                    val temperature = response.body()!!.main?.temp as? Double ?: 0.0
                    val atmosphere = when {
                        temperature >= 30.0 -> "Hot"
                        temperature >= 20.0 && temperature < 30.0 -> "Moderate"
                        else -> "Cold"
                    }

                    val windDirectionDegrees = response.body()!!.wind?.deg ?: 0
                    val windDirectionCardinal = convertWindDirection(windDirectionDegrees)

                    binding.tvOutlookSailDecision.text = weatherMain
                    binding.tvTemperatureSailDecision.text = "${response.body()!!.main?.temp}ºC"
                    binding.tvHumiditySailDecision.text = "${response.body()!!.main?.humidity}%"
                    binding.tvWindSpeedSailDecision.text= "${response.body()!!.wind?.speed}m/s"
                    binding.tvWindDirectionSailDecision.text = windDirectionCardinal
                }
            }
        }
    }

    private fun convertWindDirection(degrees: Int): String {
        val directions = arrayOf(
            "N", "NNE", "NE", "ENE",
            "E", "ESE", "SE", "SSE",
            "S", "SSW", "SW", "WSW",
            "W", "WNW", "NW", "NNW", "N"
        )

        val index = ((degrees + 11.25) % 360 / 22.5).toInt()
        return directions[index]
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