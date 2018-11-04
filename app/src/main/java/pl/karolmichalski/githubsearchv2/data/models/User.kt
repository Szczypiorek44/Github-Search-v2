package pl.karolmichalski.githubsearchv2.data.models

import com.fasterxml.jackson.annotation.JsonProperty
import kotlinx.android.parcel.Parcelize
import pl.karolmichalski.githubsearchv2.data.models.base.Identified

@Parcelize
class User(@JsonProperty("id") override val id: Int,
		   @JsonProperty("login") val login: String,
		   @JsonProperty("avatar_url") val avatarUrl: String,
		   @JsonProperty("repos_url") val reposUrl: String,
		   @JsonProperty("followers_url") val followersUrl: String) : Identified(id)