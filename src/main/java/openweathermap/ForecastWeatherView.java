package openweathermap;

import openweathermap.json.forecast.ForecastWeather;

import javax.swing.*;
import java.awt.*;

public class ForecastWeatherView extends JComponent {

    private ForecastWeather forecastWeather;

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.translate(0, getHeight());

        if (forecastWeather != null) {
            for (int i = 1; i <forecastWeather.getList().length; i++) {
                g.drawLine((i-1) * 20,
                        -(int) (forecastWeather.getList()[i-1].getMain().getTemp() * 5),
                        (i*20),
                        -(int) (forecastWeather.getList()[i].getMain().getTemp() * 5));
            }
        }
    }

    public void setForecastWeather(ForecastWeather forecastWeather) {
        this.forecastWeather = forecastWeather;
        repaint();
    }
}
