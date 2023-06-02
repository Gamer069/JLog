package me.illia.jlog.other;

import java.text.SimpleDateFormat;
import java.util.Date;

public class JLogTimestamp {
    private static final SimpleDateFormat format = new SimpleDateFormat("HH_mm_ss");

    public static String formatted(Date date) {
        return format.format(date);
    }
}
