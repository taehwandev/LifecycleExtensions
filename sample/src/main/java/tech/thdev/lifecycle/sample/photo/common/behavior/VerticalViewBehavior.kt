package tech.thdev.lifecycle.sample.photo.common.behavior

import android.content.Context
import android.support.design.widget.CoordinatorLayout
import android.support.v4.view.ViewCompat
import android.util.AttributeSet
import android.view.View

/**
 * Created by tae-hwan on 7/27/17.
 */
abstract class VerticalViewBehavior<V : View>(context: Context? = null, attributeSet: AttributeSet? = null) :
        CoordinatorLayout.Behavior<V>(context, attributeSet) {

    private var totalDyUnconsumed: Int = 0
    private var totalDy: Int = 0

    abstract fun slideUp(child: V)
    abstract fun slideDown(child: V)

    @Suppress("DEPRECATED_IDENTITY_EQUALS")
    override fun onStartNestedScroll(coordinatorLayout: CoordinatorLayout, child: V, directTargetChild: View, target: View, axes: Int, type: Int): Boolean {
        return axes and ViewCompat.SCROLL_AXIS_VERTICAL !== 0
    }

    override fun onNestedScroll(coordinatorLayout: CoordinatorLayout, child: V, target: View, dxConsumed: Int, dyConsumed: Int, dxUnconsumed: Int, dyUnconsumed: Int, type: Int) {
        super.onNestedScroll(coordinatorLayout, child, target, dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed, type)

        if (dyUnconsumed > 0 && totalDyUnconsumed <= 0) {
            totalDyUnconsumed = 0
            slideDown(child)
        } else if (dyUnconsumed < 0 && totalDyUnconsumed > 0) {
            totalDyUnconsumed = 0
            slideUp(child)
        }
        totalDyUnconsumed += dyUnconsumed
    }

    override fun onNestedPreScroll(coordinatorLayout: CoordinatorLayout, child: V, target: View, dx: Int, dy: Int, consumed: IntArray, type: Int) {
        super.onNestedPreScroll(coordinatorLayout, child, target, dx, dy, consumed, type)

        if (dy > 0 && totalDy <= 0) {
            totalDy = 0
            slideDown(child)

        } else if (dy < 0 && totalDy > 0) {
            totalDy = 0
            slideUp(child)
        }
        totalDy += dy
    }
}