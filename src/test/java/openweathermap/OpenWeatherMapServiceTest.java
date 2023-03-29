package openweathermap;

import openweathermap.json.CurrentWeather;
import org.junit.jupiter.api.Test;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import static org.junit.jupiter.api.Assertions.*;

public class OpenWeatherMapServiceTest {

    @Test
    public void getCurrentWeather() {
        // given
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.openweathermap.org/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .build();
        OpenWeatherMapService service = retrofit.create(OpenWeatherMapService.class);

        // when
        CurrentWeather weather = service.getCurrentWeather().blockingFirst();

        // then
        assertNotNull(weather);
        assertNotNull(weather.getWeather().get(0).getDescription());
        // this will fail if it gets cold
        assertTrue(weather.getMain().getTemp() > 0);
    }

}