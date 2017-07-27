package tech.thdev.lifecycle.sample.photo.common.behavior

import android.content.Context
import android.support.design.widget.BottomNavigationView
import android.util.AttributeSet

/**
 * Created by tae-hwan on 7/27/17.
 */
class BottomNavigationViewBehavior(context: Context? = null, attributeSet: AttributeSet? = null) : VerticalViewBehavior<BottomNavigationView>(context, attributeSet) {

    var duration = 200L

    override fun slideUp(child: BottomNavigationView) {
        child.run {
            clearAnimation()
            animate().translationY(0f).duration = duration
        }
    }

    override fun slideDown(child: BottomNavigationView) {
        child.run {
            clearAnimation()
            animate().translationY(height.toFloat()).duration = duration
        }
    }
}