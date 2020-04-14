package com.johanneslosch.recall.server;

import org.apache.http.client.utils.DateUtils;

import java.util.Date;

public class HandleMessages {
    public static void message(String message) {
        if (message.contains(";")) {
            handleMessage(message);
        }
    }

    private static void handleMessage(String message) {
        String[] part = message.split(";", 25500);
        String msg = part[0];
        String date = part[1];
        String time = part[2];
        String user = message.substring(message.indexOf("from: "));
        System.out.println(String.format("-> %s at: %s: %s from: %s", msg, date, time, user));
    }
//TODO: handle Dates
    /*
    - td - today;
    - tm - tomorrow)
    - nw - next week (in 7 days
    - nm - next month (in 30 days))
     */
    private static String handleDate(String date){
        String finalDate = null;
        if(date.equalsIgnoreCase("td")){
            DateUtils.formatDate(new Date(), String.valueOf(java.util.Calendar.DAY_OF_MONTH));
        }else if(date.equalsIgnoreCase("tm")){

        }else if(date.equalsIgnoreCase("nw")){

        }else if(date.equalsIgnoreCase("nm")){

        }else{
            finalDate = date;
        }
        return finalDate;
    }
}
