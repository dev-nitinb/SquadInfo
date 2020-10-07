package com.example.squadinfo.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class PlayerInfo(
    val fullName: String,
    val position: String,
    val iscaptain: Boolean,
    val iskeeper: Boolean
) : Parcelable