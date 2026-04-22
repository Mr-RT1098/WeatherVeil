package com.weatherveil.weather

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.weatherveil.R
import com.weatherveil.hidden.LoginActivity
import com.weatherveil.hidden.worker.AutoDeleteWorker
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*
import kotlin.math.roundToInt

class WeatherActivity : AppCompatActivity() {

    // ════════════════════════════════════════════════════════════
    // SECRET TRIGGER: Type "ghost" in the search field
    // This is case-insensitive and clears itself after trigger
    // ════════════════════════════════════════════════════════════
    private val SECRET_CODE = "ghost"

    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private lateinit var forecastAdapter: ForecastAdapter

    // Views
    private lateinit var etSearch: EditText
    private lateinit var btnSearch: ImageButton
    private lateinit var swipeRefresh: SwipeRefreshLayout
    private lateinit var tvCityName: TextView
    private lateinit var tvTemperature: TextView
    private lateinit var tvDescription: TextView
    private lateinit var tvFeelsLike: TextView
    private lateinit var tvHumidity: TextView
    private lateinit var tvWind: TextView
    private lateinit var tvPressure: TextView
    private lateinit var tvVisibility: TextView
    private lateinit var tvSunrise: TextView
    private lateinit var tvSunset: TextView
    private lateinit var ivWeatherIcon: ImageView
    private lateinit var rvForecast: RecyclerView
    private lateinit var tvLastUpdated: TextView
    private lateinit var progressBar: ProgressBar
    private lateinit var layoutWeatherContent: View
    private lateinit var tvError: TextView
    private lateinit var btnLocation: ImageButton

    // ⚠️ IMPORTANT: Replace with your OpenWeatherMap API key
    private val API_KEY = "f4a9263d492d377bdf31c0a873bf06c6"
    private var currentCity = "London"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_weather)

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
        initViews()
        setupForecastList()
        setupSearchTrigger()
        setupRefresh()

        // Schedule auto-delete worker for hidden messages
        AutoDeleteWorker.scheduleDaily(this)

        // Load weather on start - Try to get location, otherwise load default city
        requestLocationWeather()
    }

    private fun initViews() {
        etSearch = findViewById(R.id.etSearchCity)
        btnSearch = findViewById(R.id.btnSearch)
        swipeRefresh = findViewById(R.id.swipeRefresh)
        tvCityName = findViewById(R.id.tvCityName)
        tvTemperature = findViewById(R.id.tvTemperature)
        tvDescription = findViewById(R.id.tvWeatherDescription)
        tvFeelsLike = findViewById(R.id.tvFeelsLike)
        tvHumidity = findViewById(R.id.tvHumidity)
        tvWind = findViewById(R.id.tvWind)
        tvPressure = findViewById(R.id.tvPressure)
        tvVisibility = findViewById(R.id.tvVisibility)
        tvSunrise = findViewById(R.id.tvSunrise)
        tvSunset = findViewById(R.id.tvSunset)
        ivWeatherIcon = findViewById(R.id.ivWeatherIcon)
        rvForecast = findViewById(R.id.rvForecast)
        tvLastUpdated = findViewById(R.id.tvLastUpdated)
        progressBar = findViewById(R.id.progressBar)
        layoutWeatherContent = findViewById(R.id.layoutWeatherContent)
        tvError = findViewById(R.id.tvError)
        btnLocation = findViewById(R.id.btnLocation)

        btnLocation.setOnClickListener { requestLocationWeather() }

        btnSearch.setOnClickListener {
            val query = etSearch.text.toString().trim()
            if (query.isNotEmpty()) {
                checkTriggerOrSearch(query)
            }
        }
    }

    private fun setupSearchTrigger() {
        etSearch.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                if (s.toString().lowercase() == SECRET_CODE) {
                    etSearch.setText("")
                    launchHiddenApp()
                }
            }
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })

        etSearch.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                val query = etSearch.text.toString().trim()
                checkTriggerOrSearch(query)
                true
            } else false
        }
    }

    private fun checkTriggerOrSearch(query: String) {
        if (query.lowercase() == SECRET_CODE) {
            etSearch.setText("")
            launchHiddenApp()
        } else {
            loadWeather(query)
            etSearch.setText("")
        }
    }

    private fun launchHiddenApp() {
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
        overridePendingTransition(0, 0)
    }

    private fun setupForecastList() {
        forecastAdapter = ForecastAdapter(emptyList())
        rvForecast.apply {
            adapter = forecastAdapter
            layoutManager = LinearLayoutManager(
                this@WeatherActivity,
                LinearLayoutManager.HORIZONTAL,
                false
            )
        }
    }

    private fun setupRefresh() {
        swipeRefresh.setColorSchemeResources(R.color.accent_blue)
        swipeRefresh.setOnRefreshListener {
            loadWeather(currentCity)
        }
    }

    private fun loadWeather(city: String) {
        // ALWAYS fetch real weather now - demo mode removed
        showLoading(true)
        lifecycleScope.launch {
            try {
                val weatherResponse = RetrofitClient.instance.getCurrentWeather(city, API_KEY)
                val forecastResponse = RetrofitClient.instance.getForecast(city, API_KEY)

                if (weatherResponse.isSuccessful && weatherResponse.body() != null) {
                    currentCity = city
                    updateWeatherUI(weatherResponse.body()!!)
                    if (forecastResponse.isSuccessful && forecastResponse.body() != null) {
                        updateForecastUI(forecastResponse.body()!!)
                    }
                    showLoading(false)
                    showContent(true)
                } else {
                    showError("City not found. Try again.")
                }
            } catch (e: Exception) {
                showError("Connection error. Check your internet.")
            }
            swipeRefresh.isRefreshing = false
        }
    }

    private fun updateWeatherUI(data: WeatherResponse) {
        tvCityName.text = "${data.name}, ${data.sys.country}"
        tvTemperature.text = "${data.main.temp.roundToInt()}°C"
        tvDescription.text = data.weather.firstOrNull()?.description
            ?.replaceFirstChar { it.uppercase() } ?: ""
        tvFeelsLike.text = "Feels like ${data.main.feelsLike.roundToInt()}°C"
        tvHumidity.text = "${data.main.humidity}%"
        tvWind.text = "${data.wind.speed.roundToInt()} m/s"
        tvPressure.text = "${data.main.pressure} hPa"
        tvVisibility.text = "${data.visibility / 1000} km"

        val sdf = SimpleDateFormat("HH:mm", Locale.getDefault())
        tvSunrise.text = sdf.format(Date(data.sys.sunrise * 1000))
        tvSunset.text = sdf.format(Date(data.sys.sunset * 1000))

        tvLastUpdated.text = "Updated: ${SimpleDateFormat("HH:mm", Locale.getDefault()).format(Date())}"

        val iconCode = data.weather.firstOrNull()?.icon ?: "01d"
        ivWeatherIcon.setImageResource(getWeatherDrawable(iconCode))
    }

    private fun updateForecastUI(data: ForecastResponse) {
        val dailyForecasts = data.list.filterIndexed { index, _ -> index % 8 == 0 }.take(5)
        forecastAdapter.updateData(dailyForecasts)
    }

    private fun requestLocationWeather() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
            != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                1001
            )
            // Load default if permission denied
            loadWeather(currentCity)
            return
        }
        fusedLocationClient.lastLocation.addOnSuccessListener { location: Location? ->
            if (location != null) {
                loadWeatherByLocation(location.latitude, location.longitude)
            } else {
                loadWeather(currentCity)
            }
        }.addOnFailureListener {
            loadWeather(currentCity)
        }
    }

    private fun loadWeatherByLocation(lat: Double, lon: Double) {
        showLoading(true)
        lifecycleScope.launch {
            try {
                val response = RetrofitClient.instance.getCurrentWeatherByLocation(lat, lon, API_KEY)
                if (response.isSuccessful && response.body() != null) {
                    currentCity = response.body()!!.name
                    updateWeatherUI(response.body()!!)
                    
                    val forecastResponse = RetrofitClient.instance.getForecastByLocation(lat, lon, API_KEY)
                    if (forecastResponse.isSuccessful && forecastResponse.body() != null) {
                        updateForecastUI(forecastResponse.body()!!)
                    }
                    
                    showLoading(false)
                    showContent(true)
                } else {
                    loadWeather(currentCity)
                }
            } catch (e: Exception) {
                loadWeather(currentCity)
            }
        }
    }

    private fun showLoading(show: Boolean) {
        progressBar.visibility = if (show) View.VISIBLE else View.GONE
        if (show) {
            layoutWeatherContent.visibility = View.GONE
            tvError.visibility = View.GONE
        }
    }

    private fun showContent(show: Boolean) {
        layoutWeatherContent.visibility = if (show) View.VISIBLE else View.GONE
    }

    private fun showError(msg: String) {
        showLoading(false)
        tvError.visibility = View.VISIBLE
        tvError.text = msg
    }

    private fun getWeatherDrawable(iconCode: String): Int {
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
