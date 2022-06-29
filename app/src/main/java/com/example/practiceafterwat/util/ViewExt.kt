package com.example.practiceafterwat.util

import android.app.Activity
import android.content.Context
import android.os.Build
import android.text.Html
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions

fun View.hide() {
    visibility = View.GONE
}

fun View.show() {
    visibility = View.VISIBLE
}

fun SwipeRefreshLayout.hide() {
    isRefreshing = false
}

fun SwipeRefreshLayout.show() {
    isRefreshing = true
}

fun ImageView.show(resId: Int) {
    if (!isValidContextForGlide(this.context)) {
        return
    }

    Glide.with(this.context)
        .setDefaultRequestOptions(
            RequestOptions
            .diskCacheStrategyOf(DiskCacheStrategy.NONE)
            .skipMemoryCache(true)
        )
        .load(resId)
        .into(this)
}

fun ImageView.show(url: String) {
    if (!isValidContextForGlide(this.context)) {
        return
    }

    Glide.with(this.context)
        .load(url)
        .into(this)
}

fun ImageView.bindLoadImage(view: AppCompatImageView, url: String) {
    Glide.with(view.context).load(url).into(view)
}

fun TextView.setHtmlText(url: String? = "") {
    text = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
        Html.fromHtml(url, Html.FROM_HTML_MODE_COMPACT)
    } else {
        Html.fromHtml(url)
    }
}

private fun isValidContextForGlide(context: Context?): Boolean {
    if (context == null) {
        return false
    }

    if (context is Activity) {
        if (context.isDestroyed || context.isFinishing) {
            return false
        }
    }

    return true
}