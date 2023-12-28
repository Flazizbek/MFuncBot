package bot.multifunction.model;

import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

public class Test {

    public static void main(String[] args) {
        // Create a timer
        Timer timer = new Timer();

        // Get the current date and time
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 5); // Set the hour to 5
        calendar.set(Calendar.MINUTE, 0);      // Set the minute to 0
        calendar.set(Calendar.SECOND, 0);      // Set the second to 0

        // Schedule the task to run every day at 5:00
        timer.scheduleAtFixedRate(new PrintTextTask(), calendar.getTime(), 24 * 60 * 60 * 1000); // 24 hours in milliseconds
    }

    static class PrintTextTask extends TimerTask {
        @Override
        public void run() {
            // Replace this with the text you want to print
            System.out.println("It's 5:00! Time to print some text.");
        }
    }
}



