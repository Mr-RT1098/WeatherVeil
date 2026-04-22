package com.weatherveil.weather;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\f\u0012\b\u0012\u00060\u0002R\u00020\u00000\u0001:\u0001\u0016B\u0013\u0012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004\u00a2\u0006\u0002\u0010\u0006J\b\u0010\u0007\u001a\u00020\bH\u0016J\u0010\u0010\t\u001a\u00020\b2\u0006\u0010\n\u001a\u00020\u000bH\u0002J\u001c\u0010\f\u001a\u00020\r2\n\u0010\u000e\u001a\u00060\u0002R\u00020\u00002\u0006\u0010\u000f\u001a\u00020\bH\u0016J\u001c\u0010\u0010\u001a\u00060\u0002R\u00020\u00002\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\bH\u0016J\u0014\u0010\u0014\u001a\u00020\r2\f\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004R\u0014\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0017"}, d2 = {"Lcom/weatherveil/weather/ForecastAdapter;", "Landroidx/recyclerview/widget/RecyclerView$Adapter;", "Lcom/weatherveil/weather/ForecastAdapter$ForecastViewHolder;", "items", "", "Lcom/weatherveil/weather/ForecastItem;", "(Ljava/util/List;)V", "getItemCount", "", "getWeatherIcon", "iconCode", "", "onBindViewHolder", "", "holder", "position", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "viewType", "updateData", "newItems", "ForecastViewHolder", "app_release"})
public final class ForecastAdapter extends androidx.recyclerview.widget.RecyclerView.Adapter<com.weatherveil.weather.ForecastAdapter.ForecastViewHolder> {
    @org.jetbrains.annotations.NotNull()
    private java.util.List<com.weatherveil.weather.ForecastItem> items;
    
    public ForecastAdapter(@org.jetbrains.annotations.NotNull()
    java.util.List<com.weatherveil.weather.ForecastItem> items) {
        super();
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public com.weatherveil.weather.ForecastAdapter.ForecastViewHolder onCreateViewHolder(@org.jetbrains.annotations.NotNull()
    android.view.ViewGroup parent, int viewType) {
        return null;
    }
    
    @java.lang.Override()
    public void onBindViewHolder(@org.jetbrains.annotations.NotNull()
    com.weatherveil.weather.ForecastAdapter.ForecastViewHolder holder, int position) {
    }
    
    @java.lang.Override()
    public int getItemCount() {
        return 0;
    }
    
    public final void updateData(@org.jetbrains.annotations.NotNull()
    java.util.List<com.weatherveil.weather.ForecastItem> newItems) {
    }
    
    private final int getWeatherIcon(java.lang.String iconCode) {
        return 0;
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0086\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004R\u0011\u0010\u0005\u001a\u00020\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\t\u001a\u00020\n\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\r\u001a\u00020\n\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\fR\u0011\u0010\u000f\u001a\u00020\n\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\f\u00a8\u0006\u0011"}, d2 = {"Lcom/weatherveil/weather/ForecastAdapter$ForecastViewHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "view", "Landroid/view/View;", "(Lcom/weatherveil/weather/ForecastAdapter;Landroid/view/View;)V", "ivIcon", "Landroid/widget/ImageView;", "getIvIcon", "()Landroid/widget/ImageView;", "tvDay", "Landroid/widget/TextView;", "getTvDay", "()Landroid/widget/TextView;", "tvDesc", "getTvDesc", "tvTemp", "getTvTemp", "app_release"})
    public final class ForecastViewHolder extends androidx.recyclerview.widget.RecyclerView.ViewHolder {
        @org.jetbrains.annotations.NotNull()
        private final android.widget.TextView tvDay = null;
        @org.jetbrains.annotations.NotNull()
        private final android.widget.TextView tvTemp = null;
        @org.jetbrains.annotations.NotNull()
        private final android.widget.ImageView ivIcon = null;
        @org.jetbrains.annotations.NotNull()
        private final android.widget.TextView tvDesc = null;
        
        public ForecastViewHolder(@org.jetbrains.annotations.NotNull()
        android.view.View view) {
            super(null);
        }
        
        @org.jetbrains.annotations.NotNull()
        public final android.widget.TextView getTvDay() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final android.widget.TextView getTvTemp() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final android.widget.ImageView getIvIcon() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final android.widget.TextView getTvDesc() {
            return null;
        }
    }
}