package com.regiva.simple_weather_app.ui.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.regiva.simple_weather_app.R
import com.regiva.simple_weather_app.entity.responses.OneDayWeatherEntity
import com.regiva.simple_weather_app.util.applyDiff

class DaysAdapter(
    private var list: List<OneDayWeatherEntity>
) : RecyclerView.Adapter<DayHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        DayHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_day,
                parent,
                false
            )
        )

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: DayHolder, position: Int) = holder.bind(list[position])

    fun updateList(newList: List<OneDayWeatherEntity>) {
        applyDiff(
            oldList = list,
            newList = newList,
            areItemsTheSame = { old, new -> old.date == new.date },
            areContentsTheSame = { old, new -> old == new }
        )
        this.list = newList
    }
}