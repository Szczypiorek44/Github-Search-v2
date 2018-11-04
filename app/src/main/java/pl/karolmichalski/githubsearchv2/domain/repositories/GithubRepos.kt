package pl.karolmichalski.githubsearchv2.domain.repositories

import io.reactivex.Single
import pl.karolmichalski.githubsearchv2.data.models.Repo
import pl.karolmichalski.githubsearchv2.data.models.User
import pl.karolmichalski.githubsearchv2.data.models.base.Identified

interface GithubRepos {
	fun findRepos(keywords: String?): Single<List<Repo>>
	fun findUsers(keywords: String?): Single<List<User>>
	fun findReposAndUsers(keywords: String?): Single<List<Identified>>
	fun getRepoDetails(owner: String, repo: String): Single<Repo>
}