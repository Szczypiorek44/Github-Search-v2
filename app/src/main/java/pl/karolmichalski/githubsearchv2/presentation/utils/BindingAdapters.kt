package pl.karolmichalski.githubsearchv2.presentation.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter
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