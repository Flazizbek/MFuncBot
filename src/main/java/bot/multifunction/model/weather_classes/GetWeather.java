package bot.multifunction.model.weather_classes;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

public class GetWeather {
    public static String getWeather(String city) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest
                .newBuilder()
                .uri(URI.create("https://api.openweathermap.org/data/2.5/weather?q=Tashkent&appid=4f39792662de8d7ad953002ccfe7310d"))
                .GET()
                .build();
        HttpResponse<String> response = HttpClient.newHttpClient()
                .send(request, HttpResponse.BodyHandlers.ofString());
        String body = response.body();

        Gson gson = new GsonBuilder().create();

        WeatherData weatherData = gson.fromJson(body, WeatherData.class);

        Main main = weatherData.getMain();
        double temp = main.getTemp()-273;
        double feelsLike = main.getFeels_like()-273;
        int humidity = main.getHumidity();
        return "Temperature :"+(int)temp+"C\nFeels like :"+(int)feelsLike+"C\nHumidity :"+humidity+"%";
    }
}
