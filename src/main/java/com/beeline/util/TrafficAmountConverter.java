package com.beeline.util;

public class TrafficAmountConverter {
    private static final long BYTES_IN_KB = 1024;
    private static final long BYTES_IN_MB = BYTES_IN_KB * 1024;
    private static final long BYTES_IN_GB = BYTES_IN_MB * 1024;

    public String convertTrafficAmount(String template, long trafficAmount) {
        long gigabytes = trafficAmount / BYTES_IN_GB;
        long megabytes = (trafficAmount % BYTES_IN_GB) / BYTES_IN_MB;
        long kilobytes = (trafficAmount % BYTES_IN_MB) / BYTES_IN_KB;
        StringBuilder trafficAmountString = new StringBuilder(template);
        boolean noTrafficAmount = true;
        if (gigabytes != 0) {
            trafficAmountString.append(gigabytes).append(" ГБ ");
            noTrafficAmount = false;
        }
        if (megabytes != 0) {
            trafficAmountString.append(megabytes).append(" МБ ");
            noTrafficAmount = false;
        }
        if (kilobytes != 0) {
            trafficAmountString.append(kilobytes).append(" КБ");
            noTrafficAmount = false;
        }
        if (noTrafficAmount) {
            return "У вас закончился траффик";
        }
        return trafficAmountString.toString();
    }
}
