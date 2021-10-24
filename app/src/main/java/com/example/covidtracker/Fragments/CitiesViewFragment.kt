package com.example.covidtracker.Fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.navGraphViewModels
import androidx.viewpager2.adapter.FragmentViewHolder
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import com.example.covidtracker.Adapters.ListAdapter
import com.example.covidtracker.Adapters.RecyclerAdapter
import com.example.covidtracker.Models.DistrictModel
import com.example.covidtracker.Models.Model
import com.example.covidtracker.R
import com.example.covidtracker.ViewModel.FragmentsViewModel
import com.example.covidtracker.databinding.FragmentCitiesViewBinding
import java.lang.Exception
import org.json.JSONObject

import org.json.JSONArray





class CitiesViewFragment : Fragment() {


    lateinit var fragmentCitiesViewBinding: FragmentCitiesViewBinding
    val adapter : RecyclerAdapter by lazy { RecyclerAdapter() }
    private val viewmodel: FragmentsViewModel by navGraphViewModels(R.id.mynav)
    lateinit var queue: RequestQueue

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        fragmentCitiesViewBinding =
            FragmentCitiesViewBinding.inflate(layoutInflater,container,false)
        queue = Volley.newRequestQueue(requireContext())
        loadupdates()

        fragmentCitiesViewBinding.recycler.adapter = adapter

        fragmentCitiesViewBinding.search.queryHint = "Search city/district"
        fragmentCitiesViewBinding.search.setOnQueryTextListener(object : androidx.appcompat.widget.SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(p0: String?): Boolean {

                adapter.filter!!.filter(p0)
                fragmentCitiesViewBinding.recycler.scrollToPosition(0)
                return false


            }

            override fun onQueryTextChange(p0: String?): Boolean {
                adapter.filter!!.filter(p0)
                return false
            }


        })

        return fragmentCitiesViewBinding.root


    }


    fun loadupdates(){

        var list : List<DistrictModel> = emptyList()

        val request = JsonArrayRequest(
            Request.Method.GET, "https://data.covid19india.org/v2/state_district_wise.json", null,
            { response ->
                try {

                    var districtlist = ArrayList<DistrictModel>()
                    for (i in 0 until response.length()) {
                        val dataOBJ: JSONObject = response.getJSONObject(i)
                        if (dataOBJ.getString("state").equals(viewmodel._statename)) {
                            val jsonChild = dataOBJ.getJSONArray("districtData")
                            for (k in 0 until jsonChild.length()) {
                                val obj = jsonChild.getJSONObject(k)
                                val active = obj.getInt("active")
                                val district = obj.getString("district")
                                val deceased = obj.getInt("deceased")
                                val recoverd = obj.getInt("recovered")
                                val confirmed = obj.getInt("confirmed")

                                val child = obj.getJSONObject("delta")
                                val dconfirmed = child.getInt("confirmed")
                                val ddeceased = child.getInt("deceased")
                                val drecovered = child.getInt("recovered")

                                val m = DistrictModel()
                                m.confirmed = confirmed.toString()
                                m.active = active.toString()
                                m.district = district.toString()
                                m.deceased = deceased.toString()
                                m.recoverd = recoverd.toString()
                                m.district = district
                                m.deltaconfirmed = dconfirmed.toString()
                                m.deltarecoverd = drecovered.toString()
                                m.deltadeceased = ddeceased.toString()

                                districtlist.add(m)
                                Log.d("BAG", dconfirmed.toString())
                            }
                        }
                    }



                        adapter.setdata(districtlist)
                        fragmentCitiesViewBinding.recycler.adapter = adapter





                } catch (exp: Exception) {
                    Log.d("TAG",exp.message.toString())
                }
            }
        ) {


            Log.d("TAG",it.message.toString())
        }

        queue.add(request)

    }

}