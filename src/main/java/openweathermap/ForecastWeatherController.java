package openweathermap;

import io.reactivex.rxjava3.schedulers.Schedulers;

import javax.inject.Inject;

public class ForecastWeatherController {

    private ForecastWeatherView view;
    private OpenWeatherMapService service;
    @Inject
    public ForecastWeatherController(
            ForecastWeatherView view,
            OpenWeatherMapService service
    ) {
        this.view = view;
        this.service = service;
    }

    public void updateWeather(String location) {
        service.getForecast(location)
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.newThread())
                //.observeOn(AndroidSchedulers.mainThread()) // on Android Only
                .subscribe(view::setForecastWeather,
                        Throwable::printStackTrace);
    }

}
