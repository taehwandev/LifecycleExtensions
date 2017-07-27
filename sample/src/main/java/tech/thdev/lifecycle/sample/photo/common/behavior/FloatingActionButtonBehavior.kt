package tech.thdev.lifecycle.sample.photo.common.behavior

import android.content.Context
import android.support.design.widget.FloatingActionButton
import android.util.AttributeSet

/**
 * Created by tae-hwan on 7/27/17.
 */
class FloatingActionButtonBehavior(context: Context? = null, attributeSet: AttributeSet? = null) : VerticalViewBehavior<FloatingActionButton>(context, attributeSet) {

    var duration = 200L

    override fun slideUp(child: FloatingActionButton) {
        child.run {
            clearAnimation()
            animate().alpha(1f).translationY(0f).duration = duration
        }
    }

    override fun slideDown(child: FloatingActionButton) {
        child.run {
            clearAnimation()
            animate().alpha(0f).translationY(height.toFloat()).duration = duration
        }
    }
}