package openweathermap;

import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import openweathermap.json.current.CurrentWeather;

import javax.inject.Inject;
import javax.inject.Named;
import javax.swing.*;
import java.awt.*;
import java.net.MalformedURLException;
import java.net.URL;

public class CurrentWeatherController {

    private OpenWeatherMapService service;
    private JLabel imageLabel;
    private JLabel degreesLabel;

    private ApiKey apiKey = new ApiKey();

    @Inject
    public CurrentWeatherController(
            OpenWeatherMapService service,
            @Named("imageLabel") JLabel imageLabel,
            @Named("degreesLabel") JLabel degreesLabel) {
        this.service = service;
        this.imageLabel = imageLabel;
        this.degreesLabel = degreesLabel;
    }

    public void updateWeather(String location) {
        Disposable disposable = service.getCurrent(apiKey.toString(), location)
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.newThread())
                //.observeOn(AndroidSchedulers.mainThread()) // on Android Only
                .subscribe(this::setCurrentWeather,
                        Throwable::printStackTrace);
    }

    public void setCurrentWeather(CurrentWeather currentWeather) throws MalformedURLException {
        degreesLabel.setText(String.valueOf(currentWeather.getMain().getTemp()));
        String icon = currentWeather.getWeather().get(0).getIcon();
        String url = "http://openweathermap.org/img/w/" + icon + ".png";
        imageLabel.setIcon(new ImageIcon(new URL(url)));
    }


}
