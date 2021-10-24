package com.example.covidtracker.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.covidtracker.Adapters.ViewPagerAdapter
import com.example.covidtracker.R
import com.example.covidtracker.databinding.FragmentViewPagerBinding
import com.example.covidtracker.onboarding.ScreenFragment01
import com.example.covidtracker.onboarding.ScreenFragment02



class ViewPagerFragment : Fragment() {

    lateinit var viewPagerBinding: FragmentViewPagerBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        viewPagerBinding= FragmentViewPagerBinding.inflate(inflater,container,false)

        var fragmentlist = arrayListOf<Fragment>(

            ScreenFragment01(),
            ScreenFragment02()

        )

        val adapter = ViewPagerAdapter(
            requireActivity().supportFragmentManager,
            lifecycle,
            fragmentlist
        )

        viewPagerBinding.viewpager.adapter = adapter

        return viewPagerBinding.root
    }

}