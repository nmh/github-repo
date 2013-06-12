package com.heliocentris.rms.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

/**
 * Note:
 * <ul>
 * <li>if you have the <code>Date</code>, it is the absolute time and does not have and should not have any <code>TimeZone</code> information.
 * <li>if you have the <code>String</code> represents the time, you will need the <code>TimeZone</code> from server or your local to convert to the
 * <code>Date</code>.
 * <li>if you want the <code>String</code> represents the time, you will need the <code>TimeZone</code> from server or your local to convert to the
 * <code>String</code>.
 * <li>the default <code>TimeZone</code> is always the <code>TimeZone</code> where the server is - the server time.
 * </ul>
 * <br>
 * 
 * @see Date
 * @see Locale
 * @see TimeZone
 * 
 * @author Nguyen Tuan Thien
 * 
 */
public class DateTime
{
   public static final String DEFAULT_DATE_PATTERN = "yyyy-MM-dd";
   public static final String DEFAULT_TIME_PATTERN = "HH:mm:ss";
   public static final String DEFAULT_DATE_TIME_PATTERN = "yyyy-MM-dd HH:mm:ss";
   
   /**
    * The default <code>DateFormat</code> to use, if parse date string in server time (TimeZone.getDefault()).
    */
   public static final DateFormat DEFAULT_DATE_FORMAT = new SimpleDateFormat(DEFAULT_DATE_PATTERN);
   
   /**
    * The default <code>DateFormat</code> to use, if parse time string in server time (TimeZone.getDefault()).
    */
   public static final DateFormat DEFAULT_TIME_FORMAT = new SimpleDateFormat(DEFAULT_TIME_PATTERN);
   /**
    * The default <code>DateFormat</code> to use, if parse date/time string in server time (TimeZone.getDefault()).
    */
   public static final DateFormat DEFAULT_DATE_TIME_FORMAT = new SimpleDateFormat(DEFAULT_DATE_TIME_PATTERN);

   /**
    * 
    * @param date
    * @param pattern
    * @param from
    * @param to
    * @return
    * @throws ParseException
    */
   public static Date convert(Date date, String pattern, TimeZone from, TimeZone to) throws ParseException
   {
      return parse(format(date, pattern, from), pattern, to);
   }

   /**
    * 
    * @param date
    *           the <code>Date</code> to be convert.
    * @param from
    *           the <code>TimeZone</code> to be convert from.
    * @param to
    *           the <code>TimeZone</code> to be convert to.
    * @return the <code>Date</code> represents the date and time at the <code>TimeZone</code> to.
    * @throws ParseException
    */
   public static Date convert(Date date, TimeZone from, TimeZone to) throws ParseException
   {
      return parse(format(date, DEFAULT_DATE_TIME_PATTERN, from), to);
   }

   /**
    * 
    * @param string
    * @param pattern
    * @param from
    * @param to
    * @return
    * @throws ParseException
    */
   public static String convert(String string, String pattern, TimeZone from, TimeZone to) throws ParseException
   {
      return format(parse(string, pattern, from), pattern, to);
   }

   /**
    * 
    * @param string
    * @param from
    * @param to
    * @return
    * @throws ParseException
    */
   public static String convert(String string, TimeZone from, TimeZone to) throws ParseException
   {
      return convert(string, DEFAULT_DATE_TIME_PATTERN, from, to);
   }

   /**
    * Format the <code>Date</code> with DateTime.DEFAULT_DATE_TIME_PATTERN, <code>Locale.getDefault()</code> and <code>TimeZone.getDefault()</code>.<p>
    * Usefully to convert the given <code>Date</code> to the server time represents the by the returned <code>String</code>.
    * 
    * @param date the <code>Date</code> to be formatted as string.
    * @return string represents the <code>Date</code> at the <code>TimeZone.getDefault()</code>.
    */
   public static String format(Date date)
   {
      return DEFAULT_DATE_TIME_FORMAT.format(date);
   }

   /**
    * Format the <code>Date</code> to the date/time string using <code>DateFormat</code> at the <code>TimeZone</code>. 
    * @param date the <code>Date</code> to be formatted.
    * @param dateFormat the <code>DateFormat</code>.
    * @param timeZone the <code>TimeZone</code>. If timeZone is null, the TimeZone.getDefault() will be used.
    * @return the formatted time string.
    * @throws NullPointerException, if dateFormat is null.
    *          
    */
   public static String format(Date date, DateFormat dateFormat, TimeZone timeZone)
   {
      dateFormat.getCalendar().setTimeZone(timeZone == null ? TimeZone.getDefault() : timeZone);
      return dateFormat.format(date);
   }

   /**
    * Format the <code>Date</code> with DateTime.DEFAULT_DATE_PATTERN, <code>Locale</code> and <code>TimeZone.getDefault()</code>. Usefully to convert the given
    * <code>Date</code> to the server time represents by the returned <code>String</code>.
    * 
    * @param date
    * @param locale
    * @return
    */
   public static String format(Date date, Locale locale)
   {
      return format(date, DEFAULT_DATE_TIME_PATTERN, locale);
   }

   /**
    * 
    * @param date
    * @param locale
    * @param timeZone
    * @return
    */
   public static String format(Date date, Locale locale, TimeZone timeZone)
   {
      return format(date, DEFAULT_DATE_TIME_PATTERN, locale, timeZone);
   }

   /**
    * 
    * @param date
    * @param pattern
    * @return
    */
   public static String format(Date date, String pattern)
   {
      return format(date, pattern, Locale.getDefault());
   }

   /**
    * 
    * @param date
    * @param pattern
    * @param locale
    * @return
    */
   public static String format(Date date, String pattern, Locale locale)
   {
      return format(date, new SimpleDateFormat(pattern == null ? DEFAULT_DATE_TIME_PATTERN : pattern, locale == null ? Locale.getDefault() : locale), TimeZone.getDefault());
   }

   /**
    * 
    * @param date
    * @param pattern
    * @param locale
    * @param timeZone
    * @return
    */
   public static String format(Date date, String pattern, Locale locale, TimeZone timeZone)
   {
      return format(date, new SimpleDateFormat(pattern == null ? DEFAULT_DATE_TIME_PATTERN : pattern, locale == null ? Locale.getDefault() : locale), timeZone);
   }

   /**
    * 
    * @param date
    * @param pattern
    * @param timeZone
    * @return
    */
   public static String format(Date date, String pattern, TimeZone timeZone)
   {
      return format(date, pattern, Locale.getDefault(), timeZone);
   }

   /**
    * 
    * @param date
    * @param timeZone
    * @return
    */
   public static String format(Date date, TimeZone timeZone)
   {
      return format(date, DEFAULT_DATE_TIME_FORMAT, timeZone);
   }

   /**
    * Format the <code>Date</code> with DateTime.DEFAULT_DATE_PATTERN, <code>Locale.getDefault()</code> and <code>TimeZone.getDefault()</code>. Usefully to
    * convert the given <code>Date</code> to the server time represents the by the returned <code>String</code>.
    * 
    * @param date
    * @return string represents the date at the <code>TimeZone.getDefault()</code>.
    */
   public static String formatDate(Date date)
   {
      return DEFAULT_DATE_FORMAT.format(date);
   }

   /**
    * Format the <code>Date</code> with DateTime.DEFAULT_TIME_PATTERN, <code>Locale.getDefault()</code> and <code>TimeZone.getDefault()</code>. Usefully to
    * convert the given <code>Date</code> to the server time represents the by the returned <code>String</code>.
    * 
    * @param date
    * @return string represents the date at the <code>TimeZone.getDefault()</code>.
    */
   public static String formatTime(Date date)
   {
      return DEFAULT_TIME_FORMAT.format(date);
   }


   /**
    * Parse the date/time string and return as <code>Date</code>.<p>
    * The format is <code>DEFAULT_DATE_TIME_FORMAT</code>.  
    * The time zone is the <code>TimeZone.getDefault()</code>.
    * @param string the date/time string.
    * @return the <code>Date</code> parsed from string.
    * @throws ParseException
    */
   public static Date parse(String string) throws ParseException
   {
      return DEFAULT_DATE_TIME_FORMAT.parse(string);
   }

   /**
    * Usefully and faster, if you are in the <code>Thread</code>.
    * @param string
    * @param dateFormat
    * @param timeZone
    * @return
    * @throws ParseException
    */
   public static Date parse(String string, DateFormat dateFormat, TimeZone timeZone) throws ParseException
   {
      dateFormat.getCalendar().setTimeZone(timeZone == null ? TimeZone.getDefault() : timeZone);
      return dateFormat.parse(string);
   }

   /**
    * 
    * @param string
    * @param locale
    * @param timeZone
    * @return
    * @throws ParseException
    */
   public static Date parse(String string, Locale locale, TimeZone timeZone) throws ParseException
   {
      return parse(string, DEFAULT_DATE_TIME_PATTERN, locale, timeZone);
   }

   /**
    * 
    * @param string
    * @param pattern
    * @return
    * @throws ParseException
    */
   public static Date parse(String string, String pattern) throws ParseException
   {
      return parse(string, pattern, Locale.getDefault());
   }

   /**
    * 
    * @param string
    * @param pattern
    * @param locale
    * @return
    * @throws ParseException
    */
   public static Date parse(String string, String pattern, Locale locale) throws ParseException
   {
      return new SimpleDateFormat(pattern == null ? DEFAULT_DATE_TIME_PATTERN : pattern, locale == null ? Locale.getDefault() : locale).parse(string);
   }

   /**
    * 
    * @param string
    * @param pattern
    * @param locale
    * @param timeZone
    * @return
    * @throws ParseException
    */
   public static Date parse(String string, String pattern, Locale locale, TimeZone timeZone) throws ParseException
   {
      return parse(string, new SimpleDateFormat(pattern == null ? DEFAULT_DATE_TIME_PATTERN : pattern, locale == null ? Locale.getDefault() : locale), timeZone);
   }

   /**
    * 
    * @param string
    * @param pattern
    * @param timeZone
    * @return
    * @throws ParseException
    */
   public static Date parse(String string, String pattern, TimeZone timeZone) throws ParseException
   {
      return parse(string, pattern, Locale.getDefault(), timeZone);
   }

   /**
    * Parse the date/time string and return as <code>Date</code>.<p>
    * The format is <code>DEFAULT_DATE_TIME_FORMAT</code>.  
    * @param string the date/time string.
    * @param timeZone
    * @return
    * @throws ParseException
    */
   public static Date parse(String string, TimeZone timeZone) throws ParseException
   {
      return parse(string, DEFAULT_DATE_TIME_PATTERN, Locale.getDefault(), timeZone);
   }

   /**
    * Parse the date string and return as <code>Date</code>.<p>
    * The pattern is <code>DEFAULT_DATE_PATTERN</code> is used.  
    * @param string the date string.
    * @param timeZone
    * @return
    * @throws ParseException
    */
   public static Date parseDate(String string, TimeZone timeZone) throws ParseException
   {
      return parse(string, DEFAULT_DATE_PATTERN, Locale.getDefault(), timeZone);
   }
   
   /**
    * Parse the date string and return as <code>Date</code>.<p>
    * The format is <code>DEFAULT_DATE_FORMAT</code>.  
    * The time zone is the <code>TimeZone.getDefault()</code>.
    * @param string the date string.
    * @return the <code>Date</code> parsed from string.
    * @throws ParseException
    */
   public static Date parseDate(String string) throws ParseException
   {
      return DEFAULT_DATE_FORMAT.parse(string);
   }

   /**
    * Parse the time string and return as <code>Date</code>.<p>
    * The format is <code>DEFAULT_TIME_FORMAT</code>.
    * The time zone is the <code>TimeZone.getDefault()</code>.
    * @param string the time string.
    * @return the <code>Date</code> parsed from string.
    * @throws ParseException
    */
   public static Date parseTime(String string) throws ParseException
   {
      return DEFAULT_TIME_FORMAT.parse(string);
   }
   
   
   public static void main(String[] args)
   {
      try
      {
         System.out.println(convert(new Date(), TimeZone.getTimeZone("Europe/Berlin"), TimeZone.getTimeZone("Europe/Istanbul")));
         System.out.println(parse("2000-01-10 12:50:00"));

         int dataPointId = 32776;
         int dataValueType = (0x3C00 & dataPointId) >> 10;
         System.out.println(dataPointId & 0x03FF);
         System.out.println(dataValueType);

      }
      catch (ParseException e)
      {
         e.printStackTrace();
      }
   }
   
}
