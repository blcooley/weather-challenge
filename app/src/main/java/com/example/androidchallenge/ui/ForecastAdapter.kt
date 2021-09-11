package com.example.androidchallenge.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.androidchallenge.R
import com.example.androidchallenge.databinding.ItemForecastDayBinding
import com.example.androidchallenge.extension.loadIcon
import com.example.androidchallenge.network.models.DailyWeather
import com.example.androidchallenge.util.formatDate
import com.example.androidchallenge.util.getWeekday
import kotlin.math.roundToInt

class ForecastAdapter(
    private val items: List<DailyWeather>,
    private val timeZoneString: String? = null
) : RecyclerView.Adapter<ForecastViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ForecastViewHolder {
        val binding = ItemForecastDayBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ForecastViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ForecastViewHolder, position: Int) {
        with(holder.binding) {
            val context = this.root.context
            val forecast = items[position]
            dayText.text = getWeekday(forecast.date, timeZoneString)
            dateText.text = formatDate(forecast.date, timeZoneString)
            highLowTemperatureText.text = context.getString(R.string.temperature_high_low_digits_format, forecast.temp.high, forecast.temp.low)
            mainIcon.loadIcon(forecast.weather[0].icon)
            rainfallText.text = "${(forecast.chanceOfRain * 100).roundToInt()}%"
        }
    }

    override fun getItemCount() = items.size
}