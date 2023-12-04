package bot.multifunction.model.handlers.from_message.location;


import bot.multifunction.model.Users.UserRepo;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.telegram.telegrambots.meta.api.objects.Message;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

import static bot.multifunction.model.handlers.from_message.location.LocationHandler.getJsonElements;

public class GetCity {
    static String getCity(double latitude, double longitude, Message message) {
        try {

            String query = String.format("%s+%s", latitude, longitude);
            query = URLEncoder.encode(query, StandardCharsets.UTF_8);

            JsonArray resultsArray = getJsonElements(query);

            if (!resultsArray.isEmpty()) {
                JsonObject firstResult = resultsArray.get(0).getAsJsonObject();
                JsonObject components = firstResult.getAsJsonObject("components");

                // Вы можете выбрать нужный вам компонент, например, "city"
                String city =  components.get("city").getAsString();
                UserRepo.CITY.put(message.getChatId(),city);
                return city;
            } else {
                return null;
            }

        } catch (IOException e) {
            System.out.println("e = " + e);
            return "Error";
        }
    }
}
