package openweathermap;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.plugins.RxJavaPlugins;
import io.reactivex.rxjava3.schedulers.Schedulers;
import openweathermap.json.forecast.ForecastWeather;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ForecastWeatherControllerTest {

    static {
        // This makes it so that our Service returns immediately.
        RxJavaPlugins.setIoSchedulerHandler(scheduler -> Schedulers.trampoline());
        RxJavaPlugins.setNewThreadSchedulerHandler(scheduler -> Schedulers.trampoline());
    }

    @Test
    void updateWeather() {
        // given
        OpenWeatherMapService service = mock();
        ForecastWeatherView view = mock();
        ForecastWeatherController controller = new ForecastWeatherController(view, service);
        ForecastWeather forecastWeather = mock();
        Observable<ForecastWeather> observable = Observable.just(forecastWeather);
        doReturn(observable).when(service).getForecast("New York");

        // when
        controller.updateWeather("New York");

        // then
        verify(service).getForecast("New York");
        verify(view).setForecastWeather(forecastWeather);
    }

}