package openweathermap;

import openweathermap.dagger.DaggerForecastWeatherComponent;
import openweathermap.dagger.ForecastWeatherComponent;

public class Main {

    public static void main(String[] args) {
        ForecastWeatherComponent component = DaggerForecastWeatherComponent
                .builder()
                .build();
        ForecastWeatherFrame frame = component.providesForecastWeatherFrame();
        frame.setVisible(true);
    }

}
