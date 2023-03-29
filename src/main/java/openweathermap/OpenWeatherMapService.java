package openweathermap;

import io.reactivex.rxjava3.core.Observable;
import openweathermap.json.CurrentWeather;
import retrofit2.http.GET;

public interface OpenWeatherMapService {

    @GET("/data/2.5/weather?q=New%20York&appid=c65f21012c4876d2dc360667ec9a4a1b&units=imperial")
    Observable<CurrentWeather> getCurrentWeather();

}
