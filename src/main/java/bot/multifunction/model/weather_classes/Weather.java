package bot.multifunction.model.weather_classes;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Weather {
        private int id;
        private String main;
        private String description;
        private String icon;

    }
