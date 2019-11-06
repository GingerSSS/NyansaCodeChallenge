package com.Nyansa;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class Main {
    public void Collecturl (TimenWeb[] timenweb) {
        // Map<date, Map<url, freq>>
        Map<Integer, Map<String, Integer>> map = new HashMap<>();
        for(TimenWeb item : timenweb){
            if(!map.containsKey(item.date)){
                map.put(item.date, new HashMap<>());
            }
            // Get the Map<url, freq>
            Map<String, Integer> urlFreq = map.get(item.date);
            if(!urlFreq.containsKey(item.website)){
                urlFreq.put(item.website, 0);
            }
            // update the url freq in the map
            urlFreq.put(item.website, urlFreq.get(item.website) + 1);
            // no need to put back to map, it pass by reference
        }

        // Sort the map by date(keySet)
        Object[] keySet = map.keySet().toArray();
        Arrays.sort(keySet);
        for(Object d : keySet){
            // print the date
            printTheDate((int) d);
            // print the url & freq
            printUrlFreq(map.get(d));
        }
    }

    private void printTheDate(int d){
        // convert the d to Unix Timestamp (ms)
        long timestamp = (long) d * 24 * 60 * 60 * 1000;
        // simpleDateFormat refer: https://docs.oracle.com/javase/7/docs/api/java/text/SimpleDateFormat.html
        DateFormat simple = new SimpleDateFormat("MM/dd/yyyy z");
        simple.setTimeZone(TimeZone.getTimeZone("GMT"));
        String str = simple.format(timestamp);
        System.out.println(str);
    }

    private void printUrlFreq(Map<String, Integer> urlFreq){
        // sort the Map<url, freq> by freq (value)
        // Refer: https://www.geeksforgeeks.org/sorting-a-hashmap-according-to-values/

        List<Map.Entry<String, Integer>> ordered = new LinkedList<Map.Entry<String, Integer>>(urlFreq.entrySet());
        Collections.sort(ordered, new Comparator<Map.Entry<String, Integer>>() {
            @Override
            // Override the comparator function to sort the map with descending order
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                if (o1.getValue() == o2.getValue())
                    return 0;
                return o1.getValue() > o2.getValue() ? -1 : 1;
            }
        });

        // print the sorted list
        for(Map.Entry<String, Integer> i : ordered){
            System.out.println(i.getKey() + " " + i.getValue());
        }
    }

    /* Convert input: list<String> --> Time&Web Object */
    private TimenWeb[] saveToObj(List<String> list){
        TimenWeb[] timenweb = new TimenWeb[list.size()];
        for (int j = 0; j < list.size(); j++){
            timenweb[j] = new TimenWeb(list.get(j));
            // System.out.println(timenweb[j].date);
            // System.out.println(timenweb[j].website);
        }
        return timenweb;
    }

    public static void main(String[] args) throws Exception{
	// write your code here

        File file = new File("input.txt");
        Scanner sc = new Scanner(file);
        List<String> list = new LinkedList<>();

        /* Save the input to a linked list */
        // int i = 0;
        while(sc.hasNextLine()){
            // System.out.println(sc.nextLine());
            list.add(sc.nextLine());
            // System.out.println(list.get(i++));
        }

        /* New a Main object, call the classify functions */
        Main main = new Main();
        // Convert the input list to a Time&Web object, with variable .date and .website
        TimenWeb[] timenweb = main.saveToObj(list);
        // call the function with Time&Web object array input
        main.Collecturl(timenweb);
    }
}