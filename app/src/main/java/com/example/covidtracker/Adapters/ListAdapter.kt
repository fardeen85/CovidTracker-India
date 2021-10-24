package com.example.covidtracker.Adapters


import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment.findNavController
import androidx.navigation.fragment.findNavController
import com.example.covidtracker.Models.Model
import com.example.covidtracker.R
import com.example.covidtracker.ViewModel.FragmentsViewModel
import com.example.covidtracker.databinding.CustomlistBinding
import androidx.navigation.fragment.findNavController


class ListAdapter(var c : Context,
                  var resourcelayout : Int,
                  var data : ArrayList<Model>,
                  var viewmodel : FragmentsViewModel,
                  var navController: NavController
                  ):
    ArrayAdapter<String>(c,resourcelayout) {



    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        val row = LayoutInflater.from(parent.context).inflate(R.layout.customlist,null,false)
        var model = data[position]
        var textview = row.findViewById<TextView>(R.id.txtitems)
        textview.text.toString().format("Active cases : %s")
        textview.setText(model.State)
        row.setOnClickListener {

            viewmodel._statename = textview.text.toString()
            navController.navigate(R.id.action_homeFragment_to_citiesViewFragment)
        }

        return row
    }

    override fun getCount(): Int {
        return data.size
    }
}