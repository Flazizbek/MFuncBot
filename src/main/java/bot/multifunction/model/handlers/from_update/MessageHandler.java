package bot.multifunction.model.handlers.from_update;

import bot.multifunction.model.handlers.from_message.*;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class MessageHandler {
    public static void handle(Message message, TelegramLongPollingBot bot) throws TelegramApiException {
        if(message.isCommand()) {
            CommandHandler.handle(message,bot);
        }
        else if(message.hasText()){
            TextHandler.handle(message, bot);
        }
        else if(message.hasVideo()){
            VideoHandler.handle(message,bot);
        }
        else if(message.hasAudio()){
            AudioHandler.handle(message,bot);
        }
        else if(message.hasAnimation()){
            AnimationHandler.handle(message,bot);
        }
        else if(message.hasDocument()){
            DocumentHandler.handle(message,bot);
        }
        else if(message.hasContact()){
            ContactHandler.handle(message,bot);
        }
        else if(message.hasDice()){
            DiceHandler.handle(message,bot);
        }
    }
}
