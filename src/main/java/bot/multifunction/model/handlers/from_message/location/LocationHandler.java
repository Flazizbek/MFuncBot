package bot.multifunction.model.handlers.from_message.location;
import bot.multifunction.model.Users.UserRepo;
import bot.multifunction.model.weather_classes.GetWeather;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import lombok.NonNull;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Location;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class LocationHandler {
    static String apiKey = "7e9c0f7356cd4d3da03d282bde341a07";
    static String apiUrl = "https://api.opencagedata.com/geocode/v1/json";

    public static void handle(Message message, TelegramLongPollingBot bot) throws TelegramApiException, IOException, InterruptedException {
        Location location = message.getLocation();

        @NonNull Double lat = location.getLatitude();
        @NonNull Double lon = location.getLongitude();

        GetCity.getCity(lat, lon,message);

        String weather = GetWeather.getWeather(UserRepo.CITY.get(message.getChatId()));

        bot.execute(new SendMessage(message.getChatId().toString(),weather));
    }

    static JsonArray getJsonElements(String query) throws IOException {
        URL url = new URL(apiUrl + "?q=" + query + "&key=" + apiKey);

        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        StringBuilder response = new StringBuilder();
        String line;

        while ((line = reader.readLine()) != null) {
            response.append(line);
        }

        reader.close();
        connection.disconnect();

        // Парсинг ответа и извлечение названия города
        JsonParser jsonParser = new JsonParser();
        JsonObject jsonResponse = jsonParser.parse(response.toString()).getAsJsonObject();

        return jsonResponse.getAsJsonArray("results");
    }


}
