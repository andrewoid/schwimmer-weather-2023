package openweathermap;

import hu.akarnokd.rxjava3.swing.SwingSchedulers;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

import javax.inject.Inject;

public class ForecastWeatherController {

    private ForecastWeatherView view;
    private OpenWeatherMapService service;

    private ApiKey apiKey = new ApiKey();

    @Inject
    public ForecastWeatherController(
            ForecastWeatherView view,
            OpenWeatherMapService service
    ) {
        this.view = view;
        this.service = service;
    }

    public void updateWeather(String location) {
        Disposable disposable = service.getForecast(apiKey.toString(), location)
                .subscribeOn(Schedulers.io())
                .observeOn(SwingSchedulers.edt())
                //.observeOn(AndroidSchedulers.mainThread()) // on Android Only
                .subscribe(view::setForecastWeather,
                        Throwable::printStackTrace);
    }

}
