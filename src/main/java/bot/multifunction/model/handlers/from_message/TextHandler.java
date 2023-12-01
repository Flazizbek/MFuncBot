package bot.multifunction.model.handlers.from_message;

import bot.multifunction.model.Users.UserRepo;
import bot.multifunction.model.steps.Steps;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TextHandler {
    static Pattern pattern = Pattern.compile("\\d{2}-\\d{2}-\\d{4} \\d{2}:\\d{2}:\\d{2}");
    static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
    static  LocalDate currentDate = LocalDate.now();
    static  DayOfWeek currentDayOfWeek = currentDate.getDayOfWeek();
    public static void handle(Message message, TelegramLongPollingBot bot) {
        if(UserRepo.USER_STEP.get(message.getChatId())== Steps.CREATING_ONCE_REMINDER){
            creatingReminder(message,bot,1_000_000);
        } else if (UserRepo.USER_STEP.get(message.getChatId())== Steps.CREATING_EVERYDAY_REMINDER) {
            creatingReminder(message,bot,24);
        }else if (UserRepo.USER_STEP.get(message.getChatId())== Steps.CREATING_EVEN_DAYS_REMINDER) {
            creatingEvenReminder(message,bot,24);
        }else if (UserRepo.USER_STEP.get(message.getChatId())== Steps.CREATING_ODD_DAYS_REMINDER) {
            creatingOddReminder(message,bot,24);
        }

    }

    private static void creatingReminder(Message message, TelegramLongPollingBot bot, long period) {
        Matcher matcher = pattern.matcher(UserRepo.DATE_COLLECTOR.get(message.getChatId()));
        long seconds=0;
        if(matcher.find()) {
            TemporalAccessor accessor = formatter.parse(matcher.group());
            LocalDateTime from = LocalDateTime.from(accessor);
            Duration between = Duration.between(LocalDateTime.now(), from);
            seconds = between.toSeconds();
            ScheduledExecutorService executor;
            executor = Executors.newScheduledThreadPool(4);
            executor.scheduleAtFixedRate(() -> {
                try {
                    bot.execute(new SendMessage(message.getChatId().toString(), "You have one reminder : " + message.getText()));
                } catch (TelegramApiException e) {
                    throw new RuntimeException(e);
                }
            }, seconds, 3600 * period, TimeUnit.SECONDS);
        }
    }

    private static void creatingEvenReminder(Message message, TelegramLongPollingBot bot, long period) {
        Matcher matcher = pattern.matcher(UserRepo.DATE_COLLECTOR.get(message.getChatId()));
        long seconds=0;
        if(matcher.find()) {
            if (currentDayOfWeek == DayOfWeek.TUESDAY || currentDayOfWeek == DayOfWeek.THURSDAY || currentDayOfWeek == DayOfWeek.SATURDAY) {
                TemporalAccessor accessor = formatter.parse(matcher.group());
                LocalDateTime from = LocalDateTime.from(accessor);
                Duration between = Duration.between(LocalDateTime.now(), from);
                seconds = between.toSeconds();
                ScheduledExecutorService executor;
                executor = Executors.newScheduledThreadPool(4);
                executor.scheduleAtFixedRate(() -> {
                    try {
                        bot.execute(new SendMessage(message.getChatId().toString(), "You have one reminder : " + message.getText()));
                    } catch (TelegramApiException e) {
                        throw new RuntimeException(e);
                    }
                }, seconds, 3600 * period, TimeUnit.SECONDS);
            }
        }
    }


    public static void creatingOddReminder(Message message, TelegramLongPollingBot bot, long period) {
        Matcher matcher = pattern.matcher(UserRepo.DATE_COLLECTOR.get(message.getChatId()));
        long seconds=0;
        if(matcher.find()) {
            if (currentDayOfWeek == DayOfWeek.MONDAY || currentDayOfWeek == DayOfWeek.WEDNESDAY || currentDayOfWeek == DayOfWeek.FRIDAY) {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
                TemporalAccessor accessor = formatter.parse(matcher.group());
                LocalDateTime from = LocalDateTime.from(accessor);
                Duration between = Duration.between(LocalDateTime.now(), from);
                seconds = between.toSeconds();
                ScheduledExecutorService executor;
                executor = Executors.newScheduledThreadPool(4);
                executor.scheduleAtFixedRate(() -> {
                    try {
                        bot.execute(new SendMessage(message.getChatId().toString(), "You have one reminder : " + message.getText()));
                    } catch (TelegramApiException e) {
                        throw new RuntimeException(e);
                    }
                }, seconds, 3600 * period, TimeUnit.SECONDS);
            }
        }
    }

}


