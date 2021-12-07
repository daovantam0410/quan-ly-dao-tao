package com.daovantam.quanlydaotao.utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TimeUtil {

    public static final String PATTERN_LOCAL_DATE = "dd-MM-yyyy";
    public static final String PATTERN_LOCAL_DATE_TIME = "dd-MM-yyyy hh:mm:ss";

    public static String convertToString(LocalDate localDate){
        return localDate.format(DateTimeFormatter.ofPattern(PATTERN_LOCAL_DATE));
    }

    public static String convertToString(LocalDateTime localDateTime){
        return localDateTime.format(DateTimeFormatter.ofPattern(PATTERN_LOCAL_DATE_TIME));
    }

    //Convert Date -> LocalDate
    public static LocalDate convertToLocalDate(String date){
        return LocalDate.parse(date, DateTimeFormatter.ofPattern(PATTERN_LOCAL_DATE));
    }
}
