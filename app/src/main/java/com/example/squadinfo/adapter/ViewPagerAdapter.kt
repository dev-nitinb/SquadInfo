package com.example.squadinfo.adapter

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.squadinfo.fragment.SquadDetailChildFragment
import com.example.squadinfo.model.Teams

class ViewPagerAdapter(parentFragment: Fragment, var alTeams:ArrayList<Teams>): FragmentStateAdapter(parentFragment) {
    override fun getItemCount(): Int {
        return alTeams.size
    }

    override fun createFragment(position: Int): Fragment {
        var fragment=SquadDetailChildFragment()
        val bundle=Bundle()
        bundle.putSerializable("team",alTeams[position])
        fragment.arguments= bundle
        return fragment
    }

    /*override fun getItem(position: Int): Fragment {2

    }

    override fun getCount(): Int {
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return alTeams[position].teamName
    }*/
}