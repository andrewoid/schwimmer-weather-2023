package openweathermap.dagger;

import dagger.Component;
import openweathermap.ForecastWeatherFrame;

import javax.inject.Singleton;

@Singleton
@Component(modules = {OpenWeatherMapServiceModule.class})
public
interface ForecastWeatherComponent {

    ForecastWeatherFrame providesForecastWeatherFrame();

}
