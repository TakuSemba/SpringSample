package com.takusemba.springsample

import android.content.Context
import android.support.animation.SpringAnimation
import android.support.animation.SpringForce
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.ScaleGestureDetector
import android.view.View
import com.takusemba.springsample.extensions.*

/**
 * Created by takusemba on 2017/06/24.
 */

class SpringScaleView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) : View(context, attrs, defStyleAttr) {

    private companion object Params {
        val INITIAL_SCALE = 1f
        val STIFFNESS = SpringForce.STIFFNESS_MEDIUM
        val DAMPING_RATIO = SpringForce.DAMPING_RATIO_HIGH_BOUNCY
    }

    private val xAnim: SpringAnimation = animWithProperty(SpringAnimation.SCALE_X)
            .toPosition(INITIAL_SCALE)
            .withStiffness(STIFFNESS)
            .withDampingRatio(DAMPING_RATIO)


    private val yAnim: SpringAnimation = animWithProperty(SpringAnimation.SCALE_Y)
            .toPosition(INITIAL_SCALE)
            .withStiffness(STIFFNESS)
            .withDampingRatio(DAMPING_RATIO)

    private val detector = ScaleGestureDetector(context,
            object : ScaleGestureDetector.SimpleOnScaleGestureListener() {
                override fun onScale(detector: ScaleGestureDetector): Boolean {
                    var scaleFactor = 1f
                    scaleFactor *= detector.scaleFactor
                    scaleX *= scaleFactor
                    scaleY *= scaleFactor
                    return true
                }
            })

    init {
        setOnTouchListener { _, event ->
            if (event.action == MotionEvent.ACTION_UP) {
                xAnim.start()
                yAnim.start()
            } else {
                xAnim.cancel()
                yAnim.cancel()
                detector.onTouchEvent(event)
            }
            true
        }
    }

}
