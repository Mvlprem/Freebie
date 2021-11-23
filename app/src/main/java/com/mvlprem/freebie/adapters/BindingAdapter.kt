package com.mvlprem.freebie.adapters

import android.widget.ImageView
import android.widget.TextView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.mvlprem.freebie.R
import com.mvlprem.freebie.model.Games

/**
 * These adapters populate views with respective data.
 * Use the value("title") to call this extinction function in xml
 * @param item model data Class
 */

@BindingAdapter("title")
fun TextView.gameTitle(item: Games) {
    item.let {
        text = item.title
    }
}

@BindingAdapter("type")
fun TextView.gameType(item: Games) {
    item.let {
        text = item.type
    }
}

@BindingAdapter("worth")
fun TextView.gameWorth(item: Games) {
    item.let {
        text = item.worth
    }
}

@BindingAdapter("description")
fun TextView.gameDescription(item: Games) {
    item.let {
        text = item.description
    }
}

@BindingAdapter("instructions")
fun TextView.gameInstructions(item: Games) {
    item.let {
        text = item.instructions
    }
}

/**
 * Function using Glide library to load the uri into imageview.
 * @param imageView to provide context for Glide
 * @param item model data Class
 */
@BindingAdapter("image")
fun gameImage(imageView: ImageView, item: Games) {
    item.let {
        val url = item.image
        val uri = url.toUri().buildUpon().scheme("https").build()
        Glide.with(imageView.context)
            .load(uri)
            .apply(
                RequestOptions()
                    .placeholder(R.drawable.loading_animation)
                    .error(R.drawable.broken_image)
            )
            .into(imageView)
    }
}