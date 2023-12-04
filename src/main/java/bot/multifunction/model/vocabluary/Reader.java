package bot.multifunction.model.vocabluary;

import bot.multifunction.model.Users.UserRepo;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Reader {

    static Random random= new Random();
        String data = Files.readString(Path.of("src/main/resources/words.txt"));
        Type type = new TypeToken<List<Words>>() {}.getType();
        public final List<Words> words = new GsonBuilder().create().fromJson(data, type);
    public Reader() throws IOException {
    }

    public String giveWord(Message message, TelegramLongPollingBot bot) {
        int i = random.nextInt(words.size() + 1);
        return words.get(i).getEnglish() + " -> " + words.get(i).getRussian();
    }

    public void giveWordRegular(Message message, TelegramLongPollingBot bot) throws TelegramApiException {
        if(UserRepo.VOCAB_SWITCH_INFO.get(message.getChatId()) == VocabSwitchInfo.ON){
            int i = random.nextInt(words.size() + 1);
            ScheduledExecutorService executor;
            executor = Executors.newScheduledThreadPool(4);
            executor.scheduleAtFixedRate(() -> {
                try {
                    bot.execute(new SendMessage(message.getChatId().toString(),words.get(i).getEnglish() + " -> " + words.get(i).getRussian() ));
                } catch (TelegramApiException e) {
                    throw new RuntimeException(e);
                }
            }, 0, 3600 * 24, TimeUnit.SECONDS);
            bot.execute(new SendMessage(message.getChatId().toString(),"New words will sent every day on current time"));
        }

    }
}
