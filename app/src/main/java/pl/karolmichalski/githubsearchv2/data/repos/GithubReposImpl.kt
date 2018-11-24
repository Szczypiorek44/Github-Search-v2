package pl.karolmichalski.githubsearchv2.data.repos

import android.content.Context
import io.reactivex.Single
import io.reactivex.rxkotlin.Singles
import pl.karolmichalski.githubsearchv2.R
import pl.karolmichalski.githubsearchv2.data.exceptions.BlankInputException
import pl.karolmichalski.githubsearchv2.data.models.Repo
import pl.karolmichalski.githubsearchv2.data.models.User
import pl.karolmichalski.githubsearchv2.data.models.base.Identified
import pl.karolmichalski.githubsearchv2.domain.repositories.GithubRepos

class GithubReposImpl(private val context: Context,
					  private val apiService: ApiService) : GithubRepos {

	override fun findRepos(keywords: String?): Single<List<Repo>> {
		return when {
			keywords.isNullOrBlank() -> Single.error(BlankInputException(context.getString(R.string.enter_keywords)))
			else -> apiService.findRepos(keywords!!).map { it.repoList }
		}
	}

	override fun findUsers(keywords: String?): Single<List<User>> {
		return when {
			keywords.isNullOrBlank() -> Single.error(BlankInputException(context.getString(R.string.enter_keywords)))
			else -> apiService.findUsers(keywords!!).map { it.userList }
		}
	}

	override fun findReposAndUsers(keywords: String?): Single<List<Identified>> {
		return Singles.zip(findRepos(keywords), findUsers(keywords))
		{ repos, users ->
			ArrayList<Identified>().apply {
				addAll(repos)
				addAll(users)
				sortBy { it.id }
			}
		}
	}

	override fun getFollowersCount(followersUrl: String?): Single<String> {
		return apiService.getFollowers(followersUrl).map { context.getString(R.string.followers_count, it.count()) }
	}

	override fun getRepoDetails(owner: String, repo: String): Single<Repo> {
		return apiService.getRepoDetails(owner, repo)
	}


}