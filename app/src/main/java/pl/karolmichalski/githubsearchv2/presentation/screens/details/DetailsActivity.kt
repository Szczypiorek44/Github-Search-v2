package pl.karolmichalski.githubsearchv2.presentation.screens.details

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.activity_details.*
import pl.karolmichalski.githubsearchv2.R
import pl.karolmichalski.githubsearchv2.databinding.ActivityDetailsBinding
import pl.karolmichalski.githubsearchv2.presentation.dialogs.DecisionDialog
import pl.karolmichalski.githubsearchv2.presentation.utils.IntentDelegate

var Intent.owner by IntentDelegate.String("owner")
var Intent.repo by IntentDelegate.String("repo")

class DetailsActivity : AppCompatActivity() {

	private val viewModel by lazy {
		ViewModelProviders.of(this, DetailsViewModel.Factory(application)).get(DetailsViewModel::class.java)
	}

	private val binding by lazy {
		DataBindingUtil.setContentView<ActivityDetailsBinding>(this, R.layout.activity_details)
	}

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		binding.apply {
			setLifecycleOwner(this@DetailsActivity)
			viewModel = this@DetailsActivity.viewModel
		}
		viewModel.apply {
			repo.observe(this@DetailsActivity, Observer { binding.invalidateAll() })
			errorMessage.observe(this@DetailsActivity, Observer {
				Toast.makeText(this@DetailsActivity, it, Toast.LENGTH_SHORT).show()
			})
			getRepoDetails(intent.owner, intent.repo)
		}
		card2.setOnClickListener {
			showDecisionDialog()
		}
	}

	private fun showDecisionDialog() {
		DecisionDialog().apply {
			title = this@DetailsActivity.getString(R.string.are_you_ready_for_some_magic_question)
			button1text = this@DetailsActivity.getString(R.string.yes)
			button2text = this@DetailsActivity.getString(R.string.no)
			onButton1Click = {
				dismiss()
			}
			onButton2Click = {
				dismiss()
			}
			show(supportFragmentManager, DecisionDialog::class.java.simpleName)
		}
	}
}