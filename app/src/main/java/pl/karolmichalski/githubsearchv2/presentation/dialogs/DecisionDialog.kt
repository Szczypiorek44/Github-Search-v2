package pl.karolmichalski.githubsearchv2.presentation.dialogs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import pl.karolmichalski.githubsearchv2.R
import pl.karolmichalski.githubsearchv2.databinding.DialogDecisionBinding

class DecisionDialog : DialogFragment() {

	companion object {
		fun newInstance(title: String, button1text: String, button2text: String): DecisionDialog {
			return DecisionDialog().apply {
				this.title = title
				this.button1text = button1text
				this.button2text = button2text
			}
		}
	}

	private var title: String? = null
	private var button1text = ""
	private var button2text = ""

	var onButton1Click: () -> Unit = {}
	var onButton2Click: () -> Unit = {}

	override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
		return DataBindingUtil.inflate<DialogDecisionBinding>(inflater, R.layout.dialog_decision, container, false).also {
			it.setLifecycleOwner(this)
			it.title = title
			it.button1text = button1text
			it.button2text = button2text
			it.onButton1Click = onButton1Click
			it.onButton2Click = onButton2Click
		}.root
	}
}