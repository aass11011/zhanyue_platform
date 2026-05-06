package com.zym.fastplatform.framework.utils;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;

/**
 * 日期时间工具类
 * 提供日期时间处理的常用方法
 */
public class DateUtil {

    // ==================== 常量定义 ====================

    /**
     * 日期格式：yyyy-MM-dd
     */
    public static final String DATE_PATTERN = "yyyy-MM-dd";

    /**
     * 时间格式：HH:mm:ss
     */
    public static final String TIME_PATTERN = "HH:mm:ss";

    /**
     * 日期时间格式：yyyy-MM-dd HH:mm:ss
     */
    public static final String DATETIME_PATTERN = "yyyy-MM-dd HH:mm:ss";

    /**
     * 日期格式：yyyy/MM/dd
     */
    public static final String DATE_PATTERN_SLASH = "yyyy/MM/dd";

    /**
     * 日期时间格式：yyyy/MM/dd HH:mm:ss
     */
    public static final String DATETIME_PATTERN_SLASH = "yyyy/MM/dd HH:mm:ss";

    /**
     * 日期格式：yyyyMMdd
     */
    public static final String DATE_PATTERN_COMPACT = "yyyyMMdd";

    /**
     * 日期时间格式：yyyyMMddHHmmss
     */
    public static final String DATETIME_PATTERN_COMPACT = "yyyyMMddHHmmss";

    // ==================== 日期时间格式化器 ====================

    /**
     * 日期格式化器：yyyy-MM-dd
     */
    public static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern(DATE_PATTERN);

    /**
     * 时间格式化器：HH:mm:ss
     */
    public static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern(TIME_PATTERN);

    /**
     * 日期时间格式化器：yyyy-MM-dd HH:mm:ss
     */
    public static final DateTimeFormatter DATETIME_FORMATTER = DateTimeFormatter.ofPattern(DATETIME_PATTERN);

    /**
     * 日期格式化器：yyyy/MM/dd
     */
    public static final DateTimeFormatter DATE_FORMATTER_SLASH = DateTimeFormatter.ofPattern(DATE_PATTERN_SLASH);

    /**
     * 日期时间格式化器：yyyy/MM/dd HH:mm:ss
     */
    public static final DateTimeFormatter DATETIME_FORMATTER_SLASH = DateTimeFormatter.ofPattern(DATETIME_PATTERN_SLASH);

    /**
     * 日期格式化器：yyyyMMdd
     */
    public static final DateTimeFormatter DATE_FORMATTER_COMPACT = DateTimeFormatter.ofPattern(DATE_PATTERN_COMPACT);

    /**
     * 日期时间格式化器：yyyyMMddHHmmss
     */
    public static final DateTimeFormatter DATETIME_FORMATTER_COMPACT = DateTimeFormatter.ofPattern(DATETIME_PATTERN_COMPACT);

    /**
     * 私有构造方法，防止实例化
     */
    private DateUtil() {
        throw new AssertionError("DateUtils should not be instantiated");
    }



    // ==================== 字符串解析方法 ====================

    /**
     * 解析字符串为LocalDate（yyyy-MM-dd）
     *
     * @param dateStr 日期字符串
     * @return LocalDate对象
     * @throws DateTimeParseException 如果解析失败
     */
    public static LocalDate parseDate(String dateStr) {
        return parseDate(dateStr, DATE_FORMATTER);
    }

    /**
     * 解析字符串为LocalDateTime（yyyy-MM-dd HH:mm:ss）
     *
     * @param dateTimeStr 日期时间字符串
     * @return LocalDateTime对象
     * @throws DateTimeParseException 如果解析失败
     */
    public static LocalDateTime parseDateTime(String dateTimeStr) {
        return parseDateTime(dateTimeStr, DATETIME_FORMATTER);
    }

    /**
     * 解析字符串为LocalDate
     *
     * @param dateStr 日期字符串
     * @param formatter 格式化器
     * @return LocalDate对象
     * @throws DateTimeParseException 如果解析失败
     */
    public static LocalDate parseDate(String dateStr, DateTimeFormatter formatter) {
        if (dateStr == null || formatter == null) {
            return null;
        }
        return LocalDate.parse(dateStr, formatter);
    }

    /**
     * 解析字符串为LocalDate
     *
     * @param dateStr 日期字符串
     * @param pattern 日期格式
     * @return LocalDate对象
     * @throws DateTimeParseException 如果解析失败
     */
    public static LocalDate parseDate(String dateStr, String pattern) {
        if (dateStr == null || pattern == null) {
            return null;
        }
        return LocalDate.parse(dateStr, DateTimeFormatter.ofPattern(pattern));
    }

    /**
     * 解析字符串为LocalDateTime
     *
     * @param dateTimeStr 日期时间字符串
     * @param formatter 格式化器
     * @return LocalDateTime对象
     * @throws DateTimeParseException 如果解析失败
     */
    public static LocalDateTime parseDateTime(String dateTimeStr, DateTimeFormatter formatter) {
        if (dateTimeStr == null || formatter == null) {
            return null;
        }
        return LocalDateTime.parse(dateTimeStr, formatter);
    }

    /**
     * 解析字符串为LocalDateTime
     *
     * @param dateTimeStr 日期时间字符串
     * @param pattern 日期时间格式
     * @return LocalDateTime对象
     * @throws DateTimeParseException 如果解析失败
     */
    public static LocalDateTime parseDateTime(String dateTimeStr, String pattern) {
        if (dateTimeStr == null || pattern == null) {
            return null;
        }
        return LocalDateTime.parse(dateTimeStr, DateTimeFormatter.ofPattern(pattern));
    }

    // ==================== 日期时间计算方法 ====================

    /**
     * 计算两个日期之间的天数差
     *
     * @param startDate 开始日期
     * @param endDate 结束日期
     * @return 天数差
     */
    public static long daysBetween(LocalDate startDate, LocalDate endDate) {
        if (startDate == null || endDate == null) {
            return 0;
        }
        return ChronoUnit.DAYS.between(startDate, endDate);
    }

    /**
     * 计算两个日期时间之间的分钟差
     *
     * @param startDateTime 开始日期时间
     * @param endDateTime 结束日期时间
     * @return 分钟差
     */
    public static long minutesBetween(LocalDateTime startDateTime, LocalDateTime endDateTime) {
        if (startDateTime == null || endDateTime == null) {
            return 0;
        }
        return ChronoUnit.MINUTES.between(startDateTime, endDateTime);
    }

    /**
     * 计算两个日期时间之间的秒数差
     *
     * @param startDateTime 开始日期时间
     * @param endDateTime 结束日期时间
     * @return 秒数差
     */
    public static long secondsBetween(LocalDateTime startDateTime, LocalDateTime endDateTime) {
        if (startDateTime == null || endDateTime == null) {
            return 0;
        }
        return ChronoUnit.SECONDS.between(startDateTime, endDateTime);
    }


    // ==================== 日期时间判断方法 ====================

    /**
     * 判断是否是工作日（周一至周五）
     *
     * @param date 日期
     * @return 是否是工作日
     */
    public static boolean isWeekday(LocalDate date) {
        if (date == null) {
            return false;
        }
        DayOfWeek dayOfWeek = date.getDayOfWeek();
        return dayOfWeek != DayOfWeek.SATURDAY && dayOfWeek != DayOfWeek.SUNDAY;
    }

    /**
     * 判断是否是周末（周六或周日）
     *
     * @param date 日期
     * @return 是否是周末
     */
    public static boolean isWeekend(LocalDate date) {
        if (date == null) {
            return false;
        }
        DayOfWeek dayOfWeek = date.getDayOfWeek();
        return dayOfWeek == DayOfWeek.SATURDAY || dayOfWeek == DayOfWeek.SUNDAY;
    }
}