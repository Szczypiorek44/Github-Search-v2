package pl.karolmichalski.githubsearchv2.presentation.screens.repos

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import pl.karolmichalski.githubsearchv2.data.exceptions.BlankInputException
import pl.karolmichalski.githubsearchv2.domain.interactors.ReposAndUsersUseCase
import pl.karolmichalski.githubsearchv2.presentation.App
import javax.inject.Inject

class ReposViewModel(app: App) : AndroidViewModel(app) {

	class Factory(private val application: Application) : ViewModelProvider.NewInstanceFactory() {
		override fun <T : ViewModel?> create(modelClass: Class<T>): T {
			@Suppress("UNCHECKED_CAST")
			return ReposViewModel(application as App) as T
		}
	}

	val keywords = MutableLiveData<String>()
	val reposAndUsers = MutableLiveData<List<Any>>().apply { value = ArrayList() }
	val isLoading = MutableLiveData<Boolean>().apply { value = false }
	val errorMessage = MutableLiveData<String>()

	@Inject
	lateinit var reposAndUsersUseCase: ReposAndUsersUseCase

	init {
		app.appComponent.inject(this)
	}

	fun findReposAndUsers() {
		reposAndUsersUseCase.execute(keywords.value)
				.subscribeOn(Schedulers.io())
				.observeOn(AndroidSchedulers.mainThread())
				.doOnSubscribe { isLoading.postValue(true) }
				.doFinally { isLoading.postValue(false) }
				.subscribeBy(
						onSuccess = {
							reposAndUsers.value = it
						},
						onError = {
							if (it is BlankInputException)
								reposAndUsers.value = ArrayList()
							errorMessage.value = it.localizedMessage
						})
	}
}