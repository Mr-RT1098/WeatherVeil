package com.weatherveil.weather;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0012\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B3\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007\u0012\u0006\u0010\t\u001a\u00020\n\u0012\u0006\u0010\u000b\u001a\u00020\f\u00a2\u0006\u0002\u0010\rJ\t\u0010\u0018\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u0019\u001a\u00020\u0005H\u00c6\u0003J\u000f\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\b0\u0007H\u00c6\u0003J\t\u0010\u001b\u001a\u00020\nH\u00c6\u0003J\t\u0010\u001c\u001a\u00020\fH\u00c6\u0003JA\u0010\u001d\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\u000e\b\u0002\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u00072\b\b\u0002\u0010\t\u001a\u00020\n2\b\b\u0002\u0010\u000b\u001a\u00020\fH\u00c6\u0001J\u0013\u0010\u001e\u001a\u00020\u001f2\b\u0010 \u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010!\u001a\u00020\"H\u00d6\u0001J\t\u0010#\u001a\u00020\fH\u00d6\u0001R\u0016\u0010\u000b\u001a\u00020\f8\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0017\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u0011\u0010\t\u001a\u00020\n\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017\u00a8\u0006$"}, d2 = {"Lcom/weatherveil/weather/ForecastItem;", "", "dt", "", "main", "Lcom/weatherveil/weather/MainWeather;", "weather", "", "Lcom/weatherveil/weather/WeatherDescription;", "wind", "Lcom/weatherveil/weather/Wind;", "dateText", "", "(JLcom/weatherveil/weather/MainWeather;Ljava/util/List;Lcom/weatherveil/weather/Wind;Ljava/lang/String;)V", "getDateText", "()Ljava/lang/String;", "getDt", "()J", "getMain", "()Lcom/weatherveil/weather/MainWeather;", "getWeather", "()Ljava/util/List;", "getWind", "()Lcom/weatherveil/weather/Wind;", "component1", "component2", "component3", "component4", "component5", "copy", "equals", "", "other", "hashCode", "", "toString", "app_release"})
public final class ForecastItem {
    private final long dt = 0L;
    @org.jetbrains.annotations.NotNull()
    private final com.weatherveil.weather.MainWeather main = null;
    @org.jetbrains.annotations.NotNull()
    private final java.util.List<com.weatherveil.weather.WeatherDescription> weather = null;
    @org.jetbrains.annotations.NotNull()
    private final com.weatherveil.weather.Wind wind = null;
    @com.google.gson.annotations.SerializedName(value = "dt_txt")
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String dateText = null;
    
    public ForecastItem(long dt, @org.jetbrains.annotations.NotNull()
    com.weatherveil.weather.MainWeather main, @org.jetbrains.annotations.NotNull()
    java.util.List<com.weatherveil.weather.WeatherDescription> weather, @org.jetbrains.annotations.NotNull()
    com.weatherveil.weather.Wind wind, @org.jetbrains.annotations.NotNull()
    java.lang.String dateText) {
        super();
    }
    
    public final long getDt() {
        return 0L;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.weatherveil.weather.MainWeather getMain() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.weatherveil.weather.WeatherDescription> getWeather() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.weatherveil.weather.Wind getWind() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getDateText() {
        return null;
    }
    
    public final long component1() {
        return 0L;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.weatherveil.weather.MainWeather component2() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.weatherveil.weather.WeatherDescription> component3() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.weatherveil.weather.Wind component4() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component5() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.weatherveil.weather.ForecastItem copy(long dt, @org.jetbrains.annotations.NotNull()
    com.weatherveil.weather.MainWeather main, @org.jetbrains.annotations.NotNull()
    java.util.List<com.weatherveil.weather.WeatherDescription> weather, @org.jetbrains.annotations.NotNull()
    com.weatherveil.weather.Wind wind, @org.jetbrains.annotations.NotNull()
    java.lang.String dateText) {
        return null;
    }
    
    @java.lang.Override()
    public boolean equals(@org.jetbrains.annotations.Nullable()
    java.lang.Object other) {
        return false;
    }
    
    @java.lang.Override()
    public int hashCode() {
        return 0;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public java.lang.String toString() {
        return null;
    }
}