package bot.multifunction.model.handlers;

import bot.multifunction.model.handlers.from_update.CallBackQHandler;
import bot.multifunction.model.handlers.from_update.MessageHandler;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.IOException;

public class UpdateHandler {
    public static void handle(final Update update, final TelegramLongPollingBot bot) throws TelegramApiException, IOException {
        if (update.hasMessage()){
            MessageHandler.handle(update.getMessage(),bot);
        } else if (update.hasCallbackQuery()) {
            CallBackQHandler.handle(update.getCallbackQuery(),bot);
        }
    }
}
