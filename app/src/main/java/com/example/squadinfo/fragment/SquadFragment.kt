package com.example.squadinfo.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.viewpager.widget.ViewPager
import androidx.viewpager2.widget.ViewPager2
import com.example.squadinfo.R
import com.example.squadinfo.adapter.ViewPagerAdapter
import com.example.squadinfo.model.Teams
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener
import com.google.android.material.tabs.TabLayoutMediator

class SquadFragment : Fragment() {

    lateinit var tabLayout: TabLayout
    lateinit var viewPager: ViewPager2
    lateinit var viewPagerAdapter: ViewPagerAdapter

    var alTeams: ArrayList<Teams>? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        alTeams=arguments!!.getParcelableArrayList<Teams>("alTeams")
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_squad, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.e("Fragment ", alTeams.toString())

        tabLayout=view.findViewById(R.id.tabLayout)

        viewPager=view.findViewById(R.id.viewPager)
        viewPagerAdapter= ViewPagerAdapter(this,alTeams!!)


    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        tabLayout.setTabTextColors(ContextCompat.getColor(context!!,
            R.color.colorGrey
        ),ContextCompat.getColor(context!!,
            R.color.colorMediumBlue
        ))

        viewPager.adapter=viewPagerAdapter
        TabLayoutMediator(tabLayout,viewPager){ tab, position ->
            tab.text= alTeams!![position].teamName
        }.attach()
    }


    private fun sendChildFragmentTeam(team:Teams){
        var fragment=SquadDetailChildFragment()
        var bundle=Bundle()
        bundle.putSerializable("team", team)
        fragment.arguments=bundle
        var fragmentTransaction = childFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frameContainer,fragment)
        fragmentTransaction.commit()
    }
}