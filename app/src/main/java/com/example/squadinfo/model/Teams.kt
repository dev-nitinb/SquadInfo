package com.example.squadinfo.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.io.Serializable

@Parcelize
data class Teams(
    val teamName:String,
    val player:ArrayList<PlayerInfo>
) : Parcelable, Serializable