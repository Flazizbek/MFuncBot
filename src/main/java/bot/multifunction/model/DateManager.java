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
public class DateManager {
   private int month = 0;
   private int day = 0;

    public static void dayIncrement(CallbackQuery callbackQuery, TelegramLongPollingBot bot) throws TelegramApiException {
        Long chatId = callbackQuery.getMessage().getChatId();
        if (!UserRepo.DATE.containsKey(chatId)) {
            UserRepo.DATE.put(chatId, new DateManager());
        }
        DateManager dateManager= UserRepo.DATE.get(chatId);

        if (dateManager.getDay() < 31) {
            dateManager.setDay(dateManager.getDay() + 1);
            InlineKeyboardMarkup markup =  ButtonUtil.DayButton(callbackQuery);
            EditMessageReplyMarkup editMessageReplyMarkup = EditMessageReplyMarkup.builder()
                    .replyMarkup(markup)
                    .chatId(callbackQuery.getMessage().getChatId())
                    .messageId(callbackQuery.getMessage().getMessageId())
                    .build();
            bot.execute(editMessageReplyMarkup);
        } else {
            dateManager.setDay(0);

            InlineKeyboardMarkup markup =  ButtonUtil.DayButton(callbackQuery);
            EditMessageReplyMarkup editMessageReplyMarkup = EditMessageReplyMarkup.builder()
                    .replyMarkup(markup)
                    .chatId(callbackQuery.getMessage().getChatId())
                    .messageId(callbackQuery.getMessage().getMessageId())
                    .build();
            bot.execute(editMessageReplyMarkup);
        }
    }

    public static void dayDecrement(CallbackQuery callbackQuery, TelegramLongPollingBot bot) throws TelegramApiException {
        Long chatId = callbackQuery.getMessage().getChatId();
        if (!UserRepo.DATE.containsKey(chatId)) {
            UserRepo.DATE.put(chatId, new DateManager());
        }
        DateManager dateManager= UserRepo.DATE.get(chatId);

        if (dateManager.getDay() != 0) {
            dateManager.setDay(dateManager.getDay() - 1);
            InlineKeyboardMarkup markup =  ButtonUtil.DayButton(callbackQuery);
            EditMessageReplyMarkup editMessageReplyMarkup = EditMessageReplyMarkup.builder()
                    .replyMarkup(markup)
                    .chatId(callbackQuery.getMessage().getChatId())
                    .messageId(callbackQuery.getMessage().getMessageId())
                    .build();
            bot.execute(editMessageReplyMarkup);
        } else {
            dateManager.setDay(31);
            if (dateManager.getMonth() == 0) {
                dateManager.setMonth(12);
            } else {
                dateManager.setMonth(dateManager.getMonth() - 1);
            }
            InlineKeyboardMarkup markup =  ButtonUtil.DayButton(callbackQuery);
            EditMessageReplyMarkup editMessageReplyMarkup = EditMessageReplyMarkup.builder()
                    .replyMarkup(markup)
                    .chatId(callbackQuery.getMessage().getChatId())
                    .messageId(callbackQuery.getMessage().getMessageId())
                    .build();
            bot.execute(editMessageReplyMarkup);
        }
    }



    public static void monthIncrement(CallbackQuery callbackQuery, TelegramLongPollingBot bot) throws TelegramApiException {
        Long chatId = callbackQuery.getMessage().getChatId();
        if (!UserRepo.DATE.containsKey(chatId)) {
            UserRepo.DATE.put(chatId, new DateManager());
        }
        DateManager dateManager= UserRepo.DATE.get(chatId);

        if (dateManager.getMonth() != 12) {
            dateManager.setMonth(dateManager.getMonth() + 1);
            InlineKeyboardMarkup markup =  ButtonUtil.DayButton(callbackQuery);
            EditMessageReplyMarkup editMessageReplyMarkup = EditMessageReplyMarkup.builder()
                    .replyMarkup(markup)
                    .chatId(callbackQuery.getMessage().getChatId())
                    .messageId(callbackQuery.getMessage().getMessageId())
                    .build();
            bot.execute(editMessageReplyMarkup);
        } else {
            dateManager.setMonth(0);
            InlineKeyboardMarkup markup =  ButtonUtil.DayButton(callbackQuery);
            EditMessageReplyMarkup editMessageReplyMarkup = EditMessageReplyMarkup.builder()
                    .replyMarkup(markup)
                    .chatId(callbackQuery.getMessage().getChatId())
                    .messageId(callbackQuery.getMessage().getMessageId())
                    .build();
            bot.execute(editMessageReplyMarkup);
        }
    }
    public static void monthDecrement(CallbackQuery callbackQuery, TelegramLongPollingBot bot) throws TelegramApiException {
        Long chatId = callbackQuery.getMessage().getChatId();
        if (!UserRepo.DATE.containsKey(chatId)) {
            UserRepo.DATE.put(chatId, new DateManager());
        }
        DateManager dateManager= UserRepo.DATE.get(chatId);

        if (dateManager.getMonth() != 0) {
            dateManager.setMonth(dateManager.getMonth() - 1);
            InlineKeyboardMarkup markup =  ButtonUtil.DayButton(callbackQuery);
            EditMessageReplyMarkup editMessageReplyMarkup = EditMessageReplyMarkup.builder()
                    .replyMarkup(markup)
                    .chatId(callbackQuery.getMessage().getChatId())
                    .messageId(callbackQuery.getMessage().getMessageId())
                    .build();
            bot.execute(editMessageReplyMarkup);
        } else {
            dateManager.setMonth(12);
            InlineKeyboardMarkup markup = ButtonUtil.DayButton(callbackQuery);
            EditMessageReplyMarkup editMessageReplyMarkup = EditMessageReplyMarkup.builder()
                    .replyMarkup(markup)
                    .chatId(callbackQuery.getMessage().getChatId())
                    .messageId(callbackQuery.getMessage().getMessageId())
                    .build();
            bot.execute(editMessageReplyMarkup);
        }
    }


}
