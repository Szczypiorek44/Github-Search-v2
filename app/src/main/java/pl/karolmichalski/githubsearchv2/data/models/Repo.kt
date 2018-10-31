package pl.karolmichalski.githubsearchv2.data.models

import android.os.Parcelable
import com.fasterxml.jackson.annotation.JsonProperty
import kotlinx.android.parcel.Parcelize

@Parcelize
class Repo(@JsonProperty("id") val id: Int,
		   @JsonProperty("name") val name: String,
		   @JsonProperty("full_name") val fullName: String,
		   @JsonProperty("owner") val owner: User,
		   @JsonProperty("html_url") val htmlUrl: String,
		   @JsonProperty("description") val description: String?,
		   @JsonProperty("stargazers_count") val stargazersCount: Int) : Parcelable