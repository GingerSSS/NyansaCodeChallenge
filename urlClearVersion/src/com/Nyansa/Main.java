package com.Nyansa;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/* Time Complexity is O(n) - details please see github, README */

public class Main {

    /* Classification */
    public void Collecturl (TimenWeb[] timenweb) {
        Map<Integer, Map<String, Integer>> dateMap = new HashMap<>();
        for(TimenWeb item : timenweb){
            if(!dateMap.containsKey(item.date)){
                dateMap.put(item.date, new HashMap<>());
            }

            Map<String, Integer> urlFreq = dateMap.get(item.date);
            if(!urlFreq.containsKey(item.website)){
                urlFreq.put(item.website, 0);
            }

            urlFreq.put(item.website, urlFreq.get(item.website) + 1);
        }

        Object[] keySet = dateMap.keySet().toArray();
        Arrays.sort(keySet);

        for(Object d : keySet){
            printTheDate((int) d);
            printUrlFreq(dateMap.get(d));
        }
    }

    /* Help Method: print the date */
    private void printTheDate(int d){
        long timestamp = (long) d * 24 * 60 * 60 * 1000;
        DateFormat simple = new SimpleDateFormat("MM/dd/yyyy z");
        simple.setTimeZone(TimeZone.getTimeZone("GMT"));
        String str = simple.format(timestamp);
        System.out.println(str);
    }

    /* Help Method: print the url & freq */
    private void printUrlFreq(Map<String, Integer> urlFreq){
        List<Map.Entry<String, Integer>> ordered = new LinkedList<Map.Entry<String, Integer>>(urlFreq.entrySet());
        Collections.sort(ordered, new Comparator<Map.Entry<String, Integer>>() {
            @Override
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                if (o1.getValue() == o2.getValue())
                    return 0;
                return o1.getValue() > o2.getValue() ? -1 : 1;
            }
        });

        for(Map.Entry<String, Integer> i : ordered){
            System.out.println(i.getKey() + " " + i.getValue());
        }
    }

    /* Convert input: list<String> --> Time&Web Object */
    private TimenWeb[] saveToObj(List<String> list){
        TimenWeb[] timenweb = new TimenWeb[list.size()];
        for (int j = 0; j < list.size(); j++){
            timenweb[j] = new TimenWeb(list.get(j));
        }
        return timenweb;
    }

    public static void main(String[] args) throws Exception {
        File file = new File("input.txt");
        Scanner sc = new Scanner(file);
        List<String> list = new LinkedList<>();

        while(sc.hasNextLine()){
            list.add(sc.nextLine());
        }

        Main main = new Main();
        TimenWeb[] timenweb = main.saveToObj(list);
        main.Collecturl(timenweb);
    }
}