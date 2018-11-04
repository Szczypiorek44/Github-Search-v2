package pl.karolmichalski.githubsearchv2.data.models

import com.fasterxml.jackson.annotation.JsonProperty
import kotlinx.android.parcel.Parcelize
import pl.karolmichalski.githubsearchv2.data.models.base.Identified

@Parcelize
class Repo(@JsonProperty("id") override val id: Int,
		   @JsonProperty("name") val name: String,
		   @JsonProperty("full_name") val fullName: String,
		   @JsonProperty("owner") val owner: User,
		   @JsonProperty("html_url") val htmlUrl: String,
		   @JsonProperty("description") val description: String?,
		   @JsonProperty("stargazers_count") val stargazersCount: Int) : Identified(id)