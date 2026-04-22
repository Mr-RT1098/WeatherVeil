package com.weatherveil.weather;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\t\n\u0002\b\u0018\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0086\b\u0018\u00002\u00020\u0001BC\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007\u0012\u0006\u0010\t\u001a\u00020\n\u0012\u0006\u0010\u000b\u001a\u00020\f\u0012\u0006\u0010\r\u001a\u00020\u000e\u0012\u0006\u0010\u000f\u001a\u00020\u0010\u00a2\u0006\u0002\u0010\u0011J\t\u0010 \u001a\u00020\u0003H\u00c6\u0003J\t\u0010!\u001a\u00020\u0005H\u00c6\u0003J\u000f\u0010\"\u001a\b\u0012\u0004\u0012\u00020\b0\u0007H\u00c6\u0003J\t\u0010#\u001a\u00020\nH\u00c6\u0003J\t\u0010$\u001a\u00020\fH\u00c6\u0003J\t\u0010%\u001a\u00020\u000eH\u00c6\u0003J\t\u0010&\u001a\u00020\u0010H\u00c6\u0003JU\u0010\'\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\u000e\b\u0002\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u00072\b\b\u0002\u0010\t\u001a\u00020\n2\b\b\u0002\u0010\u000b\u001a\u00020\f2\b\b\u0002\u0010\r\u001a\u00020\u000e2\b\b\u0002\u0010\u000f\u001a\u00020\u0010H\u00c6\u0001J\u0013\u0010(\u001a\u00020)2\b\u0010*\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010+\u001a\u00020\u000eH\u00d6\u0001J\t\u0010,\u001a\u00020\u0003H\u00d6\u0001R\u0011\u0010\u000f\u001a\u00020\u0010\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\u0011\u0010\u000b\u001a\u00020\f\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019R\u0011\u0010\r\u001a\u00020\u000e\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u001bR\u0017\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u001dR\u0011\u0010\t\u001a\u00020\n\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u001f\u00a8\u0006-"}, d2 = {"Lcom/weatherveil/weather/WeatherResponse;", "", "name", "", "main", "Lcom/weatherveil/weather/MainWeather;", "weather", "", "Lcom/weatherveil/weather/WeatherDescription;", "wind", "Lcom/weatherveil/weather/Wind;", "sys", "Lcom/weatherveil/weather/Sys;", "visibility", "", "dt", "", "(Ljava/lang/String;Lcom/weatherveil/weather/MainWeather;Ljava/util/List;Lcom/weatherveil/weather/Wind;Lcom/weatherveil/weather/Sys;IJ)V", "getDt", "()J", "getMain", "()Lcom/weatherveil/weather/MainWeather;", "getName", "()Ljava/lang/String;", "getSys", "()Lcom/weatherveil/weather/Sys;", "getVisibility", "()I", "getWeather", "()Ljava/util/List;", "getWind", "()Lcom/weatherveil/weather/Wind;", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "copy", "equals", "", "other", "hashCode", "toString", "app_debug"})
public final class WeatherResponse {
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String name = null;
    @org.jetbrains.annotations.NotNull()
    private final com.weatherveil.weather.MainWeather main = null;
    @org.jetbrains.annotations.NotNull()
    private final java.util.List<com.weatherveil.weather.WeatherDescription> weather = null;
    @org.jetbrains.annotations.NotNull()
    private final com.weatherveil.weather.Wind wind = null;
    @org.jetbrains.annotations.NotNull()
    private final com.weatherveil.weather.Sys sys = null;
    private final int visibility = 0;
    private final long dt = 0L;
    
    public WeatherResponse(@org.jetbrains.annotations.NotNull()
    java.lang.String name, @org.jetbrains.annotations.NotNull()
    com.weatherveil.weather.MainWeather main, @org.jetbrains.annotations.NotNull()
    java.util.List<com.weatherveil.weather.WeatherDescription> weather, @org.jetbrains.annotations.NotNull()
    com.weatherveil.weather.Wind wind, @org.jetbrains.annotations.NotNull()
    com.weatherveil.weather.Sys sys, int visibility, long dt) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getName() {
        return null;
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
    public final com.weatherveil.weather.Sys getSys() {
        return null;
    }
    
    public final int getVisibility() {
        return 0;
    }
    
    public final long getDt() {
        return 0L;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component1() {
        return null;
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
    public final com.weatherveil.weather.Sys component5() {
        return null;
    }
    
    public final int component6() {
        return 0;
    }
    
    public final long component7() {
        return 0L;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.weatherveil.weather.WeatherResponse copy(@org.jetbrains.annotations.NotNull()
    java.lang.String name, @org.jetbrains.annotations.NotNull()
    com.weatherveil.weather.MainWeather main, @org.jetbrains.annotations.NotNull()
    java.util.List<com.weatherveil.weather.WeatherDescription> weather, @org.jetbrains.annotations.NotNull()
    com.weatherveil.weather.Wind wind, @org.jetbrains.annotations.NotNull()
    com.weatherveil.weather.Sys sys, int visibility, long dt) {
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