package pl.karolmichalski.githubsearchv2.domain.repositories

import io.reactivex.Single
import pl.karolmichalski.githubsearchv2.data.models.Repo

interface GithubRepos {
	fun findRepos(keywords: String?): Single<List<Repo>>
	fun getRepoDetails(owner: String, repo: String): Single<Repo>
}