package com.example.todolist

import android.os.Bundle
import android.view.*
import androidx.annotation.IdRes
import androidx.annotation.Nullable
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.ui.NavigationUI.onNavDestinationSelected
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.navigation.NavigationView
import java.lang.ref.WeakReference


object CustomNavigationUI {
    fun setupWithNavController(
        navigationView: BottomNavigationView,
        navController: NavController,
        @Nullable customListener: BottomNavigationView.OnNavigationItemSelectedListener?
    ) {
        navigationView.setOnNavigationItemSelectedListener { item: MenuItem? ->
            val handled = onNavDestinationSelected(item!!, navController)
            if (handled) {
                val parent = navigationView.parent
                if (parent is DrawerLayout) {
                    parent.closeDrawer(navigationView)
                } else {
                    val bottomSheetBehavior =
                        findBottomSheetBehavior(navigationView)
                    if (bottomSheetBehavior != null) {
                        bottomSheetBehavior.state = BottomSheetBehavior.STATE_HIDDEN
                    }
                }
            } else {
                customListener?.onNavigationItemSelected(item)
            }
            handled
        }
        val weakReference: WeakReference<BottomNavigationView> = WeakReference(navigationView)
        navController.addOnDestinationChangedListener(
            object : NavController.OnDestinationChangedListener {
                override fun onDestinationChanged(
                    controller: NavController,
                    destination: NavDestination, @Nullable arguments: Bundle?
                ) {
                    val view: BottomNavigationView? = weakReference.get()
                    if (view == null) {
                        navController.removeOnDestinationChangedListener(this)
                        return
                    }
                    val menu: Menu = view.menu
                    var h = 0
                    val size: Int = menu.size()
                    while (h < size) {
                        val item: MenuItem = menu.getItem(h)
                        item.isChecked = matchDestination(destination, item.itemId)
                        h++
                    }
                }
            })
    }

    private fun findBottomSheetBehavior(view: View): BottomSheetBehavior<*>? {
        val params: ViewGroup.LayoutParams = view.layoutParams
        if (params !is CoordinatorLayout.LayoutParams) {
            val parent: ViewParent = view.parent
            return if (parent is View) {
                findBottomSheetBehavior(parent as View)
            } else null
        }
        val behavior = params
            .behavior
        return if (behavior !is BottomSheetBehavior<*>) {
            // We hit a CoordinatorLayout, but the View doesn't have the BottomSheetBehavior
            null
        } else behavior
    }

    fun matchDestination(
        destination: NavDestination,
        @IdRes destId: Int
    ): Boolean {
        var currentDestination: NavDestination? = destination
        while (currentDestination!!.id != destId && currentDestination.parent != null) {
            currentDestination = currentDestination.parent
        }
        return currentDestination.id == destId
    }
}