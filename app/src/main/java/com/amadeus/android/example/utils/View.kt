package com.amadeus.android.example.utils

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.app.Activity
import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment

/**
 * Copyright (c) SRG SSR. All rights reserved.
 * <p>
 * License information is available from the LICENSE file.
 */

/** Set the View visibility to VISIBLE and eventually animate the View alpha till 100% */
fun View.visible(animate: Boolean = false, duration: Long = 300) {
    if (animate) {
        takeIf { !it.isVisible }?.apply {
            // Set the content view to 0% opacity but visible, so that it is visible
            // (but fully transparent) during the animation.
            alpha = 0f
            visibility = View.VISIBLE

            // Animate the content view to 100% opacity, and clear any animation
            // listener set on the view.
            animate()
                    .alpha(1f)
                    .setDuration(duration)
                    .setListener(null)
        }
    } else {
        visibility = View.VISIBLE
    }
}

/** Set the View visibility to INVISIBLE and eventually animate view alpha till 0% */
fun View.invisible(animate: Boolean = false, duration: Long = 300) {
    hide(View.INVISIBLE, animate, duration)
}

/** Set the View visibility to GONE and eventually animate view alpha till 0% */
fun View.gone(animate: Boolean = false, duration: Long = 300) {
    hide(View.GONE, animate, duration)
}

/** Convenient method that chooses between View.visible() or View.invisible() methods */
fun View.visibleOrInvisible(show: Boolean, animate: Boolean = false, duration: Long = 300) {
    if (show) visible(animate, duration) else invisible(animate, duration)
}

/** Convenient method that chooses between View.visible() or View.gone() methods */
fun View.visibleOrGone(show: Boolean, animate: Boolean = false, duration: Long = 300) {
    if (show) visible(animate, duration) else gone(animate, duration)
}

private fun View.hide(hidingStrategy: Int, animate: Boolean = false, duration: Long) {
    if (animate) {
        takeIf { it.isVisible }?.apply {
            animate().alpha(0f).setDuration(duration).setListener(object : AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator) {
                    super.onAnimationEnd(animation)
                    visibility = hidingStrategy
                }
            })
        }
    } else {
        visibility = hidingStrategy
    }
}

fun Fragment.hideKeyboard() {
    activity?.hideKeyboard(this.view)
}

fun Activity.hideKeyboard() {
    hideKeyboard(if (currentFocus == null) View(this) else currentFocus)
}

fun Context.hideKeyboard(view: View?) {
    view?.let {
        val inputMethodManager =
            getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(it.windowToken, 0)
    }
}