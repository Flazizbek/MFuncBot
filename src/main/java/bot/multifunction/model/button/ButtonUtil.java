package bot.multifunction.model.button;

import bot.multifunction.model.Users.UserRepo;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.List;

public class ButtonUtil {

    public static final InlineKeyboardMarkup ADD_REMAINDER = InlineKeyboardMarkup.builder()
            .keyboardRow(List.of(
                    InlineKeyboardButton.builder().text("Send once").callbackData("once").build(),
                    InlineKeyboardButton.builder().text("Send every day").callbackData("every").build()
            ))
            .keyboardRow(List.of(
                    InlineKeyboardButton.builder().text("Send on even days").callbackData("even").build(),
                    InlineKeyboardButton.builder().text("Send on odd days").callbackData("odd").build()
            ))
            .keyboardRow(List.of(
                    InlineKeyboardButton.builder().text("Cansel").callbackData("cansel").build()
            ))
            .build();
    public static final InlineKeyboardMarkup EDIT_REMAINDER = InlineKeyboardMarkup.builder()
            .keyboardRow(List.of(
                    InlineKeyboardButton.builder().text("Turn on").callbackData("on").build(),
                    InlineKeyboardButton.builder().text("Turn off").callbackData("off").build()
            )).build();
    public static final InlineKeyboardMarkup DELETE_REMAINDER = InlineKeyboardMarkup.builder()
            .keyboardRow(List.of(
                    InlineKeyboardButton.builder().text("Turn on").callbackData("on").build(),
                    InlineKeyboardButton.builder().text("Turn off").callbackData("off").build()
            )).build();
    public static final InlineKeyboardMarkup ON_OFF_MARKUP = InlineKeyboardMarkup.builder()
            .keyboardRow(List.of(
                    InlineKeyboardButton.builder().text("Turn on").callbackData("on").build(),
                    InlineKeyboardButton.builder().text("Turn off").callbackData("off").build()
            )).build();
    public static final InlineKeyboardMarkup LANGUAGE = InlineKeyboardMarkup.builder()
            .keyboardRow(List.of(
                    InlineKeyboardButton.builder().text("Turn on").callbackData("on").build(),
                    InlineKeyboardButton.builder().text("Turn off").callbackData("off").build()
            )).build();

    public static InlineKeyboardMarkup TimeIncrement(Long chatId) {
        if (UserRepo.TIME.get(chatId) == null){
            return InlineKeyboardMarkup.builder()
                    .keyboardRow(List.of(
                            InlineKeyboardButton.builder().text(String.valueOf(0)).callbackData("h").build(),
                            InlineKeyboardButton.builder().text(String.valueOf(0)).callbackData("m").build()
                    ))
                    .keyboardRow(List.of(
                            InlineKeyboardButton.builder().text("+").callbackData("hour_increment").build(),
                            InlineKeyboardButton.builder().text("-").callbackData("hour_decrement").build(),
                            InlineKeyboardButton.builder().text("+").callbackData("minute_increment").build(),
                            InlineKeyboardButton.builder().text("-").callbackData("minute_decrement").build()
                    ))
                    .keyboardRow(List.of(
                            InlineKeyboardButton.builder().text("Apply").callbackData("apply").build()
                    ))
                    .build();
        }
        return InlineKeyboardMarkup.builder()
                .keyboardRow(List.of(
                        InlineKeyboardButton.builder().text(String.valueOf(UserRepo.TIME.get(chatId).getHour())).callbackData("h").build(),
                        InlineKeyboardButton.builder().text(String.valueOf(UserRepo.TIME.get(chatId).getMinute())).callbackData("m").build()
                ))
                .keyboardRow(List.of(
                        InlineKeyboardButton.builder().text("+").callbackData("hour_increment").build(),
                        InlineKeyboardButton.builder().text("-").callbackData("hour_decrement").build(),
                        InlineKeyboardButton.builder().text("+").callbackData("minute_increment").build(),
                        InlineKeyboardButton.builder().text("-").callbackData("minute_decrement").build()
                ))
                .keyboardRow(List.of(
                        InlineKeyboardButton.builder().text("Apply").callbackData("apply_time").build()
                ))
                .build();
    }

    public static InlineKeyboardMarkup DayButton(Long chatId) {
        if (UserRepo.DATE.get(chatId) == null) {
            return InlineKeyboardMarkup.builder()
                    .keyboardRow(List.of(
                            InlineKeyboardButton.builder().text(String.valueOf(0)).callbackData("m").build(),
                            InlineKeyboardButton.builder().text(String.valueOf(0)).callbackData("d").build()
                    ))
                    .keyboardRow(List.of(
                            InlineKeyboardButton.builder().text("+").callbackData("month_increment").build(),
                            InlineKeyboardButton.builder().text("-").callbackData("month_decrement").build(),
                            InlineKeyboardButton.builder().text("+").callbackData("day_increment").build(),
                            InlineKeyboardButton.builder().text("-").callbackData("day_decrement").build()
                    ))
                    .keyboardRow(List.of(
                            InlineKeyboardButton.builder().text("Apply").callbackData("apply_day").build(),
                            InlineKeyboardButton.builder().text("Cansel").callbackData("cancel").build()
                    ))
                    .build();
        }
        return InlineKeyboardMarkup.builder()
                .keyboardRow(List.of(
                        InlineKeyboardButton.builder().text(String.valueOf(UserRepo.DATE.get(chatId).getMonth())).callbackData("m").build(),
                        InlineKeyboardButton.builder().text(String.valueOf(UserRepo.DATE.get(chatId).getDay())).callbackData("d").build()
                ))
                .keyboardRow(List.of(
                        InlineKeyboardButton.builder().text("+").callbackData("month_increment").build(),
                        InlineKeyboardButton.builder().text("-").callbackData("month_decrement").build(),
                        InlineKeyboardButton.builder().text("+").callbackData("day_increment").build(),
                        InlineKeyboardButton.builder().text("-").callbackData("day_decrement").build()
                ))
                .keyboardRow(List.of(
                        InlineKeyboardButton.builder().text("Apply").callbackData("apply_day").build(),
                        InlineKeyboardButton.builder().text("Cansel").callbackData("cancel").build()
                ))
                .build();
    }
}
