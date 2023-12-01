package bot.multifunction.model;

import bot.multifunction.model.handlers.UpdateHandler;
import lombok.SneakyThrows;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;

public class Head extends TelegramLongPollingBot{
    @SneakyThrows
    @Override
    public void onUpdateReceived(Update update) {
        UpdateHandler.handle(update,this);
    }

    public Head(){super(BotConfig.BOT_TOKEN);}
    @Override
    public String getBotUsername() {return BotConfig.BOT_USERNAME;}
}
