package com.regiva.simple_weather_app.ui.home.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.regiva.simple_weather_app.entity.responses.OneDayWeatherEntity
import com.regiva.simple_weather_app.util.convertToDateFormat
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_day.*
import kotlin.math.roundToInt

class DayHolder(
    override val containerView: View
) : RecyclerView.ViewHolder(containerView), LayoutContainer {

    fun bind(
        data: OneDayWeatherEntity
    ) {
        tv_day.text = data.date.convertToDateFormat("E HH:mm")
        tv_temp.text = "${data.mainInfo.temp.roundToInt()}°"

        Glide.with(containerView.context)
            .load("https://openweathermap.org/img/wn/" + data.weather.first().icon + "@2x.png")
            .into(iv_status)
    }
}