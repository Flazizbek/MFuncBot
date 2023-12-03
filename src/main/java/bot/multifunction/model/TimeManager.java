package bot.multifunction.model;

import bot.multifunction.model.Users.UserRepo;
import bot.multifunction.model.button.ButtonUtil;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageReplyMarkup;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class TimeManager {
    private int hour =0;
    private int minute=0;

    public static void minuteDecrement(CallbackQuery callbackQuery, TelegramLongPollingBot bot) throws TelegramApiException {
        Long chatId = callbackQuery.getMessage().getChatId();
        if (!UserRepo.TIME.containsKey(chatId)) {
            UserRepo.TIME.put(chatId, new TimeManager());
        }
        TimeManager timeManager = UserRepo.TIME.get(chatId);

        if (timeManager.getMinute() != 0) {
            timeManager.setMinute(timeManager.getMinute() - 2);
            InlineKeyboardMarkup markup = ButtonUtil.TimeIncrement(chatId);
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
            InlineKeyboardMarkup markup = ButtonUtil.TimeIncrement(chatId);
            EditMessageReplyMarkup editMessageReplyMarkup = EditMessageReplyMarkup.builder()
                    .replyMarkup(markup)
                    .chatId(callbackQuery.getMessage().getChatId())
                    .messageId(callbackQuery.getMessage().getMessageId())
                    .build();
            bot.execute(editMessageReplyMarkup);
        }
    }

    public static void minuteIncrement(CallbackQuery callbackQuery, TelegramLongPollingBot bot) throws TelegramApiException {
        Long chatId = callbackQuery.getMessage().getChatId();
        if (!UserRepo.TIME.containsKey(chatId)) {
            UserRepo.TIME.put(chatId, new TimeManager());
        }
        TimeManager timeManager = UserRepo.TIME.get(chatId);

        if (timeManager.getMinute() < 60) {
            timeManager.setMinute(timeManager.getMinute() + 2);
            InlineKeyboardMarkup markup = ButtonUtil.TimeIncrement(chatId);
            EditMessageReplyMarkup editMessageReplyMarkup = EditMessageReplyMarkup.builder()
                    .replyMarkup(markup)
                    .chatId(callbackQuery.getMessage().getChatId())
                    .messageId(callbackQuery.getMessage().getMessageId())
                    .build();
            bot.execute(editMessageReplyMarkup);
        } else {
            timeManager.setMinute(0);

            InlineKeyboardMarkup markup = ButtonUtil.TimeIncrement(chatId);
            EditMessageReplyMarkup editMessageReplyMarkup = EditMessageReplyMarkup.builder()
                    .replyMarkup(markup)
                    .chatId(callbackQuery.getMessage().getChatId())
                    .messageId(callbackQuery.getMessage().getMessageId())
                    .build();
            bot.execute(editMessageReplyMarkup);
        }
    }


    public static void hourDecrement(CallbackQuery callbackQuery, TelegramLongPollingBot bot) throws TelegramApiException {
        Long chatId = callbackQuery.getMessage().getChatId();
        if (!UserRepo.TIME.containsKey(chatId)) {
            UserRepo.TIME.put(chatId, new TimeManager());
        }
        TimeManager timeManager = UserRepo.TIME.get(chatId);

        if (timeManager.getHour() != 0) {
            timeManager.setHour(timeManager.getHour() - 1);
            InlineKeyboardMarkup markup = ButtonUtil.TimeIncrement(chatId);
            EditMessageReplyMarkup editMessageReplyMarkup = EditMessageReplyMarkup.builder()
                    .replyMarkup(markup)
                    .chatId(callbackQuery.getMessage().getChatId())
                    .messageId(callbackQuery.getMessage().getMessageId())
                    .build();
            bot.execute(editMessageReplyMarkup);
        } else {
            timeManager.setHour(23);
            InlineKeyboardMarkup markup = ButtonUtil.TimeIncrement(chatId);
            EditMessageReplyMarkup editMessageReplyMarkup = EditMessageReplyMarkup.builder()
                    .replyMarkup(markup)
                    .chatId(callbackQuery.getMessage().getChatId())
                    .messageId(callbackQuery.getMessage().getMessageId())
                    .build();
            bot.execute(editMessageReplyMarkup);
        }
    }


    public static void hourIncrement(CallbackQuery callbackQuery, TelegramLongPollingBot bot) throws TelegramApiException {
        Long chatId = callbackQuery.getMessage().getChatId();
        if (!UserRepo.TIME.containsKey(chatId)) {
            UserRepo.TIME.put(chatId, new TimeManager());
        }
        TimeManager timeManager = UserRepo.TIME.get(chatId);

        if (timeManager.getHour() != 23) {
            timeManager.setHour(timeManager.getHour() + 1);
            InlineKeyboardMarkup markup = ButtonUtil.TimeIncrement(chatId);
            EditMessageReplyMarkup editMessageReplyMarkup = EditMessageReplyMarkup.builder()
                    .replyMarkup(markup)
                    .chatId(callbackQuery.getMessage().getChatId())
                    .messageId(callbackQuery.getMessage().getMessageId())
                    .build();
            bot.execute(editMessageReplyMarkup);
        } else {
            timeManager.setHour(0);
            InlineKeyboardMarkup markup =ButtonUtil.TimeIncrement(chatId);
            EditMessageReplyMarkup editMessageReplyMarkup = EditMessageReplyMarkup.builder()
                    .replyMarkup(markup)
                    .chatId(callbackQuery.getMessage().getChatId())
                    .messageId(callbackQuery.getMessage().getMessageId())
                    .build();
            bot.execute(editMessageReplyMarkup);
        }
    }

}
