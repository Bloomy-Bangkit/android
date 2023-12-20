package com.capstone.bloomy.ui.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.viewModels
import com.capstone.bloomy.R
import com.capstone.bloomy.data.model.UserModel
import com.capstone.bloomy.data.preferences.UserPreferences
import com.capstone.bloomy.data.preferences.dataStore
import com.capstone.bloomy.databinding.ActivitySignInBinding
import com.capstone.bloomy.ui.viewmodel.AuthenticationViewModel
import com.capstone.bloomy.ui.viewmodel.UserPreferencesViewModel
import com.capstone.bloomy.ui.viewmodelfactory.AuthenticationViewModelFactory
import com.capstone.bloomy.ui.viewmodelfactory.UserPreferencesViewModelFactory

class SignInActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignInBinding

    private val authenticationViewModelFactory: AuthenticationViewModelFactory = AuthenticationViewModelFactory.getInstance()
    private val authenticationViewModel: AuthenticationViewModel by viewModels { authenticationViewModelFactory }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignInBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val userPreferences: UserPreferences = UserPreferences.getInstance(application.dataStore)
        val userPreferencesViewModelFactory: UserPreferencesViewModelFactory = UserPreferencesViewModelFactory.getInstance(userPreferences)
        val userPreferencesViewModel: UserPreferencesViewModel by viewModels { userPreferencesViewModelFactory }

        binding.btnSignIn.setOnClickListener {
            val signIn = binding.btnSignIn
            val email = binding.etEmailSignIn.text.toString()
            val password = binding.etPasswordSignIn.text.toString()

            if (email.isNotEmpty() && password.isNotEmpty()) {
                if (password.length >= 8) {
                    showLoading(signIn, true)

                    authenticationViewModel.signIn(email, password)
                    authenticationViewModel.signInResponse.observe(this) { session ->
                        if (session != null) {
                            val userModel = UserModel(
                                session.token
                            )

                            userPreferencesViewModel.saveSession(userModel)

                            showLoading(signIn, false)

                            val mainIntent = Intent(this, MainActivity::class.java)
                            startActivity(mainIntent)
                            finish()
                        }
                    }

                    authenticationViewModel.invalidSignInResponse.observe(this) { invalidSignIn ->
                        val invalid = invalidSignIn?.message.toString()

                        if (invalidSignIn != null) {
                            showLoading(signIn, false)

                            Toast.makeText(this, invalid, Toast.LENGTH_SHORT).show()
                            authenticationViewModel.defaultSignIn()
                        }
                    }
                } else {
                    Toast.makeText(this, getString(R.string.invalid_password), Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, getString(R.string.invalid_input), Toast.LENGTH_SHORT).show()
            }
        }

        binding.btnSignUp.setOnClickListener {
            startActivity(Intent(this, SignUpActivity::class.java))
        }
    }

    private fun showLoading(signIn: Button, isLoading: Boolean) { signIn.text = if (!isLoading) getString(R.string.btn_sign_in) else getString(R.string.btn_loading) }
}