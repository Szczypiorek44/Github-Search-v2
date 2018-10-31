package pl.karolmichalski.githubrepos.data.models

import android.os.Parcelable
import com.fasterxml.jackson.annotation.JsonProperty
import kotlinx.android.parcel.Parcelize

@Parcelize
class User(@JsonProperty("id") val id: Int,
		   @JsonProperty("login") val login: String,
		   @JsonProperty("avatar_url") val avatarUrl: String,
		   @JsonProperty("repos_url") val reposUrl: String): Parcelable