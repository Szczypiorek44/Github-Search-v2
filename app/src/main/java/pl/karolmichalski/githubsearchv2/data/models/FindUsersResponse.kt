package pl.karolmichalski.githubsearchv2.data.models

import com.fasterxml.jackson.annotation.JsonProperty

class FindUsersResponse(
		@JsonProperty("total_count") val totalCount: Int,
		@JsonProperty("incomplete_results") val isIncompleteResults: Boolean,
		@JsonProperty("items") val userList: List<User>
)