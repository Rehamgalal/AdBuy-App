package com.example.adbuy.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.viewpager.widget.ViewPager
import com.example.adbuy.R
import com.example.adbuy.adapter.OnBoardingAdapter
import kotlinx.android.synthetic.main.fragment_second.*

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class OnBoarding : Fragment() {

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_second, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        tablayout.setupWithViewPager(view_pager)
        view_pager.adapter = OnBoardingAdapter()

        button.setOnClickListener {
           swipePages(view_pager.currentItem)
        }
        skip.setOnClickListener {
            swipePages(view_pager.currentItem)
        }
        view_pager.addOnPageChangeListener(object :ViewPager.OnPageChangeListener{
            override fun onPageScrollStateChanged(state: Int) {

            }

            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
                if (position == 2){
                    button.text = "Signup"
                }else{
                    button.text = "Continue"
                }
            }

            override fun onPageSelected(position: Int) {

            }

        })
    }
    fun swipePages(position: Int){
        if (position == 2)
        {
            findNavController().navigate(R.id.action_onboarding_to_signUp)
        }
        else{
            view_pager.setCurrentItem(view_pager.currentItem+1)
        }
    }
}