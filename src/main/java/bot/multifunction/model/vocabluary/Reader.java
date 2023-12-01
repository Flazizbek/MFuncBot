package bot.multifunction.model.vocabluary;

import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Random;

public class Reader {

    static Random random= new Random();
        String data = Files.readString(Path.of("src/main/resources/words.txt"));
        Type type = new TypeToken<List<Words>>() {
        }.getType();
        public final List<Words> words = new GsonBuilder().create().fromJson(data, type);

    public Reader() throws IOException {
    }
    public String giveWord(){
        int i = random.nextInt(words.size()+1);
        return (words.get(i).getEnglish()+" -> "+words.get(i).getRussian());

    }
}
