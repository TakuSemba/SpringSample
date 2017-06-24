package com.takusemba.springsample.extensions

import android.support.animation.DynamicAnimation
import android.support.animation.SpringAnimation
import android.support.animation.SpringForce
import android.support.annotation.FloatRange
import android.view.View

/**
 * Created by takusemba on 2017/06/24.
 */


fun View.animWithProperty(property: DynamicAnimation.ViewProperty): SpringAnimation {
    return SpringAnimation(this, property)
}

fun SpringAnimation.toPosition(finalPosition: Float): SpringAnimation {
    return this.apply { spring = SpringForce(finalPosition) }
}

fun SpringAnimation.withStiffness(@FloatRange(from = 0.0) stiffness: Float): SpringAnimation {
    return this.apply { spring.stiffness = stiffness }
}

fun SpringAnimation.withDampingRatio(@FloatRange(from = 0.0) dampingRatio: Float): SpringAnimation {
    return this.apply { spring.dampingRatio = dampingRatio }
}

fun View.rotationAmount(dx: Float, dy: Float): Float {
    return rotation + Math.toDegrees(Math.atan2(dx - width / 2.0, height / 2.0 - dy)).toFloat()
}