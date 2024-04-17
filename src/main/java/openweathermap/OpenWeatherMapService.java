package openweathermap;

import io.reactivex.rxjava3.core.Observable;
import openweathermap.json.current.CurrentWeather;
import openweathermap.json.forecast.ForecastWeather;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface OpenWeatherMapService {

    @GET("/data/2.5/weather?units=imperial")
    Observable<CurrentWeather> getCurrent(@Query("appid") String appid, @Query("q") String location);

    @GET("/data/2.5/forecast?units=imperial")
    Observable<ForecastWeather> getForecast(@Query("appid") String appid, @Query("q") String location);

}
