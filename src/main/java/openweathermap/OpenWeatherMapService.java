package openweathermap;

import io.reactivex.rxjava3.core.Observable;
import openweathermap.json.current.CurrentWeather;
import openweathermap.json.forecast.ForecastWeather;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface OpenWeatherMapService {

    @GET("/data/2.5/weather?appid=c65f21012c4876d2dc360667ec9a4a1b&units=imperial")
    Observable<CurrentWeather> getCurrent(@Query("q") String location);

    @GET("/data/2.5/forecast?appid=c65f21012c4876d2dc360667ec9a4a1b&units=imperial")
    Observable<ForecastWeather> getForecast(@Query("q") String location);

}
