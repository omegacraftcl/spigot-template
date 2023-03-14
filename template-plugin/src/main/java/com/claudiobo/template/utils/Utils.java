package com.claudiobo.template.utils;

import java.util.Date;

public class Utils {

    public static String color(String string) {
        return string.replace("&", "§");
    }

    public static boolean isNombreValid(String str) {
        return str.matches("^\\w{3,16}$");
    }

    public static boolean isNumeric(String str) {
        try {
            Integer.valueOf(str);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static String calcularTiempo(long antes) {
        long now = new Date().getTime();
        long diff = now - antes;
        return friendlyTimeDiff(diff);
    }

    public static String friendlyTimeDiff(long timeDifferenceMilliseconds) {
        long diffSeconds = timeDifferenceMilliseconds / 1000;
        long diffMinutes = timeDifferenceMilliseconds / (60 * 1000);
        long diffHours = timeDifferenceMilliseconds / (60 * 60 * 1000);
        long diffDays = timeDifferenceMilliseconds / (60 * 60 * 1000 * 24);
        long diffWeeks = timeDifferenceMilliseconds / (60 * 60 * 1000 * 24 * 7);
        long diffMonths = (long) (timeDifferenceMilliseconds / (60 * 60 * 1000 * 24 * 30.41666666));
        long diffYears = timeDifferenceMilliseconds / ((long) 60 * 60 * 1000 * 24 * 365);

        if (diffSeconds < 5) {
            return "Hace unos segundos";
        } else if (diffMinutes < 1) {
            return diffSeconds + " segundo" + ((diffSeconds != 1) ? "s" : "");
        } else if (diffHours < 1) {
            return diffMinutes + " minuto" + ((diffMinutes != 1) ? "s" : "");
        } else if (diffDays < 1) {
            return diffHours + " hora" + ((diffHours != 1) ? "s" : "");
        } else if (diffWeeks < 1) {
            return diffDays + " dia" + ((diffDays != 1) ? "s" : "");
        } else if (diffMonths < 1) {
            return diffWeeks + " semana" + ((diffWeeks != 1) ? "s" : "");
        } else if (diffYears < 1) {
            return diffMonths + " mes" + ((diffMonths != 1) ? "s" : "");
        } else {
            return diffYears + " año" + ((diffYears != 1) ? "s" : "");
        }
    }

}
