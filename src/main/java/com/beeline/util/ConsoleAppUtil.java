package com.beeline.util;

import java.time.LocalTime;

public class ConsoleAppUtil {


    public String getFirstMessage() {
        int hour = LocalTime.now().getHour();
        String greeting;
        if (hour >= 0 && hour < 7) {
            greeting = "Доброй ночи %s!";
        } else if (hour >= 7 && hour < 11) {
            greeting = "Доброе утро %s!";
        } else if (hour >= 11 && hour < 17) {
            greeting = "Добрый день %s!";
        } else if (hour >= 17 && hour < 21) {
            greeting = "Добрый вечер %s!";
        } else {
            greeting = "Доброй ночи %s!";
        }
        return greeting;
    }
}
