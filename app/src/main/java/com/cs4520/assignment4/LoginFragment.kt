package com.cs4520.assignment4

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController

class LoginFragment : Fragment(R.layout.login_fragment) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Binds the login action to the login button
        view.findViewById<Button>(R.id.loginButton).setOnClickListener(View.OnClickListener {
            val userNameView = view.findViewById<EditText>(R.id.loginUser)
            val passwordView = view.findViewById<EditText>(R.id.loginPass)
            val userName = userNameView.text.toString()
            val password = passwordView.text.toString()

            if (userName == "admin" && password == "admin") {
                // Reset auth fields after sign in
                userNameView.setText("")
                passwordView.setText("")
                // Navigate to product list if credentials are correct
                findNavController().navigate(R.id.action_loginFrag_to_pL)
            }
            // Display error toast if auth is incorrect
            else {
                val toast = Toast.makeText(view.context, "Incorrect Credentials", Toast.LENGTH_SHORT)
                toast.show()
            }
        })
    }
}