package openweathermap;

import io.reactivex.rxjava3.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import javax.swing.*;
import java.awt.*;

public class ForecastWeatherFrame extends JFrame {

    private final JTextField location;
    private final ForecastWeatherView view;
    private final Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://api.openweathermap.org/")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .build();
    private final OpenWeatherMapService service = retrofit.create(OpenWeatherMapService.class);

    public ForecastWeatherFrame() {

        setSize(800, 600);
        setTitle("Forecast Weather");
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JPanel northPanel = new JPanel(new BorderLayout());
        location = new JTextField("New York");
        JButton submitButton = new JButton("Submit");
        northPanel.add(location, BorderLayout.CENTER);
        northPanel.add(submitButton, BorderLayout.EAST);
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        view = new ForecastWeatherView();
        panel.add(view, BorderLayout.CENTER);
        panel.add(northPanel, BorderLayout.NORTH);

        setContentPane(panel);

        submitButton.addActionListener(e -> updateWeather());

        updateWeather();
    }

    public void updateWeather() {
        service.getForecast(location.getText())
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.newThread())
                //.observeOn(AndroidSchedulers.mainThread()) // on Android Only
                .subscribe(view::setForecastWeather,
                        Throwable::printStackTrace);
    }
}
