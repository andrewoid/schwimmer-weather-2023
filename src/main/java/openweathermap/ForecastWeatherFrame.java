package openweathermap;

import io.reactivex.rxjava3.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import javax.inject.Inject;
import javax.inject.Named;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ForecastWeatherFrame extends JFrame {

    private final JPanel panel;
    private JTextField location;
    private final ForecastWeatherView view;

    private final ForecastWeatherController controller;
    private final CurrentWeatherController currentWeatherController;
    private final JLabel imageLabel;
    private final JLabel degreesLabel;

    @Inject
    public ForecastWeatherFrame(ForecastWeatherView view,
                                ForecastWeatherController controller,
                                CurrentWeatherController currentWeatherController,
                                @Named("imageLabel") JLabel imageLabel,
                                @Named("degreesLabel") JLabel degreesLabel) {

        this.view = view;
        this.controller = controller;
        this.currentWeatherController = currentWeatherController;
        this.imageLabel = imageLabel;
        this.degreesLabel = degreesLabel;

        setSize(800, 600);
        setTitle("Forecast Weather");
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        panel = new JPanel(new GridBagLayout());

        addLocationField();
        addSubmitButton();
        addImageLabel();
        addDegreesLabel();
        addForecastWeatherView();

        setContentPane(panel);

        controller.updateWeather(location.getText());
        currentWeatherController.updateWeather(location.getText());
    }

    private void addForecastWeatherView() {
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = 3;
        constraints.gridwidth = 4;
        constraints.weightx = 1.0;
        constraints.weighty = 1.0;
        constraints.fill = GridBagConstraints.BOTH;
        panel.add(view, constraints);
    }

    private void addDegreesLabel() {
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = 1;
        constraints.gridy = 1;
        panel.add(degreesLabel,constraints);
    }

    private void addImageLabel() {
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = 1;
        panel.add(imageLabel, constraints);
    }

    private void addSubmitButton() {
        JButton submitButton = new JButton("Submit");
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = 3;
        constraints.gridy = 0;
        panel.add(submitButton, constraints);
        submitButton.addActionListener(e -> {
            controller.updateWeather(location.getText());
            currentWeatherController.updateWeather(location.getText());
        });
    }

    private void addLocationField() {
        location = new JTextField("New York");
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridwidth = 4;
        panel.add(location, constraints);
    }

}
