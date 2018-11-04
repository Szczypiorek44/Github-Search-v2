package pl.karolmichalski.githubsearchv2.domain.interactors

import io.reactivex.Single
import pl.karolmichalski.githubsearchv2.domain.repositories.GithubRepos
import javax.inject.Inject

class FollowersCountUseCase @Inject constructor(private val githubRepos: GithubRepos) {

	fun execute(followersUrl: String?): Single<String> = githubRepos.getFollowersCount(followersUrl)

}