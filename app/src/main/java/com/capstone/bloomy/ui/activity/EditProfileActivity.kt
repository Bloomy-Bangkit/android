package com.capstone.bloomy.ui.activity

import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.view.MenuItem
import android.view.View
import android.view.Window
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import com.bumptech.glide.Glide
import com.capstone.bloomy.R
import com.capstone.bloomy.data.remote.indonesialocation.IndonesiaLocationConfig
import com.capstone.bloomy.data.response.KotaResponse
import com.capstone.bloomy.data.response.ProfileData
import com.capstone.bloomy.data.response.ProvinsiResponse
import com.capstone.bloomy.data.state.ResultState
import com.capstone.bloomy.databinding.ActivityEditProfileBinding
import com.capstone.bloomy.ui.viewmodel.ProfileViewModel
import com.capstone.bloomy.ui.viewmodelfactory.ProfileViewModelFactory
import com.capstone.bloomy.utils.getImageUri
import com.capstone.bloomy.utils.reduceFileImage
import com.capstone.bloomy.utils.uriToFile
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class EditProfileActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {

    private var imageUri: Uri? = null
    private var listIdProvinsi = ArrayList<Int>()
    private var listNamaProvinsi = ArrayList<String>()
    private var listIdKota = ArrayList<Int>()
    private var listNamaKota = ArrayList<String>()

    private val profileViewModelFactory: ProfileViewModelFactory = ProfileViewModelFactory.getInstance(this@EditProfileActivity)
    private val profileViewModel: ProfileViewModel by viewModels { profileViewModelFactory }

    private lateinit var binding: ActivityEditProfileBinding

    @RequiresApi(Build.VERSION_CODES.Q)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.materialToolBarEditProfile)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        profileViewModel.getProfile()
        profileViewModel.profile.observe(this) { profile ->
            setProfile(profile)
            showProvinsi()
        }

        binding.tvChooseImageEditProfile.setOnClickListener {
            showImagePickerDialog()
        }

        binding.btnEditProfile.setOnClickListener {
            val editProfile = binding.btnEditProfile
            val name = binding.etNameEditProfile.text.toString()
            val phoneNumber = binding.etPhoneNumberEditProfile.text.toString()
            val province = binding.etProvinceEditProfile.text.toString()
            val city = binding.etCityEditProfile.text.toString()
            val address = binding.etAddressEditProfile.text.toString()
            val description = binding.etDescriptionEditProfile.text.toString()

            imageUri?.let { uri ->
                val editPhotoProfile = binding.btnEditProfile
                val file = uriToFile(uri, this).reduceFileImage()

                profileViewModel.editPhotoProfile(file).observe(this) { result ->
                    if (result != null) {
                        when (result) {
                            is ResultState.Loading -> {
                                showLoadingEditProfile(editPhotoProfile, true)
                            }

                            is ResultState.Success -> {
                                showLoadingEditProfile(editPhotoProfile, false)
                                Toast.makeText(this, result.data.message, Toast.LENGTH_SHORT).show()
                            }

                            is ResultState.Error -> {
                                showLoadingEditProfile(editPhotoProfile, false)
                                Toast.makeText(this, result.error, Toast.LENGTH_SHORT).show()
                            }
                        }
                    }
                }
            }

            if (name.isNotEmpty() && phoneNumber.isNotEmpty() && province.isNotEmpty() && city.isNotEmpty() && address.isNotEmpty() && description.isNotEmpty()) {
                showLoadingEditProfile(editProfile, true)

                profileViewModel.editProfile(name, phoneNumber, address, province, city, description)
                profileViewModel.editProfileResponse.observe(this@EditProfileActivity) { response ->
                    val error = response?.error
                    val message = response?.message.toString()

                    if (error != null) {
                        showLoadingEditProfile(editProfile, false)
                        if (error == true) {
                            Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
                            profileViewModel.defaultEditProfile()
                        } else {
                            Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
                            profileViewModel.defaultEditProfile()
                        }
                    }
                }
            } else {
                Toast.makeText(this, getString(R.string.invalid_input), Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                navigateToMainActivity()
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        navigateToMainActivity()
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        parent?.getItemAtPosition(position)

        if (parent?.selectedItem == binding.etProvinceEditProfile.text.toString()) {
            showKota(listIdProvinsi[position])
        }
    }

    override fun onNothingSelected(parent: AdapterView<*>?) { }

    private fun showImagePickerDialog() {
        val dialog = Dialog(this)

        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(false)
        dialog.setContentView(R.layout.image_picker_dialog)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        val imgCamera: ImageView = dialog.findViewById(R.id.img_camera_image_picker_dialog)
        val imgGallery: ImageView = dialog.findViewById(R.id.img_gallery_image_picker_dialog)
        val btnCancel: Button = dialog.findViewById(R.id.btn_cancel_image_picker_dialog)

        imgCamera.setOnClickListener {
            imageUri = getImageUri(this)
            launchCamera.launch(imageUri)
            dialog.dismiss()
        }

        imgGallery.setOnClickListener {
            launchGallery.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
            dialog.dismiss()
        }

        btnCancel.setOnClickListener {
            dialog.dismiss()
        }

        dialog.show()
    }

    private fun setProfile(profile: ProfileData) {
        with(binding) {
            Glide.with(this@EditProfileActivity)
                .load(profile.photo)
                .into(imgEditProfile)

            etNameEditProfile.text = Editable.Factory.getInstance().newEditable(profile.nama)
            etPhoneNumberEditProfile.text = Editable.Factory.getInstance().newEditable(profile.nohp)
            etProvinceEditProfile.text = Editable.Factory.getInstance().newEditable(profile.provinsi)
            etCityEditProfile.text = Editable.Factory.getInstance().newEditable(profile.kota)
            etAddressEditProfile.text = Editable.Factory.getInstance().newEditable(profile.alamat)
            etDescriptionEditProfile.text = Editable.Factory.getInstance().newEditable(profile.description)
        }
    }

    private val launchCamera = registerForActivityResult(ActivityResultContracts.TakePicture()) { success ->
        if (success) {
            showImage()
        }
    }

    private val launchGallery = registerForActivityResult(ActivityResultContracts.PickVisualMedia()) { uri: Uri? ->
        if (uri != null) {
            imageUri = uri
            showImage()
        }
    }

    private fun showImage() {
        imageUri?.let {
            binding.imgEditProfile.setImageURI(it)
        }
    }

    private fun showProvinsi() {
        IndonesiaLocationConfig.api.getProvinsi().enqueue(object : Callback<ProvinsiResponse> {
            override fun onResponse(
                call: Call<ProvinsiResponse>,
                response: Response<ProvinsiResponse>
            ) {
                listIdProvinsi.clear()
                listNamaProvinsi.clear()

                val listResponse = response.body()?.provinsi

                listResponse?.forEach {
                    listIdProvinsi.add(it.id)
                    listNamaProvinsi.add(it.nama)
                }

                val adapter = ArrayAdapter(this@EditProfileActivity, android.R.layout.simple_dropdown_item_1line, listNamaProvinsi)

                binding.etProvinceEditProfile.setAdapter(adapter)
                binding.etProvinceEditProfile.setOnItemClickListener { _, _, position, _ ->
                    showKota(listIdProvinsi[position])
                }
            }

            override fun onFailure(call: Call<ProvinsiResponse>, t: Throwable) {
                Toast.makeText(this@EditProfileActivity, "${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun showKota(idProv: Int) {
        IndonesiaLocationConfig.api.getKota(idProv).enqueue(object : Callback<KotaResponse> {
            override fun onResponse(call: Call<KotaResponse>, response: Response<KotaResponse>) {
                val listResponse = response.body()?.kotaKabupaten

                listIdKota.clear()
                listNamaKota.clear()

                listResponse?.forEach {
                    listIdKota.add(it.id)
                    listNamaKota.add(it.nama)
                }

                val adapter = ArrayAdapter(this@EditProfileActivity, android.R.layout.simple_dropdown_item_1line, listNamaKota)

                binding.etCityEditProfile.setAdapter(adapter)
                binding.etCityEditProfile.setText("", false)
            }

            override fun onFailure(call: Call<KotaResponse>, t: Throwable) {
                Toast.makeText(this@EditProfileActivity, "${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun navigateToMainActivity() {
        val intent = Intent(this, MainActivity::class.java)
        intent.putExtra("navigateToProfileFragment", true)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
        startActivity(intent)
    }

    private fun showLoadingEditProfile(editProfile: Button, isLoading: Boolean) { editProfile.text = if (!isLoading) getString(R.string.btn_edit) else getString(R.string.btn_loading) }
}