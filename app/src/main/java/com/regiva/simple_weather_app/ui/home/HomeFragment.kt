package com.regiva.simple_weather_app.ui.home

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.badoo.mvicore.android.AndroidBindings
import com.badoo.mvicore.binder.using
import com.badoo.mvicore.modelWatcher
import com.bumptech.glide.Glide
import com.regiva.simple_weather_app.R
import com.regiva.simple_weather_app.entity.responses.CityEntity
import com.regiva.simple_weather_app.entity.responses.OneDayWeatherEntity
import com.regiva.simple_weather_app.model.data.feature.HomeFeature
import com.regiva.simple_weather_app.ui.base.MviFragment
import com.regiva.simple_weather_app.ui.home.adapter.DaysAdapter
import com.regiva.simple_weather_app.util.ErrorHandler
import com.regiva.simple_weather_app.util.convertToDateFormat
import com.regiva.simple_weather_app.util.setLoadingState
import io.reactivex.functions.Consumer
import kotlinx.android.synthetic.main.fragment_home.*
import java.util.*
import kotlin.math.roundToInt

class HomeFragment : MviFragment<HomeFragment.ViewModel, HomeFragment.UiEvents>() {

    override val layoutRes: Int
        get() = R.layout.fragment_home

    private val feature: HomeFeature by scope()
    private val errorHandler: ErrorHandler by scope()
    private val adapter: DaysAdapter by lazy {
        DaysAdapter(listOf())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setUpBindings()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecycler()
        onNext(UiEvents.OnGetWeather)
    }

    private fun setUpBindings() {
        object : AndroidBindings<HomeFragment>(this) {
            override fun setup(view: HomeFragment) {
                binder.bind(view to feature using { event ->
                    when (event) {
                        is UiEvents.OnGetWeather -> HomeFeature.Wish.GetWeather
                    }
                })
                binder.bind(feature to view using { state ->
                    ViewModel(
                        state.isLoading,
                        state.weatherForFiveDays
                    )
                })
                binder.bind(feature.news to Consumer { news ->
                    when (news) {
                        is HomeFeature.News.GetWeatherFailure -> errorHandler.proceed(news.throwable) { view.showError(it) }
                    }
                })
            }
        }.setup(this)
    }

    private fun initRecycler() {
        rv_days.layoutManager = LinearLayoutManager(activity)
        rv_days.adapter = adapter
    }

    private fun showWeather(weatherForFiveDays: Pair<List<OneDayWeatherEntity>, CityEntity>) {
        if (weatherForFiveDays.first.isNotEmpty()) {
            with (weatherForFiveDays.first.first()) {
                //todo time
                tv_updated_at.text = Date().time.div(100).convertToDateFormat("dd MMMM yyyy, HH:mm")
                tv_current_temp.text = "${this.mainInfo.temp.roundToInt()}°"
                tv_status.text = this.weather.first().main
                tv_temp_min.text = "Min: ${this.mainInfo.temp_min.roundToInt()}°"
                tv_temp_max.text = "Max: ${this.mainInfo.temp_max.roundToInt()}°"
                tv_sunrise.text = weatherForFiveDays.second.sunrise.convertToDateFormat("HH:mm")
                tv_sunset.text = weatherForFiveDays.second.sunset.convertToDateFormat("HH:mm")
                tv_wind.text = "${this.wind.speed.roundToInt()} km/h"
                tv_pressure.text = "${this.mainInfo.pressure.roundToInt()}"

                Glide.with(requireContext())
                    .load("https://openweathermap.org/img/wn/" + this.weather.first().icon + "@2x.png")
                    .into(iv_status)
            }
            tv_city.text = weatherForFiveDays.second.name
            adapter.updateList(weatherForFiveDays.first)
//            rl_posts_not_empty?.setVisible()
//            rl_posts_empty?.setGone()
        }
        else {
//            rl_posts_empty?.setVisible()
//            rl_posts_not_empty?.setGone()
        }
    }

    override fun accept(vm: ViewModel) {
        modelWatcher<ViewModel> {
            watch(ViewModel::isLoading) { pb_loading?.setLoadingState(it) }
            watch(ViewModel::weatherForFiveDays) { it?.let { showWeather(it) } }
        }.invoke(vm)
    }

    sealed class UiEvents {
        object OnGetWeather : UiEvents()
    }

    class ViewModel(
        val isLoading: Boolean,
        val weatherForFiveDays: Pair<List<OneDayWeatherEntity>, CityEntity>?
    )
}

/*{
  "cod": "200",
  "message": 0.0032,
  "cnt": 36,
  "list": [
    {
      "dt": 1487246400,
      "main": {
        "temp": 286.67,
        "temp_min": 281.556,
        "temp_max": 286.67,
        "pressure": 972.73,
        "sea_level": 1046.46,
        "grnd_level": 972.73,
        "humidity": 75,
        "temp_kf": 5.11
      },
      "weather": [
        {
          "id": 800,
          "main": "Clear",
          "description": "clear sky",
          "icon": "01d"
        }
      ],
      "clouds": {
        "all": 0
      },
      "wind": {
        "speed": 1.81,
        "deg": 247.501
      },
      "sys": {
        "pod": "d"
      },
      "dt_txt": "2017-02-16 12:00:00"
    }
  ],
  "city": {
    "id": 6940463,
    "name": "Altstadt",
    "coord": {
      "lat": 48.137,
      "lon": 11.5752
    },
    "country": "none"
  }
}*/