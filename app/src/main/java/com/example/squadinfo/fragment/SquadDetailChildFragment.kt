package com.example.squadinfo.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.squadinfo.R
import com.example.squadinfo.adapter.SquadRecyclerViewAdapter
import com.example.squadinfo.model.Teams


class SquadDetailChildFragment : Fragment() {

    lateinit var recylerView:RecyclerView
    lateinit var team: Teams
    lateinit var squadAdapter: SquadRecyclerViewAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        team = arguments!!.getSerializable("team") as Teams
        Log.e("Child Fragment ", team.toString())

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_squad_detail_child, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recylerView = view.findViewById(R.id.recylerView)
        squadAdapter = SquadRecyclerViewAdapter(context!!, team.player)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        recylerView.layoutManager = LinearLayoutManager(context)
        recylerView.adapter = squadAdapter
    }

}