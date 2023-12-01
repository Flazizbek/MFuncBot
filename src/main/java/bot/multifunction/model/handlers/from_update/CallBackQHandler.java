package bot.multifunction.model.handlers.from_update;


import bot.multifunction.model.TimeManager;
import bot.multifunction.model.Users.UserRepo;
import bot.multifunction.model.button.ButtonUtil;
import bot.multifunction.model.steps.Steps;
import bot.multifunction.model.vocabluary.Reader;
import bot.multifunction.model.vocabluary.VocabSwitchInfo;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageReplyMarkup;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.IOException;

public class CallBackQHandler {

    public static void handle(CallbackQuery callbackQuery, TelegramLongPollingBot bot) throws IOException, TelegramApiException {
        String data = callbackQuery.getData();
        switch (data) {
            case "on" -> turnOn(callbackQuery, bot);
            case "off" -> turnOff(callbackQuery, bot);
            case "once" -> createOnceReminder(callbackQuery, bot);
            case "every" -> createEveryReminder(callbackQuery, bot);
            case "even" -> createEvenReminder(callbackQuery, bot);
            case "odd" -> createOddReminder(callbackQuery, bot);
            case "cansel" ->
                    bot.execute(new SendMessage(callbackQuery.getMessage().getChatId().toString(), "Cancelled"));
            case "hour_increment" -> hourIncrement(callbackQuery, bot);
            case "hour_decrement" -> hourDecrement(callbackQuery, bot);
            case "minute_increment" -> minuteIncrement(callbackQuery, bot);
            case "minute_decrement" -> minuteDecrement(callbackQuery, bot);
            case "apply" -> applyCallBack(callbackQuery, bot);
        }
    }

    private static void applyCallBack(CallbackQuery callbackQuery, TelegramLongPollingBot bot) {

    }

    private static void minuteDecrement(CallbackQuery callbackQuery, TelegramLongPollingBot bot) throws TelegramApiException {
        Long chatId = callbackQuery.getMessage().getChatId();
        if (!UserRepo.REMINDER_TIME.containsKey(chatId)) {
            UserRepo.REMINDER_TIME.put(chatId, new TimeManager());
        }
        TimeManager timeManager = UserRepo.REMINDER_TIME.get(chatId);

        if (timeManager.getMinute() != 0) {
            timeManager.setMinute(timeManager.getMinute() - 2);
            InlineKeyboardMarkup markup = ButtonUtil.Increment(chatId);
            EditMessageReplyMarkup editMessageReplyMarkup = EditMessageReplyMarkup.builder()
                    .replyMarkup(markup)
                    .chatId(callbackQuery.getMessage().getChatId())
                    .messageId(callbackQuery.getMessage().getMessageId())
                    .build();
            bot.execute(editMessageReplyMarkup);
        } else {
            timeManager.setMinute(58);
            if (timeManager.getHour() == 0) {
                timeManager.setHour(23);
            } else {
                timeManager.setHour(timeManager.getHour() - 1);
            }
            InlineKeyboardMarkup markup = ButtonUtil.Increment(chatId);
            EditMessageReplyMarkup editMessageReplyMarkup = EditMessageReplyMarkup.builder()
                    .replyMarkup(markup)
                    .chatId(callbackQuery.getMessage().getChatId())
                    .messageId(callbackQuery.getMessage().getMessageId())
                    .build();
            bot.execute(editMessageReplyMarkup);
        }
    }

    private static void minuteIncrement(CallbackQuery callbackQuery, TelegramLongPollingBot bot) throws TelegramApiException {
        Long chatId = callbackQuery.getMessage().getChatId();
        if (!UserRepo.REMINDER_TIME.containsKey(chatId)) {
            UserRepo.REMINDER_TIME.put(chatId, new TimeManager());
        }
        TimeManager timeManager = UserRepo.REMINDER_TIME.get(chatId);

        if (timeManager.getMinute() < 60) {
            timeManager.setMinute(timeManager.getMinute() + 2);
            InlineKeyboardMarkup markup = ButtonUtil.Increment(chatId);
            EditMessageReplyMarkup editMessageReplyMarkup = EditMessageReplyMarkup.builder()
                    .replyMarkup(markup)
                    .chatId(callbackQuery.getMessage().getChatId())
                    .messageId(callbackQuery.getMessage().getMessageId())
                    .build();
            bot.execute(editMessageReplyMarkup);
        } else {
            timeManager.setMinute(0);

            InlineKeyboardMarkup markup = ButtonUtil.Increment(chatId);
            EditMessageReplyMarkup editMessageReplyMarkup = EditMessageReplyMarkup.builder()
                    .replyMarkup(markup)
                    .chatId(callbackQuery.getMessage().getChatId())
                    .messageId(callbackQuery.getMessage().getMessageId())
                    .build();
            bot.execute(editMessageReplyMarkup);
        }
    }


    private static void hourDecrement(CallbackQuery callbackQuery, TelegramLongPollingBot bot) throws TelegramApiException {
        Long chatId = callbackQuery.getMessage().getChatId();
        if (!UserRepo.REMINDER_TIME.containsKey(chatId)) {
            UserRepo.REMINDER_TIME.put(chatId, new TimeManager());
        }
        TimeManager timeManager = UserRepo.REMINDER_TIME.get(chatId);

        if (timeManager.getHour() != 0) {
            timeManager.setHour(timeManager.getHour() - 1);
            InlineKeyboardMarkup markup = ButtonUtil.Increment(chatId);
            EditMessageReplyMarkup editMessageReplyMarkup = EditMessageReplyMarkup.builder()
                    .replyMarkup(markup)
                    .chatId(callbackQuery.getMessage().getChatId())
                    .messageId(callbackQuery.getMessage().getMessageId())
                    .build();
            bot.execute(editMessageReplyMarkup);
        } else {
            timeManager.setHour(23);
            InlineKeyboardMarkup markup = ButtonUtil.Increment(chatId);
            EditMessageReplyMarkup editMessageReplyMarkup = EditMessageReplyMarkup.builder()
                    .replyMarkup(markup)
                    .chatId(callbackQuery.getMessage().getChatId())
                    .messageId(callbackQuery.getMessage().getMessageId())
                    .build();
            bot.execute(editMessageReplyMarkup);
        }
    }


    private static void hourIncrement(CallbackQuery callbackQuery, TelegramLongPollingBot bot) throws TelegramApiException {
        Long chatId = callbackQuery.getMessage().getChatId();
        if (!UserRepo.REMINDER_TIME.containsKey(chatId)) {
            UserRepo.REMINDER_TIME.put(chatId, new TimeManager());
        }
        TimeManager timeManager = UserRepo.REMINDER_TIME.get(chatId);

        if (timeManager.getHour() != 23) {
            timeManager.setHour(timeManager.getHour() + 1);
            InlineKeyboardMarkup markup = ButtonUtil.Increment(chatId);
            EditMessageReplyMarkup editMessageReplyMarkup = EditMessageReplyMarkup.builder()
                    .replyMarkup(markup)
                    .chatId(callbackQuery.getMessage().getChatId())
                    .messageId(callbackQuery.getMessage().getMessageId())
                    .build();
            bot.execute(editMessageReplyMarkup);
        } else {
            timeManager.setHour(0);
            InlineKeyboardMarkup markup = ButtonUtil.Increment(chatId);
            EditMessageReplyMarkup editMessageReplyMarkup = EditMessageReplyMarkup.builder()
                    .replyMarkup(markup)
                    .chatId(callbackQuery.getMessage().getChatId())
                    .messageId(callbackQuery.getMessage().getMessageId())
                    .build();
            bot.execute(editMessageReplyMarkup);
        }
    }


    private static void createOddReminder(CallbackQuery callbackQuery, TelegramLongPollingBot bot) throws TelegramApiException {
        bot.execute(new SendMessage(callbackQuery.getMessage().getChatId().toString(),
                "Enter date, time and text (pattern: dd-MM-yyyy HH:mm:ss ( reminder text ) ) "));
        UserRepo.USER_STEP.put(callbackQuery.getMessage().getChatId(), Steps.CREATING_ODD_DAYS_REMINDER);
    }

    private static void createEvenReminder(CallbackQuery callbackQuery, TelegramLongPollingBot bot) throws TelegramApiException {
        bot.execute(new SendMessage(callbackQuery.getMessage().getChatId().toString(),
                "Enter date, time and text (pattern: dd-MM-yyyy HH:mm:ss ( reminder text ) ) "));
        UserRepo.USER_STEP.put(callbackQuery.getMessage().getChatId(), Steps.CREATING_EVEN_DAYS_REMINDER);
    }

    private static void createEveryReminder(CallbackQuery callbackQuery, TelegramLongPollingBot bot) throws TelegramApiException {
        bot.execute(new SendMessage(callbackQuery.getMessage().getChatId().toString(),
                "Enter date, time and text (pattern: dd-MM-yyyy HH:mm:ss ( reminder text ) ) "));
        UserRepo.USER_STEP.put(callbackQuery.getMessage().getChatId(), Steps.CREATING_EVERYDAY_REMINDER);
    }


    private static void turnOn(CallbackQuery callbackQuery, TelegramLongPollingBot bot) throws IOException, TelegramApiException {
        if (UserRepo.VOCAB_SWITCH_INFO.get(callbackQuery.getMessage().getChatId()) == null ||
                UserRepo.VOCAB_SWITCH_INFO.get(callbackQuery.getMessage().getChatId()) == VocabSwitchInfo.OFF) {
            bot.execute(new SendMessage(callbackQuery.getMessage().getChatId().toString(), new Reader().giveWord()));
            UserRepo.VOCAB_SWITCH_INFO.put(callbackQuery.getMessage().getChatId(), VocabSwitchInfo.ON);
        } else if (UserRepo.VOCAB_SWITCH_INFO.get(callbackQuery.getMessage().getChatId()) == VocabSwitchInfo.ON) {
            bot.execute(new SendMessage(callbackQuery.getMessage().getChatId().toString(), "It is already ON"));
        }

    }

    private static void turnOff(CallbackQuery callbackQuery, TelegramLongPollingBot bot) throws TelegramApiException {
        if (UserRepo.VOCAB_SWITCH_INFO.get(callbackQuery.getMessage().getChatId()) == null ||
                UserRepo.VOCAB_SWITCH_INFO.get(callbackQuery.getMessage().getChatId()) == VocabSwitchInfo.OFF) {
            bot.execute(new SendMessage(callbackQuery.getMessage().getChatId().toString(), "It's already OFF"));
        } else if (UserRepo.VOCAB_SWITCH_INFO.get(callbackQuery.getMessage().getChatId()) == VocabSwitchInfo.ON) {
            bot.execute(new SendMessage(callbackQuery.getMessage().getChatId().toString(), "Stopped"));
            UserRepo.VOCAB_SWITCH_INFO.put(callbackQuery.getMessage().getChatId(), VocabSwitchInfo.OFF);
        }
    }

    private static void createOnceReminder(CallbackQuery callbackQuery, TelegramLongPollingBot bot) throws TelegramApiException {
        SendMessage sendMessage = new SendMessage(callbackQuery.getMessage().getChatId().toString(), "Select time.");
        sendMessage.setReplyMarkup(ButtonUtil.Increment(callbackQuery.getMessage().getChatId()));
        bot.execute(sendMessage);
        UserRepo.USER_STEP.put(callbackQuery.getMessage().getChatId(), Steps.CREATING_ONCE_REMINDER);
    }
}





