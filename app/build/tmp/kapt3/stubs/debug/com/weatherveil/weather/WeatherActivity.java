package com.weatherveil.weather;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0088\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0010\u0006\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0010\u0010\'\u001a\u00020(2\u0006\u0010)\u001a\u00020\u0004H\u0002J\u0010\u0010*\u001a\u00020+2\u0006\u0010,\u001a\u00020\u0004H\u0002J\b\u0010-\u001a\u00020(H\u0002J\b\u0010.\u001a\u00020(H\u0002J\u0010\u0010/\u001a\u00020(2\u0006\u00100\u001a\u00020\u0004H\u0002J\u0018\u00101\u001a\u00020(2\u0006\u00102\u001a\u0002032\u0006\u00104\u001a\u000203H\u0002J\u0012\u00105\u001a\u00020(2\b\u00106\u001a\u0004\u0018\u000107H\u0014J\b\u00108\u001a\u00020(H\u0002J\b\u00109\u001a\u00020(H\u0002J\b\u0010:\u001a\u00020(H\u0002J\b\u0010;\u001a\u00020(H\u0002J\u0010\u0010<\u001a\u00020(2\u0006\u0010=\u001a\u00020>H\u0002J\u0010\u0010?\u001a\u00020(2\u0006\u0010@\u001a\u00020\u0004H\u0002J\u0010\u0010A\u001a\u00020(2\u0006\u0010=\u001a\u00020>H\u0002J\u0010\u0010B\u001a\u00020(2\u0006\u0010C\u001a\u00020DH\u0002J\u0010\u0010E\u001a\u00020(2\u0006\u0010C\u001a\u00020FH\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082D\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0007X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0011X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0013X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0015X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0017X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\u0019X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u001a\u001a\u00020\u001bX\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u001c\u001a\u00020\u001bX\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u001d\u001a\u00020\u001bX\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u001e\u001a\u00020\u001bX\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u001f\u001a\u00020\u001bX\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010 \u001a\u00020\u001bX\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010!\u001a\u00020\u001bX\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\"\u001a\u00020\u001bX\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010#\u001a\u00020\u001bX\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010$\u001a\u00020\u001bX\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010%\u001a\u00020\u001bX\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010&\u001a\u00020\u001bX\u0082.\u00a2\u0006\u0002\n\u0000\u00a8\u0006G"}, d2 = {"Lcom/weatherveil/weather/WeatherActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "()V", "API_KEY", "", "SECRET_CODE", "btnLocation", "Landroid/widget/ImageButton;", "btnSearch", "currentCity", "etSearch", "Landroid/widget/EditText;", "forecastAdapter", "Lcom/weatherveil/weather/ForecastAdapter;", "fusedLocationClient", "Lcom/google/android/gms/location/FusedLocationProviderClient;", "ivWeatherIcon", "Landroid/widget/ImageView;", "layoutWeatherContent", "Landroid/view/View;", "progressBar", "Landroid/widget/ProgressBar;", "rvForecast", "Landroidx/recyclerview/widget/RecyclerView;", "swipeRefresh", "Landroidx/swiperefreshlayout/widget/SwipeRefreshLayout;", "tvCityName", "Landroid/widget/TextView;", "tvDescription", "tvError", "tvFeelsLike", "tvHumidity", "tvLastUpdated", "tvPressure", "tvSunrise", "tvSunset", "tvTemperature", "tvVisibility", "tvWind", "checkTriggerOrSearch", "", "query", "getWeatherDrawable", "", "iconCode", "initViews", "launchHiddenApp", "loadWeather", "city", "loadWeatherByLocation", "lat", "", "lon", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "requestLocationWeather", "setupForecastList", "setupRefresh", "setupSearchTrigger", "showContent", "show", "", "showError", "msg", "showLoading", "updateForecastUI", "data", "Lcom/weatherveil/weather/ForecastResponse;", "updateWeatherUI", "Lcom/weatherveil/weather/WeatherResponse;", "app_debug"})
public final class WeatherActivity extends androidx.appcompat.app.AppCompatActivity {
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String SECRET_CODE = "ghost";
    private com.google.android.gms.location.FusedLocationProviderClient fusedLocationClient;
    private com.weatherveil.weather.ForecastAdapter forecastAdapter;
    private android.widget.EditText etSearch;
    private android.widget.ImageButton btnSearch;
    private androidx.swiperefreshlayout.widget.SwipeRefreshLayout swipeRefresh;
    private android.widget.TextView tvCityName;
    private android.widget.TextView tvTemperature;
    private android.widget.TextView tvDescription;
    private android.widget.TextView tvFeelsLike;
    private android.widget.TextView tvHumidity;
    private android.widget.TextView tvWind;
    private android.widget.TextView tvPressure;
    private android.widget.TextView tvVisibility;
    private android.widget.TextView tvSunrise;
    private android.widget.TextView tvSunset;
    private android.widget.ImageView ivWeatherIcon;
    private androidx.recyclerview.widget.RecyclerView rvForecast;
    private android.widget.TextView tvLastUpdated;
    private android.widget.ProgressBar progressBar;
    private android.view.View layoutWeatherContent;
    private android.widget.TextView tvError;
    private android.widget.ImageButton btnLocation;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String API_KEY = "f4a9263d492d377bdf31c0a873bf06c6";
    @org.jetbrains.annotations.NotNull()
    private java.lang.String currentCity = "London";
    
    public WeatherActivity() {
        super();
    }
    
    @java.lang.Override()
    protected void onCreate(@org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    private final void initViews() {
    }
    
    private final void setupSearchTrigger() {
    }
    
    private final void checkTriggerOrSearch(java.lang.String query) {
    }
    
    private final void launchHiddenApp() {
    }
    
    private final void setupForecastList() {
    }
    
    private final void setupRefresh() {
    }
    
    private final void loadWeather(java.lang.String city) {
    }
    
    private final void updateWeatherUI(com.weatherveil.weather.WeatherResponse data) {
    }
    
    private final void updateForecastUI(com.weatherveil.weather.ForecastResponse data) {
    }
    
    private final void requestLocationWeather() {
    }
    
    private final void loadWeatherByLocation(double lat, double lon) {
    }
    
    private final void showLoading(boolean show) {
    }
    
    private final void showContent(boolean show) {
    }
    
    private final void showError(java.lang.String msg) {
    }
    
    private final int getWeatherDrawable(java.lang.String iconCode) {
        return 0;
    }
}