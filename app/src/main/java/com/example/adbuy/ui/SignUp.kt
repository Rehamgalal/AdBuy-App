package com.example.adbuy.ui

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
import com.example.adbuy.viewmodel.AdBuyViewModel
import kotlinx.android.synthetic.main.fragment_sign_up.*
import kotlinx.android.synthetic.main.fragment_sign_up.email
import kotlinx.android.synthetic.main.fragment_sign_up.password
import kotlinx.android.synthetic.main.fragment_sign_up.skip
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import timber.log.Timber

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [SignUp.newInstance] factory method to
 * create an instance of this fragment.
 */

class SignUp : Fragment() {
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
        val view = inflater.inflate(R.layout.fragment_sign_up, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        skip.setOnClickListener {
            findNavController().navigate(R.id.action_signUp_to_signIn)
        }
        login.setOnClickListener {
            findNavController().navigate(R.id.action_signUp_to_signIn)
        }
        password.filters = arrayOf(InputFilter { source, _, _, _, _, _ ->
            source.toString().filterNot { it.isWhitespace() }
        })

        signup_button.setOnClickListener {
            if (!name.text.isEmpty()){
            if (email.text.toString().isValidEmail() ){
                if (password.text.toString().length<8)
                {
                    password.setText("Password should be 8 or more characters")
                }else{
                    if (password.text.toString().equals(confirm_password.text.toString())){
                        if (!phone_num.text.toString().equals("")){
                            job = lifecycleScope.launch {
                                viewModel.safeSignupCall(name.text.toString(),name.text.toString(),email.text.toString()
                                    ,password.text.toString(),confirm_password.text.toString(),
                                phone_num.text.toString())
                            }
                        }else{
                            phone_num.setText("Please enter your phone number")
                        }
                    }else{
                        password.setText("these two fields should be the same")
                        confirm_password.setText("these two fields should be the same")
                    }
                }
            }else{
                email.setText("Email Invalid")
            }}
            else{
                name.setText("please enter your name")
            }

        }

        viewModel.userData.observe(viewLifecycleOwner, Observer {
            if (it.data==null){
                Timber.e("Login Failed")
            }else if (it.data!!.status==200){
                //Go to main screen
                Toast.makeText(context,"should be directed to main screen", Toast.LENGTH_LONG).show()

            }else if (it.data!!.status==401){
              email.setText("email already exists")
            }
        })


    }
    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment SignUp.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            SignUp().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
        fun CharSequence?.isValidEmail() = !isNullOrEmpty() && Patterns.EMAIL_ADDRESS.matcher(this).matches()
}