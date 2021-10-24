package com.example.covidtracker.Adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter

class ViewPagerAdapter(fm : FragmentManager, lifecycle: Lifecycle
,fragmentlist : ArrayList<Fragment>) : FragmentStateAdapter(fm,lifecycle) {

     var fragmentlist = fragmentlist

    override fun getItemCount(): Int {
        return fragmentlist.size

    }

    override fun createFragment(position: Int): Fragment {
        return fragmentlist[position]
    }


}