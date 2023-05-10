package openweathermap;

import io.reactivex.rxjava3.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import javax.inject.Inject;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ForecastWeatherFrame extends JFrame {

    private final JTextField location;
    private final ForecastWeatherView view;

    private ForecastWeatherController controller;

    @Inject
    public ForecastWeatherFrame(ForecastWeatherView view, ForecastWeatherController controller) {

        this.view = view;
        this.controller = controller;

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
        panel.add(view, BorderLayout.CENTER);
        panel.add(northPanel, BorderLayout.NORTH);

        setContentPane(panel);

        submitButton.addActionListener(e -> controller.updateWeather(location.getText()));

        controller.updateWeather(location.getText());
    }

}
