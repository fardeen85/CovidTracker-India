package com.example.covidtracker.Adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.covidtracker.Models.DistrictModel
import com.example.covidtracker.databinding.RowlayoutBinding
import android.widget.Toast




class RecyclerAdapter() : RecyclerView.Adapter<RecyclerAdapter.myviewholder>(),Filterable {



    var filteredlist = ArrayList<DistrictModel>()
    var oldlist = filteredlist


    class myviewholder(var itembinding : RowlayoutBinding) : RecyclerView.ViewHolder(itembinding.root){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): myviewholder {
        val row = RowlayoutBinding.inflate(LayoutInflater.from(parent.context))
        return  myviewholder(row)
    }

    override fun onBindViewHolder(holder: myviewholder, position: Int) {


        var dmodel : DistrictModel = filteredlist[position]
        holder.itembinding.txtconfiremed.setText(dmodel.confirmed)
        holder.itembinding.txtrecovered.setText(dmodel.recoverd)
        holder.itembinding.txtactives.setText(dmodel.active)
        holder.itembinding.txtdeceased.setText(dmodel.deceased)

        holder.itembinding.txtDeltaConfirmed.setText(dmodel.deltaconfirmed)
        holder.itembinding.txtDeltaDeath.setText(dmodel.deltadeceased)
        holder.itembinding.txtDeltaRecoverd.setText(dmodel.deltarecoverd)
        holder.itembinding.txtdistrict.setText(dmodel.district)

    }

    override fun getItemCount(): Int {
        return filteredlist.size
    }

    fun setdata(newlist : List<DistrictModel>){


        val diffuitls = Mydiffutils(newlist,filteredlist)
        val diffResults = DiffUtil.calculateDiff(diffuitls)
        filteredlist = newlist as ArrayList<DistrictModel>
        oldlist = filteredlist
        diffResults.dispatchUpdatesTo(this)
    }

    override fun getFilter(): Filter? {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence): FilterResults {
                val results = FilterResults()


                if (constraint.toString().isEmpty() || constraint.toString().length.equals(0) ) {
                    filteredlist = oldlist
                    notifyDataSetChanged()
                    Log.d("TAG","isempty"+oldlist.size)

                } else {
                    val resultData = ArrayList<DistrictModel>()
                    for (itemModel in oldlist) {
                        if (itemModel.district!!.toLowerCase()
                                .contains(constraint.toString().toLowerCase().trim())
                        ) {
                            resultData.add(itemModel)
                        }

                    }
                    filteredlist = resultData
                    results.values = filteredlist
                }
                return results
            }

            override fun publishResults(constraint: CharSequence, results: FilterResults) {
                var templist = ArrayList<DistrictModel>()


                    if (results.values == null) {
                        Log.d("TAG", "not found" + filteredlist)
                    } else{

                        filteredlist= results!!.values as ArrayList<DistrictModel>
                        notifyDataSetChanged()
                }
                }
            }
        }




}