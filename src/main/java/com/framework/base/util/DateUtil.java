package com.framework.base.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 日期工具类
 * @author 任文龙
 *
 */
public class DateUtil {
	private static final Log logger = LogFactory.getLog(DateUtil.class);
	private static final String defaultDatePattern = "yyyy-MM-dd";
	private static final String defaultTimePattern = "HH:mm:ss";
	private static final String defaultDTPattern="yyyy-MM-dd HH:mm:ss";
	private static final String defaultDHMPattern="yyyy-MM-dd HH:mm";
	private static final String defaultYMPattern="yyyy-MM";
	private static final String defaultMDHMPattern="MM-dd HH:mm";
	
	private static final int FIRST_DAY_OF_WEEK = Calendar.MONDAY; // 中国周一是一周的第一天
	
	/** 毫秒与秒的转换系数 */
	public static final int  MILLISECOND_TO_SECOND = 1000;
	
	public static String getNowTime(){
		Date now = new Date();
		return format(now,defaultTimePattern);
	}
	
	public static String getNowDateTime(){
		Date now = new Date();
		return format(now,defaultDTPattern);
	}
	
	public static String getDateWithDMH(){
		Date now = new Date();
		return format(now,defaultDHMPattern);
	}
	
	
	public static int getSecondsLag(String time1,String time2){
		int lag=0;
		String[] tArr1=time1.trim().split(":");
		String[] tArr2=time2.trim().split(":");
		int hour1=Integer.parseInt(tArr1[0]);
		int hour2=Integer.parseInt(tArr2[0]);
		int minute1=Integer.parseInt(tArr1[1]);
		int minute2=Integer.parseInt(tArr2[1]);
		int second1=Integer.parseInt(tArr1[2]);
		int second2=Integer.parseInt(tArr2[2]);
		lag=(hour2-hour1)*3600+(minute2-minute1)*60+(second2-second1);
		return lag;
		
	}

	/**
	 * 获得默认的 date pattern
	 */
	public static String getDatePattern() {
		return defaultDatePattern;
	}
	
	public static String getTimePattern() {
		return defaultTimePattern;
	}
	
	public static String getDateTimePattern() {
		return defaultDTPattern;
	}

	/**
	 * 返回预设Format的当前日期字符串
	 */
	public static String getToday() {
		Date today = new Date();
		return format(today);
	}
	
	/**
	 * 转成这种格式的字符串：yyyy-MM，值为当前年月
	 */
	public static String getThisYearMonth() {
		Date today = new Date();
		return format(today,defaultYMPattern);
	}

	/**
	 * 转成这种格式的字符串：yyyy-MM-dd
	 */
	public static String format(Date date) {
		return date == null ? " " : format(date, getDatePattern());
	}
	
	/**
	 *  转成这种格式的字符串:HH:mm:ss
	 */
	public static String formatTime(Date date) {
		return date == null ? " " : format(date, getTimePattern());
	}
	
	/**
	 * 转成这种格式的字符串：yyyy-MM-dd HH:mm:ss
	 */
	public static String formatDateTime(Date date) {
		return date == null ? " " : format(date, getDateTimePattern());
	}

	/**
	 * 使用参数Format格式化Date成字符串
	 */
	public static String format(Date date, String pattern) {
		return date == null ? " " : new SimpleDateFormat(pattern).format(date);
	}
	
	/**
	 * 使用参数Format格式化Date成字符串
	 */
	public static String format(String date, String pattern) {
		return date == null ? " " : new SimpleDateFormat(pattern).format(date);
	}

	/**
	 * 使用预设格式将字符串转为Date:yyyy-MM-dd
	 */
	public static Date parse(String strDate)  {
		strDate=strDate.trim();
		return StringUtils.isBlank(strDate) ? null : parse(strDate, getDatePattern());
	}

	/**
	 * 使用参数Format将字符串转为Date
	 */
	public static Date parse(String strDate, String pattern) {
		pattern=pattern.trim();
		try {
			return StringUtils.isBlank(strDate.trim()) ? null : new SimpleDateFormat(pattern).parse(strDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 在日期上增加数个整月
	 */
	public static Date addMonth(Date date, int n) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.MONTH, n);
		return cal.getTime();
	}
	
	/*
	 *	String today="2014-12-03 18:05:00";
		getBetweenTime(today));
		return map 相差多少天多少小时多少分钟多少秒
	 */
	public static Map<String,Long> getBetweenTime(String start){
		Map<String,Long> lagTime=new HashMap<String,Long>();
		String pattern="yyyy-MM-dd HH:mm:ss";
		Date begin =DateUtil.parse(start, pattern);
		long between = (System.currentTimeMillis() - begin.getTime()) / 1000;// 除以1000是为了转换成秒
		long day = between / (24 * 3600);
		long hour = between % (24 * 3600) / 3600;
		long minute = between % 3600 / 60;
		long second = between%60;
		lagTime.put("between", between);
		lagTime.put("day", day);
		lagTime.put("hour", hour);
		lagTime.put("minute", minute);
		lagTime.put("second", second);
		
		return lagTime;
		
//		System.out.println( "相差" + day1 + "天" + hour1 + "小时" + minute1+ "分" + second1 + "秒");
	}

	public static String getLastDayOfMonth(String year, String month) {
		Calendar cal = Calendar.getInstance();
		// 年
		cal.set(Calendar.YEAR, Integer.parseInt(year));
		// 月，因为Calendar里的月是从0开始，所以要-1
		// cal.set(Calendar.MONTH, Integer.parseInt(month) - 1);
		// 日，设为一号
		cal.set(Calendar.DATE, 1);
		// 月份加一，得到下个月的一号
		cal.add(Calendar.MONTH, 1);
		// 下一个月减一为本月最后一天
		cal.add(Calendar.DATE, -1);
		return String.valueOf(cal.get(Calendar.DAY_OF_MONTH));// 获得月末是几号
	}

	public static Date getDate(String year, String month, String day) throws ParseException {
		String result = year + "- " + (month.length() == 1 ? ("0 " + month) : month) + "- " + (day.length() == 1 ? ("0 " + day) : day);
		return parse(result);
	}
	
	/**
	 * 将日期的字符串，变成日期的long数据
	 * @author xiejiesheng
	 * 2015年4月20日
	 * @param dateStr
	 */
	public static Long getDateLong(String dateStr){
		if(!dateStr.contains(":")){//如果传过来的是日期型，即没有时间的
			return DateUtil.parse(dateStr,defaultDatePattern).getTime();
		}
		return DateUtil.parse(dateStr,defaultDTPattern).getTime();
	}
	
	public static String getDateStr(Long dateTime){
		return format(new Date(dateTime),defaultDTPattern);
	}
	
	public static String getDateDHMStr(Long dateTime){
		return format(new Date(dateTime),defaultDHMPattern);
	}
	
	public static String getDateMDHMStr(Long dateTime){
		return format(new Date(dateTime),defaultMDHMPattern);
	}
	
	
	
	public static int daysBetween(Date early, Date late) { 
	     
        java.util.Calendar calst = java.util.Calendar.getInstance();   
        java.util.Calendar caled = java.util.Calendar.getInstance();   
        calst.setTime(early);   
	    caled.setTime(late);   
	    //设置时间为0时   
	    calst.set(java.util.Calendar.HOUR_OF_DAY, 0);   
	    calst.set(java.util.Calendar.MINUTE, 0);   
	    calst.set(java.util.Calendar.SECOND, 0);   
	    caled.set(java.util.Calendar.HOUR_OF_DAY, 0);   
	    caled.set(java.util.Calendar.MINUTE, 0);   
	    caled.set(java.util.Calendar.SECOND, 0);   
	    //得到两个日期相差的天数   
	    int days = ((int) (caled.getTime().getTime() / 1000)
	    		- (int) (calst.getTime().getTime() / 1000)) / 3600 / 24;   
         
        return days;   
   }  
	
	/**
	 * 
	 * @param strDate
	 * @return
	 */
	public static Date parseDate(String strDate) {
		return parseDate(strDate, null);
	}

	/**
	 * parseDate
	 * 
	 * @param strDate
	 * @param pattern
	 * @return
	 */
	public static Date parseDate(String strDate, String pattern) {
		Date date = null;
		try {
			if (pattern == null) {
				pattern = defaultDatePattern;
			}
			SimpleDateFormat format = new SimpleDateFormat(pattern);
			date = format.parse(strDate);
		} catch (Exception e) {
			logger.error("parseDate error:" + e);
		}
		return date;
	}

	/**
	 * format date
	 * 
	 * @param date
	 * @return
	 */
	public static String formatDate(Date date) {
		return formatDate(date, null);
	}

	/**
	 * format date
	 * 
	 * @param date
	 * @param pattern
	 * @return
	 */
	public static String formatDate(Date date, String pattern) {
		String strDate = null;
		try {
			if (pattern == null) {
				pattern = defaultDatePattern;
			}
			SimpleDateFormat format = new SimpleDateFormat(pattern);
			strDate = format.format(date);
		} catch (Exception e) {
			logger.error("formatDate error:", e);
		}
		return strDate;
	}

	/**
	 * 取得日期：年
	 * 
	 * @param date
	 * @return
	 */
	public static int getYear(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		int year = c.get(Calendar.YEAR);
		return year;
	}

	/**
	 * 取得日期：月
	 * 
	 * @param date
	 * @return
	 */
	public static int getMonth(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		int month = c.get(Calendar.MONTH);
		return month + 1;
	}

	/**
	 * 取得日期：日
	 * 
	 * @param date
	 * @return
	 */
	public static int getDay(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		int da = c.get(Calendar.DAY_OF_MONTH);
		return da;
	}

	/**
	 * 取得当天日期是周几
	 * 
	 * @param date
	 * @return
	 */
	public static int getWeekDay(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		int week_of_year = c.get(Calendar.DAY_OF_WEEK);
		return week_of_year - 1;
	}

	/**
	 * 取得一年的第几周
	 * 
	 * @param date
	 * @return
	 */
	public static int getWeekOfYear(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		int week_of_year = c.get(Calendar.WEEK_OF_YEAR);
		return week_of_year;
	}

	/**
	 * getWeekBeginAndEndDate
	 * 
	 * @param date
	 * @param pattern
	 * @return
	 */
	public static String getWeekBeginAndEndDate(Date date, String pattern) {
		Date monday = getMondayOfWeek(date);
		Date sunday = getSundayOfWeek(date);
		return formatDate(monday, pattern) + " - "
				+ formatDate(sunday, pattern);
	}

	/**
	 * 根据日期取得对应周周一日期
	 * 
	 * @param date
	 * @return
	 */
	public static Date getMondayOfWeek(Date date) {
		Calendar monday = Calendar.getInstance();
		monday.setTime(date);
		monday.setFirstDayOfWeek(FIRST_DAY_OF_WEEK);
		monday.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		return monday.getTime();
	}

	/**
	 * 根据日期取得对应周周日日期
	 * 
	 * @param date
	 * @return
	 */
	public static Date getSundayOfWeek(Date date) {
		Calendar sunday = Calendar.getInstance();
		sunday.setTime(date);
		sunday.setFirstDayOfWeek(FIRST_DAY_OF_WEEK);
		sunday.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
		return sunday.getTime();
	}

	/**
	 * 取得月的剩余天数
	 * 
	 * @param date
	 * @return
	 */
	public static int getRemainDayOfMonth(Date date) {
		int dayOfMonth = getDayOfMonth(date);
		int day = getPassDayOfMonth(date);
		return dayOfMonth - day;
	}

	/**
	 * 取得月已经过的天数
	 * 
	 * @param date
	 * @return
	 */
	public static int getPassDayOfMonth(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		return c.get(Calendar.DAY_OF_MONTH);
	}

	/**
	 * 取得月天数
	 * 
	 * @param date
	 * @return
	 */
	public static int getDayOfMonth(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		return c.getActualMaximum(Calendar.DAY_OF_MONTH);
	}

	/**
	 * 取得月第一天
	 * 
	 * @param date
	 * @return
	 */
	public static Date getFirstDateOfMonth(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.set(Calendar.DAY_OF_MONTH, c.getActualMinimum(Calendar.DAY_OF_MONTH));
		return c.getTime();
	}

	/**
	 * 取得月最后一天
	 * 
	 * @param date
	 * @return
	 */
	public static Date getLastDateOfMonth(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.set(Calendar.DAY_OF_MONTH, c.getActualMaximum(Calendar.DAY_OF_MONTH));
		return c.getTime();
	}

	/**
	 * 取得季度第一天
	 * 
	 * @param date
	 * @return
	 */
	public static Date getFirstDateOfSeason(Date date) {
		return getFirstDateOfMonth(getSeasonDate(date)[0]);
	}

	/**
	 * 取得季度最后一天
	 * 
	 * @param date
	 * @return
	 */
	public static Date getLastDateOfSeason(Date date) {
		return getLastDateOfMonth(getSeasonDate(date)[2]);
	}

	/**
	 * 取得季度天数
	 * 
	 * @param date
	 * @return
	 */
	public static int getDayOfSeason(Date date) {
		int day = 0;
		Date[] seasonDates = getSeasonDate(date);
		for (Date date2 : seasonDates) {
			day += getDayOfMonth(date2);
		}
		return day;
	}

	/**
	 * 取得季度剩余天数
	 * 
	 * @param date
	 * @return
	 */
	public static int getRemainDayOfSeason(Date date) {
		return getDayOfSeason(date) - getPassDayOfSeason(date);
	}

	/**
	 * 取得季度已过天数
	 * 
	 * @param date
	 * @return
	 */
	public static int getPassDayOfSeason(Date date) {
		int day = 0;

		Date[] seasonDates = getSeasonDate(date);

		Calendar c = Calendar.getInstance();
		c.setTime(date);
		int month = c.get(Calendar.MONTH);

		if (month == Calendar.JANUARY || month == Calendar.APRIL
				|| month == Calendar.JULY || month == Calendar.OCTOBER) {// 季度第一个月
			day = getPassDayOfMonth(seasonDates[0]);
		} else if (month == Calendar.FEBRUARY || month == Calendar.MAY
				|| month == Calendar.AUGUST || month == Calendar.NOVEMBER) {// 季度第二个月
			day = getDayOfMonth(seasonDates[0])
					+ getPassDayOfMonth(seasonDates[1]);
		} else if (month == Calendar.MARCH || month == Calendar.JUNE
				|| month == Calendar.SEPTEMBER || month == Calendar.DECEMBER) {// 季度第三个月
			day = getDayOfMonth(seasonDates[0]) + getDayOfMonth(seasonDates[1])
					+ getPassDayOfMonth(seasonDates[2]);
		}
		return day;
	}

	/**
	 * 取得季度月
	 * 
	 * @param date
	 * @return
	 */
	public static Date[] getSeasonDate(Date date) {
		Date[] season = new Date[3];

		Calendar c = Calendar.getInstance();
		c.setTime(date);

		int nSeason = getSeason(date);
		if (nSeason == 1) {// 第一季度
			c.set(Calendar.MONTH, Calendar.JANUARY);
			season[0] = c.getTime();
			c.set(Calendar.MONTH, Calendar.FEBRUARY);
			season[1] = c.getTime();
			c.set(Calendar.MONTH, Calendar.MARCH);
			season[2] = c.getTime();
		} else if (nSeason == 2) {// 第二季度
			c.set(Calendar.MONTH, Calendar.APRIL);
			season[0] = c.getTime();
			c.set(Calendar.MONTH, Calendar.MAY);
			season[1] = c.getTime();
			c.set(Calendar.MONTH, Calendar.JUNE);
			season[2] = c.getTime();
		} else if (nSeason == 3) {// 第三季度
			c.set(Calendar.MONTH, Calendar.JULY);
			season[0] = c.getTime();
			c.set(Calendar.MONTH, Calendar.AUGUST);
			season[1] = c.getTime();
			c.set(Calendar.MONTH, Calendar.SEPTEMBER);
			season[2] = c.getTime();
		} else if (nSeason == 4) {// 第四季度
			c.set(Calendar.MONTH, Calendar.OCTOBER);
			season[0] = c.getTime();
			c.set(Calendar.MONTH, Calendar.NOVEMBER);
			season[1] = c.getTime();
			c.set(Calendar.MONTH, Calendar.DECEMBER);
			season[2] = c.getTime();
		}
		return season;
	}

	/**
	 * 
	 * 1 第一季度 2 第二季度 3 第三季度 4 第四季度
	 * 
	 * @param date
	 * @return
	 */
	public static int getSeason(Date date) {

		int season = 0;

		Calendar c = Calendar.getInstance();
		c.setTime(date);
		int month = c.get(Calendar.MONTH);
		switch (month) {
		case Calendar.JANUARY:
		case Calendar.FEBRUARY:
		case Calendar.MARCH:
			season = 1;
			break;
		case Calendar.APRIL:
		case Calendar.MAY:
		case Calendar.JUNE:
			season = 2;
			break;
		case Calendar.JULY:
		case Calendar.AUGUST:
		case Calendar.SEPTEMBER:
			season = 3;
			break;
		case Calendar.OCTOBER:
		case Calendar.NOVEMBER:
		case Calendar.DECEMBER:
			season = 4;
			break;
		default:
			break;
		}
		return season;
	}
	
	/*****************************************
	 * @功能 计算某年某周的开始日期
	 * @return interger
	 * @throws ParseException
	 ****************************************/
	public static String getYearWeekFirstDay(int yearNum, int weekNum) throws ParseException {

		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, yearNum);
		cal.set(Calendar.WEEK_OF_YEAR, weekNum);
		cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		// 分别取得当前日期的年、月、日
		String tempYear = Integer.toString(yearNum);
		String tempMonth = Integer.toString(cal.get(Calendar.MONTH) + 1);
		String tempDay = Integer.toString(cal.get(Calendar.DATE));
		String tempDate = tempYear + "-" + tempMonth + "-" + tempDay;
		return SetDateFormat(tempDate, defaultDatePattern);
	}
	
	/**
	 * @see 取得指定时间的给定格式()
	 * @return String
	 * @throws ParseException
	 */
	public static String SetDateFormat(String myDate, String strFormat) throws ParseException {

		SimpleDateFormat sdf = new SimpleDateFormat(strFormat);
		String sDate = sdf.format(sdf.parse(myDate));
		System.err.println(sDate);
		return sDate;
	}

	/*****************************************
	 * @功能 计算某年某周的结束日期
	 * @return interger
	 * @throws ParseException
	 ****************************************/
	public static String getYearWeekEndDay(int yearNum, int weekNum) throws ParseException {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, yearNum);
		cal.set(Calendar.WEEK_OF_YEAR, weekNum + 1);
		cal.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
		// 分别取得当前日期的年、月、日
		String tempYear = Integer.toString(yearNum);
		String tempMonth = Integer.toString(cal.get(Calendar.MONTH) + 1);
		String tempDay = Integer.toString(cal.get(Calendar.DATE));
		String tempDate = tempYear + "-" + tempMonth + "-" + tempDay;
		return SetDateFormat(tempDate, defaultDatePattern);
	}
	
	
	public static void main(String[] args) {
		Date date=new Date(System.currentTimeMillis());
		String fm=format(new Date());
		System.out.println(fm);
		fm=formatDateTime(date);
		System.out.println(fm);
		String dateStr="2015-4-30";
		if(!dateStr.contains(":")){
			System.out.println(dateStr);
		}
		System.out.println(DateUtil.getDateLong("2015-4-30"));
		
		
		String strDate = "2015-12-29";

		date = parseDate(strDate);
		
		try {
			System.out.println(formatDate(getSundayOfWeek(parseDate(getYearWeekFirstDay(2015, 53)))));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println(strDate + " 今天是哪一年？" + getYear(date));
		System.out.println(strDate + " 今天是哪个月？" + getMonth(date));
		System.out.println(strDate + " 今天是几号？" + getDay(date));
		System.out.println(strDate + " 今天是周几？" + getWeekDay(date));
		System.out.println(strDate + " 是一年的第几周？" + getWeekOfYear(date));
		System.out.println(strDate + " 所在周起始结束日期？"
				+ getWeekBeginAndEndDate(date, "yyyy年MM月dd日"));
		System.out.println(strDate + " 所在周周一是？"
				+ formatDate(getMondayOfWeek(date)));
		System.out.println(strDate + " 所在周周日是？"
				+ formatDate(getSundayOfWeek(date)));

		System.out.println(strDate + " 当月第一天日期？"
				+ formatDate(getFirstDateOfMonth(date)));
		System.out.println(strDate + " 当月最后一天日期？"
				+ formatDate(getLastDateOfMonth(date)));
		System.out.println(strDate + " 当月天数？" + getDayOfMonth(date));
		System.out.println(strDate + " 当月已过多少天？" + getPassDayOfMonth(date));
		System.out.println(strDate + " 当月剩余多少天？" + getRemainDayOfMonth(date));

		System.out.println(strDate + " 所在季度第一天日期？"
				+ formatDate(getFirstDateOfSeason(date)));
		System.out.println(strDate + " 所在季度最后一天日期？"
				+ formatDate(getLastDateOfSeason(date)));
		System.out.println(strDate + " 所在季度天数？" + getDayOfSeason(date));
		System.out.println(strDate + " 所在季度已过多少天？" + getPassDayOfSeason(date));
		System.out
				.println(strDate + " 所在季度剩余多少天？" + getRemainDayOfSeason(date));
		System.out.println(strDate + " 是第几季度？" + getSeason(date));
		System.out.println(strDate + " 所在季度月份？"
				+ formatDate(getSeasonDate(date)[0], "yyyy年MM月") + "/"
				+ formatDate(getSeasonDate(date)[1], "yyyy年MM月") + "/"
				+ formatDate(getSeasonDate(date)[2], "yyyy年MM月"));
	}
}
