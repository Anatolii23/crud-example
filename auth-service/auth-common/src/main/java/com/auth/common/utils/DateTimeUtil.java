package com.auth.common.utils;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;

/**
 * Common DateTime util.
 *
 * @author Anatolii Hamza
 */
public final class DateTimeUtil {

    public static final String LOCAL_DATE_TIME_PATTERN = "yyyy-MM-dd HH:mm";
    public static final String LOCAL_DATE_TIME_ISO_PATTERN = "yyyy-MM-dd'T'HH:mm:ss'Z'";
    public static final String LOCAL_DATE_TIME_PATTERN_WITH_SECONDS = "yyyy-MM-dd HH:mm:ss";
    public static final String LOCAL_DATE_PATTERN = "yyyy-MM-dd";
    public static final String ZONED_DATE_TIME_PATTERN = "yyyy-MM-dd'T'HH:mm:ssX";

    private DateTimeUtil() {
    }

    public static LocalDateTime nowLocalDateTime() {
        return LocalDateTime.now(ZoneOffset.UTC);
    }

    public static Date toDate(LocalDateTime localDateTime) {
        return Date.from(localDateTime.atZone(ZoneOffset.UTC).toInstant());
    }

    public static LocalDateTime toLocalDateTime(Date date) {
        return LocalDateTime.ofInstant(date.toInstant(), ZoneOffset.UTC);
    }

    public static Long convertMillisecondsToSeconds(Long milliseconds) {
        return Duration.ofMillis(milliseconds).toSeconds();
    }
}
