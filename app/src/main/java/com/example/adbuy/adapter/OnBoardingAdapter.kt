package com.example.adbuy.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.navigation.Navigation
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import com.example.adbuy.R

class OnBoardingAdapter() : PagerAdapter(){
init {

}
    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val inflater = LayoutInflater.from(container.context)
        val layout =  inflater.inflate(R.layout.pager_item, container, false)
        val background = layout.findViewById<ImageView>(R.id.background)
        val skip = layout.findViewById<TextView>(R.id.skip)
        val advertiseText = layout.findViewById<TextView>(R.id.advertise_text)
        val button = layout.findViewById<Button>(R.id.button)
        background.setImageResource(getItem(position).first)
        advertiseText.text = getItem(position).second
        container.addView(layout)
        return layout
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
       return view ==`object`
    }

    override fun getCount(): Int {
        return 3
    }

    fun getItem(position:Int):Pair<Int,String> {
        return when (position) {
            0 -> Pair(R.drawable.storyboarding, "The best online store for your shopping")
            1 -> Pair(R.drawable.storyboarding3, "watch product video and take points")
            else -> Pair(R.drawable.storyboarding2, "Start your journey and shop now")
        }
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }
}