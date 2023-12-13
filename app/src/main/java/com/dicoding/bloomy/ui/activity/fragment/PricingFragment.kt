package com.dicoding.bloomy.ui.activity.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.dicoding.bloomy.R
import android.app.Dialog
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.dicoding.bloomy.ui.activity.data.prediction.PricePredictionRequest
import com.dicoding.bloomy.ui.activity.data.prediction.PricePredictionResponse
import com.dicoding.bloomy.ui.activity.data.prediction.PricingService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class PricingFragment : Fragment() {

    private val apiServiceUrl = "https://api-service-vixypb3qiq-uc.a.run.app"
    private lateinit var loadingDialog: Dialog

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.activity_fish_pricing, container, false)

        view.findViewById<Button>(R.id.buttonpricing)?.setOnClickListener {
            requestPricePrediction()
        }

        loadingDialog = Dialog(requireContext())
        loadingDialog.setContentView(R.layout.loading_dialog)
        loadingDialog.setCancelable(false)
        return view
    }

    private fun requestPricePrediction() {
        loadingDialog.show()
        val retrofit = Retrofit.Builder()
            .baseUrl(apiServiceUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val service = retrofit.create(PricingService::class.java)
        val requestBody = PricePredictionRequest(
            Grade = 1.0f,
            catchingMethod = 1.0f,
            sustainability = 1.0f,
            acctualPrice = 1.0f
        )
        val call = service.predictPrice(requestBody)
        call.enqueue(object : Callback<PricePredictionResponse> {
            override fun onResponse(
                call: Call<PricePredictionResponse>,
                response: Response<PricePredictionResponse>
            ) {
                loadingDialog.dismiss()
                if (response.isSuccessful) {
                    val data = response.body()?.data
                    val predictedPrice = data?.price ?: 0

                    showPriceRecommendationDialog(predictedPrice)
                } else {
                    handleUnsuccessfulResponse(response.code(), response.message())
                }
            }
            override fun onFailure(call: Call<PricePredictionResponse>, t: Throwable) {
                loadingDialog.dismiss()
                handleFailure(t)
            }
        })
    }

    private fun handleUnsuccessfulResponse(code: Int, message: String) {
        Log.e("API Response", "Unsuccessful response. Code: $code, Message: $message")
        showToast("Unsuccessful response. Code: $code, Message: $message")
    }

    private fun handleFailure(t: Throwable) {
        Log.e("API Request", "Request failed. Error: ${t.message}")
        showToast("Request failed. Error: ${t.message}")
    }

    private fun showToast(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }

    private fun showPriceRecommendationDialog(predictedPrice: Int) {
        val dialog = Dialog(requireContext())
        dialog.setContentView(R.layout.item_fish_pricing)

        val titleTextView: TextView = dialog.findViewById(R.id.title)
        val hargaTextView: TextView = dialog.findViewById(R.id.harga)
        val predictAgainButton: Button = dialog.findViewById(R.id.predictAgainButton)
        val recommendedPrice = "Rp $predictedPrice/kg"
        titleTextView.text = "Rekomendasi Harga Ikan"
        hargaTextView.text = recommendedPrice

        predictAgainButton.setOnClickListener {
            clearInputFields()
            dialog.dismiss()
        }
        dialog.show()
    }

    private fun clearInputFields() {
        val speciesEditText = view?.findViewById<EditText>(R.id.speciesText)
        val actualPriceEditText = view?.findViewById<EditText>(R.id.actualpricetext)
        val gradeEditText = view?.findViewById<EditText>(R.id.gradeText)
        val catchingMethodEditText = view?.findViewById<EditText>(R.id.catchingmethodtext)
        val seasonalityEditText = view?.findViewById<EditText>(R.id.seasonalityText)
        val sustainabilityEditText = view?.findViewById<EditText>(R.id.sustainabilityText)

        speciesEditText?.setText("")
        actualPriceEditText?.setText("")
        gradeEditText?.setText("")
        catchingMethodEditText?.setText("")
        seasonalityEditText?.setText("")
        sustainabilityEditText?.setText("")
    }
}
