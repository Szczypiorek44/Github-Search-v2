package pl.karolmichalski.githubsearchv2.presentation.screens.details

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import pl.karolmichalski.githubsearchv2.data.models.Repo
import pl.karolmichalski.githubsearchv2.domain.interactors.RepoDetailsUseCase
import pl.karolmichalski.githubsearchv2.presentation.App
import javax.inject.Inject

class DetailsViewModel(app: App) : ViewModel() {

	class Factory(private val application: Application) : ViewModelProvider.NewInstanceFactory() {
		override fun <T : ViewModel?> create(modelClass: Class<T>): T {
			@Suppress("UNCHECKED_CAST")
			return DetailsViewModel(application as App) as T
		}
	}

	val repo = MutableLiveData<Repo>()
	val isLoading = MutableLiveData<Boolean>().apply { value = false }
	val errorMessage = MutableLiveData<String>()

	@Inject
	lateinit var repoDetailsUseCase: RepoDetailsUseCase

	init {
		app.appComponent.inject(this)
	}

	fun getRepoDetails(owner: String, repo: String) {
		repoDetailsUseCase.execute(owner, repo)
				.subscribeOn(Schedulers.io())
				.observeOn(AndroidSchedulers.mainThread())
				.doOnSubscribe { isLoading.postValue(true) }
				.doFinally { isLoading.postValue(false) }
				.subscribeBy(
						onSuccess = { this.repo.value = it },
						onError = { errorMessage.value = it.localizedMessage }
				)
	}

}