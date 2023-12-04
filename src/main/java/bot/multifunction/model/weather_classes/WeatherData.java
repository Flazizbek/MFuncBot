
package bot.multifunction.model.weather_classes;

import lombok.Data;

import java.util.List;

@Data
public  class WeatherData {
    private Coord coord;
    private List<Weather> weather;
    private String base;
    private Main main;
    private int visibility;
    private Wind wind;
    private Clouds clouds;
    private int dt;
    private Sys sys;
    private int timezone;
    private int id;
    private String name;
    private int cod;


}
@Data
class Coord {
    private double lon;
    private double lat;

    // Getters and setters
}
@Data
class Main {
    private double temp;
    private double feels_like;
    private double temp_min;
    private double temp_max;
    private int pressure;
    private int humidity;

    // Getters and setters
}
@Data
class Wind {
    private double speed;
    private int deg;

    // Getters and setters
}
@Data
class Clouds {
    private int all;

    // Getters and setters
}
@Data
class Sys {
    private int type;
    private int id;
    private String country;
    private long sunrise;
    private long sunset;

    // Getters and setters
}

