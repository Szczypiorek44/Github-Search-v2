package pl.karolmichalski.githubsearchv2.presentation.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade
import pl.karolmichalski.githubsearchv2.R


@BindingAdapter("imageUrl")
fun ImageView.setImageUrl(url: String?) {
	GlideApp.with(this)
			.load(url)
			.placeholder(R.drawable.placeholder_avatar)
			.transition(withCrossFade())
			.into(this)
}

@BindingAdapter("onScroll")
fun RecyclerView.onScroll(onScroll: () -> Unit) {
	addOnScrollListener(object : RecyclerView.OnScrollListener() {
		override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
			if (newState==1)
				onScroll()
		}
	})
}