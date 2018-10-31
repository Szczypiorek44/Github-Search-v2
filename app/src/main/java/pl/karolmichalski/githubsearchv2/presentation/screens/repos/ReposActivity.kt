package pl.karolmichalski.githubsearchv2.presentation.screens.repos

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import pl.karolmichalski.githubsearchv2.R
import pl.karolmichalski.githubsearchv2.data.models.Repo
import pl.karolmichalski.githubsearchv2.databinding.ActivityReposBinding
import pl.karolmichalski.githubsearchv2.presentation.screens.details.DetailsActivity
import pl.karolmichalski.githubsearchv2.presentation.screens.details.owner
import pl.karolmichalski.githubsearchv2.presentation.screens.details.repo
import pl.karolmichalski.githubsearchv2.presentation.utils.BundleDelegate
import pl.karolmichalski.githubsearchv2.presentation.utils.hideSoftKeyboard

class ReposActivity : AppCompatActivity(), ReposListener {

	private var Bundle.keywords by BundleDelegate.String("keywords")
	private var Bundle.repoList by BundleDelegate.List<Repo>("repoList")

	private val viewModel by lazy {
		ViewModelProviders.of(this, ReposViewModel.Factory(application)).get(ReposViewModel::class.java)
	}

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		DataBindingUtil.setContentView<ActivityReposBinding>(this, R.layout.activity_repos).apply {
			setLifecycleOwner(this@ReposActivity)
			viewModel = this@ReposActivity.viewModel
			listener = this@ReposActivity
		}
		viewModel.errorMessage.observe(this, Observer {
			Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
		})
	}

	override fun onSaveInstanceState(outState: Bundle?) {
		super.onSaveInstanceState(outState)
		viewModel.keywords.value?.let { keywords ->
			outState?.keywords = keywords
		}
		viewModel.repoList.value?.let { repoList ->
			outState?.repoList = repoList
		}
	}

	override fun onRestoreInstanceState(savedInstanceState: Bundle?) {
		super.onRestoreInstanceState(savedInstanceState)
		viewModel.keywords.value = savedInstanceState?.keywords
		viewModel.repoList.value = savedInstanceState?.repoList
	}

	override fun onSearchClick() {
		hideSoftKeyboard()
		viewModel.findRepos()
	}

	override fun onItemClick(): (Repo) -> Unit {
		return {
			val intent = Intent(this, DetailsActivity::class.java).apply {
				owner = it.owner.login
				repo = it.name
			}
			startActivity(intent)
		}
	}

}
