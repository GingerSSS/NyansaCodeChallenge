package com.Nyansa;

public class TimenWeb {
    int date;
    String website;
    public TimenWeb (String line) {
        String[] strs = line.split("\\|");
        website = strs[1];
        long unixSeconds = Long.parseLong(strs[0]);
        date = (int) Math.floor(unixSeconds / (60*60*24));
    }
}
