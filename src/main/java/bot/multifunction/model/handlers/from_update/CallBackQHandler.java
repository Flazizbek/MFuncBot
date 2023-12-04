package bot.multifunction.model.handlers.from_update;


import bot.multifunction.model.date_manage.DateManager;
import bot.multifunction.model.date_manage.TimeManager;
import bot.multifunction.model.Users.UserRepo;
import bot.multifunction.model.button.ButtonUtil;
import bot.multifunction.model.enums.steps.Steps;
import bot.multifunction.model.vocabluary.Reader;
import bot.multifunction.model.vocabluary.VocabSwitchInfo;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.IOException;


public class CallBackQHandler {

    public static void handle(CallbackQuery callbackQuery, TelegramLongPollingBot bot) throws IOException, TelegramApiException {
        String data = callbackQuery.getData();
        switch (data) {
            //Language
            case "on" -> turnOn(callbackQuery, bot);
            case "off" -> turnOff(callbackQuery, bot);
            //Reminder
            case "once" -> createOnceReminder(callbackQuery, bot);
            case "every" -> createEveryReminder(callbackQuery, bot);
            case "even" -> createEvenReminder(callbackQuery, bot);
            case "odd" -> createOddReminder(callbackQuery, bot);
            case "cansel" -> bot.execute(new SendMessage(callbackQuery.getMessage().getChatId().toString(), "Cancelled"));
            //Time
            case "hour_increment" -> TimeManager.hourIncrement(callbackQuery, bot);
            case "hour_decrement" -> TimeManager.hourDecrement(callbackQuery, bot);
            case "minute_increment" -> TimeManager.minuteIncrement(callbackQuery, bot);
            case "minute_decrement" -> TimeManager.minuteDecrement(callbackQuery, bot);
            case "apply_time" -> applyTimeCallBack(callbackQuery, bot);
            case "month_increment" -> DateManager.monthIncrement(callbackQuery, bot);
            case "month_decrement" -> DateManager.monthDecrement(callbackQuery, bot);
            case "day_increment" -> DateManager.dayIncrement(callbackQuery, bot);
            case "day_decrement" -> DateManager.dayDecrement(callbackQuery, bot);
            case "apply_day" -> applyDateCallBack(callbackQuery, bot);
        }
    }

    private static void applyDateCallBack(CallbackQuery callbackQuery, TelegramLongPollingBot bot) throws TelegramApiException {
        Long chatId = callbackQuery.getMessage().getChatId();
        DateManager dateManager = UserRepo.DATE.get(chatId);
        int month = dateManager.getMonth();
        int day = dateManager.getDay();
        String monthString = (month < 10) ? "0" + month : String.valueOf(month);
        String dayeString = (day < 10) ? "0" + day : String.valueOf(day);
        String result = monthString+"/"+dayeString+"/2023";
        UserRepo.DATE_COLLECTOR.put(chatId,result);
        dateManager.setMonth(0);
        dateManager.setDay(0);
        SendMessage sendMessage = new SendMessage(chatId.toString(), "Select time.");
        sendMessage.setReplyMarkup(ButtonUtil.TimeIncrement(chatId));
        bot.execute(sendMessage);

    }


    private static void applyTimeCallBack(CallbackQuery callbackQuery, TelegramLongPollingBot bot) throws TelegramApiException {
        Long chatId = callbackQuery.getMessage().getChatId();
        TimeManager timeManager = UserRepo.TIME.get(chatId);
        int hour = timeManager.getHour();
        int minute = timeManager.getMinute();
        String hourString = (hour < 10) ? "0" + hour : String.valueOf(hour);
        String minuteString = (minute < 10) ? "0" + minute : String.valueOf(minute);
        String result = hourString+":"+minuteString+":00";
        UserRepo.DATE_COLLECTOR.put(chatId, UserRepo.DATE_COLLECTOR.get(chatId)+" "+result);
        timeManager.setHour(0);
        timeManager.setMinute(0);
        bot.execute(new SendMessage(chatId.toString(),"Enter text for reminder"));


    }

    private static void createOddReminder(CallbackQuery callbackQuery, TelegramLongPollingBot bot) throws TelegramApiException {
        SendMessage sendMessage = new SendMessage(callbackQuery.getMessage().getChatId().toString(), "Select date(mm:dd).");
        sendMessage.setReplyMarkup(ButtonUtil.DayButton(callbackQuery));
        bot.execute(sendMessage);
        UserRepo.USER_STEP.put(callbackQuery.getMessage().getChatId(), Steps.CREATING_ODD_DAYS_REMINDER);
    }

    private static void createEvenReminder(CallbackQuery callbackQuery, TelegramLongPollingBot bot) throws TelegramApiException {
        SendMessage sendMessage = new SendMessage(callbackQuery.getMessage().getChatId().toString(), "Select date(mm:dd).");
        sendMessage.setReplyMarkup(ButtonUtil.DayButton(callbackQuery));
        bot.execute(sendMessage);
        UserRepo.USER_STEP.put(callbackQuery.getMessage().getChatId(), Steps.CREATING_EVEN_DAYS_REMINDER);
    }

    private static void createEveryReminder(CallbackQuery callbackQuery, TelegramLongPollingBot bot) throws TelegramApiException {
        SendMessage sendMessage = new SendMessage(callbackQuery.getMessage().getChatId().toString(), "Select date(mm:dd).");
        sendMessage.setReplyMarkup(ButtonUtil.DayButton(callbackQuery));
        bot.execute(sendMessage);
        UserRepo.USER_STEP.put(callbackQuery.getMessage().getChatId(), Steps.CREATING_EVERYDAY_REMINDER);
    }


    private static void turnOn(CallbackQuery callbackQuery, TelegramLongPollingBot bot) throws IOException, TelegramApiException {
        if (UserRepo.VOCAB_SWITCH_INFO.get(callbackQuery.getMessage().getChatId()) == null || UserRepo.VOCAB_SWITCH_INFO.get(callbackQuery.getMessage().getChatId()) == VocabSwitchInfo.OFF) {
            new Reader().giveWordRegular(callbackQuery.getMessage(), bot);
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
        SendMessage sendMessage = new SendMessage(callbackQuery.getMessage().getChatId().toString(), "Select date(mm:dd).");
        sendMessage.setReplyMarkup(ButtonUtil.DayButton(callbackQuery));
        bot.execute(sendMessage);
        UserRepo.USER_STEP.put(callbackQuery.getMessage().getChatId(), Steps.CREATING_ONCE_REMINDER);
    }
}





