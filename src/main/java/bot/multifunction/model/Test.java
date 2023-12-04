package bot.multifunction.model;

import bot.multifunction.model.weather_classes.Weather;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;


public class Test {
    public static void main(String[] args) throws IOException, InterruptedException {

        HttpRequest request = HttpRequest
                .newBuilder()
                .uri(URI.create("https://api.openweathermap.org/data/2.5/weather?q=Tashkent&appid=4f39792662de8d7ad953002ccfe7310d"))
                .GET()
                .build();
        HttpResponse<String> response = HttpClient.newHttpClient()
                .send(request, HttpResponse.BodyHandlers.ofString());
        String body = response.body();

        Gson gson = new GsonBuilder().create();

        Weather weather = gson.fromJson(body, Weather.class);

    }
}


