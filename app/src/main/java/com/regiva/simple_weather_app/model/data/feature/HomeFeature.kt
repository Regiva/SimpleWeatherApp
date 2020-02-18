package com.regiva.simple_weather_app.model.data.feature

import com.badoo.mvicore.element.Actor
import com.badoo.mvicore.element.NewsPublisher
import com.badoo.mvicore.element.Reducer
import com.badoo.mvicore.feature.ActorReducerFeature
import com.regiva.simple_weather_app.entity.responses.CityEntity
import com.regiva.simple_weather_app.entity.responses.OneDayWeatherEntity
import com.regiva.simple_weather_app.model.data.feature.HomeFeature.*
import com.regiva.simple_weather_app.model.interactors.WeatherInteractor
import io.reactivex.Observable
import javax.inject.Inject

class HomeFeature @Inject constructor(
    weatherInteractor: WeatherInteractor
) : ActorReducerFeature<Wish, Effect, State, News>(
    initialState = State(),
    actor = ActorImpl(
        weatherInteractor
    ),
    reducer = ReducerImpl(),
    newsPublisher = NewsPublisherImpl()
) {

    data class State(
        val isLoading: Boolean = false,
        val weatherForFiveDays: Pair<List<OneDayWeatherEntity>, CityEntity>? = null
    )

    sealed class Wish {
        object GetWeather : Wish()
    }

    sealed class Effect {
        object GetWeather : Effect()
        data class GetWeatherSuccess(val weatherForFiveDays: Pair<List<OneDayWeatherEntity>, CityEntity>) : Effect()
        data class GetWeatherFailure(val throwable: Throwable) : Effect()
    }

    sealed class News {
        data class GetWeatherFailure(val throwable: Throwable) : News()
    }

    class ActorImpl(
        private val weatherInteractor: WeatherInteractor
    ) : Actor<State, Wish, Effect> {
        override fun invoke(state: State, wish: Wish): Observable<Effect> = when (wish) {
            is Wish.GetWeather ->
                weatherInteractor.getWeatherForFiveDays()
                    .map {
                        Effect.GetWeatherSuccess(it.body()!!.list to it.body()!!.city) as Effect
                    }
                    .startWith(Effect.GetWeather)
                    .onErrorReturn { Effect.GetWeatherFailure(it) }
        }
    }

    class ReducerImpl : Reducer<State, Effect> {
        override fun invoke(state: State, effect: Effect): State = when (effect) {
            is Effect.GetWeather -> state.copy(isLoading = true)
            is Effect.GetWeatherSuccess -> state.copy(isLoading = false, weatherForFiveDays = effect.weatherForFiveDays)
            is Effect.GetWeatherFailure -> state.copy(isLoading = false)
        }
    }

    class NewsPublisherImpl : NewsPublisher<Wish, Effect, State, News> {
        override fun invoke(wish: Wish, effect: Effect, state: State): News? = when (effect) {
            is Effect.GetWeatherFailure -> News.GetWeatherFailure(effect.throwable)
            else -> null
        }
    }
}