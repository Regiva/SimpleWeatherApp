package com.regiva.simple_weather_app.presentation

import com.regiva.simple_weather_app.Screens
import com.regiva.simple_weather_app.di.DI
import com.regiva.simple_weather_app.di.module.ServerModule
import com.regiva.simple_weather_app.model.interactors.AuthInteractor
import ru.terrakok.cicerone.Router
import toothpick.Toothpick
import javax.inject.Inject

class AppLauncher @Inject constructor(
    private val router: Router,
    private val authInteractor: AuthInteractor
) {

    fun initModules() {
        if (!Toothpick.isScopeOpen(DI.SERVER_SCOPE)) {
            Toothpick.openScopes(DI.APP_SCOPE, DI.SERVER_SCOPE)
                .installModules(ServerModule())
        }
    }

    fun coldStart() {
        //todo
        /*if (authInteractor.isLoggedIn())
            router.newRootScreen(Screens.Main())
        else
            router.newRootScreen(Screens.AuthFlow)*/
        router.newRootScreen(Screens.HomeFlow)
    }
}