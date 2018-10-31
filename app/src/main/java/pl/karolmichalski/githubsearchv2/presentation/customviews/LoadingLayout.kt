package pl.karolmichalski.githubrepos.presentation.customviews

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.view.Gravity
import android.view.View
import android.widget.FrameLayout
import android.widget.ProgressBar

private const val BACKGROUND_COLOR = "#33000000"
private const val PROGRESS_BAR_SIZE = 60

class LoadingLayout @JvmOverloads constructor(
		context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

	init {
		val progressSize = (context.resources.displayMetrics.density * PROGRESS_BAR_SIZE).toInt()

		isClickable = true
		isFocusable = true
		visibility = View.GONE

		setBackgroundColor(Color.parseColor(BACKGROUND_COLOR))

		val progressBar = ProgressBar(context)
		progressBar.layoutParams = LayoutParams(progressSize, progressSize, Gravity.CENTER)
		addView(progressBar)
	}
}