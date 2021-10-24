package com.example.covidtracker.onboarding

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.widget.ViewPager2
import com.example.covidtracker.R
import com.example.covidtracker.databinding.FragmentScreen01Binding


class ScreenFragment01 : Fragment() {


    lateinit var screenFragment01: FragmentScreen01Binding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
       screenFragment01 = FragmentScreen01Binding.inflate(inflater,container,false)

        val pager = activity?.findViewById<ViewPager2>(R.id.viewpager)

        screenFragment01.next.setOnClickListener {

            pager?.currentItem = 1
        }

        return screenFragment01.root
    }



}