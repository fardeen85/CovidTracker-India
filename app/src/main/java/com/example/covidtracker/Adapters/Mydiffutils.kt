package com.example.covidtracker.Adapters

import androidx.recyclerview.widget.DiffUtil
import com.example.covidtracker.Models.DistrictModel

class Mydiffutils(

    var newlist : List<DistrictModel>,
    var oldlist : List<DistrictModel>

) : DiffUtil.Callback(){


    override fun getOldListSize(): Int {
       return oldlist.size
    }

    override fun getNewListSize(): Int {
        return newlist.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {

        return  oldlist[oldItemPosition].district == newlist[newItemPosition].district
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {

        return  when{

            oldlist[oldItemPosition].district != newlist[newItemPosition].district -> {
                false
            }
            oldlist[oldItemPosition].active != newlist[newItemPosition].active -> {
                false
            }
            oldlist[oldItemPosition].confirmed != newlist[newItemPosition].confirmed ->{
                false
            }
            oldlist[oldItemPosition].deceased != newlist[newItemPosition].deceased ->{
                false
            }
            oldlist[oldItemPosition].recoverd != newlist[newItemPosition].recoverd -> {
                false
            }
            oldlist[oldItemPosition].deltaconfirmed != newlist[newItemPosition].deltaconfirmed->{
                false
            }
            oldlist[oldItemPosition].deltadeceased != newlist[newItemPosition].deltadeceased->{
                false
            }
            oldlist[oldItemPosition].deltarecoverd != newlist[newItemPosition].deltarecoverd->{
                false
            }
            else ->{
                true
            }


        }
    }
}