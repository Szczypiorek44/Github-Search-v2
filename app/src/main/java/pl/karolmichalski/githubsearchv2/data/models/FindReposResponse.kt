package pl.karolmichalski.githubsearchv2.data.models

import com.fasterxml.jackson.annotation.JsonProperty

class FindReposResponse(
		@JsonProperty("total_count") val totalCount: Int,
		@JsonProperty("incomplete_results") val isIncompleteResults: Boolean,
		@JsonProperty("items") val repoList: List<Repo>
)