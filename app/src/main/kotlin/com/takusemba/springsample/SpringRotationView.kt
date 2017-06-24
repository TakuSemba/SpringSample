package com.takusemba.springsample

import android.content.Context
import android.support.animation.SpringAnimation
import android.support.animation.SpringForce
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import com.takusemba.springsample.extensions.*

/**
 * Created by takusemba on 2017/06/24.
 */

class SpringRotationView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) : View(context, attrs, defStyleAttr) {

    private companion object Params {
        val INITIAL_ROTATION = 0f
        val STIFFNESS = SpringForce.STIFFNESS_MEDIUM
        val DAMPING_RATIO = SpringForce.DAMPING_RATIO_HIGH_BOUNCY
    }

    private val rotationAnim: SpringAnimation = animWithProperty(SpringAnimation.ROTATION)
            .toPosition(INITIAL_ROTATION)
            .withStiffness(STIFFNESS)
            .withDampingRatio(DAMPING_RATIO)

    init {
        var currentRotation = 0f
        setOnTouchListener { view, event ->

            when (event.actionMasked) {
                MotionEvent.ACTION_DOWN -> {
                    rotationAnim.cancel()

                    currentRotation = rotationAmount(event.x, event.y)
                }
                MotionEvent.ACTION_MOVE -> {
                    val previousRotation = currentRotation

                    currentRotation = rotationAmount(event.x, event.y)
                    view.rotation += currentRotation - previousRotation
                }
                MotionEvent.ACTION_UP -> rotationAnim.start()
            }
            true
        }
    }

}
