package com.example.adbuy.ui.fragment

import android.os.Bundle
import android.text.InputFilter
import android.util.Patterns
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.adbuy.R
import com.example.adbuy.model.`object`.login.Login
import com.example.adbuy.other.Resource
import com.example.adbuy.other.Validation
import com.example.adbuy.viewmodel.AdBuyViewModel
import kotlinx.android.synthetic.main.fragment_sign_in.*
import kotlinx.android.synthetic.main.fragment_sign_in.email
import kotlinx.android.synthetic.main.fragment_sign_in.password
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import timber.log.Timber

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [SignIn.newInstance] factory method to
 * create an instance of this fragment.
 */

class SignIn : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }
    private val viewModel: AdBuyViewModel by activityViewModels()
    private var job: Job? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_sign_in, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        signup.setOnClickListener {
            findNavController().navigate(R.id.action_signIn_to_signUp)
        }
        forgot_password.setOnClickListener {
            findNavController().navigate(R.id.action_signIn_to_forgetPassword)
        }
        loginp_button.setOnClickListener {
            if (Validation.validateLoginInput(email, password)) {
                viewModel.loginAdBuy(Login(email.text.toString(),password.text.toString()))
            }else{
                Validation.validateLoginInputError(email, password)
            }

        }
        viewModel.userData.observe(viewLifecycleOwner, Observer { response ->
            when (response) {
                is Resource.Success -> {
                    hideProgressbar()
                    Toast.makeText(context, "should be directed to main screen", Toast.LENGTH_LONG).show()
                }
                is Resource.Error -> {
                    response.message?.let {
                        hideProgressbar()
                        Toast.makeText(context, it, Toast.LENGTH_LONG).show()

                        Timber.e(it)

                    }


                }
                is Resource.Loading -> {
                    showProgressbar()
                }
            }
        })

    }



    private fun hideProgressbar() {
        progressBar.visibility = View.INVISIBLE
    }

    private fun showProgressbar() {
        progressBar.visibility = View.VISIBLE
    }


}