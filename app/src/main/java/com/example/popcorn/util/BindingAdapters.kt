package com.example.popcorn.util

import android.view.inputmethod.EditorInfo
import android.widget.EditText
import android.widget.ImageView
import androidx.annotation.DrawableRes
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

@BindingAdapter("thumbnail")
fun ImageView.setThumbnail(@DrawableRes resource: Int?) {
    Glide.with(context)
        .load(resource)
        .centerCrop()
        .into(this)
}

@BindingAdapter("onKeyDone")
fun EditText.onKeyDone(listener: () -> Unit) {
    setOnEditorActionListener { _, actionId, _ ->
        if (actionId == EditorInfo.IME_ACTION_DONE) {
            listener.invoke()
        }
        return@setOnEditorActionListener false
    }
}