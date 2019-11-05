package com.Nyansa;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.*;
import java.util.Collections;
import java.util.stream.Stream;

public class Main {
    public void Collecturl (List<String> list) {
        int n = list.size();
        TimenWeb[] timenweb = new TimenWeb[n];
        for (int i = 0; i < n; i++){
            timenweb[i] = new TimenWeb(list.get(i));
            // System.out.println(timenweb[i].date);
            // System.out.println(timenweb[i].website);
        }

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
            // update the url freq
            urlFreq.put(item.website, urlFreq.get(item.website) + 1);
        }

        // Sort the keySet in the map, ordered by date from smaller one
        Object[] keySet = map.keySet().toArray();
        Arrays.sort(keySet);
        for(Object d : keySet){
            // print the date
            printTheDate((int) d);

            // sort the Map<url, freq> by value
//            Map<String, Integer> mapFreq = map.get(d);
//            MyComparator sortByValue = new MyComparator(mapFreq);
//            TreeMap<String, Integer> sortedMap = new TreeMap<>(sortByValue);
//            sortedMap.putAll(mapFreq);

//            Map<String, Integer> mapFreq = map.get(d);
//            Stream<Map.Entry<String, Integer>> sorted =
//                    mapFreq.entrySet().stream()
//                            .sorted(Collections.reverseOrder(Map.Entry.comparingByValue()));

            // print the url and its freq
//            printUrlFreq(sorted);
            printUrlFreq(map.get(d));
        }
    }
    private void printTheDate(int d){
//        System.out.println(d);
        long timestamp = (long) d * 24 * 60 * 60 * 1000;
        DateFormat simple = new SimpleDateFormat("MM/dd/yyyy");
        simple.setTimeZone(TimeZone.getTimeZone("GMT"));
        String dateString = simple.format(timestamp);
        System.out.println(dateString + " GMT");
    }
    private void printUrlFreq(Map<String, Integer> urlFreq){
        for(String str : urlFreq.keySet()){
            int freq = urlFreq.get(str);
            System.out.println(str + " " + freq);
        }
    }
    public static void main(String[] args) throws Exception{
	// write your code here

        File file = new File("input.txt");
        Scanner sc = new Scanner(file);
        List<String> list = new LinkedList<>();
        int i = 0;
        while(sc.hasNextLine()){
            // System.out.println(sc.nextLine());
            list.add(sc.nextLine());
            // System.out.println(list.get(i++));
        }
        // System.out.println(list.size());
        Main main = new Main();
        main.Collecturl(list);
    }
}
//class MyComparator implements Comparator<Integer> {
//    Map<String, Integer> map;
//    public MyComparator(Map<String, Integer> map){
//        this.map = map;
//    }
//    @Override
//    public int compare(String m1, String m2){
//        if(map.get(m1) == map.get(m2))
//            return 0;
//        return map.get(m1) > map.get(m2) ? -1 : 1;
//    }
//}