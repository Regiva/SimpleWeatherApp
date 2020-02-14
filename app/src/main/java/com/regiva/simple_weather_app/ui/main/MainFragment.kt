package com.regiva.simple_weather_app.ui.main

import android.os.Bundle
import androidx.core.os.bundleOf
import com.regiva.simple_weather_app.R
import com.regiva.simple_weather_app.Screens
import com.regiva.simple_weather_app.ui.base.BaseFragment
import com.regiva.simple_weather_app.ui.base.FlowFragment
import ru.terrakok.cicerone.Router
import ru.terrakok.cicerone.android.support.SupportAppScreen

class MainFragment : BaseFragment() {

    companion object {
        fun create(currentTab: Int? = null) =
            MainFragment().apply {
                arguments = bundleOf(
                    EXTRA_CURRENT_TAB to currentTab
                )
            }

        private const val EXTRA_CURRENT_TAB = "EXTRA_CURRENT_TAB"
    }

    private val router: Router by scope()

    private var currentTab: Int? = null
    //todo
//    private val homeTab by lazy { Screens.HomeFlow }
//    private val profileTab by lazy { Screens.HomeFlow }

    override val layoutRes: Int
        get() = R.layout.fragment_main

    private val currentTabFragment: FlowFragment?
        get() = childFragmentManager.fragments.firstOrNull { !it.isHidden } as? FlowFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments?.containsKey(EXTRA_CURRENT_TAB) == true) {
            currentTab = arguments!!.getInt(EXTRA_CURRENT_TAB)
        }
    }

    override fun onBackPressed() {
        currentTabFragment?.onBackPressed()
    }
}