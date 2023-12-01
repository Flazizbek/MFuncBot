package bot.multifunction.service;

import bot.multifunction.model.Head;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

/**
 * Main method!
 *
 */
public class Main
{
    public static void main( String[] args ) throws TelegramApiException {
        Head bot = new Head();
        TelegramBotsApi api = new TelegramBotsApi(DefaultBotSession.class);
            api.registerBot(bot);
    }
}
/*
* TODO edit commands start and help
* */