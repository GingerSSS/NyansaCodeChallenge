# NyansaCodeChallenge
This is the code challenge.

I provide two versions:
  1. urlClearVersion - A clear version of code, with less comment
  2. Collecturl - A full version of code, with comment in each step, method and helper method

The idea is: 
(assume the line of input data is n, happened on m days, max urls/day is p)

  1. read the input from the file
  2. scan the input by line and store in a linked list                                - O(n)
  3. save the input from list to an object (TimenWeb) to make the sturcture clear     - O(n)
     also simple process the timestamp to 'date' (omit time detail)
  4. call the function with an array of object (TimenWeb) as input, print the result  - O(n)
    (1) map the url with date, also map the url hit freq with url(on that date)
        Map< date, Map<url, freq> >, the outer map named dateMap, the inner map named urlFreq
    (2) for each item in the TimenWeb[] - O(n)
        a. check if the map contains the date. If not, make a new entry
        b. check if the url&freq map contains the web url. If not, make a new entry
        c. update the freq in url&freq map, update value in map.
    (3) sort the map and print
        a. sort the dateMap by date from early to late, (total m entry, array sort) - O(mlogm)
        b. print the date                                                           - O(m)
        c. print the url & freq
           (a) sort the urlFreq map by freq(value), in descending order             - O(plogp)
           (b) print the sorted map                                                 - O(mp)
           
From the requirement I know the m,p are much smaller than n, then the total Time Complexity is O(n)



# Requirement
Problem:
You’re given an input file. Each line consists of a timestamp (unix epoch in seconds) and a url separated by ‘|’ (pipe operator). The entries are not in any chronological order. Your task is to produce a daily summarized report on url hit count, organized daily (mm/dd/yyyy GMT) with the earliest date appearing first. For each day, you should display the number of times each url is visited in the order of highest hit count to lowest count. Your program should take in one command line argument: input file name. The output should be printed to stdout. You can assume that the cardinality (i.e. number of distinct values) of hit count values and the number of days are much smaller than the number of unique URLs. You may also assume that number of unique URLs can fit in memory, but not necessarily the entire file.

input.txt
1407564301|www.nba.com
1407478021|www.facebook.com
1407478022|www.facebook.com
1407481200|news.ycombinator.com
1407478028|www.google.com
1407564301|sports.yahoo.com
1407564300|www.cnn.com
1407564300|www.nba.com
1407564300|www.nba.com
1407564301|sports.yahoo.com
1407478022|www.google.com
1407648022|www.twitter.com

Output
08/08/2014 GMT
www.facebook.com 2
www.google.com 2
news.ycombinator.com 1
08/09/2014 GMT
www.nba.com 3
sports.yahoo.com 2
www.cnn.com 1
08/10/2014 GMT
www.twitter.com 1

Correctness, efficiency (speed and memory) and code cleanliness will be evaluated. Please provide a complexity analysis in Big-O notation for your program along with your source. 
