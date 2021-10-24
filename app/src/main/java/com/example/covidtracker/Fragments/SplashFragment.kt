package com.example.covidtracker.Fragments

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.covidtracker.R
import com.example.covidtracker.databinding.FragmentSplashBinding

class SplashFragment : Fragment() {

    lateinit var fragmentSplashBinding: FragmentSplashBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
         fragmentSplashBinding = FragmentSplashBinding.inflate(inflater,container,false)

        Handler().postDelayed(
            {
                if (isUserFinished()){

                    findNavController().navigate(R.id.action_splashFragment_to_homeFragment)
                }
                else {
                    findNavController().navigate(R.id.action_splashFragment_to_viewPagerFragment)
                }

            }
        ,3000
        )

        return fragmentSplashBinding.root
    }

    fun isUserFinished() : Boolean{

        val sharefref = requireContext().getSharedPreferences("",Context.MODE_PRIVATE)
        return  sharefref.getBoolean("finished",false)

    }


}