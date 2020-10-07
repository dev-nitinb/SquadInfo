package com.example.squadinfo.activity

import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.appcompat.widget.Toolbar
import com.example.squadinfo.R
import com.example.squadinfo.api.RetrofitInstance
import com.example.squadinfo.fragment.SquadFragment
import com.example.squadinfo.model.PlayerInfo
import com.example.squadinfo.model.Teams
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.gson.JsonElement
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.json.JSONObject
import retrofit2.Response


class MainActivity : AppCompatActivity() {

    private lateinit var toolbar: Toolbar
    private lateinit var toolbarTitle: AppCompatTextView
    private lateinit var ivBack: AppCompatImageView
    private lateinit var bottomNavigation: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initialize()

    }

    private fun initialize() {
        bottomNavigation = findViewById(R.id.bottomNavigation)
        toolbar = findViewById(R.id.toolbar)
        toolbarTitle = toolbar.findViewById(R.id.toolbarTitle)
        ivBack=toolbar.findViewById(R.id.ivBack)

        setSupportActionBar(toolbar)

        var view = bottomNavigation.findViewById(R.id.navigationScorecard) as View
        view.performClick()
        toolbarTitle.text = "Scorecard"

        setListener()
        loadMatchData()
    }

    private fun setListener() {
        bottomNavigation.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.navigationSummary -> {
                    toolbarTitle.text = "Summary"
                }
                R.id.navigationScorecard -> {
                    toolbarTitle.text = "Scorecard"
                }
                R.id.navigationCommentary -> {
                    toolbarTitle.text = "Commentary"
                }
                R.id.navigationMatchInfo -> {
                    toolbarTitle.text = "Match Info"
                }
            }
            true
        }
        ivBack.setOnClickListener { onBackPressed() }
    }

    fun loadMatchData() {
        val api = RetrofitInstance.api
        CoroutineScope(Dispatchers.Main).launch {

            //did not use POJO model as json response has random key
            var response: Response<JsonElement> = api.getMatchData()

            Log.e("TAG ", response.body().toString())
            if (response.isSuccessful && response.body() != null) {
                val matchResponse = response.body().toString()
                var responseJson=JSONObject(matchResponse)
                val teamJsonObj = responseJson.getJSONObject("Teams")

                var alTeams = ArrayList<Teams>()

                val keys: Iterator<*> = teamJsonObj.keys()
                while (keys.hasNext()) {
                    val key = keys.next() as String
                    var teamJsonObjNew = teamJsonObj.getJSONObject(key)

                    val teamName = teamJsonObjNew.getString("Name_Full")

                    var alPlayer = ArrayList<PlayerInfo>()

                    val playersJsonObj = teamJsonObjNew.getJSONObject("Players")
                    val keys2: Iterator<*> = playersJsonObj.keys()

                    while (keys2.hasNext()) {
                        val keyPlayer = keys2.next() as String
                        var playersJsonObjNew = playersJsonObj.getJSONObject(keyPlayer)
                        val playersName = playersJsonObjNew.getString("Name_Full")
                        val position = playersJsonObjNew.getString("Position")
                        var isCaptain = false
                        var isKeeper = false
                        if (playersJsonObjNew.has("Iscaptain")) {
                            isCaptain = playersJsonObjNew.getBoolean("Iscaptain")
                        }
                        if (playersJsonObjNew.has("Iskeeper")) {
                            isKeeper = playersJsonObjNew.getBoolean("Iskeeper")
                        }

                        alPlayer.add(PlayerInfo(playersName,position,isCaptain,isKeeper))

                    }
                    alTeams.add(Teams(teamName,alPlayer))

                }
                Log.e("TAG ", alTeams.toString())

                var fragment=SquadFragment()
                var bundle=Bundle()
                bundle.putParcelableArrayList("alTeams",alTeams)
                fragment.arguments=bundle
                var fragmentTransaction = supportFragmentManager.beginTransaction()
                fragmentTransaction.replace(R.id.frameContainer,fragment)
                fragmentTransaction.commit()
            } else {
                Toast.makeText(this@MainActivity, "Error", Toast.LENGTH_SHORT).show()
                Log.e("TAG Error", response.errorBody().toString())
            }
        }
    }

}