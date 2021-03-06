package com.johanneslosch.recall.server;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class HandleMessages {
  public static void message(String message) {
    if (message.contains(";")) {
      try {
        handleMessage(message);
      } catch (ParseException e) {
        e.printStackTrace();
      }
    }
  }

  private static void handleMessage(String message) throws ParseException {
    String[] part = message.split(";", 25500);
    String user =
        message.substring(message.indexOf("-> from:")).replace("-> from:", "");
    String msg = part[0].substring(19);
    String date = part[1];
    String time = part[2].replace("-> from:", "").replace(user, "");
    System.out.println(
        String.format("-> %s at: %s: %s / %s", msg, date, time, user));

    System.out.println(String.format("message: %s by %s time: %sdate: %s", msg, user, time, handleDate(date)));

    try {
      MySQL.MySQLUseDataManager.setData(msg, user, handleDate(date), time);
    } catch (SQLException throwable) {
      throwable.printStackTrace();
    }
  }

  /*
  - td - today;
  - tm - tomorrow)
  - nw - next week (in 7 days
  - nm - next month (in 25 days))
   */
  private static String handleDate(String date) throws ParseException {
    String finalDate = null;
    if (date.equalsIgnoreCase("td")) {
      finalDate = getDate(0);
    } else if (date.equalsIgnoreCase("tm")) {
      finalDate = getDate(1);
    } else if (date.equalsIgnoreCase("nw")) {
      finalDate = getDate(7);
    } else if (date.equalsIgnoreCase("nm")) {
      finalDate = getDate(25);
    } else {
      finalDate = date;
    }
    SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy");
    return finalDate;
  }

  // return Date (in days) in dd.MM.yyyy
  private static String getDate(int days) throws ParseException {
    Date dt = new Date();
    Calendar c = Calendar.getInstance();
    c.setTime(dt);
    c.add(Calendar.DATE, days);
    dt = c.getTime();

    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    String date = sdf.format(c.getTime());

    System.out.println(date);

    return date;
  }
}
