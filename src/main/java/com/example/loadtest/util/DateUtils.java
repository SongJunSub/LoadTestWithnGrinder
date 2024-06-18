package com.example.loadtest.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateUtils {

    private static final DateTimeFormatter INPUT_DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private static final DateTimeFormatter OUTPUT_DATE_FORMAT = DateTimeFormatter.ofPattern("yyyyMMdd");
    private static final DateTimeFormatter DATE_FULL_FORMAT = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");

    public static LocalDateTime convertToStartDate(String date) {
        LocalDate localDate = LocalDate.parse(date, INPUT_DATE_FORMAT);

        String formattedDate = localDate.format(OUTPUT_DATE_FORMAT);

        return LocalDateTime.parse(formattedDate + "000000", DATE_FULL_FORMAT);
    }

    public static LocalDateTime convertToEndDate(String date) {
        LocalDate localDate = LocalDate.parse(date, INPUT_DATE_FORMAT);

        String formattedDate = localDate.format(OUTPUT_DATE_FORMAT);

        return LocalDateTime.parse(formattedDate + "235959", DATE_FULL_FORMAT);
    }

}