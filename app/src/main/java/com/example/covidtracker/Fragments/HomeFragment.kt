package com.example.covidtracker.Fragments

import android.graphics.Color
import android.opengl.Visibility
import android.os.Bundle
import android.util.Log
import android.view.Display
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListAdapter
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.navGraphViewModels
import com.android.volley.*
import com.example.covidtracker.databinding.FragmentHomeBinding


import com.android.volley.toolbox.Volley

import kotlin.collections.ArrayList
import com.android.volley.toolbox.JsonArrayRequest
import com.example.covidtracker.Models.DistrictModel
import com.example.covidtracker.Models.Model
import com.example.covidtracker.R
import com.example.covidtracker.ViewModel.FragmentsViewModel
import java.lang.Exception


class HomeFragment : Fragment() {

    lateinit var homeBinding: FragmentHomeBinding
    //lateinit var arrayAdapter: ArrayAdapter<String>
    lateinit var statecodearray: ArrayList<Model>
    lateinit var queue: RequestQueue
    var disctrict : String = ""
    lateinit var listadapter: ListAdapter
    private val viemodel : FragmentsViewModel by  navGraphViewModels(R.id.mynav)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        homeBinding = FragmentHomeBinding.inflate(inflater, container, false)
        homeBinding.txtconnection.visibility = View.GONE

        queue = Volley.newRequestQueue(requireContext())
        getStatesfromApi()

      /* arrayAdapter =
            ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1, statecodearray)*/

        homeBinding.swipe.setOnRefreshListener {

            getStatesfromApi()
        }

        return homeBinding.root
    }


    fun getStatesfromApi() {

        homeBinding.swipe.isRefreshing = true

        statecodearray = ArrayList<Model>()
        var actives : String= ""

        val request = JsonArrayRequest(
            Request.Method.GET, "https://data.covid19india.org/v2/state_district_wise.json", null,
            { response ->
                try {
                    for (i in 0 until response.length()) {
                        val dataOBJ = response.getJSONObject(i)
                        var states = dataOBJ.get("state").toString()
                        var m = Model()
                        m.State = states
                        statecodearray.add(m)
                        Log.d("YAG",states.toString())
                        homeBinding.swipe.isRefreshing = false
                        homeBinding.txtconnection.visibility = View.GONE


                        }






                    listadapter = com.example.covidtracker.Adapters.
                    ListAdapter(requireContext(), R.layout.customlist,statecodearray,viemodel,findNavController())

                    homeBinding.stateslist.adapter = listadapter



                } catch (exp: Exception) {
                    Log.d("TAG",exp.message.toString())
                    homeBinding.swipe.isRefreshing = false
                    homeBinding.stateslist.visibility = View.INVISIBLE
                    homeBinding.txtconnection.visibility = View.VISIBLE

                }
            }
        ) { error->

            homeBinding.txtconnection.setText("Connection problem")
            homeBinding.txtconnection.visibility = View.VISIBLE

            /*when(error){

                AuthFailureError() ->  {
                    homeBinding.swipe.isRefreshing = false
                    homeBinding.txtconnection.setText("Server Error")
                    homeBinding.txtconnection.visibility = View.VISIBLE
                }
                NetworkError() -> {

                    homeBinding.swipe.isRefreshing = false
                    homeBinding.txtconnection.setText("Network error")
                    homeBinding.txtconnection.visibility = View.VISIBLE

                }

                NoConnectionError() -> {
                    homeBinding.swipe.isRefreshing = false
                    homeBinding.txtconnection.setText("No internet")
                    homeBinding.txtconnection.visibility = View.VISIBLE

                }
            }*/
            Log.d("TAG",error.message.toString())
            homeBinding.swipe.isRefreshing = false
        }

        queue.add(request)







    }
}
