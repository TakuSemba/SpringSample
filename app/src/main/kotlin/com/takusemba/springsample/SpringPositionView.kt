package com.takusemba.springsample

import android.content.Context
import android.support.animation.SpringAnimation
import android.support.animation.SpringForce
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import com.takusemba.springsample.extensions.animWithProperty
import com.takusemba.springsample.extensions.toPosition
import com.takusemba.springsample.extensions.withDampingRatio
import com.takusemba.springsample.extensions.withStiffness

/**
 * Created by takusemba on 2017/06/24.
 */

class SpringPositionView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) : View(context, attrs, defStyleAttr) {

    private companion object Params {
        private const val STIFFNESS = SpringForce.STIFFNESS_MEDIUM
        private const val DAMPING_RATIO = SpringForce.DAMPING_RATIO_HIGH_BOUNCY
    }

    private lateinit var xAnim: SpringAnimation
    private lateinit var yAnim: SpringAnimation

    init {

        viewTreeObserver.addOnGlobalLayoutListener {
            xAnim = animWithProperty(SpringAnimation.X)
                    .toPosition(x)
                    .withStiffness(STIFFNESS)
                    .withDampingRatio(DAMPING_RATIO)
            yAnim = animWithProperty(SpringAnimation.Y)
                    .toPosition(y)
                    .withStiffness(STIFFNESS)
                    .withDampingRatio(DAMPING_RATIO)
        }

        var dX = 0f
        var dY = 0f
        setOnTouchListener { view, event ->
            when (event.actionMasked) {
                MotionEvent.ACTION_DOWN -> {
                    dX = view.x - event.rawX
                    dY = view.y - event.rawY

                    xAnim.cancel()
                    yAnim.cancel()
                }
                MotionEvent.ACTION_MOVE -> {
                    animate()
                            .x(event.rawX + dX)
                            .y(event.rawY + dY)
                            .setDuration(0)
                            .start()
                }
                MotionEvent.ACTION_UP -> {
                    xAnim.start()
                    yAnim.start()
                }
            }
            true
        }
    }

}
