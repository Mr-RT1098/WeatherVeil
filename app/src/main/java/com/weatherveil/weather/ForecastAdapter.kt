package com.weatherveil.weather

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.weatherveil.R
import java.text.SimpleDateFormat
import java.util.*
import kotlin.math.roundToInt

class ForecastAdapter(private var items: List<ForecastItem>) :
    RecyclerView.Adapter<ForecastAdapter.ForecastViewHolder>() {

    inner class ForecastViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvDay: TextView = view.findViewById(R.id.tvForecastDay)
        val tvTemp: TextView = view.findViewById(R.id.tvForecastTemp)
        val ivIcon: ImageView = view.findViewById(R.id.ivForecastIcon)
        val tvDesc: TextView = view.findViewById(R.id.tvForecastDesc)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ForecastViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_forecast, parent, false)
        return ForecastViewHolder(view)
    }

    override fun onBindViewHolder(holder: ForecastViewHolder, position: Int) {
        val item = items[position]
        val sdf = SimpleDateFormat("EEE", Locale.getDefault())
        val date = Date(item.dt * 1000)
        holder.tvDay.text = sdf.format(date)
        holder.tvTemp.text = "${item.main.temp.roundToInt()}°"
        holder.tvDesc.text = item.weather.firstOrNull()?.description?.replaceFirstChar { it.uppercase() } ?: ""
        holder.ivIcon.setImageResource(getWeatherIcon(item.weather.firstOrNull()?.icon ?: "01d"))
    }

    override fun getItemCount() = items.size

    fun updateData(newItems: List<ForecastItem>) {
        items = newItems
        notifyDataSetChanged()
    }

    private fun getWeatherIcon(iconCode: String): Int {
        return when {
            iconCode.startsWith("01") -> R.drawable.ic_weather_sunny
            iconCode.startsWith("02") || iconCode.startsWith("03") -> R.drawable.ic_weather_partly_cloudy
            iconCode.startsWith("04") -> R.drawable.ic_weather_cloudy
            iconCode.startsWith("09") || iconCode.startsWith("10") -> R.drawable.ic_weather_rain
            iconCode.startsWith("11") -> R.drawable.ic_weather_storm
            iconCode.startsWith("13") -> R.drawable.ic_weather_snow
            iconCode.startsWith("50") -> R.drawable.ic_weather_mist
            else -> R.drawable.ic_weather_sunny
        }
    }
}
