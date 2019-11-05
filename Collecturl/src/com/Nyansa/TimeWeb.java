package com.Nyansa;

import java.time.Instant;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class TimeWeb {
    Date date;
    String website;
    public TimeWeb (String line) {
        String[] strs = line.split("\\|");
        website = strs[1];
        long unixSeconds = Long.parseLong(strs[0]);
//        date = new Date((long) unixSeconds * 1000);
        date = Date.from(Instant.ofEpochSecond(unixSeconds));
        System.out.println(date);
    }
}
