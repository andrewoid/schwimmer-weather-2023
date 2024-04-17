package openweathermap;

import hu.akarnokd.rxjava3.swing.RxSwingPlugins;
import hu.akarnokd.rxjava3.swing.SwingSchedulers;
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
        RxSwingPlugins.setOnEdtScheduler(scheduler -> Schedulers.trampoline());
    }

    @Test
    void updateWeather() {
        // given
        OpenWeatherMapService service = mock();
        ForecastWeatherView view = mock();
        ForecastWeatherController controller = new ForecastWeatherController(view, service);
        ForecastWeather forecastWeather = mock();
        Observable<ForecastWeather> observable = Observable.just(forecastWeather);
        doReturn(observable).when(service).getForecast(anyString(),"New York");

        // when
        controller.updateWeather("New York");

        // then
        verify(service).getForecast(anyString(), "New York");
        verify(view).setForecastWeather(forecastWeather);
    }

}