package pl.karolmichalski.githubsearchv2.presentation.screens.details

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import pl.karolmichalski.githubsearchv2.data.models.User
import pl.karolmichalski.githubsearchv2.domain.interactors.FollowersCountUseCase
import pl.karolmichalski.githubsearchv2.presentation.App
import javax.inject.Inject

class DetailsViewModel(app: App) : ViewModel() {

	class Factory(private val application: Application) : ViewModelProvider.NewInstanceFactory() {
		override fun <T : ViewModel?> create(modelClass: Class<T>): T {
			@Suppress("UNCHECKED_CAST")
			return DetailsViewModel(application as App) as T
		}
	}

	val user = MutableLiveData<User>()
	val followersCount = MutableLiveData<String>()
	val isLoading = MutableLiveData<Boolean>().apply { value = false }
	val errorMessage = MutableLiveData<String>()

	@Inject
	lateinit var followersCountUseCase: FollowersCountUseCase

	init {
		app.appComponent.inject(this)
	}

	fun getFollowersCount() {
		followersCountUseCase.execute(user.value?.followersUrl)
				.subscribeOn(Schedulers.io())
				.observeOn(AndroidSchedulers.mainThread())
				.doOnSubscribe { isLoading.postValue(true) }
				.doFinally { isLoading.postValue(false) }
				.subscribeBy(
						onSuccess = { followersCount.value = it },
						onError = { errorMessage.value = it.localizedMessage }
				)
	}

}