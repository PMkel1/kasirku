package com.thoriq.kasirku

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.thoriq.kasirku.databinding.FragmentLoginBinding

class LoginFragment : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        val binding: FragmentLoginBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_login, container, false)

        binding.submitLogin.setOnClickListener {
            val username = binding.inputUsername.text.toString()
            val password = binding.inputPassword.text.toString()
            if (cekAkun(username, password)) {
                view?.findNavController()?.navigate(R.id.action_loginFragment_to_dashboardFragment)
            }
        }
        return binding.root
    }

}
fun cekAkun(username: String,password: String): Boolean{
    return username == "admin" && password == "admin"
}