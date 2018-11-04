package pl.karolmichalski.githubsearchv2.presentation.screens.details

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.activity_details.*
import pl.karolmichalski.githubsearchv2.R
import pl.karolmichalski.githubsearchv2.data.models.User
import pl.karolmichalski.githubsearchv2.databinding.ActivityDetailsBinding
import pl.karolmichalski.githubsearchv2.presentation.dialogs.DecisionDialog
import pl.karolmichalski.githubsearchv2.presentation.utils.IntentDelegate

class DetailsActivity : AppCompatActivity() {

	companion object {
		private var Intent.user by IntentDelegate.User("user")

		fun getIntent(context: Context, user: User): Intent {
			return Intent(context, DetailsActivity::class.java).also {
				it.user = user
			}
		}
	}

	private val viewModel by lazy {
		ViewModelProviders.of(this, DetailsViewModel.Factory(application)).get(DetailsViewModel::class.java)
	}

	private val binding by lazy {
		DataBindingUtil.setContentView<ActivityDetailsBinding>(this, R.layout.activity_details)
	}

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		binding.let {
			it.setLifecycleOwner(this)
			it.viewModel = viewModel
		}
		viewModel.let {
			it.user.value = intent.user
			it.errorMessage.observe(this, Observer { message ->
				Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
			})
			it.getFollowersCount()
		}
		card2.setOnClickListener { showDecisionDialog() }
	}

	private fun showDecisionDialog() {
		DecisionDialog.newInstance(
				title = getString(R.string.are_you_ready_for_some_magic_question),
				button1text = getString(R.string.yes),
				button2text = getString(R.string.no))
				.apply {
					onButton1Click = {
						dismiss()
					}
					onButton2Click = {
						dismiss()
					}
				}
				.show(supportFragmentManager, DecisionDialog::class.java.simpleName)
	}
}