package bot.multifunction.model.handlers.from_message;

import bot.multifunction.model.Users.UserRepo;
import bot.multifunction.model.enums.CommandEnum;
import bot.multifunction.model.button.ButtonUtil;
import bot.multifunction.model.enums.steps.Steps;
import bot.multifunction.model.vocabluary.Reader;
import lombok.SneakyThrows;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.IOException;

public class CommandHandler {
    @SneakyThrows
    public static void handle(Message message, TelegramLongPollingBot bot) {
        switch (CommandEnum.of(message.getText())) {
            case START -> handleCommandStart(message, bot);

            case HELP -> handleCommandHelp(message, bot);

            case ADD_REMAINDER -> handleCommandAddReminder(message, bot);

            case VOCAB -> handleCommandSwitchVocab(message, bot);

            case GET_RANDOM_WORD -> handleCommandGetRandomWord(message, bot);

            case SET_LOCATION -> handleSetLocation(message, bot);

        }
    }

    private static void handleSetLocation(Message message, TelegramLongPollingBot bot) throws TelegramApiException {
        bot.execute(new SendMessage(message.getChatId().toString(),"Send Location go get exact Weather news "));
        UserRepo.USER_STEP.put(message.getChatId(), Steps.SET_LOCATION);
    }

    @SneakyThrows
    public static void handleCommandStart(Message message, TelegramLongPollingBot bot) {

        bot.execute(new SendMessage(message.getChatId().toString(), "Welcome "+
                message.getFrom().getUserName()));
        bot.execute(new SendMessage(message.getChatId().toString(),
                "Send /help to get information about this bot. "));
    }
    @SneakyThrows
    public static void handleCommandHelp(Message message, TelegramLongPollingBot bot) {
        bot.execute(new SendMessage(message.getChatId().toString(),
                """
         What can do this bot?
         This bot can remind you of everything at a certain time that you entered." +
         It will also help you learn foreign languages with notifications and giv everyday advices."""));
    }


    public static void handleCommandAddReminder(Message message, TelegramLongPollingBot bot) throws TelegramApiException {
        SendMessage sendMessage = new SendMessage(message.getChatId().toString(), "Select mode: ");
        sendMessage.setReplyMarkup(ButtonUtil.ADD_REMAINDER);
        bot.execute(sendMessage);
    }


    public static void handleCommandSwitchVocab(Message message, TelegramLongPollingBot bot) throws TelegramApiException {
        SendMessage sendMessage = new SendMessage(message.getChatId().toString(), "On/of?");
        sendMessage.setReplyMarkup(ButtonUtil.ON_OFF_MARKUP);
        bot.execute(sendMessage);
    }


    public static void handleCommandGetRandomWord(Message message, TelegramLongPollingBot bot) throws IOException, TelegramApiException {
        bot.execute(new SendMessage(message.getChatId().toString(), new Reader().giveWord(message,bot)));
    }


}
