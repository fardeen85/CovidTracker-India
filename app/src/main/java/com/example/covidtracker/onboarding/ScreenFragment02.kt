package com.example.covidtracker.onboarding

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.example.covidtracker.R
import com.example.covidtracker.databinding.FragmentScreen02Binding


class ScreenFragment02 : Fragment() {


    lateinit var screen02Binding: FragmentScreen02Binding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        screen02Binding = FragmentScreen02Binding.inflate(inflater,container,false)
        val pager = activity?.findViewById<ViewPager2>(R.id.viewpager)

       screen02Binding.finish.setOnClickListener {

            pager?.currentItem = 2
        }

        screen02Binding.finish.setOnClickListener {

            findNavController().navigate(R.id.action_viewPagerFragment_to_homeFragment)
            onboardingfinish()

        }

        return screen02Binding.root
    }

    private fun onboardingfinish(){


        val sharefref = requireContext().getSharedPreferences("", Context.MODE_PRIVATE)
        val edit = sharefref.edit().putBoolean("finished",true)
        edit.commit()



    }


}