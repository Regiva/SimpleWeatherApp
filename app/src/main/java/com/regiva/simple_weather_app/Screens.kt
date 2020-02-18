package com.regiva.simple_weather_app

import com.regiva.simple_weather_app.ui.home.HomeFlowFragment
import com.regiva.simple_weather_app.ui.home.HomeFragment
import ru.terrakok.cicerone.android.support.SupportAppScreen

object Screens {

    //todo
    //flows

    //tab flows
    object HomeFlow : SupportAppScreen() {
        override fun getFragment() = HomeFlowFragment()
    }

    //screens
    object Home : SupportAppScreen() {
        override fun getFragment() = HomeFragment()
    }

}