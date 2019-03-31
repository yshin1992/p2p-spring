package org.p2p.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 日期工具类
 * @author yanshuai
 *
 */
public class DateUtil {
	
	private static Logger logger = LoggerFactory.getLogger(DateUtil.class);

    /**
     * 私有构造方法
     * */
    private DateUtil() {
    }

    /**
     * 日期Format字符串枚举类
     * */
    public enum DateFormatStr {

        /**
         * yyyy-MM-dd
         * */
        D_FMT_CH("yyyy-MM-dd"),

        /**
         * yyyyMMdd
         * */
        D_FMT_EN("yyyyMMdd"),

        /**
         * yyyyMMddHH:mm:ss
         * */
        DT_FMT_EN("yyyyMMdd HH:mm:ss"),

        /**
         * yyyy-MM-dd hh:mm:ss
         * */
        DT_FMT_CH("yyyy-MM-dd HH:mm:ss"),

        /**
         * yyyy年mm月dd日 hh:mm:ss
         */
        DAT_FMT_CH("yyyy年MM月dd日  HH:mm:ss");

        private final String value;

        /**
         * @param fmtStr
         *            模式字符串
         */
        DateFormatStr(String fmtStr) {
            this.value = fmtStr;
        }

        /**
         * @return 模式字符串
         */
        public String getValue() {
            return value;
        }
    }

    /**
     * 返回枚举模式
     * 
     * @param pattern
     *            日期字符串模式
     * @return 举模式
     */
    public static DateFormatStr getDateFormtByPattern(String pattern) {
        if (StringUtils.isEmpty(pattern)) {
            return DateFormatStr.D_FMT_CH;
        } else if (StringUtils.equalsIgnoreCase(
                DateFormatStr.D_FMT_CH.value, pattern)) {
            return DateFormatStr.D_FMT_CH;
        } else if (StringUtils.equalsIgnoreCase(
                DateFormatStr.D_FMT_EN.value, pattern)) {
            return DateFormatStr.D_FMT_EN;
        } else if (StringUtils.equalsIgnoreCase(
                DateFormatStr.DT_FMT_CH.value, pattern)) {
            return DateFormatStr.DT_FMT_CH;
        } else if (StringUtils.equalsIgnoreCase(
                DateFormatStr.DT_FMT_EN.value, pattern)) {
            return DateFormatStr.DT_FMT_EN;
        } else if (StringUtils.equalsIgnoreCase(
                DateFormatStr.DAT_FMT_CH.value, pattern)) {
            return DateFormatStr.DAT_FMT_CH;
        } else {
            return DateFormatStr.D_FMT_CH;
        }
    }

    /**
     * 字符串格式日期转java.util.Date 转换失败返回null
     * 
     * @param dateStr
     *            字符串格式日期对象
     * @param dateFormat
     *            字符串格式日期的格式
     * @return 转换后的日期对象
     * */
    public static Date stringToDate(String dateStr, DateFormatStr dateFormat) {

        SimpleDateFormat df = new SimpleDateFormat(dateFormat.value);
        try {
            return df.parse(dateStr);
        } catch (ParseException e) {
           logger.error("RlsDateUtil.stringToDate(" + dateStr + ","
                        + dateFormat + ")", e);
            return null;
        }
    }

    /**
     * <p>
     * 日期格式化<br/>
     * 日期对象=null则返回空字符串
     * 
     * @param date
     *            日期
     * @param dateFormat
     *            日期Format字符串枚举类
     * @return 格式化后的日期字符串
     * */
    public static String dateFormat(Date date, DateFormatStr dateFormat) {
        if (date == null) {
            return StringUtils.EMPTY;
        }
        SimpleDateFormat df = new SimpleDateFormat(dateFormat.value);
        return df.format(date);
    }

    /**
     * <p>
     * 日期加减<br/>
     * 对指定日期进行天数加减
     * 
     * @param date
     *            指定日期
     * @param days
     *            指定天数
     * @return 计算后的日期
     * */
    public static Date addDays(Date date, int days) {

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, days);
        return calendar.getTime();
    }

    /**
     * <p>
     * 日期加减<br/>
     * 对当前日期进行年的加减
     * 
     * @param date
     *            指定日期
     * @param years
     *            加减年数
     * @return 加减后的日期
     * */
    public static Date addYear(Date date, int years) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.YEAR, years);
        return calendar.getTime();
    }

    /**
     * <p>
     * 日期加减<br/>
     * 对指定日期进行月份加减
     * 
     * @param date
     *            指定日期
     * @param months
     *            指定月数
     * @return 计算后的日期
     * */
    public static Date addMonth(Date date, int months) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH, months);
        return calendar.getTime();
    }

    /**
     * @return 返回当前应用服务器的系统时间
     * */
    public static Date systemDate() {
        return new Date(System.currentTimeMillis());
    }

    /**
     * 日期比较
     * 
     * @param dt1
     *            比较日期
     * @param dt2
     *            比较日期
     * @param fmt
     *            比较格式（比较粒度）
     * @return dt1 > dt2 返回：1 dt2 > dt1 返回 ：-1 dt1 = dt2 返回：0
     * */
    public static int dateCompare(Date dt1, Date dt2, DateFormatStr fmt) {
        int flag = 0;
        if (dt1 == null && dt2 != null) {
            flag = -1;
        } else if (dt1 != null && dt2 == null) {
            flag = 1;
        } else if (dt1 == null && dt2 == null) {
            flag = 0;
        } else {
            String dt1Str = dateFormat(dt1, fmt);
            String dt2Str = dateFormat(dt2, fmt);
            flag = dt1Str.compareTo(dt2Str);
        }
        return flag;
    }

    /**
     * 年龄计算
     * 
     * @param brithDay
     *            出生日期
     * @return 年龄
     */
    public static int calculateAge(Date brithDay) {
        int age = 0;
        Date sysDate = systemDate();
        Calendar c1 = Calendar.getInstance();
        c1.setTime(sysDate);
        Calendar c2 = Calendar.getInstance();
        c2.setTime(brithDay);

        int r1 = c1.get(Calendar.YEAR);
        int r2 = c2.get(Calendar.YEAR);
        age = r1 - r2;

        int sysMonth = c1.get(Calendar.MONTH);
        int briMonth = c2.get(Calendar.MONTH);
        if (briMonth > sysMonth) {
            age--;
        } else if (briMonth == sysMonth) {
            int sysday = c1.get(Calendar.DAY_OF_MONTH);
            int briday = c2.get(Calendar.DAY_OF_MONTH);
            if (sysday < briday) {
                age--;
            }
        }
        return age;
    }

    /**
     * 计算2个日期间的总天数包括开始日和结束日
     * 
     * @param from
     *            开始日
     * @param to
     *            结束日
     * @return 计算结果
     */
    public static int calculateDays(Date from, Date to) {
        int diffDays = dateDiff(from, to);
        int totalDays = 0;

        if (diffDays == 0) {
            totalDays = 1;
        } else {
            totalDays = diffDays + 1;
        }
        return totalDays;
    }

    /**
     * 返回2个日期之间的相隔自然天数
     * 
     * @param from
     *            日期对象1
     * @param to
     *            日期对象2
     * @return dfValue 差分值
     */
    public static int dateDiff(Date from, Date to) {
        int dfValue = 0;
        String dt1str = dateFormat(from, DateFormatStr.D_FMT_EN);
        String dt2str = dateFormat(to, DateFormatStr.D_FMT_EN);
        Date newDate1 = stringToDate(dt1str, DateFormatStr.D_FMT_EN);
        Date newDate2 = stringToDate(dt2str, DateFormatStr.D_FMT_EN);
        Calendar cal = Calendar.getInstance();
        cal.setTime(newDate1);
        long time1 = cal.getTimeInMillis();
        cal.setTime(newDate2);
        long time2 = cal.getTimeInMillis();
        long between_days = (time2 - time1) / (1000 * 3600 * 24);
        dfValue = Integer.parseInt(String.valueOf(between_days));

        return dfValue;
    }

    /**
     * 判断指定的日期是否是周末
     * 
     * @param date
     * @return true:周末
     */
    public static boolean isWeekend(Date date) {
        boolean iswkd = false;
        Calendar cld = Calendar.getInstance();
        cld.setTime(date);
        int day = cld.get(Calendar.DAY_OF_WEEK);
        if (day == Calendar.SUNDAY || day == Calendar.SATURDAY) {
            iswkd = true;
        }
        return iswkd;
    }

    /**
     * <p>
     * 返回2个日期间不含周末的自然天数
     * </p>
     * 仅不含周末。包括开始日和结束日
     * 
     * @param from
     *            开始日期
     * @param to
     *            结束日期
     * @return totalDay 计算结果
     */
    public static int dateDiffNoWeekend(Date from, Date to) {

        // 间隔自然天数
        int diffDay = dateDiff(from, to);
        int totalDay = 0;
        if (diffDay == 0) {
            totalDay = 1;
        } else {
            totalDay = diffDay + 1;
        }

        for (int start = 1; start < diffDay; start++) {
            Date date = addDays(from, start);
            if (isWeekend(date)) {
                totalDay--;
            }
        }

        // 开始和结束日是同一天
        if (diffDay == 0) {
            if (isWeekend(from)) {
                totalDay -= 1;
            }
        } else {
            if (isWeekend(from)) {
                totalDay -= 1;
            }
            if (isWeekend(to)) {
                totalDay -= 1;
            }
        }

        return totalDay;
    }


}
