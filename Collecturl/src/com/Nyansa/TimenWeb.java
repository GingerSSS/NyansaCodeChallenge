package com.Nyansa;

public class TimenWeb {
    int date;
    String website;
    public TimenWeb (String line) {
        // Split the input string to timestamp + url
        String[] strs = line.split("\\|");
        // Save the url to website
        website = strs[1];
        // Convert the string to long num
        long timestamp = Long.parseLong(strs[0]);
        // Omit the time, only need date
        date = (int) Math.floor(timestamp / (60*60*24));
    }
}
